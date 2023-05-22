package es.plexus.controller.user;

import es.plexus.config.security.TokenUtils;
import es.plexus.dto.user.UserPostDto;
import es.plexus.mapper.user.RestUserPostMapper;
import es.plexus.usecase.user.UpdateUserByIdUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class UserPutController {
    @Autowired
    private UpdateUserByIdUseCase updateUserByIdUseCase;
    @Autowired
    private RestUserPostMapper userPostMapper;
    @Autowired
    private TokenUtils tokenUtils;
    @PutMapping(path = "/users/{userId}")
    public ResponseEntity<?> updateUserById(@PathVariable long userId,
                                            @Valid @RequestBody UserPostDto user,
                                            HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        tokenUtils.verifyTokenByUserId(token, userId);
        updateUserByIdUseCase.updateUserById(this.userPostMapper.toEntity(user), userId);
        return ResponseEntity.noContent().build();
    }
}
