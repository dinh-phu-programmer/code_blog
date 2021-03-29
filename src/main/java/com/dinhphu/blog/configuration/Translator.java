package com.dinhphu.blog.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Translator {
    private static ResourceBundleMessageSource messageSource;

    public Translator(@Qualifier("texts") ResourceBundleMessageSource messageSource){
        this.messageSource=messageSource;
    }

    public static String toLocale(String code){
        Locale locale= LocaleContextHolder.getLocale();
        return messageSource.getMessage(code,null,locale);
    }
}
