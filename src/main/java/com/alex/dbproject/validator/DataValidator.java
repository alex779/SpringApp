package com.alex.dbproject.validator;

import com.alex.dbproject.domain.UserDao;
import com.alex.dbproject.model.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DataValidator implements Validator {

    @Autowired
    private UserDao dao;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");

        if (!loginValidate(user.getUsername())) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (dao.loadByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

        if (!passwordValidate(user.getPassword())) {
            errors.rejectValue("password", "Size.userForm.password");
        }
        if (!user.getPassConf().equals(user.getPassword())) {
            errors.rejectValue("passConf", "Diff.userForm.passConf");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

        if (!mailValidate(user.getEmail())) {
            errors.rejectValue("email", "Size.userForm.email");
        }
    }

    public static final Pattern MAIL
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean mailValidate(String emailStr) {
        Matcher matcher = MAIL.matcher(emailStr);
        return matcher.find();
    }

    public static final Pattern PASSWORD
            = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}");

    public static boolean passwordValidate(String passStr) {
        Matcher matcher = PASSWORD.matcher(passStr);
        return matcher.find();
    }

    public static final Pattern LOGIN
            = Pattern.compile("([a-zA-Z0-9]{3,10})+"); //TODO

    public static boolean loginValidate(String loginStr) {
        Matcher matcher = LOGIN.matcher(loginStr);
        return matcher.find();
    }

    public static final Pattern NAME
            = Pattern.compile("([a-zA-Z]{3,10})+"); //TODO

    public static boolean nameValidate(String nameStr) {
        Matcher matcher = NAME.matcher(nameStr);
        return matcher.find();
    }

}
