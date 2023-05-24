package es.plexus.controller.user;

import es.plexus.entity.user.User;
import es.plexus.mapper.user.RestUserMapper;
import es.plexus.query.user.FindUserQuery;
import es.plexus.query.user.UserResponse;
import es.plexus.shared.bus.query.QueryBus;
import es.plexus.usecase.user.FindUserByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserGetController {
    @Autowired
    private RestUserMapper userMapper;

    @Autowired
    private QueryBus queryBus;

    @GetMapping(path = "/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable long userId) {
        UserResponse userResponse = queryBus.ask(new FindUserQuery(userId));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}