<template>
  <el-container class="main-wrapper">
    <main-header :height="`70px`" />
    <el-container class="main-container">
      <router-view></router-view>
    </el-container>
    <main-footer :height="`70px`" />
  </el-container>
  <chat/>
</template>

<style>
@import "./main.css";
@import "../../common/css/common.css";
@import "../../common/css/element-plus.css";
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
import "element-ui/lib/theme-chalk/index.css";

export default {
  name: "main",
  components: {
    MainHeader,
    MainFooter,
    Chat,
  },
  setup() {
    const store = useStore();
    const router = useRouter();

    // URL이 변경되기전 거쳐가는 함수
    router.beforeEach(function(to, from, next){
      if(to.matched.some(record => record.meta.requiresAuth)){ // 로그인이 필요한 페이지라면
        if(store.getters["root/getLoginUserInfo"].userId === null){ // 현재 로그인된 상태인지 확인
          next({ path: '/login'})
        }else{
          next()
        }
      }else{
        next()
      }
    })

    // URL이 변경된 후, 현재 Path를 저장하는 함수
    router.afterEach((to, from, next) => {
      store.commit("root/setCurrentPath", to.path);
      store.commit("root/setCurrentPathParam", to.query);
      console.log(to);
    });
  }
};
</script>
