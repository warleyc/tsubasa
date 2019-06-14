package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMatchResultCutin;
import io.shm.tsubasa.repository.MMatchResultCutinRepository;
import io.shm.tsubasa.service.dto.MMatchResultCutinDTO;
import io.shm.tsubasa.service.mapper.MMatchResultCutinMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMatchResultCutin}.
 */
@Service
@Transactional
public class MMatchResultCutinService {

    private final Logger log = LoggerFactory.getLogger(MMatchResultCutinService.class);

    private final MMatchResultCutinRepository mMatchResultCutinRepository;

    private final MMatchResultCutinMapper mMatchResultCutinMapper;

    public MMatchResultCutinService(MMatchResultCutinRepository mMatchResultCutinRepository, MMatchResultCutinMapper mMatchResultCutinMapper) {
        this.mMatchResultCutinRepository = mMatchResultCutinRepository;
        this.mMatchResultCutinMapper = mMatchResultCutinMapper;
    }

    /**
     * Save a mMatchResultCutin.
     *
     * @param mMatchResultCutinDTO the entity to save.
     * @return the persisted entity.
     */
    public MMatchResultCutinDTO save(MMatchResultCutinDTO mMatchResultCutinDTO) {
        log.debug("Request to save MMatchResultCutin : {}", mMatchResultCutinDTO);
        MMatchResultCutin mMatchResultCutin = mMatchResultCutinMapper.toEntity(mMatchResultCutinDTO);
        mMatchResultCutin = mMatchResultCutinRepository.save(mMatchResultCutin);
        return mMatchResultCutinMapper.toDto(mMatchResultCutin);
    }

    /**
     * Get all the mMatchResultCutins.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMatchResultCutinDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMatchResultCutins");
        return mMatchResultCutinRepository.findAll(pageable)
            .map(mMatchResultCutinMapper::toDto);
    }


    /**
     * Get one mMatchResultCutin by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMatchResultCutinDTO> findOne(Long id) {
        log.debug("Request to get MMatchResultCutin : {}", id);
        return mMatchResultCutinRepository.findById(id)
            .map(mMatchResultCutinMapper::toDto);
    }

    /**
     * Delete the mMatchResultCutin by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMatchResultCutin : {}", id);
        mMatchResultCutinRepository.deleteById(id);
    }
}
