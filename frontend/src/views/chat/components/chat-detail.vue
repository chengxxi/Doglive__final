<template>
  <div>
    <ul v-for="(item, index) in state.recvList" v-bind:key="index">
      <li>{{ item.username + " - " + item.chatMessage }}</li>
    </ul>
  </div>
  <br>
  <div>
    <input v-model="state.content">
    <button @click="sendMessage">전송</button>
  </div>
</template>

<style scoped>
</style>

<script>
import { useStore } from 'vuex'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import { reactive } from 'vue'

export default {
  name: 'chat-detail',
  setup () {
    const store = useStore()
    const userId = store.getters['root/getLoginUserInfo'];
    let socket, client

    const state = reactive({
      recvList: [],
      content: '',
    })

    /* Methods*/

    // 이전 채팅방 로그 가져오기
    function fetchMessageLogs(){
       store.dispatch('root/requestChatMessageList', {roomId: 1, withCredentials: true})
        .then(function(result){
          console.log(result.data)
          for(var i = 0; i < result.data.messageList.length; i++){
            state.recvList.push(result.data.messageList[i])
          }
          return true;
        })
        .catch(function(err){
          return false;
        })
    }

    // 채팅방 입장 요청 + 메시지 받아오기
    function fetchJoin(){
      console.log("fetchJoin 함수 호출!")
      // 1. 채팅방 입장 메시지
      // const data = JSON.stringify({ userId: userId, roomId: 1, chatMessage: "join" });
      // client.send("/pub/chat/join", data, {})

      // 2. 채팅방에서 메시지 받아오기
      client.subscribe("/sub/chat/room/" + 1, function(result){
        console.log(JSON.parse(result.body))
        state.recvList.push(JSON.parse(result.body))
      })
    }

    // Input에 입력한 메세지 전송
    function sendMessage(){
      client.send('/pub/chat/message', JSON.stringify({roomId: 1, chatMessage: state.content, userId: userId}, {} ))
      state.content = ''
    }

    // 웹 소켓 연결 성공 시, 콜백 함수
    async function onConnected(){
      console.log("onConnected 함수 호출!")
      var load = await fetchMessageLogs()

      if(!load){
        console.log("로딩중...")
      }
      fetchJoin()
    }

    // 웹 소켓 통신 Connect
    function connect(){
      const url = "http://localhost:8080/chat-server"
      socket = new SockJS(url, { transports: ['websocket', 'xhr-streaming', 'xhr-polling']})
      client = Stomp.over(socket)

      console.log("소켓 연결을 시도합니다.")
      client.connect({withCredentials : true, userId : userId }
      ,frame => {
        console.log("연결 성공 : ", frame)
        onConnected()
      }
      ,error => {
        console.log("연결 실패 : ", error)
      })
    }

    connect()

    return { sendMessage, state }
  }
}
</script>
