package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCut;
import io.shm.tsubasa.repository.MCutRepository;
import io.shm.tsubasa.service.dto.MCutDTO;
import io.shm.tsubasa.service.mapper.MCutMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCut}.
 */
@Service
@Transactional
public class MCutService {

    private final Logger log = LoggerFactory.getLogger(MCutService.class);

    private final MCutRepository mCutRepository;

    private final MCutMapper mCutMapper;

    public MCutService(MCutRepository mCutRepository, MCutMapper mCutMapper) {
        this.mCutRepository = mCutRepository;
        this.mCutMapper = mCutMapper;
    }

    /**
     * Save a mCut.
     *
     * @param mCutDTO the entity to save.
     * @return the persisted entity.
     */
    public MCutDTO save(MCutDTO mCutDTO) {
        log.debug("Request to save MCut : {}", mCutDTO);
        MCut mCut = mCutMapper.toEntity(mCutDTO);
        mCut = mCutRepository.save(mCut);
        return mCutMapper.toDto(mCut);
    }

    /**
     * Get all the mCuts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCutDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCuts");
        return mCutRepository.findAll(pageable)
            .map(mCutMapper::toDto);
    }


    /**
     * Get one mCut by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCutDTO> findOne(Long id) {
        log.debug("Request to get MCut : {}", id);
        return mCutRepository.findById(id)
            .map(mCutMapper::toDto);
    }

    /**
     * Delete the mCut by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCut : {}", id);
        mCutRepository.deleteById(id);
    }
}
