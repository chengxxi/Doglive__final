<template>
  <div>
    <el-row>
    <el-col :span="6" v-for="(o, idx) in card" :key="idx"> 
      <el-card :body-style="{ padding: '10px' }" style="margin: 10px !important;" shadow="hover">
        <img :src="require('@/assets/images/logo2.png')" class="image" />
        <div style="padding: 14px;">
          <div class="title">
           <el-tag class="type" style="mini">{{o.type.name}}</el-tag>
            <!-- <font-awesome-icon
                        icon="eraser"
                        @click="clickBookmark(o.id)"
                        aria-hidden="true"
                        style="color: rgb(78, 78, 78); font-size: 25px; cursor: pointer; margin-top: 10px;"
                        class="scale-up-5"
                      >
                </font-awesome-icon> -->
            </div> 
            <p class="cardtitle">{{o.title}}</p>
            <div class="bottom">
              <el-button type="text" class="button" @click="readDetail(o.id)" >글 보러가기</el-button>
              <el-button type="danger" icon="el-icon-delete" @click="clickBookmark(o.id)"  style="size: small;" circle></el-button>
            </div>
        </div>
      </el-card>
    </el-col>
    </el-row>
  </div>
</template>

<style scoped>
  .type{
    float : left;
  }
  .bottom {
    margin-top: 13px;
    line-height: 12px;
    justify-content: space-between;
    float :right;

  }
  .cardtitle{
    float : left;
    display: block;
    margin : auto;

  }
  .detail{
    margin-right : 2px;
    display: block;
  }
  /* .button {
    padding: 0;
    min-height: auto;
  } */

  .image {
    width: 100%;
    display: block;
  }
  .title{
      display: flex;
     justify-content: space-between;
     margin-top:10px;
  }
  .scale-up-5{
      margin-top : 5px;

  }

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  display: block;
}
.title {
  display: flex;
  justify-content: space-between;
}
.scale-up-5 {
  margin-top: 5px;
}
</style>

<script>
import $axios from "axios";
import { computed, reactive, onMounted } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "bookmark-card",
  data() {
    return {
      isPopoverVisible: false,
      popoverOptions: {
        animation: "scale-fade",
        popoverReference: null,
        placement: "top",
        offset: "0,0"
      }
    };
  },
  props: {
    card: {
      type: String
    }
  },
  setup () {
      const store = new useStore()
      const router = new useRouter()
      const userid = store.getters["root/getLoginUserInfo"].userId;

      const state = reactive({
        boardList: [],
        board : computed(()=>{
          console.log(store.getters['root/getBoardDetail'])
          return store.getters['root/getBoardDetail']

        })
      })

      const clickBookmark = function(id) {
        $axios
            .delete(
              "/board/bookmark/" +
                store.getters["root/getLoginUserInfo"].userId +"/" +id
            )
            .then(function(result) {
              createToast("북마크가 해제되었어요 💨💨", {
                hideProgressBar: "true",
                timeout: 4500,
                showIcon: "true",
                toastBackgroundColor: "#7eaa72",
                position: "bottom-right",
                transition: "bounce",
                type: "success"
              });
              router.push({name: "mypage-bookmark"})
            })
            .catch(function(err) {
              createToast("북마크 해제에 실패했어요 😱💦", {
                hideProgressBar: "true",
                timeout: 4500,
                showIcon: "true",
                toastBackgroundColor: "#c49d83",
                position: "bottom-right",
                transition: "bounce",
                type: "warning"
              });
              console.log(err);
            });
      };

      const readDetail = function(id) {
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
};
</script>
