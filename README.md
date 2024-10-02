### 해당 프로젝트의 요구 사항
* 아래 3가지의 API를 구현한다.
  * 특강 신청 API
  * 특강 선택 API
  * 특강 신청 완료 목록 조회 API
* 각 기능 및 제약 사항에 대해 **단위 테스트를 반드시 하나 이상** 작성한다.
* **다수의 인스턴스로 어플리케이션이 동작하더라도** 문제가 없도록 작성한다.
* **동시성 이슈**를 고려하여 구현한다.

### API 스펙
* 특강 신청 API
  * 특정 userId로 선착순으로 제공되는 특강을 신청하는 API를 구현한다.
  * 동일한 신청자는 한 번의 수강 신청만 성공할 수 있다.
  * 특강은 선착순 30명만 신청 가능하다.
  * 이미 신청자가 30명이 초과되면 이후 신청자는 요청을 실패한다.
* 특강 선택 API
  * 날짜 별로 현재 신청 가능한 특강 목록을 조회하는 API를 구현한다.
  * 특강의 정원은 30명으로 고정이며, 사용자는 각 특강을 신청하기 전 목록을 조회할 수 있다.
* 특강 신청 완료 목록 조회 API
  * 특정 userId로 신청 완료된 특강 목록을 조회하는 API를 구현한다.
  * 각 항목은 특강 ID 및 이름, 강연자 정보를 담고 있어야 한다.

### 어플리케이션 패키지 구조 설계
```
hhpluslectureapply
├─api
│  └─lecture
│      ├─request
│      └─response
├─domain
│  └─lecture
│      └─dto
├─exception
├─infrastructure
│  └─lecture
│      └─entity
└─usecase
    └─lecture
        └─dto
```

### 소프트웨어 아키텍처
![4-layerd-architecture](https://github.com/user-attachments/assets/24447fe2-ec18-46ef-af09-0ab3c845d6d3)

* 채택한 아키텍처는 4-Layered 아키텍처 + 클린 아키텍처
* 애플리케이션의 핵심은 도메인(비즈니스) 계층
* API 계층은 유즈케이스 계층을 의존
* 영속성 계층과 유즈케이스 계층이 도메인 계층을 의존

### ERD (Entity Relationship Diagram)
![ERD](https://github.com/user-attachments/assets/651cf2e2-f832-4388-8e19-e657099ba59e)

* **Lecture** 테이블
  * 특강에 대한 필수적인 정보를 지닌 테이블
  * `auto_increment`로 정의한 id 값과, 특강 제목, 강사명 정보를 지닌 테이블
* **LectureOption** 테이블
  * 특강에 대한 부가적인 정보를 지닌 테이블
  * `auto_increment`로 정의한 id 값과, Lecture 테이블의 id에 해당하는 lectureId, 현재 특강을 신청한 인원 수, 신청 가능 날짜 정보를 지닌 테이블
* **LectureApplyHistory** 테이블
  * 특강 신청을 완료한 내역 정보를 지닌 테이블
  * `auto_increment`로 정의한 id 값과, Lecture 테이블의 id에 해당하는 lectureId, 사용자 id에 해당하는 userId, 특강 신청 내역이 기록된 날짜 정보를 지닌 테이블

#### 테이블 설계에 대한 설명 및 이유
1. **Lecture**, **LectureOption**, **LectureApplyHistory** 테이블에 연관 관계 (FK)를 두지 않았다.
   * 데이터 생성 및 수정 시 항상 무결성 검사를 하게 되어, 특히 `insert`가 많이 발생하는 **LectureApplyHistory** 테이블과 `update`가 많이 발생하는 LectureOption 테이블에서 성능 저하가 발생할 것을 예상했다.
   * 외래키의 제약 조건은 서비스의 확장이 어려워질 수 있다고 판단했다.
2. **Lecture** 테이블에서 **LectureOption** 테이블을 정규화한 점
   * 수강 신청을 할 때 특강의 현재 신청 인원 수를 계속 `update`해야 하는 요구가 존재한다.
   * 빈번한 `update`가 비교적 규모가 작은 테이블에서 수행하는 편이 안정적일 수 있겠다고 판단해 현재 신청 인원 수(`currentApplicants`) 컬럼을 분리할 것을 결정했다.
   * 특강의 제목, 강사명, 신청 날짜 중 비교적 수정이 일어날 가능성이 높아보이는 신청 날짜(`applicationDate`) 컬럼을 분리할 것을 결정했다.
   * LectureOption 테이블을 분리함에 따라 추후 특강에 대한 부가적인 정보를 추가함에 있어 용이할 것으로 판단했다.
3. 사용자 테이블은 생성하지 않았다.
   * 프로젝트 요구사항에서 사용자 도메인의 비즈니스 요구사항은 없었다.
   * 단지 userId로 특강 신청하기, userId로 신청 완료된 특강 목록 조회하기와 같이 userId 값으로 특강에 대해 로직을 수행하는 요구사항만 존재해 userId 값을 가지고 있도록만 테이블을 설계했다.

