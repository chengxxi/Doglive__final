<template>
  <div>
    <div class="box">
      <h3 class="description">
        🐕 강아지 MBTI란? 🐾
      </h3>
      <br>
      <p>MBTI 개별 유형 설명 페이지</p>
      <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Laudantium at illo molestias possimus nihil minus? Magnam dolor, voluptas minus vitae ab odio excepturi velit provident unde saepe itaque reiciendis nesciunt!</p>
      <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Voluptatem dolor laboriosam, neque veritatis maiores culpa quis, molestiae quisquam eius necessitatibus dolorum. Itaque esse laboriosam, similique atque tempora corrupti architecto asperiores.</p>
      <!-- 상세 소개 내용 수정 필요 -->
    </div>

    <span v-for="(idx, card) in boardList" :key="idx">
      <el-col :span="6">
        <MbtiCard
          :card="card"
          @click="readDetail(card.boardId.id)"
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
// import $axios from 'axios'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { reactive, computed } from 'vue'



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
      userId: computed(() => {
        return store.getters['root/getLoginUserInfo'].userId;
      })
    });

    const readDetail = function(id) {
      // console.log('read');
      store.commit('root/setBoardId', id);
      router.push({ name: 'MbtiDetail' });
    };


    return { state, readDetail, };
  }




}
</script>
