package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MLoginBonusSerif;
import io.shm.tsubasa.repository.MLoginBonusSerifRepository;
import io.shm.tsubasa.service.dto.MLoginBonusSerifDTO;
import io.shm.tsubasa.service.mapper.MLoginBonusSerifMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MLoginBonusSerif}.
 */
@Service
@Transactional
public class MLoginBonusSerifService {

    private final Logger log = LoggerFactory.getLogger(MLoginBonusSerifService.class);

    private final MLoginBonusSerifRepository mLoginBonusSerifRepository;

    private final MLoginBonusSerifMapper mLoginBonusSerifMapper;

    public MLoginBonusSerifService(MLoginBonusSerifRepository mLoginBonusSerifRepository, MLoginBonusSerifMapper mLoginBonusSerifMapper) {
        this.mLoginBonusSerifRepository = mLoginBonusSerifRepository;
        this.mLoginBonusSerifMapper = mLoginBonusSerifMapper;
    }

    /**
     * Save a mLoginBonusSerif.
     *
     * @param mLoginBonusSerifDTO the entity to save.
     * @return the persisted entity.
     */
    public MLoginBonusSerifDTO save(MLoginBonusSerifDTO mLoginBonusSerifDTO) {
        log.debug("Request to save MLoginBonusSerif : {}", mLoginBonusSerifDTO);
        MLoginBonusSerif mLoginBonusSerif = mLoginBonusSerifMapper.toEntity(mLoginBonusSerifDTO);
        mLoginBonusSerif = mLoginBonusSerifRepository.save(mLoginBonusSerif);
        return mLoginBonusSerifMapper.toDto(mLoginBonusSerif);
    }

    /**
     * Get all the mLoginBonusSerifs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MLoginBonusSerifDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MLoginBonusSerifs");
        return mLoginBonusSerifRepository.findAll(pageable)
            .map(mLoginBonusSerifMapper::toDto);
    }


    /**
     * Get one mLoginBonusSerif by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MLoginBonusSerifDTO> findOne(Long id) {
        log.debug("Request to get MLoginBonusSerif : {}", id);
        return mLoginBonusSerifRepository.findById(id)
            .map(mLoginBonusSerifMapper::toDto);
    }

    /**
     * Delete the mLoginBonusSerif by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MLoginBonusSerif : {}", id);
        mLoginBonusSerifRepository.deleteById(id);
    }
}
