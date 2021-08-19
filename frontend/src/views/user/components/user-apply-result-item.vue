<template>
  <el-row>
    <el-col v-for="(o, idx) in result" :key="idx" style="margin-right:1%; margin-left:1%; width: 48%">
      <el-row class=" grid-content bg-beige">
        <el-col :span="5">
          <div class="tag">
            <el-tag
              v-if="o.result == '승인'"
              color="#B4D9A7"
              effect="dark"
              size="large"
              style="border:none; font-size:14px; border-radius: 30px; font-weight:700;"
              >{{ o.result }}</el-tag
            >
            <el-tag
              v-if="o.result == '거절'"
              color="#A5A4A4"
              effect="dark"
              size="large"
              style="border:none; font-size:14px; border-radius: 30px; font-weight:700;  "
              >{{ o.result }}</el-tag
            >
            <el-tag
              v-if="o.result == '대기'"
              color="#87CEDC"
              effect="dark"
              size="large"
              style="border:none; font-size: 14px; border-radius: 30px; font-weight:700; "
              >{{ o.result }}</el-tag
            >
          </div>
        </el-col>
        <el-col :span="14">
          <div class="apply-content">
            <p style="font-size:11pt;">
              분류 : {{ o.boardType }} | 제목 :
              {{
                o.boardTitle.length < 15
                  ? o.boardTitle
                  : o.boardTitle.substr(0, 4) + "..."
              }}
            </p>
            <h5 style="font-weight:700;  ">
              {{
                o.dogName.length < 6 ? o.dogName : o.dogName.substr(0, 5) + ".."
              }}
            </h5>
          </div>
        </el-col>
        <el-col :span="2" v-if="o.result == '승인'">
          <div class="icon">
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
          <div class="icon">
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
  /* background: #F9F0E7; */
  border: solid 1px lightgray;
  height: 90px;
}
.grid-content {
  display: flex;
  align-items: center;
  flex-direction: row;
  padding: 10px 0 10px 20px;
  border-radius: 4px;
  min-height: 36px;
  margin-bottom: 20px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.tag {
  vertical-align: middle;
  margin-right: 20px;
}
.apply-content {
  text-align: left;
  vertical-align: middle;
  margin-right: 20px;
}
.apply-content p {
  text-align: left;
  margin: 5px 0 5px 0;
}
.icon {
  text-align: right;
  margin-left: 20px;
}
</style>

<script>
import { onBeforeMount, onMounted, reactive, computed } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { createToast } from "mosha-vue-toastify";

export default {
  name: "user-apply-result-item",
  props: {
    result: {
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

    const readAdoptForm = function(id) {
      store.commit("root/setAdoptFormId", id);
      router.push({ name: "AdoptFormReview" });
    };

    const isPassed = function(result){
      if(result === "승인") // 승인되었을 때는 span: 2
        return 2;
      else
        return 4; // 승인 안되었을 때는 span: 4
    }

    // 상담채팅방 열기
    const openChatting = function(counselingId) {
      store
        .dispatch("root/requestChatRoomByCounseling", {
          counselingId : counselingId,
          withCredentials : true
        })
        .then(function(result){
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
    }

    return { state, readAdoptForm, isPassed, openChatting };
  }


};
</script>
