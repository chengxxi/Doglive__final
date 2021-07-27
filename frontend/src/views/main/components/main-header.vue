<template>
<el-header class="main-header"
  :style="{'height': height}">
  <!-- Desktop 전용 화면 -->
  <div class="hide-on-small">
    <div class="menu-wrapper" @mouseover="changeDisplay('over')" @mouseleave="changeDisplay('leave')">
        <ul class="depth0">
            <li>
              <div class="logo-wrapper">
                <div class="logo"/>
              </div>
            </li>
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
            <li>
              <el-avatar class="userBtn" icon="el-icon-user-solid" @click="click"></el-avatar>
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
  right: 0;
  text-align: center;
  overflow: visible;
}
.main-header .hide-on-small{
  height: inherit;
}
.main-header .hide-on-small .menu-wrapper{
  display: inline-block;
  width: 100%;
  height: inherit;
}
.main-header .hide-on-small .menu-wrapper .depth0{
  width: 100%;
  height: auto;
  font-size: 18px;
  font-weight: 500;
  background-color: rgba(255, 255, 255, 0.2);
}
.main-header .hide-on-small .menu-wrapper .depth0 ul{
  list-style: none;
}
.main-header .hide-on-small .menu-wrapper .depth0 li{
  display: inline-block;
  width: 15%;
  line-height: 70px;
  vertical-align: top;
}
.main-header .hide-on-small .menu-wrapper .depth0 a:hover{
  color: #D7AEA4;
}
.main-header .hide-on-small .menu-wrapper .depth1{
  display: block;
  text-align: center;
  margin-top: 40px;
  font-size: 16px;
  font-weight: 400;
}
.main-header .hide-on-small .menu-wrapper .depth1 li{
  width: 100%;
  line-height: 5px;
}
.main-header .hide-on-small .logo-wrapper{
  float: left;
  cursor: pointer;
}
.main-header .hide-on-small .logo-wrapper .logo {
  width: 150px;
  height: 50px;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url('../../../assets/images/temp-logo.png');
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
