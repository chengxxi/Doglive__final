<template>
<el-row class="main-header"
  :style="{'height': height}">
  <!-- Destop 전용 화면 -->
  <div class="hide-on-small" @mouseover="changeDisplay('over')" @mouseleave="changeDisplay('leave')">
    <div class="logo-wrapper">
      <div class="logo"/>
    </div>
    <div class="menu-wrapper">
        <ul class="depth0">
            <li>
              <a href="#">독립</a>
                <ul class="depth1" :style="{display: state.showHiddenMenu}">
                    <li><a href="#">About 독립</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
            </li>
            <li>
              <a href="#">입양/임보</a>
                <ul class="depth1" :style="{display: state.showHiddenMenu}">
                  <li><a href="#">입양하기</a></li>
                  <li><a href="#">임시보호하기</a></li>
                  <li><a href="#">입양 사전 설문</a></li>
                  <li><a href="#">나와 맞는 MBTI</a></li>
                </ul>
            </li>
            <li>
              <a href="#">실종/보호</a>
                <ul class="depth1" :style="{display: state.showHiddenMenu}">
                    <li><a href="#">찾아주세요</a></li>
                    <li><a href="#">보호하고 있어요</a></li>
                </ul>
            </li>
            <li>
              <a href="#">커뮤니티</a>
                <ul class="depth1" :style="{display: state.showHiddenMenu}">
                  <li><a href="#">게시글 구경하기</a></li>
                  <li><a href="#">내 피드 확인하기</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="user-wrapper">
      <el-avatar class="userBtn" icon="el-icon-user-solid" @click="click"></el-avatar>
    </div>
  </div>
</el-row>
</template>

<style scoped>
.main-header{
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  padding: 10px 20px;
  text-align: center;
  overflow: visible;
}
.main-header .hide-on-small{
  margin: 0 auto;
  vertical-align: center;
}
.main-header .hide-on-small .logo-wrapper{
 float: left;
 cursor: pointer;
}
.main-header .hide-on-small .logo-wrapper .logo {
  width: 150px;
  height: 49px;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url('../../../assets/images/temp-logo.png');
}
.main-header .hide-on-small .menu-wrapper{
  display: inline-block;
  width: 70%;
  height: 49px;
  margin: 0 auto;
}
.main-header .hide-on-small .menu-wrapper .depth0{
  list-style: none;
  padding-left: 0;
  font-size: 18px;
  font-weight: 500;
}
.main-header .hide-on-small .menu-wrapper .depth0 li{
  display: inline-block;
  width: 25%;
  vertical-align: top;
}
.main-header .hide-on-small .menu-wrapper .depth0 a{
  text-decoration: none;
  color: black;
}
.main-header .hide-on-small .menu-wrapper .depth1{
  display: none;
  text-align: center;
  padding-left: 0;
  margin-top: 40px;
  font-size: 16px;
  font-weight: 400;
}
.main-header .hide-on-small .menu-wrapper .depth1 li{
  display: block;
  width: 100%;
  margin-top: 8%;
}
.main-header .hide-on-small .user-wrapper{
  float: right;
  width: 5%;
}
.main-header .hide-on-small .user-wrapper .userBtn{
  cursor: pointer;
}
</style>

<script>
import { reactive } from 'vue';

export default {
  name: 'main-header',
  props: {
      height: {
        type: String,
        default: '70px'
      },
  },
  setup() {
    const state = reactive({
      showHiddenMenu: false,
    })
    const changeDisplay = function(cmd){
      if(cmd === "over")
        state.showHiddenMenu = 'block';
      else
        state.showHiddenMenu = 'none';
      console.log(state.showHiddenMenu)
    }
    return { state, changeDisplay }
  },
}
</script>
