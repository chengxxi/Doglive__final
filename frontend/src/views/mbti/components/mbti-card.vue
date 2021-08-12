<template>
  <el-col>
    <el-card class="mbtiCard" shadow="hover" style="background-color: snowflake; ">
      <img :src="require('@/assets/images/profile-image.jpg')" class="img"/>
      <h3 class="mbtiTitle">
        MBTI
      </h3>
    </el-card>
  </el-col>
</template>

<style scoped>
.mbtiCard {
  align-content: center;
  display: flex;
  justify-content: center;
  margin: 10px;
}

.img {
  width: 100%;
}

.mbtiTitle {
  margin-top: 20px;
  align-content: center;
  display: flex;
  justify-content: center;
  font-weight: 700;
}
</style>

<script>

export default {
  name: 'mbti-card',

}
</script>
