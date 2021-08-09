<template>
  <!-- 개별 입양 공고 card -->
  <el-card
    :body-style="{ padding: '10px' }"
    style="margin: 10px !important;"
    shadow="hover"
    class="el-col-5"
  >
    <img :src="require('@/assets/images/logo2.png')" class="image" />
    <div style="padding: 14px;">
      <!-- <span><el-tag color="#D7AFA4">{{ card.boardType }}</el-tag></span> -->
      <div class="bottom">
        <p>{{ card.title }}</p>
        <!-- <p>{{ state.boardList.type }}</p> -->
        <el-button type="text" class="button">Details</el-button>
      </div>
    </div>
  </el-card>
</template>

<style scoped>
.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  display: block;
}

.main-body {
  width: 100%;
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 10%;
}
.main-padding {
  padding-top: 50px;
  padding-bottom: 50px;
}

h3 {
  margin-block-start: 0px;
}

:deep(.el-descriptions__title) {
  font-size: 30pt;
  font-weight: 700;
}

.dog-thumbnail {
  width: 90%;
  height: auto;
  margin-right: 20px;
  margin: 10px;
}

.dog-info-box {
  width: 100%;
  margin-right: 30px;
  margin: 10px;
  padding-right: 20px;
}

:deep(.el-descriptions__label) {
  display: inline-block;
  width: 150px;
  font-weight: 500;
}

:deep(.el-descriptions__content) {
  display: inline-block;
  font-weight: 500;
}
:deep(.el-button) {
  font-family: NEXONLv1Gothic;
  font-weight: 700;
  background: #755744;
  color: #f9f0e7;
}

.dog-image-box {
  margin: 30px;
  padding: 10px;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.el-col {
  display: flex;

  align-items: center;
}

.box-card {
  margin-top: 20px;
}

.box {
  margin-top: 30px;
  margin-bottom: 40px;
  margin-left: 50px;
  margin-right: 50px;
  padding: 40px;
  background-color: #f9f0e7;
}

.content {
  margin-top: 20px;
  margin-bottom: 20px;
  padding: 20px;
  background-color: #ffffff;
}

.el-card__body {
  display: flex;
  align-items: center;
  margin-right: auto;
}
</style>

<script>
export default {
  name: 'find-card',
  props: {
    card: Object
  }
};
</script>
