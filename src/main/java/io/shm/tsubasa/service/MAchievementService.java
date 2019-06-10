package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MAchievement;
import io.shm.tsubasa.repository.MAchievementRepository;
import io.shm.tsubasa.service.dto.MAchievementDTO;
import io.shm.tsubasa.service.mapper.MAchievementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MAchievement}.
 */
@Service
@Transactional
public class MAchievementService {

    private final Logger log = LoggerFactory.getLogger(MAchievementService.class);

    private final MAchievementRepository mAchievementRepository;

    private final MAchievementMapper mAchievementMapper;

    public MAchievementService(MAchievementRepository mAchievementRepository, MAchievementMapper mAchievementMapper) {
        this.mAchievementRepository = mAchievementRepository;
        this.mAchievementMapper = mAchievementMapper;
    }

    /**
     * Save a mAchievement.
     *
     * @param mAchievementDTO the entity to save.
     * @return the persisted entity.
     */
    public MAchievementDTO save(MAchievementDTO mAchievementDTO) {
        log.debug("Request to save MAchievement : {}", mAchievementDTO);
        MAchievement mAchievement = mAchievementMapper.toEntity(mAchievementDTO);
        mAchievement = mAchievementRepository.save(mAchievement);
        return mAchievementMapper.toDto(mAchievement);
    }

    /**
     * Get all the mAchievements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MAchievementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MAchievements");
        return mAchievementRepository.findAll(pageable)
            .map(mAchievementMapper::toDto);
    }


    /**
     * Get one mAchievement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MAchievementDTO> findOne(Long id) {
        log.debug("Request to get MAchievement : {}", id);
        return mAchievementRepository.findById(id)
            .map(mAchievementMapper::toDto);
    }

    /**
     * Delete the mAchievement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MAchievement : {}", id);
        mAchievementRepository.deleteById(id);
    }
}
