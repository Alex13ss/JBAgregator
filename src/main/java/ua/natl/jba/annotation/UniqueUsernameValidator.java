package ua.natl.jba.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import ua.natl.jba.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return userRepository == null || userRepository.findByName(username) == null;
    }

}
