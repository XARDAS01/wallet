package org.example.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        enableDefaultTransactions = false,
        basePackages = "org.example.repository"
)
public class JpaRepositoryConfiguration {
}
