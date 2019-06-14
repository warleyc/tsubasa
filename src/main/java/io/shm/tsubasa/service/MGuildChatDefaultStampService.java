package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGuildChatDefaultStamp;
import io.shm.tsubasa.repository.MGuildChatDefaultStampRepository;
import io.shm.tsubasa.service.dto.MGuildChatDefaultStampDTO;
import io.shm.tsubasa.service.mapper.MGuildChatDefaultStampMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGuildChatDefaultStamp}.
 */
@Service
@Transactional
public class MGuildChatDefaultStampService {

    private final Logger log = LoggerFactory.getLogger(MGuildChatDefaultStampService.class);

    private final MGuildChatDefaultStampRepository mGuildChatDefaultStampRepository;

    private final MGuildChatDefaultStampMapper mGuildChatDefaultStampMapper;

    public MGuildChatDefaultStampService(MGuildChatDefaultStampRepository mGuildChatDefaultStampRepository, MGuildChatDefaultStampMapper mGuildChatDefaultStampMapper) {
        this.mGuildChatDefaultStampRepository = mGuildChatDefaultStampRepository;
        this.mGuildChatDefaultStampMapper = mGuildChatDefaultStampMapper;
    }

    /**
     * Save a mGuildChatDefaultStamp.
     *
     * @param mGuildChatDefaultStampDTO the entity to save.
     * @return the persisted entity.
     */
    public MGuildChatDefaultStampDTO save(MGuildChatDefaultStampDTO mGuildChatDefaultStampDTO) {
        log.debug("Request to save MGuildChatDefaultStamp : {}", mGuildChatDefaultStampDTO);
        MGuildChatDefaultStamp mGuildChatDefaultStamp = mGuildChatDefaultStampMapper.toEntity(mGuildChatDefaultStampDTO);
        mGuildChatDefaultStamp = mGuildChatDefaultStampRepository.save(mGuildChatDefaultStamp);
        return mGuildChatDefaultStampMapper.toDto(mGuildChatDefaultStamp);
    }

    /**
     * Get all the mGuildChatDefaultStamps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuildChatDefaultStampDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGuildChatDefaultStamps");
        return mGuildChatDefaultStampRepository.findAll(pageable)
            .map(mGuildChatDefaultStampMapper::toDto);
    }


    /**
     * Get one mGuildChatDefaultStamp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGuildChatDefaultStampDTO> findOne(Long id) {
        log.debug("Request to get MGuildChatDefaultStamp : {}", id);
        return mGuildChatDefaultStampRepository.findById(id)
            .map(mGuildChatDefaultStampMapper::toDto);
    }

    /**
     * Delete the mGuildChatDefaultStamp by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGuildChatDefaultStamp : {}", id);
        mGuildChatDefaultStampRepository.deleteById(id);
    }
}
