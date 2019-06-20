package com.weisen.www.code.yjf.merchant.service;

import com.weisen.www.code.yjf.merchant.service.dto.ShoplocationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.merchant.domain.Shoplocation}.
 */
public interface ShoplocationService {

    /**
     * Save a shoplocation.
     *
     * @param shoplocationDTO the entity to save.
     * @return the persisted entity.
     */
    ShoplocationDTO save(ShoplocationDTO shoplocationDTO);

    /**
     * Get all the shoplocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ShoplocationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" shoplocation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ShoplocationDTO> findOne(Long id);

    /**
     * Delete the "id" shoplocation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
