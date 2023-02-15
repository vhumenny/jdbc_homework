package commands;

import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateUserCommand implements Command {
    @Getter
    private final int commandNumber = 5;

    @Override
    public void execute(Scanner scanner, Connection connection) {
        System.out.println("please enter id of user:");
        int id = scanner.nextInt();
        System.out.println("please enter new first_name:");
        String firstName = scanner.next();
        System.out.println("please enter new last_name:");
        String lastName = scanner.next();
        System.out.println("please enter new age:");
        int age = scanner.nextInt();
        try {
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("UPDATE persons SET first_name = ?, last_name = ?, age = ?  where id = ?");
            prepareStatement.setString(1, firstName);
            prepareStatement.setString(2, lastName);
            prepareStatement.setInt(3, age);
            prepareStatement.setInt(4, id);

            prepareStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
