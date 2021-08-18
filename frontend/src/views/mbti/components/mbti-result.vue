<template>
  <div class="mbtibox">
    <div class="box">
      <h3 class="description"></h3>
      <!-- <br /> -->
      <p>
        "독립"의 'MPTI(강아지 성향 지표, My Puppy Type Indicator)'는 총 20가지의 문항을 통해 응답자가 선호하는 강아지의 행동 및 경향을 찾고, 이러한 선호도를 토대로 응답자와 잘 맞는 강아지를 추천할 수 있도록 제작된 검사입니다. <br>
        '에너지 넘치는' 또는 '침착한'의 "활동 성향", '충성심 강한' 또는 '영리함'의 "순종 성향", '관계 지향' 또는 '독립 지향'의 "관계 성향", '신중한' 또는 '친화적인'의 "적응 성향"의 네 가지 범주를 지정하였습니다.
      </p>
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
import MbtiCard from './mbti-card.vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { reactive, onMounted } from 'vue'

export default {
  name: 'mbti-result',
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
        .dispatch('root/requestMbtiDetail', id)
        .then(function(result) {
          console.log('Mbti:', result);
          // console.log(result.data.mbtiList)

          const data = {
            id: result.data.mbti.id,
            name: result.data.mbti.name,
            title: result.data.mbti.title,
            desc: result.data.mbti.desc,
            imageUrl: result.data.mbti.imageUrl,
            matchedBoardList: result.data.matchedBoardList
          };

          store.commit('root/setMbtiDetail', data);

          router.push({ name: 'MbtiDetail' });
        })
        .catch(function(err) {
          console.log(err);
        });
    };

    // MBTI 읽어오기
    const readMbtiList = function() {
      store.dispatch('root/requestMbtiList').then(function(result) {
        console.log('MBTI:', result);
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
