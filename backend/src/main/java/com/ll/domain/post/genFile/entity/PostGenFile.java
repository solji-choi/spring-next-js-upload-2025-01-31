package com.ll.domain.post.genFile.entity;

import com.ll.domain.post.post.entity.Post;
import com.ll.global.app.AppConfig;
import com.ll.global.jpa.entity.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostGenFile extends BaseTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private String typeCode;
    private int fileNo;
    private String originalFileName;
    private String metadata;
    private String fileDateDir;
    private String fileExt;
    private String fileExtTypeCode;
    private String fileExtType2Code;
    private String fileName;
    private int fileSize;

    public String getFilePath() {
        return AppConfig.getGenFileDirPath() + "/" + getModelName() + "/" + typeCode + "/" + fileDateDir + "/" + fileName;
    }

    @Override
    public boolean equals(Object o) {
        if(id != null) return  super.equals(0);

        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PostGenFile genFile = (PostGenFile) o;
        return fileNo == genFile.fileNo && Objects.equals(typeCode, genFile.typeCode);
    }

    @Override
    public int hashCode() {
        if(id != null) return super.hashCode();

        return Objects.hash(super.hashCode(), typeCode, fileNo);
    }
}
