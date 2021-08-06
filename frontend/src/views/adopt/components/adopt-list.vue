<template>
  <div class="main-body main-padding row justify-content-center">
    <el-row>
      <!-- <AdoptCard /> -->

      <AdoptCard
        v-for="(card, idx) in state.boardList"
        :key="idx"
        :card="card"
      />
    </el-row>
  </div>
</template>

<style scoped>
.main-body {
  width: 100%;
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 10%;
}
.main-padding {
  padding-top: 50px;
  padding-bottom: 50px;
}
</style>

<script>
import $axios from "axios";
import AdoptCard from "@/views/adopt/components/adopt-card";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { onBeforeMount, onMounted, reactive, computed } from "vue";

export default {
  name: "adopt-list",
  components: { AdoptCard },
  // props: {
  //   // boardList: Object,
  //   // title: String,
  //   // boardType: String,
  //   cards: Array,

  // },

  setup() {
    const store = new useStore();
    const router = new useRouter();
    const state = reactive({
      boardList: [],
      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"].userId;
      })
    });

    const readData = function() {
      const userid = store.getters["root/getLoginUserInfo"].userId;

      $axios
        .get("/api/v1/board/adopt")
        .then(function(result) {
          console.log(result);
          console.log(result.data.boardList);

          state.boardList = result.data.boardList;
        })
        .catch(function(err) {
          console.log(err);
        });
    };

    const goRegister = function() {
      if (state.userId === null) {
        alert("로그인해주세요");
        router.push({ name: "Login" });
      } else {
        router.push({ name: "AdoptRegister" });
      }
    };

    onMounted(() => {
      // console.log('breadcrumb')
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "Adopt",
        subTitle: "입양 공고 목록"
      });
    });

    onBeforeMount(() => {
      readData();
    });

    return { state, readData, goRegister };
  }
};
</script>
