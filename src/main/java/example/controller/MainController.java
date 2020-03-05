package example.controller;

import example.domain.Post;
import example.repo.PostRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    @Value("${spring.profiles.active}")
    private String profile;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }
}
