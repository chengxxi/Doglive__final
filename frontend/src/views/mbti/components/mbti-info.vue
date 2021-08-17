<template>
  <div class='main-body main-padding'>
    <div class='box-card ' style='width:100%; border:none;'>
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
import MbtiResult from './mbti-result.vue';

export default {
  name: 'mbti-info',
  components: {
    MbtiResult
  }
};
</script>
