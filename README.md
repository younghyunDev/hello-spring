#Day4
##AOP
프로그램을 할때 보면 서비스에 관련된 핵심 사항은 아니지만 보조적인 코드가 추가되어야할 때가 있다. 예를 들면 각 함수의 실행시간을 찍는 경우라고 할 수 있다. 각 함수의 실행 시간을 늘리기 위해서는 우리가 직접 모든 함수에 코드를 추가해야되는데 함수가 1,000개라고 한다면 코드 삽입을 하는 것에 큰 시간이 들게된다.
spring은 이것을 AOP(Aspect Oriental Programming)를 통해서 해결을 할 수 있다. Aop를 적용하면 Controller -> service -> repository 의 형태에서 프록시를 추가하여 코드를 주입시켜준다.
 `@Around("execution(* hello.hellospring..*(..))")`를 통해서 해당하는 파일들에 코드를 삽입시켜줄 수 있다.
 자세한 부분 spring을 공부하면 깊게 해봐야될 부분이다.

# Day3
## 스프링 DB 접근 기술
### JDBC

```java
implementation 'org.springframework.boot:spring-boot-starter-jdbc'
```
`build.gradle` 파일에 라이브러리를 추가해줌으로써 JDBC를 활용할 수 있다.

JDBC를 활용하게 되면 직접 sql문을 작성해야 된다는 큰 번거로움이 있다. 현재는 잘 사용하지 않는다.
JDBC는 자바에서 DB 접근을 쉽게 해주게 한다. 하지만 JDBC를 활용하게 되면 매우 많은 반복이 들어가게되어 비효율을 초래한다. 
이를 해결하기 위해서 JDBC 템플릿을 사용.

기존 메모리를 통해서 데이터를 저장해둔 것을 JDBC를 이용하여 DB에 저장을 할 수 있게 되었다.
`MemoryMemberRepository`에서 `JdbcMemberRepository`로 수정함으로써 간단하게 천이를 끝낼 수 있다.
여기에서 Spring 의 `DI(Dependency Injection` 힘을 볼 수 있다. 인터페이스를 갖게 설계한 뒤에 클래스를 바꿔줌으로써 기존 메모리 기반에서 DB기반으로 손쉽게 천이가 가능하다.

`SpringConfig`에서 `dataSource`는 데이터베이스에 커넥션을 획득할 때 사용하는 개체인데 스프링 부트는 데이터베이스 커넥션 정보를 바탕으로 DataSource를 생성하고 스프링 빈으로 만들어두어 `DI`를 받을 수 있다.
### 스프링 통합테스트
지금까지는 단위테스트만을 진행하며 회원 가입, 조회  등의 기능을 테스트해보았다.
하지만 Spring에서 통합적으로 테스트를 하는 과정이 필요하기 때문에 통합과정 테스트를 진행하였다.

`@SpringBootTest`: 애노테이션을 통해서 스프링 컨테이너와 테스트를 함께 실행할 수 있게 해준다.

`@Transactional` : 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에는 롤백을 해줌을 통해서 DB에는 영향을 주지 않아 다음 테스트에 영향을 주지 않는다.

### JPA
기존에 JDBC를 활용할 때는 SQL을 직접 이용했었다. 기존에는 SQL을 사용하였기 때문에 데이터 중심의 설계를 하였다. 하지만 Spring은 객체 중심의 프레임워크라고 할 수 있다. 역할과 객체를 중심으로 설계를 하고 우리는 데이터보다는 각 객체에 집중해서 데이터를 이용할 수 있는데 JPA가 이것을 가능하게 해준다.
spring의 다형성이라는 장점은 `spring_configure`를 수정해주는 것만으로도 천이가 가능하게 될 것이다. `spring-boot-starter-data-jpa`는 내부에 JDbc 관련 라이브러리를 포함한다.
 JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야하기 때문에 `org.springframework.transaction.annotation.Transactional을 사용해야 된다. 스프링은 해당 클래스에서 메서드를 실행할때 트랜잭션을 시작하고 종료가 되면 트랜잭션을 커밋하고 예외가 난다면 롤백을 하도록 한다.

# DAY 2
## 스프링 빈과 의존 관계
### 의존 관계란 무엇인가?
생성장에 `@Autowired`가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아 넣어준다. ( Dependency Injection)


스프링빈은 component Scan을 통해서 자동으로 Controller, Service, Repository 를 자동으로 주입시켜준다.
싱글톤은 component를 하나씩만 두는 것을 싱글톤이라 하며 보통은 싱글톤 구조를 이용하여 설계를 한다.

###스프링 빈을 주입하는 방법
- 컴포넌트 스캔과 자동 의존관계 설정
  `@component` 애노테이션이 있으면 자동으로 등록된다.
   `@Controller`,`@Service`,`@Repository` 는 `@Component`를 상속받고있다.

  `@Autowired`를 사용하면 객체 생성 시점에 해당 스프링빈을 찾는다. 

- 자바 코드로 직접 스프링 빈 등록하기
  configure 파일을 통해서 스프링 빈을 등록해준다.
  정형화된 controller,service,repository 같은 경우 컴포넌트 스캔을 사용하지만 상황에 따라는 구현클래스 변경이 일어나는 경우 configure를 통해서 스프링ㅁ 빈으로 관리한다.


## MVC 패턴
### 홈화면 추가
`@GetMapping("/")` 을 통해서 홈화면이 왔을때 어떤 화면을 return 할지를 정해줄 수 있다.
컨트롤러는 static보다 우선순위가 먼저이다.

### 등록
`@PostMapping`을 통해서 해당 html에서 보내준 정보를 처리하는 방식을 사용할 수 있다.
이때 name을 통해서 어떤 정보를 받아 오는지를 정해줄 수 있다.
form.getName() 사

### 조회
@GetMapping 을 통해 조회 화면으로 이동시켜 보여줄 수 있다.
현재는 메모리에 등록을 하기 때문에 종료하게 되면 데이터가 휘발된다.
이 것을 H2database를 통해서 DB로 관리 하도록 한다.



