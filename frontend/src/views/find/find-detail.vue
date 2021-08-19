<template>
  <div class="main-body main-padding">
    <bread-crumb></bread-crumb>
    <el-card class="box-card " shadow="none" style="border:none;">
      <div style="margin-top:50px; margin-left:60px;"></div>
      <el-row class="vertical-center" :gutter="20" style="margin-top:10px;">
        <el-col :span="12" style="margin-left:50px;">
          <img class="dog-thumbnail fit-image" :src="state.board.fileList[0]" />
        </el-col>
        <el-col :span="12">
          <div class="dog-info-box" style="margin-right:50px;">
            <el-tag
              v-if="state.board.boardType.id == 3"
              class="mb-3"
              color="#B4D9A7"
              effect="dark"
              size="large"
              style="border:none; border-radius: 30px; font-size:14pt;"
              >{{ state.board.boardType.name }}
            </el-tag>
            <el-tag
              v-if="state.board.boardType.id != 3"
              class="mb-3"
              color="#87CEDC"
              effect="dark"
              size="large"
              style="border:none; border-radius: 30px; font-size:14pt;"
              >{{ state.board.boardType.name }}
            </el-tag>
            <div class="vertical-center row">
              <div class="col-md-9">
                <span :style="{ 'font-size': '30pt', 'font-weight': '700' }">
                  {{ state.board.dogType.name }}
                </span>
              </div>
              <div class="col-md-3 ms-auto">
                <div
                  class="align-self-center vertical-center"
                  style="text-align: center; height:80px"
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
              <el-descriptions-item label="성별"
                >{{ state.board.gender.name }}
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
              <el-descriptions-item label="실종/보호주소"
                >{{ state.board.sido.name }} {{ state.board.gugun.name }}
              </el-descriptions-item>
              <el-descriptions-item label="상세주소">{{
                state.board.address
              }}</el-descriptions-item>
            </el-descriptions>

            <el-divider class="mb-3" />

            <div>
              <div v-if="!state.board.isOwner">
                <el-button
                  style="width:100%; background : #755744;"
                  @click="goChat(state.board.boardId)"
                  >채팅 보내기</el-button
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
      <div class="box mt-5">
        <h4 class="mb-2">
          <b>{{ state.board.title }}</b>
        </h4>
        <div style="margin-top:20px; white-space:pre-wrap;">
          {{ state.board.description }}
        </div>
      </div>

      <div class="dog-image-box mt-5 mb-5">
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

      <!-- 조건 걸기 -->
      <div class="dog-image-box">
        <h5
          class="pt-3 pb-3"
          style="font-weight:600; padding-left:20px; background:linear-gradient( to bottom,#f3e8dc, #f5edea );"
        >
          🐶🔎 혹시 저는 아닐까요❔
        </h5>
        <!-- 유사공고 -->
        <div v-if="state.listSimilarDog != null">
          <el-scrollbar>
            <div class="flex-content">
              <p
                class="item"
                v-for="(card, idx) in state.listSimilarDog"
                :key="idx"
                style="width:360px; margin: 5px; display:inline-block"
              >
                <FindCard
                  :card="card"
                  @click="readDetail(card.boardId.id)"
                  style="margin:10px; width:360px;"
                />
              </p>
            </div>
          </el-scrollbar>
        </div>
        <div v-else>
          <el-empty
            description="유사한 강아지가 없네요😢"
            image="https://d2ud6j7vlf3xy9.cloudfront.net/img/KakaoTalk_20210816_223416590.png"
            image-size="300"
          >
          </el-empty>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import $axios from "axios";
import BreadCrumb from "@/views/adopt/components/bread-crumb.vue";
import { computed, reactive, onMounted } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";
import FindCard from "@/views/find/components/find-card.vue";

