<template>
  <div>
    <el-row>
      <AdoptCard
        v-for='(card, idx) in state.boardList'
        :key='idx'
        :card='card'
        @click='readDetail(card.id)'
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
import $axios from 'axios';
import AdoptCard from '@/views/adopt/components/adopt-card';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { onBeforeMount, onMounted, reactive, computed } from 'vue';

export default {
  name: 'adopt-list',
  components: { AdoptCard },


  setup() {
    const store = new useStore();
    const router = new useRouter();
    const state = reactive({
      boardList: [],
      userId: computed(() => {
        return store.getters['root/getLoginUserInfo'].userId;
      })
    });

    const readData = function() {
      const userid = store.getters['root/getLoginUserInfo'].userId;
      console.log(userid)

      $axios
        .get('/board/adopt')
        .then(function(result) {
          console.log(result);
          console.log(result.data.boardList);

          state.boardList = result.data.boardList;
        })
        .catch(function(err) {
          console.log(err);
        });
    };

    const readDetail = function(id) {
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
            hairType: result.data.dogInformation.hairType,
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

    const goRegister = function() {
      if (state.userId === null) {
        alert('로그인해주세요');
        router.push({ name: 'Login' });
      } else {
        router.push({ name: 'AdoptRegister' });
      }
    };

    onMounted(() => {
      store.commit('root/setBreadcrumbInfo', {
        isHome: false,
        title: 'Adopt',
        subTitle: '입양 공고 목록'
      });
      window.scrollTo(0, 0);
    });

    onBeforeMount(() => {
      readData();
    });

    return { state, readData, readDetail, goRegister };
  }
};
</script>
