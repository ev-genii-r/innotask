package com.innowise.rudkovskii.dto.card;

import com.innowise.rudkovskii.dto.card.request.CardInfoCreateRequest;
import com.innowise.rudkovskii.dto.card.response.CardInfoResponse;
import com.innowise.rudkovskii.dto.card.response.CardInfoWithUserResponse;
import com.innowise.rudkovskii.entity.CardInfo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T11:04:37+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class CardInfoMapperImpl implements CardInfoMapper {

    @Override
    public CardInfoResponse cardInfoToCardInfoResponse(CardInfo cardInfo) {
        if ( cardInfo == null ) {
            return null;
        }

        CardInfoResponse cardInfoResponse = new CardInfoResponse();

        cardInfoResponse.setId( cardInfo.getId() );
        cardInfoResponse.setNumber( cardInfo.getNumber() );
        cardInfoResponse.setHolder( cardInfo.getHolder() );
        cardInfoResponse.setExpirationDate( cardInfo.getExpirationDate() );

        return cardInfoResponse;
    }

    @Override
    public CardInfoWithUserResponse cardInfoToCardInfoWithUserResponse(CardInfo cardInfo) {
        if ( cardInfo == null ) {
            return null;
        }

        CardInfoWithUserResponse cardInfoWithUserResponse = new CardInfoWithUserResponse();

        cardInfoWithUserResponse.setId( cardInfo.getId() );
        cardInfoWithUserResponse.setNumber( cardInfo.getNumber() );
        cardInfoWithUserResponse.setHolder( cardInfo.getHolder() );
        cardInfoWithUserResponse.setExpirationDate( cardInfo.getExpirationDate() );
        cardInfoWithUserResponse.setUser( cardInfo.getUser() );

        return cardInfoWithUserResponse;
    }

    @Override
    public CardInfo toEntity(CardInfoCreateRequest cardInfoCreateRequest) {
        if ( cardInfoCreateRequest == null ) {
            return null;
        }

        CardInfo cardInfo = new CardInfo();

        cardInfo.setNumber( cardInfoCreateRequest.getNumber() );
        cardInfo.setHolder( cardInfoCreateRequest.getHolder() );
        cardInfo.setExpirationDate( cardInfoCreateRequest.getExpirationDate() );

        return cardInfo;
    }
}