export default {
  name: "FindDetail",
  components: {
    FindCard,
    BreadCrumb
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
        return store.getters["root/getBoardDetail"];
      }),
      listSimilarDog: computed(() => {
        return store.getters["root/getBoardDetail"].listSimilarDog;
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
        router.push({ name: "FindModify" });
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
            router.push({ name: "Find" });
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
        if (isBookmarked) {
          $axios
            .delete(
              "/board/bookmark/" +
                store.getters["root/getLoginUserInfo"].userId +
                "/" +
                state.board.boardId
            )
            .then(function(result) {
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

    //입양신청서 제출
    const submitAdoptForm = function() {
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

        const data = reactive({
          userId: state.userId,
          data: {
            boardId: state.board.boardId,
            boardType: state.board.boardType.name,
            boardTitle: state.board.title,
            dogName: null,
            content: null
          }
        });

        store
          .dispatch("root/registerAdoptForm", data)
          .then(function(result) {
            createChatting(result.data.counselingHistory.id); // 성공하면 상담채팅방 생성
          })
          .catch(function(error) {
            console.log(error);
          });
      }
    };

    // 상담채팅방 생성
    const createChatting = function(counselingId) {
      store
        .dispatch("root/requestCreateChatRoom", {
          counseling_id: counselingId,
          withCredentials: true // userId를 헤더 쿠키에 담아서 보냄
        })
        .then(function(result) {
          openChatting(counselingId);
        })
        .catch(function() {});
    };

    // 상담채팅방 열기
    const openChatting = function(counselingId) {
      store
        .dispatch("root/requestChatRoomByCounseling", {
          counselingId: counselingId,
          withCredentials: true
        })
        .then(function(result) {
          // 채팅방에 입장할 때, chatRoom 정보를 넘겨줌
          store.commit("root/setChatMenu", 1); // chat-detail.vue로 이동
          store.commit(
            "root/setChatRoomId",
            result.data.chatRoomList[0].chatRoom.id
          );
          store.commit(
            "root/setChatTitle",
            result.data.chatRoomList[0].counselingHistory.boardTitle
          );
          store.commit("root/setChatOpen", true); // 모두 설정해준 다음 OPEN
        })
        .catch(function(err) {
          console.log(err);
        });
    };

    // 채팅 시작하기 클릭 시, 수행되는 메소드
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
        // 채팅방이 아니라 '신청로그'가 이미 존재하는지 체크
        store
          .dispatch("root/existedForm", {
            userId: state.userId,
            boardId: state.board.boardId
          })
          .then(function(result) {
            if (result.status == 204) {
              submitAdoptForm(); // counseling history가 존재하지 않음 -> 1. counseling history 생성   2. 채팅방 생성  3. 채팅방 오픈
            } else {
              var counselingId = result.data.counselingHistory.id;
              store.dispatch("root/requestChatRoomByCounseling", {counselingId: counselingId})
              .then(function(result){
                if(result.data.chatRoomList.length == 0) // 채팅방이 삭제되어 존재하지 않음 -> 다시 생성, 오픈
                  createChatting(counselingId);
                else
                  openChatting(counselingId); // 존재하면 채팅방만 오픈
              })
              .catch(function(err){
              })
            }
          })
          .catch(function(err) {
            createToast("채팅을 시작할 수 없어요.💬💦", {
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
          store.commit(
            "root/setBoardId",
            result.data.dogInformation.boardId.id
          );
          window.scrollTo(0, 0);
        })
        .catch(function(err) {
          console.log(err);
        });
    };

    onMounted(() => {
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "실종/보호",
        path: "/find",
        subTitle: "실종/보호 동물 정보"
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
      readDetail,
      onMounted
    };
  }
};
</script>

<style scoped>
.main-body {
  width: 80%;
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

::-webkit-scrollbar {
  width: 10px;
}

::-webkit-scrollbar-thumb {
  background: linear-gradient(to bottom, #f3e8dc, #f5edea);
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
}

::-webkit-scrollbar-track {
  background-color: rgb(192, 186, 178);
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}

.flex-content {
  white-space: nowrap;
  overflow-x: auto;
  margin: 0;
  max-width: 99%;
}

.fit-image {
  object-fit: contain;
}
</style>
