package kma.topic5.springwebsample;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {

    @Autowired
    BookService bookService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/wishlist", method = RequestMethod.GET)
    public String wishlist(Model model, Authentication authentication){
        User user = userRepository.findByLogin(authentication.getName()).get();
        List<Book> wishlist = user.getBooks();
        model.addAttribute("books", wishlist);
        return "wishlist";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(){
        return "registration";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(@RequestParam(name = "login") String login,
                           @RequestParam(name = "password") String password) {
        userRepository.save(User.builder().login(login).password(password).build());
        return "redirect:/login";
    }
}
