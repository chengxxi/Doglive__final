<template>
  <div class="main-body main-padding">
    <div style="border:none; width:100%;">
      <div>
        <bread-crumb></bread-crumb>
        <div class="title" style="margin-top:3%;">
          <h2>독닢신문</h2>
        </div>   
        <div class="button" style="text-align:left; margin-top:2%;">
          <el-button
            type="outline-primary"
            round
            @click="goRegister"
            >글 작성하기</el-button
          >
          <el-button
            type="outline-primary"
            round
            @click="goRegister"
            >내 피드 보러가기</el-button
          >
      </div>
        <el-card
          class="box-card "
          style="width:100%;border:none; margin-left:1%; margin-right:1%;"
          shadow="none"
        >
          <router-view></router-view>
        </el-card>
      </div>
    </div>
  </div>
</template>

<style scoped>
.main-body {
  width: 100%;
  margin-left: 10%;
  margin-right: 10%;
  margin-bottom: 50px;
}
.main-padding {
  padding-top: 50px;
}

.title {
  font-size: 2.5rem;
  font-weight: normal;
}
.el-button {
  margin-right: 1%;
}
.button .el-button{
  border: solid 1px lightgray !important;
}
.button .el-button:hover {
  color: black !important;
  background-color: #f9f0e7 !important;
  border: solid 1px #f9f0e7 !important;
}
</style>

<script>
import { defineComponent, reactive, computed, ref } from "vue";
import $axios from "axios";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { onMounted } from "vue";
import BreadCrumb from "./components/bread-crumb.vue";
import CommunityBoardList from "./components/community-board-list.vue";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "community",
  components: {
    BreadCrumb,
    CommunityBoardList
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();
    const state = reactive({
      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"].userId;
      })
    });

    const goRegister = function() {
      if (state.userId === null) {
        createToast("로그인을 진행해주세요 💨💨", {
          hideProgressBar: "true",
          timeout: 4500,
          showIcon: "true",
          toastBackgroundColor: "#c49d83",
          position: "bottom-left",
          transition: "bounce",
          type: "success"
        });
        router.push({ name: "Login" });
      } else {
        router.push({ name: "community-board-register" });
      }
    };

    onMounted(() => {
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "독립신문",
        path: "/community",
        subTitle: "게시글 구경하기"
      });

      window.scrollTo(0, 0);
    });

    return { state, goRegister };
  }
};
</script>