package com.progbook.specification.builder.impl;

import com.mysema.query.support.Expressions;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.EntityPathBase;
import com.progbook.persistence.model.QAnswer;
import com.progbook.specification.builder.FilterSpecBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QdslAnswerFilterSpecBuilder extends FilterSpecBuilder {

    public static final String LANGUAGE_VERSION_SEPARATOR= ",";
    private final QAnswer _answer = QAnswer.answer;


    @Override
    protected EntityPathBase getMainEntity() {
        return _answer;
    }

    @Override
    public Predicate build(Map<String, String> filterParams){
        List<BooleanExpression> expressions = new ArrayList<>();
        for (String filterParamName : filterParams.keySet()) {
            switch (filterParamName){
               case "uuid":expressions.add(withUuid(filterParams.get(filterParamName))); break;
               case "id":expressions.add(withId(filterParams.get(filterParamName))); break;
               case "question":expressions.add(withQuestion(filterParams.get(filterParamName))); break;
               case "language":expressions.add(withLanguage(filterParams.get(filterParamName))); break;
            }
        }
        return Expressions.allOf(expressions.toArray(new BooleanExpression[]{}));
    }

    private BooleanExpression withQuestion(String questionUuid){
        return _answer.question.uuid.eq(questionUuid);
    }

    private BooleanExpression withLanguage(String languageAndVersion){
        String[] langVersion = languageAndVersion.split(LANGUAGE_VERSION_SEPARATOR);
        if(langVersion.length == 2){
            return _answer.language.name.eq(langVersion[0]).and(_answer.language.version.eq(langVersion[1]));
        }
        return _answer.language.name.eq(langVersion[0]);
    }



}
