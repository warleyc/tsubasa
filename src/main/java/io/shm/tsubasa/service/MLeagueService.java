package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MLeague;
import io.shm.tsubasa.repository.MLeagueRepository;
import io.shm.tsubasa.service.dto.MLeagueDTO;
import io.shm.tsubasa.service.mapper.MLeagueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MLeague}.
 */
@Service
@Transactional
public class MLeagueService {

    private final Logger log = LoggerFactory.getLogger(MLeagueService.class);

    private final MLeagueRepository mLeagueRepository;

    private final MLeagueMapper mLeagueMapper;

    public MLeagueService(MLeagueRepository mLeagueRepository, MLeagueMapper mLeagueMapper) {
        this.mLeagueRepository = mLeagueRepository;
        this.mLeagueMapper = mLeagueMapper;
    }

    /**
     * Save a mLeague.
     *
     * @param mLeagueDTO the entity to save.
     * @return the persisted entity.
     */
    public MLeagueDTO save(MLeagueDTO mLeagueDTO) {
        log.debug("Request to save MLeague : {}", mLeagueDTO);
        MLeague mLeague = mLeagueMapper.toEntity(mLeagueDTO);
        mLeague = mLeagueRepository.save(mLeague);
        return mLeagueMapper.toDto(mLeague);
    }

    /**
     * Get all the mLeagues.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MLeagueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MLeagues");
        return mLeagueRepository.findAll(pageable)
            .map(mLeagueMapper::toDto);
    }


    /**
     * Get one mLeague by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MLeagueDTO> findOne(Long id) {
        log.debug("Request to get MLeague : {}", id);
        return mLeagueRepository.findById(id)
            .map(mLeagueMapper::toDto);
    }

    /**
     * Delete the mLeague by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MLeague : {}", id);
        mLeagueRepository.deleteById(id);
    }
}
