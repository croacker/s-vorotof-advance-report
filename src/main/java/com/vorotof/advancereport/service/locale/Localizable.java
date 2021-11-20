package com.vorotof.advancereport.service.locale;

public interface Localizable {

    default String getString(String key){
        var languageCode = getLanguageCode();
        return getLocaleService().getString(key, languageCode);
    }

    String getLanguageCode();

    LocaleService getLocaleService();

}
