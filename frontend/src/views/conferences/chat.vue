<template>
  <div class="chat-main">
    <div v-for="chat in state.chatArray" :key="chat" class="chatlog">
      {{chat}}
    </div>
    <el-input
      class='chatinput'
      type='text'
      v-model='chatString'
      @keyup.enter='sendMessage'
    />
  </div>
</template>

<style scoped>
.chatlog {
  width: 50%;
  height: 400px;
  background-color: white;
  border: 3px solid pink;
  padding: 9px;
}
.chatinput {
  width: 50%;
  height: 15%;
  text-align: left;
  padding-left: 15px;
}
</style>

<script>
import { onBeforeMount, reactive } from 'vue';
export default {
  name: 'Chat',
  props:{
    session:Object
  },
  setup(props) {
    const state = reactive({
      session: undefined,
      publisher: undefined,
      subscribers:[],
      myUserName: '',
      chatArray: [],
      chatString:'',
    })

    onBeforeMount(() => {
      console.log('chat.vue > props.session 🔽')
      console.log(props.session)
      state.session = props.session

      state.session.on('signal:chatSend', (event) => {
        console.log('arrive chat')
        console.log(event.data)
        console.log()
      })
    })

    const sendMessage = function() {
      console.log('sendMessage > 사용자가 enter 쳤다')
      console.log(state.chatString)
      state.session.signal({
        data:state.chatString,
        to:[],
        type: 'chatSend'
      })
      .then(()=> {
        console.log('Message successfully send❗')
        state.chatString='';
      })
      .catch(error => {
        console.log(error)
      })
    }
    return {state, onBeforeMount, sendMessage}
  }
  // mounted() {
  //   console.log("Chat: ", this.socket);
  //   /* chatting */
  //   this.socket.on("chat", (user, msg) => {
  //     // console.log(name, msg);
  //     var chatLog = document.getElementById("chatLog");
  //     chatLog.append(user.username + ": " + msg + "\n");
  //     chatLog.scrollTop = chatLog.scrollHeight;
  //   });
  // },
};
</script>


