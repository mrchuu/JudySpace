package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper;

import java.util.List;

// D : Dto
// E : Entity
// R : Request Entity
public interface EntityMapper<D,E,R> {
    E toEntitty(D var1);

    D toDto(E var1);

    E toE(R var1);

    List<E> toE(List<R> var1);

    List<E> toEntity(List<D> var1);

    List<D> toDto(List<E> var1);

    R toR(E var1);

    List<R> toR(List<R> var1);
}
