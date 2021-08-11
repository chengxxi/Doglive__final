<template>
<div class="block">
  <div class="question">
    <p class="demonstration">
      다른 강아지들과 어울리는 데 시간이 좀 걸려요.
    </p>

    <el-rate
      v-model="value"
      :colors="colors"
      class="rating"
    >
    </el-rate>
  </div>

  <div class="question">
    <p class="demonstration">
      내가 원하는 것을 얻기 위해 때로는 불쌍한 척을 해요.
    </p>

    <el-radio-group v-model="radio" class="rating">
      <el-radio-button :label="1" class="radioBtn" size="medium">매우 아니다</el-radio-button> <!-- 매우 아니다 -->
      <el-radio-button :label="2" size="small">조금 아니다</el-radio-button> <!-- 조금 아니다 -->
      <el-radio-button :label="3" size="mini">보통이다</el-radio-button> <!-- 보통이다 -->
      <el-radio-button :label="4" size="small">조금 그렇다</el-radio-button> <!-- 조금 그렇다 -->
      <el-radio-button :label="5" size="medium">매우 그렇다</el-radio-button> <!-- 매우 그렇다 -->
    </el-radio-group>
  </div>

  <div class="question">
    <p class="demonstration">
      혼날 때, 한껏 주눅들어 있어요.
    </p>

    <el-radio-item class="align-items-center">
      <el-radio-group v-model="radio3" class="rating">
        <span>비동의</span>
        <el-radio-button :label="1" class="radioBtn-lg-lf"></el-radio-button> <!-- 매우 아니다 -->
        <el-radio-button :label="2" class="radioBtn-md-lf"></el-radio-button> <!-- 조금 아니다 -->
        <el-radio-button :label="3" class="radioBtn-sm"></el-radio-button> <!-- 보통이다 -->
        <el-radio-button :label="4" class="radioBtn-md-rt"></el-radio-button> <!-- 조금 그렇다 -->
        <el-radio-button :label="5" class="radioBtn-lg-rt"></el-radio-button> <!-- 매우 그렇다 -->
        <span>동의</span>
      </el-radio-group>
    </el-radio-item>

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
  font-size: 1.25rem;
}

.rating {
  font-size: 2rem;
  align-items: center;
}

.rating > span {
  font-size: 1rem;
}

.radioBtn-lg-lf {
  width: 3rem;
  height: 3rem;
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
  width: 1rem;
  height: 1rem;
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
  width: 3rem;
  height: 3rem;
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
      value: ref(null),
      colors: ref(['#99A9BF', '#F7BA2A', '#FF9900']),
      // same as { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
      // iconClasses: ref(['icon-rate-face-1', 'icon-rate-face-2', 'icon-rate-face-3'])
      // same as { 2: 'icon-rate-face-1', 4: { value: 'icon-rate-face-2', excluded: true }, 5: 'icon-rate-face-3' }
      radio: ref(null),
      radio2: ref(null),
      radio3: ref(null),
    };
  },
}
</script>


