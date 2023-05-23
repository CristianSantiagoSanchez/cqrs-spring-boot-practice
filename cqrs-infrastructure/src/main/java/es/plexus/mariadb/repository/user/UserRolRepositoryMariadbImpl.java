package es.plexus.mariadb.repository.user;

import es.plexus.entity.user.UserRol;
import es.plexus.mariadb.user.UserRolMariadb;
import es.plexus.mariadb.mapper.user.UserRolMapper;
import es.plexus.repository.user.command.UserRolCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRolRepositoryMariadbImpl implements UserRolCommandRepository {
    @Autowired
    private UserRolMariadbRepository userRolJpaRepository;
    @Autowired
    private UserRolMapper userRolMapper;
    @Override
    public UserRol createRol(UserRol userRol) {
        return userRolMapper.toEntity(userRolJpaRepository.save(userRolMapper.toJpa(userRol)));
    }

    @Override
    public UserRol findOneByName(String name) {
        Optional<UserRolMariadb> user = userRolJpaRepository.findOneByName(name);
        if (user.isEmpty()) {
            return null;
        }
        return userRolMapper.toEntity(user.get());
    }

    @Override
    public UserRol findOneById(long id) {
        Optional<UserRolMariadb> user = userRolJpaRepository.findOneById(id);
        if (user.isEmpty()) {
            return null;
        }
        return userRolMapper.toEntity(user.get());
    }
}
