package car.command;

public interface InvokerCommand {
    void setup(int dt);

    void sense();

    void decide();

    void action();

}
