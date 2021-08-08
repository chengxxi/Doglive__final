<template>
  <el-row :gutter="20">
    <el-col :span="12" v-for="(o, idx) in applicant" :key="idx">
      <div class="grid-content bg-beige">
        <div class="image">
          <el-avatar shape="circle" :size="70" :src="require('@/assets/images/profile-image.jpg')" :style="{'border' : 'solid 1px rgb(212, 212, 212)'}"/>
        </div>
        <div class="apply-content"><p>분류 : 입양</p> <p>이름 : {{o.name}}</p></div>
        
        <div class="icon">
          <font-awesome-icon
                        icon="eraser"
                        aria-hidden="true"
                        style="color: rgb(78, 78, 78); font-size: 20px; cursor: pointer; margin-right:20px"
                        class="scale-up-5">
          </font-awesome-icon>
          <font-awesome-icon
                        icon="eraser"
                        aria-hidden="true"
                        style="color: rgb(78, 78, 78); font-size: 20px; cursor: pointer; margin-top: 10px;"
                        class="scale-up-5">
          </font-awesome-icon>
        </div>
        <div class="button">
          <el-tag
              color="#D7AFA4"
              effect="dark"
              size="medium"
              style="border:none; border-radius: 30px; font-size:7pt; display:block; margin-bottom: 5px">승인</el-tag>
          <el-tag
              color="#BDBDBD"
              effect="dark"
              size="medium"
              style="border:none; border-radius: 30px; font-size:7pt; display:block;">거절</el-tag>
        </div>
      </div>
    </el-col>

  </el-row>
</template>

<style scoped>
  /* .el-row{
    display: inline-block;
  } */
  .el-col {
    border-radius: 4px;
  }
  .bg-beige {
    background: #F9F0E7;
    height : 90px;
  }
  .grid-content {
    display: flex;
    align-items: center;
    flex-direction: row;
    padding: 10px 0 10px 20px;
    border-radius: 4px;
    min-height: 36px;
    margin-bottom: 20px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  } 
  .image{
    display: flex;
    align-items: center;
    float : left;
    vertical-align: middle; 
    margin-right: 20px;
  }
  .apply-content{
    float : left;
    vertical-align: middle; 
    margin-right: 20px;
  }
  .apply-content p{
    margin: 5px 0 5px 0;
  }
  .button{
    float : right;
    vertical-align: middle; 

  }
  .icon{
    float : right;
    margin-right: 20px;

  }
</style>

<script>
export default {
  name: 'user-applicant-item',
  props:{
      applicant :{
          type: String
      }

  },
  setup () {

  }
}
</script>
