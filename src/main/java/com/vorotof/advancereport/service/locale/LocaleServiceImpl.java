package com.vorotof.advancereport.service.locale;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@AllArgsConstructor
public class LocaleServiceImpl implements LocaleService{

    private final MessageSource messageSource;

    @Override
    public String getString(String key, String languageCode) {
        var locale = new Locale(languageCode);
            return messageSource.getMessage(key, null, locale);
    }

}
