package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGuildNegativeWord;
import io.shm.tsubasa.repository.MGuildNegativeWordRepository;
import io.shm.tsubasa.service.dto.MGuildNegativeWordDTO;
import io.shm.tsubasa.service.mapper.MGuildNegativeWordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGuildNegativeWord}.
 */
@Service
@Transactional
public class MGuildNegativeWordService {

    private final Logger log = LoggerFactory.getLogger(MGuildNegativeWordService.class);

    private final MGuildNegativeWordRepository mGuildNegativeWordRepository;

    private final MGuildNegativeWordMapper mGuildNegativeWordMapper;

    public MGuildNegativeWordService(MGuildNegativeWordRepository mGuildNegativeWordRepository, MGuildNegativeWordMapper mGuildNegativeWordMapper) {
        this.mGuildNegativeWordRepository = mGuildNegativeWordRepository;
        this.mGuildNegativeWordMapper = mGuildNegativeWordMapper;
    }

    /**
     * Save a mGuildNegativeWord.
     *
     * @param mGuildNegativeWordDTO the entity to save.
     * @return the persisted entity.
     */
    public MGuildNegativeWordDTO save(MGuildNegativeWordDTO mGuildNegativeWordDTO) {
        log.debug("Request to save MGuildNegativeWord : {}", mGuildNegativeWordDTO);
        MGuildNegativeWord mGuildNegativeWord = mGuildNegativeWordMapper.toEntity(mGuildNegativeWordDTO);
        mGuildNegativeWord = mGuildNegativeWordRepository.save(mGuildNegativeWord);
        return mGuildNegativeWordMapper.toDto(mGuildNegativeWord);
    }

    /**
     * Get all the mGuildNegativeWords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuildNegativeWordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGuildNegativeWords");
        return mGuildNegativeWordRepository.findAll(pageable)
            .map(mGuildNegativeWordMapper::toDto);
    }


    /**
     * Get one mGuildNegativeWord by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGuildNegativeWordDTO> findOne(Long id) {
        log.debug("Request to get MGuildNegativeWord : {}", id);
        return mGuildNegativeWordRepository.findById(id)
            .map(mGuildNegativeWordMapper::toDto);
    }

    /**
     * Delete the mGuildNegativeWord by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGuildNegativeWord : {}", id);
        mGuildNegativeWordRepository.deleteById(id);
    }
}
