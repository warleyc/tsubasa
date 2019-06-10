package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MChallengeQuestWorld;
import io.shm.tsubasa.repository.MChallengeQuestWorldRepository;
import io.shm.tsubasa.service.dto.MChallengeQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MChallengeQuestWorldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MChallengeQuestWorld}.
 */
@Service
@Transactional
public class MChallengeQuestWorldService {

    private final Logger log = LoggerFactory.getLogger(MChallengeQuestWorldService.class);

    private final MChallengeQuestWorldRepository mChallengeQuestWorldRepository;

    private final MChallengeQuestWorldMapper mChallengeQuestWorldMapper;

    public MChallengeQuestWorldService(MChallengeQuestWorldRepository mChallengeQuestWorldRepository, MChallengeQuestWorldMapper mChallengeQuestWorldMapper) {
        this.mChallengeQuestWorldRepository = mChallengeQuestWorldRepository;
        this.mChallengeQuestWorldMapper = mChallengeQuestWorldMapper;
    }

    /**
     * Save a mChallengeQuestWorld.
     *
     * @param mChallengeQuestWorldDTO the entity to save.
     * @return the persisted entity.
     */
    public MChallengeQuestWorldDTO save(MChallengeQuestWorldDTO mChallengeQuestWorldDTO) {
        log.debug("Request to save MChallengeQuestWorld : {}", mChallengeQuestWorldDTO);
        MChallengeQuestWorld mChallengeQuestWorld = mChallengeQuestWorldMapper.toEntity(mChallengeQuestWorldDTO);
        mChallengeQuestWorld = mChallengeQuestWorldRepository.save(mChallengeQuestWorld);
        return mChallengeQuestWorldMapper.toDto(mChallengeQuestWorld);
    }

    /**
     * Get all the mChallengeQuestWorlds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MChallengeQuestWorldDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MChallengeQuestWorlds");
        return mChallengeQuestWorldRepository.findAll(pageable)
            .map(mChallengeQuestWorldMapper::toDto);
    }


    /**
     * Get one mChallengeQuestWorld by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MChallengeQuestWorldDTO> findOne(Long id) {
        log.debug("Request to get MChallengeQuestWorld : {}", id);
        return mChallengeQuestWorldRepository.findById(id)
            .map(mChallengeQuestWorldMapper::toDto);
    }

    /**
     * Delete the mChallengeQuestWorld by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MChallengeQuestWorld : {}", id);
        mChallengeQuestWorldRepository.deleteById(id);
    }
}
