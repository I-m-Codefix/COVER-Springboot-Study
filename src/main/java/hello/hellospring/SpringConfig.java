package hello.hellospring;

//import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

        import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

/*
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

*/

/*
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
 */

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

/* TimeTraceAop의 @Component 를 다르게 쓰기.
    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
 */

    /*
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);        // 다형성을 활용하는 걸 편리하게 하도록 스프링에서 제공해줌.
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);

    }
}
     */
}
