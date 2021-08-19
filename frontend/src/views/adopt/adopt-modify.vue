<template>
  <div class="main-body main-padding">
    <div style="width:100%; ">
      <bread-crumb></bread-crumb>
      <el-card
        class="box-card"
        style="width:100%; height:auto;  border:none;"
        shadow="none"
      >
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
              style="font-weight:600; padding-left:20px; background:linear-gradient( to top,  #f3ede7, #f5e9e4 );"
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
                  <el-input
                    style="width:70%;"
                    v-model="ruleForm.name"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row> </el-row>

            <el-row class="mt-3 mb-3">
              <el-col :span="10">
                <el-row>
                  <el-col :span="12">
                    <el-form-item
                      label="공고 타입"
                      prop="type"
                      label-width="90%"
                    >
                      <el-select
                        style="width:90%;"
                        v-model="state.board.boardType.id"
                        placeholder="분류"
                      >
                        <el-option label="입양" :value="1"></el-option>
                        <el-option label="임보" :value="2"></el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="연령대" prop="age">
                      <el-select
                        style="width:90%;"
                        v-model="state.board.ageType.id"
                        placeholder="연령대"
                      >
                        <el-option
                          label="Puppy(~ 6개월)"
                          :value="4"
                        ></el-option>
                        <el-option
                          label="Junior(7개월 ~ 2살)"
                          :value="5"
                        ></el-option>
                        <el-option
                          label="Adult(3살 ~ 8살)"
                          :value="6"
                        ></el-option>
                        <el-option label="Senior(9살 ~)" :value="7"></el-option>
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
                        v-model="state.board.weight.id"
                        placeholder="크기"
                      >
                        <el-option label="소(8kg 미만)" :value="1"></el-option>
                        <el-option
                          label="중(8kg-18kg 미만)"
                          :value="2"
                        ></el-option>
                        <el-option label="대(18kg 이상)" :value="3"></el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item
                      label-width="100%"
                      label="품종"
                      prop="dogType"
                    >
                      <el-select
                        v-model="state.board.dogType.id"
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
                        v-model="state.board.sido.id"
                        placeholder="시/도"
                        style="width:95% ;"
                        @change="gugunList(state.board.sido.id)"
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
                      label="(구/군)"
                      prop="gugun"
                      label-width="100%"
                    >
                      <el-select
                        style="width:95% ;"
                        v-model="state.board.gugun.id"
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
                        v-model="state.board.colorType.id"
                        placeholder="컬러"
                      >
                        <el-option label="White" :value="12"></el-option>
                        <el-option label="Beige" :value="13"></el-option>
                        <el-option label="Gray" :value="14"></el-option>
                        <el-option label="Brown" :value="15"></el-option>
                        <el-option label="Black" :value="16"></el-option>
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
              style="font-weight:600; padding-left:20px; background:linear-gradient( to top,  #f3ede7, #f5e9e4 );"
            >
              🐶 성격을 소개해주세요
            </h5>

            <el-row class="mt-4 mb-3" style="margin-left:5%; margin:right:5%;">
              <el-col :span="12">
                <el-form-item label="활동성향" prop="energy" label-width="30%">
                  <el-radio-group v-model="ruleForm.energy">
                    <el-popover placement="top" width="200" trigger="hover">
                      <h6 style="font-weight:600">에너지 Energetic</h6>
                      활동적, 즉흥적인
                      <template #reference>
                        <el-radio label="에너지있는" border></el-radio>
                      </template>
                    </el-popover>
                    <el-popover placement="top" width="200" trigger="hover">
                      <h6 style="font-weight:600 ;">
                        침착한 Introversion
                      </h6>
                      차분한, 일관적인
                      <template #reference>
                        <el-radio label="침착한" border></el-radio>
                      </template>
                    </el-popover>
                  </el-radio-group>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item
                  label="순종성향"
                  prop="obedience"
                  label-width="30%"
                >
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
            <el-row style="margin-left:5%; margin:right:5%;">
              <el-col :span="12">
                <el-form-item
                  label="관계성향"
                  prop="relationship"
                  label-width="30%"
                >
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
                <el-form-item
                  label="적응성향"
                  prop="adaptability"
                  label-width="30%"
                >
                  <div>
                    <el-radio-group v-model="ruleForm.adaptability">
                      <el-popover
                        placement="bottom"
                        width="200"
                        trigger="hover"
                      >
                        <h6 style="font-weight:600">신중한 Prudent</h6>
                        낯가리는, 조심스러운

                        <template #reference>
                          <el-radio label="낯가리는" border></el-radio>
                        </template>
                      </el-popover>
                      <el-popover
                        placement="bottom"
                        width="200"
                        trigger="hover"
                      >
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
              style="font-weight:600; padding-left:20px; background:linear-gradient( to top,  #f3ede7, #f5e9e4 );"
            >
              <span style="font-size: 1.25rem; font-weight:600">
                📷 사진을 업로드 해주세요
              </span>
              <span> (최대 5장 / 최소 2장)</span>
            </div>
            <el-row class="mt-4  mb-3">
              <div class="mb-3" id="imgFileUploadInsertWrapper">
                <div
                  id="imgFileUploadInsertThumbnail"
                  class="thumbnail-wrapper"
                >
                  <el-image
                    style="width: 200px; height:200px; box-shadow:0 2px 12px 0 rgb(0 0 0 / 10%); cursor:pointer; position:relative; margin-right:20px; border-radius:20px; float:left;"
                    v-for="(file, index) in state.board.fileList"
                    v-bind:src="file"
                    v-bind:key="index"
                    @click="deleteOriginFile(index)"
                    :fit="fit"
                    :hover="state.hover"
                  ></el-image>
                  <el-image
                    style="width: 200px; height:200px; box-shadow:0 2px 12px 0 rgb(0 0 0 / 10%); cursor:pointer; position:relative; margin-right:20px; border-radius:20px; float:left;"
                    v-for="(file, index) in state.thumbnailList"
                    v-bind:src="file"
                    v-bind:key="index"
                    @click="deleteNewFile(index)"
                    :fit="fit"
                    :hover="state.hover"
                  ></el-image>

                  <input
                    @change="changeFile"
                    type="file"
                    id="inputFileUploadInsert"
                    style="display:none"
                    multiple
                  />
                  <div style="float:left;">
                    <label for="inputFileUploadInsert" style="cursor:pointer;">
                      <div
                        style="background:linear-gradient( to top, #f3ede7, #f5e9e4 );
                      text-align:center;
