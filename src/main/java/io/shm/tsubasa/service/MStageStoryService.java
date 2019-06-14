package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MStageStory;
import io.shm.tsubasa.repository.MStageStoryRepository;
import io.shm.tsubasa.service.dto.MStageStoryDTO;
import io.shm.tsubasa.service.mapper.MStageStoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MStageStory}.
 */
@Service
@Transactional
public class MStageStoryService {

    private final Logger log = LoggerFactory.getLogger(MStageStoryService.class);

    private final MStageStoryRepository mStageStoryRepository;

    private final MStageStoryMapper mStageStoryMapper;

    public MStageStoryService(MStageStoryRepository mStageStoryRepository, MStageStoryMapper mStageStoryMapper) {
        this.mStageStoryRepository = mStageStoryRepository;
        this.mStageStoryMapper = mStageStoryMapper;
    }

    /**
     * Save a mStageStory.
     *
     * @param mStageStoryDTO the entity to save.
     * @return the persisted entity.
     */
    public MStageStoryDTO save(MStageStoryDTO mStageStoryDTO) {
        log.debug("Request to save MStageStory : {}", mStageStoryDTO);
        MStageStory mStageStory = mStageStoryMapper.toEntity(mStageStoryDTO);
        mStageStory = mStageStoryRepository.save(mStageStory);
        return mStageStoryMapper.toDto(mStageStory);
    }

    /**
     * Get all the mStageStories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MStageStoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MStageStories");
        return mStageStoryRepository.findAll(pageable)
            .map(mStageStoryMapper::toDto);
    }


    /**
     * Get one mStageStory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MStageStoryDTO> findOne(Long id) {
        log.debug("Request to get MStageStory : {}", id);
        return mStageStoryRepository.findById(id)
            .map(mStageStoryMapper::toDto);
    }

    /**
     * Delete the mStageStory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MStageStory : {}", id);
        mStageStoryRepository.deleteById(id);
    }
}
