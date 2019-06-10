package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MChatSystemMessage;
import io.shm.tsubasa.repository.MChatSystemMessageRepository;
import io.shm.tsubasa.service.dto.MChatSystemMessageDTO;
import io.shm.tsubasa.service.mapper.MChatSystemMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MChatSystemMessage}.
 */
@Service
@Transactional
public class MChatSystemMessageService {

    private final Logger log = LoggerFactory.getLogger(MChatSystemMessageService.class);

    private final MChatSystemMessageRepository mChatSystemMessageRepository;

    private final MChatSystemMessageMapper mChatSystemMessageMapper;

    public MChatSystemMessageService(MChatSystemMessageRepository mChatSystemMessageRepository, MChatSystemMessageMapper mChatSystemMessageMapper) {
        this.mChatSystemMessageRepository = mChatSystemMessageRepository;
        this.mChatSystemMessageMapper = mChatSystemMessageMapper;
    }

    /**
     * Save a mChatSystemMessage.
     *
     * @param mChatSystemMessageDTO the entity to save.
     * @return the persisted entity.
     */
    public MChatSystemMessageDTO save(MChatSystemMessageDTO mChatSystemMessageDTO) {
        log.debug("Request to save MChatSystemMessage : {}", mChatSystemMessageDTO);
        MChatSystemMessage mChatSystemMessage = mChatSystemMessageMapper.toEntity(mChatSystemMessageDTO);
        mChatSystemMessage = mChatSystemMessageRepository.save(mChatSystemMessage);
        return mChatSystemMessageMapper.toDto(mChatSystemMessage);
    }

    /**
     * Get all the mChatSystemMessages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MChatSystemMessageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MChatSystemMessages");
        return mChatSystemMessageRepository.findAll(pageable)
            .map(mChatSystemMessageMapper::toDto);
    }


    /**
     * Get one mChatSystemMessage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MChatSystemMessageDTO> findOne(Long id) {
        log.debug("Request to get MChatSystemMessage : {}", id);
        return mChatSystemMessageRepository.findById(id)
            .map(mChatSystemMessageMapper::toDto);
    }

    /**
     * Delete the mChatSystemMessage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MChatSystemMessage : {}", id);
        mChatSystemMessageRepository.deleteById(id);
    }
}
