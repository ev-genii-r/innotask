package com.innowise.rudkovskii.dto.user;

import com.innowise.rudkovskii.dto.card.response.CardInfoResponse;
import com.innowise.rudkovskii.dto.user.request.UserCreateRequest;
import com.innowise.rudkovskii.dto.user.request.UserUpdateRequest;
import com.innowise.rudkovskii.dto.user.response.UserResponse;
import com.innowise.rudkovskii.dto.user.response.UserWithCardsResponse;
import com.innowise.rudkovskii.entity.CardInfo;
import com.innowise.rudkovskii.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T11:04:36+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setName( user.getName() );
        userResponse.setSurname( user.getSurname() );
        userResponse.setBirthDate( user.getBirthDate() );
        userResponse.setEmail( user.getEmail() );

        return userResponse;
    }

    @Override
    public UserWithCardsResponse userToUserWithCardsResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserWithCardsResponse userWithCardsResponse = new UserWithCardsResponse();

        userWithCardsResponse.setId( user.getId() );
        userWithCardsResponse.setName( user.getName() );
        userWithCardsResponse.setSurname( user.getSurname() );
        userWithCardsResponse.setEmail( user.getEmail() );
        userWithCardsResponse.setBirthDate( user.getBirthDate() );
        userWithCardsResponse.setCards( cardInfoListToCardInfoResponseList( user.getCards() ) );

        return userWithCardsResponse;
    }

    @Override
    public User toUser(UserCreateRequest userCreateRequest) {
        if ( userCreateRequest == null ) {
            return null;
        }

        User user = new User();

        user.setName( userCreateRequest.getName() );
        user.setSurname( userCreateRequest.getSurname() );
        user.setBirthDate( userCreateRequest.getBirthDate() );
        user.setEmail( userCreateRequest.getEmail() );

        return user;
    }

    @Override
    public User toUser(UserUpdateRequest userUpdateRequest) {
        if ( userUpdateRequest == null ) {
            return null;
        }

        User user = new User();

        user.setName( userUpdateRequest.getName() );
        user.setSurname( userUpdateRequest.getSurname() );
        user.setBirthDate( userUpdateRequest.getBirthDate() );
        user.setEmail( userUpdateRequest.getEmail() );

        return user;
    }

    protected CardInfoResponse cardInfoToCardInfoResponse(CardInfo cardInfo) {
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

    protected List<CardInfoResponse> cardInfoListToCardInfoResponseList(List<CardInfo> list) {
        if ( list == null ) {
            return null;
        }

        List<CardInfoResponse> list1 = new ArrayList<CardInfoResponse>( list.size() );
        for ( CardInfo cardInfo : list ) {
            list1.add( cardInfoToCardInfoResponse( cardInfo ) );
        }

        return list1;
    }
}
