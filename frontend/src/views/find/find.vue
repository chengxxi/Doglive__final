<template>
  <div class="main-body main-padding">
    <el-card class="box-card " style="width:100%; border:none;" shadow="hover">
      <bread-crumb></bread-crumb>

      <div style="margin-top:50px">
        <h1 class="title">실종/보호 게시물</h1>
        <p>실종/보호 아이들</p>
        <!-- 상세 문구 수정 필요 -->

      </div>
      <div>
        <el-button type="outline-primary" @click="goRegister" round>
          실종 공고 작성하기
        </el-button>

        <!-- 실종 공고 Filter -->
        <FindFilter />
      </div>

      <FindList />

    </el-card>
  </div>
</template>

<style>
/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%, 위 여백 50px) */
.main-body {
  width: 100%;
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
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
import FindFilter from './components/find-filter.vue';
import FindList from './components/find-list.vue';
import BreadCrumb from './components/bread-crumb.vue';
import { reactive, computed, ref } from 'vue'; // defineComponent
// import $axios from 'axios';

import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { onMounted } from 'vue';

export default {
  name: 'find',
  components: {
    FindFilter,
    FindList,
    BreadCrumb
  },

  setup() {
    const dialogVisible = ref(false);
    const dialogVisible2 = ref(false);

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
        router.push({ name: 'FindRegister' });
      }
    };

    onMounted(() => {
      console.log('breadcrumb');
      store.commit('root/setBreadcrumbInfo', {
        isHome: false,
        title: '실종/보호',
        subTitle: '실종/보호 공고 목록'
      });
    });

    return {
      state,
      dialogVisible,
      dialogVisible2,

      goRegister
    };
  }
};
</script>
