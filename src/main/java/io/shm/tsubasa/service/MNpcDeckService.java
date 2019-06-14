package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MNpcDeck;
import io.shm.tsubasa.repository.MNpcDeckRepository;
import io.shm.tsubasa.service.dto.MNpcDeckDTO;
import io.shm.tsubasa.service.mapper.MNpcDeckMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MNpcDeck}.
 */
@Service
@Transactional
public class MNpcDeckService {

    private final Logger log = LoggerFactory.getLogger(MNpcDeckService.class);

    private final MNpcDeckRepository mNpcDeckRepository;

    private final MNpcDeckMapper mNpcDeckMapper;

    public MNpcDeckService(MNpcDeckRepository mNpcDeckRepository, MNpcDeckMapper mNpcDeckMapper) {
        this.mNpcDeckRepository = mNpcDeckRepository;
        this.mNpcDeckMapper = mNpcDeckMapper;
    }

    /**
     * Save a mNpcDeck.
     *
     * @param mNpcDeckDTO the entity to save.
     * @return the persisted entity.
     */
    public MNpcDeckDTO save(MNpcDeckDTO mNpcDeckDTO) {
        log.debug("Request to save MNpcDeck : {}", mNpcDeckDTO);
        MNpcDeck mNpcDeck = mNpcDeckMapper.toEntity(mNpcDeckDTO);
        mNpcDeck = mNpcDeckRepository.save(mNpcDeck);
        return mNpcDeckMapper.toDto(mNpcDeck);
    }

    /**
     * Get all the mNpcDecks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MNpcDeckDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MNpcDecks");
        return mNpcDeckRepository.findAll(pageable)
            .map(mNpcDeckMapper::toDto);
    }


    /**
     * Get one mNpcDeck by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MNpcDeckDTO> findOne(Long id) {
        log.debug("Request to get MNpcDeck : {}", id);
        return mNpcDeckRepository.findById(id)
            .map(mNpcDeckMapper::toDto);
    }

    /**
     * Delete the mNpcDeck by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MNpcDeck : {}", id);
        mNpcDeckRepository.deleteById(id);
    }
}
