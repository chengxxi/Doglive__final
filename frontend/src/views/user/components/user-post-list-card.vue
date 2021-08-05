<template>
  <div class="mypage-body">
      <div class="hide-on-small">
        <div class="menu-title">
          <h3>작성글 목록</h3>
        </div>
        <div class="mypage-content">
          <el-table :data="tableData" height="250" style="width: 100%">
            <el-table-column prop="date" label="Date" width="180">
            </el-table-column>
            <el-table-column prop="userId" label="UserId" width="120">
            </el-table-column>
            <el-table-column prop="title" label="Title">
            </el-table-column>
          </el-table>
        </div>
      </div>
    </div>
</template>

<style scoped>
.mypage-body{
  float: left;
  width: calc(100% - 240px);
  height: 100%;
}
.mypage-body .menu-title{
  width: inherit;
  padding-top: 10px;
  padding-bottom: 10px;
  border-bottom: solid 1px rgb(212, 212, 212);
}
.mypage-body .mypage-content {
  padding-top: 60px;
  text-align: center;
  margin: 0 auto;
}
</style>

<script>
import $axios from 'axios';

export default {
  name: 'user-post-list',
  setup() {
    return {
        tableData: [{
          date: '2016-05-03',
          userId: 'Tom',
          title: 'No. 189, Grove St, Los Angeles'
        }]
      }
    
    

  }
}
</script>
