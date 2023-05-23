package es.plexus.command.user;

import es.plexus.entity.user.User;
import es.plexus.shared.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class UpdateUserCommand implements Command {
    private User user;
    private long userId;

}