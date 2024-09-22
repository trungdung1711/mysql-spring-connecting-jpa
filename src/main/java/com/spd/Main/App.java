package com.spd.Main;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.java.Log;

@SpringBootApplication
@Log
public class App implements CommandLineRunner
{
	public static void main(String[] args) 
	{
		SpringApplication.run(App.class, args);
	}


	private final DataSource m_dataSource;


	public App(final DataSource ds)
	{
		m_dataSource = ds;
	}


	@Override
	public void run(String... args) throws Exception 
	{
		log.info("Database: " + m_dataSource.toString());
		final JdbcTemplate restTemplate = new JdbcTemplate(m_dataSource);
	}

}
