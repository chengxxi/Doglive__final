<template>
  <div class="main-body main-padding">
    <bread-crumb></bread-crumb>
    <div class="box-card " style="width:100%; border:none;">
      <div style="padding:10px"></div>
      <MbtiIntro />

      <!-- 테스트 하기 버튼 -->
      <!-- <MbtiDescription /> -->
      <!-- <MbtiQuestion /> -->
      <!-- 유형 알아보기 버튼 -->
      <!-- <MbtiResult /> -->
      <div style="padding:50px;"></div>
    </div>
  </div>
</template>

<style>
/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%, 위 여백 50px) */
.main-body {
  width: 100%;
  margin-left: 10%;
  margin-right: 10%;
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
import MbtiIntro from "./components/mbti-intro.vue";
import BreadCrumb from "@/views/adopt/components/bread-crumb.vue";
import { onMounted } from "vue";
import { useStore } from "vuex";

export default {
  name: "mbti",
  components: {
    MbtiIntro,
    BreadCrumb
  },
  setup() {
    const store = new useStore();

    onMounted(() => {
      console.log("breadcrumb");
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "MPTI",
        path: "/mbti",
        subTitle: "MPTI란?"
      });
      window.scrollTo(0, 0);
    });
  }
};
</script>
