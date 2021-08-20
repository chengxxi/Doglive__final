<template>
  <div class="chat-header">
    <i class="el-icon-arrow-left back-btn" @click="goBack"></i>
    <i class="el-icon-close close-btn" @click="changeOpen"></i>
    <span class="title">
      {{ chat.title }}
    </span>

  </div>
  <div v-if="state.isOpenConference" class="notice-bar-open">
    <a class="el-icon-arrow-up" @click="changeOpenConference"></a>
    <a class="video-btn" @click="createConference(chat.title)" style="align">
      <font-awesome-icon :icon="['fas', 'video']"></font-awesome-icon>
      화상회의 하러가기
    </a>
  </div>
  <div v-else style="width:100%" class="notice-bar-close" >
    <button class="open-conference " type="button" @click="changeOpenConference" style="align-content:center;">
      <i class="el-icon-message-solid"></i>
    </button>
  </div>
  <div
    class="chat-body"
    @scroll="scroll"
    v-loading="chat.loading"
    :element-loading-svg="svgInfo.path"
    :element-loading-svg-view-box="svgInfo.viewBox"
    :ref="el => { if (el) divs = el;}"
  >
    <ChatMessage
      v-for="(message, index) in state.chatList"
      :key="index"
      :message="message"
    />
  </div>
  <div class="chat-input">
    <el-input
      type="textarea"
      v-model="state.content"
      @keyup.enter="sendMessage"
    ></el-input>
    <el-button
      class="send-btn"
      :class="{ active: !state.activeButton }"
      @click="sendMessage"
      >전송</el-button
    >
    <font-awesome-icon class="exit-btn" :icon="['fa', 'door-open']" @click="deleteChatRoom()"></font-awesome-icon>

    <chat-delete :roomId="chat.roomId"/>
  </div>
</template>

