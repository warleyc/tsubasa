package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGuildRoulettePrize;
import io.shm.tsubasa.repository.MGuildRoulettePrizeRepository;
import io.shm.tsubasa.service.dto.MGuildRoulettePrizeDTO;
import io.shm.tsubasa.service.mapper.MGuildRoulettePrizeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGuildRoulettePrize}.
 */
@Service
@Transactional
public class MGuildRoulettePrizeService {

    private final Logger log = LoggerFactory.getLogger(MGuildRoulettePrizeService.class);

    private final MGuildRoulettePrizeRepository mGuildRoulettePrizeRepository;

    private final MGuildRoulettePrizeMapper mGuildRoulettePrizeMapper;

    public MGuildRoulettePrizeService(MGuildRoulettePrizeRepository mGuildRoulettePrizeRepository, MGuildRoulettePrizeMapper mGuildRoulettePrizeMapper) {
        this.mGuildRoulettePrizeRepository = mGuildRoulettePrizeRepository;
        this.mGuildRoulettePrizeMapper = mGuildRoulettePrizeMapper;
    }

    /**
     * Save a mGuildRoulettePrize.
     *
     * @param mGuildRoulettePrizeDTO the entity to save.
     * @return the persisted entity.
     */
    public MGuildRoulettePrizeDTO save(MGuildRoulettePrizeDTO mGuildRoulettePrizeDTO) {
        log.debug("Request to save MGuildRoulettePrize : {}", mGuildRoulettePrizeDTO);
        MGuildRoulettePrize mGuildRoulettePrize = mGuildRoulettePrizeMapper.toEntity(mGuildRoulettePrizeDTO);
        mGuildRoulettePrize = mGuildRoulettePrizeRepository.save(mGuildRoulettePrize);
        return mGuildRoulettePrizeMapper.toDto(mGuildRoulettePrize);
    }

    /**
     * Get all the mGuildRoulettePrizes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuildRoulettePrizeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGuildRoulettePrizes");
        return mGuildRoulettePrizeRepository.findAll(pageable)
            .map(mGuildRoulettePrizeMapper::toDto);
    }


    /**
     * Get one mGuildRoulettePrize by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGuildRoulettePrizeDTO> findOne(Long id) {
        log.debug("Request to get MGuildRoulettePrize : {}", id);
        return mGuildRoulettePrizeRepository.findById(id)
            .map(mGuildRoulettePrizeMapper::toDto);
    }

    /**
     * Delete the mGuildRoulettePrize by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGuildRoulettePrize : {}", id);
        mGuildRoulettePrizeRepository.deleteById(id);
    }
}
