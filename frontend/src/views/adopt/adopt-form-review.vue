<template>
  <div class="main-body main-padding">
    <div style="width:100%;  ">
      <bread-crumb></bread-crumb>
      <el-card
        class="box-card"
        style="width:100%; height:auto; border:none;"
        shadow="none"
      >
        <div style="margin-left:30px; margin-right:30px;">
          <el-form
            class="mt-5"
            style=" margin:100px;"
            label-position="left"
            :model="form"
            :rules="rules"
            ref="form"
          >
            <h4 class="mb-3" style="font-weight:600;">
              🙋‍♀️ 기본 인적 사항
            </h4>
            <el-divider />

            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item label="신청자 이름" style="width:100%">
                  <el-input
                    v-model="state.adoptForm.name"
                    placeholder=""
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col :span="12">
                <el-form-item label="이메일 주소" style="width:95%">
                  <el-input v-model="state.adoptForm.email" disabled>
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="연락처" style="width:100%">
                  <el-input v-model="state.adoptForm.phone" disabled></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="15">
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="거주지(시/도)" label-width="100%">
                      <el-input
                        v-model="state.adoptForm.sido"
                        style="width:95% ;"
                        disabled
                      ></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="거주지(구/군)" label-width="100%">
                      <el-input
                        v-model="state.adoptForm.gugun"
                        style="width:95% ;"
                        disabled
                      ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-col>
              <el-col :span="5">
                <el-row>
                  <el-col :span="24">
                    <el-form-item label="결혼여부" label-width="100%">
                      <el-input
                        v-model="state.adoptForm.isMarried"
                        style="width:95% ;"
                        disabled
                      ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-col>
              <el-col :span="4">
                <el-form-item label="성별" label-width="100%">
                  <el-input
                    v-model="state.adoptForm.gender"
                    style="width:95% ;"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <div style="margin-top:100px;">
              <h4 class="mt-1 mb-3" style="font-weight:600; ">
                ✍ 입양 관련 설문
              </h4>
            </div>
            <el-divider />
            <el-row class="mt-3 mb-3">
              <el-col :span="24">
                <el-form-item label="공고 제목" style="width:100%">
                  <el-input v-model="state.adoptForm.title" disabled></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col :span="12">
                <el-form-item label="강아지 이름" style="width:95%">
                  <el-input
                    v-model="state.adoptForm.dogName"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="공고 타입" style="width:100%">
                  <el-input v-model="state.adoptForm.type" disabled></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q1"
                  style="width:100%"
                  label-width="70%"
                >
                  {{ state.adoptForm.answer1 }}
                </el-form-item>
              </el-col>
            </el-row>
            <el-row
              v-if="
                state.adoptForm.answer1 === 'Yes' &&
                  state.adoptForm.answer1sub != null &&
                  state.adoptForm.answer1sub != ''
              "
              class="mt-3 mb-3"
            >
              <el-col>
                <el-form-item :label="question.q1sub" style="width:100%">
                  <el-input
                    v-model="state.adoptForm.answer1sub"
                    type="textarea"
                    disabled
                    :rows="3"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q2"
                  style="width:100%"
                  label-width="70%"
                >
                  {{ state.adoptForm.answer2 }}
                </el-form-item>
              </el-col>
            </el-row>
            <el-row
              v-if="
                state.adoptForm.answer2 === 'Yes' &&
                  state.adoptForm.answer2sub != null &&
                  state.adoptForm.answer2sub != ''
              "
              class="mt-3 mb-3"
            >
              <el-col>
                <el-form-item :label="question.q2sub" style="width:100%">
                  <el-input
                    v-model="state.adoptForm.answer2sub"
                    disabled
                    type="textarea"
                    :rows="3"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q3"
                  style="width:100%"
                  label-width="70%"
                >
                  {{ state.adoptForm.answer3 }}
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q3sub"
                  style="width:100%"
                  label-width="70%"
                >
                  {{ state.adoptForm.answer3sub }}
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q4"
                  style="width:100%"
                  label-width="70%"
                >
                  {{ state.adoptForm.answer4 }}
                </el-form-item>
              </el-col>
            </el-row>

            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q5"
                  style="width:100%"
                  label-width="70%"
                >
                  {{ state.adoptForm.answer5 }}
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q6"
                  style="width:100%"
                  label-width="70%"
                >
                  {{ state.adoptForm.answer6 }}
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item :label="question.q7" style="width:100%">
                  <el-input
                    v-model="state.adoptForm.answer7"
                    disabled
                    type="textarea"
                    :rows="4"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item :label="question.q8" style="width:100%">
                  <el-input
                    v-model="state.adoptForm.answer8"
                    disabled
                    type="textarea"
                    :rows="4"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item :label="question.q9" style="width:100%">
                  <el-input
                    v-model="state.adoptForm.answer9"
                    disabled
                    type="textarea"
                    :rows="4"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item :label="question.q10" style="width:100%">
                  <el-input
                    v-model="state.adoptForm.answer10"
                    disabled
                    type="textarea"
                    :rows="4"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row
              class="mt-5"
              style=" display: flex;
  justify-content: center;"
            >
              <el-button
                type="primary"
                @click="goBack"
                style=" margin-top:20px; font-weight:600;  margin-right:5px; height:40px;"
                >마이페이지 돌아가기
              </el-button>
            </el-row>
          </el-form>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import BreadCrumb from "./components/bread-crumb.vue";
