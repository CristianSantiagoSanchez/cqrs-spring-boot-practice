package es.plexus.controller.user;

import es.plexus.command.user.UpdateUserCommand;
import es.plexus.config.security.TokenUtils;
import es.plexus.dto.user.UserPatchDto;
import es.plexus.entity.user.User;
import es.plexus.mapper.user.RestUserPatchMapper;
import es.plexus.mapper.user.UserResponseMapper;
import es.plexus.query.user.FindUserQuery;
import es.plexus.query.user.UserResponse;
import es.plexus.shared.bus.command.CommandBus;
import es.plexus.shared.bus.query.QueryBus;
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
    @Autowired
    private UserResponseMapper userResponseMapper;
    @Autowired
    QueryBus queryBus;
    @Autowired
    CommandBus commandBus;
    @PatchMapping(path = "/users/{userId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchUserById(@PathVariable long userId,
                                           @Valid @RequestBody UserPatchDto userPatchDto,
                                           HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        tokenUtils.verifyTokenByUserId(token, userId);
        UserResponse userResponse = queryBus.ask(new FindUserQuery(userId));
        User userTarget = userResponseMapper.responseToUserEntity(userResponse);
        User userPatched = restUserPatchMapper.PatchToEntity(userPatchDto, userTarget);
        commandBus.dispatch(new UpdateUserCommand(userPatched, userId));
        return ResponseEntity.noContent().build();
    }
}