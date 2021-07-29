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
          <!-- <div class="button-group">
            <span class="comment">아직 아이디가 없으신가요?</span>
            <img class="login-Btn" @click="login" :src="require('@/assets/images/kakao_login_start_medium_wide.png')"/>
          </div> -->
        </div>
      </el-card>
  </div>
</template>
<style scoped>
/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%, 위 여백 10%) */
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
  /* 수직 정렬을 위한 스타일 */
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
export default {
  name: 'login-dialog',
  setup() {

    // 카카오 로그인
    const loginWithKakao = function(){
      console.log('login')
      const params = {
        redirectUri: "http://localhost:8080/kakao/login",
        scope: 'account_email, birthday'
      }
      window.Kakao.Auth.authorize(params);

      // Kakao.Auth.login({
      //   success: function(response){
      //     console.log(response);
      //   },
      //   fail: function(error){
      //     console.log(error)
      //   }
      // })
    }

    return { loginWithKakao }
  }
}
</script>
