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



