package ru.bull.pastebin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bull.pastebin.dto.PostRequest;
import ru.bull.pastebin.dto.PostResponse;
import ru.bull.pastebin.model.Post;
import ru.bull.pastebin.model.Post;
import ru.bull.pastebin.service.PostService;

import java.util.Optional;

@Controller
public class MainController {
    private final PostService postService;

    public MainController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/")
    public String createPost(){
        return "main";
    }

    @GetMapping("/post/{hash}")
    public String getPost(@PathVariable String hash, Model model){
        Optional<String> postText = postService.getPost(hash);

        if (postText.isPresent()) {
            model.addAttribute("content", postText.get());
            model.addAttribute("notFound", false);
        } else {
            model.addAttribute("notFound", true);
        }

        return "post";
    }

    @PostMapping("/createpost")
    @ResponseBody
    public PostResponse createPost(@RequestBody PostRequest request){
        Post post = postService.createPost(request.getContent());
        String link = "/post/" + post.getHash();
        return new PostResponse(link);

    }

}
