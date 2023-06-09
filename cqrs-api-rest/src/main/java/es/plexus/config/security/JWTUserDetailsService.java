package es.plexus.config.security;

import es.plexus.entity.user.User;
import es.plexus.repository.user.command.UserCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JWTUserDetailsService implements UserDetailsService {
    @Autowired
    private UserCommandRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User with username: " + username + " does not exists");
        }
        return new UserDetailsImpl(user);
    }
}
