<template>
  <div class="main-body main-padding">
    <el-card class="box-card " shadow="hover">
      <bread-crumb></bread-crumb>

      <div style="margin-top:60px; margin-left:60px;">
        <h3>{{ state.board.title }}</h3>
      </div>

      <el-row class="vertical-center" :gutter="20" style="margin-top:10px;">
        <el-col :span="12" style="margin-left:50px;">
          <img
            class="dog-thumbnail"
            :src="require('@/assets/images/mbti_isfp.png')"
          />
        </el-col>
        <el-col :span="12">
          <div class="dog-info-box" style="margin-right:50px;">
            <el-tag
              class="mb-3"
              color="#D7AEA4"
              effect="dark"
              size="large"
              style="border:none; border-radius: 30px; font-size:14pt;"
              >{{ state.board.boardType.name }}</el-tag
            >
            <div class="vertical-center row">
              <div class="col-md-9">
                <span :style="{ 'font-size': '30pt', 'font-weight': '700' }">{{
                  state.board.dogName
                }}</span>
              </div>
              <div class="col-md-3 ms-auto">
                <div
                  class="align-self-center vertical-center"
                  style="text-align: center;"
                >
                  <font-awesome-icon
                    :icon="[state.board.isbookmarked ? 'fas' : 'far', 'star']"
                    @click="clickBookmark()"
                    aria-hidden="true"
                    style="color: rgb(255, 226, 95); font-size: 40px; cursor: pointer;"
                  >
                  </font-awesome-icon>

                  <img
                    @click="kakaoShare"
                    style="margin-left:15px; cursor: pointer;"
                    src="//developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_small.png"
                    width="40"
                  />
                </div>
              </div>
            </div>

            <el-divider class="mt-4 mb-4" />

            <el-descriptions class="margin-top mt-3" :column="1" :size="size">
              <el-descriptions-item label="성별"
                >{{ state.board.gender.name }}
              </el-descriptions-item>
              <el-descriptions-item label="연령">{{
                state.board.ageType.name
              }}</el-descriptions-item>
              <el-descriptions-item label="무게">{{
                state.board.weight.name
              }}</el-descriptions-item>
              <el-descriptions-item label="컬러">{{
                state.board.colorType.name
              }}</el-descriptions-item>
              <el-descriptions-item label="품종">{{
                state.board.hairType.name
              }}</el-descriptions-item>
              <el-descriptions-item label="실종/보호장소">{{
                state.board.address
              }}</el-descriptions-item>
            </el-descriptions>

            <el-divider class="mb-3" />

            <div>
              <div v-if="!state.board.isOwner">
                <el-button style="width:100%; background : #755744;"
                  >채팅 보내기</el-button
                >
              </div>
              <div v-if="!state.board.isOwner">
                <el-row>
                  <el-col :span="12"
                    ><el-button
                      class="m-2"
                      style="width:100%;   background : #755744;"
                      @click="goModify(state.board.boardId)"
                      >공고 수정</el-button
                    ></el-col
                  >
                  <el-col :span="12"
                    ><el-button
                      class="m-2"
                      style="width:100%;   background : #C4C4C4;"
                      @click="doDelete(state.board.boardId)"
                      >공고 삭제</el-button
                    ></el-col
                  >
                </el-row>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
      <div class="box">
        {{ state.board.description }}
      </div>
      <el-divider />

      <div class="dog-image-box">
        <el-carousel :interval="4000" type="card" height="500px">
          <el-carousel-item v-for="item in 6" :key="item">
            <img
              style="  width: 100%;
	height: auto;

"
              :src="require('@/assets/images/mbti_infj.png')"
            />
          </el-carousel-item>
        </el-carousel>
      </div>
    </el-card>
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

h3 {
  margin-block-start: 0px;
}

:deep(.el-descriptions) {
  font-size: 13pt;
  font-weight: 500;
}
:deep(.el-descriptions__title) {
  font-size: 30pt;
  font-weight: 700;
}

.dog-thumbnail {
  width: 90%;
  height: auto;
  margin-right: 20px;
  margin: 10px;
}

.dog-info-box {
  width: 100%;
  margin-right: 30px;
  margin: 10px;
  padding-right: 20px;
}

:deep(.el-descriptions__label) {
  display: inline-block;
  width: 150px;
  font-weight: 500;
}

:deep(.el-descriptions__content) {
  display: inline-block;
  font-weight: 500;
}
:deep(.el-button) {
  font-size: 15pt;
  font-family: NEXONLv1Gothic;
  font-weight: 700;
  color: #f9f0e7;
}

