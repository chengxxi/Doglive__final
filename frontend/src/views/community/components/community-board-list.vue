<template>
  <!-- <div>  v-loading="communities.loading" -->
  <div class="chat-body"
      :ref="el => { if(el) divs = el}"
  >
    <div class="button" style="text-align:right; margin-top:2%;">
      <el-button type="outline-primary" round @click="goRegister"
        >글 작성하기</el-button
      >
      <el-button type="outline-primary" round @click="goMyCommunity"
        >내 피드 보러가기</el-button
      >
    </div>
    <el-row class="board" v-for="(item, index) in state.boardList" :key="index">
      <div>
        <div class="title">
          <div class="user">
            <img class="user-profile" :src="item.profileImageUrl" />
            <span>{{ item.name }}</span>
          </div>
          <!-- 자신의 글만 수정, 삭제 가능 -->
          <!-- <div class="button-group" v-if="item.userId==state.userId"> -->
          <span class="button-group" v-if="item.userId == state.userId">
            <el-button-group>
              <el-button
                type="info"
                plain
                icon="el-icon-edit"
                size="mini"
                @click="updateCommunity(item.id)"
              ></el-button>
              <el-button
                type="info"
                plain
                icon="el-icon-delete"
                size="mini"
                @click="deleteCommunity(item.id)"
              ></el-button>
            </el-button-group>
          </span>
        </div>
        <div class="body">
          <el-carousel
            class="image-carousel"
            indicator-position="none"
          >
            <el-carousel-item
              v-for="(img, index) in item.fileList"
              :key="index"
            >
              <img class="image" :src="img" />
            </el-carousel-item>
          </el-carousel>
          <div class="tag">
            <el-tag
              v-if="item.category == '입양일기'"
              color="#D7AFA4"
              effect="dark"
              style="border:none; border-radius: 30px; font-size:14px;"
              >{{ item.category }}</el-tag
            >
            <el-tag
              v-if="item.category == '임보일기'"
              color="#E9CDA4"
              effect="dark"
              style="border:none; border-radius: 30px; font-size:14px;"
              >{{ item.category }}</el-tag
            >
            <el-tag
              v-if="item.category == '자유게시판'"
              color="#87CEDC"
              effect="dark"
              style="border:none; border-radius: 30px; font-size:14px;"
              >{{ item.category }}</el-tag
            >
            <el-tag
              v-if="item.category == '나눔'"
              color="#B4D9A7"
              effect="dark"
              style="border:none; border-radius: 30px; font-size:14px;"
              >{{ item.category }}</el-tag
            >
          </div>
          <div class="content">
            <span style="font-weight: 600; font-size: 18px;">
              <!-- <font-awesome-icon
              icon="heart"
              aria-hidden="true"
              style="color: rgb(237, 0, 109); font-size: 3vmin; cursor: pointer; margin-top: 10px;"
              class="scale-up-5"
            >
          </font-awesome-icon> -->
              {{ item.title }}</span
            >
            <div
              class="boardcontent"
              style="margin-top:2%; white-space:pre-wrap;"
            >
              {{ item.description }}
            </div>
          </div>
        </div>
        <div class="comment">
          <img
            class="icon"
            :src="require('@/assets/images/icon.png')"
            style="margin-left:2%;"
          />
          <el-input
            placeholder="댓글을 입력해주세요"
            v-model="comment.input"
            class="comment-input"
            @keyup.enter="RegisterComment(item.id)"
          >
          </el-input>
          <el-button
            class="comment-button"
            icon="el-icon-s-promotion"
            @click="RegisterComment(item.id)"
          ></el-button>
          <div
            v-for="(i, index) in state.reverseList"
            :key="index"
            style="margin:2%;"
          >
            <div v-if="i.communityId.id == item.id && i.isDelete == true">
              <span
                style="margin-right:2%; font-weight:bold; font-size: 16px;"
                >{{ i.name }}</span
              >
              <span style="margin-right:2%; font-size: 16px;">{{
                i.comment
              }}</span>
              <span
                ><el-button
                  v-if="state.userId == i.userId"
                  class="close-button"
                  icon="el-icon-close"
                  style="position: absolute; right: 0; padding:1%; margin-right:3%;"
                  @click="DeleteComment(i.id)"
                ></el-button
              ></span>
            </div>
          </div>
        </div>
      </div>
    </el-row>
    <div style="text-align: center; font-size:larger !important">
      <p v-if="communities.loading"><i class="el-icon-loading"></i></p>
      <p v-if="communities.noMore"><i class="el-icon-circle-check"></i></p>
    </div>
  </div>
