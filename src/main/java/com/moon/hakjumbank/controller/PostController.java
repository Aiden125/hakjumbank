package com.moon.hakjumbank.controller;

import com.moon.hakjumbank.domain.Member;
import com.moon.hakjumbank.domain.Post;
import com.moon.hakjumbank.service.MemberService;
import com.moon.hakjumbank.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/new")
    public String createForm(Model model) {
        model.addAttribute("postsForm", new PostForm());
        return "posts/createMemberForm";
    }

    @PostMapping("/posts/new")
    public String create(@Valid PostForm form, BindingResult result) {
        // result 바인딩이 되어있으면 에러가 있어도 아래를 실행시켜줌 약간 try catch 문 같은 느낌
        if (result.hasErrors()) {
            return "posts/createPostsForm";
        }

        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());

        return "redirect:/";
    }

//    @GetMapping("/posts")
//    public String list(Model model) {
//        List<Member> members = memberService.findMembers();
//        model.addAttribute("members", members);
//        return "posts/postsList";
//    }
}
