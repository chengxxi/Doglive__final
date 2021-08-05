<template>
  <div>
    <ul v-for="(item, index) in state.recvList" v-bind:key="index">
      <li>{{ item.userId + " - " + item.message }}</li>
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
    const userId = Math.floor(Math.random() * (10000 - 1) + 1);
    let socket, client

    const state = reactive({
      recvList: [],
      content: '',
    })

    const sendMessage = function(){
      client.send('/pub/chat/message', JSON.stringify({roomId: 1, message: state.content, userId: userId}, {} ))
      state.content = ''
    }

    const onConnected = function(){
      // 특정 채팅방 입장 요청
      const data = JSON.stringify({ userId: userId, roomId: 1, message: "join" });
      client.send("/pub/chat/join", data, {})

      // 특정 채팅방에서 메시지 받아오기
      client.subscribe("/sub/chat/room/" + 1, function(result){
        console.log(JSON.parse(result.body))
        state.recvList.push(JSON.parse(result.body))
      })
    }

    // 웹 소켓 통신 Connect
    const connect = function(){
      const url = "http://localhost:8080/api/v1/chat-server"
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
