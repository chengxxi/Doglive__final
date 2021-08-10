<template>
  <div class="box">
    <!-- style="margin-left:30px; margin:right:30px; display: flex; justify-content: center;" -->
    <h3 class="description">
      🐕 나와 맞는 성향의 강아지 찾기 테스트 🐾
    </h3>
    <p>원하는 강아지 성향 기반 MBTI 테스트를 통해 </p>
    <!-- 상세 소개 내용 수정 필요 -->
  </div>

</template>

<style scoped>
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

.box {
  margin-top: 30px;
  margin-bottom: 40px;
  margin-left: 50px;
  margin-right: 50px;
  padding: 40px;
  background-color: #f9f0e7;
}

.description {
  font-size: 1.5rem;
  font-weight: 500;
}


</style>

<script>
export default {
  name: 'MbtiDescription',
  setup () {

  }
}
</script>


