package es.plexus.mongodb.mapper.user;

import es.plexus.entity.user.UserRol;
import es.plexus.mongodb.user.UserRolMongodb;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRolMongodbMapper {
    UserRolMongodb toMongo(UserRol userRol);
    UserRol toEntity(UserRolMongodb userRolMongodb);
}