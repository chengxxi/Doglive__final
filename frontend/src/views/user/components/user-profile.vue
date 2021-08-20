<template>
  <div class="mypage-body">
    <div class="hide-on-small">
      <div class="menu-title">
        <h5>내 프로필</h5>
      </div>
      <div class="mypage-content">
        <!-- 사용자 프로필 정보 -->
        <el-form class="userinfo-wrapper" :model="formData" label-width="120px" label-position="right">
          <!-- 사용자 프로필 이미지 -->
          <div class="image-wrapper">
            <label for="changeProfile">
            <el-image
              class="user-profile scale-up-2"
              :src="formData.userProfile.profileImageUrl"
              :fit="fit"
              style="cursor:pointer"
              ></el-image>
            </label>
              <input
                id="changeProfile"
                ref="file"
                accept="image/*"
                type="file"
                style="display: none"
                @change="changeFile"/>
          </div>
        <el-form-item
          label="닉네임"
          :rules="{ required: true, message: '닉네임을 입력해주세요', trigger: 'change' }">
        <el-input v-model="formData.userProfile.name"></el-input>
        </el-form-item>
        <el-form-item
          label="이메일"
          :rules="[
            { required: true, message: 'Please input email address', trigger: 'change' },
            { type: 'email', message: 'Please input correct email address', trigger: ['blur', 'change'] }
          ]">
        <el-input v-model="formData.userProfile.email"></el-input>
        </el-form-item>
        <el-form-item
          label="생년월일"
          :rules="{ required: true, message: 'Please input birthday', trigger: 'change' }">
        <el-date-picker type="date" v-model="formData.userProfile.birth"></el-date-picker>
        </el-form-item>
        <el-form-item
          label="연락처"
          :rules="{ required: true, message: 'Please input phoneNumber', trigger: 'change' }">
        <el-input v-model="formData.userProfile.phoneNumber"></el-input>
        </el-form-item>
        </el-form>
        <div class="button-group">
          <el-button class="updateBtn" @click="updateProfile">수정하기</el-button>
          <el-popconfirm title="정말로 탈퇴하시겠습니까?" @confirm="userDelete">
            <template #reference>
              <el-button type="danger">탈퇴하기</el-button>
            </template>
          </el-popconfirm>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.mypage-body{
  float: left;
  width: calc(100% - 240px);
  height: 100%;
}
.mypage-body .menu-title {
  text-align: left;
  padding-top: 10px;
  padding-bottom: 10px;
  border-bottom: solid 1px rgb(212, 212, 212);
}
.mypage-body .mypage-content {
  padding-top: 60px;
  text-align: center;
  margin: 0 auto;
}
.mypage-body .mypage-content .image-wrapper{
  margin-bottom: 30px;
}
.mypage-body .mypage-content .userinfo-wrapper{
  width: 50%;
  text-align: center;
  margin: 0 auto;
  border-bottom: solid 1px rgb(212, 212, 212);
}
.mypage-body .mypage-content .userinfo-wrapper .el-input{
  width: 60% !important;
  min-width: 150px;
}
/* element-ui에서 제공해주는 내부 클래스를 변경하려면 :deep(선택자) 붙여주기 */
:deep(.el-date-editor.el-input){
  width: 60% !important;
  min-width: 150px;
}
:deep(.el-form-item__error){
  left: 20%;
}
/* 수정하기 버튼 */
.mypage-body .mypage-content .button-group{
  margin-top: 20px;
  margin-bottom: 50px;
}
.mypage-body .mypage-content .button-group .el-button{
  border-color: #D7AEA4;
  font-weight: 600;
}
.user-profile{
  width: 150px;
  height: 150px;
  border-radius: 100%;
  margin-left: 8px;
  margin-right: 10px;
}
:deep(.el-image__inner){
  object-fit: cover;
}

</style>

<script>
import $axios from 'axios';
import { computed, reactive } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import Cookies from 'universal-cookie';
import { createToast } from "mosha-vue-toastify";
import "mosha-vue-toastify/dist/style.css";

export default {
  name: 'user-profile',

  setup() {
    const store = new useStore();
    const router = new useRouter()
    const cookies = new Cookies()

    const formData = reactive({
        userProfile : computed(() => {
          console.log(store.getters["root/getUpdateUserInfo"])
          return store.getters["root/getUpdateUserInfo"];
        }
      ),
      imageFile : "",
     })

    const userId = store.getters['root/getLoginUserInfo'].userId;
    store.dispatch('root/requestUserProfile', userId)
      .then(function(result){
        console.log(result)
        const Profile = {
          profileImageUrl : result.data.userProfile.profileImageUrl,
          name : result.data.userProfile.name,
          email :  result.data.userProfile.email,
          birth : result.data.userProfile.birth,
          phoneNumber : result.data.userProfile.phoneNumber,
        }
        store.commit('root/setProfile',Profile)
        console.log(result.data)
      }).catch(function(err){
        console.log(err)
      });



    const updateProfile = function(){
      
      if(formData.imageFile!=""){
        const updateData = new FormData();

        updateData.append("name", formData.userProfile.name);
        updateData.append("email", formData.userProfile.email);
        updateData.append("phoneNumber", formData.userProfile.phoneNumber);
        updateData.append("birth", formData.userProfile.birth);
        updateData.append("file", formData.imageFile);

        store.dispatch('root/changeUserInfo', {userId :userId, data :updateData})
        .then(function(result){
          createToast("프로필이 수정되었어요 ✨", {
                  hideProgressBar: "true",
                  timeout: 4500,
                  showIcon: "true",
                  toastBackgroundColor: "#7eaa72",
                  position: "bottom-right",
                  transition: "bounce",
                  type: "success"
              });
          router.push({name : 'Mypage'})
        }).catch(function(err){
          console.log(err)
        });
      }else{
        store.dispatch('root/changeUserInfoNoImage', {userId :userId, data :formData.userProfile})
        .then(function(result){
          createToast("프로필이 수정되었어요 💨💨", {
                  hideProgressBar: "true",
                  timeout: 4500,
                  showIcon: "true",
                  toastBackgroundColor: "#7eaa72",
                  position: "bottom-right",
                  transition: "bounce",
                  type: "success"
              });
          router.push({name : 'Mypage'})
        }).catch(function(err){
          console.log(err)
        });
      }
    }

    const userDelete = function(){
      $axios.delete('/users/' + userId)
      .then(function(result){
        // Request Header에 cookie를 담아서 전송 ({withCredentials: true} 설정을 통해 Cookie를 헤더에 담아서 전송 가능.)
        store.dispatch('root/requestKakaoLogout', {withCredentials: true})
        .then(function(result){
          createToast("탈퇴가 완료되었습니다 🎈🎈", {
                hideProgressBar: "true",
                timeout: 4500,
                showIcon: "true",
                toastBackgroundColor: "#7eaa72",
                position: "bottom-left",
                transition: "bounce",
                type: "success"
          });
          // cookie 삭제
          cookies.remove('accessToken', { path : '/', sameSite : 'strict' })
          cookies.remove('refreshToken', { path : '/', sameSite : 'strict' })
          store.commit('root/setLoginUserInfo', null);
          router.push({name : 'Main'})
        })
      }).catch(function(err){
        alert("탈퇴에 실패하였습니다.")
        console.log(err)
      });
    }

    //파일 업로드 시 호출
    const changeFile = function(e) {
      const file = e.target.files[0];
      formData.userProfile.profileImageUrl = URL.createObjectURL(file);
      formData.imageFile = file;
    };


    return { updateProfile, userDelete, formData, changeFile }
  }
}
</script>

