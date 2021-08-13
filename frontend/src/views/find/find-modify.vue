<template>
  <div class="main-body main-padding">
    <el-card
      class="box-card"
      style="width:100%; height:auto;  border:none;"
      shadow="none"
    >
      <bread-crumb></bread-crumb>

      <div>
        <el-form
          style=" margin:100px; padding-left:10px; padding-right:10px;"
          label-position="left"
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
        >
          <h5
            class="pt-3 pb-3"
            style="font-weight:600; padding-left:20px; background:linear-gradient( to bottom,#f0ebe0, #f6ede9 );"
          >
            📑 기본정보를 입력해주세요
          </h5>

          <el-row class="mt-4 mb-3">
            <el-col :span="14">
              <el-form-item label="제목" prop="title" label-width="20%">
                <el-input
                  style="width:70%;"
                  v-model="ruleForm.title"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="공고 타입" prop="type" label-width="40%">
                <el-select
                  style="width:60%;"
                  v-model="ruleForm.type"
                  placeholder="분류"
                >
                  <el-option label="실종" value="3"></el-option>
                  <el-option label="보호" value="4"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row> </el-row>

          <el-row class="mt-3 mb-3">
            <el-col :span="10">
              <el-row>
                <el-col :span="12">
                  <el-form-item label="컬러" prop="color" label-width="100%">
                    <el-select
                      style="width:90%;"
                      v-model="ruleForm.color"
                      placeholder="컬러"
                    >
                      <el-option label="White" value="12"></el-option>
                      <el-option label="Beige" value="13"></el-option>
                      <el-option label="Gray" value="14"></el-option>
                      <el-option label="Brown" value="15"></el-option>
                      <el-option label="Black" value="16"></el-option>
                      <el-option label="기타" value="17"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="연령대" prop="age">
                    <el-select
                      style="width:90%;"
                      v-model="ruleForm.age"
                      placeholder="연령대"
                    >
                      <el-option label="Puppy(~ 6개월)" value="4"></el-option>
                      <el-option
                        label="Junior(7개월 ~ 2살)"
                        value="5"
                      ></el-option>
                      <el-option label="Adult(3살 ~ 8살)" value="6"></el-option>
                      <el-option label="Senior(9살 ~)" value="7"></el-option>
                      <el-option label="기타" value="17"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-col>
            <el-col :span="10">
              <el-row>
                <el-col :span="12">
                  <el-form-item label="크기" prop="size">
                    <el-select
                      style="width:90%;"
                      v-model="ruleForm.size"
                      placeholder="크기"
                    >
                      <el-option label="소(8kg 미만)" value="1"></el-option>
                      <el-option
                        label="중(8kg-18kg 미만)"
                        value="2"
                      ></el-option>
                      <el-option label="대(18kg 이상)" value="3"></el-option>
                      <el-option label="기타" value="17"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label-width="100%" label="품종" prop="dogType">
                    <el-select
                      v-model="ruleForm.dogType"
                      placeholder="품종"
                      style="width:90%;"
                    >
                      <el-option
                        v-for="dog in state.dogTypeList"
                        :key="dog.id"
                        :label="dog.name"
                        :value="dog.id"
                      >
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-col>
            <el-col :span="4">
              <el-form-item label="성별" label-width="100%" prop="gender">
                <el-radio-group v-model="ruleForm.gender" style="width:100%;">
                  <el-radio
                    style="margin-left:10%; margin-right:10%;"
                    label="남"
                    border
                  ></el-radio>
                  <el-radio label="여" border></el-radio>
                </el-radio-group>
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
                      v-model="ruleForm.sido"
                      placeholder="시/도"
                      style="width:95% ;"
                      :change="gugunList(ruleForm.sido)"
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
                      v-model="ruleForm.gugun"
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
            <el-col :span="9">
              <el-form-item label="상세주소" prop="address" label-width="100%">
                <el-input
                  style="width:100%;"
                  v-model="ruleForm.address"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row class="mt-3 mb-3">
            <el-form-item label="부가 설명" prop="desc">
              <el-input
                type="textarea"
                :rows="7"
                maxlength="1000"
                v-model="ruleForm.desc"
              ></el-input>
            </el-form-item>
          </el-row>

          <div class="mb-3" style="margin-top:100px;"></div>
          <div
            class="pt-3 pb-3"
            style="font-weight:600; padding-left:20px; background:linear-gradient( to bottom,#f0ebe0, #f6ede9 );"
          >
            <span style="font-size: 1.25rem; font-weight:600">
              📷 사진을 업로드 해주세요
            </span>
            <span> (최대 5장)</span>
          </div>

          <el-row class="mt-4">
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
        sido: this.state.board.sido,
        gugun: this.state.board.gugun,
        type: this.state.board.boardType,
        title: this.state.board.title,
        name: this.state.board.dogName,
        gender: this.state.board.gender.name,
        dogType: this.state.board.dogType,
        color: this.state.board.colorType,
        age: this.state.board.ageType,
        size: this.state.board.weight,

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
        dogType: [
          {
            required: true,
            message: "품종을 선택해주세요",
            trigger: "blur"
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
        address: [
          {
            required: true,
            message: "상세 주소를 입력해주세요",
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

        title: this.ruleForm.title,
        description: this.ruleForm.desc,
        colorType: Number(this.ruleForm.color),
        dogType: this.ruleForm.dogType,
        gender:
          this.ruleForm.gender == "여"
            ? 8
            : this.ruleForm.gender == "남"
            ? 9
            : 17,
        age: Number(this.ruleForm.age),
        address: this.ruleForm.address,
        weight: Number(this.ruleForm.size),
        gugun: this.ruleForm.gugun
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
      dogTypeList: [],
      board: computed(() => {
        console.log(store.getters["root/getBoardDetail"]);
        return store.getters["root/getBoardDetail"];
      }),

      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"];
      }),
      sidoList: [],
      gugunList: [{ id: 0, name: "시/도를 먼저 선택해주세요" }]
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
            timeout: 4500,
            showIcon: "true",
            toastBackgroundColor: "#7eaa72",
            position: "bottom-left",
            transition: "bounce",
            type: "success"
          });
          console.log("수정 성공");
          router.push({ name: "FindDetail" });
        })
        .catch(function(err) {
          createToast("공고 수정에 실패했어요 😱💦", {
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

    //강아지 품종 데이터 읽어오기
    const readDogTypeList = function() {
      store.dispatch("root/requestDogTypeList").then(function(result) {
        console.log("dogType:", result);
        state.dogTypeList = result.data.dogTypeList;
        state.dogTypeList.push({ id: 17, name: "기타" });
      });
    };

    //시도 리스트 가져오기
    store
      .dispatch("root/requestSidoCodeList")
      .then(function(result) {
        console.log("call : sidocode");
        state.sidoList = result.data.sidoList;
      })
      .catch(function(error) {
        console.log(error);
      });

    //시도에 맞는 구군 리스트 가져오기
    const gugunList = function(selectedSidoCode) {
      console.log(selectedSidoCode);

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
      console.log("breadcrumb");
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "실종/보호",
        path: "/find",
        subTitle: "실종/보호 공고 수정"
      });
      readDogTypeList();
      window.scrollTo(0, 0);
    });

    return { state, modifyData, gugunList, readDogTypeList };
  }
};
</script>

<style scoped>
.main-body {
  width: 100%;
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 10%;
}
.main-padding {
  padding-top: 50px;
  padding-bottom: 50px;
}

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
  background-color: #755744;
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

:deep(.el-textarea__inner) {
  resize: none;
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
