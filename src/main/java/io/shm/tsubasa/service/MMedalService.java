package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMedal;
import io.shm.tsubasa.repository.MMedalRepository;
import io.shm.tsubasa.service.dto.MMedalDTO;
import io.shm.tsubasa.service.mapper.MMedalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMedal}.
 */
@Service
@Transactional
public class MMedalService {

    private final Logger log = LoggerFactory.getLogger(MMedalService.class);

    private final MMedalRepository mMedalRepository;

    private final MMedalMapper mMedalMapper;

    public MMedalService(MMedalRepository mMedalRepository, MMedalMapper mMedalMapper) {
        this.mMedalRepository = mMedalRepository;
        this.mMedalMapper = mMedalMapper;
    }

    /**
     * Save a mMedal.
     *
     * @param mMedalDTO the entity to save.
     * @return the persisted entity.
     */
    public MMedalDTO save(MMedalDTO mMedalDTO) {
        log.debug("Request to save MMedal : {}", mMedalDTO);
        MMedal mMedal = mMedalMapper.toEntity(mMedalDTO);
        mMedal = mMedalRepository.save(mMedal);
        return mMedalMapper.toDto(mMedal);
    }

    /**
     * Get all the mMedals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMedalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMedals");
        return mMedalRepository.findAll(pageable)
            .map(mMedalMapper::toDto);
    }


    /**
     * Get one mMedal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMedalDTO> findOne(Long id) {
        log.debug("Request to get MMedal : {}", id);
        return mMedalRepository.findById(id)
            .map(mMedalMapper::toDto);
    }

    /**
     * Delete the mMedal by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMedal : {}", id);
        mMedalRepository.deleteById(id);
    }
}
