package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,
        Long>, MemberRepository {
    //인터페이스만으로 기본적인 CURD 설계가 가능하다. 페이징 기능도 자동으로 제공한다.

    Optional<Member> findByName(String name);
}