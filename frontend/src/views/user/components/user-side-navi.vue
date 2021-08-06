<template>
  <div class="side-navi">
    <div class="hide-on-small">
      <div class="menu-title">
        <h5>마이페이지</h5>
      </div>
      <!-- 사용자 정보 -->
      <el-menu
        class="menu-list el-menu-vertical-demo"
        active-text-color="#D7AEA4"
        default-active="1-1"
        :default-openeds=openedMenu
        @open="handleOpen"
        @close="handleClose">
        <el-submenu index="1">
          <template #title>
            <i class="el-icon-user-solid"></i>
            <span>회원정보</span>
          </template>
          <el-menu-item index="1-1" @click="clickProfile">- 내 프로필</el-menu-item>
          <el-menu-item index="1-2" @click="clickMyPost">- 내가 쓴 글 보기</el-menu-item>
          <el-menu-item index="1-3" @click="clickBookmark">- 북마크</el-menu-item>
        </el-submenu>
        <el-submenu index="2">
          <template #title>
            <i class="el-icon-chat-line-square"></i>
            <span>입양/임시보호</span>
          </template>
          <el-menu-item index="2-1" @click="clickApplyResult">- 상담 신청 결과</el-menu-item>
          <el-menu-item index="2-2" @click="clickApplicant">- 신청자 목록 보기</el-menu-item>
        </el-submenu>
      </el-menu>
    </div>
  </div>
</template>

<style scoped>
.side-navi{
  float: left;
  width: 200px;
  height: 100%;
  margin-right: 40px;
}
.side-navi .menu-title {
  text-align: center;
  padding-top: 10px;
  padding-bottom: 10px;
  border-bottom: solid 1px rgb(212, 212, 212);
}
.side-navi .menu-list {
  position: inherit !important;
  margin-top: 30px;
  border: 0 !important;
}
.el-menu .el-submenu{
   position: inherit !important;
}
.side-navi .menu-list .el-submenu{
  font-weight: 600;
}
.side-navi .menu-list .el-menu-item{
  color: black;
  font-weight: 500;
}
</style>

<script>
import $axios from 'axios';
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { ElMessageBox } from 'element-plus';
import { computed, reactive, watchEffect, ref } from 'vue';

export default {
  name: 'main-footer',

  setup() {
    const store = new useStore();
    const router = new useRouter();
    const state = reactive({
      // showContent : false,
      userInfo: computed(()=> {
        this.userId = store.getters['root/getLoginUserInfo'].userId;
        return store.getters['root/getLoginUserInfo'].userId;
      })
    })
    const openedMenu = ['1', '2']; // 처음 화면에 들어왔을 때, open할 메뉴의 index 저장
    const handleOpen = function(key, keyPath){
      console.log(key, keyPath);
    }
    const handleClose = function(key, keyPath){
      console.log(key, keyPath);
    }
    const clickProfile = function(){
      router.push({name: 'mypage-profile'})
    }
    const clickMyPost = function(){
      router.push({name: 'mypage-mypost'})
    }
    const clickBookmark = function(){
      router.push({name: 'mypage-bookmark'})
    }
    const clickApplicant = function(){
      router.push({name: 'mypage-applicant-list'})
    }
    const clickApplyResult = function(){
      router.push({name: 'mypage-result-list'})
    }
    
   
    return {handleOpen, handleClose, clickProfile, clickMyPost, clickBookmark, clickApplicant, clickApplyResult, state, openedMenu}
  }
}
</script>
