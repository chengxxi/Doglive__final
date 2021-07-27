<template>
<el-header class="main-header"
  :style="{'height': height}">
  <!-- Desktop 전용 화면 -->
  <div class="hide-on-small">
    <div class="menu-wrapper">
        <ul class="depth0" @mouseleave="changeDisplay('leave')">
            <li>
                <div class="logo"/>
            </li>
            <li></li>
            <li>
              <a href="#" @mouseover="changeDisplay('over')">독립</a>
                <ul class="depth1" :style="{display: state.showHiddenMenu}">
                    <li><a href="#">About 독립</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
            </li>
            <li>
              <a href="#" @mouseover="changeDisplay('over')">입양/임보</a>
                <ul class="depth1" :style="{display: state.showHiddenMenu}">
                  <li><a href="#">입양하기</a></li>
                  <li><a href="#">임시보호하기</a></li>
                  <li><a href="#">입양 사전 설문</a></li>
                  <li><a href="#">나와 맞는 강아지 MBTI</a></li>
                </ul>
            </li>
            <li>
              <a href="#" @mouseover="changeDisplay('over')">실종/보호</a>
                <ul class="depth1" :style="{display: state.showHiddenMenu}">
                    <li><a href="#">찾아주세요</a></li>
                    <li><a href="#">보호하고 있어요</a></li>
                </ul>
            </li>
            <li>
              <a href="#" @mouseover="changeDisplay('over')">커뮤니티</a>
                <ul class="depth1" :style="{display: state.showHiddenMenu}">
                  <li><a href="#">게시글 구경하기</a></li>
                  <li><a href="#">내 피드 확인하기</a></li>
                </ul>
            </li>
              <li></li>
            <li>
              <!-- User 아이콘에 마우스 hover했을 때 나올 popover 정의 -->
              <!-- <el-button @click="changeUserModal">activate</el-button> -->
              <el-popover
                placement="bottom"
                width="200"
                trigger="click"
                v-model="state.showUserModal">
                <table :style="{margin: '0 auto'}">
                  <tr v-for="(path, key, index) in notLoggedIn" v-bind:key="index">
                    <td><a :href="path">{{ key }}</a></td>
                  </tr>
                </table>
                <template #reference>
                  <i class="el-icon-s-custom userBtn" @click="changeUserModal" ></i>
                </template>
              </el-popover>
            </li>
        </ul>
    </div>
  </div>
</el-header>
</template>

<style scoped>
.main-header{
  position: fixed;
  top: 0;
  left: 0;
  text-align: center;
  overflow: visible;
}
.main-header .hide-on-small{
  height: inherit;
}
.main-header .hide-on-small .menu-wrapper{
  width: 100%;
}
/* 상단 메뉴 */
.main-header .hide-on-small .menu-wrapper .depth0{
  display: table;
  table-layout: fixed;
  width: 100%;
  font-size: 18px;
  font-weight: 500;
  background-color: rgba(255, 244, 233, 0.979);
}
.main-header .hide-on-small .menu-wrapper .depth0 ul{
  list-style: none;
  width: 100%;
}
.main-header .hide-on-small .menu-wrapper .depth0 > li{
  display: table-cell;
  line-height: 70px;
  vertical-align: top;
}
.main-header .hide-on-small .menu-wrapper .depth0 a:hover{
  color: #D7AEA4;
}
/* 메인 로고 */
.main-header .hide-on-small .logo {
  width: 150px;
  height: 50px;
  margin-top: 10px;
  margin-left: 20px;
  cursor: pointer;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url('../../../assets/images/temp-logo.png');
}
.main-header .hide-on-small .userBtn{
  cursor: pointer;
}
/* 하단 메뉴 */
.main-header .hide-on-small .menu-wrapper .depth1{
  text-align: center;
  margin-bottom: 20px;
  font-size: 16px;
  font-weight: 500;
}
.main-header .hide-on-small .menu-wrapper .depth1 > li{
  width: 100%;
  line-height: 40px;
}
</style>

<script>
import { computed, reactive } from 'vue';

export default {
  name: 'main-header',
  props: {
      height: {
        type: String,
        default: '70px'
      },
  },
  setup() {
    const notLoggedIn = {
      '로그인': '/',
    };
    const loggedIn = {
      '마이페이지': '/',
      '로그아웃': '/'
    };

    const state = reactive({
      showHiddenMenu: 'none',
      showUserModal: false,
    })

    const changeUserModal = function(){
      state.showUserModal=!state.showUserModal;
    }

    const changeDisplay = function(mouse){
      if(mouse === "over")
        state.showHiddenMenu = 'block';
      else
        state.showHiddenMenu = 'none';
    }
    return { state, changeUserModal, changeDisplay, notLoggedIn, loggedIn }
  },
}
</script>
