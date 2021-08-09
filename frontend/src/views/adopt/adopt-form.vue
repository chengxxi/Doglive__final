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
          <div style="margin-bottom:50px;">
            <h2 class="mt-1 mb-3" style="font-weight:600;">
              ✍ 입양 신청서 작성
            </h2>
          </div>
          <h4 class="mt-1 mb-3" style="font-weight:600;">
            기본 인적 사항
          </h4>
          <el-divider />
          <el-row class="mt-3 mb-3">
            <el-col :span="8">
              <el-form-item label="공고 제목" prop="title" style="width:95%">
                <el-input v-model="ruleForm.title" disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item
                label="강아지 이름"
                prop="dogName"
                style="width:95%"
              >
                <el-input v-model="ruleForm.dogName" disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="공고 타입" prop="type" style="width:100%">
                <el-input v-model="ruleForm.boardType" disabled></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="mt-3 mb-3">
            <el-col :span="12">
              <el-form-item label="신청자 이름" prop="name" style="width:95%">
                <el-input
                  v-model="ruleForm.name"
                  placeholder="조다운"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="이메일 주소" prop="email" style="width:100%">
                <el-input
                  v-model="ruleForm.email"
                  placeholder="ssafy@ssafy.com"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="mt-3 mb-3">
            <el-col :span="12">
              <el-form-item label="핸드폰 번호" prop="email" style="width:95%">
                <el-input
                  v-model="ruleForm.phone"
                  placeholder="010-0000-0000"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="직업" prop="job" style="width:95%">
                <el-input
                  v-model="ruleForm.job"
                  placeholder="개발자"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="나이" prop="age" style="width:100%">
                <el-input v-model="ruleForm.age" placeholder="25"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="mt-3 mb-3">
            <el-col :span="18">
              <el-form-item label="거주지" prop="sido" style="width:95%">
                <el-select
                  v-model="state.selectedSidoCode"
                  multiple
                  placeholder="시/도"
                >
                  <el-option
                    v-for="(sido, idx) in state.sidoList"
                    :key="idx"
                    :label="sido.name"
                    :value="sido.id"
                  >
                  </el-option>
                </el-select>
                <el-select
                  v-model="selectedGugunCode"
                  multiple
                  collapse-tags
                  style="margin-left: 20px;"
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
            <el-col :span="6">
              <el-form-item label="결혼여부" prop="gender">
                <el-radio-group v-model="ruleForm.gender">
                  <el-radio label="O" border></el-radio>
                  <el-radio label="X" border></el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="mt-3 mb-3"> </el-row>
          <el-row
            class="mt-5"
            style=" display: flex;
  justify-content: center;"
          >
            <el-button type="primary">작성</el-button>
            <el-button>초기화</el-button>
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
        boardType: this.state.board.boardType.name,
        title: this.state.board.title,
        dogName: this.state.board.dogName,
        email: "",
        name: this.state.userIn,
        phone: "",
        age: "",
        sido: "",
        gugun: "",
        job: "",
        isMarried: "",
        quesition1_1: "",
        question1_2: "",
        question2_1: "",
        quesiont2_2: ""
      },
      rules: {}
    };
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();

    const state = reactive({
      board: computed(() => {
        console.log(store.getters["root/getBoardDetail"]);
        return store.getters["root/getBoardDetail"];
      }),
      userInfo: computed(() => {
        return store.getters["root/getLoginUserInfo"];
      }),
      sidoList: [],
      gugunList: [],
      selectedSidoCode: "",
      selectedDongCode: ""
    });

    store
      .dispatch("root/requestSidoCodeList")
      .then(function(result) {
        console.log("call : sidocode");
        state.sidoList = result.data.sidoList;
      })
      .catch(function(error) {
        console.log(error);
      });

    const gugunList = function() {
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
        title: "입양/임보 상담",
        subTitle: "입양/임보 신청서 작성"
      });
      window.scrollTo(0, 0);
    });

    return { state, gugunList };
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
