<template>
  <div class="main-body main-padding">
    <div style="width:100%; ">
      <bread-crumb></bread-crumb>
      <el-card class="box-card " shadow="none" style="border:none;">
        <div style="margin-top:50px; margin-left:60px;"></div>

        <el-row class="vertical-center" :gutter="20">
          <el-col :span="12" style="margin-left:50px;">
            <img
              class="dog-thumbnail fit-image"
              :src="state.board.fileList[0]"
            />
          </el-col>
          <el-col :span="12">
            <div class="dog-info-box" style="margin-right:50px;">
              <el-row>
                <el-tag
                  v-if="state.board.boardType.id == 1"
                  class="mb-3  scale-up-2"
                  color="#D7AFA4"
                  effect="dark"
                  size="large"
                  style="border:none; border-radius: 30px; font-size:14pt; float:left;"
                  >{{ state.board.boardType.name }}</el-tag
                >
                <el-tag
                  v-if="state.board.boardType.id != 1"
                  class="mb-3  scale-up-2"
                  color="#E9CDA4"
                  effect="dark"
                  size="large"
                  style="border:none; border-radius: 30px; font-size:14pt; float:left;"
                  >{{ state.board.boardType.name }}</el-tag
                >
              </el-row>
              <div class="vertical-center row">
                <div class="col-md-9">
                  <span
                    :style="{ 'font-size': '30pt', 'font-weight': '700' }"
                    >{{ state.board.dogName }}</span
                  >
                </div>
                <div class="col-md-3 ms-auto">
                  <div
                    class="align-self-center vertical-center"
                    style="text-align: center;"
                  >
                    <font-awesome-icon
                      v-if="!state.board.isOwner"
                      class="scale-up-2"
                      :icon="[state.board.isbookmarked ? 'fas' : 'far', 'star']"
                      @click="clickBookmark()"
                      aria-hidden="true"
                      style="color: rgb(255, 226, 95); font-size: 40px; cursor: pointer;"
                    >
                    </font-awesome-icon>

                    <img
                      @click="kakaoShare"
                      class="scale-up-2"
                      style="margin-left:15px; cursor: pointer;"
                      src="//developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_small.png"
                      width="40"
                    />
                  </div>
                </div>
              </div>

              <el-divider class="mt-4 mb-4" />

              <el-descriptions class="margin-top mt-3" :column="1" :size="size">
                <el-descriptions-item label="성별/중성화여부"
                  >{{ state.board.gender.name }} /
                  {{ state.board.neutralization ? "중성화 O" : "중성화 X" }}
                </el-descriptions-item>
                <el-descriptions-item label="연령">{{
                  state.board.ageType.name
                }}</el-descriptions-item>
                <el-descriptions-item label="크기">{{
                  state.board.weight.name
                }}</el-descriptions-item>
                <el-descriptions-item label="컬러">{{
                  state.board.colorType.name
                }}</el-descriptions-item>
                <el-descriptions-item label="품종">{{
                  state.board.dogType.name
                }}</el-descriptions-item>
                <el-descriptions-item label="현재위치">
                  {{ state.board.sido.name }} {{ state.board.gugun.name }}
                </el-descriptions-item>
                <el-descriptions-item label="MBTI">
                  <el-tag
                    class="mb-2"
                    effect="dark"
                    style="height:30px; background:linear-gradient( to right, #D7AFA4, #E9CDA4, #B4D9A7, #87CEDC ); border:none;font-weight:700; color: #606266; "
                    >{{ state.board.mbti }}</el-tag
                  >

                  <el-popover placement="bottom" width="200" trigger="hover">
                    <div class="content">
                      <h4 style="font-weight:700;">강아지 MBTI 해석하기</h4>
                      <hr />

                      <table class="table table-borderless">
                        <thead>
                          <tr style="text-align:center;">
                            <th scope="col">
                              <h6 style="font-weight:700; color:#606266;">
                                활동성향
                              </h6>
                            </th>
                            <th scope="col">
                              <h6 style="font-weight:700; color:#606266;">
                                순종성향
                              </h6>
                            </th>

                            <th scope="col">
                              <h6 style="font-weight:700; color:#606266;">
                                관계성향
                              </h6>
                            </th>
                            <th scope="col">
                              <h6 style="font-weight:700; color:#606266;">
                                적응성향
                              </h6>
                            </th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td>
                              <el-col>
                                <el-tag
                                  class="m-3"
                                  color="#D7AFA4"
                                  effect="dark"
                                  size="large"
                                  :style="{ border: '3px solid #D7AFA4' }"
                                  >E</el-tag
                                >에너지 <br />
                                Energetic</el-col
                              >
                            </td>
                            <td>
                              <el-col>
                                <el-tag
                                  class="m-3"
                                  color="#E9CDA4"
                                  effect="dark"
                                  size="large"
                                  :style="{ border: '3px solid #E9CDA4' }"
                                  >S</el-tag
                                >충성심강한 <br />
                                Supportive</el-col
                              >
                            </td>

                            <td>
                              <el-col>
                                <el-tag
                                  class="m-3"
                                  color="#B4D9A7"
                                  effect="dark"
                                  size="large"
                                  :style="{ border: '3px solid #B4D9A7' }"
                                  >F</el-tag
                                >관계지향 <br />
                                Friendly</el-col
                              >
                            </td>
                            <td>
                              <el-col>
                                <el-tag
                                  class="m-3"
                                  color="#87CEDC"
                                  effect="dark"
                                  size="large"
                                  :style="{ border: '3px solid #87CEDC' }"
                                  >P</el-tag
                                >신중한 <br />Prudent</el-col
                              >
                            </td>
                          </tr>
                          <tr>
                            <td>
                              <el-col>
                                <el-tag
                                  class="m-3"
                                  color="#FFFFFF"
                                  style="border: 3px solid #D7AFA4; color: #606266;"
                                  effect="dark"
                                  size="large"
                                  >I</el-tag
                                >침착한 <br />Introversion</el-col
                              >
                            </td>
                            <td>
                              <el-col>
                                <el-tag
                                  class="m-3"
                                  color="#FFFFFF"
                                  style="border: 3px solid #E9CDA4; color: #606266;"
                                  effect="dark"
                                  size="large"
                                  >N</el-tag
                                >영리한 <br />
                                Naughty</el-col
                              >
                            </td>

                            <td>
                              <el-col>
                                <el-tag
                                  class="m-3"
                                  color="#FFFFFF"
                                  style="border: 3px solid #B4D9A7; color: #606266;"
                                  effect="dark"
                                  size="large"
                                  >T</el-tag
                                >독립지향 <br />
                                independenT</el-col
                              >
                            </td>
                            <td>
                              <el-col>
                                <el-tag
                                  class="m-3"
                                  color="#FFFFFF"
                                  style="border: 3px solid #87CEDC; color: #606266;"
                                  effect="dark"
                                  size="large"
                                  >J</el-tag
                                >친화적인 <br />
                                Jolly</el-col
                              >
                            </td>
                          </tr>
                        </tbody>
                      </table>
                    </div>

                    <template #reference>
                      <i
                        class="el-icon-question scale-up-2"
                        style="margin-left : 10px; cursor: pointer;"
                      />
                    </template>
                  </el-popover>
                </el-descriptions-item>
              </el-descriptions>

              <el-divider class="mb-3" />

              <div>
                <div v-if="!state.board.isOwner">
                  <el-button
                    style="width:100%; background : #755744;"
                    @click="goChat(state.board.boardId)"
                    >상담 신청</el-button
                  >
                </div>
                <div v-if="state.board.isOwner">
                  <el-row>
                    <el-col :span="12"
                      ><el-button
                        class="m-2"
                        style="width:100%;   background : #755744;"
                        @click="goModify(state.board.boardId)"
                        >공고 수정</el-button
                      ></el-col
                    >
                    <el-col :span="12">
                      <el-popconfirm
                        confirmButtonText="삭제"
                        cancelButtonText="취소"
                        icon="el-icon-warning"
                        iconColor="iconColor"
                        title="게시물을 정말 삭제하시겠습니까? "
                        @confirm="doDelete(state.board.boardId)"
                      >
                        <template #reference>
                          <el-button
                            class="m-2"
                            style="width:100%;   background : #C4C4C4;"
                            >공고 삭제</el-button
                          >
                        </template>
                      </el-popconfirm>
                    </el-col>
                  </el-row>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
        <div class="box mt-5">
          <h4
            class="mb-2
        "
          >
            <b>{{ state.board.title }}</b>
          </h4>
          <div style="margin-top:20px; white-space:pre-wrap;">
            {{ state.board.description }}
          </div>
        </div>

        <div class="dog-image-box mt-5 ">
          <el-carousel :interval="4000" type="card" height="500px">
            <el-carousel-item v-for="img in state.board.fileList" :key="img">
              <img
                class="fit-image"
                style="width: 100%; height: 500px;"
                :src="img"
              />
            </el-carousel-item>
          </el-carousel>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import $axios from "axios";
