import commands.*;
import db.DBConnector;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final List<Command> commandList;

    public Menu() {
        this.commandList = fillCommands();
    }

    private List<Command> fillCommands() {
        List<Command> commands = new ArrayList<>();
        commands.add(new AddUserCommand());
        commands.add(new DeleteUserCommand());
        commands.add(new GetAllUsersCommand());
        commands.add(new GetUserByIdCommand());
        commands.add(new GetAllUsersOlderCommand());
        commands.add(new GetUserByFirstNameCommand());
        commands.add(new UpdateUserCommand());
        return commands;
    }

    public void run() {
        while (true) {
            System.out.println("""
                    INFO MENU:
                    To add new user enter 1;
                    to get all users enter 2;
                    to get all users older than, enter 3;
                    to delete user enter 4;
                    to update data on user enter 5;
                    to get user by id enter 6;
                    to get user by first_name enter 7;
                    to exit enter - finish""");
            String line = new Scanner(System.in).nextLine();
            if (line.equals("finish")) return;
            parseUserInput(line);
        }
    }


    private void parseUserInput(String line) {
        DBConnector dbConnector = new DBConnector();
        Connection connection = dbConnector.getConnection();
        Scanner scanner = new Scanner(System.in);
        String regex = "([1-7])";
        if (!line.matches(regex)) {
            System.out.println("Unknown command, try again!");
        } else {
            for (Command currentCommand : commandList) {
                if (isCommandMatches(line, currentCommand)) {
                    currentCommand.execute(scanner, connection);
                }
            }
        }
    }

    private boolean isCommandMatches(String line, Command currentCommand) {
        return Integer.parseInt(line) == currentCommand.getCommandNumber();
    }
}
