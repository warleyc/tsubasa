package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MLoginBonusRound;
import io.shm.tsubasa.repository.MLoginBonusRoundRepository;
import io.shm.tsubasa.service.dto.MLoginBonusRoundDTO;
import io.shm.tsubasa.service.mapper.MLoginBonusRoundMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MLoginBonusRound}.
 */
@Service
@Transactional
public class MLoginBonusRoundService {

    private final Logger log = LoggerFactory.getLogger(MLoginBonusRoundService.class);

    private final MLoginBonusRoundRepository mLoginBonusRoundRepository;

    private final MLoginBonusRoundMapper mLoginBonusRoundMapper;

    public MLoginBonusRoundService(MLoginBonusRoundRepository mLoginBonusRoundRepository, MLoginBonusRoundMapper mLoginBonusRoundMapper) {
        this.mLoginBonusRoundRepository = mLoginBonusRoundRepository;
        this.mLoginBonusRoundMapper = mLoginBonusRoundMapper;
    }

    /**
     * Save a mLoginBonusRound.
     *
     * @param mLoginBonusRoundDTO the entity to save.
     * @return the persisted entity.
     */
    public MLoginBonusRoundDTO save(MLoginBonusRoundDTO mLoginBonusRoundDTO) {
        log.debug("Request to save MLoginBonusRound : {}", mLoginBonusRoundDTO);
        MLoginBonusRound mLoginBonusRound = mLoginBonusRoundMapper.toEntity(mLoginBonusRoundDTO);
        mLoginBonusRound = mLoginBonusRoundRepository.save(mLoginBonusRound);
        return mLoginBonusRoundMapper.toDto(mLoginBonusRound);
    }

    /**
     * Get all the mLoginBonusRounds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MLoginBonusRoundDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MLoginBonusRounds");
        return mLoginBonusRoundRepository.findAll(pageable)
            .map(mLoginBonusRoundMapper::toDto);
    }


    /**
     * Get one mLoginBonusRound by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MLoginBonusRoundDTO> findOne(Long id) {
        log.debug("Request to get MLoginBonusRound : {}", id);
        return mLoginBonusRoundRepository.findById(id)
            .map(mLoginBonusRoundMapper::toDto);
    }

    /**
     * Delete the mLoginBonusRound by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MLoginBonusRound : {}", id);
        mLoginBonusRoundRepository.deleteById(id);
    }
}
