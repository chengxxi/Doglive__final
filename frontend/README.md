# Dog-Live



## Frontend 디렉토리 구조 

```
.
└── main
    ├── generated
    ├── java
    └── resources
        ├── README.md
        ├── application.properties
        ├── babel.config.js
        ├── dist
        │   ├── css
        │   │   ├── app.22fde46a.css
        │   │   └── chunk-vendors.bfd3c584.css
        │   ├── favicon.ico
        │   ├── fonts
        │   │   ├── NEXONLv1GothicBold.ttf
        │   │   ├── NEXONLv1GothicRegular.ttf
        │   │   └── NEXONLv1GothicLight.woff
        │   ├── img
        │   │   └── ssafy-logo.74eec4f3.png
        │   ├── index.html
        │   └── js
        │       ├── app.2a195a37.js
        │       ├── app.2a195a37.js.map
        │       ├── chunk-vendors.184466aa.js
        │       └── chunk-vendors.184466aa.js.map
        ├── package-lock.json
        ├── package.json /* 필요 플러그인, 모듈 목록 */
        ├── public
        │   ├── favicon.ico
        │   └── index.html
        ├── src
        │   ├── App.vue /* 진입점 */
        │   ├── assets /* 리소스 저장용 폴더 */
        │   │   ├── images
        │   │   |
        |   |   └── fonts /* 폰트 저장 폴더 */
        │   ├── common
        │   │   ├── css
        │   │   │   ├── common.css /* 공통 요소 (html, body, font), 공통 반응형 .hide-on-small 등 */
        │   │   │   └── element-plus.css /* Element Plus Overriding CSS */
        │   │   ├── lib
        │   │   │   ├── axios.js
        │   │   │   ├── element-plus.js
        │   │   │   ├── i18n.js
        │   │   │   ├── store.js
        │   │   │   └── vue-router.js
        │   │   ├── config.js /* 공용 설정 관련 함수 정의 */
        │   │   └── util.js  /* 공용 유틸 관련 함수 정의 */
        │   ├── main.js
        │   └── views
        │       │
        |       ├── about /* 독립 메뉴 (서비스 소개, 팀 소개) */
        |       |   ├── about-service.vue
        |       |   └── about-team.vue
        │       │
        |       ├── user
        │       │   ├── components /* 유저 관련 컴포넌트(프로필, 내 글목록, 신청자 목록, 신청결과 목록) */
        |       |   |   ├── user-profile.vue
        |       |   |   ├── user-profile-form.vue
        |       |   |   ├── user-post-list.vue
        |       |   |   ├── user-apply-list.vue
        |       |   |   ├── user-apply-item.vue
        |       |   |   ├── user-apply-result-list.vue
        |       |   |   └── user-apply-result-item.vue
        |       |   |
        |       |   └── mypage.vue
        |       |
        │       └── main
        │           ├── components /* 메인 관련 컴포넌트(사이드바, 헤더, 푸터, 로그인 다이얼로그) */
        │           │   ├── login-dialog.vue
        |           |   ├── main-content.vue
        │           │   ├── main-footer.vue
        │           │   └── main-header.vue
        │           │
        │           ├── main.css
        │           ├── main.vue
        |           ├── menu.json
        │           └── store
        │               ├── actions.js
        │               ├── getters.js
        │               ├── index.js
        │               ├── mutations.js
        │               └── state.js
        ├── vue.config.js /* Vue3 관련 설정 파일(프록시, 기타 옵션) */
        └── webpack.config.js /* stylus 파일 확장자(.styl) 관련 설정 */
```



## Project setup

```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).