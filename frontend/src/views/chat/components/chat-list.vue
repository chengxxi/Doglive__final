<template>
  <div class="chat-background"/>
  <div class="chat-header">
    <div class="chat-title">DOG-LIVE 💬</div>
    <i class="el-icon-close close-btn" @click="changeOpen"></i>
    <div class="chat-subtitle">채팅을 통해 임보/입양을 상담해보세요</div>
  </div>
  <div class="chat-body">
    <ChatCard
      v-for="(card, idx) in state.roomList"
      :key="idx"
      :card="card"
      @click="enterRoom(card)"
    />
  </div>
</template>

<style scoped>
.chat-background {
  position: absolute;
  width: 100%;
  height: 200px;
  background: linear-gradient(180deg, rgb(215, 174, 164) 0%, rgb(215, 174, 164) 50%, rgb(250, 250, 250)) 100% center;
}
.chat-header{
  position: relative;
  font-size: 20px;
  width: 100%;
  height: 120px;
  padding: 20px;
  color: rgb(250, 250, 250); /* font color */
}
.chat-title{
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 10px;
}
.chat-subtitle{
  font-size: 14px;
  font-weight: 400;
}
.chat-body{
  min-height: 400px;
  max-height: 470px;
  position: relative;
  margin: 0;
  padding: 10px;
  overflow-y: scroll;
}
.chat-body::-webkit-scrollbar{
  display: none;
}
.close-btn{
  cursor: pointer;
  position: absolute;
  top: 20px;
  right: 20px;
}
</style>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import ChatCard from "./chat-card.vue";

export default {
  name: 'chat-list',

  components:{
    ChatCard
  },

  setup () {
    const store = useStore()
    const state = reactive({
      roomList: [],
    })
    const chat = reactive({
      open: computed(()=> store.getters['root/getChat'].open),
    })

    // 채팅방에 입장할 때, chatRoom 정보를 넘겨줌
    const enterRoom = function(card){
      store.commit('root/setChatMenu', 1); // chat-detail.vue로 이동
      store.commit('root/setChatRoomId', card.chatRoom.id);
      store.commit('root/setChatTitle', card.boardTitle)
    }

    // 현재 로그인한 유저의 userId 쿠키를 헤더에 포함하여 전송
    store.dispatch('root/requestChatRoomList', {withCredentials: true})
    .then(function(result){
      console.log(result);
      var chatRoomList = result.data.chatRoomList
      for(var i = 0; i < chatRoomList.length; i++){
        state.roomList.push({
          chatRoom : chatRoomList[i].chatRoom,
          boardTitle: chatRoomList[i].counselingHistory.boardTitle,
          user1 : chatRoomList[i].userNameList[0],
          user2 : chatRoomList[i].userNameList[1],
          unRead : chatRoomList[i].unReadCount,
        })
      }
    })
    .catch(function(err){
    })

    // 닫기 버튼 : 채팅 Open 여부 변경
    function changeOpen(){
      store.commit('root/setChatOpen', !chat.open)
    }

    return { state, chat, enterRoom, changeOpen }
  }
}
</script>
