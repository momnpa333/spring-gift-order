# spring-gift-enhancement

## step3 기능 구현 목록

### 기능 요구 사항

상품 옵션의 수량을 지정된 숫자만큼 빼는 기능을 구현한다.

- 별도의 HTTP API를 만들 필요는 없다.
- 서비스 클래스 또는 엔티티 클래스에서 기능을 구현하고 나중에 사용할 수 있도록 한다.

### 프로그래밍 요구 사항

- 구현한 기능에 대해 적절한 테스트 전략을 생각하고 작성한다.
- 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드에 대해 단위 테스트를 구현한다.

### 힌트

```
var option = optionRepository.findByProductId(productId).orElseThrow();
option.subtract(quantity)
```

- [x] 상품 옵션의 수량을 지정된 숫자만큼 빼는 기능을 구현.
- [x] 상품 수량에 대해 동시성 구현

## step3로 넘어오면서 리팩토링 해야할 목록

- [ ] 테스트 코드 service 단위 테스트
- [ ] controller 통합 테스트
- [ ] 동시성 관련 테스트 코드 작성
- [ ] pageRequest 작성
- [ ] pageResponse 함수형 인터페이스 사용하는 부분 수정
- [ ] 상품 조회 API 테스트 코드 수정
- [ ] gateProductService 제작 필요성