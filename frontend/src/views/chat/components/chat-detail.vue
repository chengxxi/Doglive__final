<template>
  <div class="chat-header">
    <i class="el-icon-arrow-left back-btn" @click="goBack"></i>
    <i class="el-icon-close close-btn" @click="changeOpen"></i>
    <span class="title">{{ chat.title }}</span>
  </div>
  <div class="chat-body"
    @scroll="scroll"
    v-loading="chat.loading"
    :element-loading-svg="svgInfo.path"
    :element-loading-svg-view-box="svgInfo.viewBox"
    :ref="el => { if(el) divs = el}"
    >
    <ChatMessage
      v-for="(message, index) in state.chatList"
      :key="index"
      :message="message"
    />
  </div>
  <div class="chat-input">
    <el-input type="textarea" v-model="state.content" @keyup.enter="sendMessage"></el-input>
    <el-button class="send-btn" :class="{active : !state.activeButton}" @click="sendMessage">전송</el-button>
    <i class="video-btn"><font-awesome-icon  :icon="[ 'fas' , 'video']"></font-awesome-icon></i>
    <!-- <i class="el-icon-video-camera video-btn"></i> -->
  </div>
</template>

<style scoped>
.chat-header{
  position: relative;
  font-size: 20px;
  width: 100%;
  height: 60px;
  padding: 20px;
  color: rgb(250, 250, 250); /* font color */
  background-color: rgb(215, 174, 164);
  box-shadow: 0px 0px 10px 5px rgb(230, 230, 230);
  text-align: center;
}
.title{
  font-size: 16px;
  font-weight: 500;
  vertical-align: super;
}
.chat-body{
  height: calc(100% - 140px); /* input 높이(80) + margin 높이(25) */
  position: relative;
  margin: 0;
  padding: 10px;
  overflow-y: auto;
}
.chat-body::-webkit-scrollbar{
  display: none;
}
.chat-input {
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 80px;
  padding-left: 10px;
  padding-right: 10px;
  background-color: white;
}
.send-btn{
  display: inline-block;
}
/* 아무것도 입력하지 않았을 때 전송 버튼 비활성화 */
.active{
  cursor:not-allowed !important;
  background-color: rgba(117, 87, 68, 0.2) !important;
}
:deep(.el-textarea){
  display: inline-block;
  width: 280px;
  overflow: auto;
}
:deep(.el-textarea__inner){
  width: 280px;
  height: 100%;
  border-style: none;
  padding-left: 0px;
  padding-right: 10px;
  resize: none;
}
:deep(.el-textarea__inner::-webkit-scrollbar){
  display: none;
}
:deep(.el-button){
  width: 60px;
  padding: 10px;
  color: white;
  background-color: rgba(117, 87, 68, 0.8);
}
:deep(.el-button:hover) {
  color: #f9f0e7;
  border-color: #755744;
  background-color: #755744;
}
.back-btn{
  cursor: pointer;
  position: absolute;
  top: 20px;
  left: 20px;
}
.video-btn{
  cursor: pointer;
  position: absolute;
  bottom: 10px;
  left: 15px;
  color: rgb(150, 150, 150);
}
.close-btn{
  cursor: pointer;
  position: absolute;
  top: 20px;
  right: 20px;
}
</style>

<script>
import svg from '@/assets/svgs/loading.js'
import ChatMessage from './chat-message.vue'
import { useStore } from 'vuex'
import { ref, reactive, computed, onUpdated, onMounted } from 'vue'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import _ from 'lodash'

