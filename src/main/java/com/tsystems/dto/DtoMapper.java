package com.tsystems.dto;

/**
 * Created by nikita on 26.09.2020.
 */
public interface DtoMapper<E> {

    /**
     * Converting this DTO object to entity
     * @return Entity object
     */
    E convertToEntity();

    /**
     * Fill DTO object from entity
     * @param entity Entity with data
     */
    void convertToDto(E entity);

    /**
     * Add many-to-many and one-to-many dependencies
     * @param entity entity with dependencies
     * @return this object with dependencies
     */
    DtoMapper addDependencies(E entity);
}
