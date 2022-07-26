package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // controller 라서 스프링 빈으로 자동 등록됨
public class MemberController {

    private final MemberService memberService;

    @Autowired // spring container 에서 memberService 를 가져온다 but MemberService는 순수한 자바 코드라서 없음
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
