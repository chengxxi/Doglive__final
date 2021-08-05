<template>
  <table :style="{width : 300, height: 500}" v-for="(item, index) in state.roomList" :key="index">
    <tr>
      <th>채팅방 이름</th>
    </tr>
    <tr>
      <td>{{ item.name }}</td>
      <td><button @click="enterRoom(item.id)">입장</button></td>
    </tr>
  </table>
  <div :style="{width: 500, height: 500, margin: auto}">
    <button @click="createRoom">채팅방생성</button>
  </div>
</template>

<style scoped>
</style>

<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'chat-list',
  setup () {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      roomList: [],
    })

    const createRoom = function(){
      store.dispatch('root/createChatRoom', "chat1")
      .then(function(result){
        console.log("채팅방 생성완료!")
      })
      .catch(function(err){
        console.log("채팅방 생성실패!")
      })
    }

    const enterRoom = function(id){
      console.log(id)
      router.push({name: 'chat-detail', params: {roomId : id}})
    }

    store.dispatch('root/requestChatRoomList')
    .then(function(result){
      console.log(result.data)
      state.roomList = result.data
    })
    .catch(function(err){
      console.log(err)
    })
    return { state, createRoom, enterRoom }
  }
}
</script>
