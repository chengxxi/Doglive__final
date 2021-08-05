<template>
  <div>
    <el-row>
    <el-col :span="6" v-for="(o, idx) in card" :key="idx"> <!-- :offset="index > 0 ? 2 : 0" -->
      <el-card :body-style="{ padding: '10px' }" style="margin: 10px !important;" shadow="hover">
        <img :src="require('@/assets/images/logo2.png')" class="image" />
        <div style="padding: 14px;">
          <!-- 입양/임보 구분 -->
          <div><el-tag style="mini">{{o.type.name}}</el-tag></div>
          <div class="bottom">
            <p>{{o.title}}</p>
            <el-button type="text" class="button">작성글 보러가기</el-button>
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
  name: 'post-list-card',
  props:{
      card :{
          type: String
      }
 
  },
  setup () {

  }
}
</script>