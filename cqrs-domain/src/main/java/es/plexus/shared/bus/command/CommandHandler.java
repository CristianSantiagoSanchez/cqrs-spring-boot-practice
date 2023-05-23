package es.plexus.shared.bus.command;

public interface CommandHandler<T extends Command> {
    void handle(T command);
}