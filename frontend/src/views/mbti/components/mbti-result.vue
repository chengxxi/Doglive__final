<template>
  <div>
    <div class="box">
      <h3 class="description">
        🐕 강아지 MBTI란? 🐾
      </h3>
      <br>
      <p>MBTI 개별 유형 설명 페이지</p>
      <!-- 상세 소개 내용 수정 필요 -->
    </div>

    <span v-for="(idx, card) in state.MbtiList" :key="idx">
      <el-col :span="6">
        <MbtiCard
          :card="card"
          @click="readDetail(card.id)"
        />
      </el-col>
    </span>

<!--
    <el-row class="mbtiBox">
      <MbtiCard
        v-for="(idx, card) in 16"
        :key="idx"
        :card="card"
        @click="readDetail(card.boardId.id)"
        :span="6"
      />
    </el-row> -->



  </div>

</template>

<style scoped>
.box {
  margin-top: 30px;
  margin-bottom: 30px;
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
    boardList: Array
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();
    const state = reactive({
      mbtiList: []

    });


    const readDetail = function(id) {
      store.dispatch('root/requestMbtiDetail', id).then(function(result) {
        console.log('Mbti:', result);
        // console.log(result.data.mbtiList)
        store.commit('root/setMbtiDetail', result.data.mbti)
        store.push({ name : 'MbtiDetail' })
      });

      router.push({ name: 'MbtiDetail' });

    };


    // MBTI 읽어오기
    const readMbtiList = function() {
      store.dispatch('root/requestMbtiList').then(function(result) {
        console.log('Mbti:', result);
        // console.log(result.data.mbtiList)

        state.MbtiList = result.data.mbtiList
      });
    };

    onMounted(() => {
      readMbtiList();
    })



    return { state, readDetail, readMbtiList, };
  }




}
</script>
