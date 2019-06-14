package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMarathonQuestTsubasaPointReward;
import io.shm.tsubasa.repository.MMarathonQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.dto.MMarathonQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MMarathonQuestTsubasaPointRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMarathonQuestTsubasaPointReward}.
 */
@Service
@Transactional
public class MMarathonQuestTsubasaPointRewardService {

    private final Logger log = LoggerFactory.getLogger(MMarathonQuestTsubasaPointRewardService.class);

    private final MMarathonQuestTsubasaPointRewardRepository mMarathonQuestTsubasaPointRewardRepository;

    private final MMarathonQuestTsubasaPointRewardMapper mMarathonQuestTsubasaPointRewardMapper;

    public MMarathonQuestTsubasaPointRewardService(MMarathonQuestTsubasaPointRewardRepository mMarathonQuestTsubasaPointRewardRepository, MMarathonQuestTsubasaPointRewardMapper mMarathonQuestTsubasaPointRewardMapper) {
        this.mMarathonQuestTsubasaPointRewardRepository = mMarathonQuestTsubasaPointRewardRepository;
        this.mMarathonQuestTsubasaPointRewardMapper = mMarathonQuestTsubasaPointRewardMapper;
    }

    /**
     * Save a mMarathonQuestTsubasaPointReward.
     *
     * @param mMarathonQuestTsubasaPointRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MMarathonQuestTsubasaPointRewardDTO save(MMarathonQuestTsubasaPointRewardDTO mMarathonQuestTsubasaPointRewardDTO) {
        log.debug("Request to save MMarathonQuestTsubasaPointReward : {}", mMarathonQuestTsubasaPointRewardDTO);
        MMarathonQuestTsubasaPointReward mMarathonQuestTsubasaPointReward = mMarathonQuestTsubasaPointRewardMapper.toEntity(mMarathonQuestTsubasaPointRewardDTO);
        mMarathonQuestTsubasaPointReward = mMarathonQuestTsubasaPointRewardRepository.save(mMarathonQuestTsubasaPointReward);
        return mMarathonQuestTsubasaPointRewardMapper.toDto(mMarathonQuestTsubasaPointReward);
    }

    /**
     * Get all the mMarathonQuestTsubasaPointRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonQuestTsubasaPointRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMarathonQuestTsubasaPointRewards");
        return mMarathonQuestTsubasaPointRewardRepository.findAll(pageable)
            .map(mMarathonQuestTsubasaPointRewardMapper::toDto);
    }


    /**
     * Get one mMarathonQuestTsubasaPointReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMarathonQuestTsubasaPointRewardDTO> findOne(Long id) {
        log.debug("Request to get MMarathonQuestTsubasaPointReward : {}", id);
        return mMarathonQuestTsubasaPointRewardRepository.findById(id)
            .map(mMarathonQuestTsubasaPointRewardMapper::toDto);
    }

    /**
     * Delete the mMarathonQuestTsubasaPointReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMarathonQuestTsubasaPointReward : {}", id);
        mMarathonQuestTsubasaPointRewardRepository.deleteById(id);
    }
}
