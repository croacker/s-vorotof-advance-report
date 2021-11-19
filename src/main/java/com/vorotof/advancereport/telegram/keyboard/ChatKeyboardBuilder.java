package com.vorotof.advancereport.telegram.keyboard;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ChatKeyboardBuilder implements KeyboardBuilder {

    @Getter
    private List<ChatButton> buttons = new ArrayList<>();

    public ChatButton newButton(){
        var button = new ChatButton();
        buttons.add(button);
        return button;
    }

    @Override
    public ChatButton newButton(String text) {
        return newButton().setText(text);
    }

    @Override
    public ReplyKeyboard build() {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        buttons.forEach(define -> {
            var button = new InlineKeyboardButton();
            button.setText(define.getText());
            button.setCallbackData(define.getData());
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(button);
            rowList.add(row);
        });
        keyboard.setKeyboard(rowList);
        return keyboard;
    }

}
