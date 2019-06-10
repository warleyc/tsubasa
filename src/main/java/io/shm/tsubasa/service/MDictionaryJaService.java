package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDictionaryJa;
import io.shm.tsubasa.repository.MDictionaryJaRepository;
import io.shm.tsubasa.service.dto.MDictionaryJaDTO;
import io.shm.tsubasa.service.mapper.MDictionaryJaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDictionaryJa}.
 */
@Service
@Transactional
public class MDictionaryJaService {

    private final Logger log = LoggerFactory.getLogger(MDictionaryJaService.class);

    private final MDictionaryJaRepository mDictionaryJaRepository;

    private final MDictionaryJaMapper mDictionaryJaMapper;

    public MDictionaryJaService(MDictionaryJaRepository mDictionaryJaRepository, MDictionaryJaMapper mDictionaryJaMapper) {
        this.mDictionaryJaRepository = mDictionaryJaRepository;
        this.mDictionaryJaMapper = mDictionaryJaMapper;
    }

    /**
     * Save a mDictionaryJa.
     *
     * @param mDictionaryJaDTO the entity to save.
     * @return the persisted entity.
     */
    public MDictionaryJaDTO save(MDictionaryJaDTO mDictionaryJaDTO) {
        log.debug("Request to save MDictionaryJa : {}", mDictionaryJaDTO);
        MDictionaryJa mDictionaryJa = mDictionaryJaMapper.toEntity(mDictionaryJaDTO);
        mDictionaryJa = mDictionaryJaRepository.save(mDictionaryJa);
        return mDictionaryJaMapper.toDto(mDictionaryJa);
    }

    /**
     * Get all the mDictionaryJas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDictionaryJaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDictionaryJas");
        return mDictionaryJaRepository.findAll(pageable)
            .map(mDictionaryJaMapper::toDto);
    }


    /**
     * Get one mDictionaryJa by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDictionaryJaDTO> findOne(Long id) {
        log.debug("Request to get MDictionaryJa : {}", id);
        return mDictionaryJaRepository.findById(id)
            .map(mDictionaryJaMapper::toDto);
    }

    /**
     * Delete the mDictionaryJa by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDictionaryJa : {}", id);
        mDictionaryJaRepository.deleteById(id);
    }
}
