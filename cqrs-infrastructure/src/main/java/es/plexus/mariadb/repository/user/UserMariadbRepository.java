package es.plexus.mariadb.repository.user;


import es.plexus.mariadb.user.UserMariadb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMariadbRepository extends JpaRepository<UserMariadb, Long>, JpaSpecificationExecutor<UserMariadb>{
    public List<UserMariadb> findAll();
    public Optional<UserMariadb> findByEmail(String email);
    public Optional<UserMariadb> findOneByUsername(String username);
    public List<UserMariadb> findByUsername(String username);
    public Optional<UserMariadb> findByUsernameAndPassword(String username, String password);
}
