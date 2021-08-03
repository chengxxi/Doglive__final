<template>
  <div class="main-body">
      <el-card class="login-wrapper"
        body-style="height: inherit">
        <div class="group-call-content">
          <el-button @click="joinSession">회의 시작하기</el-button>
        </div>
      </el-card>
  </div>
</template>
<style scoped>
/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%) */
.main-body{
  display: flex;
  width: 100%;
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 10%;
  margin: 0 auto;
}
.login-wrapper{
  width: 400px;
  height: 400px;
  margin: auto;
  text-align: center;
}
.login-wrapper .login-title{
  height: 20px;
  font-size: 1.125rem;
  font-weight: 600;
  padding-bottom: 10px;
  border-bottom: solid 1px rgb(212, 212, 212);;
}
.login-wrapper .login-content{
  height: calc(100% - 70px); /* el-card padding(40px) + login-title(30px) */
  padding-top: 2%;
}
.login-wrapper .button-group{
  width: 300px;
  padding-top: 25px;
  margin: 0 auto;
}
.login-wrapper .comment{
  display: block;
  margin-bottom: 10px;
  /* text-align: left; */
}
.login-wrapper .loginBtn{
  cursor: pointer;
}
</style>

<script>
import axios from 'axios' // 서버로부터 받은 kakao url에 요청보내기 위한 axios
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { WebRTC } from 'vue-webrtc'
import Conferenceroom from '../../conferences/conferenceroom.vue'


export default {
  name: 'conference',
  components: {
  },
  setup() {

    const store = new useStore()
    const router = new useRouter()

    const joinSession = function() {
      location.href="/conference/room";
    }

    return {joinSession}

  }
}
</script>
