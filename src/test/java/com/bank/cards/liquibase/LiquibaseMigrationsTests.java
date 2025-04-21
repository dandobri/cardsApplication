package com.bank.cards.liquibase;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LiquibaseMigrationsTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testLiquibaseMigration1() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'cards'");
            resultSet.next();
            int count = resultSet.getInt(1);
            Assertions.assertEquals(1, count);

            resultSet = statement.executeQuery("SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'transactions'");
            resultSet.next();
            count = resultSet.getInt(1);
            Assertions.assertEquals(1, count);

            resultSet = statement.executeQuery("SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'users'");
            resultSet.next();
            count = resultSet.getInt(1);
            Assertions.assertEquals(1, count);
        } catch (SQLException e) {
            log.error("Error in connection with database in tests {}", e.getMessage());
        }
    }
}
