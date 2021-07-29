<template>
  <div class="main-body">
      <el-card class="login-wrapper"
        body-style="height: inherit">
        <div class="login-content">
          <div class="login-title">
            <span>간편 로그인</span>
          </div>
          <div class="button-group">
            <img width="200" :src="require('@/assets/images/mbti_isfp.png')"/>
            <span class="comment">카카오 아이디로 간편하게 로그인!
            </span>
            <img class="loginBtn" @click="loginWithKakao" :src="require('@/assets/images/kakao_login_medium_wide.png')"/>
          </div>
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

export default {
  name: 'login-dialog',
  setup() {
    const store = new useStore()
    const router = new useRouter()

    // 카카오 로그인
    const loginWithKakao = function(){
      store.dispatch('root/requestLogin')
      .then(function(result){
        console.log(result)
        openKakaoLogin(result.data) // 카카오 로그인 창 OPEN & 로그인
      })
      .catch(function(err){
        console.log('로그인 실패' + err);
      })
    }

    const openKakaoLogin = function(url){
      axios.get(url)
      .then(function(result){
        // accessToken 및 회원정보 저장
        console.log(result.user)
        store.commit('root/setAccessToken', result.user.Token.accessToken)
        store.commit('root/setRefreshToken', result.user.Token.refreshToken)
        store.commit('root/setLoginUserInfo', result.user.userInfo.userInfo)
        alert("로그인 되었습니다!")
        // 기존 페이지로 redirect
        router.push({name: 'Main'})
      })
      .catch(function(err){
        alert("로그인에 실패하였습니다. 아이디와 비밀번호를 확인해주세요.")
        router.push({name: 'Main'})
      })
    }

    return { loginWithKakao }
  }
}
</script>
