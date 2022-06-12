package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

// alt + enter 누르면 static 을 통해 Assertions 없애기 가능
import java.util.List;

import static org.assertj.core.api.Assertions.*;

// class level 에서 test 가능하다
// 각각의 method 에서도 test 가능

// test 할 때 method 의 순서가 보장이 안된다.
// 따라서 순서와 상관없이 method 별로 따로 동작하게끔 설계해야 한다
// findAll() 이 먼저 수행되므로 findByName() d에서 오류가 난다
// 그러므로 test 끝나고 사용된 data 는 clear 해주어야 한다

// test class 먼저 코드를 작성해 놓고 MemberRepository(구현 클래스) 작성 (순서 뒤집기) -> 테스트 주도 개발(TDD)
public class MemoryMemberRepositoryTest {

    // 인터페이스 객체? 를 구현체 class 로 생성
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // test 실행될 때마다 store 를 지우게 된다
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member=new Member();
        // ctrl + shift + enter 누르면 바로 내려가게 할 수 있음
        member.setName("spring");

        repository.save(member);
        // Optional 에서 꺼낼때 get() 사용
        Member result=repository.findById(member.getId()).get();
        // 출력으로 확인하는 방법
//        System.out.println("result = "+(result==member));
        // 기대되는 것(result), 실제의 것(member)
        // 기대된 것이 나오면 초록색, 안나오면 Test fail 됨
//        Assertions.assertEquals(result,member);
        //
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        // shift + f6 -> 한꺼번에 수정할 때 유용
        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result=repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result=repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
