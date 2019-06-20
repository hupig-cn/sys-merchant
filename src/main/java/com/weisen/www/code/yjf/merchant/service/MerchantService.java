package com.weisen.www.code.yjf.merchant.service;

import com.weisen.www.code.yjf.merchant.service.dto.MerchantDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.merchant.domain.Merchant}.
 */
public interface MerchantService {

    /**
     * Save a merchant.
     *
     * @param merchantDTO the entity to save.
     * @return the persisted entity.
     */
    MerchantDTO save(MerchantDTO merchantDTO);

    /**
     * Get all the merchants.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MerchantDTO> findAll(Pageable pageable);


    /**
     * Get the "id" merchant.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MerchantDTO> findOne(Long id);

    /**
     * Delete the "id" merchant.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
