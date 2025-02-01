package com.ll.domain.post.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostComment is a Querydsl query type for PostComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostComment extends EntityPathBase<PostComment> {

    private static final long serialVersionUID = 1234829512L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostComment postComment = new QPostComment("postComment");

    public final com.ll.global.jpa.entity.QBaseTime _super = new com.ll.global.jpa.entity.QBaseTime(this);

    public final com.ll.domain.member.member.entity.QMember author;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final com.ll.domain.post.post.entity.QPost post;

    public QPostComment(String variable) {
        this(PostComment.class, forVariable(variable), INITS);
    }

    public QPostComment(Path<? extends PostComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostComment(PathMetadata metadata, PathInits inits) {
        this(PostComment.class, metadata, inits);
    }

    public QPostComment(Class<? extends PostComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new com.ll.domain.member.member.entity.QMember(forProperty("author")) : null;
        this.post = inits.isInitialized("post") ? new com.ll.domain.post.post.entity.QPost(forProperty("post"), inits.get("post")) : null;
    }

}

