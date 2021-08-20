<template>
  <el-row>
    <el-col
      v-for="(o, idx) in applicant"
      :key="idx"
      style="margin-right:1%; margin-left:1%; width: 48%"
    >
      <el-row class="grid-content bg-beige" v-if="o.boardType=='입양'|| o.boardType=='임보'">
        <el-col :span="4">
          <div class="image">
            <el-avatar
              s4hape="circle"
              :size="60"
              :src="o.applicantId.profileImageUrl"
              :style="{ border: 'solid 1px rgb(212, 212, 212)' }"
            />
          </div>
        </el-col>
        <el-col :span="12">
          <div class="apply-content">
            <p>이름 : {{ o.applicantId.name }}</p>
            <p>{{ o.applicantId.email }}</p>
          </div>
        </el-col>
        <!-- 채팅방, 입양신청서 버튼 -->
        <el-col :span="2" v-if="o.result == '승인'">
          <div>
            <font-awesome-icon
              icon="comments"
              aria-hidden="true"
              @click="openChatting(o.id)"
              style="color: rgb(78, 78, 78); font-size: 20px; cursor: pointer; margin-right:15px"
              class="scale-up-3"
            >
            </font-awesome-icon>
          </div>
        </el-col>
        <el-col :span="isPassed(o.result)">
          <div>
            <font-awesome-icon
              icon="file"
              aria-hidden="true"
              @click="readAdoptForm(o.id)"
              style="color: rgb(78, 78, 78); font-size: 20px; cursor: pointer; margin-right:10px"
              class="scale-up-3"
            >
            </font-awesome-icon>
          </div>
        </el-col>
        <!-- 승인, 거절 태그 -->
        <el-col :span="3">
          <div
            class="button"
            style="text-align:center;"
            v-if="o.result === '대기'"
          >
            <!-- 클릭 되는 태그 -->
            <el-tag
              color="#D7AFA4"
              effect="dark"
              size="medium"
              style="border:none; border-radius: 30px; font-size:9pt; display:block; margin-bottom: 5px; cursor: pointer"
              @click="clickAccept(o.id)"
              >승인</el-tag
            >
            <el-tag
              color="#BDBDBD"
              effect="dark"
              size="medium"
              style="border:none; border-radius: 30px; font-size:9pt; display:block; ; cursor: pointer"
              @click="clickReject(o.id)"
              >거절</el-tag
            >
          </div>
          <div v-else>
            <!-- 클릭 되지 않는 태그 -->
            <el-tag
              v-if="o.result === '승인'"
              color="#D7AFA4"
              effect="dark"
              size="medium"
              style="border:none; border-radius: 30px; font-size:9pt; display:block; cursor: default"
              >승인</el-tag
            >
            <el-tag
              v-if="o.result === '거절'"
              color="#BDBDBD"
              effect="dark"
              size="medium"
              style="border:none; border-radius: 30px; font-size:9pt; display:block; cursor: default"
              >거절</el-tag
            >
          </div>
        </el-col>
      </el-row>
    </el-col>
  </el-row>
</template>

<style scoped>
/* .el-row{
    display: inline-block;
  } */
.el-col {
  border-radius: 4px;
}
.bg-beige {
  /* background: linear-gradient(to top, #f3ede7, #f5e9e4); */
  border: solid 1px lightgray;
  height: 90px;
}
.grid-content {
  display: flex;
  align-items: center;
  flex-direction: row;
  border-radius: 4px;
  padding: 10px;
  min-height: 36px;
  margin-bottom: 20px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.image {
  display: flex;
  align-items: center;
  float: left;
  vertical-align: middle;
  margin-right: 20px;
}
.apply-content {
  vertical-align: middle;
  margin-right: 20px;
}
.apply-content p {
  text-align: left;
  margin: 5px 0 5px 0;
}
.button {
  text-align: right;
  vertical-align: middle;
}
</style>

<script>
import { onBeforeMount, onMounted, reactive, computed } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "user-applicant-item",
  props: {
    applicant: {
      type: String
    }
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();
    const userid = store.getters["root/getLoginUserInfo"].userId;
    const state = reactive({
      boardList: [],
      board: computed(() => {
        console.log(store.getters["root/getBoardDetail"]);
        return store.getters["root/getBoardDetail"];
      })
    });

    async function clickAccept(id) {
      router.push({ name: "mypage-applicant-list" });
      store
        .dispatch("root/changeResult", { id: id, status: { result: "승인" } })
        .then(async function(result) {
          var awaitResult = await createChatting(id); // 채팅방 생성
          router.go(router.currentRoute); // 승인 후 강제 새로고침
        })
        .catch(function(err) {
          console.log(err);
        });
    };

    const clickReject = function(id) {
      store
        .dispatch("root/changeResult", { id: id, status: { result: "거절" } })
        .then(async function(result) {
          console.log(result)
          var out = await createToast("신청 결과가 등록되었습니다 💨💨", {
            hideProgressBar: "true",
            timeout: 4500,
            showIcon: "true",
            toastBackgroundColor: "#7eaa72",
            position: "bottom-left",
            transition: "bounce",
            type: "success"
          });

          router.go(router.currentRoute); // 승인 후 강제 새로고침
        })
        .catch(function(err) {
          console.log(err);
        });
    };

    const readAdoptForm = function(id) {
      store.commit("root/setAdoptFormId", id);
      router.push({ name: "AdoptFormReview" });
    };

    // 승인여부에 따른 span 값 지정
    const isPassed = function(result) {
      if (result === "승인")
        // 승인되었을 때는 span: 2
        return 2;
      else return 4; // 승인 안되었을 때는 span: 4
    };

    // 상담채팅방 생성
    const createChatting = function(counselingId) {
      return new Promise(function(resolve, reject){
        store
        .dispatch("root/requestCreateChatRoom", {
          counseling_id: counselingId,
          withCredentials: true // userId를 헤더 쿠키에 담아서 보냄
        })
        .then(function(result) {
          createToast(
            "신청 결과가 등록되었습니다 💨💨 채팅방에서 상담을 시작해보세요!🐾💌",
            {
              hideProgressBar: "true",
              timeout: 4500,
              showIcon: "true",
              toastBackgroundColor: "#7eaa72",
              position: "bottom-left",
              transition: "bounce",
              type: "success"
            }
          );
          resolve(result)
        })
        .catch(function(err) {
          createToast(
            "상담채팅방을 생성하지 못하였습니다. 다시 시도해주세요.💬💦",
            {
              hideProgressBar: "true",
              timeout: 4500,
              showIcon: "true",
              toastBackgroundColor: "#c49d83",
              position: "bottom-left",
              transition: "bounce",
              type: "warning"
            }
          );
          resolve(err)
        });
      });
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
          store.commit('root/setChatMenu', 1); // chat-detail.vue로 이동
          store.commit('root/setChatRoomId', result.data.chatRoomList[0].chatRoom.id);
          store.commit('root/setChatTitle', result.data.chatRoomList[0].counselingHistory.boardTitle)
          store.commit('root/setChatOpen', true); // 모두 설정해준 다음 OPEN
        })
        .catch(function(err){
          console.log(err)
          createToast(
            "이미 종료된 상담입니다! 💬💦",
            {
              hideProgressBar: "true",
              timeout: 4500,
              showIcon: "true",
              toastBackgroundColor: "#c49d83",
              position: "bottom-left",
              transition: "bounce",
              type: "warning"
            }
          );
        })
        .catch(function(err) {
          console.log(err);
        });
    };

    return {
      state,
      clickAccept,
      clickReject,
      readAdoptForm,
      isPassed,
      openChatting
    };
  }
};
</script>

