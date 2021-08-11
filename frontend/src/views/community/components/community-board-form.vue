<template>
  <div>
    <el-row class="board">
      <div>
        <el-form
          style=" margin:5%;"
          label-position="left"
          :model="boardForm"
          :rules="rules"
          ref="boardForm"
          label-width="120px"
        >
        <h5 class="mt-1 mb-2" style="font-weight:600">
          👨‍👩‍👧 당신의 이야기를 들려주세요 !
        </h5>
        <el-divider />
          <el-row class="mb-4">
            <el-col :span="12">
              <el-form-item label="제목" prop="title" style="width:95%">
                <el-input :model="boardForm.title"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="카테고리" prop="category" style="width:95%">
                <el-select :model="boardForm.category" placeholder="입양일기">
                    <el-option
                      label="입양일기"
                      value="입양일기"
                    ></el-option>
                    <el-option
                      label="임보일기"
                      value="임보일기"
                    ></el-option>
                    <el-option
                      label="자유게시판"
                      value="자유게시판"
                    ></el-option>
                    <el-option
                      label="나눔"
                      value="나눔"
                    ></el-option>
                  </el-select>
                </el-form-item>
            </el-col>
          </el-row>

          <span style="font-size: 1.25rem; font-weight:600">
            📷 사진을 업로드 해주세요
          </span>
          <span> (최대 5장)</span>
          <el-divider />

          <el-row class="mb-5">
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

          <span style="font-size: 1.25rem; font-weight:600">
            📝 내용을 작성해주세요
          </span>
          <el-divider />
          <el-row class="mb-3">
            <el-form-item prop="description">
              <el-input
                type="textarea"
                :rows="7"
                maxlength="1000"
                show-word-limit
                :model="boardForm.description"
              ></el-input>
            </el-form-item>
          </el-row>
          

          <el-row
            class="mt-5"
            style=" display: flex;
            justify-content: center;"
          >
            <el-button type="primary" @click="submitForm('boardForm')"
              >작성</el-button
            >
            <el-button @click="resetForm('boardForm')">초기화</el-button>
          </el-row>

        </el-form>
      </div>
    </el-row>
  </div>
</template>

<style scoped>


li.el-select-dropdown__item.selected {
  color: #755744;
  font-weight: 700;
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

<script>
import $axios from "axios";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { onBeforeMount, onMounted, reactive, computed } from "vue";

export default {
  name: "community-board-list",
  data(){
    return{
      boardForm:{
        category : "",
        title : "냐냐냐ㅑㄴ",
        description : "",
      }
    }
  },
  method:{
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
        return store.getters["root/getLoginUserInfo"].userId;
      }),

    });
   


    

    onMounted(() => {
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "Adopt",
        subTitle: "입양 공고 목록"
      });
    });

   

    return { state };
  }
}
</script>
