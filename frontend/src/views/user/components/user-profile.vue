<template>
  <div class="mypage-body">
    <div class="hide-on-small">
      <div class="menu-title">
        <h3>내 프로필</h3>
      </div>
      <div class="mypage-content">
        <!-- 사용자 프로필 이미지 -->
        <div class="image-wrapper">
          <el-avatar shape="circle" :size="200" :src="require('@/assets/images/profile-image.jpg')" :style="{'border' : 'solid 1px rgb(212, 212, 212)'}"/>
        </div>

        <!-- 사용자 프로필 정보 -->
        <!-- <el-table class="userinfo-wrapper"
          :data="tableData"
          show-header=false
          style="width: 50%; min-width: 300px">
          <el-table-column class="label" prop="label" min-width="80">
          </el-table-column>
          <el-table-column min-width="150">
            <el-input></el-input>
          </el-table-column>
        </el-table> -->

        <!-- 사용자 프로필 정보 -->
        <el-form class="userinfo-wrapper" :model="formData" label-width="120px" label-position="left">
        <el-form-item
          label="닉네임"
          :rules="{ required: true, message: 'Please input email address', trigger: 'blur' }">
        <el-input v-model="formData.nickname"></el-input>
        </el-form-item>

        <el-form-item
          label="Email"
          :rules="[
            { required: true, message: 'Please input email address', trigger: 'blur' },
            { type: 'email', message: 'Please input correct email address', trigger: ['blur', 'change'] }
          ]">
        <el-input v-model="formData.email" disabled></el-input>
        </el-form-item>

        <el-form-item
          label="생년월일"
          :rules="{ required: true, message: 'Please input email address', trigger: 'blur' }">
        <el-date-picker style="width: 60%; min-width: 150px" type="date" v-model="formData.birth" disabled></el-date-picker>
        </el-form-item>

        <el-form-item
          label="Phone"
          :rules="{ required: true, message: 'Please input email address', trigger: 'blur' }">
        <el-input v-model="formData.phone" disabled></el-input>
        </el-form-item>

        </el-form>
        <div class="button-group">
          <el-button class="updateBtn" @click="updateProfile">수정하기</el-button>
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
  padding-top: 60px;
  text-align: center;
  margin: 0 auto;
}
.mypage-body .mypage-content .image-wrapper{
  margin-bottom: 30px;
}
.mypage-body .mypage-content .userinfo-wrapper{
  width: 50%;
  text-align: center;
  margin: 0 auto;
  border-bottom: solid 1px rgb(212, 212, 212);
}
.mypage-body .mypage-content .userinfo-wrapper .el-input{
  width: 60% !important;
  min-width: 150px;
}
.mypage-body .mypage-content .button-group{
  margin-top: 20px;
}
.mypage-body .mypage-content .button-group .el-button{
  border-color: #D7AEA4;
  font-weight: 600;
}
</style>

<script>
import { reactive } from 'vue';

export default {
  name: 'user-profile',
  setup() {
    const formData = reactive({
      nickname: '호두맘',
      email: 'ssafy@ssafy.com',
      birth: '1998-06-26',
      phone: '010-1234-1234',
     });

    const updateProfile = function(){
    }
    return { updateProfile, formData }
  }
}
</script>
