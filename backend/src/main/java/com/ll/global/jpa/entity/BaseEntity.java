package com.ll.global.jpa.entity;

import com.ll.standard.util.Ut;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY) // AUTO_INCREMENT
    @Setter(AccessLevel.PROTECTED)
    @EqualsAndHashCode.Include
    protected Long id;

    public String getModelName() {
        String simpleName = this.getClass().getSimpleName();

        return Ut.str.lcfirst(simpleName);
    }
}