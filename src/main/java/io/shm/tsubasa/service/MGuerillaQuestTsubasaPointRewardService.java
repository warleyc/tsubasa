package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGuerillaQuestTsubasaPointReward;
import io.shm.tsubasa.repository.MGuerillaQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.dto.MGuerillaQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MGuerillaQuestTsubasaPointRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGuerillaQuestTsubasaPointReward}.
 */
@Service
@Transactional
public class MGuerillaQuestTsubasaPointRewardService {

    private final Logger log = LoggerFactory.getLogger(MGuerillaQuestTsubasaPointRewardService.class);

    private final MGuerillaQuestTsubasaPointRewardRepository mGuerillaQuestTsubasaPointRewardRepository;

    private final MGuerillaQuestTsubasaPointRewardMapper mGuerillaQuestTsubasaPointRewardMapper;

    public MGuerillaQuestTsubasaPointRewardService(MGuerillaQuestTsubasaPointRewardRepository mGuerillaQuestTsubasaPointRewardRepository, MGuerillaQuestTsubasaPointRewardMapper mGuerillaQuestTsubasaPointRewardMapper) {
        this.mGuerillaQuestTsubasaPointRewardRepository = mGuerillaQuestTsubasaPointRewardRepository;
        this.mGuerillaQuestTsubasaPointRewardMapper = mGuerillaQuestTsubasaPointRewardMapper;
    }

    /**
     * Save a mGuerillaQuestTsubasaPointReward.
     *
     * @param mGuerillaQuestTsubasaPointRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MGuerillaQuestTsubasaPointRewardDTO save(MGuerillaQuestTsubasaPointRewardDTO mGuerillaQuestTsubasaPointRewardDTO) {
        log.debug("Request to save MGuerillaQuestTsubasaPointReward : {}", mGuerillaQuestTsubasaPointRewardDTO);
        MGuerillaQuestTsubasaPointReward mGuerillaQuestTsubasaPointReward = mGuerillaQuestTsubasaPointRewardMapper.toEntity(mGuerillaQuestTsubasaPointRewardDTO);
        mGuerillaQuestTsubasaPointReward = mGuerillaQuestTsubasaPointRewardRepository.save(mGuerillaQuestTsubasaPointReward);
        return mGuerillaQuestTsubasaPointRewardMapper.toDto(mGuerillaQuestTsubasaPointReward);
    }

    /**
     * Get all the mGuerillaQuestTsubasaPointRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuerillaQuestTsubasaPointRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGuerillaQuestTsubasaPointRewards");
        return mGuerillaQuestTsubasaPointRewardRepository.findAll(pageable)
            .map(mGuerillaQuestTsubasaPointRewardMapper::toDto);
    }


    /**
     * Get one mGuerillaQuestTsubasaPointReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGuerillaQuestTsubasaPointRewardDTO> findOne(Long id) {
        log.debug("Request to get MGuerillaQuestTsubasaPointReward : {}", id);
        return mGuerillaQuestTsubasaPointRewardRepository.findById(id)
            .map(mGuerillaQuestTsubasaPointRewardMapper::toDto);
    }

    /**
     * Delete the mGuerillaQuestTsubasaPointReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGuerillaQuestTsubasaPointReward : {}", id);
        mGuerillaQuestTsubasaPointRewardRepository.deleteById(id);
    }
}
