package es.plexus.usecase.user;

import es.plexus.entity.user.User;
import es.plexus.exceptions.user.UserNotFoundException;
import es.plexus.mapper.user.UserResponseMapper;
import es.plexus.query.user.UserResponse;
import es.plexus.repository.user.command.UserCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindUserByIdUseCase {
    @Autowired
    private UserCommandRepository userRepository;
    @Autowired
    private UserResponseMapper userResponseMapper;

    public UserResponse getUserById(long userId){
        User userDB = userRepository.findById(userId);
        if (userDB == null){
            throw new UserNotFoundException("User not found with id " + userId);
        }

        return userResponseMapper.entityToUserResponse(userDB);
    }
}
