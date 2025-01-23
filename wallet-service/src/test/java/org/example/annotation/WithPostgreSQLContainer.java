package org.example.annotation;

import org.example.container.WalletPostgreSQLContainer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.TYPE)
@Retention(RUNTIME)
@Testcontainers
@Import(WithPostgreSQLContainer.PostgresqlTestcontainersTestConfiguration.class)
@Inherited
public @interface WithPostgreSQLContainer {

    @TestConfiguration(proxyBeanMethods = false)
    class PostgresqlTestcontainersTestConfiguration {
        @Bean(destroyMethod = "stop")
        @ServiceConnection
        public PostgreSQLContainer<?> postgreSQLContainer() {
            final var isSupported = TestcontainersConfiguration.getInstance()
                    .environmentSupportsReuse();

            TestcontainersConfiguration.getInstance().updateUserConfig("testcontainers.reuse.enable", ""+isSupported);
            return new WalletPostgreSQLContainer<>().withReuse(isSupported);
        }
    }
}
