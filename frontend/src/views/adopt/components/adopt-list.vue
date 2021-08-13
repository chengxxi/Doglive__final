<template>
  <!-- <AdoptCard /> -->
  <span v-for="(card, idx) in boardList" :key="idx">
    <el-col :span="6">
      <AdoptCard
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
      store
        .dispatch("root/requestBoardDetail", {
          boardId: id,
          userId: userid
        })
        .then(function(result) {
          console.log(result);

          const boardDetail = {
            boardId: result.data.dogInformation.boardId.id,
            boardType: result.data.dogInformation.boardId.type,
            thumbnailUrl: result.data.dogInformation.boardId.thumbnailUrl,
            title: result.data.dogInformation.boardId.title,
            address: result.data.dogInformation.address,
            mbti: result.data.dogInformation.mbti,
            colorType: result.data.dogInformation.colorType,
            gender: result.data.dogInformation.gender,
            dogType: result.data.dogInformation.dogType,
            neutralization: result.data.dogInformation.neutralization,
            writer: result.data.writer,
            weight: result.data.dogInformation.weight,
            ageType: result.data.dogInformation.age,
            regDate: result.data.dogInformation.boardId.regDate,
            fileList: result.data.boardImageList,
            isOwner: result.data.owner,
            gugun: result.data.dogInformation.gugun,
            sido: result.data.dogInformation.gugun.sidoCode,
            description: result.data.dogInformation.description,
            dogName: result.data.dogInformation.dogName,
            isBookmarked: result.data.bookmarked
          };

          store.commit("root/setBoardDetail", boardDetail);
          router.push({ name: "AdoptDetail" });
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

    return { state, readDetail, goRegister };
  }
};
</script>
