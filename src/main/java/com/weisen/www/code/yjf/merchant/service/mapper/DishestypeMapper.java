package com.weisen.www.code.yjf.merchant.service.mapper;

import com.weisen.www.code.yjf.merchant.domain.*;
import com.weisen.www.code.yjf.merchant.service.dto.DishestypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dishestype} and its DTO {@link DishestypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DishestypeMapper extends EntityMapper<DishestypeDTO, Dishestype> {



    default Dishestype fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dishestype dishestype = new Dishestype();
        dishestype.setId(id);
        return dishestype;
    }
}
