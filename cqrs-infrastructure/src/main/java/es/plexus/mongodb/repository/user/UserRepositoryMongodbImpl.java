package es.plexus.mongodb.repository.user;

import es.plexus.entity.user.User;
import es.plexus.mongodb.mapper.user.UserMongodbMapper;
import es.plexus.mongodb.user.UserMongodb;
import es.plexus.repository.user.query.UserQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryMongodbImpl implements UserQueryRepository<Long> {
    @Autowired
    private UserMongodbMapper mapper;
    @Autowired
    private UserMongodbRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.mapper.toListEntity(userRepository.findAll());
    }

    @Override
    public User findById(Long userId) {
        Optional<UserMongodb> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return null;
        }
        return this.mapper.toEntity(user.get());
    }

    @Override
    public User findByEmail(String email) {
        Optional<UserMongodb> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return null;
        }
        return this.mapper.toEntity(user.get());
    }

    @Override
    public List<User> findByUsername(String username) {
        List<UserMongodb> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return null;
        }
        return this.mapper.toListEntity(user);
    }

    @Override
    public User findOneByUsername(String username) {
        Optional<UserMongodb> user = userRepository.findOneByUsername(username);
        if (user.isEmpty()) {
            return null;
        }
        return this.mapper.toEntity(user.get());
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        Optional<UserMongodb> user = userRepository.findByUsernameAndPassword(username, password);
        if (user.isEmpty()) {
            return null;
        }
        return this.mapper.toEntity(user.get());
    }

    @Override
    public User createUser(User user) {
        return this.mapper.toEntity(userRepository.save(this.mapper.toMongo(user)));
    }

    @Override
    public User updateUser(User user) {
        return this.mapper.toEntity(userRepository.save(this.mapper.toMongo(user)));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
