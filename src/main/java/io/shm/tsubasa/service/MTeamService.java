package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTeam;
import io.shm.tsubasa.repository.MTeamRepository;
import io.shm.tsubasa.service.dto.MTeamDTO;
import io.shm.tsubasa.service.mapper.MTeamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTeam}.
 */
@Service
@Transactional
public class MTeamService {

    private final Logger log = LoggerFactory.getLogger(MTeamService.class);

    private final MTeamRepository mTeamRepository;

    private final MTeamMapper mTeamMapper;

    public MTeamService(MTeamRepository mTeamRepository, MTeamMapper mTeamMapper) {
        this.mTeamRepository = mTeamRepository;
        this.mTeamMapper = mTeamMapper;
    }

    /**
     * Save a mTeam.
     *
     * @param mTeamDTO the entity to save.
     * @return the persisted entity.
     */
    public MTeamDTO save(MTeamDTO mTeamDTO) {
        log.debug("Request to save MTeam : {}", mTeamDTO);
        MTeam mTeam = mTeamMapper.toEntity(mTeamDTO);
        mTeam = mTeamRepository.save(mTeam);
        return mTeamMapper.toDto(mTeam);
    }

    /**
     * Get all the mTeams.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTeamDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTeams");
        return mTeamRepository.findAll(pageable)
            .map(mTeamMapper::toDto);
    }


    /**
     * Get one mTeam by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTeamDTO> findOne(Long id) {
        log.debug("Request to get MTeam : {}", id);
        return mTeamRepository.findById(id)
            .map(mTeamMapper::toDto);
    }

    /**
     * Delete the mTeam by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTeam : {}", id);
        mTeamRepository.deleteById(id);
    }
}
