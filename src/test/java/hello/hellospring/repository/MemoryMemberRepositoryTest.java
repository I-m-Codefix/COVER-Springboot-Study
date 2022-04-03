package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest { // 굳이 public으로 안해도 됌.

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // Test는 서로 의존관계없이 끝나야함.
    @AfterEach //@Test하나 끝날 때마다 repository clear.(저장소 비움)
    public void afterEach() {

        repository.clearStore();
    }

    @Test // org.junit.jupiter.api
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();  // Optoinal에서 값을 꺼낼 때는 get()을 통해 가능. (좋은 방법은 아니지만 테스트니까~)
        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(result, member); // org.junit.jupiter.api, 같은지 확인해주는 Assertions.assertEquals 기능 사용.
        //Assertions.assertEquals(null, member);
        assertThat(member).isEqualTo(result); // org.assertj.core.api, 위에 Assertions보다 쓰기 편한 것.
        // Assertions는 Alt+Enter -> static import하면 더 간단하게.

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName(("spring1"));
        repository.save(member1);

        Member member2 = new Member();
        member2.setName(("spring2"));
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
