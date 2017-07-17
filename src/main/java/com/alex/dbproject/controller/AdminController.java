package com.alex.dbproject.controller;

import com.alex.dbproject.domain.UserDao;
import com.alex.dbproject.model.User;
import com.alex.dbproject.validator.DataValidator;
import static com.alex.dbproject.validator.DataValidator.loginValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @Autowired
    private DataValidator validator;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("list", userDao.list());
        return "admin";
    }

    @RequestMapping(value = "admin/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("userForm", userDao.getById(id));
        return "adminEdit";
    }

    @RequestMapping(value = "admin/edit/{id}", method = RequestMethod.POST)
    public String edit(
            @ModelAttribute("userForm") User userForm,
            Model model,
            @PathVariable("id") int id) {

        if (!loginValidate(userForm.getUsername()) || userDao.loadByUsername(userForm.getUsername()) != null) {
            model.addAttribute("error", "Wrong input. Name can't be empty or shorter than two characters.");
            return "adminEdit";
        }

        User user = userDao.getById(id);
        user.setUsername(userForm.getUsername());
        user.setRoleUser(userForm.getRoleUser());
        userDao.updateUser(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "admin/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("userForm", new User());
        return "add";
    }

    @RequestMapping(value = "admin/add", method = RequestMethod.POST)
    public String add(
            @ModelAttribute("userForm") User userForm,
            BindingResult bindingResult,
            Model model) {

        validator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "add";
        }

        userForm.setPassword(encoder.encode(userForm.getPassword()));
        userDao.save(userForm);

        return "redirect:/admin";
    }

    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable("id") int id) {

        userDao.delete(id);
        return "redirect:/admin";
    }
}