<style scoped>
.chat-header {
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
.title {
  font-size: 16px;
  font-weight: 500;
  vertical-align: super;
}
.notice-bar-open {
    position: absolute;
    top: 60px;
    left: 0;
    padding: 15px;
    width: 100%;
    background-color: rgba(255, 255, 255, 0.85);
    display: flex;
    flex-direction: row;
    z-index: 99;
}
.notice-bar-close {
    padding: 10px;
    width: 90%;
    position: absolute;
    top: 60px;
    display: flex;
    z-index: 99;
}
.open-conference {
  margin-left: 85%;
  background: rgba(0,0,0,0.4);
  border: 0px solid black;
  border-radius: 100%;
  width: 12%;
  height: 40px;
  padding-top: 4px;
  margin-right: 0;
}
:deep(.el-icon-arrow-up) {
  cursor: pointer;
}
:deep(.el-icon-message-solid) {
  color: white;
  font-size: 1.45rem;
}
.chat-body {
  height: calc(100% - 140px); /* input 높이(80) + margin 높이(25) */
  position: relative;
  margin: 0;
  padding: 10px;
  overflow-y: auto;
}

.chat-body::-webkit-scrollbar {
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
.send-btn {
  display: inline-block;
}
/* 아무것도 입력하지 않았을 때 전송 버튼 비활성화 */
.active {
  cursor: not-allowed !important;
  background-color: rgba(117, 87, 68, 0.2) !important;
}
:deep(.el-textarea) {
  display: inline-block;
  width: 280px;
  overflow: auto;
}
:deep(.el-textarea__inner) {
  width: 280px;
  height: 100%;
  border-style: none;
  padding-left: 0px;
  padding-right: 10px;
  resize: none;
}
:deep(.el-textarea__inner::-webkit-scrollbar) {
  display: none;
}
:deep(.el-button) {
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
.back-btn {
  cursor: pointer;
  position: absolute;
  top: 20px;
  left: 20px;
}
.video-btn {
  cursor: pointer;
  bottom: 10px;
  left: 15px;
  margin-left: 22%;
  color: rgb(0, 0, 0, 0.85);
}
.exit-btn{
  cursor: pointer;
  position: absolute;
  bottom: 15px;
  left: 15px;
  color: rgb(150, 150, 150);
}
.close-btn {
  cursor: pointer;
  position: absolute;
  top: 20px;
  right: 20px;
}
</style>

<script>
import svg from "@/assets/svgs/loading.js";
import ChatMessage from "./chat-message.vue";
import ChatDelete from "./chat-delete.vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { ref, reactive, computed, onUpdated } from "vue";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

export default {
  name: "chat-detail",

  components: {
    ChatMessage,
    ChatDelete
  },

  setup() {
    const store = useStore();
    const router = useRouter();
    const userId = store.getters["root/getLoginUserInfo"].userId;
    const roomId = store.getters["root/getChat"].roomId;
    const divs = ref(null); // 요기
    const svgInfo = svg[0];
    let socket, client;

    const state = reactive({
      recvList: [], // 서버로부터 받은 데이터 축적 (내림차순)
      chatList: [], // 사용자에게 보여줄 데이터 (아래에서 부터 위로 오름차순)
      content: "",
      activeButton: computed(() => {
        if (state.content.trim().length > 0) return true;
        else return false;
      }),
      isOpenConference: true,
    });
    const chat = reactive({
      init: true, // 처음 채팅방 들어왔을 때
      recv: false, // 새로운 메세지를 받았을 때
      loaded: false, // 이전 로그를 페이지 단위로 불러올 때,
      open: computed(() => store.getters["root/getChat"].open),
      title: computed(() => store.getters["root/getChat"].title),
      roomId: computed(() => store.getters["root/getChat"].roomId),
      loading: true,
      isLoading: computed(() => chat.loading),
      page: 0,
      noMore: false,
      prevScrollHeight: 0,
    });

    /* Methods*/

    // 이전 채팅방 로그 가져오기
    function fetchMessageLogs() {
      return new Promise(function(resolve, reject){
      store
        .dispatch("root/requestChatMessageList", {
          roomId: roomId,
          page: chat.page,
          withCredentials: true
        })
        .then(function(result) {
          var size = result.data.messageList.length;
          for (var i = 0; i < size; i++)
            state.recvList.push(result.data.messageList[i]);
          // 다 받아왔으면
          if (size < 30) chat.noMore = true;
          chat.loading = false;
          chat.loaded = true;
          state.chatList = [...state.recvList].reverse();
          resolve(result)
        })
        .catch(function(err) {
          resolve(err)
        });
      });
    }

    // 채팅방 입장 요청 + 메시지 받아오기
    function fetchJoin() {
      client.subscribe("/sub/chat/room/" + roomId, function(result) {
        var msg = JSON.parse(result.body);
        state.recvList.push(msg);
        state.chatList.push(msg);
        chat.recv = true;
      });
    }

    // Input에 입력한 메세지 전송
    function sendMessage() {
      if (!state.activeButton) // 입력하지 않은 상태로는 전송 불가
        return;

      client.send("/pub/chat/message", JSON.stringify({ roomId: roomId, chatMessage: state.content.trim(), userId: userId }, {} ) );
      state.content = "";
    }

    // 웹 소켓 연결 성공 시, 콜백 함수
    async function onConnected() {
      var load = await fetchMessageLogs();
      fetchJoin();
    }

    // 웹 소켓 통신 Connect
    function connect(){
      const url = "https://i5a501.p.ssafy.io/api/v1/chat-server" // 배포용
      //const url = "https://localhost:8081/api/v1/chat-server"
      socket = new SockJS(url, { transports: ['websocket', 'xhr-streaming', 'xhr-polling']})
      client = Stomp.over(socket)
      client.connect({withCredentials : true, userId : userId }
      ,frame => {
        console.log("연결 성공", frame);
        onConnected()
      }
      ,error => {
        console.log("연결 실패", error);
      })
    }

    // 닫기 버튼 : 채팅 Open 여부 변경
    function changeOpen() {
      store.commit("root/setChatOpen", !chat.open);
      store.commit("root/setExitMessage", false);
    }

    // 닫기 버튼 : 화상회의 Open 여부 변경
    function changeOpenConference() {
      state.isOpenConference = !state.isOpenConference
    }

    // 뒤로가기 버튼 : 채팅방 목록으로 넘어감
    function goBack() {
      // 나가기 전, 지금까지 받았던 메세지들을 read 처리요청
      store.dispatch("root/requestChatMessageUpdate", {roomId : roomId, withCredentials : true})
      .then(function(result){
      }).catch(function(err){
      });
      store.commit("root/setChatMenu", 0);
      store.commit("root/setChatTitle", "");
      store.commit("root/setChatRoomId", "");
      store.commit("root/setExitMessage", false);
    }

    function deleteChatRoom(){
      store.commit("root/setExitMessage", true);
    }

    // 스크롤 하단 고정
    function scrollToBottom(el) {
      el.value.scrollTop = 99999;
    }

    // scrollTop == 0 이면 다음 페이지 호출
    async function scroll(state) {
      if (divs.value.scrollTop == 0 && !chat.noMore) {
        chat.page += 1;
        await fetchMessageLogs();
      }
    }

    // 화상회의 연결
    function createConference(title) {
      // 공고 정보 받아오기
      const conference = {
        roomId: store.getters["root/getChat"].roomId,
        title: title,
        thumbnailUrl: "",
        writer: "",
        reader: "",
        fromChat: true
      };
      changeOpen(); // 채팅창 닫기
      store.commit("root/setConference", conference);
      moveConference();
    }

    function moveConference() {
      router.push({ name: "conference" });
    }

    onUpdated(() => {
      if (chat.init || chat.recv) {
        // 처음 화면을 불러올 때와 새로운 메세지를 받을 때만 스크롤을 맨 아래로 배치
        chat.init = false;
        chat.recv = false;
        scrollToBottom(divs);
      }

      if(chat.loaded){
        // 처음 화면을 불러오는 게 아닌 이전 로그를 페이지 단위로 불러올 때 호출
        chat.loaded = false;
        if(divs.value.scrollTop == 0){
          divs.value.scrollTop = (divs.value.scrollHeight - chat.prevScrollHeight) // 시작위치 재설정
        }
        // 이전 스크롤 정보를 저장
        chat.prevScrollHeight = divs.value.scrollHeight
      }
    });

    connect();

    return {
      sendMessage,
      router,
      state,
      chat,
      changeOpen,
      goBack,
      svgInfo,
      divs,
      scroll,
      createConference,
      deleteChatRoom,
      changeOpenConference
    };
  }
};
</script>
