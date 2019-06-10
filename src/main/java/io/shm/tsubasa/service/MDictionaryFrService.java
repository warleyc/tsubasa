package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDictionaryFr;
import io.shm.tsubasa.repository.MDictionaryFrRepository;
import io.shm.tsubasa.service.dto.MDictionaryFrDTO;
import io.shm.tsubasa.service.mapper.MDictionaryFrMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDictionaryFr}.
 */
@Service
@Transactional
public class MDictionaryFrService {

    private final Logger log = LoggerFactory.getLogger(MDictionaryFrService.class);

    private final MDictionaryFrRepository mDictionaryFrRepository;

    private final MDictionaryFrMapper mDictionaryFrMapper;

    public MDictionaryFrService(MDictionaryFrRepository mDictionaryFrRepository, MDictionaryFrMapper mDictionaryFrMapper) {
        this.mDictionaryFrRepository = mDictionaryFrRepository;
        this.mDictionaryFrMapper = mDictionaryFrMapper;
    }

    /**
     * Save a mDictionaryFr.
     *
     * @param mDictionaryFrDTO the entity to save.
     * @return the persisted entity.
     */
    public MDictionaryFrDTO save(MDictionaryFrDTO mDictionaryFrDTO) {
        log.debug("Request to save MDictionaryFr : {}", mDictionaryFrDTO);
        MDictionaryFr mDictionaryFr = mDictionaryFrMapper.toEntity(mDictionaryFrDTO);
        mDictionaryFr = mDictionaryFrRepository.save(mDictionaryFr);
        return mDictionaryFrMapper.toDto(mDictionaryFr);
    }

    /**
     * Get all the mDictionaryFrs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDictionaryFrDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDictionaryFrs");
        return mDictionaryFrRepository.findAll(pageable)
            .map(mDictionaryFrMapper::toDto);
    }


    /**
     * Get one mDictionaryFr by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDictionaryFrDTO> findOne(Long id) {
        log.debug("Request to get MDictionaryFr : {}", id);
        return mDictionaryFrRepository.findById(id)
            .map(mDictionaryFrMapper::toDto);
    }

    /**
     * Delete the mDictionaryFr by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDictionaryFr : {}", id);
        mDictionaryFrRepository.deleteById(id);
    }
}
