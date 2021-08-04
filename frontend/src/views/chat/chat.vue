<template>
  <iframe></iframe>
</template>

<style scoped>
</style>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import { reactive } from '@vue/reactivity'

export default {
  name: 'chat',

  setup () {
    const recvList = reactive([])
    let socket, client

    const onConnected = function(){
      console.log("연결중")

      // // 특정 채팅방 입장 요청
      // client.send("/pub/chat/join", {}, JSON.stringify({userId: "1827439689", roomId: 1, time: new Date()}))

      // 특정 채팅방에서 메시지 받아오기
      // client.subscribe("/sub/chat/room/" + 1, function(result){
      //   console.log('recv : ' + result.body)
      //   recvList.push(JSON.parse(result.body))
      // })
      console.log("연결완료!")
    }

    const onError = function(err){
      console.log("에러발생!")
      console.log(err)
    }

    // 웹 소켓 통신 Connect
    const connect = function(){
      const url = "http://localhost:8080/chat-server"
      socket = new SockJS(url, { transports: ['websocket']})
      client = Stomp.over(socket)

      console.log("소켓 연결을 시도합니다.")
      console.log(socket)
      console.log(client)
      client.connect({}
      ,frame => {
        console.log("연결 성공 : ", frame)
      }
      ,error => {
        console.log("연결 실패 : ", error)
      })
    }

    // 소켓통신 요청
    connect()

    return {}
  },

}
</script>
