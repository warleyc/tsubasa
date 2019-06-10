package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDictionaryEs;
import io.shm.tsubasa.repository.MDictionaryEsRepository;
import io.shm.tsubasa.service.dto.MDictionaryEsDTO;
import io.shm.tsubasa.service.mapper.MDictionaryEsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDictionaryEs}.
 */
@Service
@Transactional
public class MDictionaryEsService {

    private final Logger log = LoggerFactory.getLogger(MDictionaryEsService.class);

    private final MDictionaryEsRepository mDictionaryEsRepository;

    private final MDictionaryEsMapper mDictionaryEsMapper;

    public MDictionaryEsService(MDictionaryEsRepository mDictionaryEsRepository, MDictionaryEsMapper mDictionaryEsMapper) {
        this.mDictionaryEsRepository = mDictionaryEsRepository;
        this.mDictionaryEsMapper = mDictionaryEsMapper;
    }

    /**
     * Save a mDictionaryEs.
     *
     * @param mDictionaryEsDTO the entity to save.
     * @return the persisted entity.
     */
    public MDictionaryEsDTO save(MDictionaryEsDTO mDictionaryEsDTO) {
        log.debug("Request to save MDictionaryEs : {}", mDictionaryEsDTO);
        MDictionaryEs mDictionaryEs = mDictionaryEsMapper.toEntity(mDictionaryEsDTO);
        mDictionaryEs = mDictionaryEsRepository.save(mDictionaryEs);
        return mDictionaryEsMapper.toDto(mDictionaryEs);
    }

    /**
     * Get all the mDictionaryEs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDictionaryEsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDictionaryEs");
        return mDictionaryEsRepository.findAll(pageable)
            .map(mDictionaryEsMapper::toDto);
    }


    /**
     * Get one mDictionaryEs by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDictionaryEsDTO> findOne(Long id) {
        log.debug("Request to get MDictionaryEs : {}", id);
        return mDictionaryEsRepository.findById(id)
            .map(mDictionaryEsMapper::toDto);
    }

    /**
     * Delete the mDictionaryEs by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDictionaryEs : {}", id);
        mDictionaryEsRepository.deleteById(id);
    }
}
