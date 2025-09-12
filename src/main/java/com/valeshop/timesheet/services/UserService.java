package com.valeshop.timesheet.services;

import com.valeshop.timesheet.entities.user.User;
import com.valeshop.timesheet.entities.user.UserRegisterDTO;
import com.valeshop.timesheet.entities.user.UserType;
import com.valeshop.timesheet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserRegisterDTO dataUser) {
        User newUser;
        String userType = dataUser.userType();

        String encodedPassword = passwordEncoder.encode(dataUser.password());

        if ("Administrador".equalsIgnoreCase(userType)) {
            newUser = new User(null, dataUser.email(), encodedPassword, UserType.Administrador);

        } else if ("Normal".equalsIgnoreCase(userType)) {
            newUser = new User(null, dataUser.email(), encodedPassword, UserType.Normal);

        } else {
            throw new IllegalArgumentException("Tipo de usuário inválido: " + userType);
        }
        userRepository.save(newUser);
        return newUser;
    }
}
