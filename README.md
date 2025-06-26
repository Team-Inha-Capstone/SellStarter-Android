# 🛒셀스타터(Sell-Starter)

<img
  src="https://github.com/user-attachments/assets/84124e16-cd7c-4e4b-91ef-3226b4896008"
  width="850"
/>

## 목차

1. [**서비스 소개**](#1)
1. [**기술 스택**](#2)
1. [**주요 기능**](#3)
1. [**기술적 고민**](#4)
1. [**안드로이드 아키텍처 설계**](#5)

<div id="1"></div>

## 서비스 소개
**셀스타터(Sell-Starter)는** 
<br>📦물류 창고가 없는 1인 스마트스토어를 위한 물류 관리와 예측,  주문 통합  관리 솔루션입니다.
<br>(2025년 1학기 인하대학교 컴퓨터공학 종합설계 프로젝트로 진행하였습니다.)

<div id="2"></div>

## 기술 스택
|                            | Technology                                                  |
|------------------------------------------|-------------------------------------------------------------|
| **UI**                                   | Jetpack Compose                                             |
| **Architecture**                         | MVVM, Clean Architecture, Single-Activity Architecture      |
| **Android Jetpack**       | ViewModel, Navigation, WebView       |
| **Networking**                           | Retrofit, OkHttp                                            |
| **DI & Serialization & State**           | Hilt, Kotlin Serialization, UiState                         |
| **Concurrency & Streams**                | Coroutine, Flow                                             |
| **Imaging, Barcode & Charting**          | Coil (Image), Zxing (Barcode), Vico (Graph)                 |
| **Notification**          | FCM (Firebase Cloud Messaging)                 |

<div id="3"></div>

## 주요 기능
![image](https://github.com/user-attachments/assets/3e70ade3-ba4f-43c2-8c77-a91dd81b5759)
<img width="1104" alt="스크린샷 2025-06-14 오후 11 07 03" src="https://github.com/user-attachments/assets/67520ddd-6ab8-44ba-a624-9c39e59439cd"/>

![image](https://github.com/user-attachments/assets/cda5cb57-e936-4c31-97bd-0807d5595f93)
![image](https://github.com/user-attachments/assets/bf5bae64-fcfc-4155-88df-4d192452a063)
![image](https://github.com/user-attachments/assets/af4dccba-384f-4051-a67f-a9bc4b40bc44)
![image](https://github.com/user-attachments/assets/0f9e38eb-04ca-48c2-82f9-622183f39031)
![image](https://github.com/user-attachments/assets/a8de3990-5672-4e63-914c-b5f0fe191b70)
![image](https://github.com/user-attachments/assets/880406a0-460f-4566-b376-72e60bcd0ad6)

<div id="4"></div>

## 기술적 고민
### Zxing을 활용한 재고 바코드 자동 생성, 저장 구현

- Zxing 라이브러리를 활용해 재고 등록 시 **랜덤 바코드를 자동 생성**하는 기능을 구현함
- 생성된 바코드를 앱 내 Modal에 실시간으로 렌더링하며, 사용자 클릭 시 **해당 바코드와 제품명이 포함된 이미지를 디바이스에 저장**할 수 있도록 처리함

### 앱 내 글자 크기 조정 기능 구현

- 고령 사용자 및 시력 취약층을 고려해 **글자 크기 조정 기능을 직접 개발**
- 사용자의 설정값을 Jetpack `DataStore`에 저장하여, 앱 전체에서 **일관된 텍스트 크기 반영**이 가능하도록 설계 / 자체 `Typography` 디자인 시스템을 구축하여 **글자 크기별 스타일과 스케일을 유기적으로 조정**
- 폰트 크기 변경 시 발생할 수 있는 **레이아웃 깨짐 및 오버플로우 문제는 Modifier 조합 및 `Box`, `Column`의 유연한 배치를 통해 해결**함 / 해당 기능은 사용자 접근성 향상과 다양한 기기 환경 대응에 실질적 효과를 보임

### 재고와 주문 리스트 페이지네이션 구현

- 재고 리스트는 실시간 스크롤 탐색에 적합하도록 `LazyColumn`과 `LazyListState`를 활용한 **무한 스크롤 방식**으로 구현
- 주문 리스트는 특정 주문을 되짚어 확인하는 사용 흐름을 고려해 페이지 번호 기반 탐색 방식(1,2,3)을 적용하여 사용자 직접 이동 가능
- 두 방식 모두 **API 중복 호출 방지, 로딩 처리, 마지막 페이지 여부 판단 등 상태 관리 최적화**로 안정적인 UX 제공


<div id="5"></div>

## 안드로이드 아키텍처 설계
### 디자인 시스템
<img
  src="https://github.com/user-attachments/assets/220ad188-99ee-48ff-88dc-9a604d947784"
  width="800"
/>

### 아키텍처
<img
  src="https://github.com/user-attachments/assets/43d59802-3b81-4aec-855e-f08be1dad0fe"
  width="800"
/>

### 폴더링
