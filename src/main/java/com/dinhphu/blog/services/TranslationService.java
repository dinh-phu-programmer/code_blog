package com.dinhphu.blog.services;

import com.dinhphu.blog.util.TranslationCode;
import org.springframework.stereotype.Service;

import static com.dinhphu.blog.configuration.Translator.toLocale;

@Service
public class TranslationService {
    public String getTranslation(){
        return toLocale(TranslationCode.GREETING);
    }
}
