<template>
  <div>
    <div class="box">
      <h1 class="description">
        🐕 My Puppy Type Indicator 🐾
      </h1>

      <img
        src="https://d2ud6j7vlf3xy9.cloudfront.net/mbti/MPTI_%EB%A9%94%EC%9D%B8.png"
        class="img"
        style="height:600px; margin-top:30px;  object-fit: cover;"
      />
      <p>
        "독립"의 <b>'MPTI(My Puppy Type Indicator)'</b>는 강아지들의 성격을 총
        16가지로 분류해 정리했어요. <br />

        원하는 강아지의 모습을 머릿속에 그리며, 하나씩 질문에 응답하다 보면 나와
        꼭 맞는 강아지를 추천받을 수 있답니다! <br />

        "독립"의 'MPTI'를 통해
        <b style="color: #D7AFA4;">새롭게 우리 가족이 될 강아지</b>를 발견해
        보세요. <br />
      </p>

      <!-- <a
        href="https://www.16personalities.com/articles/introducing-16pet-sonalities"
        ><span style="float: right; font-size:10pt; font-weight: 300;">
          이미지 출처 : 16 pet-sonalities</span
        ></a
      > -->
    </div>

    <div class="mbtiBtn">
      <!-- round option -->
      <a href="/mbti/test">
        <el-button class="goMbtiBtn" round>MPTI 테스트 하러 가기</el-button>
      </a>
      <a href="/mbti/info">
        <el-button class="showMbtiBtn" round
          >독립 MPTI 테스트 유형 보기</el-button
        >
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
  line-height: 2rem;
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
  border: 2px solid #e9cda4;
  background-color: #e9cda4;
  font-weight: 600;
  color: white;
  margin: 0 15px;
}

.showMbtiBtn {
  border: 2px solid #d7aea4;
  background-color: #d7aea4;
  font-weight: 600;
  color: white;
  margin: 0 15px;
}
</style>

<script>
export default {
  name: "MbtiIntro"
};
</script>
