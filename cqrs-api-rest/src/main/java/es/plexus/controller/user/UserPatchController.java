package es.plexus.controller.user;

import es.plexus.config.security.TokenUtils;
import es.plexus.dto.user.UserPatchDto;
import es.plexus.entity.user.User;
import es.plexus.mapper.user.RestUserPatchMapper;
import es.plexus.usecase.user.FindUserByIdUseCase;
import es.plexus.usecase.user.UpdateUserByIdUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class UserPatchController {
    @Autowired
    private FindUserByIdUseCase findUserByIdUseCase;
    @Autowired
    private UpdateUserByIdUseCase updateUserByIdUseCase;
    @Autowired
    private RestUserPatchMapper restUserPatchMapper;
    @Autowired
    private TokenUtils tokenUtils;
    @PatchMapping(path = "/users/{userId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchUserById(@PathVariable long userId,
                                           @Valid @RequestBody UserPatchDto userPatchDto,
                                           HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        tokenUtils.verifyTokenByUserId(token, userId);

        User userTarget = findUserByIdUseCase.getUserById(userId);
        User userPatched = restUserPatchMapper.PatchToEntity(userPatchDto, userTarget);
        updateUserByIdUseCase.updateUserById(userPatched, userId);
        return ResponseEntity.noContent().build();
    }
}