import BreadCrumb from "./components/bread-crumb.vue";
import { computed, reactive, onMounted } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "AdoptDetail",
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
      Kakao.init("d0106aa9ba1feb9c379bbb82194695fe");
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
      if (
        state.userId === null ||
        state.userId == "" ||
        state.userId === undefined
      ) {
        createToast("로그인해야 이용 가능하개🐕‍🦺💨", {
          hideProgressBar: "true",
          timeout: 4500,
          showIcon: "true",
          toastBackgroundColor: "#c49d83",
          position: "bottom-left",
          transition: "bounce",
          type: "warning"
        });
        router.push({ name: "Login" });
      } else {
        console.log(id, "go modify");
        router.push({ name: "AdoptModify" });
      }
    };

    const doDelete = function(id) {
      if (
        state.userId === null ||
        state.userId == "" ||
        state.userId === undefined
      ) {
        createToast("로그인해야 이용 가능하개🐕‍🦺💨", {
          hideProgressBar: "true",
          timeout: 4500,
          showIcon: "true",
          toastBackgroundColor: "#c49d83",
          position: "bottom-left",
          transition: "bounce",
          type: "warning"
        });
        router.push({ name: "Login" });
      } else {
        store
          .dispatch("root/requestDeleteBoard", id)
          .then(function(result) {
            createToast("공고가 삭제되었어요 💨💨", {
              hideProgressBar: "true",
              timeout: 4500,
              showIcon: "true",
              toastBackgroundColor: "#7eaa72",
              position: "bottom-left",
              transition: "bounce",
              type: "success"
            });
            router.push({ name: "Adopt" });
          })
          .catch(function(err) {
            createToast("공고 삭제에 실패했어요 😱💦", {
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
      }
    };

    const kakaoShare = function() {
      Kakao.Link.sendDefault({
        objectType: "feed",
        content: {
          title: state.board.title,
          description: state.board.description,
          imageUrl: state.board.fileList[0],
          link: {
            mobileWebUrl: "https://i5a501.p.ssafy.io/",
            webUrl: "https://i5a501.p.ssafy.io/"
          }
        },
        buttons: [
          {
            title: "독립으로 이동",
            link: {
              mobileWebUrl: "https://i5a501.p.ssafy.io/",
              webUrl: "https://i5a501.p.ssafy.io/"
            }
          }
        ]
      });
    };

    const clickBookmark = function() {
      const isBookmarked = store.getters["root/getIsbookmarked"];

      if (
        state.userId === null ||
        state.userId == "" ||
        state.userId === undefined
      ) {
        createToast("로그인해야 이용 가능하개🐕‍🦺💨", {
          hideProgressBar: "true",
          timeout: 4500,
          showIcon: "true",
          toastBackgroundColor: "#c49d83",
          position: "bottom-left",
          transition: "bounce",
          type: "warning"
        });
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
                timeout: 4500,
                showIcon: "true",
                toastBackgroundColor: "#7eaa72",
                position: "bottom-left",
                transition: "bounce",
                type: "success"
              });
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
                timeout: 4500,
                showIcon: "true",
                toastBackgroundColor: "#7eaa72",
                position: "bottom-left",
                transition: "bounce",
                type: "success"
              });
            })
            .catch(function(err) {
              createToast("북마크 등록에 실패했어요 😱💦", {
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
        }
      }
    };

    const goChat = function(id) {
      if (
        state.userId === null ||
        state.userId == "" ||
        state.userId === undefined
      ) {
        createToast("로그인해야 이용 가능하개🐕‍🦺💨", {
          hideProgressBar: "true",
          timeout: 4500,
          showIcon: "true",
          toastBackgroundColor: "#c49d83",
          position: "bottom-left",
          transition: "bounce",
          type: "warning"
        });
        router.push({ name: "Login" });
      } else {
        store
          .dispatch("root/existedForm", {
            userId: state.userId,
            boardId: state.board.boardId
          })
          .then(function(result) {
            if (result.status == 204) {
              // 입양 신청서가 존재하지 않음
              router.push({ name: "AdoptFormConfirm" });
            } else {
              createToast("이미 신청서를 작성했던 공고입니다 💬💦", {
                hideProgressBar: "true",
                timeout: 4500,
                showIcon: "true",
                toastBackgroundColor: "#c49d83",
                position: "bottom-left",
                transition: "bounce",
                type: "warning"
              });
            }
          })
          .catch(function(err) {
            createToast("상담 신청에 실패했어요. 💬💦", {
              hideProgressBar: "true",
              timeout: 4500,
              showIcon: "true",
              toastBackgroundColor: "#c49d83",
              position: "bottom-left",
              transition: "bounce",
              type: "warning"
            });
          });
      }
    };

    const readDetail = function(id) {
      console.log("read");

      var checkId = state.userId;
      if (checkId === undefined || checkId === null || checkId == "") {
        checkId = "none";
      }

      store
        .dispatch("root/requestBoardDetail", {
          boardId: id,
          userId: checkId
        })
        .then(function(result) {
          console.log(result);
          console.log(result.data.listSimilarDog);
          const boardDetail = {
            boardId: result.data.dogInformation.boardId.id,
            boardType: result.data.dogInformation.boardId.type,
            thumbnailUrl: result.data.dogInformation.boardId.thumbnailUrl,
            title: result.data.dogInformation.boardId.title,
            address: result.data.dogInformation.address,
            mbti: result.data.dogInformation.mbti,
            colorType: result.data.dogInformation.colorType,
            gender: result.data.dogInformation.gender,
            dogType: result.data.dogInformation.dogType,
            neutralization: result.data.dogInformation.neutralization,
            writer: result.data.writer,
            weight: result.data.dogInformation.weight,
            ageType: result.data.dogInformation.age,
            regDate: result.data.dogInformation.boardId.regDate,
            fileList: result.data.boardImageList,
            isOwner: result.data.owner,
            gugun: result.data.dogInformation.gugun,
            sido: result.data.dogInformation.gugun.sidoCode,
            description: result.data.dogInformation.description,
            dogName: result.data.dogInformation.dogName,
            isBookmarked: result.data.bookmarked,
            listSimilarDog: result.data.listSimilarDog
          };

          store.commit("root/setBoardDetail", boardDetail);
          window.scrollTo(0, 0);
        })
        .catch(function(err) {
          console.log(err);
        });
    };

    onMounted(() => {
      console.log("breadcrumb");
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "입양/임보",
        subTitle: "입양/임보 동물 정보",
        path: "/adopt"
      });
      window.scrollTo(0, 0);
    });

    return {
      state,
      goChat,
      clickBookmark,
      kakaoShare,
      doDelete,
      goModify,
      readDetail
    };
  }
};
</script>
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
  max-height: 500px;
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

:deep(.el-button--primary) {
  color: #fff;
  background-color: #755744;
  background: #755744;
}

.dog-image-box {
  margin: 30px;
  padding: 10px;
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

.fit-image {
  object-fit: contain;
}
</style>
