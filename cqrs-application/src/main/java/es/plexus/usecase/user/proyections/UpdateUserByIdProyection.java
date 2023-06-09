package es.plexus.usecase.user.proyections;

import es.plexus.entity.user.User;
import es.plexus.exceptions.user.EmailUsedException;
import es.plexus.exceptions.user.UserNotFoundException;
import es.plexus.exceptions.user.UsernameUsedException;
import es.plexus.repository.user.query.UserQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UpdateUserByIdProyection {
    @Autowired
    private UserQueryRepository userRepository;

    public void updateUserById(User userToUpdate, long userId) {
        User userDB = userRepository.findById(userId);
        if (userDB == null){
            throw new UserNotFoundException("User not found with id " + userId);
        }

        if(!(userDB.getEmail().equals(userToUpdate.getEmail()))){
            if (userRepository.findByEmail(userToUpdate.getEmail()) != null){
                throw new EmailUsedException("Email " + userToUpdate.getEmail() + " is in use");
            }
        }
        if(!(userDB.getUsername().equals(userToUpdate.getUsername()))){
            if (userRepository.findByUsername(userToUpdate.getUsername()) != null){
                throw new UsernameUsedException("Username " + userToUpdate.getUsername() + " is in use");
            }
        }

        userToUpdate.setSignUpDate(userDB.getSignUpDate());
        userToUpdate.setId(userId);
        User user = userRepository.updateUser(userToUpdate);
    }
}
