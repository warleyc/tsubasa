package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MExtensionSaleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MExtensionSale} and its DTO {@link MExtensionSaleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MExtensionSaleMapper extends EntityMapper<MExtensionSaleDTO, MExtensionSale> {



    default MExtensionSale fromId(Long id) {
        if (id == null) {
            return null;
        }
        MExtensionSale mExtensionSale = new MExtensionSale();
        mExtensionSale.setId(id);
        return mExtensionSale;
    }
}
