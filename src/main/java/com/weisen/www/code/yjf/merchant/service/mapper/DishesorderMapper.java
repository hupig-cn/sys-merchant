package com.weisen.www.code.yjf.merchant.service.mapper;

import com.weisen.www.code.yjf.merchant.domain.*;
import com.weisen.www.code.yjf.merchant.service.dto.DishesorderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dishesorder} and its DTO {@link DishesorderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DishesorderMapper extends EntityMapper<DishesorderDTO, Dishesorder> {



    default Dishesorder fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dishesorder dishesorder = new Dishesorder();
        dishesorder.setId(id);
        return dishesorder;
    }
}
