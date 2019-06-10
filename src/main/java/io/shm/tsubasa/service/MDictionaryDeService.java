package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDictionaryDe;
import io.shm.tsubasa.repository.MDictionaryDeRepository;
import io.shm.tsubasa.service.dto.MDictionaryDeDTO;
import io.shm.tsubasa.service.mapper.MDictionaryDeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDictionaryDe}.
 */
@Service
@Transactional
public class MDictionaryDeService {

    private final Logger log = LoggerFactory.getLogger(MDictionaryDeService.class);

    private final MDictionaryDeRepository mDictionaryDeRepository;

    private final MDictionaryDeMapper mDictionaryDeMapper;

    public MDictionaryDeService(MDictionaryDeRepository mDictionaryDeRepository, MDictionaryDeMapper mDictionaryDeMapper) {
        this.mDictionaryDeRepository = mDictionaryDeRepository;
        this.mDictionaryDeMapper = mDictionaryDeMapper;
    }

    /**
     * Save a mDictionaryDe.
     *
     * @param mDictionaryDeDTO the entity to save.
     * @return the persisted entity.
     */
    public MDictionaryDeDTO save(MDictionaryDeDTO mDictionaryDeDTO) {
        log.debug("Request to save MDictionaryDe : {}", mDictionaryDeDTO);
        MDictionaryDe mDictionaryDe = mDictionaryDeMapper.toEntity(mDictionaryDeDTO);
        mDictionaryDe = mDictionaryDeRepository.save(mDictionaryDe);
        return mDictionaryDeMapper.toDto(mDictionaryDe);
    }

    /**
     * Get all the mDictionaryDes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDictionaryDeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDictionaryDes");
        return mDictionaryDeRepository.findAll(pageable)
            .map(mDictionaryDeMapper::toDto);
    }


    /**
     * Get one mDictionaryDe by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDictionaryDeDTO> findOne(Long id) {
        log.debug("Request to get MDictionaryDe : {}", id);
        return mDictionaryDeRepository.findById(id)
            .map(mDictionaryDeMapper::toDto);
    }

    /**
     * Delete the mDictionaryDe by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDictionaryDe : {}", id);
        mDictionaryDeRepository.deleteById(id);
    }
}
