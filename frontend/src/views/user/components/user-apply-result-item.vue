<template>
  <el-row :gutter="20">
    <el-col :span="12">
      <div class="grid-content bg-beige">
        <div class="tag">
          <el-tag
              color="#D7AFA4"
              effect="dark"
              size="medium"
              style="border:none; border-radius: 30px; font-size:10pt;">승인</el-tag>
        </div>
        <div class="apply-content"><p>글제목: 우리강쥐를 입양하세요</p></div>
        <div class="icon">
          <font-awesome-icon
                        icon="eraser"
                        aria-hidden="true"
                        style="color: rgb(78, 78, 78); font-size: 20px; cursor: pointer; margin-right:20px"
                        class="scale-up-5">
          </font-awesome-icon>
        </div>
      </div>
    </el-col>
    <el-col :span="12">
      <div class="grid-content bg-beige">
      </div>
    </el-col>
    <el-col :span="12">
      <div class="grid-content bg-beige">
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
  .tag{
    vertical-align: middle; 
    margin-right: 20px;
  }
  .apply-content{
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

  }
</style>

<script>
export default {
  name: 'user-apply-result-item',
  setup () {

  }
}
</script>