display:table-cell;
vertical-align:middle; box-shadow:0 2px 12px 0 rgb(0 0 0 / 10%);
                      width:200px; height:200px; border-radius:20px;
                    "
                      >
                        <i
                          class="el-icon-plus "
                          style="margin-left : 10px;
                    font-size:40px; color:#D8D8D8;"
                        />
                      </div>
                    </label>
                  </div>
                </div>
              </div>
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
  name: "AdoptModify",
  components: {
    BreadCrumb
  },
  data() {
    return {
      ruleForm: {
        sido: this.state.board.sido.id,
        gugun: this.state.board.gugun.id,
        type: this.state.board.boardType.id,
        title: this.state.board.title,
        name: this.state.board.dogName,
        gender: this.state.board.gender.name,
        dogType: this.state.board.dogType.id,
        color: this.state.board.colorType.id,
        age: this.state.board.ageType.id,
        size: this.state.board.weight.id,
        neutralization: this.state.board.neutralization == true ? "O" : "X",

        desc: this.state.board.description,
        energy:
          this.state.board.mbti.indexOf("E") == 0 ? "에너지있는" : "침착한",
        obedience:
          this.state.board.mbti.indexOf("S") == 1 ? "충성심 강한" : "영리한",
        relationship:
          this.state.board.mbti.indexOf("F") == 2 ? "의존적인" : "독립적인",
        adaptability:
          this.state.board.mbti.indexOf("P") == 3 ? "낯가리는" : "친화적인"
      },
      rules: {
        type: [
          {
            required: true,
            message: "공고 타입을 선택해주세요",
            trigger: "change"
          }
        ],
        title: [
          {
            required: true,
            message: "제목을 입력해주세요.",
            trigger: "change"
          },
          {
            min: 3,
            max: 20,
            message: "3글자 이상, 20글자 이하로 입력해주세요.",
            trigger: "change"
          }
        ],
        name: [
          {
            required: true,
            message: "이름을 입력해주세요.",
            trigger: "change"
          },
          {
            min: 1,
            max: 10,
            message: "10글자 이하로 입력해주세요.",
            trigger: "change"
          }
        ],
        gender: [
          { required: true, message: "성별을 선택해주세요", trigger: "change" }
        ],
        dogType: [
          {
            required: true,
            message: "품종을 선택해주세요",
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
        color: [
          {
            required: true,
            message: "컬러타입을 선택해주세요",
            trigger: "change"
          }
        ],
        age: [
          {
            required: true,
            message: "연령대를 선택해주세요",
            trigger: "change"
          }
        ],
        size: [
          { required: true, message: "크기를 선택해주세요", trigger: "change" }
        ],
        neutralization: [
          {
            required: true,
            message: "중성화여부를 선택해주세요",
            trigger: "change"
          }
        ],

        desc: [
          {
            required: true,
            message: "설명을 입력해주세요 (소개글, 특이사항 등)",
            trigger: "change"
          },
          {
            min: 50,
            max: 1000,
            message: "50글자 이상, 1000글자 이하로 입력해주세요.",
            trigger: "change"
          }
        ],
        energy: [
          {
            required: true,
            message: "활동성향을 관찰 후 선택해주세요",
            trigger: "change"
          }
        ],
        obedience: [
          {
            required: true,
            message: "순종성향을 관찰 후 선택해주세요",
            trigger: "change"
          }
        ],
        relationship: [
          {
            required: true,
            message: "관계성향을 관찰 후 선택해주세요",
            trigger: "change"
          }
        ],
        adaptability: [
          {
            required: true,
            message: "적응성향을 관찰 후 선택해주세요",
            trigger: "change"
          }
        ]
      }
    };
  },

  methods: {
    submitForm(formName) {
      if (this.state.board.fileList.length + this.state.newAddFile.length > 5) {
        createToast("사진은 5장까지만 업로드 가능해요 💬💦", {
          hideProgressBar: "true",
          timeout: 4500,
          showIcon: "true",
          toastBackgroundColor: "#c49d83",
          position: "bottom-left",
          transition: "bounce",
          type: "warning"
        });
      } else if (
        this.state.board.fileList.length + this.state.newAddFile.length >=
        2
      ) {
        const mbti =
          [this.ruleForm.energy == "에너지있는" ? "E" : "I"] +
          [this.ruleForm.obedience == "충성심 강한" ? "S" : "N"] +
          [this.ruleForm.relationship == "의존적인" ? "F" : "T"] +
          [this.ruleForm.adaptability == "친화적인" ? "J" : "P"];

        const data = {
          mbti: mbti,
          title: this.ruleForm.title,
          dogName: this.ruleForm.name,
          gender: this.ruleForm.gender,
          desc: this.ruleForm.desc,
          neutralization: this.ruleForm.neutralization
        };
        this.$refs[formName].validate(valid => {
          if (valid) {
            this.modifyData(data);
          } else {
            createToast("작성하지 않은 항목이 있어요 💬💦", {
              hideProgressBar: "true",
              timeout: 4500,
              showIcon: "true",
              toastBackgroundColor: "#c49d83",
              position: "bottom-left",
              transition: "bounce",
              type: "warning"
            });
          }
        });
      } else {
        createToast("사진은 두 장 이상 업로드해주세요 💬💦", {
          hideProgressBar: "true",
          timeout: 4500,
          showIcon: "true",
          toastBackgroundColor: "#c49d83",
          position: "bottom-left",
          transition: "bounce",
          type: "warning"
        });
      }
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },

  setup() {
    const store = new useStore();
    const router = new useRouter();

    const state = reactive({
      board: store.getters["root/getBoardDetail"],
      deleteList: [],
      newAddFile: [],
      thumbnailList: [],
      dogTypeList: [],
      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"];
      }),
      sidoList: [],
      gugunList: []
    });

    const modifyData = function(data) {
      if (state.board.gugun.id == null) {
        createToast("작성하지 않은 항목이 있어요 💬💦", {
          hideProgressBar: "true",
          timeout: 4500,
          showIcon: "true",
          toastBackgroundColor: "#c49d83",
          position: "bottom-left",
          transition: "bounce",
          type: "warning"
        });
      } else {
        const formData = new FormData();
        formData.append("userId", state.userId.userId);
        formData.append("boardType", state.board.boardType.id);
        formData.append("dogName", data.dogName);
        formData.append("mbti", data.mbti);
        formData.append("title", data.title);
        formData.append("description", data.desc);
        formData.append("colorType", state.board.colorType.id);
        formData.append("age", state.board.ageType.id);
        formData.append("dogType", state.board.dogType.id);
        formData.append("gender", data.gender == "여" ? 8 : 9);
        formData.append(
          "neutralization",
          data.neutralization == "O" ? true : false
        );
        formData.append("weight", state.board.weight.id);
        formData.append("gugun", state.board.gugun.id);

        const cnt = this.state.deleteList.length;
        for (var i = 0; i < cnt; i++) {
          formData.append("delList", this.state.deleteList[i]);
        }

        for (var j = 0; j < this.state.newAddFile.length; j++) {
          formData.append("fileList", this.state.newAddFile[j]);
        }

        store
          .dispatch("root/requestModifyBoard", {
            boardId: state.board.boardId,
            data: formData
          })
          .then(function(result) {
            store
              .dispatch("root/requestBoardDetail", {
                boardId: state.board.boardId,
                userId: state.userId.userId
              })
              .then(function(result) {
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

                createToast("공고가 수정되었어요 📜🐾", {
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
      }
    };

    //시도 리스트 가져오기
    store
      .dispatch("root/requestSidoCodeList")
      .then(function(result) {
        state.sidoList = result.data.sidoList;
      })
      .catch(function(error) {
        console.log(error);
      });

    //강아지 품종 데이터 읽어오기
    const readDogTypeList = function() {
      store.dispatch("root/requestDogTypeList").then(function(result) {
        state.dogTypeList = result.data.dogTypeList;
        state.dogTypeList.push({ id: 17, name: "기타" });
      });
    };

    //시도에 맞는 구군 리스트 가져오기
    const gugunList = function(selectedSidoCode) {
      state.board.gugun = {};

      store
        .dispatch("root/requestGugunCodeList", selectedSidoCode)
        .then(function(result) {
          state.gugunList = result.data.gugunList;
        })
        .catch(function(error) {
          console.log(error);
        });
    };

    const gugunInitList = function(selectedSidoCode) {
      store
        .dispatch("root/requestGugunCodeList", selectedSidoCode)
        .then(function(result) {
          state.gugunList = result.data.gugunList;
        })
        .catch(function(error) {
          console.log(error);
        });
    };

    //파일 업로드 시 호출
    const changeFile = function(fileEvent) {
      if (
        fileEvent.target.files &&
        fileEvent.target.files[0].size > 3 * 1024 * 1024
      ) {
        createToast("파일 사이즈가 3MB를 넘어요 💬💦", {
          hideProgressBar: "true",
          timeout: 4500,
          showIcon: "true",
          toastBackgroundColor: "#c49d83",
          position: "bottom-left",
          transition: "bounce",
          type: "warning"
        });
      } else if (fileEvent.target.files && fileEvent.target.files.length > 0) {
        for (var i = 0; i < fileEvent.target.files.length; i++) {
          const file = fileEvent.target.files[i];
          state.thumbnailList.push(URL.createObjectURL(file));
          state.newAddFile.push(file);
        }
      }
    };

    const deleteOriginFile = function(index) {
      state.deleteList.push(state.board.fileList[index]);
      state.board.fileList.splice(index, 1);
    };

    const deleteNewFile = function(index) {
      state.thumbnailList.splice(index, 1);
      state.newAddFile.splice(index, 1);
    };

    onMounted(() => {
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "입양/임보",
        path: "/adopt",
        subTitle: "입양/임보 공고 수정"
      });
      readDogTypeList();
      gugunInitList(state.board.sido.id);
      window.scrollTo(0, 0);
    });

    return {
      state,
      gugunList,
      changeFile,
      deleteOriginFile,
      deleteNewFile,
      modifyData,
      readDogTypeList
    };
  }
};
</script>

<style scoped>
:deep(.el-radio__inner:hover) {
  border-color: #755744;
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
:deep(.el-button) {
  font-weight: 600;
}
:deep(.el-button:hover) {
  color: #f9f0e7;
  border-color: #755744;
  background-color: #755744;
}

:deep(.el-textarea__inner) {
  resize: none;
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
