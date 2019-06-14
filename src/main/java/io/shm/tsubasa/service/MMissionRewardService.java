package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMissionReward;
import io.shm.tsubasa.repository.MMissionRewardRepository;
import io.shm.tsubasa.service.dto.MMissionRewardDTO;
import io.shm.tsubasa.service.mapper.MMissionRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMissionReward}.
 */
@Service
@Transactional
public class MMissionRewardService {

    private final Logger log = LoggerFactory.getLogger(MMissionRewardService.class);

    private final MMissionRewardRepository mMissionRewardRepository;

    private final MMissionRewardMapper mMissionRewardMapper;

    public MMissionRewardService(MMissionRewardRepository mMissionRewardRepository, MMissionRewardMapper mMissionRewardMapper) {
        this.mMissionRewardRepository = mMissionRewardRepository;
        this.mMissionRewardMapper = mMissionRewardMapper;
    }

    /**
     * Save a mMissionReward.
     *
     * @param mMissionRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MMissionRewardDTO save(MMissionRewardDTO mMissionRewardDTO) {
        log.debug("Request to save MMissionReward : {}", mMissionRewardDTO);
        MMissionReward mMissionReward = mMissionRewardMapper.toEntity(mMissionRewardDTO);
        mMissionReward = mMissionRewardRepository.save(mMissionReward);
        return mMissionRewardMapper.toDto(mMissionReward);
    }

    /**
     * Get all the mMissionRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMissionRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMissionRewards");
        return mMissionRewardRepository.findAll(pageable)
            .map(mMissionRewardMapper::toDto);
    }


    /**
     * Get one mMissionReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMissionRewardDTO> findOne(Long id) {
        log.debug("Request to get MMissionReward : {}", id);
        return mMissionRewardRepository.findById(id)
            .map(mMissionRewardMapper::toDto);
    }

    /**
     * Delete the mMissionReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMissionReward : {}", id);
        mMissionRewardRepository.deleteById(id);
    }
}
