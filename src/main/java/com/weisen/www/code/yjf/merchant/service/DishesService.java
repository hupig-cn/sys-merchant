package com.weisen.www.code.yjf.merchant.service;

import com.weisen.www.code.yjf.merchant.service.dto.DishesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.merchant.domain.Dishes}.
 */
public interface DishesService {

    /**
     * Save a dishes.
     *
     * @param dishesDTO the entity to save.
     * @return the persisted entity.
     */
    DishesDTO save(DishesDTO dishesDTO);

    /**
     * Get all the dishes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DishesDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dishes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DishesDTO> findOne(Long id);

    /**
     * Delete the "id" dishes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
