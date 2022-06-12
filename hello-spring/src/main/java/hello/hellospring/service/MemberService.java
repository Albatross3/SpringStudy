package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// service 클래스는 비즈니스와 가까운 용어를 사용해야 한다
// ctrl + shift + t 하게 되면 자동으로 test class 생성가능
public class MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    // 외부에서 memberRepository 를 생성하게끔 만들어준다
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 x
        // ctrl+alt+v 하면 변수 추출하기 (반환값과 변수 등장)
        // result 가 null 이 아니라 값이 있으면 throw 동작함
        // optional 로 감싸서 반환해주기에 ifPresent()오 같은 메소드 활용 가능
        // get() 바로 쓰는 것은 권장되지 않음

        // ctrl+alt+shift+t 눌러서 extract method 를 통해 method 추출
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId(); // 임의로 아이디만 반환
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
