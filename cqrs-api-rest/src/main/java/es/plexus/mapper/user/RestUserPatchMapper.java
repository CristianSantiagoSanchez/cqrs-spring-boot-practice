package es.plexus.mapper.user;

import es.plexus.dto.user.UserPatchDto;
import es.plexus.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RestUserPatchMapper {
    default User PatchToEntity(UserPatchDto userPatchDto, @MappingTarget User targetUser) {
        if (userPatchDto.getUsername() != null) {
            targetUser.setUsername(userPatchDto.getUsername());
        }
        if (userPatchDto.getName() != null) {
            targetUser.setName(userPatchDto.getName());
        }
        if (userPatchDto.getSurnames() != null) {
            targetUser.setSurnames(userPatchDto.getSurnames());
        }
        if (userPatchDto.getEmail() != null) {
            targetUser.setEmail(userPatchDto.getEmail());
        }
        if (userPatchDto.getBirthdate() != null) {
            targetUser.setBirthdate(userPatchDto.getBirthdate());
        }
        if (userPatchDto.getDescription() != null) {
            targetUser.setDescription(userPatchDto.getDescription());
        }
        return targetUser;
    }
}
