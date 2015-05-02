package com.progbook;

import com.progbook.specification.builder.FilterSpecBuilderFactory;
import com.progbook.specification.PredicateWrapper;

import java.util.HashMap;

public class FilterCriteria<T> extends HashMap<String,String> {
    public PredicateWrapper<T> toPredicate(Class entityType,Class<T> predicateType){
        return FilterSpecBuilderFactory.getFilterSpecBuilder(entityType,predicateType).build(this);
    }
}
