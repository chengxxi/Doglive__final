<template>
  <div class="mypage-body">
    <div class="hide-on-small">
      <div class="menu-title">
        <h3>내 프로필</h3>
      </div>
      <div class="mypage-content">
        <!-- 사용자 프로필 이미지 -->
        <div class="image-wrapper">
          <el-avatar shape="circle" :size="250" :src="require('@/assets/images/profile-image.jpg')" :style="{'border' : 'solid 1px rgb(212, 212, 212)'}"/>
        </div>
        <!-- 사용자 프로필 정보 -->
        <el-table class="userinfo-wrapper"
          :data="tableData"
          show-header=false
          style="width: 70%">
          <el-table-column class="label" prop="label" width="200">
          </el-table-column>
          <el-table-column>
            <el-input></el-input>
          </el-table-column>
        </el-table>
        <div class="button-group">
          <el-button @click="updateProfile">수정하기</el-button>
        </div>
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
  padding-top: 70px;
  text-align: center;
  margin: 0 auto;
}
.mypage-body .mypage-content .image-wrapper{
  margin-bottom: 20px;
}
.mypage-body .mypage-content .userinfo-wrapper{
  text-align: center;
  margin: 0 auto;
}
.mypage-body .mypage-content .button-group{
  margin-top: 20px;
}
</style>

<script>
export default {
  name: 'user-profile',
  setup() {
    const tableData = [
      {label : '이메일'},
      {label : '닉네임'},
      {label : '생년월일'},
      {label : '전화번호'},
      {label : '성별'},
    ];

    const updateProfile = function(){
    }
    return { updateProfile, tableData }
  }
}
</script>
