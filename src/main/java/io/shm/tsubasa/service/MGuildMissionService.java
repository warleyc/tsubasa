package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGuildMission;
import io.shm.tsubasa.repository.MGuildMissionRepository;
import io.shm.tsubasa.service.dto.MGuildMissionDTO;
import io.shm.tsubasa.service.mapper.MGuildMissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGuildMission}.
 */
@Service
@Transactional
public class MGuildMissionService {

    private final Logger log = LoggerFactory.getLogger(MGuildMissionService.class);

    private final MGuildMissionRepository mGuildMissionRepository;

    private final MGuildMissionMapper mGuildMissionMapper;

    public MGuildMissionService(MGuildMissionRepository mGuildMissionRepository, MGuildMissionMapper mGuildMissionMapper) {
        this.mGuildMissionRepository = mGuildMissionRepository;
        this.mGuildMissionMapper = mGuildMissionMapper;
    }

    /**
     * Save a mGuildMission.
     *
     * @param mGuildMissionDTO the entity to save.
     * @return the persisted entity.
     */
    public MGuildMissionDTO save(MGuildMissionDTO mGuildMissionDTO) {
        log.debug("Request to save MGuildMission : {}", mGuildMissionDTO);
        MGuildMission mGuildMission = mGuildMissionMapper.toEntity(mGuildMissionDTO);
        mGuildMission = mGuildMissionRepository.save(mGuildMission);
        return mGuildMissionMapper.toDto(mGuildMission);
    }

    /**
     * Get all the mGuildMissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuildMissionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGuildMissions");
        return mGuildMissionRepository.findAll(pageable)
            .map(mGuildMissionMapper::toDto);
    }


    /**
     * Get one mGuildMission by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGuildMissionDTO> findOne(Long id) {
        log.debug("Request to get MGuildMission : {}", id);
        return mGuildMissionRepository.findById(id)
            .map(mGuildMissionMapper::toDto);
    }

    /**
     * Delete the mGuildMission by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGuildMission : {}", id);
        mGuildMissionRepository.deleteById(id);
    }
}
