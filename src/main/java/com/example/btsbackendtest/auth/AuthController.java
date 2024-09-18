package com.example.btsbackendtest.auth;

import com.example.btsbackendtest.models.Request;
import com.example.btsbackendtest.models.Response;
import com.example.btsbackendtest.user.User;
import com.example.btsbackendtest.utils.Utils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(path="/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path="/register")
    public ResponseEntity<Response.InitData> initData(
            @RequestBody @Valid Request.InitData request
    ) {
        User user = authService.create(request.getEmail(), request.getUsername(), request.getPassword());
        Response.InitData response = Response.InitData.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping(path="/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid Request.Login request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }


}
