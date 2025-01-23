package org.example.configuration;

import org.example.exception.WalletGlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
public class ApplicationConfiguration {

    @Bean
    public WalletGlobalExceptionHandler walletGlobalExceptionHandler(Environment environment) {
        return new WalletGlobalExceptionHandler();
    }
}
