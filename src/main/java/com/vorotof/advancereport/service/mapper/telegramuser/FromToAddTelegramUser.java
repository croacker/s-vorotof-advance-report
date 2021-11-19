package com.vorotof.advancereport.service.mapper.telegramuser;

import com.vorotof.advancereport.service.dto.telegramuser.AddTelegramUserDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class FromToAddTelegramUser implements Mapper<User, AddTelegramUserDto> {
    @Override
    public AddTelegramUserDto map(User input) {
        return new AddTelegramUserDto()
                .setId(input.getId())
                .setIsBot(input.getIsBot())
                .setUserName(input.getUserName())
                .setFirstName(input.getFirstName())
                .setLastName(input.getLastName())
                .setCanJoinGroups(input.getCanJoinGroups())
                .setCanReadAllGroupMessages(input.getCanReadAllGroupMessages())
                .setSupportInlineQueries(input.getSupportInlineQueries());
    }
}
