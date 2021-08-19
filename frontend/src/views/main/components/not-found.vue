<template>
  <div class="main-body  vertical-center">
    <el-card class="login-wrapper p-3" body-style="height: inherit">
      <div class="login-content mt-3">
        <div
          class="login-title vertical-center "
          style=" display: flex;justify-content:center;font-size:40px; margin-bottom:10px;"
        >
          404
        </div>
        <div class="button-group mb-2">
          <img width="300" :src="require('@/assets/images/ESTJ.png')" />
          <h3 class="mt-3 comment" style="font-size:15pt;">
            접근할 수 없는 페이지입니다!
          </h3>
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
  margin-top: 5%;
  margin-bottom: 5%;
}
.login-wrapper .login-title {
  height: 50px;
  font-size: 40px;
  font-weight: 600;
  padding-bottom: 10px;
  border-bottom: solid 1px rgb(212, 212, 212);
}
.login-wrapper .login-content {
  height: calc(100% - 70px); /* el-card padding(40px) + login-title(30px) */
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
  name: "not-found",
  setup() {

  }
};
</script>
