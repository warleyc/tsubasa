package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MInitUserDeck;
import io.shm.tsubasa.repository.MInitUserDeckRepository;
import io.shm.tsubasa.service.dto.MInitUserDeckDTO;
import io.shm.tsubasa.service.mapper.MInitUserDeckMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MInitUserDeck}.
 */
@Service
@Transactional
public class MInitUserDeckService {

    private final Logger log = LoggerFactory.getLogger(MInitUserDeckService.class);

    private final MInitUserDeckRepository mInitUserDeckRepository;

    private final MInitUserDeckMapper mInitUserDeckMapper;

    public MInitUserDeckService(MInitUserDeckRepository mInitUserDeckRepository, MInitUserDeckMapper mInitUserDeckMapper) {
        this.mInitUserDeckRepository = mInitUserDeckRepository;
        this.mInitUserDeckMapper = mInitUserDeckMapper;
    }

    /**
     * Save a mInitUserDeck.
     *
     * @param mInitUserDeckDTO the entity to save.
     * @return the persisted entity.
     */
    public MInitUserDeckDTO save(MInitUserDeckDTO mInitUserDeckDTO) {
        log.debug("Request to save MInitUserDeck : {}", mInitUserDeckDTO);
        MInitUserDeck mInitUserDeck = mInitUserDeckMapper.toEntity(mInitUserDeckDTO);
        mInitUserDeck = mInitUserDeckRepository.save(mInitUserDeck);
        return mInitUserDeckMapper.toDto(mInitUserDeck);
    }

    /**
     * Get all the mInitUserDecks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MInitUserDeckDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MInitUserDecks");
        return mInitUserDeckRepository.findAll(pageable)
            .map(mInitUserDeckMapper::toDto);
    }


    /**
     * Get one mInitUserDeck by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MInitUserDeckDTO> findOne(Long id) {
        log.debug("Request to get MInitUserDeck : {}", id);
        return mInitUserDeckRepository.findById(id)
            .map(mInitUserDeckMapper::toDto);
    }

    /**
     * Delete the mInitUserDeck by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MInitUserDeck : {}", id);
        mInitUserDeckRepository.deleteById(id);
    }
}
