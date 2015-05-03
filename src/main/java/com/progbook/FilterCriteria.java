package com.progbook;

import com.mysema.query.types.Predicate;
import com.progbook.specification.builder.FilterSpecBuilderFactory;

import java.util.HashMap;

public class FilterCriteria extends HashMap<String,String> {
    public Predicate toPredicate(Class entityType){
        return FilterSpecBuilderFactory.getFilterSpecBuilder(entityType).build(this);
    }
}
