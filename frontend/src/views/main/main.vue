<template>
  <el-container class="main-wrapper">
    <main-header :height="`70px`" />
    <el-container class="main-container">
      <router-view></router-view>
    </el-container>
    <main-footer :height="`70px`" />
  </el-container>
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
import "element-ui/lib/theme-chalk/index.css";

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

    router.afterEach((to, from, next) => {
      store.commit("root/setCurrentPath", to.path);
      store.commit("root/setCurrentPathParam", to.query);
      console.log(to);
    });
  }
};
</script>
