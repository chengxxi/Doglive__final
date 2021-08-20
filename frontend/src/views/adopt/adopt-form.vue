<template>
  <div class="main-body main-padding">
    <div style="width:100%; ">
      <bread-crumb></bread-crumb>
      <el-card
        class="box-card"
        style="width:100%; height:auto;  border:none;"
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
                <el-form-item
                  label="신청자 이름"
                  prop="name"
                  style="width:100%"
                >
                  <el-input
                    v-model="form.name"
                    placeholder="실명을 기재해주세요"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col :span="12">
                <el-form-item
                  label="이메일 주소"
                  prop="email"
                  style="width:95%"
                >
                  <el-input v-model="form.email" placeholder="ssafy@ssafy.com">
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="연락처" prop="phone" style="width:100%">
                  <el-input
                    v-model="form.phone"
                    placeholder="010-0000-0000"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="15">
                <el-row>
                  <el-col :span="12">
                    <el-form-item
                      label="거주지(시/도)"
                      prop="sido"
                      label-width="100%"
                    >
                      <el-select
                        v-model="form.sido"
                        placeholder="시/도"
                        style="width:95% ;"
                        :change="gugunList(form.sido)"
                      >
                        <el-option
                          v-for="(sido, idx) in state.sidoList"
                          :key="idx"
                          :label="sido.name"
                          :value="sido.id"
                        >
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item
                      label="거주지(구/군)"
                      prop="gugun"
                      label-width="100%"
                    >
                      <el-select
                        style="width:95% ;"
                        v-model="form.gugun"
                        placeholder="구/군"
                      >
                        <el-option
                          v-for="(gugun, idx) in state.gugunList"
                          :key="idx"
                          :label="gugun.name"
                          :value="gugun.id"
                        >
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-col>
              <el-col :span="5">
                <el-row>
                  <el-col :span="24">
                    <el-form-item
                      label="결혼여부"
                      prop="isMarried"
                      label-width="100%"
                    >
                      <el-radio-group
                        style="width:100%"
                        v-model="form.isMarried"
                      >
                        <el-radio
                          style="margin-left:10%; "
                          label="Yes"
                          border
                        ></el-radio>
                        <el-radio label="No" border></el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-col>
              <el-col :span="4">
                <el-form-item label="성별" label-width="100%" prop="gender">
                  <el-radio-group v-model="form.gender" style="width:100%;">
                    <el-radio
                      style="margin-left:10%; "
                      label="남"
                      border
                    ></el-radio>
                    <el-radio label="여" border></el-radio>
                  </el-radio-group>
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
                <el-form-item label="공고 제목" prop="title" style="width:100%">
                  <el-input v-model="state.board.title" disabled></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col :span="12">
                <el-form-item
                  label="강아지 이름"
                  prop="dogName"
                  style="width:95%"
                >
                  <el-input v-model="state.board.dogName" disabled></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="공고 타입" prop="type" style="width:100%">
                  <el-input
                    v-model="state.board.boardType.name"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q1"
                  prop="answer1"
                  style="width:100%"
                  label-width="70%"
                >
                  <el-radio-group v-model="form.answer1">
                    <el-radio label="Yes" border></el-radio>
                    <el-radio label="No" border></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row v-if="form.answer1 === 'Yes'" class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q1sub"
                  prop="answer1sub"
                  style="width:100%"
                >
                  <el-input
                    v-model="form.answer1sub"
                    type="textarea"
                    :rows="3"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q2"
                  prop="answer2"
                  style="width:100%"
                  label-width="70%"
                >
                  <el-radio-group v-model="form.answer2">
                    <el-radio label="Yes" border></el-radio>
                    <el-radio label="No" border></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row v-if="form.answer2 === 'Yes'" class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q2sub"
                  prop="answer2sub"
                  style="width:100%"
                >
                  <el-input
                    v-model="form.answer2sub"
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
                  prop="answer3"
                  style="width:100%"
                  label-width="70%"
                >
                  <el-input-number
                    v-model="form.answer3"
                    :min="1"
                    :max="10"
                  ></el-input-number>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q3sub"
                  prop="answer3sub"
                  style="width:100%"
                  label-width="70%"
                >
                  <el-radio-group v-model="form.answer3sub">
                    <el-radio label="동의" border></el-radio>
                    <el-radio label="일부동의" border></el-radio>
                    <el-radio label="반대" border></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q4"
                  prop="answer4"
                  style="width:100%"
                  label-width="70%"
                >
                  <el-select v-model="form.answer4" placeholder="거주형태">
                    <el-option label="아파트" value="아파트"></el-option>
                    <el-option label="단독주택" value="단독주택"></el-option>
                    <el-option label="빌라" value="빌라"></el-option>
                    <el-option label="원룸" value="원룸"></el-option>
                    <el-option label="기타" value="기타"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q5"
                  prop="answer5"
                  style="width:100%"
                  label-width="70%"
                >
                  <el-radio-group v-model="form.answer5">
                    <el-radio label="Yes" border></el-radio>
                    <el-radio label="No" border></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q6"
                  prop="answer6"
                  style="width:100%"
                  label-width="70%"
                >
                  <el-radio-group v-model="form.answer6">
                    <el-radio label="Yes" border></el-radio>
                    <el-radio label="No" border></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q7"
                  prop="answer7"
                  style="width:100%"
                >
                  <el-input
                    v-model="form.answer7"
                    type="textarea"
                    :rows="4"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q8"
                  prop="answer8"
                  style="width:100%"
                >
                  <el-input
                    v-model="form.answer8"
                    type="textarea"
                    :rows="4"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q9"
                  prop="answer9"
                  style="width:100%"
                >
                  <el-input
                    v-model="form.answer9"
                    type="textarea"
                    :rows="4"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="mt-3 mb-3">
              <el-col>
                <el-form-item
                  :label="question.q10"
                  prop="answer10"
                  style="width:100%"
                >
                  <el-input
                    v-model="form.answer10"
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
              <el-button type="primary" @click="submitForm('form')"
                >제출</el-button
              >
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
  name: "AdoptForm",
  components: {
    BreadCrumb
  },
  data() {
    return {
      form: {
        email: "",
        name: "",
        phone: "",
        gender: "",
        age: "",
        sido: "",
        gugun: "",
        job: "",

        isMarried: "",

        answer1: "",
        answer1sub: "",
        answer2: "",
        answer2sub: "",
        answer3: 0,
        answer3sub: "",
        answer4: "",
        answer5: false,
        answer6: "",
        answer7: "",
        answer8: "",
        answer9: "",
        disabled: true
      },
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
      },
      rules: {
        //유효성 검사
        email: [
          {
            required: true,
            message: "이메일을 입력해주세요.",
            trigger: "change"
          }
        ],
        name: [
          {
            required: true,
            message: "이름을 입력해주세요.",
            trigger: "change"
          }
        ],
        phone: [
          {
            required: true,
            message: "연락처를 입력해주세요.",
            trigger: "change"
          }
        ],
        gender: [
          {
            required: true,
            message: "성별을 입력해주세요.",
            trigger: "change"
          }
        ],
        age: [
          {
            required: true,
            message: "나이를 입력해주세요.",
            trigger: "change"
          }
        ],
        sido: [
          {
            required: true,
            message: "주소지를 입력해주세요.",
            trigger: "change"
          }
        ],
        gugun: [
          {
            required: true,
            message: "주소지를 입력해주세요.",
            trigger: "change"
          }
        ],
        isMarried: [
          {
            required: true,
            message: "결혼 여부를 입력해주세요.",
            trigger: "change"
          }
        ],
        job: [
          {
            required: true,
            message: "직업을 입력해주세요.",
            trigger: "change"
          }
        ],
        answer1: [
          {
            required: true,
            message: "답변을 입력해주세요.",
            trigger: "change"
          }
        ],
        answer2: [
          {
            required: true,
            message: "답변을 입력해주세요.",
            trigger: "change"
          }
        ],
        answer3: [
          {
            required: true,
            message: "답변을 입력해주세요.",
            trigger: "change"
          }
        ],
        answer3sub: [
          {
            required: true,
            message: "답변을 입력해주세요.",
            trigger: "change"
          }
        ],
        answer4: [
          {
            required: true,
            message: "답변을 입력해주세요.",
            trigger: "change"
          }
        ],
        answer5: [
          {
            required: true,
            message: "답변을 입력해주세요.",
            trigger: "change"
          }
        ],
        answer6: [
          {
            required: true,
            message: "답변을 입력해주세요.",
            trigger: "change"
          }
        ],
        answer7: [
          {
            required: true,
            message: "답변을 입력해주세요.",
            trigger: "change"
          },
          {
            min: 10,
            message: "10글자 이상 작성해주세요.",
            trigger: "change"
          },
          {
            max: 500,
            message: "500글자 이하로 작성해주세요.",
            trigger: "change"
          }
        ],
        answer8: [
          {
            required: true,
            message: "답변을 입력해주세요.",
            trigger: "change"
          },
          {
            min: 20,
            message: "20글자 이상 작성해주세요.",
            trigger: "change"
          },
          {
            max: 500,
            message: "500글자 이하로 작성해주세요.",
            trigger: "change"
          }
        ],
        answer9: [
          {
            required: true,
            message: "답변을 입력해주세요.",
            trigger: "change"
          },
          {
            min: 20,
            message: "20글자 이상 작성해주세요.",
            trigger: "change"
          },
          {
            max: 500,
            message: "500글자 이하로 작성해주세요.",
            trigger: "change"
          }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      const data = {
        userId: this.state.userInfo.userId,
        data: {
          boardId: this.state.board.boardId,
          boardType: this.state.board.boardType.name,
          boardTitle: this.state.board.title,
          dogName: this.state.board.dogName,
          content: JSON.stringify(this.form)
        }
      };

      console.log(data);

      this.$refs[formName].validate(valid => {
        if (valid) {
          this.submitAdoptForm(data);
          console.log(this.ruleForm);
        } else {
          createToast("작성하지 않은 필수항목이 있어요 💬💦", {
            hideProgressBar: "true",
            timeout: 4500,
            showIcon: "true",
            toastBackgroundColor: "#c49d83",
            position: "bottom-left",
            transition: "bounce",
            type: "warning"
          });
          return false;
        }
      });
    }
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();

    const state = reactive({
      board: computed(() => {
        return store.getters["root/getBoardDetail"];
      }),
      userInfo: computed(() => {
        return store.getters["root/getLoginUserInfo"];
      }),
      sidoList: [],
      gugunList: [{ id: 0, name: "시/도를 먼저 선택해주세요" }]
    });

    //시도 리스트 가져오기
    store
      .dispatch("root/requestSidoCodeList")
      .then(function(result) {
        state.sidoList = result.data.sidoList;
      })
      .catch(function(error) {
        console.log(error);
      });

    //이메일 중복체크
    const clickEmailCheck = function() {
      createToast("🚧 아직 구현중🔨인 기능이에요 🚧", {
        hideProgressBar: "true",
        timeout: 4500,
        showIcon: "true",
        toastBackgroundColor: "#c49d83",
        position: "bottom-left",
        transition: "bounce",
        type: "warning"
      });
    };

    //입양신청서 제출
    const submitAdoptForm = function(data) {
      store
        .dispatch("root/registerAdoptForm", data)
        .then(function(result) {
          //2. 마이페이지 제출 확인 페이지로 이동
          createToast(
            "입양 신청서가 제출 되었어요. 마이페이지에서 승인 결과를 확인할 수 있습니다!🐾💌",
            {
              hideProgressBar: "true",
              timeout: 4000,
              showIcon: "true",
              toastBackgroundColor: "#7eaa72",
              position: "bottom-left",
              transition: "bounce",
              type: "success"
            }
          );
          router.push({ name: "AdoptDetail" });
        })
        .catch(function(error) {
          createToast("입양 신청서 제출에 실패했어요 💬💦", {
            hideProgressBar: "true",
            timeout: 4500,
            showIcon: "true",
            toastBackgroundColor: "#c49d83",
            position: "bottom-left",
            transition: "bounce",
            type: "warning"
          });
          console.log(error);
        });
    };

    //시도에 맞는 구군 리스트 가져오기
    const gugunList = function(selectedSidoCode) {
      store
        .dispatch("root/requestGugunCodeList", selectedSidoCode)
        .then(function(result) {
          console.log("call : guguncode");

          state.gugunList = result.data.gugunList;
        })
        .catch(function(error) {
          console.log(error);
        });
    };

    onMounted(() => {
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "입양/임보",
        path: "/adopt",
        subTitle: "입양/임보 신청서 작성"
      });
      window.scrollTo(0, 0);
    });

    return { state, gugunList, clickEmailCheck, submitAdoptForm };
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

:deep(.el-textarea__inner) {
  resize: none;
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
  font-weight: 500;
}

.box {
  margin-top: 30px;
  margin-bottom: 40px;
  padding: 40px;
  background-color: #faf4ef;
}

:deep(.el-input.is-disabled .el-input__inner) {
  background-color: #f9f0e7;
  border-color: #f9f0e7;
  color: #616161;
}

.center {
  display: flex;
  justify-content: center;
}
</style>
