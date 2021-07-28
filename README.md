## 🐶 독립 Dog-Live (獨立)
> 견과류 (犬과You)  :  조다운, 이상현, 이영주, 최혜원, 한승희


유기견들의 행복한 삶을 위해 노력하는 서비스입니다.

<br>

* * *

### 프로젝트 기획

<br><br>

### 서버 아키텍처
![image](/uploads/95a84c075630ea281c5b7895f384885e/image.png)

<br><br>

### 프로젝트 구조

#### front-end

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
        │   │   ├── element-icons.abe71f7d.ttf
        │   │   └── element-icons.d9491be2.woff
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

#### back-end

```
.
└── main
    ├── generated
    ├── java
    │   └── com
    │       └── ssafy
    │           ├── GroupCallApplication.java
    │           ├── api  /* REST API 요청관련 컨트롤러, 서비스, 요청/응답 모델 정의*/
    │           │   ├── controller
    │           │   │   ├── 
    │           │   │   └── 
    │           │   ├── request
    │           │   │   ├── 
    │           │   │   └── 
    │           │   ├── response
    │           │   │   ├── 
    │           │   │   └── 
    │           │   └── service
    │           │       ├── 
    │           │       └── 
    │           ├── common /* 공용 유틸, 응답 모델, 인증, 예외처리 관련 정의*/
    │           │   ├── auth
    │           │   │   ├── 
    │           │   │   ├── 
    │           │   │   └── 
    │           │   ├── exception
    │           │   │   └── handler
    │           │   │       └── 
    │           │   ├── model
    │           │   │   └── response
    │           │   │       └── 
    │           │   └── util
    │           │       ├── 
    │           │       └── 
    │           ├── config /* WebMvc 및 JPA, Security, Swagger 등의 추가 플러그인 설정 정의*/
    │           │   ├── AuthDBConfig.java
    │           │   ├── BoardDBConfig.java
    │           │   ├── ChatDBConfig.java
    │           │   ├── CommunityDBConfig.java
    │           │   ├── QuerydslConfig.java
    │           │   └── querydsl
	│           |      ├── AuthQuerydslRepository.java
	│           |      ├── BoardQuerydslRepository.java
	│           |      ├── ChatQuerydslRepository.java
	│           |      └── CommunityQuerydslRepository.java
	│           │
    │           └── db /* 디비에 저장될 모델 정의 및 쿼리 구현 */
    │               └── entity
    │                   └── auth
    │                      ├── Bookmark.java
    │                      ├── CounselingHistory.java
    │                      ├── User.java
    │                      ├── UserToken.java
    │                      └── UserProfile.java
    │                   └── board
    │                      ├── BaseEntity.java
    │                      ├── Board.java
    │                      ├── BoardCategory.java
    │                      ├── BoardComment.java
    │                      ├── DogInformation.java
    │                      └── BoardImage.java
    │                   └── chat
    │                      ├── BaseEntity.java
    │                      ├── ChatMessage.java
    │                      ├── ChatRoom.java
    │                      ├── ChatRoomJoin.java
    │                      ├── Conference.java
    │                      ├── ConferenceHistory.java
    │                      └── ConferenceUser.java
    │                   └── community
    │                      ├── BaseEntity.java
    │                      ├── Community.java
    │                      ├── CommunityComment.java
    │                      └── CommunityImage.java
	│               │
    │               └── repository
    │                   └── auth
    │                      ├── BookmarkRepository.java
    │                      ├── CounselingHistoryRepository.java
    │                      ├── UserRepository.java
    │                      ├── UserTokenRepository.java
    │                      └── UserProfileRepository.java
    │                   └── board
    │                      ├── BoardRepository.java
    │                      ├── BoardCategoryRepository.java
    │                      ├── BoardCommentRepository.java
    │                      ├── DogInformationRepository.java
    │                      └── BoardImageRepository.java
    │                   └── chat
    │                      ├── ChatMessageRepository.java
    │                      ├── ChatRoomRepository.java
    │                      ├── ChatRoomJoinRepository.java
    │                      ├── ConferenceRepository.java
    │                      ├── ConferenceHistoryRepository.java
    │                      └── ConferenceUserRepository.java
    │                   └── community
    │                      ├── CommunityRepository.java
    │                      ├── CommunityCommentRepository.java
    │                      └── CommunityImageRepository.java
    └── resources
        ├── README.md
        ├── application.yml /* 웹 리소스(서버 host/port, 디비 host/port/계정/패스워드) 관련 설정 정의 */
        ├── babel.config.js
        ├── dist
        ├── package-lock.json
        ├── package.json
        ├── public

```

<br><br>


## 개발문서 
#### [ 📑 Wiki 보러 가기](https://lab.ssafy.com/s05-webmobile1-sub2/S05P12A501/-/wikis/home)

### 🚩 목표
- 많은 사람들이 유기동물들을 쉽게 접하고 자연스럽게 입양으로 연결될 수 있도록

### 📑 [팀 노션](https://www.notion.so/ssafyseoul5-1/1-a1fcecce811a42f4bc399dd562d1ce2f)

### 🙌 팀 그라운드 룰
- [팀 규칙](https://lab.ssafy.com/s05-webmobile1-sub2/S05P12A501/-/wikis/Life-Rule)
- [깃 커밋, 브랜치 컨벤션](https://lab.ssafy.com/s05-webmobile1-sub2/S05P12A501/-/wikis/Git-Rule)
- [팀 일정관리 ](https://lab.ssafy.com/s05-webmobile1-sub2/S05P12A501/-/wikis/Jira-Rule)


### 🐰 [기획](https://lab.ssafy.com/s05-webmobile1-sub2/S05P12A501/-/wikis/%EB%8F%85%EB%A6%BD-Dog-Live-(%E7%8D%A8%E7%AB%8B))
- [기능 기획](https://docs.google.com/document/d/12U0s1zGgW9k-aRnPHZd7vn-ctiviVSggAL86gH6pok4/edit)
- [프로젝트 기획서](https://www.figma.com/file/8qu5MvPdGtlTdXLOIcX8af/Dog-Live?node-id=26%3A2)
- [와이어프레임](https://www.figma.com/file/8qu5MvPdGtlTdXLOIcX8af/Dog-Live?node-id=23%3A8)
- [컴포넌트](https://www.figma.com/file/8qu5MvPdGtlTdXLOIcX8af/%EB%8F%85%EB%A6%BD-Dog-Live?node-id=0%3A1)
- [기능 명세서](https://www.notion.so/ssafyseoul5-1/b41fb130e68846e9981052ba6777def4)
- [ERD](https://www.erdcloud.com/d/RW9JRbhqsxG6zZGKh)
- [프로젝트 회의록](https://www.notion.so/ssafyseoul5-1/8e71df79d7d14bd98bafc4e8da122940?v=e1af9bc232154533b626d0289c1996b3)

### 🎉 주간 회고
-
