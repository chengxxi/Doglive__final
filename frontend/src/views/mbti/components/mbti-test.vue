<template>
  <div class="main-body main-padding">
    <div style="width:100%; ">
      <!-- <bread-crumb></bread-crumb> -->
      <div class="box-card " style="width:100%; border:none;">
        <div class="container-lg ">
          <div v-if="state.index == 0">
            <div class="row flex-center" style="margin-top:10%;">
              <div class="col-auto text-center">
                <h5 class="fw-bold">16 Pupsonality</h5>
                <h1 class="fw-bold" style="font-size:60pt; ">
                  MPTI
                </h1>
                <h4 class="fw-bold">My Puppy Type Indicator</h4>
                <hr class="mx-auto text-dark" style="height:2px;width:50px" />
                <p class="mb-3">
                  당신과 찰떡💛궁합 반려견🐶은 어떤 성향일까요❔ <br />
                  테스트 할 준비가 되셨나요?
                </p>
              </div>
            </div>
            <div class="row h-100 justify-content-center pt-1">
              <div class="col-12 col-sm-9 col-md-4 mt-2">
                <div class="text-center pt-2 z-index-2">
                  <el-button
                    type="primary"
                    @click="nextPage"
                    class="btn btn-lg btn-primary rounded-pill hover-top"
                    >테스트 시작하기</el-button
                  >
                </div>
              </div>
            </div>
          </div>

          <div v-if="state.index >= 1 && state.index <= 20">
            <div class="row flex-center" style="margin-top:12%;">
              <div class="col-auto text-center">
                <h3 class="fw-bold">{{ state.question[state.index - 1] }}</h3>
                <p class="mb-3">
                  제가 마음에 드는 만큼 막대⚪를 이동시켜주세요
                </p>
              </div>
            </div>
            <div class="row h-100 justify-content-center pt-5">
              <img
                style="width:150px;cursor:pointer; margin-left:10p; margin-right:10px;"
                src="https://d2ud6j7vlf3xy9.cloudfront.net/img/KakaoTalk_20210816_233837503.png
