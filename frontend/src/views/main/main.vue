<template>
  <div class="main-wrapper">
    <main-header :height="`70px`" />
    <el-container class="main-container">
      <router-view></router-view>
    </el-container>
    <main-footer :height="`70px`" />
  </div>
  <chat />
</template>

<style>
@import "./main.css";
@import "../../common/css/common.css";
@import "../../common/css/element-plus.css";
@import "../../common/css/animation.css";
</style>

<script>
// for bootstrap 5
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";
import MainHeader from "./components/main-header";
import MainFooter from "./components/main-footer";
import Chat from "@/views/chat/chat.vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { createToast } from "mosha-vue-toastify";
import "element-ui/lib/theme-chalk/index.css";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "main",
  components: {
    MainHeader,
    MainFooter,
    Chat
  },
  setup() {
    const store = useStore();
    const router = useRouter();

    // URL이 변경되기전 거쳐가는 함수
    router.beforeEach(function(to, from, next) {
      if (to.matched.some(record => record.meta.requiresAuth)) {
        // 로그인이 필요한 페이지라면
        if (
          store.getters["root/getLoginUserInfo"].userId === null ||
          store.getters["root/getLoginUserInfo"].userId === undefined ||
          store.getters["root/getLoginUserInfo"].userId == ""
        ) {
          // 현재 로그인된 상태인지 확인
          createToast("로그인해야 이용 가능하개🐕‍🦺💨", {
            hideProgressBar: "true",
            timeout: 4500,
            showIcon: "true",
            toastBackgroundColor: "#c49d83",
            position: "bottom-left",
            transition: "bounce",
            type: "warning"
          });
          next({ path: "/login" });
        } else {
          next();
        }
      } else if(to.matched.some(record => record.meta.requiredFromChat)) {
        if(store.getters['root/getConference'].fromChat == false) {
          createToast("비정상적인 접근이개🐕‍🦺💨", {
            hideProgressBar: "true",
            timeout: 4500,
            showIcon: "true",
            toastBackgroundColor: "#c49d83",
            position: "bottom-left",
            transition: "bounce",
            type: "warning"
          });
          next({ path: '/'})
          }else{
          next()
        }
      } else if(from.name == 'conference') {        // 화상회의에서 나올 때
        store.commit('root/setConference', {
          roomID: '',
          thumbnailUrl:'',
          writer:'',    // 글 작성자
          reader:'',    // 신청자
          fromChat:false,
        })
        next()
      } else{
        next()
      }


    })

    // URL이 변경된 후, 현재 Path를 저장하는 함수
    router.afterEach((to, from, next) => {
      store.commit("root/setCurrentPath", to.path);
      store.commit("root/setCurrentPathParam", to.query);
    });
  }
};
</script>
