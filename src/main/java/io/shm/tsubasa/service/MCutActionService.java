package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCutAction;
import io.shm.tsubasa.repository.MCutActionRepository;
import io.shm.tsubasa.service.dto.MCutActionDTO;
import io.shm.tsubasa.service.mapper.MCutActionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCutAction}.
 */
@Service
@Transactional
public class MCutActionService {

    private final Logger log = LoggerFactory.getLogger(MCutActionService.class);

    private final MCutActionRepository mCutActionRepository;

    private final MCutActionMapper mCutActionMapper;

    public MCutActionService(MCutActionRepository mCutActionRepository, MCutActionMapper mCutActionMapper) {
        this.mCutActionRepository = mCutActionRepository;
        this.mCutActionMapper = mCutActionMapper;
    }

    /**
     * Save a mCutAction.
     *
     * @param mCutActionDTO the entity to save.
     * @return the persisted entity.
     */
    public MCutActionDTO save(MCutActionDTO mCutActionDTO) {
        log.debug("Request to save MCutAction : {}", mCutActionDTO);
        MCutAction mCutAction = mCutActionMapper.toEntity(mCutActionDTO);
        mCutAction = mCutActionRepository.save(mCutAction);
        return mCutActionMapper.toDto(mCutAction);
    }

    /**
     * Get all the mCutActions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCutActionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCutActions");
        return mCutActionRepository.findAll(pageable)
            .map(mCutActionMapper::toDto);
    }


    /**
     * Get one mCutAction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCutActionDTO> findOne(Long id) {
        log.debug("Request to get MCutAction : {}", id);
        return mCutActionRepository.findById(id)
            .map(mCutActionMapper::toDto);
    }

    /**
     * Delete the mCutAction by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCutAction : {}", id);
        mCutActionRepository.deleteById(id);
    }
}
