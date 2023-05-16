package es.plexus.controller.user;

import es.plexus.config.security.TokenUtils;
import es.plexus.usecase.user.DeleteUserByIdUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserDeleteController {
    @Autowired
    private DeleteUserByIdUseCase deleteUserByIdUseCase;
    @Autowired
    private TokenUtils tokenUtils;
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId,
                                            HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        tokenUtils.verifyTokenByUserId(token, userId);
        deleteUserByIdUseCase.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
