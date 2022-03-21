package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // save하면 저장소에 회원저장
    Optional<Member> findById(Long id); //없으면 null을 반환하는데, 이것 대신 Optional 방식 사용.
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
