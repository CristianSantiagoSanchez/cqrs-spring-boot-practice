package es.plexus.mongodb.mapper.user;

import es.plexus.entity.user.User;
import es.plexus.mariadb.user.UserMariadb;
import es.plexus.mongodb.user.UserMongodb;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMongodbMapper {

    UserMongodb toMongo(User user);
    User toEntity(UserMongodb userMongodb);
    List<UserMongodb> toListMongo(List<User> users);
    List<User> toListEntity(List<UserMongodb> userMongodbs);
}
