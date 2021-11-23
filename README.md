# Dicom Service Management Project
## :dart: Objective
Dicom 의료 영상 데이터를 저장, 관리하며 시각화 기능과 머신러닝 개발을 지원하는 SW개발
## :bar_chart: Success metrics
의료데이터 관리 및 시각화 지원 기능을 구현하여 v1.0 배포를 목표로 한다.(option 제외)
## 📓 Requirements
### 1. 의료 데이터 관리
Requirement | User Story | Notes | Priority
------------- | ------------- | ------------- | -------------
Dicom 업로드, 다운로드 API 구현 | 개발자는 REST API를 통해서 데이터 업로드, 다운로드를 요청 할 수 있다. | orthanc 서버를 이용해서 구현이 가능하다. |1
Dicom 업로드, 다운로드 클라이언트 구현 | 사용자는 웹 UI를 통해서 Dicom데이터 업로드, 다운로드를 요청 할 수 있다.| React를 이용하여 UI를 구성하고 REST API형식으로 미들웨서 서버에 요청하도록하여 구현이 가능하다| 2
Dicom 리스트 뷰 기능 | 사용자는 저장되어 있는 Dicom 리스트를 웹 UI를 통해서 확인 할 수 있다. | React를 이용하여 UI를 구성하고 REST API형식으로 미들웨서 서버에 요청하도록하여 구현이 가능하다. | 3
프로젝트별 데이터 관리 | 사용자는 웹 UI를 통해서 프로젝트별로 데이터를 그룹화 할 수 있으며 접근권한을 설정 할 수 있다. | MongoDB에 사용자 계정 정보와  Dicom 메타데이터를 연결함으로써 설정이 가능하다. | 4
영상 데이터 업로드, 다운로드 클라이언트 구현 | 사용자는 웹 UI를 통해서 영상 데이터 업로드, 다운로드를 요청 할 수 있다. | 영상데이터를 입력받아 이미지 슬라이싱 후 Dicom형태로 변환하여 구현이 가능하다.(가정) | 5(>v1.0)
### 2. 의료 데이터 시각화 지원
Requirement | User Story | Notes | Priority
------------- | ------------- | ------------- | -------------
Dicom 파일 선택 기능 | 사용자는 웹 UI에 있는 Dicom 리스트 뷰에서 한 행을 클릭하여 해당 Dicom 파일을 시각화 하여 볼 수 있다. | 각 리스트에 OHIF 뷰어를 호출하는 링크를 설정함으로써 구현이 가능하다. | 1
줌 인/아웃, 드래그 등 기분 툴 구현 | 사용자는 웹 UI를 통해서 이미지를 다루는 툴을 사용할 수 있다. | OHIF 오픈소스를 통해서 구현이 가능하다. | 2
Segmentation 뷰어 기능 구현 | 사용자는 웹 UI를 통해서 Dicom에 Segmentation이 적용된 이미지를 확인 할 수 있다. | Dicom SEG 파일을 orthan서버에 업로드 함으로써 구현이 가능하다. | 3
Annotation(ROI) 작성 기능 구현 | 사용자는 웹 UI를 통해서 이미지 위에 Annotation을 작성할 수 있으며 서버에 저장이 가능하다. | OHIF에는 기능이 구현되어 있지 않아 추후 기능 구현이 필요하다. 작업 난이도 또한 상당히 높다. | 4(>v1.0)
## 🏗️Architecture
![image](https://user-images.githubusercontent.com/30094719/142571856-107b52ac-df5a-47c1-9242-1a5d5d50d9f0.png)


## Usage
1. DicomServer의 README.md를 참조하여 서버 실행
2. DicomClient/dicom-clinet의 README.md 를 참조하여 클라이언트 서버 실행
