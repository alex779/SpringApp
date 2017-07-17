package com.alex.dbproject.controller;

import com.alex.dbproject.domain.UserDao;
import com.alex.dbproject.model.User;
import static com.alex.dbproject.validator.DataValidator.mailValidate;
import static com.alex.dbproject.validator.DataValidator.passwordValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping(value = "/user")
    public String user(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("email", user.getEmail());
        model.addAttribute(user);

        return "user";
    }

    @RequestMapping(value = "user/edit/{id}", method = RequestMethod.GET)
    public String userEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("userForm", userDao.getById(id));
        return "userEdit";
    }

    @RequestMapping(value = "user/edit/{id}", method = RequestMethod.POST)
    public String userEdit(
            @ModelAttribute("userForm") User userForm,
            Model model,
            @PathVariable("id") int id) {

        if (!mailValidate(userForm.getEmail())) {
            model.addAttribute("mailErr", "Wrong email address");
            return "userEdit";
        } else if (!passwordValidate(userForm.getPassword())) {
            model.addAttribute("passErr", "Wrong password");
            return "userEdit";
        } else if (!userForm.getPassword().equals(userForm.getPassConf())) {
            model.addAttribute("passConfErr", "Password confirmation doesn't match");
            return "userEdit";
        }

        User user = userDao.getById(id);
        user.setEmail(userForm.getEmail());
        user.setPassword(encoder.encode(userForm.getPassword()));
        userDao.updateUser(user);

        return "redirect:/user";
    }
}
