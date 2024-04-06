package car.command;

public interface InvokerCommand {
    void setup(int dt);

    void execute(final CommandCar command);

}
