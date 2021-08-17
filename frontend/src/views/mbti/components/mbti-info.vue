<template>
  <div class="main-body main-padding">
    <div class="box-card " style="width:100%; border:none;">
      <div style="margin-top:50px ">
        <h5 class="fw-bold mb-0">16 Pupsonality</h5>
        <h1 class="fw-bold mt-0" style="font-size:60pt; ">
          MPTI
        </h1>
        <h4 class="fw-bold mt-0">My Puppy Type Indicator</h4>
        <p>강아지 성향 기반 MBTI를 통한 나와 맞는 성향의 강아지 찾기</p>
        <!-- 상세 문구 수정 필요 -->
      </div>

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
import MbtiResult from "./mbti-result.vue";

export default {
  name: "mbti-info",
  components: {
    MbtiResult
  }
};
</script>
