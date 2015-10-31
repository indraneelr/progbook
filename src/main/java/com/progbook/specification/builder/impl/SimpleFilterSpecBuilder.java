package com.progbook.specification.builder.impl;

import com.mysema.query.support.Expressions;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.EntityPathBase;
import com.progbook.specification.builder.FilterSpecBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleFilterSpecBuilder<T> extends FilterSpecBuilder {
    private EntityPathBase<T> entityPathBase;

    public SimpleFilterSpecBuilder(Class<T> entityClass, String variableName) {
        entityPathBase= new EntityPathBase<>(entityClass,variableName);
    }

    @Override
    protected EntityPathBase getMainEntity() {
        return entityPathBase;
    }

    @Override
    public Predicate build(Map<String, String> filterParams) {
        List<BooleanExpression> expressions = new ArrayList<>();
        for (String filterParamName : filterParams.keySet()) {
            switch (filterParamName){
                case "uuid":expressions.add(withUuid(filterParams.get(filterParamName))); break;
            }
        }
        return Expressions.allOf(expressions.toArray(new BooleanExpression[]{}));
    }
}
