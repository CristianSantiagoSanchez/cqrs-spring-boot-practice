package es.plexus.usecase.user;

import es.plexus.entity.user.User;
import es.plexus.entity.user.UserRol;
import es.plexus.exceptions.user.EmailUsedException;
import es.plexus.exceptions.user.UsernameUsedException;
import es.plexus.repository.user.UserRepository;
import es.plexus.repository.user.UserRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CreateUserUseCase {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRolRepository userRolRepository;

    public User saveUser(User userRequest) {
        User existingUsername = userRepository.findOneByUsername(userRequest.getUsername());
        if (existingUsername != null){
            throw new UsernameUsedException("Username " + userRequest.getUsername() + " already exists");
        }

        User existingEmail = userRepository.findByEmail(userRequest.getEmail());
        if (existingEmail != null){
            throw new EmailUsedException("Email " + userRequest.getEmail() + " is already exists");
        }

        if (userRequest.getDescription() == null)
            userRequest.setDescription("Write your description");
        userRequest.setSignUpDate(LocalDate.now());
        userRequest.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
        UserRol userRol = userRolRepository.findOneByName("user");
        userRequest.setUserRol(userRol);

        User userCreated = userRepository.createUser(userRequest);


        return userCreated;
    }
}
