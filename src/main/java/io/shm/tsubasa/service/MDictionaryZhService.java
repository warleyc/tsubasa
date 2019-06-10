package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDictionaryZh;
import io.shm.tsubasa.repository.MDictionaryZhRepository;
import io.shm.tsubasa.service.dto.MDictionaryZhDTO;
import io.shm.tsubasa.service.mapper.MDictionaryZhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDictionaryZh}.
 */
@Service
@Transactional
public class MDictionaryZhService {

    private final Logger log = LoggerFactory.getLogger(MDictionaryZhService.class);

    private final MDictionaryZhRepository mDictionaryZhRepository;

    private final MDictionaryZhMapper mDictionaryZhMapper;

    public MDictionaryZhService(MDictionaryZhRepository mDictionaryZhRepository, MDictionaryZhMapper mDictionaryZhMapper) {
        this.mDictionaryZhRepository = mDictionaryZhRepository;
        this.mDictionaryZhMapper = mDictionaryZhMapper;
    }

    /**
     * Save a mDictionaryZh.
     *
     * @param mDictionaryZhDTO the entity to save.
     * @return the persisted entity.
     */
    public MDictionaryZhDTO save(MDictionaryZhDTO mDictionaryZhDTO) {
        log.debug("Request to save MDictionaryZh : {}", mDictionaryZhDTO);
        MDictionaryZh mDictionaryZh = mDictionaryZhMapper.toEntity(mDictionaryZhDTO);
        mDictionaryZh = mDictionaryZhRepository.save(mDictionaryZh);
        return mDictionaryZhMapper.toDto(mDictionaryZh);
    }

    /**
     * Get all the mDictionaryZhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDictionaryZhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDictionaryZhs");
        return mDictionaryZhRepository.findAll(pageable)
            .map(mDictionaryZhMapper::toDto);
    }


    /**
     * Get one mDictionaryZh by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDictionaryZhDTO> findOne(Long id) {
        log.debug("Request to get MDictionaryZh : {}", id);
        return mDictionaryZhRepository.findById(id)
            .map(mDictionaryZhMapper::toDto);
    }

    /**
     * Delete the mDictionaryZh by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDictionaryZh : {}", id);
        mDictionaryZhRepository.deleteById(id);
    }
}
