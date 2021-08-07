<template>
  <div>
    <el-row>
    <el-col :span="6" v-for="(o, idx) in card" :key="idx"> 
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
            <el-button type="text" class="button" @click="readDetail(o.id)" >글 보러가기</el-button>
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
import { onBeforeMount, onMounted, reactive, computed } from "vue";
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
        boardList: [],
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

          $axios.delete('/board/bookmark/'+store.getters['root/getLoginUserInfo'].userId+'/'+state.board.boardId)
          .then(function(result){
            console.log('deleteBookmark!!!!!!');
            store.commit('root/setIsbookmarked', false)
            alert('북마크가 해제되었습니다');
          }).catch(function(err){
            console.log(err)
          });

        }else{
          $axios.post('/board/bookmark', {
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

      const readDetail = function(id) {
      const userid = store.getters["root/getLoginUserInfo"].userId;

      $axios
        .get("/board/" + id + "/" + userid)
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
            dogName: result.data.dogInformation.dogName
          };

          store.commit("root/setBoardDetail", boardDetail);

          router.push({ name: "AdoptDetail" });
        })
        .catch(function(err) {
          console.log(err);
        });
    };

      return { state, clickBookmark, readDetail}

  }
}
</script>
