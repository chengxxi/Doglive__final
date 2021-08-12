<template>
  <span v-for='(card, idx) in boardList' :key='idx'>
    <el-col :span='6'>
      <MbtiCard
        :card='card'
        @click='readDetail(card.boardId.id)'
        style='margin:10px; '
      />
    </el-col>
  </span>
</template>

<style scoped>
</style>


<script>
import $axios from 'axios'
import MbtiCard from '@/views/mbti/components/mbti-card'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { reactive, computed } from 'vue'


export default {
  name: 'mbti-result',
  components: { MbtiCard },

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
      console.log('read');
      const userid = store.getters['root/getLoginUserInfo'].userId;

      $axios
        .get('/board/' + id + '/' + userid)
        .then(function(result) {
          console.log(result);

          const boardDetail = {
            boardId: result.data.board.id,
            boardType: result.data.board.type,
            thumbnailUrl: result.data.board.thumbnailUrl,
            title: result.data.board.title,
            address: result.data.dogInformation.address,
            mbti: result.data.dogInformation.mbti,
            colorType: result.data.dogInformation.colorType,
            gender: result.data.dogInformation.gender,
            dogType: result.data.dogInformation.dogType,
            neutralization: result.data.dogInformation.neutralization,
            writer: result.data.writer,
            weight: result.data.dogInformation.weight,
            ageType: result.data.dogInformation.age,
            regDate: result.data.board.regDate,
            fileList: result.data.boardImageList,
            isOwner: result.data.owner,
            description: result.data.dogInformation.description,
            dogName: result.data.dogInformation.dogName,
            isBookmarked: result.data.bookmarked
          };

          store.commit('root/setBoardDetail', boardDetail);

          router.push({ name: 'AdoptDetail' });
        })
        .catch(function(err) {
          console.log(err);
        });
    };



    return { state, readDetail, };
  }
};
</script>
