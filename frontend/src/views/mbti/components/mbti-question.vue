<template>
<div class="block">
  <div class="question"> <!-- question 1 -->
    <p class="demonstration">
      혼날 때, 한껏 주눅들어 있어요.
    </p>

    <el-radio-item>
      <el-radio-group v-model="radio1" class="rating">
        <span style="color: #755744; font-weight: 530;">별로예요</span>
        <el-radio-button label="0" class="radioBtn-lg-lf"></el-radio-button> <!-- 매우 아니다 -->
        <el-radio-button label="25" class="radioBtn-md-lf"></el-radio-button> <!-- 조금 아니다 -->
        <el-radio-button label="50" class="radioBtn-sm"></el-radio-button> <!-- 보통이다 -->
        <el-radio-button label="75" class="radioBtn-md-rt"></el-radio-button> <!-- 조금 그렇다 -->
        <el-radio-button label="100" class="radioBtn-lg-rt"></el-radio-button> <!-- 매우 그렇다 -->
        <span style="color: #c58676; font-weight: 530;">좋아요</span>
      </el-radio-group>
    </el-radio-item>

  </div> <!-- question 1 -->




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

.block {
  margin-top: 30px;
  margin-bottom: 30px;
  margin-left: 50px;
  margin-right: 50px;
  padding: 40px;
}

.question {
  font-weight: 500;
  margin-bottom: 40px;
  /* align-items: center; */
}

.demonstration {
  font-size: 1.5rem;
  font-weight: 500;
  align-content: center;
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.rating {
  font-size: 2rem;
  align-items: center;
  border-radius: 50%;
  cursor: pointer;
  display: flex;
  justify-content: center;
}

.rating > span {
  font-size: 1rem;
}

.radioBtn-lg-lf {
  width: 2.5rem;
  height: 2.5rem;
  border: 2px solid #755744;
  border-radius: 50%;
  margin: 0 25px;
}

.radioBtn-md-lf {
  width: 2rem;
  height: 2rem;
  border: 2px solid #8a6f5e;
  border-radius: 50%;
  margin: 0 25px;
}

.radioBtn-sm {
  width: 1.5rem;
  height: 1.5rem;
  border: 2px solid #E9CDA4;
  border-radius: 50%;
  margin: 0 25px;
}

.radioBtn-md-rt {
  width: 2rem;
  height: 2rem;
  border: 2px solid #D7AEA4;
  border-radius: 50%;
  margin: 0 25px;
}

.radioBtn-lg-rt {
  width: 2.5rem;
  height: 2.5rem;
  border: 2px solid #c58676;
  border-radius: 50%;
  margin: 0 25px;
}




/* radio 안에 체크하는 원 부분 삭제 */
:deep(.el-radio-button__inner) {
  display: none;
}
/* :deep(.el-radio-button.is-checked) {
  border-color: #755744;
  color: #755744;
} */


/* radio btn styling */




</style>

<script>
import { ref } from 'vue'

export default {
  name: 'MbtiQuestion',
  setup() {
    return {
      radio1: ref(null),
    };
  },
}
</script>


