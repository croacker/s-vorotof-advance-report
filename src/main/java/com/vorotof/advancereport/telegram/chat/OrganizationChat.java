package com.vorotof.advancereport.telegram.chat;

import com.vorotof.advancereport.service.OrganizationService;
import com.vorotof.advancereport.service.dto.organization.OrganizationDto;
import com.vorotof.advancereport.service.mapper.telegram.TelegramOrganizationDtoToString;
import com.vorotof.advancereport.telegram.keyboard.ChatKeyboardBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Чат организации
 */
@AllArgsConstructor
public class OrganizationChat implements Chat{

    private final String chatId;

    private final OrganizationService organizationService;

    private final TelegramOrganizationDtoToString toStringMapper;

    @Override
    public String getChatId() {
        return String.valueOf(chatId);
    }

    @Override
    public ReplyKeyboard findByName(String expression) {
        var organizations = getOrganizations(expression);
        var builder = new ChatKeyboardBuilder();
        organizations.forEach(organization -> builder.newButton()
                .setText(toStringMapper.map(organization))
                .setData(organization.getId().toString()));
        return builder.build();
    }

    @Override
    public String getDescription() {
        return "Поиск организаций";
    }

    private List<OrganizationDto> getOrganizations(String expression){
        var pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        return organizationService.getOrganizations(expression.trim(), pageable);
    }
}
