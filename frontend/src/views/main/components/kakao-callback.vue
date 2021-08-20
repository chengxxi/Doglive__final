<template>
  <div></div>
</template>

<style></style>

<script>
import $axios from "axios";
import Cookies from "universal-cookie";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "kakao-callback",
  setup() {
    const store = new useStore();
    const router = new useRouter();
    const cookies = new Cookies();

    // URL에서 code를 뽑아오는 코드
    const code = store.getters["root/getCurrentPathParam"].code;

    // code를 이용하여 AccessToken 받아온 후,
    store
      .dispatch("root/requestAccessToken", code)
      .then(function(result) {
        console.log(result);
        const accessToken = result.data.user.accessToken;
        const refreshToken = result.data.user.refreshToken;

        // accessToken을 통해 userInfo 받아오기 + store에 저장
        $axios
          .post("/kakao/login", {
            accessToken: accessToken,
            refreshToken: refreshToken
          })
          .then(function(result) {
            console.log(result);
            cookies.set("accessToken", result.data.user.Token.accessToken, {
              path: "/",
              sameSite: "strict"
            });
            cookies.set("refreshToken", result.data.user.Token.refreshToken, {
              path: "/",
              sameSite: "strict"
            });
            cookies.set('userId', result.data.user.userInfo.userid, { path : '/', sameSite : 'strict' })
            store.commit(
              "root/setLoginUserInfo",
              result.data.user.userInfo.userid
            );
            console.log("userid:", result.data.user.userInfo.userid);

            createToast(
              {
                title: "어서오개 🐶💛",
                description: "로그인 되었어요! 환영합니다"
              },
              {
                hideProgressBar: "true",
                timeout: 4000,
                showIcon: "true",
                toastBackgroundColor: "#7eaa72",
                position: "bottom-left",
                transition: "bounce",
                type: "success"
              }
            );

            router.push({ name: "Main" }); // Main으로 redirect
          })
          .catch(function(err) {
            console.log(err);
            createToast("로그인에 실패했어요 💬💦", {
              hideProgressBar: "true",
              timeout: 4000,
              showIcon: "true",
              toastBackgroundColor: "#c49d83",
              position: "bottom-left",
              transition: "bounce",
              type: "warning"
            });
          });
      })
      .catch(function(err) {
        createToast("로그인에 실패했어요 💬💦", {
          hideProgressBar: "true",
          timeout: 4000,
          showIcon: "true",
          toastBackgroundColor: "#c49d83",
          position: "bottom-left",
          transition: "bounce",
          type: "warning"
        });
      });

    router.push({ name: "Main" }); // Main으로 redirect
  }
};
</script>
