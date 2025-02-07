package com.ll.domain.base.genFile.genFile.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGenFile is a Querydsl query type for GenFile
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QGenFile extends EntityPathBase<GenFile> {

    private static final long serialVersionUID = 1802906713L;

    public static final QGenFile genFile = new QGenFile("genFile");

    public final com.ll.global.jpa.entity.QBaseTime _super = new com.ll.global.jpa.entity.QBaseTime(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath fileDateDir = createString("fileDateDir");

    public final StringPath fileExt = createString("fileExt");

    public final StringPath fileExtType2Code = createString("fileExtType2Code");

    public final StringPath fileExtTypeCode = createString("fileExtTypeCode");

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Integer> fileNo = createNumber("fileNo", Integer.class);

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Integer> fileSize = createNumber("fileSize", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath metadata = createString("metadata");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final StringPath originalFileName = createString("originalFileName");

    public final StringPath typeCode = createString("typeCode");

    public QGenFile(String variable) {
        super(GenFile.class, forVariable(variable));
    }

    public QGenFile(Path<? extends GenFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGenFile(PathMetadata metadata) {
        super(GenFile.class, metadata);
    }

}

