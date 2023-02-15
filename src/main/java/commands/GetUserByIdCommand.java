package commands;

import lombok.Getter;
import models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetUserByIdCommand implements Command {
    @Getter
    private final int commandNumber = 6;

    @Override
    public void execute(Scanner scanner, Connection connection) {
        System.out.println("please enter id: ");
        int id = scanner.nextInt();
        Person person = null;
        try {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM persons WHERE id = ?");
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                person = new Person(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(person);
    }
}
