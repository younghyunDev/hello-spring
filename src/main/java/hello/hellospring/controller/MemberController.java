package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    //다른 컨트롤러에서 MemberService를 가져다 쓰는 문제가 생긴다.
    //private final MemberService memberService = new MemberService();

    //spring에 등록을 하고 쓰자
    private final MemberService memberService;

    //스프링빈에 등록되어있는 멤버 서비스와 연결을 시켜준다. dependency injection
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm.html";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member= new Member();
        member.setName(form.getName());

        System.out.println("member = "+ member.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members=memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
