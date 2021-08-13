<template>
<div>
  <div class="box">
    <h1 class="description">
      🐕 강아지 MBTI란? 🐾
    </h1>

    <img src="https://storage.googleapis.com/neris/public/images/articles/pets.svg" class="img"/>


    <p>'독립'의 강아지 MBTI는 강아지들의 성격을 총 16가지로 분류해 정리했어요. 원하는 성향을 선택하면 나와 꼭 맞는 강아지를 추천해 준답니다.</p>
    <p>강아지 MBTI를 통해 새롭게 우리 가족이 될 강아지를 발견해 보세요!</p>

    <a href="https://www.16personalities.com/articles/introducing-16pet-sonalities"><span style="float: right; font-weight: 300;">16 pet-sonalities</span></a>
    <!-- 상세 소개 내용 수정 필요 -->
  </div>

  <div class="mbtiBtn">
      <!-- round option -->
      <a href="/mbti/test">
        <el-button class="goMbtiBtn">MBTI 테스트 하러 가기</el-button>
      </a>
      <a href="/mbti/info">
        <el-button class="showMbtiBtn">독립 MBTI 테스트 유형 보기</el-button>
      </a>

      <!-- black -->
      <a href="/mbti/test">
        <el-button class="goMbtiBtn2">MBTI 테스트 하러 가기</el-button>
      </a>
      <a href="/mbti/info">
        <el-button class="showMbtiBtn2">독립 MBTI 테스트 유형 보기</el-button>
      </a>


    </div>

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

.img {
  width: 100%;
  margin-bottom: 3%;
}

.mbtiBtn {
  align-content: center;
  display: flex;
  justify-content: center;
  margin-bottom: 40px;
}

.goMbtiBtn {
  border: 2px solid #E9CDA4;
  background-color: #E9CDA4;
  font-weight: 600;
  color: white;
  margin: 0 15px;
}

.showMbtiBtn {
  border: 2px solid #D7AEA4;
  background-color: #D7AEA4;
  font-weight: 600;
  color: white;
  margin: 0 15px;
}



.goMbtiBtn2 {
  border: 2px solid #E9CDA4;
  background-color: #E9CDA4;
  font-weight: 600;
  /* color: white; */
  margin: 0 15px;
}

.showMbtiBtn2 {
  border: 2px solid #D7AEA4;
  background-color: #D7AEA4;
  font-weight: 600;
  /* color: white; */
  margin: 0 15px;
}



</style>

<script>
export default {
  name: 'MbtiIntro',
  setup () {

  }
}
</script>