</template>

<style scoped>
.board {
  max-width: 600px;
  max-width: 850px;
  min-width: 300px;
  min-height: 550px;
  width: 50%;
  height: 100%;
  border: 1px solid lightgray;
  border-radius: 5px;
  margin-right: 25%;
  margin-left: 25%;
  margin-bottom: 4%;
  margin-top: 4%;
}
.title {
  display: table;
  width: 100%;
  min-height: 60px;
  font-size: 16px;
  font-weight: 500;
  border-bottom: solid 1px rgb(240, 240, 240);
  padding: 10px;
}
.button .el-button {
  border: solid 1px lightgray !important;
  margin-right: 1%;
}
.button .el-button:hover {
  color: black !important;
  background-color: #f9f0e7 !important;
  border: solid 1px #f9f0e7 !important;
}
.user {
  display: table-cell;
  text-align: left;
  vertical-align: middle;
}
.user-profile {
  width: 30px;
  height: 30px;
  border-radius: 100%;
  margin-left: 8px;
  margin-right: 10px;
}
.button-group {
  position: absolute;
  right: 0;
  margin-top: 1%;
  margin-right: 3%;
}
.image-carousel {
  position: relative;
  width: inherit; /*정방향 크기 */
  margin-bottom: 10px;
}
.image {
  position: absolute;
  width: 100%;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  object-fit: cover;
}

.title > span {
  display: inline-block;
  margin-bottom: 2%;
}
.boardcontent {
  font-size: 14px;
}
.content {
  min-height: 50px;
  margin: 2% 5% 5% 5%;
  vertical-align: middle;
  text-align: left;
}
.tag {
  margin-left: 4.5%;
}
.icon {
  width: 30px;
  height: 30px;
  color: #755744;
}
.comment {
  padding: 10px 10px 0 10px;
  border-top: solid 1px rgb(240, 240, 240);
}
.comment-input {
  width: 80%;
}
.comment-button {
  position: absolute;
  right: 10px;
  color: #755744;
}
:deep(.el-tag) {
  height: 30px;
  text-align: center;
  vertical-align: middle;
}
:deep(.el-input__inner) {
  border: none;
}
:deep(.el-button) {
  border: none;
  background-color: white;
}
:deep(.el-button:hover) {
  color: black;
  background-color: white;
}
:deep(.el-carousel__container) {
  position: relative;
  width: 100%;
  padding-top: 100%; /* 정방향 */
}
</style>

