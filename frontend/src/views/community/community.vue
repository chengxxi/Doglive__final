<template>
  <div class="main-body main-padding">
    <div style="border:none; width:100%;">
      <div>
        <bread-crumb></bread-crumb>

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
</style>

<script>
import { defineComponent, reactive, computed, ref } from "vue";
import $axios from "axios";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { onMounted } from "vue";
import BreadCrumb from "@/views/adopt/components/bread-crumb.vue";
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

    onMounted(() => {
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "독립신문",
        path: "/community",
        subTitle: "게시글 구경하기"
      });

      window.scrollTo(0, 0);
    });

    return { state };
  }
};
</script>
