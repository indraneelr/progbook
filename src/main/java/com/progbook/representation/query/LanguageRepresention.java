package com.progbook.representation.query;

import com.progbook.persistence.model.Language;

import java.util.ArrayList;
import java.util.Collection;

public class LanguageRepresention {
    private Long id;
    private String name;
    private String uuid;
    private String version;
    private String nameAndVersion;

    public LanguageRepresention() {
    }

    public LanguageRepresention(Language language) {
        if (language == null) return;
        id = language.getId();
        uuid = language.getUuid();
        name = language.getName();
        version = language.getVersion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNameAndVersion(){
//        if (nameAndVersion == null){
//            nameAndVersion = name +" - "+version;
//        }
        return name +" - "+version;
//        return nameAndVersion;
    }

    public static Collection<LanguageRepresention> map(Collection<Language> languages) {
        Collection<LanguageRepresention> languageRepresentions = new ArrayList<>();
        if (languages != null) {
            for (Language language : languages) {
                languageRepresentions.add(new LanguageRepresention(language));
            }
        }
        return languageRepresentions;
    }
}
