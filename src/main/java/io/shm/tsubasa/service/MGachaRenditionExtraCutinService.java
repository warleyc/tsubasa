package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGachaRenditionExtraCutin;
import io.shm.tsubasa.repository.MGachaRenditionExtraCutinRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionExtraCutinDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionExtraCutinMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGachaRenditionExtraCutin}.
 */
@Service
@Transactional
public class MGachaRenditionExtraCutinService {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionExtraCutinService.class);

    private final MGachaRenditionExtraCutinRepository mGachaRenditionExtraCutinRepository;

    private final MGachaRenditionExtraCutinMapper mGachaRenditionExtraCutinMapper;

    public MGachaRenditionExtraCutinService(MGachaRenditionExtraCutinRepository mGachaRenditionExtraCutinRepository, MGachaRenditionExtraCutinMapper mGachaRenditionExtraCutinMapper) {
        this.mGachaRenditionExtraCutinRepository = mGachaRenditionExtraCutinRepository;
        this.mGachaRenditionExtraCutinMapper = mGachaRenditionExtraCutinMapper;
    }

    /**
     * Save a mGachaRenditionExtraCutin.
     *
     * @param mGachaRenditionExtraCutinDTO the entity to save.
     * @return the persisted entity.
     */
    public MGachaRenditionExtraCutinDTO save(MGachaRenditionExtraCutinDTO mGachaRenditionExtraCutinDTO) {
        log.debug("Request to save MGachaRenditionExtraCutin : {}", mGachaRenditionExtraCutinDTO);
        MGachaRenditionExtraCutin mGachaRenditionExtraCutin = mGachaRenditionExtraCutinMapper.toEntity(mGachaRenditionExtraCutinDTO);
        mGachaRenditionExtraCutin = mGachaRenditionExtraCutinRepository.save(mGachaRenditionExtraCutin);
        return mGachaRenditionExtraCutinMapper.toDto(mGachaRenditionExtraCutin);
    }

    /**
     * Get all the mGachaRenditionExtraCutins.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionExtraCutinDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGachaRenditionExtraCutins");
        return mGachaRenditionExtraCutinRepository.findAll(pageable)
            .map(mGachaRenditionExtraCutinMapper::toDto);
    }


    /**
     * Get one mGachaRenditionExtraCutin by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGachaRenditionExtraCutinDTO> findOne(Long id) {
        log.debug("Request to get MGachaRenditionExtraCutin : {}", id);
        return mGachaRenditionExtraCutinRepository.findById(id)
            .map(mGachaRenditionExtraCutinMapper::toDto);
    }

    /**
     * Delete the mGachaRenditionExtraCutin by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGachaRenditionExtraCutin : {}", id);
        mGachaRenditionExtraCutinRepository.deleteById(id);
    }
}
