package org.example;

import javax.annotation.processing.Generated;
import org.example.api.dto.WalletDto;
import org.example.model.Operation;
import org.example.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-23T19:00:25+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.8 (Amazon.com Inc.)"
)
@Component
public class WalletMapperImpl implements WalletMapper {

    @Autowired
    private OperationMapper operationMapper;

    @Override
    public WalletDto mapToDto(Wallet wallet, Page<Operation> operations) {
        if ( wallet == null && operations == null ) {
            return null;
        }

        WalletDto walletDto = new WalletDto();

        if ( wallet != null ) {
            walletDto.setId( wallet.getId() );
            walletDto.setBalance( wallet.getBalance() );
            walletDto.setCreated( wallet.getCreated() );
        }
        walletDto.setOperations( operationMapper.mapToDto( operations ) );

        return walletDto;
    }
}
