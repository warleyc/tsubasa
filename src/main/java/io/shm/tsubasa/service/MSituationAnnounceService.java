package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MSituationAnnounce;
import io.shm.tsubasa.repository.MSituationAnnounceRepository;
import io.shm.tsubasa.service.dto.MSituationAnnounceDTO;
import io.shm.tsubasa.service.mapper.MSituationAnnounceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MSituationAnnounce}.
 */
@Service
@Transactional
public class MSituationAnnounceService {

    private final Logger log = LoggerFactory.getLogger(MSituationAnnounceService.class);

    private final MSituationAnnounceRepository mSituationAnnounceRepository;

    private final MSituationAnnounceMapper mSituationAnnounceMapper;

    public MSituationAnnounceService(MSituationAnnounceRepository mSituationAnnounceRepository, MSituationAnnounceMapper mSituationAnnounceMapper) {
        this.mSituationAnnounceRepository = mSituationAnnounceRepository;
        this.mSituationAnnounceMapper = mSituationAnnounceMapper;
    }

    /**
     * Save a mSituationAnnounce.
     *
     * @param mSituationAnnounceDTO the entity to save.
     * @return the persisted entity.
     */
    public MSituationAnnounceDTO save(MSituationAnnounceDTO mSituationAnnounceDTO) {
        log.debug("Request to save MSituationAnnounce : {}", mSituationAnnounceDTO);
        MSituationAnnounce mSituationAnnounce = mSituationAnnounceMapper.toEntity(mSituationAnnounceDTO);
        mSituationAnnounce = mSituationAnnounceRepository.save(mSituationAnnounce);
        return mSituationAnnounceMapper.toDto(mSituationAnnounce);
    }

    /**
     * Get all the mSituationAnnounces.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MSituationAnnounceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MSituationAnnounces");
        return mSituationAnnounceRepository.findAll(pageable)
            .map(mSituationAnnounceMapper::toDto);
    }


    /**
     * Get one mSituationAnnounce by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MSituationAnnounceDTO> findOne(Long id) {
        log.debug("Request to get MSituationAnnounce : {}", id);
        return mSituationAnnounceRepository.findById(id)
            .map(mSituationAnnounceMapper::toDto);
    }

    /**
     * Delete the mSituationAnnounce by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MSituationAnnounce : {}", id);
        mSituationAnnounceRepository.deleteById(id);
    }
}
