<template>
  <el-col>
    <div v-if="isHovered">
      <el-card class="mbtiCard" shadow="hover" style="background-color: snowflake; ">
        <img :src="require('@/assets/images/profile-image.jpg')" class="img"/>
        <h3 class="mbtiTitle">
          MBTI
        </h3>
      </el-card>
    </div>

    <div v-else>
      <el-card class="mbtiCard" shadow="hover" style="background-color: snowflake; ">
        <img :src="require('@/assets/images/profile-image.jpg')" class="img"/>
        <h3 class="mbtiTitle">
          MBTI
        </h3>
        <p>description</p>
      </el-card>
    </div>
  </el-col>

</template>

<style scoped>
.mbtiCard {
  align-content: center;
  display: flex;
  justify-content: center;
  margin: 10px;
  height: 300px;
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

el-col {
  position: relative;
}

.el-card:hover {
  box-shadow: 10px 10px 10px 0px rgba(0, 0, 0, 0.25);
}



</style>

<script>

export default {
  name: 'mbti-card',

}
</script>
