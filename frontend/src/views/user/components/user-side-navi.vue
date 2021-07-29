<template>
  <div class="side-navi">
    <div class="hide-on-small">
      <div class="menu-title">
        <h3>마이페이지</h3>
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
          <el-menu-item index="1-1">- 내 프로필</el-menu-item>
          <el-menu-item index="1-2">- 내가 쓴 글 보기</el-menu-item>
          <el-menu-item index="1-2">- 회원탈퇴</el-menu-item>
        </el-submenu>
        <el-submenu index="2">
          <template #title>
            <i class="el-icon-chat-line-square"></i>
            <span>입양/임시보호</span>
          </template>
          <el-menu-item index="2-1">- 상담 신청 결과</el-menu-item>
          <el-menu-item index="2-2">- 신청자 목록 보기</el-menu-item>
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
  border-top: solid 1px rgb(212, 212, 212);
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
export default {
  name: 'main-footer',
  setup() {
    const openedMenu = ['1', '2']; // 처음 화면에 들어왔을 때, open할 메뉴의 index 저장
    const handleOpen = function(key, keyPath){
      console.log(key, keyPath);
    }
    const handleClose = function(key, keyPath){
      console.log(key, keyPath);
    }
    return {handleOpen, handleClose, openedMenu}
  }
}
</script>
