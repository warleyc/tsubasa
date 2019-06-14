package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTutorialMessage;
import io.shm.tsubasa.repository.MTutorialMessageRepository;
import io.shm.tsubasa.service.dto.MTutorialMessageDTO;
import io.shm.tsubasa.service.mapper.MTutorialMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTutorialMessage}.
 */
@Service
@Transactional
public class MTutorialMessageService {

    private final Logger log = LoggerFactory.getLogger(MTutorialMessageService.class);

    private final MTutorialMessageRepository mTutorialMessageRepository;

    private final MTutorialMessageMapper mTutorialMessageMapper;

    public MTutorialMessageService(MTutorialMessageRepository mTutorialMessageRepository, MTutorialMessageMapper mTutorialMessageMapper) {
        this.mTutorialMessageRepository = mTutorialMessageRepository;
        this.mTutorialMessageMapper = mTutorialMessageMapper;
    }

    /**
     * Save a mTutorialMessage.
     *
     * @param mTutorialMessageDTO the entity to save.
     * @return the persisted entity.
     */
    public MTutorialMessageDTO save(MTutorialMessageDTO mTutorialMessageDTO) {
        log.debug("Request to save MTutorialMessage : {}", mTutorialMessageDTO);
        MTutorialMessage mTutorialMessage = mTutorialMessageMapper.toEntity(mTutorialMessageDTO);
        mTutorialMessage = mTutorialMessageRepository.save(mTutorialMessage);
        return mTutorialMessageMapper.toDto(mTutorialMessage);
    }

    /**
     * Get all the mTutorialMessages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTutorialMessageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTutorialMessages");
        return mTutorialMessageRepository.findAll(pageable)
            .map(mTutorialMessageMapper::toDto);
    }


    /**
     * Get one mTutorialMessage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTutorialMessageDTO> findOne(Long id) {
        log.debug("Request to get MTutorialMessage : {}", id);
        return mTutorialMessageRepository.findById(id)
            .map(mTutorialMessageMapper::toDto);
    }

    /**
     * Delete the mTutorialMessage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTutorialMessage : {}", id);
        mTutorialMessageRepository.deleteById(id);
    }
}
