package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // controller 라서 스프링 빈으로 자동 등록됨
public class MemberController {

    private final MemberService memberService;

    @Autowired // spring container 에서 memberService 를 가져온다 but MemberService는 순수한 자바 코드라서 없음
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        // 회원가입 끝나고 홈 화면으로 보내기
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        // members 라는 List 객체를 "members"에 담아 model에 넘긴다
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
