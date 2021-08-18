<template>
  <el-row>
    <div class="message my-card" v-if="isMine()">
      <span class="sendTime">{{ $filters.convertTime(message.sendTimeAt) }}</span>
      <el-card class="message-card mine">{{message.chatMessage}}</el-card>
    </div>
    <div v-else>
      <div class="username"> {{message.username}}</div>
      <div class="message your-card">
        <el-card class="message-card yours">{{message.chatMessage}}</el-card>
        <div class="sendTime">{{ $filters.convertTime(message.sendTimeAt) }}</div>
      </div>
    </div>

  </el-row>
</template>

<style scoped>
.message{
  display: flex;
  flex-direction: row;
  align-items:flex-end;
}
.my-card{
  float: right;
  clear: both;
  margin-left: 50px;
}
.your-card{
  float: left;
  clear: both;
  margin-right: 50px;
}
.username{
  flex: none;
  font-size: 14px;
  font-weight: 400;
}
.message-card{
  display: inline-block;
  font-size: 14px;
  min-height: 20px;
  max-width: 220px;
  text-align: left;
  margin-bottom: 10px;
}
.mine{
  background-color: #F9F0E7;
  position: static;
  float: right;
  clear: both;
  margin-left: 5px;
}
.yours{
  background-color: white;
  float: left;
  clear: both;
  margin-right: 5px;
}
.sendTime{
  display: inline-block;
  font-size: 11px;
  color: rgb(126, 126, 126);
  margin-bottom: 10px;
}
:deep(.el-card){
  box-shadow: 0 1px 8px 0 rgba(0, 0, 0, 0.1) !important;
}
:deep(.el-card__body){
  padding: 5px 10px 5px 10px;
  word-break:break-all;
}
</style>

<script>
import { useStore } from 'vuex'
export default {
  name: 'chat-message',

  props: {
    message: Object,
  },

  setup(props){
    const store = useStore()
    const userId = store.getters['root/getLoginUserInfo'].userId;
    const isMine = function(){
      if(props.message.userId == userId) // 내 메시지면 true
        return true
      else
        return false
    }

    return { isMine }
  }
}
</script>
