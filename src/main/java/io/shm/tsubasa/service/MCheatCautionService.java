package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCheatCaution;
import io.shm.tsubasa.repository.MCheatCautionRepository;
import io.shm.tsubasa.service.dto.MCheatCautionDTO;
import io.shm.tsubasa.service.mapper.MCheatCautionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCheatCaution}.
 */
@Service
@Transactional
public class MCheatCautionService {

    private final Logger log = LoggerFactory.getLogger(MCheatCautionService.class);

    private final MCheatCautionRepository mCheatCautionRepository;

    private final MCheatCautionMapper mCheatCautionMapper;

    public MCheatCautionService(MCheatCautionRepository mCheatCautionRepository, MCheatCautionMapper mCheatCautionMapper) {
        this.mCheatCautionRepository = mCheatCautionRepository;
        this.mCheatCautionMapper = mCheatCautionMapper;
    }

    /**
     * Save a mCheatCaution.
     *
     * @param mCheatCautionDTO the entity to save.
     * @return the persisted entity.
     */
    public MCheatCautionDTO save(MCheatCautionDTO mCheatCautionDTO) {
        log.debug("Request to save MCheatCaution : {}", mCheatCautionDTO);
        MCheatCaution mCheatCaution = mCheatCautionMapper.toEntity(mCheatCautionDTO);
        mCheatCaution = mCheatCautionRepository.save(mCheatCaution);
        return mCheatCautionMapper.toDto(mCheatCaution);
    }

    /**
     * Get all the mCheatCautions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCheatCautionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCheatCautions");
        return mCheatCautionRepository.findAll(pageable)
            .map(mCheatCautionMapper::toDto);
    }


    /**
     * Get one mCheatCaution by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCheatCautionDTO> findOne(Long id) {
        log.debug("Request to get MCheatCaution : {}", id);
        return mCheatCautionRepository.findById(id)
            .map(mCheatCautionMapper::toDto);
    }

    /**
     * Delete the mCheatCaution by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCheatCaution : {}", id);
        mCheatCautionRepository.deleteById(id);
    }
}
