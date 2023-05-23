package es.plexus.usecase.user;

import es.plexus.entity.user.User;
import es.plexus.exceptions.user.UserNotFoundException;
import es.plexus.repository.user.command.UserCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserByIdUseCase {
    @Autowired
    private UserCommandRepository userRepository;

    public void deleteUserById(Long userId) {
        User userDB = userRepository.findById(userId);
        if (userDB == null){
            throw new UserNotFoundException("User not found with id " + userId);
        }

        userRepository.deleteUser(userId);
    }
}
