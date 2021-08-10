<template>
  <div class="main-body main-padding">
    <el-card class="box-card " style="width:100%;" shadow="none">
      <bread-crumb></bread-crumb>

      <div style="margin-top:50px">
        <h1 class="title">Community</h1>
        <p>강아지와 당신의 이야기</p>
      </div>

      <div>

      </div>
      <el-space wrap>
        <!-- 카테고리 Filter -->

      </el-space>




      <community-board-list></community-board-list>
    </el-card>
    
  </div>
</template>

<style scoped>
/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%, 위 여백 50px) */
.main-body {
  width: 70%;
  margin-left: 20%; /* 페이지 양옆 200px여백 -> 10% */
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
