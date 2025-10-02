package com.final_project.note.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNote is a Querydsl query type for Note
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNote extends EntityPathBase<Note> {

    private static final long serialVersionUID = -1936741042L;

    public static final QNote note = new QNote("note");

    public final com.final_project.global.Entities.QBaseEntity _super = new com.final_project.global.Entities.QBaseEntity(this);

    public final ListPath<Content, QContent> content = this.<Content, QContent>createList("content", Content.class, QContent.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath memberId = createString("memberId");

    public final StringPath noteId = createString("noteId");

    public final EnumPath<com.final_project.note.constants.NoteStatus> noteStatus = createEnum("noteStatus", com.final_project.note.constants.NoteStatus.class);

    public final StringPath privateMemo = createString("privateMemo");

    public final ListPath<Quiz, QQuiz> quizResult = this.<Quiz, QQuiz>createList("quizResult", Quiz.class, QQuiz.class, PathInits.DIRECT2);

    public final ListPath<Quiz, QQuiz> quizzes = this.<Quiz, QQuiz>createList("quizzes", Quiz.class, QQuiz.class, PathInits.DIRECT2);

    public final StringPath summary = createString("summary");

    public final ListPath<Tag, QTag> tags = this.<Tag, QTag>createList("tags", Tag.class, QTag.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath youtubeUrl = createString("youtubeUrl");

    public QNote(String variable) {
        super(Note.class, forVariable(variable));
    }

    public QNote(Path<? extends Note> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNote(PathMetadata metadata) {
        super(Note.class, metadata);
    }

}

