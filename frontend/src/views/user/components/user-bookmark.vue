<template>
  <div class="mypage-body">
      <div class="hide-on-small">
        <div class="menu-title">
          <h5>북마크</h5>
        </div>
        <div class="mypage-content">
          <el-row v-if="state.tableData.length == 0">
            <el-empty
              style="margin-top:80px; margin-bottom:50px;"
              description="북마크한 글이 없다개 💨"
            ></el-empty>
          </el-row>
          <el-row v-if="state.tableData.length != 0" style="margin-bottom : 200px;">
            <bookmark-card :card="state.tableData"></bookmark-card>
          </el-row> 
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
/* .mypage-body .menu-title{
  width: inherit;
  padding-top: 10px;
  padding-bottom: 10px;
  border-bottom: solid 1px rgb(212, 212, 212);
} */
.mypage-body .menu-title {
  text-align: left;
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
import BookmarkCard from './user-bookmark-card.vue'

export default {
  name: 'user-bookmark',
  components:{
    BookmarkCard,
  },
  
  setup() {
    const store = new useStore()
    const router = new useRouter()
    const state = reactive({
     tableData : [],
    })

    const userId = store.getters['root/getLoginUserInfo'].userId;

    store.dispatch('root/requestBookmarkList', userId)
      .then(function(result){
        console.log(result.data)
        state.tableData = result.data.boardList;
      }).catch(function(err){
        console.log(err)
      });

    return { state }
   
  }
}
</script>