## micro-blog

### 1️⃣ ERD

<img width="999" alt="스크린샷 2025-04-30 오전 9 31 21" src="https://github.com/user-attachments/assets/14b31c3d-95ac-4810-adef-9129315da916" />

일반적인 게시판을 생각하고 만들었습니다.

| 테이블 | 엔티티 |
|---|---|
| users | 사용자 |
| user_profile | 사용자 프로필 |
| posts | 게시글 |
| comments | 댓글 |

- 일대일 관계 : 한 명의 사용자는 하나의 프로필을 가진다.
- 일대다 관계 : 한 명의 사용자는 여러 개의 게시글을 작성할 수 있다.
- 다대다 양방향 관계 : 하나의 게시글에 여러 개의 댓글을 작성할 수 있고, 사용자는 본인이 작성한 모든 댓글을 확인할 수 있다.

### 2️⃣ 프로젝트 구조

<img width="316" alt="스크린샷 2025-04-30 오전 10 15 34" src="https://github.com/user-attachments/assets/94521043-40cd-4626-a666-29a70a61be0c" />

main 브랜치에 jdbc, jpa 패키지가 각각 들어있습니다. 

공통으로 사용되는 항목 (exception, swagger) 은 common 패키지에 있습니다. 

jdbc 와 jpa 각 패키지에 들어있는 dto, entity 등은 이름은 같지만 내용이 조금씩 달라서 합치지 않고 따로 작성한 것이니 참고 바랍니다.

### 3️⃣ 프로젝트 실행

1. 로컬에서 애플리케이션을 실행시키면 hibernate 가 테이블을 자동 생성합니다.
2. swagger 로 API 명세를 확인할 수 있습니다. (http://localhost:8080/swagger-ui/index.html)
3. POSTMAN 으로 API를 테스트해볼 수 있습니다.
    - postman_collection.json 파일 다운로드 (경로 : https://github.com/ghkdusghd/micro-blog/tree/main/postman)
    - POSTMAN 실행
    - workspace 에서 import 클릭하여 다운받은 json 파일을 넣으면 Collection 생성됨 (완료)
