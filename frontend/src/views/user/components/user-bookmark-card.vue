<template>
  <div>
    <el-row :gutter="20">
    <el-col :span="6" v-for="(o, idx) in card" :key="idx">
      <el-card :body-style="{ padding: '10px' }" style="background-color:#f9f4f0; width:90%; height:360px;" shadow="hover">
        <el-row style="margin-top:1%; margin-right:1%; margin-left:1%;">
          <el-tag
            v-if="o.type.name == '입양'"
            class="mb-2"
            color="#D7AFA4"
            effect="dark"
            size="small"
            style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF; float:right; "
            >{{ o.type.name }}</el-tag
          >
          <el-tag
            v-if="o.type.name == '임보'"
            class="mb-2"
            color="#E9CDA4"
            effect="dark"
            size="small"
            style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF;  float:right;"
            >{{ o.type.name }}</el-tag
          >
          <el-tag
            v-if="o.type.name == '실종'"
            class="mb-2"
            color="#bdaf9f"
            effect="dark"
            size="small"
            style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF;  float:right;"
            >{{ o.type.name }}</el-tag
          >
          <el-tag
            v-if="o.type.name == '보호'"
            class="mb-2"
            color="#a3723c"
            effect="dark"
            size="small"
            style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF;  float:right;"
            >{{ o.type.name }}</el-tag
          >
        </el-row>
        <el-row style="margin-top:1%; display:flex; display: flex; justify-content: center;">
          <img src="https://placedog.net/500/500?random" class="image" />
        </el-row>
        <el-row style="margin-top:3%; margin-right:2.5%; margin-left:2.5%; vertical-align: middle;">
          <h6 style="font-weight:700; margin-top:10%;" class="mb-0">
            {{ o.title.length < 15
                ? o.title
                : o.title.substr(0, 12) + "..."}}
          </h6>
        </el-row>
        <el-row>
          <div style="padding-top:7%; text-align:right; maring-right:4%;">
            <el-button style="justify-content: space-between; align-items: center; margin-right:5%; color:black;" type="text" class="button" @click="readDetail(o.id)"  >글 보러가기</el-button>
            <font-awesome-icon
            icon="trash-alt"
            aria-hidden="true"
            style="color: rgb(0, 0, 0); font-size: 2.5vmin; cursor: pointer; margin-top: 10px; text-align:right; display:inline-block;"
            @click="clickBookmark(o.id)">
            </font-awesome-icon>
          </div>
        </el-row>
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
    margin-top: 2%;;
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
                position: "bottom-left",
                transition: "bounce",
                type: "success"
              });
              router.go(router.currentRoute)
            })
            .catch(function(err) {
              createToast("북마크 해제에 실패했어요 😱💦", {
                hideProgressBar: "true",
                timeout: 4500,
                showIcon: "true",
                toastBackgroundColor: "#c49d83",
                position: "bottom-left",
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
