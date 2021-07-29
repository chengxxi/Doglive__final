<template>
  <div class="ccc">
    <el-carousel :interval="1500" direction="vertical" arrow="hover" height="1050px" @change="carouselChange">
      <el-carousel-item v-for="item in imgArray" :key="item">
        <img :src="item" class="rightImg">
      </el-carousel-item>
    </el-carousel>
  </div>

</template>

<style>
.ccc {
  width: 100%;
  height: 1050px;
  > .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }

  > .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  > .el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
  }
}

</style>


<script>
export default {
  name: 'main-content',
  data() {
    return {
      imgArray:[
        require('../../../assets/images/banner2.png'),
        require('../../../assets/images/banner3.png'),
        require('../../../assets/images/banner4.png'),
      ]
    };
  },
  methods: {
  	carouselChange(index){
  		this.carouselIndex = index
  	}
  },
  setup() {
  }
}
</script>
