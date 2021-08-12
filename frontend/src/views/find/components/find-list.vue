<template>
  <!-- <AdoptCard /> -->
  <span v-for="(card, idx) in boardList" :key="idx">
    <el-col :span="6">
      <FindCard
        :card="card"
        @click="readDetail(card.boardId.id)"
        style="margin:10px; "
      />
    </el-col>
  </span>
</template>

<style scoped></style>

<script>
import $axios from "axios";
import FindCard from "@/views/find/components/find-card.vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { onBeforeMount, onMounted, reactive, computed } from "vue";

export default {
  name: "FindList",
  components: { FindCard },
  // props: {
  //   // boardList: Object,
  //   // title: String,
  //   // boardType: String,
  //   // cards: Array,
  // },
  props: {
    boardList: Array
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();
    const state = reactive({
      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"].userId;
      })
    });

    const readDetail = function(id) {
      console.log("read");
      store.commit("root/setBoardId", id);
    };

    const goRegister = function() {
      if (state.userId === null) {
        alert("로그인해주세요");
        router.push({ name: "Login" });
      } else {
        router.push({ name: "AdoptRegister" });
      }
    };

    return { state, readDetail, goRegister };
  }
};
</script>
