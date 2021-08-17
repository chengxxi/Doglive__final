<template>
  <div class='main-body main-padding'>
    <div class='box-card ' style='width:100%; border:none;'>
      <div class='box'>
        <h3 class='description'>
          🐕 강아지 MBTI란? 🐾
        </h3>
        <br />
        <p>'독립'이 만든 강아지 성향 MBTI</p>
        <!-- 상세 소개 내용 수정 필요 -->
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

.box {
  margin-top: 30px;
  margin-bottom: 30px;
  margin-left: 50px;
  margin-right: 50px;
  padding: 40px;
  background-color: #f9f0e7;
}

.description {
  font-size: 1.5rem;
  font-weight: 600;
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
