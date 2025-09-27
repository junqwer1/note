package com.final_project.note.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNote is a Querydsl query type for Note
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNote extends EntityPathBase<Note> {

    private static final long serialVersionUID = -1936741042L;

    public static final QNote note = new QNote("note");

    public final com.final_project.global.Entities.QBaseEntity _super = new com.final_project.global.Entities.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final EnumPath<com.final_project.note.constants.IsContentStatus> isPublicContent = createEnum("isPublicContent", com.final_project.note.constants.IsContentStatus.class);

    public final EnumPath<com.final_project.note.constants.IsNoteStatus> isPublicNote = createEnum("isPublicNote", com.final_project.note.constants.IsNoteStatus.class);

    public final StringPath memberId = createString("memberId");

    public final StringPath noteId = createString("noteId");

    public final EnumPath<com.final_project.note.constants.NoteStatus> noteStatus = createEnum("noteStatus", com.final_project.note.constants.NoteStatus.class);

    public final StringPath privateMemo = createString("privateMemo");

    public final StringPath summary = createString("summary");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

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

