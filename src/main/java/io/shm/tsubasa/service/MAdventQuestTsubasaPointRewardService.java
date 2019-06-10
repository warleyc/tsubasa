package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MAdventQuestTsubasaPointReward;
import io.shm.tsubasa.repository.MAdventQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.dto.MAdventQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MAdventQuestTsubasaPointRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MAdventQuestTsubasaPointReward}.
 */
@Service
@Transactional
public class MAdventQuestTsubasaPointRewardService {

    private final Logger log = LoggerFactory.getLogger(MAdventQuestTsubasaPointRewardService.class);

    private final MAdventQuestTsubasaPointRewardRepository mAdventQuestTsubasaPointRewardRepository;

    private final MAdventQuestTsubasaPointRewardMapper mAdventQuestTsubasaPointRewardMapper;

    public MAdventQuestTsubasaPointRewardService(MAdventQuestTsubasaPointRewardRepository mAdventQuestTsubasaPointRewardRepository, MAdventQuestTsubasaPointRewardMapper mAdventQuestTsubasaPointRewardMapper) {
        this.mAdventQuestTsubasaPointRewardRepository = mAdventQuestTsubasaPointRewardRepository;
        this.mAdventQuestTsubasaPointRewardMapper = mAdventQuestTsubasaPointRewardMapper;
    }

    /**
     * Save a mAdventQuestTsubasaPointReward.
     *
     * @param mAdventQuestTsubasaPointRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MAdventQuestTsubasaPointRewardDTO save(MAdventQuestTsubasaPointRewardDTO mAdventQuestTsubasaPointRewardDTO) {
        log.debug("Request to save MAdventQuestTsubasaPointReward : {}", mAdventQuestTsubasaPointRewardDTO);
        MAdventQuestTsubasaPointReward mAdventQuestTsubasaPointReward = mAdventQuestTsubasaPointRewardMapper.toEntity(mAdventQuestTsubasaPointRewardDTO);
        mAdventQuestTsubasaPointReward = mAdventQuestTsubasaPointRewardRepository.save(mAdventQuestTsubasaPointReward);
        return mAdventQuestTsubasaPointRewardMapper.toDto(mAdventQuestTsubasaPointReward);
    }

    /**
     * Get all the mAdventQuestTsubasaPointRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MAdventQuestTsubasaPointRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MAdventQuestTsubasaPointRewards");
        return mAdventQuestTsubasaPointRewardRepository.findAll(pageable)
            .map(mAdventQuestTsubasaPointRewardMapper::toDto);
    }


    /**
     * Get one mAdventQuestTsubasaPointReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MAdventQuestTsubasaPointRewardDTO> findOne(Long id) {
        log.debug("Request to get MAdventQuestTsubasaPointReward : {}", id);
        return mAdventQuestTsubasaPointRewardRepository.findById(id)
            .map(mAdventQuestTsubasaPointRewardMapper::toDto);
    }

    /**
     * Delete the mAdventQuestTsubasaPointReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MAdventQuestTsubasaPointReward : {}", id);
        mAdventQuestTsubasaPointRewardRepository.deleteById(id);
    }
}
