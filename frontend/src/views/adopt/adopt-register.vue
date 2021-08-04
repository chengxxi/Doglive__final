<template>
<div class="main-body main-padding">
    <bread-crumb></bread-crumb>
    <el-card class="box-card mt-5 mb-5 p-5"  shadow="hover">
    <h5 class="mt-1 mb-3">강아지의 기본정보를 입력해주세요</h5>
      <el-divider />
    <el-form label-position="left"   :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
  <el-form-item label="제목" prop="name">
    <el-input v-model="ruleForm.name"></el-input>
  </el-form-item>
    <el-form-item label="강아지 이름" prop="name">
    <el-input v-model="ruleForm.name"></el-input>
  </el-form-item>
  <el-form-item label="연령대" prop="region">
    <el-select v-model="ruleForm.region" placeholder="Puppy(~ 6개월)">
      <el-option label="Puppy(~ 6개월)" value="4"></el-option>
      <el-option label="Junior(7개월 ~ 2살)" value="5"></el-option>
      <el-option label="Adult(3살 ~ 8살)" value="6"></el-option>
      <el-option label="Senior(9살 ~)" value="7"></el-option>
    </el-select>
  </el-form-item>
    <el-form-item label="크기" prop="region">
    <el-select v-model="ruleForm.region" placeholder="소(8kg 미만)">
      <el-option label="소(8kg 미만)" value="1"></el-option>
      <el-option label="중(8kg-18kg 미만)" value="2"></el-option>
      <el-option label="대(18kg 이상)" value="3"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="성별" prop="resource">
    <el-radio-group v-model="ruleForm.resource">
      <el-radio label="남"></el-radio>
      <el-radio label="여"></el-radio>
    </el-radio-group>
  </el-form-item>
  <el-form-item label="털 길이" prop="resource">
    <el-radio-group v-model="ruleForm.resource">
      <el-radio label="장모"></el-radio>
      <el-radio label="단모"></el-radio>
    </el-radio-group>
  </el-form-item>
  <el-form-item label="중성화 여부" prop="resource">
    <el-switch v-model="ruleForm.resource"></el-switch>
  </el-form-item>
<el-form-item label="현재 주소" prop="name">
    <el-input placeholder="ㅇㅇ시 ㅇㅇ구" v-model="ruleForm.name"></el-input>
  </el-form-item>

  <el-form-item label="부가 설명" prop="desc">
    <el-input type="textarea" v-model="ruleForm.desc"></el-input>
  </el-form-item>



  <h5 class="mb-3" style="margin-top:70px;">강아지의 성격을 소개해주세요</h5>
  <el-divider />
   <el-form-item label="활동성향" prop="resource">
    <el-radio-group v-model="ruleForm.resource">
      <el-popover
                placement="bottom"
                width="200"
                trigger="hover"
                >
                   <h6 style="font-weight:600">에너지 Energetic</h6>활동적, 즉흥적인
                <template #reference>
                  <el-radio label="에너지있는"></el-radio>
                </template>
              </el-popover>
<el-popover
                placement="bottom"
                width="200"
                trigger="hover"
                >
                   <h6  style="font-weight:600">침착한 Introversion</h6>차분한, 일관적인
                <template #reference>
                  <el-radio label="침착한"></el-radio>
                </template>
              </el-popover>
    </el-radio-group>
  </el-form-item>
  <el-form-item label="활동성향" prop="resource">
    <el-radio-group v-model="ruleForm.resource">
      <el-popover
                placement="bottom"
                width="200"
                trigger="hover"
                >
                   <h6 style="font-weight:600">에너지 Energetic</h6>활동적, 즉흥적인
                <template #reference>
                  <el-radio label="에너지있는"></el-radio>
                </template>
              </el-popover>
<el-popover
                placement="bottom"
                width="200"
                trigger="hover"
                >
                   <h6  style="font-weight:600">침착한 Introversion</h6>차분한, 일관적인
                <template #reference>
                  <el-radio label="침착한"></el-radio>
                </template>
              </el-popover>
    </el-radio-group>
  </el-form-item>
  <el-form-item label="활동성향" prop="resource">
    <el-radio-group v-model="ruleForm.resource">
      <el-popover
                placement="bottom"
                width="200"
                trigger="hover"
                >
                   <h6 style="font-weight:600">에너지 Energetic</h6>활동적, 즉흥적인
                <template #reference>
                  <el-radio label="에너지있는"></el-radio>
                </template>
              </el-popover>
<el-popover
                placement="bottom"
                width="200"
                trigger="hover"
                >
                   <h6  style="font-weight:600">침착한 Introversion</h6>차분한, 일관적인
                <template #reference>
                  <el-radio label="침착한"></el-radio>
                </template>
              </el-popover>
    </el-radio-group>
  </el-form-item>
  <el-form-item label="활동성향" prop="resource">
    <el-radio-group v-model="ruleForm.resource">
      <el-popover
                placement="bottom"
                width="200"
                trigger="hover"
                >
                   <h6 style="font-weight:600">에너지 Energetic</h6>활동적, 즉흥적인

                <template #reference>
                  <el-radio label="에너지있는"></el-radio>
                </template>
              </el-popover>
<el-popover
                placement="bottom"
                width="200"
                trigger="hover"
                >
                   <h6  style="font-weight:600">침착한 Introversion</h6>차분한, 일관적인
                <template #reference>
                  <el-radio label="침착한"></el-radio>
<i class="el-icon-question"/>
                </template>
              </el-popover>
    </el-radio-group>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">작성</el-button>
    <el-button @click="resetForm('ruleForm')">초기화</el-button>
  </el-form-item>
</el-form>
    </el-card>
</div>
</template>

<script>
import $axios from 'axios'
import BreadCrumb from './components/bread-crumb.vue'
import { computed, reactive, onMounted } from 'vue';
import { useStore } from 'vuex'
import { useRouter } from 'vue-router';

export default {
  name : 'AdoptReigster',
  components : {
    BreadCrumb,
  },
  data(){
     return {
        ruleForm: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: '',
          dialogImageUrl: '',
        dialogVisible: false,
        disabled: false
        },
        rules: {
          name: [
            { required: true, message: 'Please input Activity name', trigger: 'blur' },
            { min: 3, max: 5, message: 'Length should be 3 to 5', trigger: 'blur' }
          ],
          region: [
            { required: true, message: 'Please select Activity zone', trigger: 'change' }
          ],
          date1: [
            { type: 'date', required: true, message: 'Please pick a date', trigger: 'change' }
          ],
          date2: [
            { type: 'date', required: true, message: 'Please pick a time', trigger: 'change' }
          ],
          type: [
            { type: 'array', required: true, message: 'Please select at least one activity type', trigger: 'change' }
          ],
          resource: [
            { required: true, message: 'Please select activity resource', trigger: 'change' }
          ],
          desc: [
            { required: true, message: 'Please input activity form', trigger: 'blur' }
          ]
        }
      };
  },
     methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
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

  setup(){
      const store = new useStore()
      const router = new useRouter()

    onMounted(() => {
        console.log('breadcrumb')
        store.commit('root/setBreadcrumbInfo', {
          isHome : false,
          title: '입양/임보',
          subTitle: '입양/임보 공고 작성'
        })


      })

  }
}
</script>

<style scoped>
</style>
