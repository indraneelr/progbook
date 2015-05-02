package com.progbook.specification.builder;

import com.progbook.specification.PredicateWrapper;

import java.util.Map;

public interface FilterSpecBuilder<T> {
    PredicateWrapper<T> build(Map<String, String> filterParams);
}