import { computed, reactive, onMounted, ref } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "AdoptFormReview",
  components: {
    BreadCrumb
  },
  data() {
    return {
      question: {
        q1: "1. 이전에 반려동물을 키우신 적이 있으신가요?",
        q1sub:
          "1-2. 만일 있으시다면 어떤 종류의 동물인지, 얼마나 오래 키우셨는지, 지금은 어떻게 되었는지 적어주세요.",
        q2: "2. 현재 집에서 다른 동물을 키우고 계십니까?",
        q2sub:
          "2-2. 만일 있으시다면 각 동물의 종류, 성별, 중성화 여부를 기재해주세요.",
        q3: "3. 귀하의 가족은 모두 몇 명인가요?",
        q3sub: "4. 입양 결정에 가족 모두 동의하십니까?",
        q4: "5. 거주하고 계신 주택 형태를 선택해 주세요.",
        q5:
          "6. 화상회의를 통한 입양상담과 부가적인 주거환경 확인에 동의하시나요?",
        q6:
          "7. 반려동물을 입양하시려면, 중성화 수술 시행에도 동의하셔야 합니다. 이에 동의하십니까?",

        q7:
          "8. 키우던 반려동물을 개인 사정으로 포기한 경험이 있으신가요? 이유는 무엇인가요?",
        q8: "9. 입양을 원하시는 가장 큰 이유는 무엇인가요?",
        q9:
          "10. 앞으로 결혼, 임신, 출산 등 가족의 변화가 있는 경우 반려동물의 거취문제에 대해 어떻게 생각하십니까?",
        q10:
          "11. 그 외에 입양 신청에 관해 덧붙이고자 하시는 말씀이 있으시면 적어주시기 바랍니다."
      }
    };
  },

  setup() {
    const store = new useStore();
    const router = new useRouter();

    const state = reactive({
      adoptFormId: computed(() => {
        console.log(store.getters["root/getAdoptFormId"]);
        return store.getters["root/getAdoptFormId"];
      }),
      adoptForm: {}
    });

    const readAdoptForm = function() {
      store
        .dispatch("root/readAdoptForm", state.adoptFormId)
        .then(function(result) {
          state.adoptForm = result.data.content;
        })
        .catch(function(err) {
          console.log(err);
        });
    };

    const goBack = function() {
      router.push({ name: "Mypage" });
    };

    onMounted(() => {
      console.log("breadcrumb");
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "입양/임보",
        path: "/adopt",
        subTitle: "입양/임보 신청서 조회"
      });
      readAdoptForm();
      window.scrollTo(0, 0);
    });

    return { state, readAdoptForm, goBack };
  }
};
</script>

<style scoped>
:deep(.el-radio__inner:hover) {
  border-color: #755744;
  color: #755744;
}

:deep(.el-radio.is-bordered.is-checked) {
  border-color: #755744;
  background-color: #f9f0e7;
  color: #755744;
  font-weight: 600;
}

li.el-select-dropdown__item.selected {
  color: #755744;
  font-weight: 700;
}

:deep(.el-radio__inner) {
  display: none;
}
:deep(.el-radio__input.is-checked + .el-radio__label) {
  border-color: #755744;
  color: #755744;
}

:deep(.el-radio__input.is-checked .el-radio__inner) {
  border-color: #755744;
  background-color: #754444;
  background: #755744;
}

:deep(.el-radio) {
  margin-left: 0px;
  margin-right: 0px;
}

:deep(.el-button) {
  font-weight: 600;
}
:deep(.el-button:hover) {
  color: #f9f0e7;
  border-color: #755744;
  background-color: #755744;
}

:deep(.el-input__inner:focus) {
  border: 1px solid #755744;
}

:deep(.el-textarea__inner:focus) {
  border: 1px solid #755744;
}

:deep(.el-radio-button__inner:hover) {
  color: #d7aea4;
}

:deep(.el-button--primary) {
  color: #755744;
  border-color: #f9f0e7;
  background-color: #f9f0e7;
}
:deep(.el-radio-button__orig-radio:checked + .el-radio-button__inner) {
  border-color: #755744;
  background-color: #755744;
  box-shadow: -1px 0 0 0 #755744;
}

:deep(.el-select-dropdown__item.selected) {
  color: #755744;
}
:deep(.el-select .el-input.is-focus .el-input__inner) {
  border-color: #755744;
}
:deep(.el-switch.is-checked .el-switch__core) {
  border-color: #755744;
  background-color: #755744;
}

:deep(.el-form-item__label) {
  font-size: 12pt;
}

.box {
  margin-top: 30px;
  margin-bottom: 40px;
  padding: 40px;
  background-color: #faf4ef;
}

:deep(.el-input.is-disabled .el-input__inner) {
  background-color: #fffbf7;
  border-color: #fffbf7;
  font-size: 11pt;
  color: #616161;
  cursor: auto;
}

.center {
  display: flex;
  justify-content: center;
}

:deep(.el-textarea.is-disabled .el-textarea__inner) {
  background-color: #fffbf7;
  border-color: #fffbf7;
  font-size: 11pt;
  color: #616161;
  cursor: auto;
  resize: none;
}
</style>
