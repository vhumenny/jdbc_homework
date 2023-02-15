package commands;

import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddUserCommand implements Command {
    @Getter
    private final int commandNumber = 1;

    @Override
    public void execute(Scanner scanner, Connection connection) {
        System.out.println("please enter first_name: ");
        String firstName = scanner.next();
        System.out.println("please enter last_name: ");
        String lastName = scanner.next();
        System.out.println("please enter age: ");
        int age = scanner.nextInt();
        try {
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("INSERT INTO jdbc_example.persons(first_name, last_name,age) VALUES (?, ?, ?) ");
            prepareStatement.setString(1, firstName);
            prepareStatement.setString(2, lastName);
            prepareStatement.setInt(3, age);

            prepareStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
