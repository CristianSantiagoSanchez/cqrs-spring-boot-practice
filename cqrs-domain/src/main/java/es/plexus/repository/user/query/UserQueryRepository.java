package es.plexus.repository.user.query;

import es.plexus.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQueryRepository<L>{
    public List<User> findAll();
    public User findByEmail(String email);
    public User findOneByUsername(String username);
    public List<User> findByUsername(String username);
    public User findByUsernameAndPassword(String username, String password);
    public User createUser(User user);
    public User updateUser(User user);
    public void deleteUser(L userId);
    public User findById(L userId);

}
