package com.jungle.board.user.domain.dto.request;

import com.jungle.board.user.domain.type.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import com.jungle.board.user.domain.User;
import com.jungle.board.user.domain.type.ProviderType;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateUserRequest {
    @NotBlank
    @Size(min=4 , max=15)
    private String userId;

    @NotBlank
    @Size(min=2 , max=20)
    private String nickname;

    @NonNull
    @Email
    private String email;

    @Min(7)
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    private ProviderType authProvider;
}
