<template>
  <div class="main-body main-padding">
    <el-card class="box-card " style="width:100%; border:none;" shadow="hover">
      <div style="margin-top:50px">
        <h1 class="title">실종/보호 게시물</h1>
        <p>실종/보호 아이들</p>

      </div>

      <MBTIdescription />
      <MBTIquestion />

    </el-card>
  </div>
</template>


<style>
/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%, 위 여백 50px) */
.main-body {
  width: 100%;
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 10%;
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
import MBTIdescription from './components/mbti-description.vue';
import MBTIquestion from './components/mbti-question.vue';

export default {
  name: 'find',
  components: {
    MBTIdescription,
    MBTIquestion,
  },
}
</script>
