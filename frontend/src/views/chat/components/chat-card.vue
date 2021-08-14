<template>
  <el-card class="box-card">
    <div class="room-title">
      {{ card.boardTitle }}
    </div>
    <div class="room-user">
      <i class="el-icon-user-solid"></i>
      {{ card.user1 }}
      {{ card.user2 }}
    </div>
    <el-badge v-if="card.unRead > 0" :value="card.unRead" class="message-badge" type="danger"></el-badge>
  </el-card>
</template>

<style scoped>
.box-card{
  position: relative;
  height: 80px;
  border-radius: 10px;
  cursor: pointer;
  margin-bottom: 10px;
}
.box-card:hover{
  background-color: rgb(248, 248, 248);
}

:deep(.el-card__body){
  height: inherit;
  padding: 15px;
}
.room-title{
  margin-bottom: 5px;
}
.room-user{
  font-size: 14px;
  font-weight: 400;
  color: rgb(126, 126, 126) !important;
}
.message-badge{
  position: absolute;
  right: 15px;
  top: 32px;

}
</style>

<script>
export default {
  name: 'chat-card',

  props: {
    card: Object
  },

  setup(props){
    console.log(props)
  }
}
</script>
