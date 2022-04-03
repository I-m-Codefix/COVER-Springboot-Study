package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //hash map 사용(단순하게~), sequence는 0,1,2키값 생성하는 것.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null이 발생할 가능성이 있으면 Optional로 감쌈.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))  //루프로 돌림. 람다 사용~ member.getName()과 name이 같은지 확인
                .findAny(); // 같은 경우에만 필터링 되고, 찾으면 Optional로 반환.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store.values 는 멤버들을 쭉 반환.
    }

    public void clearStore() {
        store.clear(); // store을 싹 비움.
    }
}

// 동작하는지 안하는지 확인하는 기가막힌 방법이 바로 Testcase를 사용하는 것입니다..! => JUnit 프레임워크를 실행해서 Test..!!