package commands;

import lombok.Getter;
import models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetAllUsersOlderCommand implements Command {
    @Getter
    private final int commandNumber = 3;


    @Override
    public void execute(Scanner scanner, Connection connection) {
        List<Person> people = new ArrayList<>();

        System.out.println("please enter age: ");
        int age = scanner.nextInt();
        try {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM persons WHERE age > ?");
            prepareStatement.setInt(1, age);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                people.add(new Person(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4)));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        people.forEach(person -> System.out.println(person.toString()));
    }
}
