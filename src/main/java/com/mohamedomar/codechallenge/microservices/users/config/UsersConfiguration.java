package com.mohamedomar.codechallenge.microservices.users.config;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;


/**
 * Spring configuration.
 * 
 * @author Mohamed Omar
 */
@Configuration
@ComponentScan("com.mohamedomar.codechallenge.microservices.users")
@EntityScan("com.mohamedomar.codechallenge.microservices.users.model")
@EnableJpaRepositories("com.mohamedomar.codechallenge.microservices.users.repository")
@PropertySource("classpath:db-config.properties")
public class UsersConfiguration {

	protected Logger logger;

	public UsersConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

	/**
	 * Creates an in-memory database populated with test data for fast testing
	 */
	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");
        //TODO , in real application, this should be populated from a persistent source, i.e. DB
		// Create an in-memory H2 relational database containing some demo
		// users. 
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/000-schema.sql")
				.addScript("classpath:testdb/001-data.sql").build();

		logger.info("dataSource = " + dataSource);

		// Sanity check
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> users = jdbcTemplate.queryForList("SELECT * FROM T_USER");
		logger.info("on system start up, " + users.size() + " users had been inserted");

		return dataSource;
	}
}
