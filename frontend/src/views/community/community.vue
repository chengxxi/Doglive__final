<template>
  <div class="main-body main-padding">
    <el-card class="box-card " style="width:100%;" shadow="none">
      <bread-crumb></bread-crumb>
      <div style="margin-top:50px">
        <h1 class="title">Community</h1>
        <p>강아지와 당신의 이야기</p>
      </div>
      <community-board-list></community-board-list>
    </el-card>
    
  </div>
</template>

<style scoped>
.main-body {
  width: 60%;
  margin-left: 20%; 
  margin-right: 20%;
  margin-bottom : 50px
}
.main-padding {
  padding-top: 50px;
}

.title {
  font-size: 2.5rem;
  font-weight: normal;
}
</style>

<script>

import $axios from "axios";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { onMounted } from "vue";
import { defineComponent, reactive, computed, ref } from "vue";
import BreadCrumb from "./components/bread-crumb.vue";
import CommunityBoardList from "./components/community-board-list.vue";

export default {
  name: "community",
  components:{
      BreadCrumb,
      CommunityBoardList
  },
  setup() {


  }
}
</script>
