package com.final_project.note.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuizResult is a Querydsl query type for QuizResult
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuizResult extends EntityPathBase<QuizResult> {

    private static final long serialVersionUID = 568298286L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuizResult quizResult = new QQuizResult("quizResult");

    public final StringPath memberId = createString("memberId");

    public final QNote note;

    public final NumberPath<Long> resultId = createNumber("resultId", Long.class);

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> solvedAt = createDateTime("solvedAt", java.time.LocalDateTime.class);

    public final StringPath submittedAnswers = createString("submittedAnswers");

    public QQuizResult(String variable) {
        this(QuizResult.class, forVariable(variable), INITS);
    }

    public QQuizResult(Path<? extends QuizResult> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuizResult(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuizResult(PathMetadata metadata, PathInits inits) {
        this(QuizResult.class, metadata, inits);
    }

    public QQuizResult(Class<? extends QuizResult> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.note = inits.isInitialized("note") ? new QNote(forProperty("note")) : null;
    }

}

