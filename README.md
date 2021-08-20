# 📢 독립 Version 0.2.0

#### 🔗 [독립 Version 0.2.0 즐기러가기](http://i5a501.p.ssafy.io/)

#### 💛 [독립 사용 설명서](<https://lab.ssafy.com/s05-webmobile1-sub3/S05P13A501/-/blob/release-0.2.0/exec/A501_%EB%8F%85%EB%A6%BD_%EC%82%AC%EC%9A%A9_%EC%84%A4%EB%AA%85%EC%84%9C(%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4).md>)

#### 💻 [포팅 매뉴얼](https://lab.ssafy.com/s05-webmobile1-sub3/S05P13A501/-/blob/release-0.2.0/exec/%ED%8F%AC%ED%8C%85_%EB%A7%A4%EB%89%B4%EC%96%BC.md)

<br>

# 🐶 독립 Dog-Live (獨立)

> 서울 5반 1팀 견과류 🥜 조다운, 이상현, 이영주, 최혜원, 한승희

<br>
![image](/uploads/fef8846dde3324c6d3994901c3b2d355/image.png) <br>

#### 비대면 상담과 매칭 알고리즘을 통해 유기견과 예비 반려인을 연결해주는 입양 플랫폼

<br>

**당신**과 **강아지**의 행복한 삶을 위해,

유기 및 실종된 강아지들이 불안정한 생활으로부터 벗어나 행복하고 안정적인 삶을 살 수 있도록 노력하는 서비스입니다.

<br><br>

## 🗂 Content

