package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MSoundBankEvent;
import io.shm.tsubasa.repository.MSoundBankEventRepository;
import io.shm.tsubasa.service.dto.MSoundBankEventDTO;
import io.shm.tsubasa.service.mapper.MSoundBankEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MSoundBankEvent}.
 */
@Service
@Transactional
public class MSoundBankEventService {

    private final Logger log = LoggerFactory.getLogger(MSoundBankEventService.class);

    private final MSoundBankEventRepository mSoundBankEventRepository;

    private final MSoundBankEventMapper mSoundBankEventMapper;

    public MSoundBankEventService(MSoundBankEventRepository mSoundBankEventRepository, MSoundBankEventMapper mSoundBankEventMapper) {
        this.mSoundBankEventRepository = mSoundBankEventRepository;
        this.mSoundBankEventMapper = mSoundBankEventMapper;
    }

    /**
     * Save a mSoundBankEvent.
     *
     * @param mSoundBankEventDTO the entity to save.
     * @return the persisted entity.
     */
    public MSoundBankEventDTO save(MSoundBankEventDTO mSoundBankEventDTO) {
        log.debug("Request to save MSoundBankEvent : {}", mSoundBankEventDTO);
        MSoundBankEvent mSoundBankEvent = mSoundBankEventMapper.toEntity(mSoundBankEventDTO);
        mSoundBankEvent = mSoundBankEventRepository.save(mSoundBankEvent);
        return mSoundBankEventMapper.toDto(mSoundBankEvent);
    }

    /**
     * Get all the mSoundBankEvents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MSoundBankEventDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MSoundBankEvents");
        return mSoundBankEventRepository.findAll(pageable)
            .map(mSoundBankEventMapper::toDto);
    }


    /**
     * Get one mSoundBankEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MSoundBankEventDTO> findOne(Long id) {
        log.debug("Request to get MSoundBankEvent : {}", id);
        return mSoundBankEventRepository.findById(id)
            .map(mSoundBankEventMapper::toDto);
    }

    /**
     * Delete the mSoundBankEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MSoundBankEvent : {}", id);
        mSoundBankEventRepository.deleteById(id);
    }
}
