<template> </template>
<style scoped></style>

<script>
import Cookies from "universal-cookie";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "logout-dialog",
  setup() {
    const router = new useRouter();
    const store = new useStore();
    const cookies = new Cookies();

    // Request Header에 cookie를 담아서 전송 ({withCredentials: true} 설정을 통해 Cookie를 헤더에 담아서 전송 가능.)
    store
      .dispatch("root/requestKakaoLogout", { withCredentials: true })
      .then(function(result) {
        // cookie 삭제
        cookies.remove("accessToken", { path: "/", sameSite: "strict" });
        cookies.remove("refreshToken", { path: "/", sameSite: "strict" });
        cookies.remove('userId', { path : '/', sameSite : 'strict' })
        store.commit("root/setLoginUserInfo", null);
        const boardDetail = {
          boardId: 1,
          boardType: 1,
          dogName: "",
          thumbnailUrl: "",
          title: "",
          address: "",
          mbti: "",
          description: "",
          colorType: {},
          gender: {},
          hairType: {},
          neutralization: {},
          userName: "",
          weight: {},
          ageType: {},
          regDate: {},
          readCount: {},
          fileList: [],
          isOwner: false,
          isbookmarked: false
        };

        store.commit("root/setBoardDetail", boardDetail);
        store.commit("root/setChatOpen", false);
        store.commit("root/setChatTitle", "");
        store.commit("root/setChatRoomId", "");
        store.commit("root/setChatMenu", 0);
        store.commit("root/setExitMessage", false);

        createToast(
          {
            title: "다음에 또 만나개 🙌🐶",
            description: "로그아웃이 완료되었어요"
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

        router.push({ name: "Main" });
      })
      .catch(function(err) {
        createToast("로그아웃에 실패했어요 💬💦", {
          hideProgressBar: "true",
          timeout: 4000,
          showIcon: "true",
          toastBackgroundColor: "#c49d83",
          position: "bottom-left",
          transition: "bounce",
          type: "warning"
        });
        router.push({ name: "Main" });
      });
  }
};
</script>