1. [🔈 프로젝트 소개](#-프로젝트-소개)
   <br>
   - [📑 타겟 및 목표](#-타겟-및-목표)
   - [📑 개발 환경](#-개발-환경)
   - [📑 기술 스택](#-기술-스택)
   - [📑 서버 아키텍처](#-서버-아키텍처)
   - [📑 ERD](#-erd)
   - [📑 컴포넌트](#-컴포넌트)
   - [📑 와이어프레임](#-와이어프레임)
   - [📑 스토리보드](#-스토리보드)
   - [📑 핵심 기능](#-핵심-기능)
     <br>
2. [🔈 구현 결과](#-구현-결과)
   <br>
   - [📑 Frontend](#frontend)
   - [📑 Backend](#backend)
     <br>
3. [🔈 팀원 소개 및 역할](#-팀원-소개-및-역할)
   <br><br>
4. [🔈 개발 문서](#-개발-문서)

<br><br><br><br>

---

## 🥜 프로젝트 소개

### 🔔 타겟 및 목표

#### 🧿 타겟

    ☝ 강아지 입양을 희망하거나 망설이고 있는 사람
    ✌ 유기동물 입양 문화에 관심이 있는 사람
    🤟 보호소 방문상담이 어려운 사람

#### 🏃‍♀️ 목표

    ✔ 유기동물 정보 접근성 개선 및 입양 문화 활성화에 기여
    ✔ 실시간 화상 상담이 가능한 유기동물 입양 플랫폼 개발
    ✔ 유기동물 입양 지향 커뮤니티 활성화
    ✔ WebRTC, WebSocket 등의 기술을 활용한 서비스 구현

<br><br>

### 🔨 개발 환경

- OS : Windows 10

- Server : AWS EC2

  - Ubuntu 20.04.1
  - Docker 20.10.8

- Backend

  - Java : Java 1.8.0
  - Framework : SpringBoot 2.4.5
  - ORM : JPA(Hibernate)
  - Nginx : 1.18.0 (Ubuntu)
  - IDE : Intellij 2021.1.3 / Visual Studio Code 1.59
  - Dependency tool : gradle-6.7
  - Database : MariaDB-10.3.31
  - Image Server : AWS S3 & CloudFront

- Frontend - HTML5, CSS3, Javascript(Es6) - Vue 3.0.0 - @vue/cli 4.5.0 - Vuex 4.0.0 - Vue-axios 3.2.4
  <br><br>

### 🔨 기술 스택

![image](/uploads/1c6881c2b70a865751439296996f1ecd/image.png)

<br><br>

### 🎈 서버 아키텍처

![image](/uploads/c0225c4fee74d38e6879af84dc2782ed/image.png)

### 🔔 타겟 및 목표

#### 🧿 타겟

    ☝ 강아지 입양을 희망하거나 망설이고 있는 사람
    ✌ 유기동물 입양 문화에 관심이 있는 사람
    🤟 보호소 방문상담이 어려운 사람

#### 🏃‍♀️ 목표

    ✔ 유기동물 정보 접근성 개선 및 입양 문화 활성화에 기여
    ✔ 실시간 화상 상담이 가능한 유기동물 입양 플랫폼 개발
    ✔ 유기동물 입양 지향 커뮤니티 활성화
    ✔ WebRTC, WebSocket 등의 기술을 활용한 서비스 구현

<br><br>

### 🔨 기술 스택

![image](/uploads/e4dec9417792b30c162d79d3aefbfd5a/image.png)

<br><br>

### 🎈 서버 아키텍처

![image](/uploads/95a84c075630ea281c5b7895f384885e/image.png)

<br><br>

### 🔍 ERD

![image](/uploads/abef4b0fa6208f15673cfded09d4841a/image.png)

<br><br>

### 🎶 컴포넌트

#### 🎨 대표색상 & 아이콘

![image](/uploads/6dcdde98f56f218384a4829dcf2d3250/image.png)

<br><br>

#### ✏ 폰트

![image](/uploads/761a03e03e0d07d2c2e63a7abbf98847/image.png)

<br><br>

#### 🧮 버튼

![image](/uploads/32398d27b4be5bd4e3f09ee0ca79d7a7/image.png)

<br><br>

#### 🏷 태그

![image](/uploads/1761b492e9e621e4f484674dcb22242c/image.png)

<br><br>

#### ⌨ 입력폼

![image](/uploads/9c85299e9aecbe41cfaf42d8a4bab812/image.png)

<br><br>

### 🎨 와이어프레임

⚠ 많은 페이지가 존재하는 관계로 일부만 README.md에 업로드하고 나머지는 첨부하겠습니다. [📁 와이어프레임 보러가기](https://lab.ssafy.com/s05-webmobile1-sub2/S05P12A501/-/tree/master/documents/Wireframe)

▪ 메인 페이지
![image](/uploads/62098347bf416c3feb82158593fb5b87/image.png)
<br><br>

▪ 성향 매칭 페이지
![image](/uploads/2db44e991f7268b3a0579ebc5749e008/image.png)
<br><br>

▪ 성향 테스트 페이지
![image](/uploads/271e6ba2b4585385546e946d77551499/image.png)
<br><br>

### 📖 스토리보드

⚠ 많은 페이지가 존재하는 관계로 일부만 README.md에 업로드하고 나머지는 첨부하겠습니다. [📁 스토리보드 보러가기](https://lab.ssafy.com/s05-webmobile1-sub2/S05P12A501/-/blob/master/documents/Storyboard/DogLive_%EC%8A%A4%ED%86%A0%EB%A6%AC%EB%B3%B4%EB%93%9C.pdf)

▪ 메인 페이지 관련
![image](/uploads/75829b704ffad2e53cde4620f2a68bc9/image.png)
<br><br>

▪ 성향 매칭 페이지 관련
![image](/uploads/0d71427572bed03a7509c91286484369/image.png)
<br><br>

▪ 입양 상세 내용 페이지 관련
![image](/uploads/b80254ed10e60467b6105035d1f02d6c/image.png)
<br><br>

▪ 실종/보호 유사 공고 페이지 관련
![image](/uploads/35af50485ea25a4a1ae8aacfb5b665e3/image.png)
<br><br>

▪ 커뮤니티 목록 페이지 관련
![image](/uploads/3a966523d04612c72b21e2d326317f2a/image.png)
<br><br>

### 💎 핵심 기능

    🔑 WebRTC를 통한 화상 상담
    🔑 매칭 알고리즘
    🔑 SNS 형식 커뮤니티

![image](/uploads/776e43fecbbbe6e40710f110995bacce/image.png)
![image](/uploads/d7c286754be030799f079454d5f0d5c6/image.png)
![image](/uploads/0a1940fc16d7f67428603ac1bd75e26a/image.png)
<br><br>

## 🥜 구현 결과

### Frontend

#### ✨ 프론트 디자인

#### [💜 프론트 디자인 보기](https://lab.ssafy.com/s05-webmobile1-sub2/S05P12A501/-/wikis/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EB%94%94%EC%9E%90%EC%9D%B8)

#### ✨ 프론트 구현 메인 페이지

- header, footer, carousel 등 구현  
   ![image](/uploads/4374caa937fff595f6c1bc1ab4bf034c/image.png)  
   ![image](/uploads/ecfd8c9902889e1fb090ed3925d3e76d/image.png)

<br><br>

#### ✨ 프론트 구현 로그인 페이지

- 상단 header - User 아이콘 마우스 Hover로 로그인 페이지로 이동  
   ![image](/uploads/007f1de6fb158665ac7259fb55fd60be/image.png)

- 로그인 페이지
  ![image](/uploads/cf10532d43eeebd0b19b3f0a0cca168d/image.png)

<br><br>

#### ✨ 프론트 구현 로그인 결과 (회원가입/로그인)

- 로그인 버튼 클릭 시 카카오 API 호출 및 로그인 진행  
   ![image](/uploads/0c111c6a41e7895c4e137f18162f6414/image.png)

      ![image](/uploads/9ce879e4edde3e561abf6c168bd72123/image.png)

  <br><br>

✔✔ 자체 DB에 해당 유저 없을 시 -> 회원가입 후 로그인  
✔✔ 자체 DB에 해당 유저 있을 시 -> 로그인

<br><br>
DB 데이터 확인

- User Table  
   ![image](/uploads/bed202258196e09f576964219f8a4e6c/image.png)
- UserToken Table  
   ![image](/uploads/1f937ab37c450b5957fed8722f75ddf6/image.png)
- UserProfile Table  
   ![image](/uploads/cd26425ffd18c9aa9f65e896383396fd/image.png)  
   ( 카카오 phone_number 접근에 실패하여 임시로 birthday값을 mapping한 상태 )

<br><br><br><br>

### Backend

📋 ERD 설계
![image](/uploads/208a8eb6ec51379d798c63ee42406591/image.png)
<br><br>

#### 로그인/회원가입

✔✔ 자체 DB에 해당 유저 없을 시 -> 회원가입 후 로그인  
✔✔ 자체 DB에 해당 유저 있을 시 -> 로그인

<br><br>

- kakao 인가 코드 받기
  ![image](/uploads/3c6d4e1d7bfef30da73d4ac7e27f2c1a/image.png)

- 카카오 로그인/회원가입 완료 - User Table  
   ![image](/uploads/0d597998113f385b759a7d424ec654d0/image.png)  
   ![image](/uploads/9f413de5e2b958557dae4efc2d4841d9/image.png)  
   ![image](/uploads/c6eabc05b9ba346c4282217fd6ea1ea5/image.png)  
  <br><br>

#### 회원탈퇴

![image](/uploads/b09ab5fa43de99e9b1345e4b7397cede/image.png)

DB 데이터 .삭제 확인

![image](/uploads/304dea9aee32d70d48ff968fd6359344/image.png)  
![image](/uploads/218135223b89fea8b2259827375d80ab/image.png)  
![image](/uploads/c8df503e36aaed7e0eee652ca9d68c94/image.png)

<br><br>

### 🔍 ERD

![image](/uploads/2ea42521895fee82f68527a11010b629/image.png)

<br><br>

### 💌 기능명세서

[📁 기능명세서 보러가기](https://www.notion.so/ssafyseoul5-1/b41fb130e68846e9981052ba6777def4)
<br><br>

### 🎨 와이어프레임

[📁 와이어프레임 보러가기](https://lab.ssafy.com/s05-webmobile1-sub3/S05P13A501/-/tree/release-0.2.0/documents/Wireframe)

<br><br>

### 📖 스토리보드

[📁 스토리보드 보러가기](https://lab.ssafy.com/s05-webmobile1-sub3/S05P13A501/-/blob/release-0.2.0/documents/Storyboard/DogLive_%EC%8A%A4%ED%86%A0%EB%A6%AC%EB%B3%B4%EB%93%9C.pdf)

<br><br>

### 💎 핵심 기능

    🔑 WebRTC를 통한 화상 상담
    🔑 매칭 알고리즘
    🔑 SNS 형식 커뮤니티

![image](/uploads/250e26eeb231efec8f62b7cadcf6de1a/image.png)  
![image](/uploads/d9ced1a2a864b0ae9cd1ea6aaff304db/image.png)  
![image](/uploads/1c2fed8a4ece44b767a1709b786e4978/image.png)  
![image](/uploads/8a91dc4a6668e782a18ab773e5a23b23/image.png)  
<br><br>

## 🥜 구현 결과

#### 🔗 [독립 Version 0.2.0 즐기러가기](http://i5a501.p.ssafy.io/)

<br><br>

## 🥜 실행 가이드

#### 💻 [포팅 매뉴얼](https://lab.ssafy.com/s05-webmobile1-sub3/S05P13A501/-/blob/release-0.2.0/exec/%ED%8F%AC%ED%8C%85_%EB%A7%A4%EB%89%B4%EC%96%BC.md)

#### 💛 [독립 사용 설명서](<https://lab.ssafy.com/s05-webmobile1-sub3/S05P13A501/-/blob/release-0.2.0/exec/A501_%EB%8F%85%EB%A6%BD_%EC%82%AC%EC%9A%A9_%EC%84%A4%EB%AA%85%EC%84%9C(%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4).md>)

<br><br>

## 🥜 팀원 소개 및 역할

<br><br>
|Name|이상현|이영주|조다운|최혜원|한승희|
|-----|-----|-----|------|----|-----|
|Profile|![image](/uploads/4d156331ccd5da65543cb18035789378/image.png)|![image](/uploads/9c86cd209dadba7f4a1f4beed56032d0/image.png)|![image](/uploads/23efbe58618bc9ab0740879970255519/image.png)|![image](/uploads/a87491d4b266835da47b4c53b9f82749/image.png)|![image](/uploads/50d11a1a597d1cd199369ed7df756f90/image.png)|
|Position|Frontend, Backend Develop & UI/UX|Backend Develop & CI/CD| 👑팀장 & Backend Develop & CI/CD|Backend Develop & CI/CD|Frontend & UI/UX|
|Git|@zxd9857|@clleo97|@jodawooooon | @hiii456| @chengxxi|

<br><br>

## 🥜 개발 문서

#### [ 🧾 기능명세서 ](https://lab.ssafy.com/s05-webmobile1-sub3/S05P13A501/-/blob/master/documents/function/DogLive_%EA%B8%B0%EB%8A%A5%EB%AA%85%EC%84%B8%EC%84%9C.pdf)

#### [ 📑 Wiki 보러 가기 ](https://lab.ssafy.com/s05-webmobile1-sub3/S05P13A501/-/wikis/home)

<br><br><br><br>
