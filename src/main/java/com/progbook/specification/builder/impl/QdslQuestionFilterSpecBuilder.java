package com.progbook.specification.builder.impl;

import com.mysema.query.support.Expressions;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.EntityPathBase;
import com.progbook.persistence.model.QQuestion;
import com.progbook.specification.builder.FilterSpecBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class QdslQuestionFilterSpecBuilder extends FilterSpecBuilder {
    public static final String TAGS_SEPARATOR= ",";
    private final QQuestion _question = QQuestion.question;

    @Override
    protected EntityPathBase getMainEntity() {
        return _question;
    }

    @Override
    public Predicate build(Map<String, String> filterParams) {
        List<BooleanExpression> expressions = new ArrayList<>();
        for (String filterParamName : filterParams.keySet()) {
            switch (filterParamName){
                case "uuid":expressions.add(withUuid(filterParams.get(filterParamName))); break;
                case "id":expressions.add(withId(filterParams.get(filterParamName))); break;
                case "title":expressions.add(ifTitleContains(filterParams.get(filterParamName))); break;
                case "category":expressions.add(withCategory(filterParams.get(filterParamName))); break;
                case "tags":expressions.add(withTags(filterParams.get(filterParamName))); break;
            }
        }

        return Expressions.allOf(expressions.toArray(new BooleanExpression[]{}));
    }

    private BooleanExpression withTags(String tags) {
        String[] questionTags = tags.split(TAGS_SEPARATOR);
        return _question.tags.any().name.in(Arrays.asList(questionTags));
    }

    private BooleanExpression withCategory(String categoryName) {
        return _question.category.name.eq(categoryName);
    }

    private BooleanExpression ifTitleContains(String titleString) {
        return _question.title.contains(titleString);
    }


}
