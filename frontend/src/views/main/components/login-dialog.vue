<template>
  <div class="main-body  vertical-center">
    <el-card class="login-wrapper p-3" body-style="height: inherit">
      <div class="login-content mt-3">
        <div
          class="login-title vertical-center "
          style=" display: flex;
  justify-content: center;"
        >
          <h5 class=" mb-4">간편 로그인</h5>
        </div>
        <div class="button-group mb-2">
          <img width="200" :src="require('@/assets/images/mbti_isfp.png')" />
          <h6 class="mt-3 comment" style="font-size:10pt;">
            카카오 아이디로 간편하게 시작하세요 🐾
          </h6>
          <img
            class="loginBtn"
            @click="loginWithKakao"
            :src="require('@/assets/images/kakao_login_medium_wide.png')"
          />
        </div>
      </div>
    </el-card>
  </div>
</template>
<style scoped>
/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%) */
.main-body {
  display: flex;
  width: 100%;
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 10%;
  margin: 0 auto;
}
.login-wrapper {
  width: 500px;

  margin: auto;
  text-align: center;
}
.login-wrapper .login-title {
  height: 20px;
  font-size: 1.125rem;
  font-weight: 600;
  padding-bottom: 10px;
  border-bottom: solid 1px rgb(212, 212, 212);
}
.login-wrapper .login-content {
  height: calc(100% - 70px); /* el-card padding(40px) + login-title(30px) */
  padding-top: 2%;
}
.login-wrapper .button-group {
  width: 300px;
  padding-top: 25px;
  margin: 0 auto;
}
.login-wrapper .comment {
  display: block;
  margin-bottom: 10px;
  /* text-align: left; */
}
.login-wrapper .loginBtn {
  cursor: pointer;
}
</style>

<script>
export default {
  name: "login-dialog",
  setup() {
    if (!Kakao.isInitialized()) {
      Kakao.init("bacd72f58ac01490602415c683ad8c05");
    }
    // 카카오 로그인
    const loginWithKakao = function() {
      Kakao.Auth.authorize({
        redirectUri: "http://i5a501.p.ssafy.io:8080/kakao/callback"
      });
    };
    return { loginWithKakao };
  }
};
</script>
