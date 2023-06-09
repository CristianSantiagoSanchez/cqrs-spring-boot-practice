package es.plexus.mongodb.initializer;

import es.plexus.entity.user.User;
import es.plexus.entity.user.UserRol;
import es.plexus.repository.user.query.UserQueryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DbMongoInitializer {
    @Autowired
    private UserQueryRepository userRepository;

    @PostConstruct
    @Order(2)
    public void initUsers() {
        String username1 = "prueba1";
        User userDb1 = userRepository.findOneByUsername(username1);
        if (userDb1 == null){
            User user = new User();
            user.setId(1L);
            user.setName("prueba1");
            user.setSurnames("surenames1");
            user.setEmail("prueba1@gmail.com");
            user.setBirthdate(LocalDate.of(2020,02,02));
            String pass = new BCryptPasswordEncoder().encode("1234567");
            user.setPassword(pass);
            user.setUsername("prueba1");
            user.setDescription("Descripcion prueba1");
            user.setSignUpDate(LocalDate.now());
            UserRol userRol1 = new UserRol(1L, "admin");
            user.setUserRol(userRol1);
            userRepository.createUser(user);
        }
        String username2 = "prueba2";
        User userDb2 = userRepository.findOneByUsername(username2);
        if (userDb2 == null){
            User user = new User();
            user.setId(2L);
            user.setName("prueba2");
            user.setSurnames("surenames2");
            user.setEmail("prueba2@gmail.com");
            user.setBirthdate(LocalDate.of(2020,03,03));
            String pass = new BCryptPasswordEncoder().encode("1234567");
            user.setPassword(pass);
            user.setUsername("prueba2");
            user.setDescription("Descripcion prueba2");
            user.setSignUpDate(LocalDate.now());
            UserRol userRol1 = new UserRol(2L, "user");
            user.setUserRol(userRol1);
            userRepository.createUser(user);
        }
    }

}
