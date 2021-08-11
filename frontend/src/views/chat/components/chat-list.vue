<template>
  <div class="chat-background"/>
  <div class="chat-header">
    <span class="chat-title">독립</span>
    <i class="el-icon-close close-btn" @click="changeOpen"></i>
  </div>
  <div class="chat-body">
    <ChatCard
      v-for="(card, idx) in state.roomList"
      :key="idx"
      :card="card"
      @click="enterRoom(card.chatRoom)"
    />
  </div>
  <div class="chat-footer"/>
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
}
.chat-body{
  position: relative;
  margin: 0;
  padding: 10px;
  overflow-y: scroll;
}
.chat-body::-webkit-scrollbar{
  display: none;
}
.chat-footer {
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 50px;
  border-top: solid 1px rgb(230, 230, 230);
}
.close-btn{
  cursor: pointer;
  width: 30px;
  height: 30px;
  position: absolute;
}
</style>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import ChatCard from "./chat-card.vue";

export default {

  name: 'chat-list',

  components:{
    ChatCard
  },

  setup () {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      roomList: [],
    })
    const chat = reactive({
      open: computed(()=> store.getters['root/getChat'].open),
      isActive : false,
    })

    // 채팅방에 입장할 때, chatRoom 정보를 넘겨줌
    const enterRoom = function(chatRoom){
      console.log(chatRoom)
      store.commit('root/setChatMenu', 1); // chat-detail.vue로 이동
      store.commit('root/setChatRoomId', chatRoom.id);
      store.commit('root/setChatTitle', chatRoom.name);
      // router.push({name: 'chat-detail', params: {roomId : id}})
    }

    // 현재 로그인한 유저의 userId 쿠키를 헤더에 포함하여 전송
    store.dispatch('root/requestChatRoomList', {withCredentials: true})
    .then(function(result){
      console.log(result.data.chatRoomList)
      var chatRoomList = result.data.chatRoomList
      for(var i = 0; i < chatRoomList.length; i++){
        state.roomList.push({
          chatRoom : chatRoomList[i].chatRoom,
          user1 : chatRoomList[i].userNameList[0],
          user2 : chatRoomList[i].userNameList[1],
          unRead : chatRoomList[i].unReadCount
        })
      }
    })
    .catch(function(err){
      console.log(err)
    })

    function changeOpen(){
      store.commit('root/setChatOpen', !chat.open)
      chat.isActive = true;
    }
    return { state, chat, enterRoom, changeOpen,}
  }
}
</script>
