package com.progbook.specification.builder;

import com.mysema.query.support.Expressions;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.SimplePath;

import java.util.Map;

public abstract class FilterSpecBuilder {

    protected BooleanExpression withUuid(String uuid) {
        SimplePath<String> entityUuid = Expressions.path(String.class, getMainEntity(), "uuid");
        return entityUuid.eq(uuid);
    }

    protected abstract EntityPathBase getMainEntity();

    public abstract Predicate build(Map<String, String> filterParams);
}
