package es.plexus.query.user;

import es.plexus.shared.bus.query.QueryHandler;
import es.plexus.usecase.user.FindUserByIdUseCase;
import org.springframework.stereotype.Service;

@Service
public final class FindUserQueryHandler implements QueryHandler<FindUserQuery, UserResponse> {
    private final FindUserByIdUseCase findUserByIdUseCase;

    public FindUserQueryHandler(FindUserByIdUseCase findUserByIdUseCase) {
        this.findUserByIdUseCase = findUserByIdUseCase;
    }

    @Override
    public UserResponse handle(FindUserQuery query)  {
        return findUserByIdUseCase.getUserById(query.getId());
    }
}