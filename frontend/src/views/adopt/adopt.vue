<template>
  <div class='main-body main-padding'>
    <el-card class='box-card ' style='width:100%; border:none;'>
      <bread-crumb></bread-crumb>

      <div style='margin-top:50px'>
        <h1 class='title'>입양하기 게시물</h1>
        <p>입양을 기다리는 아이들</p>
        <!-- 상세 문구 수정 필요 -->
      </div>

      <div>
        <el-button type='outline-primary' @click='goRegister' round>
          입양 공고 작성하기
        </el-button>

        <!-- if 사용자가 아직 테스트 전이면 -->
        <el-button type='outline-primary' @click='goMBTI' round>
          나와 맞는 강아지 찾기
        </el-button>


        <!-- if 사용자의 테스트 결과가 있으면 필터링-->



        <AdoptFilter />
      </div>



      <AdoptList />
    </el-card>
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
import AdoptFilter from './components/adopt-filter.vue'
import AdoptList from './components/adopt-list.vue'
import BreadCrumb from './components/bread-crumb.vue'
import { reactive, computed, onMounted } from 'vue'; // ref
// import $axios from 'axios'

import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { createToast } from 'mosha-vue-toastify'
import 'mosha-vue-toastify/dist/style.css'



export default {
  name: 'adoptlist',
  components: {
    AdoptFilter,
    AdoptList,
    BreadCrumb
  },

  setup() {
    const store = new useStore();
    const router = new useRouter();

    const state = reactive({
      userId: computed(() => {
        return store.getters['root/getLoginUserInfo'].userId;
      })
    });

    const goRegister = function() {
      if (state.userId === null) {
        alert('로그인을 진행해주세요!');
        router.push({ name: 'Login' });
      } else {
        router.push({ name: 'AdoptRegister' });
      }
    };

    const goMBTI = function() {
      // if 사용자가 아직 테스트 전이면
      createToast('아직 테스트 결과가 없어요.', {
          hideProgressBar: 'true',
          timeout: 4500,
          showIcon: 'true',
          toastBackgroundColor: '#c49d83',
          position: 'bottom-right',
          transition: 'bounce',
          type: 'warning'
        });
        router.push({ name: 'MBTI' });

      // else 사용자 테스트 결과가 있으면 필터링
    };

    onMounted(() => {
      console.log('breadcrumb');
      store.commit('root/setBreadcrumbInfo', {
        isHome: false,
        title: '입양/임보',
        subTitle: '입양 공고 목록'
      });
    });

    return {
      state,
      goRegister,
      goMBTI
    };
  }
};
</script>
