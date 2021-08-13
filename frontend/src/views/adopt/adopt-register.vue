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
            style="font-weight:600; padding-left:20px; background:linear-gradient( to top, #f0ebe0, #f6ede9 );"
          >
            📑 기본정보를 입력해주세요
          </h5>

          <el-row class="mt-4 mb-3">
            <el-col :span="12">
              <el-form-item label="제목" prop="title" label-width="30%">
                <el-input
                  style="width:65%;"
                  v-model="ruleForm.title"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label-width="30%" label="강아지 이름" prop="name">
                <el-input style="width:70%;" v-model="ruleForm.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row> </el-row>

          <el-row class="mt-3 mb-3">
            <el-col :span="10">
              <el-row>
                <el-col :span="12">
                  <el-form-item label="공고 타입" prop="type" label-width="90%">
                    <el-select
                      style="width:90%;"
                      v-model="ruleForm.type"
                      placeholder="분류"
                    >
                      <el-option label="입양" value="1"></el-option>
                      <el-option label="임보" value="2"></el-option>
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
              <el-form-item
                label="중성화 여부"
                label-width="100%"
                label-position="center"
                prop="neutralization"
              >
                <el-radio-group
                  style="width:100%;"
                  v-model="ruleForm.neutralization"
                >
                  <el-radio
                    label="O"
                    style="margin-left:10%; margin-right:10%;"
                    border
                  ></el-radio>
                  <el-radio label="X" border></el-radio>
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
            <el-col :span="5">
              <el-row>
                <el-col :span="24">
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

          <el-row class="mt-3">
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

          <h5
            class="pt-3 pb-3"
            style="font-weight:600; padding-left:20px; background:linear-gradient( to top, #f0ebe0, #f6ede9 );"
          >
            🐶 성격을 소개해주세요
          </h5>

          <el-row class="mt-4 mb-3">
            <el-col :span="12">
              <el-form-item label="활동성향" prop="energy">
                <el-radio-group v-model="ruleForm.energy">
                  <el-popover placement="top" width="200" trigger="hover">
                    <h6 style="font-weight:600">에너지 Energetic</h6>
                    활동적, 즉흥적인
                    <template #reference>
                      <el-radio label="에너지있는" border></el-radio>
                    </template>
                  </el-popover>
                  <el-popover placement="top" width="200" trigger="hover">
                    <h6 style="font-weight:600">침착한 Introversion</h6>
                    차분한, 일관적인
                    <template #reference>
                      <el-radio label="침착한" border></el-radio>
                    </template>
                  </el-popover>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="순종성향" prop="obedience">
                <el-radio-group v-model="ruleForm.obedience">
                  <el-popover placement="top" width="200" trigger="hover">
                    <h6 style="font-weight:600">충성심 강한 Supportive</h6>
                    유순한, 성실한
                    <template #reference>
                      <el-radio label="충성심 강한" border></el-radio>
                    </template>
                  </el-popover>
                  <el-popover placement="top" width="200" trigger="hover">
                    <h6 style="font-weight:600">영리한 Naughty</h6>
                    재치한, 똑똑한
                    <template #reference>
                      <el-radio label="영리한" border></el-radio>
                    </template>
                  </el-popover>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="관계성향" prop="relationship">
                <el-radio-group v-model="ruleForm.relationship">
                  <el-popover placement="bottom" width="200" trigger="hover">
                    <h6 style="font-weight:600">관계지향 Friendly</h6>
                    반려인 바라기
                    <template #reference>
                      <el-radio label="의존적인" border></el-radio>
                    </template>
                  </el-popover>
                  <el-popover placement="bottom" width="200" trigger="hover">
                    <h6 style="font-weight:600">독립지향 independenT</h6>
                    스스로 행동하는
                    <template #reference>
                      <el-radio label="독립적인" border></el-radio>
                    </template>
                  </el-popover>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="적응성향" prop="adaptability">
                <div>
                  <el-radio-group v-model="ruleForm.adaptability">
                    <el-popover placement="bottom" width="200" trigger="hover">
                      <h6 style="font-weight:600">신중한 Prudent</h6>
                      낯가리는, 조심스러운

                      <template #reference>
                        <el-radio label="낯가리는" border></el-radio>
                      </template>
                    </el-popover>
                    <el-popover placement="bottom" width="200" trigger="hover">
                      <h6 style="font-weight:600">친화적인 Jolly</h6>
                      모든 것에 호기심 가득한
                      <template #reference>
                        <el-radio label="친화적인" border></el-radio>
                      </template>
                    </el-popover>
                  </el-radio-group>
                </div>
              </el-form-item>
            </el-col>
          </el-row>
          <div class="mb-3" style="margin-top:100px;"></div>
          <div
            class="pt-3 pb-3"
            style="font-weight:600; padding-left:20px; background:linear-gradient( to top, #f0ebe0, #f6ede9 );"
          >
            <span style="font-size: 1.25rem; font-weight:600">
              📷 사진을 업로드 해주세요
            </span>
            <span> (최대 5장)</span>
          </div>
          <el-row class="mt-4">
            <div class="form-check mb-3">
              <input
                v-model="state.attachFile"
                class="form-check-input"
                type="checkbox"
                value=""
                id="chkFileUploadInsert"
              />
              <label class="form-check-label" for="chkFileUploadInsert"
                >파일 추가</label
              >
            </div>
            <div
              class="mb-3"
              v-show="state.attachFile"
              id="imgFileUploadInsertWrapper"
            >
              <input
                @change="changeFile"
                type="file"
                id="inputFileUploadInsert"
                multiple
              />
              <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper">
                <img
                  v-for="(file, index) in state.fileList"
                  v-bind:src="file"
                  v-bind:key="index"
                />
              </div>
            </div>

            <el-form-item props="filePath">
              <el-upload
                action="#"
                list-type="picture-card"
                :auto-upload="false"
                :on-remove="handleRemove"
                :file-list="ruleForm.filePath"
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
            </el-form-item>
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
  name: "AdoptReigster",
  components: {
    BreadCrumb
  },
  data() {
    return {
      ruleForm: {
        sido: "",
        gugun: "",
        type: "",
        title: "",
        name: "",
        gender: "",
        dogType: "",
        color: "",
        age: "",
        size: "",
        neutralization: "",
        address: "",
        desc: "",
        energy: "",
        obedience: "",
        relationship: "",
        adaptability: "",

        disabled: false,
        filePath: []
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
        neutralization: [
          {
            required: true,
            message: "중성화여부를 선택해주세요",
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
        ],
        file: [
          {
            required: true,
            message: "사진을 최소 1장 업로드해주세요.",
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

      var formData = new FormData();

      const data = {
        fileList: [],
        dogName: this.ruleForm.name,
        boardType: Number(this.ruleForm.type),
        userId: this.state.userId.userId,
        mbti: mbti,
        title: this.ruleForm.title,
        description: this.ruleForm.desc,
        colorType: Number(this.ruleForm.color),
        dogType: this.ruleForm.dogType,
        gender: this.ruleForm.gender == "여" ? 8 : 9,
        age: Number(this.ruleForm.age),
        neutralization: this.ruleForm.neutralization == "O" ? true : false,
        weight: Number(this.ruleForm.size),
        gugun: this.ruleForm.gugun
      };
      console.log(data);

      this.$refs[formName].validate(valid => {
        this.registerData(data);
        console.log(this.ruleForm);
        // if (valid) {
        //   this.registerData(data);
        //   console.log(this.ruleForm);
        // } else {
        //   console.log("error submit!!");
        //   return false;
        // }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    }
  },

  setup() {
    const store = new useStore();
    const router = new useRouter();

    const state = reactive({
      attachFile: false,
      fileList: [],
      dogTypeList: [],
      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"];
      }),
      sidoList: [],
      gugunList: [{ id: 0, name: "시/도를 먼저 선택해주세요" }]
    });

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

    const registerData = function(data) {
      store
        .dispatch("root/requestRegisterBoard", data)
        .then(function(result) {
          console.log("등록 성공");

          store
            .dispatch("requestBoardDetail", {
              boardId: result.data.boardId,
              userId: state.userId.userId
            })
            .then(function(result) {
              console.log(result);

              const boardDetail = {
                boardId: result.data.dogInformation.boardId.id,
                boardType: result.data.dogInformation.boardId.type,
                thumbnailUrl: result.data.dogInformation.boardId.thumbnailUrl,
                title: result.data.dogInformation.boardId.title,
                address: result.data.dogInformation.address,
                mbti: result.data.dogInformation.mbti,
                colorType: result.data.dogInformation.colorType,
                gender: result.data.dogInformation.gender,
                dogType: result.data.dogInformation.dogType,
                neutralization: result.data.dogInformation.neutralization,
                writer: result.data.writer,
                weight: result.data.dogInformation.weight,
                ageType: result.data.dogInformation.age,
                regDate: result.data.dogInformation.boardId.regDate,
                fileList: result.data.boardImageList,
                isOwner: result.data.owner,
                gugun: result.data.dogInformation.gugun,
                sido: result.data.dogInformation.gugun.sidoCode,
                description: result.data.dogInformation.description,
                dogName: result.data.dogInformation.dogName,
                isBookmarked: result.data.bookmarked
              };
              createToast("공고가 등록되었어요 📜🐾", {
                hideProgressBar: "true",
                timeout: 4500,
                showIcon: "true",
                toastBackgroundColor: "#7eaa72",
                position: "bottom-left",
                transition: "bounce",
                type: "success"
              });
              store.commit("root/setBoardDetail", boardDetail);
              router.push({ name: "AdoptDetail" });
            })
            .catch(function(err) {
              console.log(err);
            });
        })
        .catch(function(err) {
          createToast("공고 등록에 실패했어요 💬💦", {
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

    //파일 업로드 시 호출
    const changeFile = function(fileEvent) {
      if (fileEvent.target.files && fileEvent.target.files.length > 0) {
        for (var i = 0; i < fileEvent.target.files.length; i++) {
          const file = fileEvent.target.files[i];
          state.fileList.push(URL.createObjectURL(file));
        }
      }
    };

    onMounted(() => {
      console.log("breadcrumb");
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "입양/임보",
        path: "/adopt",
        subTitle: "입양/임보 공고 작성"
      });
      readDogTypeList();
      window.scrollTo(0, 0);
    });

    return { state, gugunList, changeFile, registerData, readDogTypeList };
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

.thumbnail-wrapper {
  margin-top: 5px;
}

.thumbnail-wrapper img {
  width: 100px !important;
  margin-right: 5px;
  max-width: 100%;
}
</style>
