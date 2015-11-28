package com.progbook.persistence;

import com.ninja_squad.dbsetup.operation.Operation;

import static com.ninja_squad.dbsetup.Operations.*;

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
                    .columns("id", "name", "description", "uuid")
                    .values("1", "java", "java lang", "uuid-001")
                    .values("2", "javascript", "a web lang", "uuid-002")
                    .values("3", "python", "snake lang", "uuid-003")
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
                    .columns("id", "firstname","lastname", "email", "uuid")
                    .values("1", "the","maker", "maker@world.com", "person-uuid-1")
                    .values("2", "follower","guy", "follower@world.com", "person-uuid-2")
                    .build()
    );
    public static final Operation INSERT_QUESTIONS_STARTER_SET = sequenceOf(
            insertInto("question")
                    .columns("id", "title", "category_id", "person_id", "uuid", "date_created", "description")
                    .values("100", "dev env setup", "1", "1", "question-uuid-100", "2015-01-30 21:21:21","How do you setup the dev environment for this? system.out.println('hello world')")
                    .values("101", "for loop", "2", "1", "question-uuid-101", "2015-01-30 20:20:20","How do you write a for loop in this?")
                    .values("102", "while loop", "2", "1", "question-uuid-102", "2015-01-30 20:20:20","How do you write a while loop in this?")
                    .values("103", "writing functions", "3", "1", "question-uuid-103", "2015-01-30 20:21:20","How do you write a method in this?")
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
                    .columns("id", "question_id", "language_id", "person_id", "uuid", "date_created", "content")
                    .values("100", "100", "2", "1", "answer-uuid-100", "2015-01-30 20:20:20","Its simple. RTFM.alert('you suck!');")
                    .values("101", "100", "1", "1", "answer-uuid-101", "2015-01-30 20:20:20","Its not that simple. Its java. system.out.println('i am goin home|->');got it?")
                    .values("106", "100", "3", "1", "answer-uuid-106", "2015-01-30 20:20:20","function methodName(Object param){}")
                    .values("102", "101", "1", "1", "answer-uuid-102", "2015-01-30 20:20:20","Just google dude!!")
                    .values("103", "101", "2", "1", "answer-uuid-103", "2015-01-30 20:20:20","for(int i=0;i<n;i++){}")
                    .values("104", "102", "1", "1", "answer-uuid-104", "2015-01-30 20:20:20","for(var i=0;i<n;i++){}")
                    .values("105", "103", "2", "1", "answer-uuid-105", "2015-01-30 20:20:20","while(isTrue(condition)){}")
                    .build()
    );

/*

    public static final Operation INSERT_ANSWER_CONTENT_BLOCKS = sequenceOf(
            insertInto("content_block")
                    .columns("id", "answer_id", "uuid", "content", "sequence_number", "title", "type", "language_id", "person_id", "date_created")
                    .values("1100", "100", "a-uuid-100", "Its simple. RTFM", "1", "setup", "text", "1", "2", "2015-01-30 20:20:20")
                    .values("1101", "100", "a-uuid-101", "alert('you suck!');", "2", "setup", "code", "1", "2", "2015-01-30 20:20:20")
                    .values("1102", "101", "a-uuid-102", "Its not that simple. Its java", "1", "java setup", "text", "1", "2", "2015-01-30 20:20:20")
                    .values("1103", "101", "a-uuid-103", "system.out.println('i am goin home|->');", "2", "java setup", "code", "1", "2", "2015-01-30 20:20:20")
                    .values("1104", "101", "a-uuid-104", "got it?", "3", "java setup", "text", "1", "2", "2015-01-30 20:20:20")
                    .values("1105", "102", "a-uuid-105", "Just google dude!!", "1", "env setup", "text", "3", "2", "2015-01-30 20:20:20")
                    .values("1106", "103", "a-uuid-106", "for(int i=0;i<n;i++){}", "1", "for loop", "code", "1", "2", "2015-01-30 20:20:20")
                    .values("1107", "104", "a-uuid-107", "for(var i=0;i<n;i++){}", "1", "for loop", "code", "2", "2", "2015-01-30 20:20:20")
                    .values("1108", "105", "a-uuid-108", "while(isTrue(condition)){}", "1", "while loop", "code", "1", "2", "2015-01-30 20:20:20")
                    .values("1109", "106", "a-uuid-109", "function methodName(Object param){}", "1", "method ", "code", "2", "2", "2015-01-30 20:20:20")
                    .build()
    );
*/

    public static final Operation INSERT_COMMENTS = sequenceOf(
            insertInto("comment")
                    .columns("id", "content", "answer_id", "person_id", "uuid", "date_created")
                    .values("100", "Sorry should have done that!", "100", "1", "comment-uuid-100", "2015-01-30 20:20:20")
                    .values("101", "WTF!Die you moron!", "100", "1", "comment-uuid-101", "2015-01-30 20:20:20")
                    .values("102", "oh! very similar to java", "103", "1", "comment-uuid-102", "2015-01-30 20:20:20")
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
