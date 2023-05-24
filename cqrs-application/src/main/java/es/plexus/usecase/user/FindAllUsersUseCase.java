package es.plexus.usecase.user;

import es.plexus.entity.user.User;
import es.plexus.repository.user.command.UserCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllUsersUseCase {
    @Autowired
    private UserCommandRepository userRepository;



    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
