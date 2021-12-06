# 🐶 독립 Dog-Live (獨立)

> 서울 5반 1팀 견과류 🥜 조다운, 이상현, 이영주, 최혜원, 한승희

SSAFY 관통프로젝트 우수상 🥈

<br>

![image](https://user-images.githubusercontent.com/61124319/130741327-74d6b5dc-82b0-45e4-847b-d973818b305c.png)

<br>

### 🔗 [독립 라이브 서비스 즐기러가기](https://doglive.co.kr)

### 💻 [직접 실행해보기! 빌드 및 배포 가이드](https://github.com/2021-Dog-Live/Dog-Live/blob/master/exec/A501_%EB%8F%85%EB%A6%BD_%ED%8F%AC%ED%8C%85_%EB%A7%A4%EB%89%B4%EC%96%BC.md)

### 📺 [독립 UCC Full.ver](https://www.youtube.com/watch?v=0WWNJ1AWsgQ&t=7s)


<br><br>

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

![image](https://user-images.githubusercontent.com/61124319/130740383-42a5b8a7-e59c-4da1-bbf5-96447d38937d.png)

<br><br>

### 🎈 서버 아키텍처

![image](https://user-images.githubusercontent.com/61124319/130740427-5ed3f8c8-31d6-4709-89e0-c35eff808766.png)

<br><br>

### 🔍 ERD

![image](https://user-images.githubusercontent.com/61124319/130740501-ef191746-8396-4e49-8323-f10ca85a0923.png)

<br><br>

### 💌 기능명세서

[📁 기능명세서 보러가기](https://www.notion.so/ssafyseoul5-1/b41fb130e68846e9981052ba6777def4)
<br><br>

### 🎨 와이어프레임

[📁 와이어프레임 보러가기](https://www.figma.com/file/8qu5MvPdGtlTdXLOIcX8af/%EB%8F%85%EB%A6%BD-Dog-Live?node-id=0%3A1)

<br><br>

### 📖 스토리보드

[📁 스토리보드 보러가기](https://www.figma.com/file/8qu5MvPdGtlTdXLOIcX8af/%EB%8F%85%EB%A6%BD-Dog-Live?node-id=26%3A2)

<br><br>

### 💎 핵심 기능

    🔑 WebRTC를 통한 화상 상담
    🔑 매칭 알고리즘
    🔑 SNS 형식 커뮤니티

![image](https://user-images.githubusercontent.com/61124319/130740586-ac100504-4151-4582-bd65-ccbdc6d6ee33.png)
![image](https://user-images.githubusercontent.com/61124319/130740609-16492586-e5ee-40d0-bcec-4be27b6f376a.png)
![image](https://user-images.githubusercontent.com/61124319/130740632-3264b5fd-7d08-448d-8fba-378405caebae.png)
![image](https://user-images.githubusercontent.com/61124319/130740654-de537f49-ac58-4e32-b108-6e9a2cad6677.png)
<br><br>

## 🥜 구현 결과

#### 🔗 [독립 Version 0.2.1 즐기러가기](http://i5a501.p.ssafy.io/)

<br><br>

## 🥜 실행 가이드

#### 💻 [포팅 매뉴얼](https://www.notion.so/ssafyseoul5-1/aeb2f5a759cc415fbc5450521673f964)

#### 💛 [독립 사용 설명서](https://www.notion.so/ssafyseoul5-1/938d94c6b64d43bb8ccfed1e0a0dbfbd)

<br><br>

## 🥜 팀원 소개 및 역할

<br><br>
|Name|이상현|이영주|조다운|최혜원|한승희|
|-----|-----|-----|------|----|-----|
|Profile|![image](https://user-images.githubusercontent.com/61124319/130740694-34f9a3e5-f742-41cb-b752-c76f422b1d61.png)|![image](https://user-images.githubusercontent.com/61124319/130740722-616db638-688c-4196-959e-fe22a472dc3e.png)|![image](https://user-images.githubusercontent.com/61124319/130740752-b0009eb0-0b37-4ca9-a73b-af4546a638f0.png)|![image](https://user-images.githubusercontent.com/61124319/130740778-f09b08db-8b53-4766-8c70-aa3b350d284d.png)|![image](https://user-images.githubusercontent.com/61124319/130740827-ab576311-288f-46e4-bf53-9ef398ce8b53.png)|
|Position|Frontend, Backend Develop & UI/UX|Backend Develop & CI/CD| 👑팀장 & Backend Develop & CI/CD|Backend Develop & CI/CD|Frontend & UI/UX|
|Git|[@kimkuan](https://github.com/kimkuan)|[@0JUUU](https://github.com/0JUUU)|[@jodawoooon](https://github.com/jodawoooon)|[@hiii456](https://github.com/hiii456)|[@chengxxi](https://github.com/chengxxi)|

<br><br>

## 🥜 개발 문서

#### [ 🧾 기능명세서 ](https://www.notion.so/ssafyseoul5-1/b41fb130e68846e9981052ba6777def4)

#### [ 📑 Notion ](https://www.notion.so/ssafyseoul5-1/1-a1fcecce811a42f4bc399dd562d1ce2f)

<br><br><br><br>
