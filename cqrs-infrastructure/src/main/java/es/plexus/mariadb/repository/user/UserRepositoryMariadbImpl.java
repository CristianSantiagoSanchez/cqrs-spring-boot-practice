package es.plexus.mariadb.repository.user;

import es.plexus.entity.user.User;
import es.plexus.mariadb.user.UserMariadb;
import es.plexus.mapper.user.UserMapper;
import es.plexus.repository.user.UserCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryMariadbImpl implements UserCommandRepository<Long> {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private UserMariadbRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.mapper.toListEntity(userRepository.findAll());
    }

    @Override
    public List<User> findFilterUser(String username) {
        Specification<UserMariadb> spec = Specification.where(null);
        if (username != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), "%" + username + "%"));
        }
        return this.mapper.toListEntity(userRepository.findAll(spec));
    }

    @Override
    public User findById(Long userId) {
        Optional<UserMariadb> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return null;
        }
        return this.mapper.toEntity(user.get());
    }

    @Override
    public User findByEmail(String email) {
        Optional<UserMariadb> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return null;
        }
        return this.mapper.toEntity(user.get());
    }

    @Override
    public List<User> findByUsername(String username) {
        List<UserMariadb> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return null;
        }
        return this.mapper.toListEntity(user);
    }

    @Override
    public User findOneByUsername(String username) {
        Optional<UserMariadb> user = userRepository.findOneByUsername(username);
        if (user.isEmpty()) {
            return null;
        }
        return this.mapper.toEntity(user.get());
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        Optional<UserMariadb> user = userRepository.findByUsernameAndPassword(username, password);
        if (user.isEmpty()) {
            return null;
        }
        return this.mapper.toEntity(user.get());
    }

    @Override
    public User createUser(User user) {
        return this.mapper.toEntity(userRepository.save(this.mapper.toJpa(user)));
    }

    @Override
    public User updateUser(User user) {
        return this.mapper.toEntity(userRepository.save(this.mapper.toJpa(user)));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
