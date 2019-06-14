package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTicketQuestTsubasaPointReward;
import io.shm.tsubasa.repository.MTicketQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.dto.MTicketQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MTicketQuestTsubasaPointRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTicketQuestTsubasaPointReward}.
 */
@Service
@Transactional
public class MTicketQuestTsubasaPointRewardService {

    private final Logger log = LoggerFactory.getLogger(MTicketQuestTsubasaPointRewardService.class);

    private final MTicketQuestTsubasaPointRewardRepository mTicketQuestTsubasaPointRewardRepository;

    private final MTicketQuestTsubasaPointRewardMapper mTicketQuestTsubasaPointRewardMapper;

    public MTicketQuestTsubasaPointRewardService(MTicketQuestTsubasaPointRewardRepository mTicketQuestTsubasaPointRewardRepository, MTicketQuestTsubasaPointRewardMapper mTicketQuestTsubasaPointRewardMapper) {
        this.mTicketQuestTsubasaPointRewardRepository = mTicketQuestTsubasaPointRewardRepository;
        this.mTicketQuestTsubasaPointRewardMapper = mTicketQuestTsubasaPointRewardMapper;
    }

    /**
     * Save a mTicketQuestTsubasaPointReward.
     *
     * @param mTicketQuestTsubasaPointRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MTicketQuestTsubasaPointRewardDTO save(MTicketQuestTsubasaPointRewardDTO mTicketQuestTsubasaPointRewardDTO) {
        log.debug("Request to save MTicketQuestTsubasaPointReward : {}", mTicketQuestTsubasaPointRewardDTO);
        MTicketQuestTsubasaPointReward mTicketQuestTsubasaPointReward = mTicketQuestTsubasaPointRewardMapper.toEntity(mTicketQuestTsubasaPointRewardDTO);
        mTicketQuestTsubasaPointReward = mTicketQuestTsubasaPointRewardRepository.save(mTicketQuestTsubasaPointReward);
        return mTicketQuestTsubasaPointRewardMapper.toDto(mTicketQuestTsubasaPointReward);
    }

    /**
     * Get all the mTicketQuestTsubasaPointRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTicketQuestTsubasaPointRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTicketQuestTsubasaPointRewards");
        return mTicketQuestTsubasaPointRewardRepository.findAll(pageable)
            .map(mTicketQuestTsubasaPointRewardMapper::toDto);
    }


    /**
     * Get one mTicketQuestTsubasaPointReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTicketQuestTsubasaPointRewardDTO> findOne(Long id) {
        log.debug("Request to get MTicketQuestTsubasaPointReward : {}", id);
        return mTicketQuestTsubasaPointRewardRepository.findById(id)
            .map(mTicketQuestTsubasaPointRewardMapper::toDto);
    }

    /**
     * Delete the mTicketQuestTsubasaPointReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTicketQuestTsubasaPointReward : {}", id);
        mTicketQuestTsubasaPointRewardRepository.deleteById(id);
    }
}
