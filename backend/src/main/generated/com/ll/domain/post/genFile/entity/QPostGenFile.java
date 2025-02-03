package com.ll.domain.post.genFile.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostGenFile is a Querydsl query type for PostGenFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostGenFile extends EntityPathBase<PostGenFile> {

    private static final long serialVersionUID = 108909480L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostGenFile postGenFile = new QPostGenFile("postGenFile");

    public final com.ll.global.jpa.entity.QBaseTime _super = new com.ll.global.jpa.entity.QBaseTime(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath filePath = createString("filePath");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final com.ll.domain.post.post.entity.QPost post;

    public final StringPath typeCode = createString("typeCode");

    public QPostGenFile(String variable) {
        this(PostGenFile.class, forVariable(variable), INITS);
    }

    public QPostGenFile(Path<? extends PostGenFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostGenFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostGenFile(PathMetadata metadata, PathInits inits) {
        this(PostGenFile.class, metadata, inits);
    }

    public QPostGenFile(Class<? extends PostGenFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new com.ll.domain.post.post.entity.QPost(forProperty("post"), inits.get("post")) : null;
    }

}

