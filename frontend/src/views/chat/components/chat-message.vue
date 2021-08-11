<template>
  <el-card class="box-card">
    <div class="room-title">
      {{ card.chatRoom.name }}
    </div>
    <div class="room-user">
      <i class="el-icon-user-solid"></i>
      {{ card.userNameList}}
    </div>

    <el-badge :value="card.unReadCount" class="message-badge" type="danger"></el-badge>
  </el-card>
</template>

<style scoped>
</style>

<script>
import { computed, reactive } from 'vue'
export default {
  name: 'chat-message',

  props: {
    message: Object,
  },

  setup(){

  }
}
</script>
