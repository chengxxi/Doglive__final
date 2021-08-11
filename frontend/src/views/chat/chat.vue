<template>

  <div class="chat-wrapper" :class="{enter : chat.open, leave : !chat.open}" v-if="chat.open">
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
  margin: 0;
  width: 360px;
  height: 80%;
  min-height: 520px;
  max-height: 720px;
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
@keyframes fade-out{
  from { opacity: 1; }
  to { opacity: 0; }
}
@keyframes fade-in{
  from { opacity: 0; }
  to { opacity: 1; }
}
</style>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import ChatList from './components/chat-list.vue'
import ChatDetail from './components/chat-detail.vue'

export default {
  name: 'chat',

  components: {
    ChatList,
    ChatDetail
  },

  setup(){
    const store = useStore()
    const state = reactive({
      login: computed(()=> {
        if(store.getters['root/getLoginUserInfo'].userId === null)
          return false
        else
          return true
      })
    })
    const chat = reactive({
      open: computed(()=> store.getters['root/getChat'].open),
      menu: computed(()=> store.getters['root/getChat'].menu),
      isActive : false,
    })

    function changeOpen(){
      store.commit('root/setChatOpen', !chat.open)
      chat.isActive = true;
    }
    return { state, chat, changeOpen };
  },
}
</script>
