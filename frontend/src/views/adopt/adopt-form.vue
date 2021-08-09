<template>
  <div class="main-body main-padding">
    <el-card
      class="box-card"
      style="width:100%; height:auto;  border:none;"
      shadow="none"
    >
      <bread-crumb></bread-crumb>

      <div style="margin-left:30px; margin:right:30px;">
        <el-form
          style=" margin:100px;"
          label-position="left"
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="120px"
        >
          <div
            style="display: flex;
  justify-content: center;"
          >
            <h3
              class="mt-1 mb-3"
              style="font-weight:600; background-color:#fbe997"
            >
              입양은 "평생, 끝까지" 책임지는 일입니다.
            </h3>
          </div>
          <div
            style=" margin-bottom:70px; display: flex;
  justify-content: center;"
          >
            <h6>
              반려동물은 물건이 아닌 생명이고 가족입니다. 아이들이 두 번
              상처받지 않도록 신중하게 고민하고 책임감을 가져주세요.
            </h6>
          </div>

          <h4 class="mt-1 mb-3" style="font-weight:600">
            🚥 입양 절차 및 유의사항
          </h4>
          <div class="box" style="margin-bottom:60px;">
            <h5 style="font-weight:600">1. 입양 신청서 작성</h5>
            <div style="margin-left:20px; margin-bottom:40px;">
              <h6>
                입양을 원하시는 분은 [입양 신청] 버튼을 누르시고, 입양신청서를
                작성해 주세요.
              </h6>
              <h6>
                🚫
                <b>작성 내용이 사실과 다를 경우 입양이 취소될 수 있습니다.</b>
              </h6>
            </div>

            <h5 style="font-weight:600">2. 채팅 및 화상상담</h5>
            <div style="margin-left:20px; margin-bottom:40px;">
              <h6>
                채팅 및 화상상담 임보자가 신청서 검토 및 승인 후 개별 면담이
                시작됩니다.
              </h6>
              <h6>
                입양 신청자는 채팅 및 화상회의를 통해 개별 면담을 진행하게
                됩니다.
              </h6>
              <h6>
                이 상담 결과로 최종 입양이 결정됩니다.
              </h6>
            </div>

            <h5 style="font-weight:600">3. 입양동의서 작성 및 입양 확정</h5>
            <div style="margin-left:20px;">
              <h6>
                입양이 결정되면 대면 만남을 통해 입양 동의서 작성 후 모든 절차가
                마무리 됩니다.
              </h6>
              <h6>
                🚫
                <b
                  >단, 방문시 사실과 다르거나 입양자격이 부족하다고 판단되면
                  입양이 취소될 수 있습니다.</b
                >
              </h6>
            </div>
          </div>

          <h4 class="mt-1 mb-3" style="font-weight:600">
            🚥 입양 자격 확인
          </h4>
          <div class="box" style=" margin-bottom:60px;">
            <h5 style="font-weight:600;">
              다음의 경우에는 입양이 어렵습니다.
            </h5>
            <div style="margin-left:20px; margin-top:20px;">
              <h6>
                <font-awesome-icon :icon="['fas', 'check-circle']" />
                가정으로의 입양이 아닌 경우 ( 예 : 공장, 회사, 식당 등 )
              </h6>
              <h6>
                <font-awesome-icon :icon="['fas', 'check-circle']" />
                미성년자의 입양 신청
              </h6>
              <h6>
                <font-awesome-icon :icon="['fas', 'check-circle']" />
                가족 구성원 전체의 동의를 얻지 않은 경우
              </h6>
              <h6>
                <font-awesome-icon :icon="['fas', 'check-circle']" />
                가족 구성원 중 동물에 대한 알레르기로 치료를 받고 있거나
                동물에게 위해가 될 수 있는 정신 질환을 가진 경우
              </h6>
              <h6>
                <font-awesome-icon :icon="['fas', 'check-circle']" />
                미취학 아동의 자녀가 3명 이상인 경우
              </h6>
              <h6>
                <font-awesome-icon :icon="['fas', 'check-circle']" />
                70세 이상 혼자 거주하고 있는 경우
              </h6>
            </div>
          </div>

          <el-divider style="margin-bottom:60px;" />

          <h4 class="mt-1 mb-3" style="font-weight:600;">
            ✍ 입양 신청서 작성
          </h4>

          <el-row class="mt-3 mb-3">
            <el-col :span="12">
              <el-form-item label="공고 제목" prop="title" style="width:95%">
                <el-input v-model="ruleForm.title" disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                label="강아지 이름"
                prop="dogName"
                style="width:100%"
              >
                <el-input v-model="ruleForm.dogName" disabled></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="mt-3 mb-3">
            <el-col :span="12">
              <el-form-item label="이름" prop="name" style="width:95%">
                <el-input v-model="ruleForm.name"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="이메일주소" prop="email" style="width:100%">
                <el-input v-model="ruleForm.email"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="mt-3 mb-3">
            <el-col :span="8">
              <el-form-item label="전화번호" prop="age" style="width:100%">
                <el-input-number
                  v-model="ruleForm.age"
                  :step="5"
                ></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="나이" prop="age" style="width:100%">
                <el-input-number
                  v-model="ruleForm.age"
                  :step="5"
                ></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="결혼여부" prop="gender">
                <el-radio-group v-model="ruleForm.gender">
                  <el-radio label="O" border></el-radio>
                  <el-radio label="X" border></el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row
            class="mt-5"
            style=" display: flex;
  justify-content: center;"
          >
            <el-button type="primary" @click="submitForm('ruleForm')"
              >작성</el-button
            >
            <el-button @click="resetForm('ruleForm')">초기화</el-button>
          </el-row>
        </el-form>
      </div>
    </el-card>
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
  name: "AdoptForm",
  components: {
    BreadCrumb
  },
  data() {
    return {
      ruleForm: {
        type: "",
        title: this.state.board.title,
        dogName: this.state.board.dogName,
        email: "",
        name: "",
        gender: "",
        hair: "",
        color: "",
        age: 25,
        size: "",
        neutralization: "",
        address: "",
        desc: "",
        energy: "",
        obedience: "",
        relationship: "",
        adaptability: "",
        disabled: false,
        dialogImageUrl: "",
        dialogVisible: false,
        disabled: false
      },
      rules: {
        type: [
          {
            required: true,
            message: "공고 타입을 선택해주세요",
            trigger: "blur"
          }
        ],
        name: [
          { required: true, message: "이름을 입력해주세요.", trigger: "blur" },
          {
            min: 1,
            max: 10,
            message: "10글자 이하로 입력해주세요.",
            trigger: "blur"
          }
        ],
        gender: [
          { required: true, message: "성별을 선택해주세요", trigger: "blur" }
        ],
        hair: [
          {
            required: true,
            message: "헤어타입을 선택해주세요",
            trigger: "blur"
          }
        ],
        color: [
          {
            required: true,
            message: "컬러타입을 선택해주세요",
            trigger: "blur"
          }
        ],
        age: [
          { required: true, message: "연령대를 선택해주세요", trigger: "blur" }
        ],
        size: [
          { required: true, message: "크기를 선택해주세요", trigger: "blur" }
        ],
        neutralization: [
          {
            required: true,
            message: "중성화여부를 선택해주세요",
            trigger: "blur"
          }
        ],
        address: [
          {
            required: true,
            message: "현재 강아지의 위치를 입력해주세요",
            trigger: "blur"
          },
          {
            min: 3,
            max: 20,
            message: "3글자 이상, 20글자 이하로 입력해주세요.",
            trigger: "blur"
          }
        ],
        desc: [
          {
            required: true,
            message: "설명을 입력해주세요 (소개글, 특이사항 등)",
            trigger: "blur"
          },
          {
            min: 50,
            max: 1000,
            message: "50글자 이상, 1000글자 이하로 입력해주세요.",
            trigger: "blur"
          }
        ],
        energy: [
          {
            required: true,
            message: "활동성향을 관찰 후 선택해주세요",
            trigger: "blur"
          }
        ],
        obedience: [
          {
            required: true,
            message: "순종성향을 관찰 후 선택해주세요",
            trigger: "blur"
          }
        ],
        relationship: [
          {
            required: true,
            message: "관계성향을 관찰 후 선택해주세요",
            trigger: "blur"
          }
        ],
        adaptability: [
          {
            required: true,
            message: "적응성향을 관찰 후 선택해주세요",
            trigger: "blur"
          }
        ]
      }
    };
  },

  methods: {
    submitForm(formName) {
      const mbti =
        [this.ruleForm.energy == "에너지있는" ? "E" : "I"] +
        [this.ruleForm.obedience == "충성심 강한" ? "S" : "N"] +
        [this.ruleForm.relationship == "의존적인" ? "F" : "T"] +
        [this.ruleForm.adaptability == "친화적인" ? "J" : "P"];

      const data = {
        thumbnailUrl: "",
        filePath: [],
        email: "",
        dogName: this.ruleForm.name,
        boardType: Number(this.ruleForm.type.id),
        userId: this.state.userId.userId,
        mbti: mbti,
        title: this.ruleForm.title,
        description: this.ruleForm.desc,
        colorType: Number(this.ruleForm.color.id),
        hairType: this.ruleForm.hair == "단모" ? 10 : 11,
        gender: this.ruleForm.gender == "여" ? 8 : 9,
        age: Number(this.ruleForm.age.id),
        neutralization:
          this.ruleForm.neutralization == "중성화 O" ? true : false,
        weight: Number(this.ruleForm.size.id),
        address: this.ruleForm.address
      };
      console.log(data);

      this.$refs[formName].validate(valid => {
        if (valid) {
          this.registerData(data);
          console.log(this.ruleForm);
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleRemove(file) {
      console.log(file);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleDownload(file) {
      console.log(file);
    }
  },

  setup() {
    const store = new useStore();
    const router = new useRouter();

    const state = reactive({
      board: computed(() => {
        console.log(store.getters["root/getBoardDetail"]);
        return store.getters["root/getBoardDetail"];
      }),
      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"];
      })
    });

    const registerData = function(data) {
      store
        .dispatch("root/requestRegisterBoard", data)
        .then(function(result) {
          createToast("공고가 등록되었어요 📜🐾", {
            hideProgressBar: "true",
            timeout: 4500,
            showIcon: "true",
            toastBackgroundColor: "#7eaa72",
            position: "bottom-right",
            transition: "bounce",
            type: "success"
          });
          console.log("등록 성공");
          router.push({ name: "Adopt" });
        })
        .catch(function(err) {
          createToast("공고 등록에 실패했어요 💬💦", {
            hideProgressBar: "true",
            timeout: 4500,
            showIcon: "true",
            toastBackgroundColor: "#c49d83",
            position: "bottom-right",
            transition: "bounce",
            type: "warning"
          });
          console.log(err);
        });
    };

    onMounted(() => {
      console.log("breadcrumb");
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "입양/임보 상담",
        subTitle: "입양/임보 신청서 작성"
      });
    });

    return { state, registerData };
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
  font-weight: 500;
}

.box {
  margin-top: 30px;
  margin-bottom: 40px;
  padding: 40px;
  background-color: #faf4ef;
}
</style>
