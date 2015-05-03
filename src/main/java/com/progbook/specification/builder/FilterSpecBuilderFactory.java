package com.progbook.specification.builder;

import com.progbook.specification.builder.impl.QdslAnswerFilterSpecBuilder;
import com.progbook.specification.builder.impl.QdslQuestionFilterSpecBuilder;

public class FilterSpecBuilderFactory {
    private static FilterSpecBuilder qdslAnswerFilterSpecBuilder;
    private static FilterSpecBuilder qdslQuestionFilterSpecBuilder;

    public static FilterSpecBuilder getFilterSpecBuilder(Class entityType) {
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
        if (qdslQuestionFilterSpecBuilder == null) {
            qdslQuestionFilterSpecBuilder = new QdslQuestionFilterSpecBuilder();
        }
        return qdslQuestionFilterSpecBuilder;
    }

    public static FilterSpecBuilder getQdslAnswerFilterSpecBuilder() {
        if (qdslAnswerFilterSpecBuilder == null) {
            qdslAnswerFilterSpecBuilder = new QdslAnswerFilterSpecBuilder();
        }
        return qdslAnswerFilterSpecBuilder;
    }
}
