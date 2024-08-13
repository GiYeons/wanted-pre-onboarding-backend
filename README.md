# 원티드 프리온보딩 백엔드 인턴십 선발과제

## ⚙️ 기술스택

| 기술        | 버전  |
| ----------- | ----- |
| Java        | 17    |
| Spring Boot | 3.3.2 |
| Gradle      |       |
| MySQL       |       |

&nbsp;

## 🛠️ ERD Diagram
![erd](https://github.com/user-attachments/assets/83a2f17c-294a-4b73-bcaa-7cb1e6785bee)

☑️ 하나의 Company는 여러 개의 JobPost를 등록할 수 있습니다.  
☑️ 하나의 JobPost는 여러 개의 Application을 받을 수 있습니다.  
☑️ 한 명의 User는 여러 개의 JobPost에 지원할 수 있습니다.

&nbsp;

## 📝 요구사항

### 1. 채용공고 등록

회사는 채용공고를 등록합니다.

#### ✅ 로직

`POST` /jobPost/create

- 회사의 id를 받아 DB에 등록되어 있는지 확인합니다.

#### JobPostRequest

```json
{
  "company_id": 2,
  "position": "백엔드 주니어 개발자",
  "reward": 300000,
  "content": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "skill": "Django",
  "nation": "한국",
  "region": "서울"
}
```

#### JobPostResponse

```json
{
  "id": 7,
  "company_id": 2,
  "company_name": "원티드",
  "position": "백엔드 주니어 개발자",
  "reward": 300000,
  "content": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "skill": "Django",
  "nation": "한국",
  "region": "서울"
}
```

&nbsp;

### 2. 채용공고 수정

회사는 채용공고를 수정합니다.

#### ✅ 로직

`PUT` /jobPost/{id}

- 채용공고의 id를 받아 DB에 등록된 공고인지 확인합니다.
- 회사의 id를 받아 해당 채용공고를 수정할 권한이 있는지 확인합니다. 회사 정보가 일치하지 않는다면 `AccessDeniedException`를 발생시킵니다.

#### JobPostRequest

```json
{
  "company_id": 2,
  "position": "백엔드 주니어 개발자",
  "reward": 300000,
  "content": "5번 채용공고 수정됨",
  "skill": "Django",
  "nation": "한국",
  "region": "서울"
}
```

&nbsp;

### 3. 채용공고 삭제

회사는 채용공고를 삭제합니다.

#### ✅ 로직

`DELETE` /jobPost/{id}

- 채용공고의 id를 받아 DB에 등록된 공고인지 확인합니다.
- 회사의 id를 받아 해당 채용공고를 삭제할 권한이 있는지 확인합니다. 회사 정보가 일치하지 않는다면 `AccessDeniedException`를 발생시킵니다.

#### JobPostDeleteRequest

```json
{
  "company_id": 2
}
```

&nbsp;

### 4. 채용공고 목록 조회

채용공고 목록을 조회합니다.  
검색어가 없는 경우 전체 목록을 가져오고, 검색어가 있는 경우 검색된 공고만 가져옵니다. **[가산요소]**

#### ✅ 로직

`GET` /jobPost/all  
`GET` /jobPost/all?search="검색어"

- **검색어가 NULL이거나 공백이라면,** 전체 공고를 반환합니다. 이때 id를 기준으로 내림차순 정렬합니다.
- **검색어가 존재한다면,** '회사명', '국가', '지역', '채용포지션', '사용기술' 필드와 비교하여 일치하는 키워드가 포함된 공고를 가져옵니다.
- 키워드 검색 쿼리는 `@Query`를 활용하여 JPQL 문법으로 작성했습니다.

```java
@Query("SELECT DISTINCT jp FROM JobPost jp " +
            "LEFT OUTER JOIN jp.company c " +
            "WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(jp.position) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(jp.content) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(jp.skill) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(jp.nation) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(jp.region) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "ORDER BY jp.id DESC"
    )
    List<JobPost> searchJobPost(
            @Param("keyword") String keyword
    );
```

&nbsp;

### 5. 채용 상세 페이지 조회

채용 상세 페이지를 조회합니다.  
해당 회사가 올린 다른 채용공고가 추가적으로 포함됩니다. **[가산요소]**

#### ✅ 로직

`GET` /jobPost/{id}

- 채용공고의 id를 받아 DB에 등록된 공고인지 확인합니다.
- 현재 공고의 상세 정보 및 동일 회사의 다른 채용공고의 id 리스트를 가져옵니다. `@Query`를 활용해 작성했습니다.

```java
@Query("SELECT jp.id FROM JobPost jp WHERE jp.company.id = :companyId AND " +
            "jp.id <> :currentJobPostId ORDER BY jp.id DESC")
    List<Integer> findByCompanyExceptForCurrentJobPost(
            @Param("companyId") Long companyId,
            @Param("currentJobPostId") Long currentJobPostId);
```

#### JobPostDetailResponse

```json
{
  "id": 6,
  "company_id": 2,
  "company_name": "원티드",
  "position": "백엔드 주니어 개발자",
  "reward": 540000,
  "content": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "skill": "Spring Boot",
  "nation": "한국",
  "region": "서울",
  "other_job_posts": [5, 4]
}
```

&nbsp;

### 6. 채용공고 지원 [가산요소]

사용자는 채용공고에 지원합니다. 단, 공고별로 1회만 지원 가능합니다.

#### ✅ 로직

`POST` /application/create

- 사용자와 채용공고의 id를 받아 DB에 등록되어 있는지 확인합니다.
- 사용자가 해당 채용공고에 지원한 이력이 있는지 확인하고, 있다면 `IllegalStateException` 예외를 발생시킵니다.

#### ApplicationRequest

```json
{
  "job_post_id": 2,
  "user_id": 1
}
```

&nbsp;

## 🪛 Test

Java 오픈소스 프레임워크인 Mockito을 이용하여 모든 요구사항에 대한 유닛 테스트를 진행했습니다.
