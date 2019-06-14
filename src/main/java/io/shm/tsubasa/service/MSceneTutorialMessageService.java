package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MSceneTutorialMessage;
import io.shm.tsubasa.repository.MSceneTutorialMessageRepository;
import io.shm.tsubasa.service.dto.MSceneTutorialMessageDTO;
import io.shm.tsubasa.service.mapper.MSceneTutorialMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MSceneTutorialMessage}.
 */
@Service
@Transactional
public class MSceneTutorialMessageService {

    private final Logger log = LoggerFactory.getLogger(MSceneTutorialMessageService.class);

    private final MSceneTutorialMessageRepository mSceneTutorialMessageRepository;

    private final MSceneTutorialMessageMapper mSceneTutorialMessageMapper;

    public MSceneTutorialMessageService(MSceneTutorialMessageRepository mSceneTutorialMessageRepository, MSceneTutorialMessageMapper mSceneTutorialMessageMapper) {
        this.mSceneTutorialMessageRepository = mSceneTutorialMessageRepository;
        this.mSceneTutorialMessageMapper = mSceneTutorialMessageMapper;
    }

    /**
     * Save a mSceneTutorialMessage.
     *
     * @param mSceneTutorialMessageDTO the entity to save.
     * @return the persisted entity.
     */
    public MSceneTutorialMessageDTO save(MSceneTutorialMessageDTO mSceneTutorialMessageDTO) {
        log.debug("Request to save MSceneTutorialMessage : {}", mSceneTutorialMessageDTO);
        MSceneTutorialMessage mSceneTutorialMessage = mSceneTutorialMessageMapper.toEntity(mSceneTutorialMessageDTO);
        mSceneTutorialMessage = mSceneTutorialMessageRepository.save(mSceneTutorialMessage);
        return mSceneTutorialMessageMapper.toDto(mSceneTutorialMessage);
    }

    /**
     * Get all the mSceneTutorialMessages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MSceneTutorialMessageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MSceneTutorialMessages");
        return mSceneTutorialMessageRepository.findAll(pageable)
            .map(mSceneTutorialMessageMapper::toDto);
    }


    /**
     * Get one mSceneTutorialMessage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MSceneTutorialMessageDTO> findOne(Long id) {
        log.debug("Request to get MSceneTutorialMessage : {}", id);
        return mSceneTutorialMessageRepository.findById(id)
            .map(mSceneTutorialMessageMapper::toDto);
    }

    /**
     * Delete the mSceneTutorialMessage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MSceneTutorialMessage : {}", id);
        mSceneTutorialMessageRepository.deleteById(id);
    }
}
