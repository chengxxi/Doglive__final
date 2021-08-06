<template>
  <div class="mypage-body">
      <div class="hide-on-small">
        <div class="menu-title">
          <h5>작성글 목록</h5>
        </div>
        <div class="mypage-content">
          <posting-card :card="state.tableData"></posting-card>
        </div>
      </div>
    </div>
</template>

<style scoped>
.mypage-body{
  float: left;
  width: calc(100% - 240px);
  height: 100%;
}
.mypage-body .menu-title{
  width: inherit;
  padding-top: 10px;
  padding-bottom: 10px;
  border-bottom: solid 1px rgb(212, 212, 212);
}
.mypage-body .mypage-content {
  padding-top: 60px;
  text-align: center;
  margin: 0 auto;
}
</style>

<script>
import $axios from 'axios';
import { computed, reactive, onMounted } from 'vue';
import { useStore } from 'vuex'
import { useRouter } from 'vue-router';
import PostingCard from './user-post-list-card.vue'

export default {
  name: 'user-post-list',
  components :{
    PostingCard
  },
  setup() {
    const store = new useStore()
    const router = new useRouter()
    const state = reactive({
     tableData : [],
    })
    const userId = store.getters['root/getLoginUserInfo'].userId;

    store.dispatch('root/requestUserPostList', userId)
      .then(function(result){
        console.log("check : " + result)
        state.tableData = result.data.boardList;
      }).catch(function(err){
        console.log(err)
      });

      return { state }
  }
}
</script>
