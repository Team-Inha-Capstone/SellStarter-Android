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
### [🔗시연영상](https://www.youtube.com/watch?v=IouDCotkOm0)
**셀스타터(Sell-Starter)는** 
<br>📦물류 창고가 없는 1인 스마트스토어를 위한 물류 관리와 예측,  주문 통합  관리 솔루션입니다.
<br>(2025년 1학기 인하대학교 컴퓨터공학 종합설계 프로젝트로 진행하였습니다.)

<div id="2"></div>

## 기술 스택
|                            | Technology                                                  |
|------------------------------------------|-------------------------------------------------------------|
| **UI**                                   | Jetpack Compose                                             |
| **Architecture**                         | MVVM, Clean Architecture, Single-Activity Architecture      |
| **Android Jetpack**       | ViewModel, Navigation, WebView, DataStore       |
| **Networking**                           | Retrofit, OkHttp                                            |
| **DI & Serialization & State**           | Hilt, Kotlin Serialization, UiState                         |
| **Test**          | Junit5, MockK, Compose UI Test            |
| **Concurrency & Streams**                | Coroutine, Flow                                             |
| **Imaging, Barcode & Charting**          | Coil (Image), Zxing (Barcode), Vico (Graph), Lottie (Animation)   |
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
### Zxing 활용한 재고 바코드 자동 생성 및 저장 구현
- Zxing 라이브러리를 활용해 재고 등록 시 **바코드 자동 생성** 기능을 구현함
- 생성된 바코드를 모달에 렌더링하고, `ComposeView.drawToBitmap()`로 캡처한 바코드와 제품명 이미지를 기기에 저장해 **사용자 재고 관리 편의성 구축**

### 앱 내 글자 크기 조정 기능 구현
- 솔루션 내 다양한 연령층, 시력 취약층 사용자를 고려해 앱 내 **글자 크기 조정 기능을 직접 개발**
- 자체 `Typography` 디자인 시스템을 통해 **글자 스케일을 유기적으로 관리하여, 사용자 접근성 및 다양한 기기환경의 레이아웃 안정성 확보하고,** 설정값을 `DataStore`에 저장하여 앱 전체에 **일관된 텍스트 크기 반영되도록** 설계

### 재고·주문 리스트 각각 UX 페이지네이션
- 재고 리스트는 실시간 스크롤 탐색에 적합하도록 `LazyVerticalGrid`와 `LazyGridState`를 활용해 `onLoadMore()`를 트리거하는 **무한 스크롤 방식**으로 구현
- 주문 리스트는 특정 주문을 되짚어 확인하는 사용자 흐름을 고려해, **페이지 번호 기반 탐색 방식** 적용하여 직접 이동하도록 구현
- 두 방식 모두 **API 중복 호출 방지, 로딩 처리, 마지막 페이지 여부 판단 등 상태관리**로 안정적인 UX 제공하고자 함.

### Unit Test·Compose UI Test 코드 작성
- `JUnit5`과 `MockK`로 재고·주문 UseCase 레이어의 핵심 비즈니스 로직 및 예외 시나리오를 단위 테스트로 검증하고, CI 파이프라인 내 테스트 자동화 환경 구축하여 배포 안정성 갖추고자 함.
- `ComposeTestRule`을 통해 재고 등록·리스트 조회·상세 진입 등 주요 UI 시나리오를 테스트하고, QA 검증으로 사용자 경험 안정성 확보

### 🔗[리팩토링을 위한 MVI 패턴 학습]("https://hyeonlog-developer.tistory.com/217")

<div id="5"></div>

## 안드로이드 아키텍처 설계
### 디자인 시스템
<img
  src="https://github.com/user-attachments/assets/be37ca26-67f6-411f-bcfb-fe57b46ae31d"
  width="800"
/>

### 아키텍처
<img
  src="https://github.com/user-attachments/assets/15fecd5b-2963-41bf-bf56-b157ca338170"
  width="800"
/>

### 폴더링

