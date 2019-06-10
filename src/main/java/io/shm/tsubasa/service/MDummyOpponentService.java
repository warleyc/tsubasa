package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDummyOpponent;
import io.shm.tsubasa.repository.MDummyOpponentRepository;
import io.shm.tsubasa.service.dto.MDummyOpponentDTO;
import io.shm.tsubasa.service.mapper.MDummyOpponentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDummyOpponent}.
 */
@Service
@Transactional
public class MDummyOpponentService {

    private final Logger log = LoggerFactory.getLogger(MDummyOpponentService.class);

    private final MDummyOpponentRepository mDummyOpponentRepository;

    private final MDummyOpponentMapper mDummyOpponentMapper;

    public MDummyOpponentService(MDummyOpponentRepository mDummyOpponentRepository, MDummyOpponentMapper mDummyOpponentMapper) {
        this.mDummyOpponentRepository = mDummyOpponentRepository;
        this.mDummyOpponentMapper = mDummyOpponentMapper;
    }

    /**
     * Save a mDummyOpponent.
     *
     * @param mDummyOpponentDTO the entity to save.
     * @return the persisted entity.
     */
    public MDummyOpponentDTO save(MDummyOpponentDTO mDummyOpponentDTO) {
        log.debug("Request to save MDummyOpponent : {}", mDummyOpponentDTO);
        MDummyOpponent mDummyOpponent = mDummyOpponentMapper.toEntity(mDummyOpponentDTO);
        mDummyOpponent = mDummyOpponentRepository.save(mDummyOpponent);
        return mDummyOpponentMapper.toDto(mDummyOpponent);
    }

    /**
     * Get all the mDummyOpponents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDummyOpponentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDummyOpponents");
        return mDummyOpponentRepository.findAll(pageable)
            .map(mDummyOpponentMapper::toDto);
    }


    /**
     * Get one mDummyOpponent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDummyOpponentDTO> findOne(Long id) {
        log.debug("Request to get MDummyOpponent : {}", id);
        return mDummyOpponentRepository.findById(id)
            .map(mDummyOpponentMapper::toDto);
    }

    /**
     * Delete the mDummyOpponent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDummyOpponent : {}", id);
        mDummyOpponentRepository.deleteById(id);
    }
}
