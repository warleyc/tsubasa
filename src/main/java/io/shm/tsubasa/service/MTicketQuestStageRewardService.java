package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTicketQuestStageReward;
import io.shm.tsubasa.repository.MTicketQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MTicketQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MTicketQuestStageRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTicketQuestStageReward}.
 */
@Service
@Transactional
public class MTicketQuestStageRewardService {

    private final Logger log = LoggerFactory.getLogger(MTicketQuestStageRewardService.class);

    private final MTicketQuestStageRewardRepository mTicketQuestStageRewardRepository;

    private final MTicketQuestStageRewardMapper mTicketQuestStageRewardMapper;

    public MTicketQuestStageRewardService(MTicketQuestStageRewardRepository mTicketQuestStageRewardRepository, MTicketQuestStageRewardMapper mTicketQuestStageRewardMapper) {
        this.mTicketQuestStageRewardRepository = mTicketQuestStageRewardRepository;
        this.mTicketQuestStageRewardMapper = mTicketQuestStageRewardMapper;
    }

    /**
     * Save a mTicketQuestStageReward.
     *
     * @param mTicketQuestStageRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MTicketQuestStageRewardDTO save(MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO) {
        log.debug("Request to save MTicketQuestStageReward : {}", mTicketQuestStageRewardDTO);
        MTicketQuestStageReward mTicketQuestStageReward = mTicketQuestStageRewardMapper.toEntity(mTicketQuestStageRewardDTO);
        mTicketQuestStageReward = mTicketQuestStageRewardRepository.save(mTicketQuestStageReward);
        return mTicketQuestStageRewardMapper.toDto(mTicketQuestStageReward);
    }

    /**
     * Get all the mTicketQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTicketQuestStageRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTicketQuestStageRewards");
        return mTicketQuestStageRewardRepository.findAll(pageable)
            .map(mTicketQuestStageRewardMapper::toDto);
    }


    /**
     * Get one mTicketQuestStageReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTicketQuestStageRewardDTO> findOne(Long id) {
        log.debug("Request to get MTicketQuestStageReward : {}", id);
        return mTicketQuestStageRewardRepository.findById(id)
            .map(mTicketQuestStageRewardMapper::toDto);
    }

    /**
     * Delete the mTicketQuestStageReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTicketQuestStageReward : {}", id);
        mTicketQuestStageRewardRepository.deleteById(id);
    }
}
