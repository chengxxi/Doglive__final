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
              <el-form-item label="현재 주소" prop="address">
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
                  <el-option
                    label="Puppy(~ 6개월)"
                    value="{ id: 4, name: 'Puppy(~ 6개월)' }"
                  ></el-option>
                  <el-option
                    label="Junior(7개월 ~ 2살)"
                    value="{ id: 5, name: 'Junior(7개월 ~ 2살)' }"
                  ></el-option>
                  <el-option
                    label="Adult(3살 ~ 8살)"
                    value="{ id: 6, name: 'Adult(3살 ~ 8살)' }"
                  ></el-option>
                  <el-option
                    label="Senior(9살 ~)"
                    value="{ id: 7, name: 'Senior(9살 ~)' }"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="크기" prop="size">
                <el-select v-model="ruleForm.size" placeholder="소(8kg 미만)">
                  <el-option
                    label="소(8kg 미만)"
                    value="{ id: 1, name: '소(8kg 미만)' }"
                  ></el-option>
                  <el-option
                    label="중(8kg-18kg 미만)"
                    value="{ id: 2, name: '중(8kg-18kg 미만)' }"
                  ></el-option>
                  <el-option
                    label="대(18kg 이상)"
                    value="{ id: 3, name: '대(18kg 이상)' }"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="컬러" prop="color">
                <el-select v-model="ruleForm.color" placeholder="White">
                  <el-option
                    label="White"
                    value="{ id: 12, name: 'White' }"
                  ></el-option>
                  <el-option
                    label="Beige"
                    value="{ id: 13, name: 'Beige' }"
                  ></el-option>
                  <el-option
                    label="Gray"
                    value="{ id: 14, name: 'Gray' }"
                  ></el-option>
                  <el-option
                    label="Brown"
                    value="{ id: 15, name: 'Brown' }"
                  ></el-option>
                  <el-option
                    label="Black"
                    value="{ id: 16, name: 'Black' }"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="공고 타입" prop="type">
                <el-select v-model="ruleForm.type" placeholder="입양">
                  <el-option
                    label="입양"
                    value="{ id: 1, name: '입양' }"
                  ></el-option>
                  <el-option
                    label="임보"
                    value="{ id: 2, name: '임보' }"
                  ></el-option>
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
          <div class="mb-3" style="margin-top:100px;"></div>
          <span style="font-size: 1.25rem; font-weight:600">
            📷 사진을 업로드 해주세요
          </span>
          <span> (최대 5장)</span>
          <el-divider />
          <el-row>
            <el-upload
              action="#"
              list-type="picture-card"
              :auto-upload="false"
              limit="5"
              on-exceed=""
            >
              <template #default>
                <i class="el-icon-plus"></i>
              </template>
              <template #file="{file}">
                <div>
                  <img
                    class="el-upload-list__item-thumbnail"
                    :src="file.url"
                    alt=""
                  />
                  <span class="el-upload-list__item-actions">
                    <span
                      class="el-upload-list__item-preview"
                      @click="handlePictureCardPreview(file)"
                    >
                      <i class="el-icon-zoom-in"></i>
                    </span>
                    <span
                      v-if="!disabled"
                      class="el-upload-list__item-delete"
                      @click="handleDownload(file)"
                    >
                      <i class="el-icon-download"></i>
                    </span>
                    <span
                      v-if="!disabled"
                      class="el-upload-list__item-delete"
                      @click="handleRemove(file)"
                    >
                      <i class="el-icon-delete"></i>
                    </span>
                  </span>
                </div>
              </template>
            </el-upload>
            <el-dialog v-model="dialogVisible">
              <img width="100%" :src="dialogImageUrl" alt="" />
            </el-dialog>
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
  name: "FindReigster",
  components: {
    BreadCrumb
  },
  data() {
    return {
      ruleForm: {
        type: "",
        title: "",
        name: "",
        gender: "",
        hair: "",
        color: "",
        age: "",
        size: "",
        neutralization: "",
        address: "",
        desc: "",
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
          createToast("공고 등록에 실패했어요 😱💦", {
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
        title: "실종/보호",
        subTitle: "실종/보호 공고 작성"
      });
    });

    return { state, registerData };
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
