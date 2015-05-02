package com.progbook.specification.builder;

import com.mysema.query.support.Expressions;
import com.mysema.query.types.Expression;
import com.mysema.query.types.Ops;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.SimplePath;
import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Language;
import com.progbook.persistence.model.Question;
import com.progbook.specification.PredicateWrapper;
import com.progbook.specification.QdslPredicateWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QdslAnswerFilterSpecBuilder implements FilterSpecBuilder<Predicate> {

    public static final String LANGUAGE_VERSION_SEPARATOR= ",";
    private SimplePath<Answer> answer = Expressions.path(Answer.class, "answer");

    @Override
    public PredicateWrapper<Predicate> build(Map<String, String> filterParams){
        List<Expression> expressions = new ArrayList<>();
        for (String filterParamName : filterParams.keySet()) {
            switch (filterParamName){
               case "uuid":expressions.add(withUuid(filterParams.get(filterParamName))); break;
               case "question":expressions.add(withQuestion(filterParams.get(filterParamName))); break;
               case "language":expressions.add(withLanguage(filterParams.get(filterParamName))); break;
            }
        }
        BooleanExpression predicate = Expressions.predicate(Ops.AND, (Expression<?>[]) expressions.toArray());
        return new QdslPredicateWrapper(predicate);
    }

    private Expression withUuid(String uuid) {
        SimplePath<String> answerUuid = Expressions.path(String.class, answer, "uuid");
        return answerUuid.eq(uuid);
    }

    private Expression withQuestion(String questionUuid){
        SimplePath<Question> question = Expressions.path(Question.class, answer, "question");
        SimplePath<String> questionUuidFromAnswer = Expressions.path(String.class, question, "uuid");
        return questionUuidFromAnswer.eq(questionUuid);
    }

    private Expression withLanguage(String languageAndVersion){
        String[] langVersion = languageAndVersion.split(LANGUAGE_VERSION_SEPARATOR);
        SimplePath<Language> language = Expressions.path(Language.class, answer, "language");
        SimplePath<String> languageName = Expressions.path(String.class, language, "name");
        SimplePath<String> languageVersion = Expressions.path(String.class, language, "version");

        if(langVersion.length == 2){
            return languageName.eq(langVersion[0]).and(languageVersion.eq(langVersion[1]));
        }

        return languageName.eq(langVersion[0]);
    }



}
