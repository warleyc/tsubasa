package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MRivalEncountCutin;
import io.shm.tsubasa.repository.MRivalEncountCutinRepository;
import io.shm.tsubasa.service.dto.MRivalEncountCutinDTO;
import io.shm.tsubasa.service.mapper.MRivalEncountCutinMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MRivalEncountCutin}.
 */
@Service
@Transactional
public class MRivalEncountCutinService {

    private final Logger log = LoggerFactory.getLogger(MRivalEncountCutinService.class);

    private final MRivalEncountCutinRepository mRivalEncountCutinRepository;

    private final MRivalEncountCutinMapper mRivalEncountCutinMapper;

    public MRivalEncountCutinService(MRivalEncountCutinRepository mRivalEncountCutinRepository, MRivalEncountCutinMapper mRivalEncountCutinMapper) {
        this.mRivalEncountCutinRepository = mRivalEncountCutinRepository;
        this.mRivalEncountCutinMapper = mRivalEncountCutinMapper;
    }

    /**
     * Save a mRivalEncountCutin.
     *
     * @param mRivalEncountCutinDTO the entity to save.
     * @return the persisted entity.
     */
    public MRivalEncountCutinDTO save(MRivalEncountCutinDTO mRivalEncountCutinDTO) {
        log.debug("Request to save MRivalEncountCutin : {}", mRivalEncountCutinDTO);
        MRivalEncountCutin mRivalEncountCutin = mRivalEncountCutinMapper.toEntity(mRivalEncountCutinDTO);
        mRivalEncountCutin = mRivalEncountCutinRepository.save(mRivalEncountCutin);
        return mRivalEncountCutinMapper.toDto(mRivalEncountCutin);
    }

    /**
     * Get all the mRivalEncountCutins.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MRivalEncountCutinDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MRivalEncountCutins");
        return mRivalEncountCutinRepository.findAll(pageable)
            .map(mRivalEncountCutinMapper::toDto);
    }


    /**
     * Get one mRivalEncountCutin by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MRivalEncountCutinDTO> findOne(Long id) {
        log.debug("Request to get MRivalEncountCutin : {}", id);
        return mRivalEncountCutinRepository.findById(id)
            .map(mRivalEncountCutinMapper::toDto);
    }

    /**
     * Delete the mRivalEncountCutin by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MRivalEncountCutin : {}", id);
        mRivalEncountCutinRepository.deleteById(id);
    }
}
