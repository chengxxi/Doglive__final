<template>
  <div class="box">
    <h3 class="description">
      🐕 강아지 MBTI란? 🐾
    </h3>
    <br>
    <p>'독립'의 강아지 MBTI는 강아지들의 성격을 총 16가지로 분류해 정리했어요. 원하는 성향을 선택하면 나와 꼭 맞는 강아지를 추천해 준답니다.</p>
    <p>강아지 MBTI를 통해 새롭게 우리 가족이 될 강아지를 발견해 보세요!</p>
    <!-- 상세 소개 내용 수정 필요 -->
  </div>

</template>

<style scoped>
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
export default {
  name: 'MbtiDescription',
  setup () {

  }
}
</script>


