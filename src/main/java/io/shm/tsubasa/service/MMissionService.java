package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMission;
import io.shm.tsubasa.repository.MMissionRepository;
import io.shm.tsubasa.service.dto.MMissionDTO;
import io.shm.tsubasa.service.mapper.MMissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMission}.
 */
@Service
@Transactional
public class MMissionService {

    private final Logger log = LoggerFactory.getLogger(MMissionService.class);

    private final MMissionRepository mMissionRepository;

    private final MMissionMapper mMissionMapper;

    public MMissionService(MMissionRepository mMissionRepository, MMissionMapper mMissionMapper) {
        this.mMissionRepository = mMissionRepository;
        this.mMissionMapper = mMissionMapper;
    }

    /**
     * Save a mMission.
     *
     * @param mMissionDTO the entity to save.
     * @return the persisted entity.
     */
    public MMissionDTO save(MMissionDTO mMissionDTO) {
        log.debug("Request to save MMission : {}", mMissionDTO);
        MMission mMission = mMissionMapper.toEntity(mMissionDTO);
        mMission = mMissionRepository.save(mMission);
        return mMissionMapper.toDto(mMission);
    }

    /**
     * Get all the mMissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMissionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMissions");
        return mMissionRepository.findAll(pageable)
            .map(mMissionMapper::toDto);
    }


    /**
     * Get one mMission by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMissionDTO> findOne(Long id) {
        log.debug("Request to get MMission : {}", id);
        return mMissionRepository.findById(id)
            .map(mMissionMapper::toDto);
    }

    /**
     * Delete the mMission by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMission : {}", id);
        mMissionRepository.deleteById(id);
    }
}
