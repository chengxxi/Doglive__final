<template>
  <table :style="{width : 300, height: 500}" v-for="(item, index) in state.roomList" :key="index">
    <tr>
      <td>{{ item.chatRoom.name }}</td>
      <td>{{ item.userNameList }}</td>
      <td><button @click="enterRoom(item.id)">입장</button></td>
    </tr>
  </table>
  <div :style="{width: 500, height: 500, margin: auto}">
    <el-button type="text" @click="open">채팅방생성</el-button>
  </div>
</template>

<style scoped>
@import url("//unpkg.com/element-ui@2.15.5/lib/theme-chalk/index.css");
</style>

<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
export default {

  name: 'chat-list',
  setup () {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      roomList: [],
    })

    const createRoom = function(value){
      store.dispatch('root/createChatRoom', value)
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

    const open = function(){
      ElMessageBox.prompt('Please input your e-mail', 'Tip', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
      }).then(({value}) => {
        createRoom(value) // 채팅방 생성
        ElMessage({
          type:'success',
          message: '채팅방 이름 : ' + value
        })
      }).catch(()=>{
        ElMessage({
          type:'info',
          message: '채팅방 생성되지 않았습니다.'
        })
      })
    }

    // 현재 로그인한 유저의 userId 쿠키를 헤더에 포함하여 전송
    store.dispatch('root/requestChatRoomList', {withCredentials: true})
    .then(function(result){
      console.log(result.data.chatRoomList)
      var chatRoomList = result.data.chatRoomList
      for(var i = 0; i < chatRoomList.length; i++){
        state.roomList.push({
          chatRoom : chatRoomList[i].chatRoom,
          userNameList : chatRoomList[i].userNameList
        })
      }
      console.log(state.roomList)
    })
    .catch(function(err){
      console.log(err)
    })


    return { state, createRoom, enterRoom, open }
  }
}
</script>
