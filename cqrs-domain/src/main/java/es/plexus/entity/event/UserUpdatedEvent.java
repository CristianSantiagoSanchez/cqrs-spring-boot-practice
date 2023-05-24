package es.plexus.entity.event;

import es.plexus.entity.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserUpdatedEvent extends BookSwapEvent{
    private User user;
    private static final String SWAP_REGISTER_TOPIC = "user_updated";

    public UserUpdatedEvent(User user){
        super(SWAP_REGISTER_TOPIC);
        this.user = user;
    }
}