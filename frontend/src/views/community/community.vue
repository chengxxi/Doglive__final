<template>
  <div class="main-body main-padding">
    <el-card class="box-card" style="width:100%; margin-left:1%; margin-right:1%;" shadow="none">
      <bread-crumb></bread-crumb>
      <div style="margin-top:2%; margin-left:1%;">
        <h1 class="title">Community</h1>
        <p>강아지와 당신의 이야기</p>
      </div>

      <router-view ></router-view>
    </el-card>
    
  </div>
</template>

<style scoped>
.main-body {
  width: 100%;
  margin-left: 10%; 
  margin-right: 10%;
  margin-bottom : 50px;
}
.main-padding {
  padding-top: 50px;
}

.title {
  font-size: 2.5rem;
  font-weight: normal;
}
.el-button{
  margin-right:1%;
}
</style>

<script>
import { defineComponent, reactive, computed, ref } from "vue";
import $axios from "axios";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { onMounted } from "vue";
import BreadCrumb from "./components/bread-crumb.vue";
import CommunityBoardList from "./components/community-board-list.vue";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "community",
  components:{
      BreadCrumb,
      CommunityBoardList,
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();
    const state = reactive({
      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"].userId;
      })
    });

    

    return {state}


  }
}
</script>
