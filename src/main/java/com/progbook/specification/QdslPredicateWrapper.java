package com.progbook.specification;

import com.mysema.query.types.Predicate;

public class QdslPredicateWrapper implements PredicateWrapper<Predicate> {
    private Predicate predicate;

    public QdslPredicateWrapper(Predicate predicate) {

        this.predicate = predicate;
    }

    public Predicate toPredicate(){
        return predicate;
    }
}