"
              />

              <div class="col">
                <div class="block" style="padding-top:4%;">
                  <el-slider v-model="state.score" :step="20" show-stops>
                  </el-slider>
                </div>
              </div>

              <img
                style="width:150px;cursor:pointer; margin-left:10p; margin-right:10px;"
                src="https://d2ud6j7vlf3xy9.cloudfront.net/img/KakaoTalk_20210816_233822105.png"
              />
            </div>
            <div
              class="row h-100 justify-content-center"
              style="padding-top:10px;"
            >
              <div class="text-center pt-2 z-index-2  mt-2">
                <el-button
                  min="1"
                  max="101"
                  type="primary"
                  @click="nextPage"
                  class="btn btn-lg btn-primary rounded-pill hover-top"
                  >다음</el-button
                >
              </div>
              <div class="col-auto text-center mt-2">
                {{ state.index }} / 20
                <!-- <el-progress
                  :percentage="state.index * 5"
                  :color="state.colors"
                ></el-progress> -->
                <p style="font-size:11pt;" v-if="state.index == 10">
                  📢이제 절반 왔다개❕❗
                </p>
                <p
                  style="font-size:11pt;"
                  v-if="state.index >= 16 && state.index <= 19"
                >
                  얼마 안남았다개❕❗
                </p>
                <p style="font-size:11pt;" v-if="state.index == 20">
                  한 문제 남았다개🥰
                </p>
              </div>
            </div>
          </div>

          <div v-if="state.index > 20">
            <div class="row flex-center" style="margin-top:12%;">
              <div class="col-auto text-center">
                <h1 class="fw-bold">테스트가 끝났다개🐶</h1>

                <hr class="mx-auto text-dark" style="height:2px;width:50px" />
                <p class="mb-3">
                  🤍두근두근🤍 <br />
                  당신의 강아지를 만나러 갈 준비 되셨나요?
                </p>
              </div>
            </div>
            <div class="row h-100 justify-content-center pt-3 ">
              <div class="col-12 col-sm-9 col-md-4 mt-4">
                <div class="text-center pt-2 z-index-2">
                  <el-button
                    type="primary"
                    @click="goResult"
                    class="btn btn-lg btn-primary rounded-pill hover-top"
                    >결과보기 💌</el-button
                  >
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MbtiDescription from "./mbti-description.vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import BreadCrumb from "@/views/adopt/components/bread-crumb.vue";
import { computed, reactive, onMounted } from "vue";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "mbti-test",
  components: {
    MbtiDescription,
    BreadCrumb
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();

    const state = reactive({
      score: 0,
      isLoading: computed(() => state.score),
      index: 0,
      question: [
        "나는 잘 때를 제외하고 가만히 있는 것을 참지 못해요🤸‍♂️🤸‍♀️",
        "혼날 때, 한껏 주눅들어 있어요😥",
        "저는 애견카페에서 다른 강아지랑 노는 것보다 단둘이 산책🌿🌳하는게 좋아요!",
        "다른 강아지들과 어울리는 데 시간이 좀 걸려요👀",
        "우리 집에 손님이 왔을 때, 나는 무척 신나요🎉",
        "나는 이 세상에서 반려인만 있으면 돼요💞 ",
        "저는 애정표현💘에 매우 적극적이에요! 🥰😘",
        "모르는 사람과 같이 있는 시간은 견디기 힘들어요..😓💦",
        "에너지가 넘쳐서 집에 들어가기 싫어요 산책 한 바퀴 더 돌아요🦮",
        "교육받을 때, 간식이 없으면 나🐶를 교육하기 힘들거에요❗ ",
        "우리 집에 강아지는 💛나 혼자🤍였으면 좋겠어요!",
        "낯선 장난감🏈은 천천히 알아보고 싶어요.",

        "반려인이 누워있을 때, 같이 누워있어요🛏",
        "반려인이 바빠도, 내가 심심하면 놀아줘야해요😝",
        "자는 공간은 분리되었으면 좋겠어요 🙄",

        "나는야 우리 동네 최고 인싸 강아지!🐕‍🦺✨ 친화력이 좋아요.",
        "쉽게 흥분하는 일은 거의 없어요🙂🧘‍♂️",

        "내가 원하는 것을 얻기 위해 때로는 불쌍한 척🥺을 해요.",

        "저만의 영역🏠을 존중해주면 좋겠어요!",

        "처음 가 보는 익숙하지 않은 장소🌎도 두렵지 않아요."
      ],
      answer: []
    });

    const nextPage = function() {
      state.answer.push(state.score);

      state.score = 0;
      state.index += 1;
    };

    //MBTI 테스트 결과 계산하여 axios
    const goResult = function() {
      //결과 계산
      const result = {
        scoreI: 0,
        scoreE: 0,
        scoreN: 0,
        scoreS: 0,
        scoreF: 0,
        scoreT: 0,
        scoreP: 0,
        scoreJ: 0
      };

      for (var i = 1; i <= 20; i++) {
        if (i % 4 == 1) {
          if (i <= 9) {
            //에너지
            result.scoreE += state.answer[i];
            result.scoreI += 100 - state.answer[i];
          } else {
            //침착한
            result.scoreI += state.answer[i];
            result.scoreE += 100 - state.answer[i];
          }
        } else if (i % 4 == 2) {
          if (i <= 6) {
            //충성심강한
            result.scoreS += state.answer[i];
            result.scoreN += 100 - state.answer[i];
          } else {
            //영리한
            result.scoreN += state.answer[i];
            result.scoreS += 100 - state.answer[i];
          }
        } else if (i % 4 == 3) {
          if (i <= 11) {
            //관계지향
            result.scoreF += state.answer[i];
            result.scoreT += 100 - state.answer[i];
          } else {
            //독립지향
            result.scoreT += state.answer[i];
            result.scoreF += 100 - state.answer[i];
          }
        } else if (i % 4 == 0) {
          if (i <= 12) {
            //신중한
            result.scoreP += state.answer[i];
            result.scoreJ += 100 - state.answer[i];
          } else {
            //친화적인
            result.scoreJ += state.answer[i];
            result.scoreP += 100 - state.answer[i];
          }
        }
      }

      store
        .dispatch("root/requestMBTIResult", result)
        .then(function(result) {
          const data = {
            id: result.data.mbti.id,
            name: result.data.mbti.name,
            title: result.data.mbti.title,
            desc: result.data.mbti.desc,
            imageUrl: result.data.mbti.imageUrl,
            matchedBoardList: result.data.matchedBoardList
          };

          store.commit("root/setMbtiDetail", data);

          //router.push({ name: "Main" });
          router.push({ name: "MbtiDetail" });
        })
        .catch(function(err) {
          console.log(err);
        });
    };
    onMounted(() => {
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "MPTI",
        path: "/mbti",
        subTitle: "16Pupsonality MPTI"
      });
      window.scrollTo(0, 0);
      state.index = 0;
    });

    return { state, nextPage, goResult };
  }
};
</script>

<style scoped>
@import "../../../common/css/main-content.css";

/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%, 위 여백 50px) */
.main-body {
  width: 100%;
  margin-left: 10%;
  margin-right: 10%;
}
/* .mypage-body .menu-title{
  width: inherit;
  padding-top: 10px;
  padding-bottom: 10px;
  border-bottom: solid 1px rgb(212, 212, 212);
} */
.mypage-body .menu-title {
  text-align: left;
  padding-top: 10px;
  padding-bottom: 10px;
  border-bottom: solid 1px rgb(212, 212, 212);
}
.mypage-body .mypage-content {
  padding-top: 40px;
  text-align: center;
  margin: 0 auto;
}

:deep(.el-slider__button) {
  border: 15px solid #dfc6bd;
  color: #dfc6bd;
}

:deep(.el-button--primary) {
  color: #755744;
  border-color: #f9f0e7;
  background-color: #f9f0e7;
}

:deep(.el-button) {
  font-weight: 600;
}
:deep(.el-button:hover) {
  color: #f9f0e7;
  border-color: #755744;
  background-color: #755744;
}

:deep(.el-slider__bar) {
  background-color: #dfc6bd;
}

:deep(.el-slider__runway) {
  background-color: #f9f0e7;
}
</style>
