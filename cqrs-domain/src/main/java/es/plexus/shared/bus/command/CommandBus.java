package es.plexus.shared.bus.command;

import es.plexus.shared.bus.command.exception.CommandHandlerExecutionError;

public interface CommandBus {
    void dispatch(Command command) throws CommandHandlerExecutionError;
}