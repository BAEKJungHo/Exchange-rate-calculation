# Exchange-rate-calculation

## 도메인

- __환율(ExchangeRate)__
    - CurrencyLayer API 의 JSON 응답 결과에 있는 속성을 갖도록 함
    - API 응답 결과 속성 중 quotes 에 환율 정보가 다 들어있음
    - 따라서, 특정 통화(Currency)에 대한 환율을 가져와야하는 역할을 `환율 도메인`에서 담당해야 한다고 판단
- __통화(Currency)__
    - 통화를 표기하기 위한 enum
- __수취국가(RecevingCountry)__
    - 수취 국가는 enum 으로 관리하며, 속성으로 국가이름과, 통화를 가지고 있음
    - 따라서, `RecevingCountry -> Currency` 에 의존하고 있음
    - 만드는 과정에서 `송금국가`는 USD 로 고정이며, CurrencyLayer API 의 응답 결과에 있는 SOURCE 속성이 USD 로 고정이기 때문에 따로 클래스를 만들어서 관리하진 않음

## Resource Naming

- GET `/exchangeRates/form`, produces = MediaType.TEXT_HTML_VALUE
    - 환율 계산 및 송금을 위한 폼
- POST `/exchangeRates/{currency}`
    - 셀렉트 박스로 통화 선택 시(Ex. KRW) 환율을 조회하기 위함
- POST `/exchangesRates`
    - 환율 계산 후 송금

