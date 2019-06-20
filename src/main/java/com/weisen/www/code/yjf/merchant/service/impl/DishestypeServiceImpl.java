package com.weisen.www.code.yjf.merchant.service.impl;

import com.weisen.www.code.yjf.merchant.service.DishestypeService;
import com.weisen.www.code.yjf.merchant.domain.Dishestype;
import com.weisen.www.code.yjf.merchant.repository.DishestypeRepository;
import com.weisen.www.code.yjf.merchant.service.dto.DishestypeDTO;
import com.weisen.www.code.yjf.merchant.service.mapper.DishestypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Dishestype}.
 */
@Service
@Transactional
public class DishestypeServiceImpl implements DishestypeService {

    private final Logger log = LoggerFactory.getLogger(DishestypeServiceImpl.class);

    private final DishestypeRepository dishestypeRepository;

    private final DishestypeMapper dishestypeMapper;

    public DishestypeServiceImpl(DishestypeRepository dishestypeRepository, DishestypeMapper dishestypeMapper) {
        this.dishestypeRepository = dishestypeRepository;
        this.dishestypeMapper = dishestypeMapper;
    }

    /**
     * Save a dishestype.
     *
     * @param dishestypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DishestypeDTO save(DishestypeDTO dishestypeDTO) {
        log.debug("Request to save Dishestype : {}", dishestypeDTO);
        Dishestype dishestype = dishestypeMapper.toEntity(dishestypeDTO);
        dishestype = dishestypeRepository.save(dishestype);
        return dishestypeMapper.toDto(dishestype);
    }

    /**
     * Get all the dishestypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DishestypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Dishestypes");
        return dishestypeRepository.findAll(pageable)
            .map(dishestypeMapper::toDto);
    }


    /**
     * Get one dishestype by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DishestypeDTO> findOne(Long id) {
        log.debug("Request to get Dishestype : {}", id);
        return dishestypeRepository.findById(id)
            .map(dishestypeMapper::toDto);
    }

    /**
     * Delete the dishestype by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dishestype : {}", id);
        dishestypeRepository.deleteById(id);
    }
}
