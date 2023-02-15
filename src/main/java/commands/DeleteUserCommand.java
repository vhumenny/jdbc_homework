package commands;

import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteUserCommand implements Command {
    @Getter
    private final int commandNumber = 4;


    @Override
    public void execute(Scanner scanner, Connection connection) {
        System.out.println("please enter user id: ");
        int id = scanner.nextInt();
        try {
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("DELETE FROM jdbc_example.persons where id = ?");
            prepareStatement.setInt(1, id);

            prepareStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

