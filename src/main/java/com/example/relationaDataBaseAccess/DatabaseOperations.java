package com.example.relationaDataBaseAccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseOperations {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseOperations.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void doOperations() {
        logger.info("Doing db operations");

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE clients(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
    }
}
