package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MColorPalette;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MColorPalette entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MColorPaletteRepository extends JpaRepository<MColorPalette, Long>, JpaSpecificationExecutor<MColorPalette> {

}
