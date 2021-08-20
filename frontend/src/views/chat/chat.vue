<template>
  <div class="chat-wrapper" :class="{enter : chat.open, leave : !chat.open, click: (chat.open && chat.click)}" v-if="chat.open">
    <ChatList v-if="chat.menu == 0"/>
    <ChatDetail v-else-if="chat.menu == 1"/>
  </div>
  <div class="chat-background">
    <i class="el-icon-chat-dot-round chat-button" @click="changeOpen"/>
  </div>
</template>

<style scoped>
.chat-wrapper{
  z-index: 10;
  position: fixed;
  right: 25px;
  bottom: 25px;
  width: 360px;
  height: 100%;
  min-height: 520px;
  max-height: 580px;
  border-radius: 16px;
  background-color: rgb(250, 250, 250);
  box-shadow: 2px 2px 10px 5px rgba(0, 0, 0, 0.1);
  overflow-y: hidden;
}
.enter {
  display: block;
}
.leave {
  display: none;
}
.click {
  animation: 0.25s ease-out 0s 1 normal scaleUp;
}
.chat-background{
  z-index: 5;
  position: fixed;
  right: 0;
  bottom: 0;
  margin-right: 2%;
  margin-bottom: 2%;
  width: 70px;
  height: 70px;
  background-color: #D7AEA4;
  border-radius: 50%;
  vertical-align: middle;
  box-shadow: 2px 3px 5px 2px rgba(0, 0, 0, 0.1);
}
.chat-button{
  position: absolute;
  margin-left: 10px;
  margin-top: 10px;
  font-size: 50px !important;
  color: rgb(250, 250, 250);
  cursor: pointer;
}
/* 애니메이션 */
@keyframes scaleUp{
  from { transform: scale(0.0); }
  to { transform: scale(1.0); }
}
</style>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import ChatList from './components/chat-list.vue'
import ChatDetail from './components/chat-detail.vue'
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: 'chat',

  components: {
    ChatList,
    ChatDetail
  },

  setup(){
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      login: computed(()=> {
        var userId = store.getters['root/getLoginUserInfo'].userId;
        if( userId === null || userId == "" || userId === undefined)
          return false
        else
          return true
      })
    })
    const chat = reactive({
      open: computed(()=> store.getters['root/getChat'].open),
      menu: computed(()=> store.getters['root/getChat'].menu),
    })

    function changeOpen(){
      chat.click = true;
      if(!state.login){ // 로그인이 되어있지 않으면 로그인 화면으로 이동
        createToast("로그인해야 이용 가능하개🐕‍🦺💨", {
          hideProgressBar: "true",
          timeout: 4500,
          showIcon: "true",
          toastBackgroundColor: "#c49d83",
          position: "bottom-left",
          transition: "bounce",
          type: "warning"
        });
        router.push({ path: '/login'})
      }
      else
        store.commit('root/setChatOpen', !chat.open)
    }
    return { state, chat, changeOpen };
  },
}
</script>
