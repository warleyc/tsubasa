package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MAction;
import io.shm.tsubasa.repository.MActionRepository;
import io.shm.tsubasa.service.dto.MActionDTO;
import io.shm.tsubasa.service.mapper.MActionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MAction}.
 */
@Service
@Transactional
public class MActionService {

    private final Logger log = LoggerFactory.getLogger(MActionService.class);

    private final MActionRepository mActionRepository;

    private final MActionMapper mActionMapper;

    public MActionService(MActionRepository mActionRepository, MActionMapper mActionMapper) {
        this.mActionRepository = mActionRepository;
        this.mActionMapper = mActionMapper;
    }

    /**
     * Save a mAction.
     *
     * @param mActionDTO the entity to save.
     * @return the persisted entity.
     */
    public MActionDTO save(MActionDTO mActionDTO) {
        log.debug("Request to save MAction : {}", mActionDTO);
        MAction mAction = mActionMapper.toEntity(mActionDTO);
        mAction = mActionRepository.save(mAction);
        return mActionMapper.toDto(mAction);
    }

    /**
     * Get all the mActions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MActionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MActions");
        return mActionRepository.findAll(pageable)
            .map(mActionMapper::toDto);
    }


    /**
     * Get one mAction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MActionDTO> findOne(Long id) {
        log.debug("Request to get MAction : {}", id);
        return mActionRepository.findById(id)
            .map(mActionMapper::toDto);
    }

    /**
     * Delete the mAction by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MAction : {}", id);
        mActionRepository.deleteById(id);
    }
}
