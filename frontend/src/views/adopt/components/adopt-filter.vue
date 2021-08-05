<template>
  <div class="filter-wrapper">
    <el-button @click="showFilter = !showFilter" circle>
      <i class="el-icon-s-operation"></i>
    </el-button>

    <div v-show="showFilter" class="select-wrapper">
      <!-- Filter Options -->
      <el-select v-model="value_color" multiple clearable placeholder="색상">
        <el-option
          v-for="color in options_color"
          :key="color.value"
          :label="color.label"
          :value="color.value">
        </el-option>
      </el-select>

      <el-select v-model="value_gender" multiple clearable placeholder="성별">
        <el-option
          v-for="gender in options_gender"
          :key="gender.value"
          :label="gender.label"
          :value="gender.value">
        </el-option>
      </el-select>

      <el-select v-model="value_size" multiple clearable placeholder="크기">
        <el-option
          v-for="size in options_size"
          :key="size.value"
          :label="size.label"
          :value="size.value">
        </el-option>
      </el-select>

      <el-select v-model="value_age" multiple clearable placeholder="연령">
        <el-option
          v-for="age in options_age"
          :key="age.value"
          :label="age.label"
          :value="age.value">
        </el-option>
      </el-select>
    </div>

  </div>
</template>


<style scoped>
.filter-wrapper {
  width: 100%;
}

.select-wrapper {
  display: table;
  table-layout: fixed;
  margin-top: 20px;
  width: 100%;
}

.el-select {
  margin-left: 20px;
  margin-bottom: 10px;
}

.el-button {
  margin-top: 20px;
}



</style>



<script>
// import { reactive } from "vue";

export default {
  name: 'adopt-filter',
  data() {
    return {
      // Color Option
      options_color: [{
        value: 'White',
        label: 'White'
      }, {
        value: 'Beige',
        label: 'Beige'
      }, {
        value: 'Gray',
        label: 'Gray'
      }, {
        value: 'Brown',
        label: 'Brown'
      }, {
        value: 'Black',
        label: 'Black'
      }],
      // Gender Option
      options_gender: [{
        value: 'female',
        label: 'female'
      }, {
        value: 'male',
        label: 'male'
      }],
      // Size Option
      options_size: [{
        value: 'small',
        label: 'small'
      }, {
        value: 'medium',
        label: 'medium'
      }, {
        value: 'large',
        label: 'large'
      }],
      // Age Option
      options_age: [{
        value: 'puppy',
        label: 'puppy'
      }, {
        value: 'junior',
        label: 'junior'
      }, {
        value: 'adult',
        label: 'adult'
      }, {
        value: 'senior',
        label: 'senior'
      }],

      value_color: [],
      value_gender: [],
      value_size: [],
      value_age: [],

      showFilter: false,

    }
  }
}
</script>
