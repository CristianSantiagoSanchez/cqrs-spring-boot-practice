package es.plexus.mongodb.repository.user;


import es.plexus.mongodb.user.UserMongodb;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMongodbRepository extends MongoRepository<UserMongodb, Long> {
    public List<UserMongodb> findAll();
    public Optional<UserMongodb> findByEmail(String email);
    public Optional<UserMongodb> findOneByUsername(String username);
    public List<UserMongodb> findByUsername(String username);
    public Optional<UserMongodb> findByUsernameAndPassword(String username, String password);
}
