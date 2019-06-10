package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDictionaryPt;
import io.shm.tsubasa.repository.MDictionaryPtRepository;
import io.shm.tsubasa.service.dto.MDictionaryPtDTO;
import io.shm.tsubasa.service.mapper.MDictionaryPtMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDictionaryPt}.
 */
@Service
@Transactional
public class MDictionaryPtService {

    private final Logger log = LoggerFactory.getLogger(MDictionaryPtService.class);

    private final MDictionaryPtRepository mDictionaryPtRepository;

    private final MDictionaryPtMapper mDictionaryPtMapper;

    public MDictionaryPtService(MDictionaryPtRepository mDictionaryPtRepository, MDictionaryPtMapper mDictionaryPtMapper) {
        this.mDictionaryPtRepository = mDictionaryPtRepository;
        this.mDictionaryPtMapper = mDictionaryPtMapper;
    }

    /**
     * Save a mDictionaryPt.
     *
     * @param mDictionaryPtDTO the entity to save.
     * @return the persisted entity.
     */
    public MDictionaryPtDTO save(MDictionaryPtDTO mDictionaryPtDTO) {
        log.debug("Request to save MDictionaryPt : {}", mDictionaryPtDTO);
        MDictionaryPt mDictionaryPt = mDictionaryPtMapper.toEntity(mDictionaryPtDTO);
        mDictionaryPt = mDictionaryPtRepository.save(mDictionaryPt);
        return mDictionaryPtMapper.toDto(mDictionaryPt);
    }

    /**
     * Get all the mDictionaryPts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDictionaryPtDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDictionaryPts");
        return mDictionaryPtRepository.findAll(pageable)
            .map(mDictionaryPtMapper::toDto);
    }


    /**
     * Get one mDictionaryPt by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDictionaryPtDTO> findOne(Long id) {
        log.debug("Request to get MDictionaryPt : {}", id);
        return mDictionaryPtRepository.findById(id)
            .map(mDictionaryPtMapper::toDto);
    }

    /**
     * Delete the mDictionaryPt by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDictionaryPt : {}", id);
        mDictionaryPtRepository.deleteById(id);
    }
}