.dog-image-box {
  margin: 30px;
  padding: 10px;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.el-col {
  display: flex;

  align-items: center;
}

.box-card {
  margin-top: 20px;
}

.box {
  margin-top: 30px;
  margin-bottom: 40px;
  margin-left: 50px;
  margin-right: 50px;
  padding: 40px;
  background-color: #f9f0e7;
}

.content {
  margin-top: 20px;
  margin-bottom: 20px;
  padding: 20px;
  background-color: #ffffff;
}

.el-card__body {
  display: flex;
  align-items: center;
  margin-right: auto;
}
</style>

<script>
import $axios from "axios";
import BreadCrumb from "./components/bread-crumb.vue";
import { computed, reactive, onMounted } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "FindDetail",
  components: {
    BreadCrumb,
    createToast
  },
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
  setup() {
    if (!Kakao.isInitialized()) {
      Kakao.init("2c046ed5f7ec0f72bdf74502a7ccb16c");
    }
    const store = new useStore();
    const router = new useRouter();

    const state = reactive({
      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"].userId;
      }),
      isBookmarked: computed(() => {
        return store.getters["root/getIsbookmarked"];
      }),
      board: computed(() => {
        console.log(store.getters["root/getBoardDetail"]);
        return store.getters["root/getBoardDetail"];
      })
    });

    const goModify = function(id) {
      console.log(id, "go modify");
      router.push({ name: "AdoptModify" });
    };

    const doDelete = function(id) {
      store
        .dispatch("root/requestDeleteBoard", id)
        .then(function(result) {
          createToast("공고가 삭제되었어요 💨💨", {
            hideProgressBar: "true",
            timeout: 4000,
            showIcon: "true",
            toastBackgroundColor: "#D7AFA4",
            position: "bottom-right",
            transition: "bounce",
            type: "success"
          });
          router.push({ name: "Adopt" });
        })
        .catch(function(err) {
          createToast("공고 삭제에 실패했어요 💬💦", {
            hideProgressBar: "true",
            timeout: 4000,
            showIcon: "true",
            toastBackgroundColor: "#D7AFA4",
            position: "bottom-right",
            transition: "bounce",
            type: "fail"
          });
          console.log(err);
        });
    };

    const kakaoShare = function() {
      Kakao.Link.sendDefault({
        objectType: "feed",
        content: {
          title: state.board.title,
          description: state.board.description,
          imageUrl: "@/assets/images/mbti_isfp.png",
          link: {
            mobileWebUrl: "http://i5a501.p.ssafy.io/",
            androidExecutionParams: "test"
          }
        },
        buttons: [
          {
            title: "독립으로 이동",
            link: {
              mobileWebUrl: "http://i5a501.p.ssafy.io/"
            }
          }
        ]
      });
    };

    const clickBookmark = function() {
      const isBookmarked = store.getters["root/getIsbookmarked"];

      if (state.userId === null) {
        alert("로그인을 진행해주세요!");
        router.push({ name: "Login" });
      } else {
        console.log("북마크 등록 ", isBookmarked);
        if (isBookmarked) {
          $axios
            .delete(
              "/board/bookmark/" +
                store.getters["root/getLoginUserInfo"].userId +
                "/" +
                state.board.boardId
            )
            .then(function(result) {
              console.log("deleteBookmark!!!!!!");
              store.commit("root/setIsbookmarked", false);
              createToast("북마크가 해제되었어요 💨💨", {
                hideProgressBar: "true",
                timeout: 4000,
                showIcon: "true",
                toastBackgroundColor: "#D7AFA4",
                position: "bottom-right",
                transition: "bounce",
                type: "success"
              });
            })
            .catch(function(err) {
              createToast("북마크 해제에 실패했어요 💬💦", {
                hideProgressBar: "true",
                timeout: 4000,
                showIcon: "true",
                toastBackgroundColor: "#D7AFA4",
                position: "bottom-right",
                transition: "bounce",
                type: "fail"
              });
              console.log(err);
            });
        } else {
          $axios
            .post("/board/bookmark", {
              userId: store.getters["root/getLoginUserInfo"].userId,
              boardId: state.board.boardId
            })
            .then(function(result) {
              console.log("insertBookmark!!!!!!");
              store.commit("root/setIsbookmarked", true);
              createToast("북마크가 등록되었어요 🐾💌", {
                hideProgressBar: "true",
                timeout: 4000,
                showIcon: "true",
                toastBackgroundColor: "#D7AFA4",
                position: "bottom-right",
                transition: "bounce",
                type: "success"
              });
            })
            .catch(function(err) {
              createToast("북마크 등록에 실패했어요 💬💦", {
                hideProgressBar: "true",
                timeout: 4000,
                showIcon: "true",
                toastBackgroundColor: "#D7AFA4",
                position: "bottom-right",
                transition: "bounce",
                type: "fail"
              });
              console.log(err);
            });
        }
      }
    };

    onMounted(() => {
      console.log("breadcrumb");
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "실종/보호",
        subTitle: "실종/보호 동물 정보"
      });
    });

    return { state, clickBookmark, kakaoShare, doDelete, goModify };
  }
};
</script>
