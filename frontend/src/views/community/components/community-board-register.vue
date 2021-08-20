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
            <el-col :span="18">
              <el-form-item label="제목" prop="title" style="margin-right:3%;">
                <el-input v-model="boardForm.title"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="카테고리" prop="category">
                <el-select v-model="boardForm.category" placeholder="입양일기">
                  <el-option label="입양일기" value="입양일기"></el-option>
                  <el-option label="임보일기" value="임보일기"></el-option>
                  <el-option label="자유게시판" value="자유게시판"></el-option>
                  <el-option label="나눔" value="나눔"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <span style="font-size: 1.25rem; font-weight:600">
            📷 사진을 업로드 해주세요
          </span>
          <span> (최대 3장, 최소 1장)</span>
          <el-divider />

          <el-row class="mt-4 mb-3">
            <div class="mb-3" id="imgFileUploadInsertWrapper">
              <div class="thumbnail-wrapper">
                <el-image
                  style="width: 200px; height:200px; box-shadow:0 2px 12px 0 rgb(0 0 0 / 10%); cursor:pointer; position:relative; margin-right:20px; border-radius:20px; float:left;"
                  v-for="(file, index) in state.fileList"
                  v-bind:src="file"
                  v-bind:key="index"
                  @click="deleteFile(index)"
                  :fit="fit"
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
                      vertical-align:middle;
                      box-shadow:0 2px 12px 0 rgb(0 0 0 / 10%);
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

          <!-- <el-row class="mb-5">
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
          </el-row> -->

          <span style="font-size: 1.25rem; font-weight:600">
            📝 내용을 작성해주세요
          </span>
          <el-divider />
          <el-row class="mb-3">
            <el-form-item label="내용" prop="description">
              <el-input
                class="textarea"
                type="textarea"
                v-model="boardForm.description"
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

:deep(.el-textarea__inner) {
  resize: none;
  height: 200px;
  white-space: pre;
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
.textarea {
  resize: none;
  white-space: pre;
}
</style>

<script>
import { computed, reactive, onMounted } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: "community-board-register",
  data() {
    return {
      boardForm: {
        userId: this.state.userId.userId,
        category: "",
        title: "",
        description: ""
      },
      rules: {
        category: [
          {
            required: true,
            message: "게시글 카테고리를 선택해주세요",
            trigger: "change"
          }
        ],
        title: [
          { required: true, message: "제목을 입력해주세요.", trigger: "blur" },
          {
            min: 3,
            max: 25,
            message: "3글자 이상, 25글자 이하로 입력해주세요.",
            trigger: "change"
          }
        ],
        description: [
          { required: true, message: "내용을 입력해주세요.", trigger: "blur" },
          {
            min: 10,
            max: 1000,
            message: "10글자 이상, 1000글자 이하로 입력해주세요.",
            trigger: "change"
          }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      if (this.state.sendFile.length > 3) {
        createToast("사진은 3장까지만 업로드 가능해요 💬💦", {
          hideProgressBar: "true",
          timeout: 4500,
          showIcon: "true",
          toastBackgroundColor: "#c49d83",
          position: "bottom-left",
          transition: "bounce",
          type: "warning"
        });
      } else if (this.state.sendFile.length >= 1) {
        const formData = new FormData();

        formData.append("userId", this.state.userId.userId);
        formData.append("title", this.boardForm.title);
        formData.append("category", this.boardForm.category);
        formData.append("description", this.boardForm.description);

        const cnt = this.state.sendFile.length;
        for (var i = 0; i < cnt; i++) {
          formData.append("fileList", this.state.sendFile[i]);
        }
        console.log(formData);

        this.$refs[formName].validate(valid => {
          if (valid) {
            console.log(this.boardForm);
            this.registerData(formData);
          } else {
            console.log("error submit!!");
            return false;
          }
        });
      } else {
        createToast("사진은 한 장 이상 업로드해주세요 💬💦", {
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
      fileList: [],
      sendFile: [],
      userId: computed(() => {
        return store.getters["root/getLoginUserInfo"];
      })
    });

    const registerData = function(data) {
      store
        .dispatch("root/requestRegisterCommunity", data)
        .then(function(result) {
          createToast("게시글이 등록되었어요 📜🐾", {
            hideProgressBar: "true",
            timeout: 4500,
            showIcon: "true",
            toastBackgroundColor: "#7eaa72",
            position: "bottom-left",
            transition: "bounce",
            type: "success"
          });
          console.log("등록 성공");
          router.push({ name: "Community" });
        })
        .catch(function(err) {
          createToast("게시글 등록에 실패했어요 💬💦", {
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
          state.fileList.push(URL.createObjectURL(file));
          state.sendFile.push(file);
        }
      }
    };

    const deleteFile = function(index) {
      console.log(state.sendFile);
      state.fileList.splice(index, 1);
      state.sendFile.splice(index, 1);
      console.log(state.sendFile);
    };

    onMounted(() => {
      store.commit("root/setBreadcrumbInfo", {
        isHome: false,
        title: "독립신문",
        subTitle: "글 작성하기"
      });
    });

    return { state, registerData, changeFile, deleteFile };
  }
};
</script>
