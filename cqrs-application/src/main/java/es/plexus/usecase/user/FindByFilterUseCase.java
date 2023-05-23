package es.plexus.usecase.user;

import es.plexus.entity.user.User;
import es.plexus.repository.user.UserCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindByFilterUseCase {
    @Autowired
    private UserCommandRepository userRepository;
    public List<User> getFilterUsername(String username){
        return userRepository.findFilterUser(username);
    }
}
