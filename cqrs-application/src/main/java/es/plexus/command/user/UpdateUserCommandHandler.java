package es.plexus.command.user;

import es.plexus.shared.bus.command.CommandHandler;
import es.plexus.usecase.user.UpdateUserByIdUseCase;
import org.springframework.stereotype.Service;

@Service
public final class UpdateUserCommandHandler implements CommandHandler<UpdateUserCommand> {
    private final UpdateUserByIdUseCase updateUserByIdUseCase;

    public UpdateUserCommandHandler(UpdateUserByIdUseCase updateUserByIdUseCase) {
        this.updateUserByIdUseCase = updateUserByIdUseCase;
    }

    @Override
    public void handle(UpdateUserCommand command) {
        updateUserByIdUseCase.updateUserById(command.getUser(), command.getUserId());
    }
}