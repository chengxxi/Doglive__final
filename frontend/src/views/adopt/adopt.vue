<template>
  <div class="main-body main-padding">
    <el-card class="box-card " shadow="none" style="border:none;">
      <div>
        <bread-crumb></bread-crumb>
        <div style="margin-top:50px">
          <h1 class="title">입양/임보</h1>
          <p>입양을 기다리는 아이들</p>
          <!-- 상세 문구 수정 필요 -->
        </div>

        <el-button type="outline-primary" @click="goRegister" round
          >글 작성하기</el-button
        >

        <el-button type="outline-primary" @click="dialogVisible = true" round
          >나와 맞는 강아지 찾기</el-button
        >

        <span class="filter-wrapper">
          <el-button @click="showFilter = !showFilter" circle>
            <i class="el-icon-s-operation"></i>
          </el-button>
          <el-row v-show="showFilter" class="select-wrapper" :gutter="20">
            <!-- Filter Options -->
            <el-select
              v-model="value_type"
              clearable
              placeholder="공고타입"
              style="width:25%;"
            >
              <el-option
                v-for="type in options_type"
                :key="type.value"
                :label="type.label"
                :value="type.value"
              >
              </el-option>
            </el-select>

            <el-select
              v-model="value_gender"
              clearable
              placeholder="성별"
              style="width:25%;"
            >
              <el-option
                v-for="gender in options_gender"
                :key="gender.value"
                :label="gender.label"
                :value="gender.value"
              >
              </el-option>
            </el-select>

            <el-select
              v-model="value_size"
              clearable
              placeholder="크기"
              style="width:25%;"
            >
              <el-option
                v-for="size in options_size"
                :key="size.value"
                :label="size.label"
                :value="size.value"
              >
              </el-option>
            </el-select>

            <el-select
              v-model="value_age"
              clearable
              placeholder="연령"
              style="width:25%;"
            >
              <el-option
                v-for="age in options_age"
                :key="age.value"
                :label="age.label"
                :value="age.value"
              >
              </el-option>
            </el-select>
          </el-row>
          <el-row>
            <el-col :span="18">
              <el-form-item style="width:100%">
                <el-input placeholder="이름" v-model="value_search"></el-input>
              </el-form-item>
            </el-col>
            <el-button @click="searchButon(value_search)">
              검색
            </el-button>
          </el-row>
        </span>

        <AdoptList />
      </div>
    </el-card>
  </div>
</template>

<style scoped>
.main-body {
  width: 100%;
  margin-left: 10%;
  margin-right: 10%;
}
.main-padding {
  padding-top: 50px;
  padding-bottom: 50px;
}

h3 {
  margin-block-start: 0px;
}

:deep(.el-descriptions) {
  font-size: 13pt;
  font-weight: 500;
}
:deep(.el-descriptions__title) {
  font-size: 30pt;
  font-weight: 700;
}

.dog-thumbnail {
  width: 90%;
  height: auto;
  margin-right: 20px;
  margin: 10px;
}

.dog-info-box {
  width: 100%;
  margin-right: 30px;
  margin: 10px;
  padding-right: 20px;
}

:deep(.el-descriptions__label) {
  display: inline-block;
  width: 150px;
  font-weight: 500;
}

:deep(.el-descriptions__content) {
  display: inline-block;
  font-weight: 500;
}
:deep(.el-button) {
  font-size: 15pt;
  font-family: NEXONLv1Gothic;
  font-weight: 700;
  color: #f9f0e7;
}

.dog-image-box {
  margin: 30px;
  padding: 10px;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.el-col {
  display: flex;

  align-items: center;
}

.box-card {
  margin-top: 20px;
}

.box {
  margin-top: 30px;
  margin-bottom: 40px;
  margin-left: 50px;
  margin-right: 50px;
  padding: 40px;
  background-color: #f9f0e7;
}

.content {
  margin-top: 20px;
  margin-bottom: 20px;
  padding: 20px;
  background-color: #ffffff;
}

.el-card__body {
  display: flex;
  align-items: center;
  margin-right: auto;
}
</style>

<script>
import AdoptFilter from "./components/adopt-filter.vue";
import AdoptList from "./components/adopt-list.vue";
import BreadCrumb from "./components/bread-crumb.vue";
import { reactive, computed, onMounted } from "vue"; // ref
// import $axios from 'axios'

import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "adoptlist",
  components: {
    AdoptList,
    BreadCrumb
  },
  data() {
    return {
      // Color Option
      options_type: [
        {
          value: 1,
          label: "입양"
        },
        {
          value: 2,
          label: "임보"
        }
      ],
      // Gender Option
      options_gender: [
        {
          value: 8,
          label: "여"
        },
        {
          value: 9,
          label: "남"
        }
      ],
      // Size Option
      options_size: [
        {
          value: 1,
          label: "소(8kg 미만)"
        },
        {
          value: 2,
          label: "중(8kg-18kg 미만)"
        },
        {
          value: 3,
          label: "대(18kg 이상)"
        }
      ],
      // Age Option
      options_age: [
        {
          value: 4,
          label: "Puppy(~ 6개월)"
        },
        {
          value: 5,
          label: "Junior(7개월 ~ 2살)"
        },
        {
          value: 6,
          label: "Adult(3살 ~ 8살)"
        },
        {
          value: 7,
          label: "Senior(9살 ~)"
        }
      ],

      value_type: "",
      value_gender: "",
      value_size: "",
      value_age: "",
      value_search: "",

      showFilter: true
    };
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();

    const state = reactive({
      boardList: [],
      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"].userId;
      })
    });

    const goRegister = function() {
      router.push({ name: "AdoptRegister" });
    };

    const searchButton = function(data) {
      console.log(data);
    };
    const goMBTI = function() {
      // if 사용자가 아직 테스트 전이면
      createToast("아직 테스트 결과가 없어요.", {
        hideProgressBar: "true",
        timeout: 4500,
        showIcon: "true",
        toastBackgroundColor: "#c49d83",
        position: "bottom-right",
        transition: "bounce",
        type: "warning"
      });
      router.push({ name: "MBTI" });

      // else 사용자 테스트 결과가 있으면 필터링
    };

    onMounted(() => {
      console.log("breadcrumb");
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "입양/임보",
        subTitle: "입양 공고 목록"
      });
    });

    return {
      state,
      searchButton,
      goRegister,
      goMBTI
    };
  }
};
</script>
