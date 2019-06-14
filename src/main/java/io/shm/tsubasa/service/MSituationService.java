package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MSituation;
import io.shm.tsubasa.repository.MSituationRepository;
import io.shm.tsubasa.service.dto.MSituationDTO;
import io.shm.tsubasa.service.mapper.MSituationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MSituation}.
 */
@Service
@Transactional
public class MSituationService {

    private final Logger log = LoggerFactory.getLogger(MSituationService.class);

    private final MSituationRepository mSituationRepository;

    private final MSituationMapper mSituationMapper;

    public MSituationService(MSituationRepository mSituationRepository, MSituationMapper mSituationMapper) {
        this.mSituationRepository = mSituationRepository;
        this.mSituationMapper = mSituationMapper;
    }

    /**
     * Save a mSituation.
     *
     * @param mSituationDTO the entity to save.
     * @return the persisted entity.
     */
    public MSituationDTO save(MSituationDTO mSituationDTO) {
        log.debug("Request to save MSituation : {}", mSituationDTO);
        MSituation mSituation = mSituationMapper.toEntity(mSituationDTO);
        mSituation = mSituationRepository.save(mSituation);
        return mSituationMapper.toDto(mSituation);
    }

    /**
     * Get all the mSituations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MSituationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MSituations");
        return mSituationRepository.findAll(pageable)
            .map(mSituationMapper::toDto);
    }


    /**
     * Get one mSituation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MSituationDTO> findOne(Long id) {
        log.debug("Request to get MSituation : {}", id);
        return mSituationRepository.findById(id)
            .map(mSituationMapper::toDto);
    }

    /**
     * Delete the mSituation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MSituation : {}", id);
        mSituationRepository.deleteById(id);
    }
}
