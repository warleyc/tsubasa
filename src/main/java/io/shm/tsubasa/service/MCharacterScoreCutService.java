package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCharacterScoreCut;
import io.shm.tsubasa.repository.MCharacterScoreCutRepository;
import io.shm.tsubasa.service.dto.MCharacterScoreCutDTO;
import io.shm.tsubasa.service.mapper.MCharacterScoreCutMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCharacterScoreCut}.
 */
@Service
@Transactional
public class MCharacterScoreCutService {

    private final Logger log = LoggerFactory.getLogger(MCharacterScoreCutService.class);

    private final MCharacterScoreCutRepository mCharacterScoreCutRepository;

    private final MCharacterScoreCutMapper mCharacterScoreCutMapper;

    public MCharacterScoreCutService(MCharacterScoreCutRepository mCharacterScoreCutRepository, MCharacterScoreCutMapper mCharacterScoreCutMapper) {
        this.mCharacterScoreCutRepository = mCharacterScoreCutRepository;
        this.mCharacterScoreCutMapper = mCharacterScoreCutMapper;
    }

    /**
     * Save a mCharacterScoreCut.
     *
     * @param mCharacterScoreCutDTO the entity to save.
     * @return the persisted entity.
     */
    public MCharacterScoreCutDTO save(MCharacterScoreCutDTO mCharacterScoreCutDTO) {
        log.debug("Request to save MCharacterScoreCut : {}", mCharacterScoreCutDTO);
        MCharacterScoreCut mCharacterScoreCut = mCharacterScoreCutMapper.toEntity(mCharacterScoreCutDTO);
        mCharacterScoreCut = mCharacterScoreCutRepository.save(mCharacterScoreCut);
        return mCharacterScoreCutMapper.toDto(mCharacterScoreCut);
    }

    /**
     * Get all the mCharacterScoreCuts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCharacterScoreCutDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCharacterScoreCuts");
        return mCharacterScoreCutRepository.findAll(pageable)
            .map(mCharacterScoreCutMapper::toDto);
    }


    /**
     * Get one mCharacterScoreCut by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCharacterScoreCutDTO> findOne(Long id) {
        log.debug("Request to get MCharacterScoreCut : {}", id);
        return mCharacterScoreCutRepository.findById(id)
            .map(mCharacterScoreCutMapper::toDto);
    }

    /**
     * Delete the mCharacterScoreCut by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCharacterScoreCut : {}", id);
        mCharacterScoreCutRepository.deleteById(id);
    }
}
