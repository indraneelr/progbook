package com.progbook.specification.builder;

import com.progbook.persistence.model.*;
import com.progbook.specification.builder.impl.QdslAnswerFilterSpecBuilder;
import com.progbook.specification.builder.impl.QdslQuestionFilterSpecBuilder;
import com.progbook.specification.builder.impl.SimpleFilterSpecBuilder;

import java.util.HashMap;
import java.util.Map;

public class FilterSpecBuilderFactory {

    private static Map<String,FilterSpecBuilder> filterSpecBuilders = new HashMap<>();

    public static FilterSpecBuilder getFilterSpecBuilder(Class entityType) {
        switch (entityType.getSimpleName()) {
            case "Answer":
                return getQdslAnswerFilterSpecBuilder();
            case "Question":
                return getQdslQuestionFilterSpecBuilder();
            case "Language":
                return createSimpleSpecBuilder(Language.class);
            case "QuestionTag":
                return createSimpleSpecBuilder(QuestionTag.class);
            case "Vote":
                return createSimpleSpecBuilder(Vote.class);
            case "Comment":
                return createSimpleSpecBuilder(Comment.class);
            case "Category":
                return createSimpleSpecBuilder(Category.class);
        }
        return null;
    }

    private static FilterSpecBuilder getQdslQuestionFilterSpecBuilder() {
       return createSpecBuilder("question", QdslQuestionFilterSpecBuilder.class);
    }

    public static FilterSpecBuilder getQdslAnswerFilterSpecBuilder() {
        return createSpecBuilder("answer", QdslAnswerFilterSpecBuilder.class);
    }

    private static <T extends FilterSpecBuilder> FilterSpecBuilder createSpecBuilder(String entityName, Class<? extends FilterSpecBuilder> specBuilderClass) {
        if (filterSpecBuilders.get(entityName) == null) {
            try {
                filterSpecBuilders.put(entityName,specBuilderClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
        return filterSpecBuilders.get(entityName);
    }

    private static FilterSpecBuilder createSimpleSpecBuilder(Class entityClass) {
        String entityName = entityClass.getSimpleName().toLowerCase();
        if (filterSpecBuilders.get(entityName) == null) {
                filterSpecBuilders.put(entityName,new SimpleFilterSpecBuilder(entityClass, entityName));
        }
        return filterSpecBuilders.get(entityName);
    }
}
