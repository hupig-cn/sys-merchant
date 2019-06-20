package com.weisen.www.code.yjf.merchant.service.impl;

import com.weisen.www.code.yjf.merchant.service.ShoplocationService;
import com.weisen.www.code.yjf.merchant.domain.Shoplocation;
import com.weisen.www.code.yjf.merchant.repository.ShoplocationRepository;
import com.weisen.www.code.yjf.merchant.service.dto.ShoplocationDTO;
import com.weisen.www.code.yjf.merchant.service.mapper.ShoplocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Shoplocation}.
 */
@Service
@Transactional
public class ShoplocationServiceImpl implements ShoplocationService {

    private final Logger log = LoggerFactory.getLogger(ShoplocationServiceImpl.class);

    private final ShoplocationRepository shoplocationRepository;

    private final ShoplocationMapper shoplocationMapper;

    public ShoplocationServiceImpl(ShoplocationRepository shoplocationRepository, ShoplocationMapper shoplocationMapper) {
        this.shoplocationRepository = shoplocationRepository;
        this.shoplocationMapper = shoplocationMapper;
    }

    /**
     * Save a shoplocation.
     *
     * @param shoplocationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ShoplocationDTO save(ShoplocationDTO shoplocationDTO) {
        log.debug("Request to save Shoplocation : {}", shoplocationDTO);
        Shoplocation shoplocation = shoplocationMapper.toEntity(shoplocationDTO);
        shoplocation = shoplocationRepository.save(shoplocation);
        return shoplocationMapper.toDto(shoplocation);
    }

    /**
     * Get all the shoplocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ShoplocationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Shoplocations");
        return shoplocationRepository.findAll(pageable)
            .map(shoplocationMapper::toDto);
    }


    /**
     * Get one shoplocation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ShoplocationDTO> findOne(Long id) {
        log.debug("Request to get Shoplocation : {}", id);
        return shoplocationRepository.findById(id)
            .map(shoplocationMapper::toDto);
    }

    /**
     * Delete the shoplocation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Shoplocation : {}", id);
        shoplocationRepository.deleteById(id);
    }
}
