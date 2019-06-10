package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDictionaryAr;
import io.shm.tsubasa.repository.MDictionaryArRepository;
import io.shm.tsubasa.service.dto.MDictionaryArDTO;
import io.shm.tsubasa.service.mapper.MDictionaryArMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDictionaryAr}.
 */
@Service
@Transactional
public class MDictionaryArService {

    private final Logger log = LoggerFactory.getLogger(MDictionaryArService.class);

    private final MDictionaryArRepository mDictionaryArRepository;

    private final MDictionaryArMapper mDictionaryArMapper;

    public MDictionaryArService(MDictionaryArRepository mDictionaryArRepository, MDictionaryArMapper mDictionaryArMapper) {
        this.mDictionaryArRepository = mDictionaryArRepository;
        this.mDictionaryArMapper = mDictionaryArMapper;
    }

    /**
     * Save a mDictionaryAr.
     *
     * @param mDictionaryArDTO the entity to save.
     * @return the persisted entity.
     */
    public MDictionaryArDTO save(MDictionaryArDTO mDictionaryArDTO) {
        log.debug("Request to save MDictionaryAr : {}", mDictionaryArDTO);
        MDictionaryAr mDictionaryAr = mDictionaryArMapper.toEntity(mDictionaryArDTO);
        mDictionaryAr = mDictionaryArRepository.save(mDictionaryAr);
        return mDictionaryArMapper.toDto(mDictionaryAr);
    }

    /**
     * Get all the mDictionaryArs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDictionaryArDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDictionaryArs");
        return mDictionaryArRepository.findAll(pageable)
            .map(mDictionaryArMapper::toDto);
    }


    /**
     * Get one mDictionaryAr by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDictionaryArDTO> findOne(Long id) {
        log.debug("Request to get MDictionaryAr : {}", id);
        return mDictionaryArRepository.findById(id)
            .map(mDictionaryArMapper::toDto);
    }

    /**
     * Delete the mDictionaryAr by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDictionaryAr : {}", id);
        mDictionaryArRepository.deleteById(id);
    }
}
