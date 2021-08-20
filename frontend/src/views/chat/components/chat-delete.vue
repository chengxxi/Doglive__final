<template>
  <el-card class="box-card" v-if="state.show">
    <p class="warning"><font-awesome-icon :icon="['fa', 'check-circle']"></font-awesome-icon> warning</p>
    <p>채팅방과 모든 채팅내역은 삭제됩니다.<br> 정말 나가시겠습니까?</p>
    <div>
      <el-button @click="deleteChatRoom()">확인</el-button>
      <el-button @click="cancel()" class="cancel">취소</el-button>
    </div>
  </el-card>
</template>

<style scoped>
.box-card{
  width: 300px;
  height: 160px;
  font-size: 15px;
  position: absolute;
  right: 30px;
  bottom: 225px;
  border-radius: 4px;
  border: solid 1px lightgray;
  text-align: center;
  box-shadow: 0px 0px 0px 1000px rgb(0 0 0 / 30%) !important;
}
.warning{
  font-weight: 600;
  color: rgba(117, 87, 68);
}
.cancel{
  background-color: rgb(190, 190, 190) !important;
}
.cancel:hover{
  border: solid 1px rgb(180, 180, 180) !important;
}
:deep(.el-card__body){
  padding: 10px;
}
</style>

<script>
import { reactive } from '@vue/reactivity';
import { useStore } from "vuex";
import { computed } from '@vue/runtime-core';
import { createToast } from "mosha-vue-toastify";

export default {
  name: 'chat-delete',

  props: {
    roomId : Number,
    show : Boolean
  },

  setup(props){
    const store = useStore();
    const state = reactive({
      show : computed(() => store.getters["root/getChat"].exitMessage )
    })

    const deleteChatRoom = function(){
      store.dispatch("root/requestDeleteChatRoom", {withCredentials : true, roomId : props.roomId })
      .then(function(result){
        createToast("채팅방이 삭제되었어요 💨💨", {
          hideProgressBar: "true",
          timeout: 4500,
          showIcon: "true",
          toastBackgroundColor: "#7eaa72",
          position: "bottom-left",
          transition: "bounce",
          type: "success"
        });

        store.commit("root/setChatMenu", 0);
        store.commit("root/setChatTitle", "");
        store.commit("root/setChatRoomId", "");
        store.commit("root/setExitMessage", false);
      })
      .catch(function(err){
      })
    }

    const cancel = function(){
      store.commit("root/setExitMessage", false);
    }
    return { deleteChatRoom, cancel, state };
  }
}
</script>
