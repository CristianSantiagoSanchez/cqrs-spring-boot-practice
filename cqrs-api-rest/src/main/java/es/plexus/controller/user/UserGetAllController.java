package es.plexus.controller.user;

import es.plexus.config.security.TokenUtils;
import es.plexus.entity.user.User;
import es.plexus.mapper.user.RestUserMapper;
import es.plexus.usecase.user.FindByFilterUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserGetAllController {
    @Autowired
    private FindByFilterUseCase findByFilterUseCase;
    @Autowired
    private RestUserMapper userMapper;
    @GetMapping(path = "/users")
    public ResponseEntity<?> getUsers(@RequestParam(value = "username", required = false) String username) {
        List<User> users = findByFilterUseCase.getFilterUsername(username);
        return new ResponseEntity<>(this.userMapper.toListDto(users), HttpStatus.OK);
    }
}