<script>
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { ref, onBeforeMount, onMounted, reactive, computed, onUnmounted } from "vue";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "community-board-list",
  components: {
    createToast
  },

  setup() {
    const store = new useStore();
    const router = new useRouter();
    const divs = ref(null); // 요기
    const state = reactive({
      boardList: [],
      reverseList: [],
      comments: [],
      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"].userId;
      }),
      userProfile: computed(() => {
        return store.getters["root/getUpdateUserInfo"];
      }),
      isLoading: computed(() => state.comments)
    });

    const communities = reactive({
      init: true,
      page: 0,
      loading: true,
      isLoading: computed(() => communities.loading),
      noMore: false,
      isNoMore: computed(() => communities.noMore),
    });

    // 댓글 입력 값을 받아올 객체
    const comment = reactive({
      input: ""
    });

    const goRegister = function() {
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
          type: "success"
        });
        router.push({ name: "Login" });
      } else {
        router.push({ name: "community-board-register" });
      }
    };

    const goMyCommunity = function() {
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
          type: "success"
        });
        router.push({ name: "Login" });
      } else {
        router.push({ name: "community-mycommunity" });
      }
    };

    // 다음 커뮤니티 목록 가져오기
    function fetchCommunityList() {
      return new Promise(function(resolve, reject){
      store
        .dispatch("root/requestCommunityBoardList", communities.page)
        .then(async function(result) {
          var size = result.data.length;
          for (var i = 0; i < size; i++) {
            state.boardList.push(result.data[i]);
            const id = result.data[i].id;
            await store
              .dispatch("root/requestCommunityComment", id)
              .then(async function(result) {
                var size = result.data.commentList.length;
                for (var i = 0; i < size; i++) {
                  state.comments.push(result.data.commentList[i]);
                }
                state.reverseList = [...state.comments].reverse();
              })
              .catch(function(err) {
                console.log(err);
              });
          }
          communities.loading = false // 로딩 중지
          communities.init = false

          // 다 받아왔으면 = 더 이상 남아있지 않으면
          if(size < 10)
            communities.noMore = true

          resolve(result)
        })
        .catch(function(err) {
          console.log(err);
          resolve(err)
        });
       });
    }

    function handleScroll(event) {
      // 요기
      if (
         !communities.noMore && !communities.init &&
          window.scrollY >= divs.value.scrollHeight - 900
      ) {
        communities.loading = true
        communities.page += 1;
        fetchCommunityList();
      }
    }

    // store.dispatch('root/requestCommunityBoardList')
    //   .then(function(result){
    //     console.log(result)
    //     state.boardList = result.data;
    //   }).catch(function(err){
    //     console.log(err)
    // });

    const updateCommunity = function(id) {
      store
        .dispatch("root/requestCommunityDetail", id)
        .then(function(result) {
          const CommunityDetail = {
            communityId: result.data.community.id,
            title: result.data.community.title,
            category: result.data.community.category,
            description: result.data.community.description,
            fileList: result.data.filePath
          };
          store.commit("root/setCommunityBoard", CommunityDetail);
          router.push({ name: "community-board-update" });
        })
        .catch(function(err) {
          console.log(err);
        });
    };

    const deleteCommunity = function(id) {
      store
        .dispatch("root/requestDeleteCommunity", id)
        .then(function(result) {
          createToast("게시글이 삭제되었어요 💨💨", {
            hideProgressBar: "true",
            timeout: 4500,
            showIcon: "true",
            toastBackgroundColor: "#7eaa72",
            position: "bottom-left",
            transition: "bounce",
            type: "success"
          });
          router.go(router.currentRoute);
        })
        .catch(function(err) {
          createToast("게시글 삭제에 실패했어요 😱💦", {
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

    const RegisterComment = function(id) {
      if (state.userId === null ||
            state.userId == "" ||
            state.userId === undefined) {
          createToast("로그인해야 이용 가능하개🐕‍🦺💨", {
            hideProgressBar: "true",
            timeout: 4500,
            showIcon: "true",
            toastBackgroundColor: "#c49d83",
            position: "bottom-left",
            transition: "bounce",
            type: "success"
          });
          router.push({ name: "Login" });
        }else {
          if (
            comment.input == null ||comment.input == "" ) {
            createToast("댓글 내용을 적어주세요 😱💦", {
              hideProgressBar: "true",
              timeout: 4500,
              showIcon: "true",
              toastBackgroundColor: "#c49d83",
              position: "bottom-left",
              transition: "bounce",
              type: "success"
            });
          }else{
          store
            .dispatch("root/requestRegisterComment", {
              communityId: id,
              userId: state.userId,
              comment: comment.input
            })
            .then(function(result) {
              createToast("댓글이 등록되었어요 💨💨", {
                hideProgressBar: "true",
                timeout: 4500,
                showIcon: "true",
                toastBackgroundColor: "#7eaa72",
                position: "bottom-left",
                transition: "bounce",
                type: "success"
              });
              state.comments.push(result.data);
              comment.input = "";
              state.reverseList = [...state.comments].reverse();
            })
            .catch(function(err) {
              createToast("댓글 등록에 실패했어요 😱💦", {
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
      }
    };

    const DeleteComment = function(id) {
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
          .dispatch("root/requestDeleteComment", id)
          .then(function(result) {
            createToast("댓글이 삭제되었어요 💨💨", {
              hideProgressBar: "true",
              timeout: 4500,
              showIcon: "true",
              toastBackgroundColor: "#7eaa72",
              position: "bottom-left",
              transition: "bounce",
              type: "success"
            });

            for (var i = 0; i < state.comments.length; i++) {
              if (state.comments[i].id == id) {
                state.comments.splice(i, 1);
              }
            }
            state.reverseList = [...state.comments].reverse();
          })
          .catch(function(err) {
            createToast("댓글 삭제에 실패했어요 😱💦", {
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

    onMounted(() => {
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "독립신문",
        subTitle: "게시글 구경하기"
      });
      fetchCommunityList();
      window.addEventListener("scroll", handleScroll)
    });

    onUnmounted(() => {
      window.removeEventListener("scroll", handleScroll)
    })

    return {
      state,
      deleteCommunity,
      updateCommunity,
      communities,
      fetchCommunityList,
      comment,
      RegisterComment,
      DeleteComment,
      goRegister,
      goMyCommunity,
      divs
    };
  }
};
</script>
