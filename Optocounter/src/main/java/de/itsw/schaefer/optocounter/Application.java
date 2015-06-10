package de.itsw.schaefer.optocounter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

		dataSource.setDriverClass(org.h2.Driver.class);
		dataSource.setUrl(Messages.getString("dataSource.url")); //$NON-NLS-1$
		dataSource.setUsername(Messages.getString("dataSource.username")); //$NON-NLS-1$
		dataSource.setPassword(Messages.getString("dataSource.password")); //$NON-NLS-1$

		return dataSource;
	}

	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory(
			EntityManagerFactory emf) {
		HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
		factory.setEntityManagerFactory(emf);
		return factory;
	}
}
