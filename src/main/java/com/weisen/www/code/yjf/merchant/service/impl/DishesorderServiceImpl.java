package com.weisen.www.code.yjf.merchant.service.impl;

import com.weisen.www.code.yjf.merchant.service.DishesorderService;
import com.weisen.www.code.yjf.merchant.domain.Dishesorder;
import com.weisen.www.code.yjf.merchant.repository.DishesorderRepository;
import com.weisen.www.code.yjf.merchant.service.dto.DishesorderDTO;
import com.weisen.www.code.yjf.merchant.service.mapper.DishesorderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Dishesorder}.
 */
@Service
@Transactional
public class DishesorderServiceImpl implements DishesorderService {

    private final Logger log = LoggerFactory.getLogger(DishesorderServiceImpl.class);

    private final DishesorderRepository dishesorderRepository;

    private final DishesorderMapper dishesorderMapper;

    public DishesorderServiceImpl(DishesorderRepository dishesorderRepository, DishesorderMapper dishesorderMapper) {
        this.dishesorderRepository = dishesorderRepository;
        this.dishesorderMapper = dishesorderMapper;
    }

    /**
     * Save a dishesorder.
     *
     * @param dishesorderDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DishesorderDTO save(DishesorderDTO dishesorderDTO) {
        log.debug("Request to save Dishesorder : {}", dishesorderDTO);
        Dishesorder dishesorder = dishesorderMapper.toEntity(dishesorderDTO);
        dishesorder = dishesorderRepository.save(dishesorder);
        return dishesorderMapper.toDto(dishesorder);
    }

    /**
     * Get all the dishesorders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DishesorderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Dishesorders");
        return dishesorderRepository.findAll(pageable)
            .map(dishesorderMapper::toDto);
    }


    /**
     * Get one dishesorder by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DishesorderDTO> findOne(Long id) {
        log.debug("Request to get Dishesorder : {}", id);
        return dishesorderRepository.findById(id)
            .map(dishesorderMapper::toDto);
    }

    /**
     * Delete the dishesorder by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dishesorder : {}", id);
        dishesorderRepository.deleteById(id);
    }
}