export default {
  name: 'chat-detail',

  components: {
    ChatMessage,
  },

  props: {
    boardTitle: ''
  },

  setup () {
    const store = useStore()
    const userId = store.getters['root/getLoginUserInfo'].userId;
    const roomId = store.getters['root/getChat'].roomId;
    const divs = ref(null); // 요기
    const svgInfo = svg[0]
    let socket, client

    const state = reactive({
      recvList: [], // 서버로부터 받은 데이터 축적 (내림차순)
      chatList: [], // 사용자에게 보여줄 데이터 (아래에서 부터 위로 오름차순)
      content: '',
      activeButton: computed(()=> {
        if(state.content.trim().length > 0) return true
        else return false
      }),
    })
    const chat = reactive({
      init: true,
      open: computed(()=> store.getters['root/getChat'].open),
      title: computed(()=> store.getters['root/getChat'].title),
      loading: true,
      isLoading : computed(()=> chat.loading),
      page: 0,
      noMore: false,
      prev: 0,
      now : 0,
    })

    /* Methods*/

    // 이전 채팅방 로그 가져오기
    function fetchMessageLogs(){
      store.dispatch('root/requestChatMessageList', {roomId: roomId, page: chat.page, withCredentials: true})
        .then(function(result){
          var size = result.data.messageList.length;
          for(var i = 0; i < size; i++)
            state.recvList.push(result.data.messageList[i])
          // 다 받아왔으면
          if(size < 30)
            chat.noMore = true
          chat.loading = false
          state.chatList = [...state.recvList].reverse()
        })
        .catch(function(err){
        })
    }

    // 채팅방 입장 요청 + 메시지 받아오기
    function fetchJoin(){
      client.subscribe("/sub/chat/room/" + roomId, function(result){
        console.log(JSON.parse(result.body))
        state.recvList.push(JSON.parse(result.body))
        state.chatList.push(JSON.parse(result.body))
        chat.init = true;
      })
    }

    // Input에 입력한 메세지 전송
    function sendMessage(){
      console.log(state.activeButton)
      if(!state.activeButton) // 입력하지 않은 상태로는 전송 불가
        return

      client.send('/pub/chat/message', JSON.stringify({roomId: roomId, chatMessage: state.content.trim(), userId: userId}, {} ))
      state.content = ''
    }

    // 웹 소켓 연결 성공 시, 콜백 함수
    async function onConnected(){
      console.log("onConnected 함수 호출!")
      var load = await fetchMessageLogs()
      fetchJoin()
    }

    // 웹 소켓 통신 Connect
    function connect(){
      const url = "https://i5a501.p.ssafy.io/api/v1/chat-server" // 배포용
      //const url = "https://localhost:8081/api/v1/chat-server"
      socket = new SockJS(url, { transports: ['websocket', 'xhr-streaming', 'xhr-polling']})
      client = Stomp.over(socket)
      client.connect({withCredentials : true, userId : userId }
      ,frame => {
        console.log("연결 성공 : ", frame)
        onConnected()
      }
      ,error => {
        console.log("연결 실패 : ", error)
      })
    }

    // 닫기 버튼 : 채팅 Open 여부 변경
    function changeOpen(){
      store.commit('root/setChatOpen', !chat.open)
    }

    // 뒤로가기 버튼 : 채팅방 목록으로 넘어감
    function goBack(){
      store.commit('root/setChatMenu', 0)
      store.commit('root/setChatTitle', '')
      store.commit('root/setChatRoomId', '')
    }

    // 스크롤 하단 고정
    function scrollToBottom(el){
      el.value.scrollTop = 99999
    }

    async function scroll(state){ // 요기
      const scrollTop = divs.value.scrollTop;
      const scrollHeight = divs.value.scrollHeight;
      const clientHeight = divs.value.clientHeight;

      if(scrollTop  == 0  && !chat.noMore){
        console.log('불러와1')
        console.log(divs)

        chat.page += 1
        chat.prev = divs.value.scrollHeight
        await fetchMessageLogs()

        console.log(divs.value.scrollTop)
        console.log("prev", chat.prev)
        console.log("now", chat.now)
        divs.value.scrollTop += 1800;
      }
      console.log("scrollTo", divs.value.scrollTop);
    }

    onUpdated(()=> {
      if(chat.init){ // 처음 화면을 불러올 때만 스크롤을 맨 아래로 배치
        console.log(divs)
        chat.init = false;
        scrollToBottom(divs)
      }
      chat.now = divs.value.scrollHeight
    })

    connect()

    return { sendMessage, state, chat, changeOpen, goBack, svgInfo, divs, scroll }
  }
}
</script>
