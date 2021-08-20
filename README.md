# 📢 독립 Version 0.2.0
#### 🔗 [독립 Version 0.2.0 즐기러가기](http://i5a501.p.ssafy.io/)
#### 💛 [독립 사용 설명서](https://lab.ssafy.com/s05-webmobile1-sub3/S05P13A501/-/blob/release-0.2.0/exec/A501_%EB%8F%85%EB%A6%BD_%EC%82%AC%EC%9A%A9_%EC%84%A4%EB%AA%85%EC%84%9C(%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4).md )
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
* * *
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

- Frontend
    - HTML5, CSS3, Javascript(Es6)
    - Vue 3.0.0
    - @vue/cli 4.5.0
    - Vuex 4.0.0
    - Vue-axios 3.2.4
<br><br>

### 🔨 기술 스택
![image](/uploads/1c6881c2b70a865751439296996f1ecd/image.png)

<br><br>

### 🎈 서버 아키텍처
![image](/uploads/c0225c4fee74d38e6879af84dc2782ed/image.png)

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
#### 💛 [독립 사용 설명서](https://lab.ssafy.com/s05-webmobile1-sub3/S05P13A501/-/blob/release-0.2.0/exec/A501_%EB%8F%85%EB%A6%BD_%EC%82%AC%EC%9A%A9_%EC%84%A4%EB%AA%85%EC%84%9C(%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4).md )

<br><br>

## 🥜 팀원 소개 및 역할

<br><br>
|Name|이상현|이영주|조다운|최혜원|한승희|
|-----|-----|-----|------|----|-----|
|Profile|![image](/uploads/4d156331ccd5da65543cb18035789378/image.png)|![image](/uploads/9c86cd209dadba7f4a1f4beed56032d0/image.png)|![image](/uploads/23efbe58618bc9ab0740879970255519/image.png)|![image](/uploads/a87491d4b266835da47b4c53b9f82749/image.png)|![image](/uploads/50d11a1a597d1cd199369ed7df756f90/image.png)|
|Position|Frontend, Backend Develop & UI/UX|Backend Develop & CI/CD|	👑팀장 & Backend Develop & CI/CD|Backend Develop & CI/CD|Frontend & UI/UX|
|Git|@zxd9857|@clleo97|@jodawooooon | @hiii456|	@chengxxi|


<br><br>

## 🥜 개발 문서
#### [ 🧾 기능명세서 ](https://lab.ssafy.com/s05-webmobile1-sub3/S05P13A501/-/blob/master/documents/function/DogLive_%EA%B8%B0%EB%8A%A5%EB%AA%85%EC%84%B8%EC%84%9C.pdf)
#### [ 📑 Wiki 보러 가기 ](https://lab.ssafy.com/s05-webmobile1-sub3/S05P13A501/-/wikis/home)


<br><br><br><br>
