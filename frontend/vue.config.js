
// Vue3 관련 설정 파일
module.exports = {

  devServer: {
    compress: true,
    disableHostCheck: true,
    https: false,
    port: 8080,
    open: true,
    proxy: {
      '/api/v1': {
        target: 'http://localhost:8080/'
      }
    },
    historyApiFallback: true,
    hot: true
  },
  transpileDependencies: [
    'element-plus',
    'vuex-persist',
  ],
  lintOnSave: false,
  outputDir: '../backend/src/main/resources/dist'
}
