package es.plexus.repository.user;

import es.plexus.entity.user.UserRol;
import es.plexus.jpa.user.UserRolJpa;
import es.plexus.mapper.user.UserRolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRolRepositoryImpl implements UserRolRepository{
    @Autowired
    private UserRolJpaRepository userRolJpaRepository;
    @Autowired
    private UserRolMapper userRolMapper;
    @Override
    public UserRol createRol(UserRol userRol) {
        return userRolMapper.toEntity(userRolJpaRepository.save(userRolMapper.toJpa(userRol)));
    }

    @Override
    public UserRol findOneByName(String name) {
        Optional<UserRolJpa> user = userRolJpaRepository.findOneByName(name);
        if (user.isEmpty()) {
            return null;
        }
        return userRolMapper.toEntity(user.get());
    }

    @Override
    public UserRol findOneById(long id) {
        Optional<UserRolJpa> user = userRolJpaRepository.findOneById(id);
        if (user.isEmpty()) {
            return null;
        }
        return userRolMapper.toEntity(user.get());
    }
}
