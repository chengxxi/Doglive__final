<template>
  <div class="main-body main-padding">
    <el-card class="box-card " style="width:100%;" shadow="none">
      <bread-crumb></bread-crumb>
      <div style="margin-top:50px margin-left:1%;">
        <h1 class="title">Community</h1>
        <p>강아지와 당신의 이야기</p>
      </div>
      <div style="text-align:right;">
        <el-button type="outline-primary"  round
        >글 작성하기</el-button>
      </div>
      <community-board-list></community-board-list>
    </el-card>
    
  </div>
</template>

<style scoped>
.main-body {
  width: 90%;
  margin-left: 25%; 
  margin-right: 25%;
  margin-bottom : 50px
}
.main-padding {
  padding-top: 50px;
}

.title {
  font-size: 2.5rem;
  font-weight: normal;
}
.el-button{
  margin-right:1%;
}
</style>

<script>
import BreadCrumb from "./components/bread-crumb.vue";
import CommunityBoardList from "./components/community-board-list.vue";

export default {
  name: "community",
  components:{
      BreadCrumb,
      CommunityBoardList
  },
  setup() {


  }
}
</script>
