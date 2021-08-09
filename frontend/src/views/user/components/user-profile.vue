<template>
  <div class="mypage-body">
    <div class="hide-on-small">
      <div class="menu-title">
        <h5>내 프로필</h5>
      </div>
      <div class="mypage-content">
        <!-- 사용자 프로필 이미지 -->
        <div class="image-wrapper">
          <el-avatar shape="circle" :size="200" :src="require('@/assets/images/profile-image.jpg')" :style="{'border' : 'solid 1px rgb(212, 212, 212)'}"/>
          <!-- <el-avatar shape="circle" :size="200" :src="formData.userProfile.imageURL" :style="{'border' : 'solid 1px rgb(212, 212, 212)'}" style="background-size:cover;"/> -->
        </div>

        <!-- 사용자 프로필 정보 -->
        <el-form class="userinfo-wrapper" :model="formData" label-width="120px" label-position="right">
        <el-form-item
          label="닉네임"
          :rules="{ required: true, message: '닉네임을 입력해주세요', trigger: 'change' }">
        <el-input v-model="formData.userProfile.name"></el-input>
        </el-form-item>
        <el-form-item
          label="Email"
          :rules="[
            { required: true, message: 'Please input email address', trigger: 'change' },
            { type: 'email', message: 'Please input correct email address', trigger: ['blur', 'change'] }
          ]">
        <el-input v-model="formData.userProfile.email"></el-input>
        </el-form-item>
        <el-form-item
          label="생년월일"
          :rules="{ required: true, message: 'Please input email address', trigger: 'change' }">
        <el-date-picker type="date" v-model="formData.userProfile.birth" disabled></el-date-picker>
        </el-form-item>
        <el-form-item
          label="Phone"
          :rules="{ required: true, message: 'Please input email address', trigger: 'change' }">
        <el-input v-model="formData.userProfile.phone"></el-input>
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

</style>

<script>
import $axios from 'axios';
import { computed, reactive, watchEffect, ref } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import Cookies from 'universal-cookie';

export default {
  name: 'user-profile',
  
  setup() {
    const store = new useStore();
    const router = new useRouter()
    const cookies = new Cookies()
    
    const formData = reactive({
        userProfile : computed(() => {
          return store.getters["root/getUpdateUserInfo"];
        }
      )
     })

    const userId = store.getters['root/getLoginUserInfo'].userId;
    store.dispatch('root/requestUserProfile', userId)
      .then(function(result){
        const Profile = {
          imageURL : result.data.userProfile.profileImageUrl,
          name : result.data.userProfile.name,
          email :  result.data.userProfile.email,
          birth : '1997' + result.data.userProfile.phoneNumber,
          phone : '',
        }
        store.commit('root/setProfile',Profile)
        console.log(result.data)
      }).catch(function(err){
        console.log(err)
      });



    const updateProfile = function(){
      console.log("프로필 업데이트" + formData.userProfile)
      store.dispatch('root/changeUserInfo', {userId :userId, data :formData.userProfile})
      .then(function(result){
        alert("수정이 완료되었습니다!")
        router.push({name : 'Mypage'})
      }).catch(function(err){
        console.log(err)
      });
    }

    const userDelete = function(){
      $axios.delete('/users/' + userId)
      .then(function(result){
        // Request Header에 cookie를 담아서 전송 ({withCredentials: true} 설정을 통해 Cookie를 헤더에 담아서 전송 가능.)
        store.dispatch('root/requestKakaoLogout', {withCredentials: true})
        .then(function(result){
          alert("탈퇴가 완료 되었습니다.")
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
    return { updateProfile, userDelete, formData }
  }
}
</script>

