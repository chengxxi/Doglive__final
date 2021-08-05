<template>
  <div class="main-body main-padding">
    <h1 class="title">입양하기 게시물</h1>
    <p>입양을 기다리는 아이들</p> <!-- 상세 문구 수정 필요 -->

    <el-space wrap>
      <!-- 입양 공고 Filter -->
      <AdoptFilter/>

      <!-- 내 MBTI와 맞는 강아지 찾기 버튼 -->
      <div class="MBTIbtn">
        <el-button type="outline-primary" @click="dialogVisible = true" round>나와 맞는 강아지 찾기</el-button>
        <!-- MBTI 기능 연계가 완료되면 사라질 코드 -->
        <el-dialog
          title="Notice"
          v-model="dialogVisible"
          width="30%"
        >
          <span>준비 중인 기능입니다.</span>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogVisible = false">Close</el-button>
            </span>
          </template>
        </el-dialog>
        <!-- 임시 코드 (끝) -->
      </div>

      <!-- 입양 공고 작성하기 버튼 -->
      <div class="newAdoptbtn">
        <el-button type="outline-primary" @click="dialogVisible2 = true" round>입양 공고 작성하기</el-button>
        <!-- 공고 작성 기능 연계가 완료되면 사라질 코드 -->
        <el-dialog
          title="Notice"
          v-model="dialogVisible2"
          width="30%"
        >
          <span>준비 중인 기능입니다.</span>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogVisible2 = false">Close</el-button>
            </span>
          </template>
        </el-dialog>
        <!-- 임시 코드 (끝) -->
      </div>

    </el-space>





    <AdoptList/>

  </div>
</template>

<style>
/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%, 위 여백 50px) */
.main-body{
  width: 100%;
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 10%;
}
.main-padding{
  padding-top: 50px;
}


.title {
  font-size: 2.5rem;
  font-weight: normal;
}
</style>

<script>
import AdoptFilter from './components/adopt-filter.vue'
import AdoptList from './components/adopt-list.vue'

import { defineComponent, ref } from 'vue';


export default {
  name: 'adoptlist',
  components: {
    AdoptFilter,
    AdoptList,
  },

  setup() {
    const dialogVisible = ref(false);
    const dialogVisible2 = ref(false);

    return {
      dialogVisible,
      dialogVisible2
    }
  },

}
</script>
