package com.progbook.persistence;

import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;

import static com.ninja_squad.dbsetup.Operations.*;
import static com.ninja_squad.dbsetup.Operations.insertInto;

public class CommonDbOperations {

    public static final Operation DELETE_ALL =
            deleteAllFrom("question_tags_map", "comment", "answer", "question", "person", "language", "category", "question_tag");

    public static final Operation INSERT_REFERENCE_DATA = sequenceOf(
            insertInto("category")
                    .columns("id", "name", "description")
                    .values("1", "setup", "dev infrastructure")
                    .values("2", "loops", "how to construct a loop")
                    .values("3", "functions", "how to write a function")
                    .build(),
            insertInto("language")
                    .columns("id", "name", "description")
                    .values("1", "java", "java lang")
                    .values("2", "javascript", "a web lang")
                    .values("3", "python", "snake lang")
                    .build(),
            insertInto("question_tag")
                    .columns("id", "name", "description")
                    .values("1", "setup", "setup")
                    .values("2", "loops", "loops")
                    .values("3", "functions", "functions")
                    .build()
    );

    public static final Operation INSERT_PERSONS = sequenceOf(
            insertInto("person")
                    .columns("id", "name", "email", "uuid")
                    .values("1", "the maker", "maker@world.com", "person-uuid-1")
                    .values("2", "follower", "follower@world.com", "person-uuid-2")
                    .build()
    );
    public static final Operation INSERT_QUESTIONS_STARTER_SET = sequenceOf(
            insertInto("question")
                    .columns("id", "content", "title", "category_id", "person_id", "uuid", "date_created")
                    .values("100", "How do you setup the dev environment for this?", "dev env setup", "1", "1", "question-uuid-100", "2015-01-30 21:21:21")
                    .values("101", "How do you write a for loop in this?", "for loop", "2", "1", "question-uuid-101", "2015-01-30 20:20:20")
                    .values("102", "How do you write a while loop in this?", "while loop", "2", "1", "question-uuid-102", "2015-01-30 20:20:20")
                    .values("103", "How do you write a method in this?", "writing functions", "3", "1", "question-uuid-103", "2015-01-30 20:21:20")
                    .build()
    );

    public static final Operation INSERT_QUESTION_TAGS = sequenceOf(
            insertInto("question_tags_map")
                    .columns("question_id", "question_tag_id")
                    .values("100", "1")
                    .values("101", "2")
                    .values("102", "2")
                    .values("103", "3")
                    .build()
    );

    public static final Operation INSERT_ANSWERS_STARTER_SET = sequenceOf(
            insertInto("answer")
                    .columns("id", "content", "question_id", "language_id", "person_id", "uuid", "date_created")
                    .values("100", "Its simple. RTFM", "100", "1", "2", "answer-uuid-100", "2015-01-30 20:20:20")
                    .values("101", "Its not that simple. Its java. RTFM", "100", "1", "1", "answer-uuid-101", "2015-01-30 20:20:20")
                    .values("106", "Just google dude!!", "100", "3","1", "answer-uuid-106", "2015-01-30 20:20:20")
                    .values("102", "for(int i=0;i<n;i++){}", "101", "1", "1", "answer-uuid-102", "2015-01-30 20:20:20")
                    .values("103", "for(var i=0;i<n;i++){}", "101", "2", "1", "answer-uuid-103", "2015-01-30 20:20:20")
                    .values("104", "while(isTrue(condition)){}", "102", "1", "1", "answer-uuid-104", "2015-01-30 20:20:20")
                    .values("105", "function methodName(Object param){}", "103", "2", "1", "answer-uuid-105", "2015-01-30 20:20:20")
                    .build()
    );


    public static final Operation INSERT_COMMENTS = sequenceOf(
            insertInto("comment")
                    .columns("id", "content", "answer_id", "person_id","uuid", "date_created")
                    .values("100", "Sorry should have done that!", "100", "1","comment-uuid-100", "2015-01-30 20:20:20")
                    .values("101", "WTF!Die you moron!", "100", "1","comment-uuid-101", "2015-01-30 20:20:20")
                    .values("102", "oh! very similar to java", "103", "1","comment-uuid-102", "2015-01-30 20:20:20")
                    .build()
    );
    public static final Operation LOAD_STARTER_DATASET = sequenceOf(
            DELETE_ALL,
            INSERT_REFERENCE_DATA,
            INSERT_PERSONS,
            INSERT_QUESTIONS_STARTER_SET,
            INSERT_QUESTION_TAGS,
            INSERT_ANSWERS_STARTER_SET,
            INSERT_COMMENTS
    );
}
