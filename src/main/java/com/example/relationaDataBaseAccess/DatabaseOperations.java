package com.example.relationaDataBaseAccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DatabaseOperations {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseOperations.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void doOperations() {
        createTable();
        List<Object[]> splitUpNames = prepareClientsToInsert();
        insertClients(splitUpNames);
        getJosh();
    }

    private void insertClients(List<Object[]> splitUpNames) {
        jdbcTemplate.batchUpdate("INSERT INTO clients(first_name, last_name) VALUES (?,?)", splitUpNames);
    }

    private void getJosh() {
        logger.info("Querying for customer records where first_name = 'Josh':");
        jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM clients WHERE first_name = ?", new Object[] { "Josh" },
                (rs, rowNum) -> new Client(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(customer -> logger.info(customer.toString()));
    }

    private List<Object[]> prepareClientsToInsert() {
        List<Object[]> splitUpNames = Arrays.asList("Josh Bloch", "Jon Skeet").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        splitUpNames.forEach(name -> logger.info(String.format("Inserting clients record for %s %s", name[0], name[1])));
        return splitUpNames;
    }

    private void createTable() {
        logger.info("Creating table");
        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE clients(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
    }
}
