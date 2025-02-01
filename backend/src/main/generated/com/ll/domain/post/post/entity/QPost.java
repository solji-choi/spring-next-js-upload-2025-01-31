package com.ll.domain.post.post.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = -756501822L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final com.ll.global.jpa.entity.QBaseTime _super = new com.ll.global.jpa.entity.QBaseTime(this);

    public final com.ll.domain.member.member.entity.QMember author;

    public final ListPath<com.ll.domain.post.comment.entity.PostComment, com.ll.domain.post.comment.entity.QPostComment> comments = this.<com.ll.domain.post.comment.entity.PostComment, com.ll.domain.post.comment.entity.QPostComment>createList("comments", com.ll.domain.post.comment.entity.PostComment.class, com.ll.domain.post.comment.entity.QPostComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final BooleanPath listed = createBoolean("listed");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final BooleanPath published = createBoolean("published");

    public final StringPath title = createString("title");

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new com.ll.domain.member.member.entity.QMember(forProperty("author")) : null;
    }

}

