<template>
  <div class="main-body main-padding">
    <div class="box-card " style="width:100%; border:none;">
      <div style="margin-top:50px">
        <h1 class="title">나와 맞는 강아지 찾기</h1>
        <p>강아지 성향 기반 MBTI를 통한 나와 맞는 성향의 강아지 찾기</p>
        <!-- 상세 문구 수정 필요 -->

      </div>

      <!-- 개별 유형 설명 card -->
      <MbtiResult />

    </div>
  </div>
</template>


<style>
/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%, 위 여백 50px) */
.main-body {
  width: 100%;
  margin-left: 10%;
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
import MbtiResult from './mbti-result.vue'


export default {
  name: 'mbti-info',
  components: {
    MbtiResult,
  },
}
</script>
