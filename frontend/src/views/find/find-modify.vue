<template>
  <div class="main-body main-padding">
    <el-card
      class="box-card"
      style="width:100%; height:auto;  border:none;"
      shadow="hover"
    >
      <bread-crumb></bread-crumb>

      <div>
        <el-form
          style=" margin:100px;"
          label-position="left"
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="120px"
        >
          <h5 class="mt-1 mb-3" style="font-weight:600">
            📑 기본정보를 입력해주세요
          </h5>
          <el-divider />
          <el-row class="mt-3 mb-3">
            <el-form-item label="제목" prop="title" style="width:100%">
              <el-input v-model="ruleForm.title"></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="강아지 이름" prop="name" style="width:95%">
                <el-input v-model="ruleForm.name"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="실종/보호 주소" prop="address">
                <el-input
                  placeholder="ㅇㅇ시 ㅇㅇ구"
                  v-model="ruleForm.address"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row class="mt-3 mb-3">
            <el-col :span="12">
              <el-form-item label="연령대" prop="age">
                <el-select v-model="ruleForm.age" placeholder="Puppy(~ 6개월)">
                  <el-option label="Puppy(~ 6개월)" value="4"></el-option>
                  <el-option label="Junior(7개월 ~ 2살)" value="5"></el-option>
                  <el-option label="Adult(3살 ~ 8살)" value="6"></el-option>
                  <el-option label="Senior(9살 ~)" value="7"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="크기" prop="size">
                <el-select v-model="ruleForm.size" placeholder="소(8kg 미만)">
                  <el-option label="소(8kg 미만)" value="1"></el-option>
                  <el-option label="중(8kg-18kg 미만)" value="2"></el-option>
                  <el-option label="대(18kg 이상)" value="3"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="컬러" prop="color">
                <el-select v-model="ruleForm.color" placeholder="White">
                  <el-option label="White" value="12"></el-option>
                  <el-option label="Beige" value="13"></el-option>
                  <el-option label="Gray" value="14"></el-option>
                  <el-option label="Brown" value="15"></el-option>
                  <el-option label="Black" value="16"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="공고 타입" prop="type">
                <el-select v-model="ruleForm.type" placeholder="입양">
                  <el-option label="실종" value="3"></el-option>
                  <el-option label="보호" value="4"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="mt-3 mb-3">
            <el-col :span="12">
              <el-form-item label="성별" prop="gender">
                <el-radio-group v-model="ruleForm.gender">
                  <el-radio label="남"></el-radio>
                  <el-radio label="여"></el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="품종" prop="hair">
                <el-radio-group v-model="ruleForm.hair">
                  <el-radio label="장모"></el-radio>
                  <el-radio label="단모"></el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row class="mt-3 mb-3">
            <el-form-item label="부가 설명" prop="desc">
              <el-input
                type="textarea"
                :rows="7"
                maxlength="1000"
                show-word-limit
                v-model="ruleForm.desc"
              ></el-input>
            </el-form-item>
          </el-row>

          <el-row
            class="mt-5"
            style=" display: flex;
  justify-content: center;"
          >
            <el-button type="primary" @click="submitForm('ruleForm')"
              >수정</el-button
            >
            <el-button @click="resetForm('ruleForm')">초기화</el-button>
          </el-row>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import BreadCrumb from "./components/bread-crumb.vue";
import { computed, reactive, onMounted } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "FindModify",
  components: {
    BreadCrumb
  },
  data() {
    return {
      ruleForm: {
        type: this.state.board.boardType.id,
        title: this.state.board.title,
        name: this.state.board.dogName,
        gender: this.state.board.gender.name,
        hair: this.state.board.hairType.name,
        color: this.state.board.colorType.id,
        age: this.state.board.ageType.id,
        size: this.state.board.weight.id,
        neutralization:
          this.state.board.neutralization == true ? "중성화 O" : "중성화 X",
        address: this.state.board.address,
        desc: this.state.board.description,
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
        title: [
          { required: true, message: "제목을 입력해주세요.", trigger: "blur" },
          {
            min: 3,
            max: 20,
            message: "3글자 이상, 20글자 이하로 입력해주세요.",
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
            message: "실종/보호 장소를 입력해주세요",
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
        ]
      }
    };
  },

  methods: {
    submitForm(formName) {
      const data = {
        thumbnailUrl: "",
        filePath: [],
        dogName: this.ruleForm.name,
        boardType: Number(this.ruleForm.type),
        userId: this.state.userId.userId,
        mbti: "",
        title: this.ruleForm.title,
        description: this.ruleForm.desc,
        colorType: Number(this.ruleForm.color),
        hairType: this.ruleForm.hair == "단모" ? 10 : 11,
        gender: this.ruleForm.gender == "여" ? 8 : 9,
        age: Number(this.ruleForm.age),
        neutralization:
          this.ruleForm.neutralization == "중성화 O" ? true : false,
        weight: Number(this.ruleForm.size),
        address: this.ruleForm.address
      };
      console.log(data);

      this.$refs[formName].validate(valid => {
        if (valid) {
          this.modifyData(data);
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

    const modifyData = function(data) {
      store
        .dispatch("root/requestModifyBoard", {
          boardId: state.board.boardId,
          data: data
        })
        .then(function(result) {
          createToast("공고가 수정되었어요 📜🐾", {
            hideProgressBar: "true",
            timeout: 4000,
            showIcon: "true",
            toastBackgroundColor: "#7eaa72",
            position: "bottom-right",
            transition: "bounce",
            type: "success"
          });
          console.log("수정 성공");
          router.push({ name: "Adopt" });
        })
        .catch(function(err) {
          createToast("공고 수정에 실패했어요 💬💦", {
            hideProgressBar: "true",
            timeout: 4000,
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
        title: "실종/보호",
        subTitle: "실종/보호 공고 수정"
      });
    });

    return { state, modifyData };
  }
};
</script>

<style scoped>
:deep(.el-radio__inner:hover) {
  border-color: #755744;
}
:deep(.el-radio__input.is-checked + .el-radio__label) {
  color: #755744;
}

:deep(.el-radio__input.is-checked .el-radio__inner) {
  border-color: #755744;
  background-color: #755744;
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
</style>
