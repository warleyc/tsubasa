package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MColorPaletteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MColorPalette} and its DTO {@link MColorPaletteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MColorPaletteMapper extends EntityMapper<MColorPaletteDTO, MColorPalette> {



    default MColorPalette fromId(Long id) {
        if (id == null) {
            return null;
        }
        MColorPalette mColorPalette = new MColorPalette();
        mColorPalette.setId(id);
        return mColorPalette;
    }
}
