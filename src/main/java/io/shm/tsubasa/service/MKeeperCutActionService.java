package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MKeeperCutAction;
import io.shm.tsubasa.repository.MKeeperCutActionRepository;
import io.shm.tsubasa.service.dto.MKeeperCutActionDTO;
import io.shm.tsubasa.service.mapper.MKeeperCutActionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MKeeperCutAction}.
 */
@Service
@Transactional
public class MKeeperCutActionService {

    private final Logger log = LoggerFactory.getLogger(MKeeperCutActionService.class);

    private final MKeeperCutActionRepository mKeeperCutActionRepository;

    private final MKeeperCutActionMapper mKeeperCutActionMapper;

    public MKeeperCutActionService(MKeeperCutActionRepository mKeeperCutActionRepository, MKeeperCutActionMapper mKeeperCutActionMapper) {
        this.mKeeperCutActionRepository = mKeeperCutActionRepository;
        this.mKeeperCutActionMapper = mKeeperCutActionMapper;
    }

    /**
     * Save a mKeeperCutAction.
     *
     * @param mKeeperCutActionDTO the entity to save.
     * @return the persisted entity.
     */
    public MKeeperCutActionDTO save(MKeeperCutActionDTO mKeeperCutActionDTO) {
        log.debug("Request to save MKeeperCutAction : {}", mKeeperCutActionDTO);
        MKeeperCutAction mKeeperCutAction = mKeeperCutActionMapper.toEntity(mKeeperCutActionDTO);
        mKeeperCutAction = mKeeperCutActionRepository.save(mKeeperCutAction);
        return mKeeperCutActionMapper.toDto(mKeeperCutAction);
    }

    /**
     * Get all the mKeeperCutActions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MKeeperCutActionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MKeeperCutActions");
        return mKeeperCutActionRepository.findAll(pageable)
            .map(mKeeperCutActionMapper::toDto);
    }


    /**
     * Get one mKeeperCutAction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MKeeperCutActionDTO> findOne(Long id) {
        log.debug("Request to get MKeeperCutAction : {}", id);
        return mKeeperCutActionRepository.findById(id)
            .map(mKeeperCutActionMapper::toDto);
    }

    /**
     * Delete the mKeeperCutAction by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MKeeperCutAction : {}", id);
        mKeeperCutActionRepository.deleteById(id);
    }
}
