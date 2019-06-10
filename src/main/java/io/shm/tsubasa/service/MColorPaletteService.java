package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MColorPalette;
import io.shm.tsubasa.repository.MColorPaletteRepository;
import io.shm.tsubasa.service.dto.MColorPaletteDTO;
import io.shm.tsubasa.service.mapper.MColorPaletteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MColorPalette}.
 */
@Service
@Transactional
public class MColorPaletteService {

    private final Logger log = LoggerFactory.getLogger(MColorPaletteService.class);

    private final MColorPaletteRepository mColorPaletteRepository;

    private final MColorPaletteMapper mColorPaletteMapper;

    public MColorPaletteService(MColorPaletteRepository mColorPaletteRepository, MColorPaletteMapper mColorPaletteMapper) {
        this.mColorPaletteRepository = mColorPaletteRepository;
        this.mColorPaletteMapper = mColorPaletteMapper;
    }

    /**
     * Save a mColorPalette.
     *
     * @param mColorPaletteDTO the entity to save.
     * @return the persisted entity.
     */
    public MColorPaletteDTO save(MColorPaletteDTO mColorPaletteDTO) {
        log.debug("Request to save MColorPalette : {}", mColorPaletteDTO);
        MColorPalette mColorPalette = mColorPaletteMapper.toEntity(mColorPaletteDTO);
        mColorPalette = mColorPaletteRepository.save(mColorPalette);
        return mColorPaletteMapper.toDto(mColorPalette);
    }

    /**
     * Get all the mColorPalettes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MColorPaletteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MColorPalettes");
        return mColorPaletteRepository.findAll(pageable)
            .map(mColorPaletteMapper::toDto);
    }


    /**
     * Get one mColorPalette by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MColorPaletteDTO> findOne(Long id) {
        log.debug("Request to get MColorPalette : {}", id);
        return mColorPaletteRepository.findById(id)
            .map(mColorPaletteMapper::toDto);
    }

    /**
     * Delete the mColorPalette by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MColorPalette : {}", id);
        mColorPaletteRepository.deleteById(id);
    }
}
