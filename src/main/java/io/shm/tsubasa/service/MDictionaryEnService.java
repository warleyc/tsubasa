package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDictionaryEn;
import io.shm.tsubasa.repository.MDictionaryEnRepository;
import io.shm.tsubasa.service.dto.MDictionaryEnDTO;
import io.shm.tsubasa.service.mapper.MDictionaryEnMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDictionaryEn}.
 */
@Service
@Transactional
public class MDictionaryEnService {

    private final Logger log = LoggerFactory.getLogger(MDictionaryEnService.class);

    private final MDictionaryEnRepository mDictionaryEnRepository;

    private final MDictionaryEnMapper mDictionaryEnMapper;

    public MDictionaryEnService(MDictionaryEnRepository mDictionaryEnRepository, MDictionaryEnMapper mDictionaryEnMapper) {
        this.mDictionaryEnRepository = mDictionaryEnRepository;
        this.mDictionaryEnMapper = mDictionaryEnMapper;
    }

    /**
     * Save a mDictionaryEn.
     *
     * @param mDictionaryEnDTO the entity to save.
     * @return the persisted entity.
     */
    public MDictionaryEnDTO save(MDictionaryEnDTO mDictionaryEnDTO) {
        log.debug("Request to save MDictionaryEn : {}", mDictionaryEnDTO);
        MDictionaryEn mDictionaryEn = mDictionaryEnMapper.toEntity(mDictionaryEnDTO);
        mDictionaryEn = mDictionaryEnRepository.save(mDictionaryEn);
        return mDictionaryEnMapper.toDto(mDictionaryEn);
    }

    /**
     * Get all the mDictionaryEns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDictionaryEnDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDictionaryEns");
        return mDictionaryEnRepository.findAll(pageable)
            .map(mDictionaryEnMapper::toDto);
    }


    /**
     * Get one mDictionaryEn by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDictionaryEnDTO> findOne(Long id) {
        log.debug("Request to get MDictionaryEn : {}", id);
        return mDictionaryEnRepository.findById(id)
            .map(mDictionaryEnMapper::toDto);
    }

    /**
     * Delete the mDictionaryEn by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDictionaryEn : {}", id);
        mDictionaryEnRepository.deleteById(id);
    }
}
