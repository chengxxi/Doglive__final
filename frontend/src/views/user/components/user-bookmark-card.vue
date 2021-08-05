<template>
  <div>
    <el-row>
    <el-col :span="6" v-for="(o, idx) in card" :key="idx"> <!-- :offset="index > 0 ? 2 : 0" -->
      <el-card :body-style="{ padding: '10px' }" style="margin: 10px !important;" shadow="hover">
        <img :src="require('@/assets/images/logo2.png')" class="image" />
        <div style="padding: 14px;">
          <div class="title">
            <span><el-tag style="mini">{{o.type.name}}</el-tag></span>
            <font-awesome-icon
                        :icon="[ state.board.isbookmarked  ? 'fas' : 'far', 'star']"
                        @click="clickBookmark()"
                        aria-hidden="true"
                        style="color: rgb(255, 226, 95); font-size: 25px; cursor: pointer;"
                        class="scale-up-5"
                      >
                </font-awesome-icon>
            </div>
          <div class="bottom">
            <p>{{o.title}}</p>
            <el-button type="text" class="button">글 보러가기</el-button>
          </div>
        </div>
      </el-card>
    </el-col>
    </el-row>
  </div>


</template>

<style scoped>
  .bottom {
    margin-top: 13px;
    line-height: 12px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .button {
    padding: 0;
    min-height: auto;
  }

  .image {
    width: 100%;
    display: block;
  }
  .title{
      display: flex;
     justify-content: space-between;
  }
  .scale-up-5{
      margin-top : 5px;

  }


</style>

<script>
import $axios from 'axios'
import { computed, reactive, onMounted } from 'vue';
import { useStore } from 'vuex'
import { useRouter } from 'vue-router';

export default {
  name: 'bookmark-card',
  data() {
    return {

        isPopoverVisible: false,
        popoverOptions: {
          animation: "scale-fade",
          popoverReference: null,
          placement: "top",
          offset: "0,0"
        }
      }
  },
  props:{
      card :{
          type: String
      }
 
  },
  setup () {
      Kakao.init('2c046ed5f7ec0f72bdf74502a7ccb16c');
      const store = new useStore()
      const router = new useRouter()

      const state = reactive({
        isBookmarked : computed(()=>{
          return store.getters['root/getIsbookmarked']
        }),
        board : computed(()=>{
          console.log(store.getters['root/getBoardDetail'])
          return store.getters['root/getBoardDetail']
        })
      })

      const clickBookmark = function(){
        const isBookmarked = store.getters['root/getIsbookmarked'];

        console.log('북마크 등록 ', isBookmarked);
        if(isBookmarked){

          $axios.delete('/api/v1/board/bookmark/'+store.getters['root/getLoginUserInfo'].userId+'/'+state.board.boardId)
          .then(function(result){
            console.log('deleteBookmark!!!!!!');
            store.commit('root/setIsbookmarked', false)
            alert('북마크가 해제되었습니다');
          }).catch(function(err){
            console.log(err)
          });

        }else{
          $axios.post('/api/v1/board/bookmark', {
            userId : store.getters['root/getLoginUserInfo'].userId,
            boardId : state.board.boardId
          })
          .then(function(result){
            console.log('insertBookmark!!!!!!');
            store.commit('root/setIsbookmarked', true)
            alert('북마크 등록되었습니다');
          }).catch(function(err){
            console.log(err)
          });
        }
      }

      return { state, clickBookmark}

  }
}
</script>
