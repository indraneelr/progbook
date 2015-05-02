package com.progbook.specification.builder;

import com.mysema.query.types.Predicate;

public class FilterSpecBuilderFactory {
    private static FilterSpecBuilder qdslAnswerFilterSpecBuilder;

    public static FilterSpecBuilder getFilterSpecBuilder(Class entityType, Class predicateType) {
        if (predicateType.equals(Predicate.class)) {
            switch (entityType.getSimpleName()) {
                case "Answer":
                    return getQdslAnswerFilterSpecBuilder();
                case "Question":
                    return getQdslQuestionFilterSpecBuilder();
                case "Language":
                    return getQdslLanguageFilterSpecBuilder();
                case "QuestionTag":
                    return getQdslQuestionTagFilterSpecBuilder();
                case "Vote":
                    return getQdslVoteFilterSpecBuilder();
                case "Comment":
                    return getQdslCommentFilterSpecBuilder();
                case "Category":
                    return getQdslCategoryFilterSpecBuilder();
            }
        }
        return null;
    }

    private static FilterSpecBuilder getQdslCategoryFilterSpecBuilder() {
        return null;
    }

    private static FilterSpecBuilder getQdslCommentFilterSpecBuilder() {
        return null;
    }

    private static FilterSpecBuilder getQdslVoteFilterSpecBuilder() {
        return null;
    }

    private static FilterSpecBuilder getQdslQuestionTagFilterSpecBuilder() {
        return null;
    }

    private static FilterSpecBuilder getQdslLanguageFilterSpecBuilder() {
        return null;
    }

    private static FilterSpecBuilder getQdslQuestionFilterSpecBuilder() {
        return null;
    }

    public static FilterSpecBuilder getQdslAnswerFilterSpecBuilder() {
        if(qdslAnswerFilterSpecBuilder == null){
            qdslAnswerFilterSpecBuilder = new QdslAnswerFilterSpecBuilder();
        }
        return  qdslAnswerFilterSpecBuilder ;
    }
}
