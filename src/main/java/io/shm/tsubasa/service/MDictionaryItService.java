package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDictionaryIt;
import io.shm.tsubasa.repository.MDictionaryItRepository;
import io.shm.tsubasa.service.dto.MDictionaryItDTO;
import io.shm.tsubasa.service.mapper.MDictionaryItMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDictionaryIt}.
 */
@Service
@Transactional
public class MDictionaryItService {

    private final Logger log = LoggerFactory.getLogger(MDictionaryItService.class);

    private final MDictionaryItRepository mDictionaryItRepository;

    private final MDictionaryItMapper mDictionaryItMapper;

    public MDictionaryItService(MDictionaryItRepository mDictionaryItRepository, MDictionaryItMapper mDictionaryItMapper) {
        this.mDictionaryItRepository = mDictionaryItRepository;
        this.mDictionaryItMapper = mDictionaryItMapper;
    }

    /**
     * Save a mDictionaryIt.
     *
     * @param mDictionaryItDTO the entity to save.
     * @return the persisted entity.
     */
    public MDictionaryItDTO save(MDictionaryItDTO mDictionaryItDTO) {
        log.debug("Request to save MDictionaryIt : {}", mDictionaryItDTO);
        MDictionaryIt mDictionaryIt = mDictionaryItMapper.toEntity(mDictionaryItDTO);
        mDictionaryIt = mDictionaryItRepository.save(mDictionaryIt);
        return mDictionaryItMapper.toDto(mDictionaryIt);
    }

    /**
     * Get all the mDictionaryIts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDictionaryItDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDictionaryIts");
        return mDictionaryItRepository.findAll(pageable)
            .map(mDictionaryItMapper::toDto);
    }


    /**
     * Get one mDictionaryIt by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDictionaryItDTO> findOne(Long id) {
        log.debug("Request to get MDictionaryIt : {}", id);
        return mDictionaryItRepository.findById(id)
            .map(mDictionaryItMapper::toDto);
    }

    /**
     * Delete the mDictionaryIt by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDictionaryIt : {}", id);
        mDictionaryItRepository.deleteById(id);
    }
}
