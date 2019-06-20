package com.weisen.www.code.yjf.merchant.service;

import com.weisen.www.code.yjf.merchant.service.dto.DishesorderDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.merchant.domain.Dishesorder}.
 */
public interface DishesorderService {

    /**
     * Save a dishesorder.
     *
     * @param dishesorderDTO the entity to save.
     * @return the persisted entity.
     */
    DishesorderDTO save(DishesorderDTO dishesorderDTO);

    /**
     * Get all the dishesorders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DishesorderDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dishesorder.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DishesorderDTO> findOne(Long id);

    /**
     * Delete the "id" dishesorder.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
