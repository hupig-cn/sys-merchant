package com.weisen.www.code.yjf.merchant.service;

import com.weisen.www.code.yjf.merchant.service.dto.DishestypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.merchant.domain.Dishestype}.
 */
public interface DishestypeService {

    /**
     * Save a dishestype.
     *
     * @param dishestypeDTO the entity to save.
     * @return the persisted entity.
     */
    DishestypeDTO save(DishestypeDTO dishestypeDTO);

    /**
     * Get all the dishestypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DishestypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dishestype.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DishestypeDTO> findOne(Long id);

    /**
     * Delete the "id" dishestype.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
