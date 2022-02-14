package kma.topic5.springwebsample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyFirstController {

    @RequestMapping("/some-path/{id}/other-stuff")
    public String somePathHandler(
        @PathVariable("id") int id,
        @RequestParam("page") int page,
        Model model
    ) {
        model.addAttribute("myPageParam", page);
        model.addAttribute("myIdParam", id);
        return "my-first-template-page";
    }
}
