package org.example.container;

import org.testcontainers.containers.PostgreSQLContainer;

public class WalletPostgreSQLContainer<SELF extends PostgreSQLContainer<SELF>> extends PostgreSQLContainer<SELF> {

    public static final String POSTGRESQL_IMAGE = "postgres:16.1-alpine";

    public WalletPostgreSQLContainer() {
        super(POSTGRESQL_IMAGE);
    }
}
