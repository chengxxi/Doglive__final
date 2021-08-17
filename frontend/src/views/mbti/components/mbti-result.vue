<template>
  <div class="mbtibox">
    <div class="box">
      <h3 class="description"></h3>
      <!-- <br /> -->
      <p>'독립'이 만든 강아지 성향 MBTI</p>
      <!-- 상세 소개 내용 수정 필요 -->
    </div>

    <span v-for="(card, idx) in state.MbtiList" :key="idx">
      <el-col :span="6">
        <MbtiCard
          style="cursor:pointer;"
          :card="card"
          @click="readDetail(card.id)"
        />
      </el-col>
    </span>
  </div>
</template>

<style scoped>
.mbtibox {
  padding-bottom: 50px !important;
}
.box {
  margin-top: 30px;
  margin-bottom: 40px;
  margin-left: 50px;
  margin-right: 50px;
  padding: 40px;
  background-color: #f9f0e7;
}

.description {
  font-size: 1.5rem;
  font-weight: 600;
}

.mbtiBox {
  margin: 30px 50px;
  padding: 40px;
}
</style>

<script>
import MbtiCard from "./mbti-card.vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { reactive, onMounted } from "vue";

export default {
  name: "mbti-result",
  components: { MbtiCard },
  // methods: {
  //   toggleCard: function(card) {
  //       card.flipped = !card.flipped;
  //   },
  // }
  props: {
    mbtiList: Array
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();
    const state = reactive({
      mbtiList: []
    });

    const readDetail = function(id) {
      store
        .dispatch("root/requestMbtiDetail", id)
        .then(function(result) {
          console.log("Mbti:", result);
          // console.log(result.data.mbtiList)

          const data = {
            id: result.data.mbti.id,
            name: result.data.mbti.name,
            title: result.data.mbti.title,
            desc: result.data.mbti.desc,
            imageUrl: result.data.mbti.imageUrl,
            matchedBoardList: result.data.matchedBoardList
          };

          store.commit("root/setMbtiDetail", data);

          router.push({ name: "MbtiDetail" });
        })
        .catch(function(err) {
          console.log(err);
        });
    };

    // MBTI 읽어오기
    const readMbtiList = function() {
      store.dispatch("root/requestMbtiList").then(function(result) {
        console.log("MBTI:", result);
        // console.log(result.data.mbtiList)

        state.MbtiList = result.data.mbtiList;
      });
    };

    onMounted(() => {
      // readDetail();
      readMbtiList();
      window.scrollTo(0, 0);
    });

    return { state, readDetail, readMbtiList };
  }
};
</script>
