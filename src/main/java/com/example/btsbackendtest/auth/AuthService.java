package com.example.btsbackendtest.auth;

import com.example.btsbackendtest.config.AppConfig;
import com.example.btsbackendtest.config.jwt.JwtService;
import com.example.btsbackendtest.exceptions.BadRequest;
import com.example.btsbackendtest.exceptions.DuplicateException;
import com.example.btsbackendtest.models.Request;
import com.example.btsbackendtest.user.User;
import com.example.btsbackendtest.user.UserRepository;
import com.example.btsbackendtest.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AppConfig appConfig;

    public AuthResponse login(Request.Login request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if(user.isEmpty()) {
            throw new BadRequest("invalid email or password");
        }
        boolean isPasswordValid = appConfig.passwordEncoder().matches(request.getPassword(), user.get().getPassword());

        if(!isPasswordValid) {
            throw new BadRequest("invalid email or password");
        }
        String token = jwtService.generateToken(user.get());
        AuthResponse response = AuthResponse.builder()
                .accessToken(token)
                .message("Logged in successfully")
                .statusCode(200)
                .build();

        return response;
    }

    public User create(String email, String username, String password) throws DuplicateException {
//        var userSession = Utils.getUserSession();
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()) {
            throw new DuplicateException("Pengguna telah terdaftar.");
        }
        var hashPassword = appConfig.passwordEncoder().encode(password);
        User newUser = new User(
                email,
                username,
                hashPassword
        );
        return userRepository.save(newUser);
    }

    public User update(User oldUser, String newPassword) {
        oldUser.setPassword(newPassword);
        return userRepository.save(oldUser);
    }

}
