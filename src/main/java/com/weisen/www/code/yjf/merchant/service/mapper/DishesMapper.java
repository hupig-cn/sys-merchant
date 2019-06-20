package com.weisen.www.code.yjf.merchant.service.mapper;

import com.weisen.www.code.yjf.merchant.domain.*;
import com.weisen.www.code.yjf.merchant.service.dto.DishesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dishes} and its DTO {@link DishesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DishesMapper extends EntityMapper<DishesDTO, Dishes> {



    default Dishes fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dishes dishes = new Dishes();
        dishes.setId(id);
        return dishes;
    }
}
