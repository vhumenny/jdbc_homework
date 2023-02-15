package commands;

import java.sql.Connection;
import java.util.Scanner;

public interface Command {
    int getCommandNumber();
    void execute(Scanner scanner, Connection connection);
}
