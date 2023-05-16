package es.plexus.usecase.user;

import es.plexus.entity.user.User;
import es.plexus.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindByFilterUseCase {
    @Autowired
    private UserRepository userRepository;
    public List<User> getFilterUsername(String username){
        return userRepository.findFilterUser(username);
    }
}
