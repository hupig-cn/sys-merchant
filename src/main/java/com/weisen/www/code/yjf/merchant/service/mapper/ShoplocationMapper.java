package com.weisen.www.code.yjf.merchant.service.mapper;

import com.weisen.www.code.yjf.merchant.domain.*;
import com.weisen.www.code.yjf.merchant.service.dto.ShoplocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Shoplocation} and its DTO {@link ShoplocationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ShoplocationMapper extends EntityMapper<ShoplocationDTO, Shoplocation> {



    default Shoplocation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Shoplocation shoplocation = new Shoplocation();
        shoplocation.setId(id);
        return shoplocation;
    }
}
