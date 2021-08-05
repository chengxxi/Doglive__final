<template>
  <div> <!-- multiple template 때문에 div로 묶었으나 el-card만 들어갈 예정 -->
    <!-- 개별 입양 공고 card -->
    <el-row>
    <el-col :span="4" v-for="(o, index) in 12" :key="o" :offset="index > 0 ? 2 : 0">
      <el-card :body-style="{ padding: '0px' }">
        <img :src="require('@/assets/images/logo2.png')" class="image" />
        <div style="padding: 14px;">
          <span>입양 공고</span>
          <div class="bottom">
            <p>공고 제목</p>
            <el-button type="text" class="button">Operating</el-button>
          </div>
        </div>
      </el-card>
    </el-col>
    </el-row>
  </div>


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

</style>

<script>
export default {
  name: 'adopt-card',
  setup () {

  }
}
</script>
