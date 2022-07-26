package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

// Test 는 한글로 바꾸어도 된다 (실제 prodution 되는 코드는 한글 x) ,test 코드는 build 시 포함 안됨

class MemberServiceTest {

//    MemberService memberService=new MemberService();
    // MemberService 에서 memberRepository 객체와 Test 에서의 memberRepository 객체가 달라서
    // 객체 변수(store) 접근시 다른 store 로 접근할 수 있다
    // 다른 repository 를 이용하기 때문에 문제가 된다
//    MemoryMemberRepository memberRepository=new MemoryMemberRepository();
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // test 전에 같은 memberRepository 를 가지도록 할 수 있다 (service 와 test 모두 같음)
    // 이와 같은 상황을 DI(Dependency Injection) 의존성 주입 이라고 한다
    @BeforeEach // 각각의 test 메소드 실행 전에 호출되어 처리
    public void beforeEach(){
        memberRepository =new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);
    }

    // test 실행될 때마다 store 를 지우게 된다
    @AfterEach // 각각의 test 메소드 실행 후에 호출되어 처리
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given : 이런 data 를
        Member member=new Member();
        member.setName("spring");

        //when : 이러한 경우에
        Long saveId=memberService.join(member);

        //then : 검증 부분
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 예외인 경우가 터지는지 확인하는 것도 중요하다
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // message 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // try catch 활용
/*        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            // 중복 예외 터져서 정상적으로 수행된 경우
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
    */
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}