package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MArousalItem;
import io.shm.tsubasa.repository.MArousalItemRepository;
import io.shm.tsubasa.service.dto.MArousalItemDTO;
import io.shm.tsubasa.service.mapper.MArousalItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MArousalItem}.
 */
@Service
@Transactional
public class MArousalItemService {

    private final Logger log = LoggerFactory.getLogger(MArousalItemService.class);

    private final MArousalItemRepository mArousalItemRepository;

    private final MArousalItemMapper mArousalItemMapper;

    public MArousalItemService(MArousalItemRepository mArousalItemRepository, MArousalItemMapper mArousalItemMapper) {
        this.mArousalItemRepository = mArousalItemRepository;
        this.mArousalItemMapper = mArousalItemMapper;
    }

    /**
     * Save a mArousalItem.
     *
     * @param mArousalItemDTO the entity to save.
     * @return the persisted entity.
     */
    public MArousalItemDTO save(MArousalItemDTO mArousalItemDTO) {
        log.debug("Request to save MArousalItem : {}", mArousalItemDTO);
        MArousalItem mArousalItem = mArousalItemMapper.toEntity(mArousalItemDTO);
        mArousalItem = mArousalItemRepository.save(mArousalItem);
        return mArousalItemMapper.toDto(mArousalItem);
    }

    /**
     * Get all the mArousalItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MArousalItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MArousalItems");
        return mArousalItemRepository.findAll(pageable)
            .map(mArousalItemMapper::toDto);
    }


    /**
     * Get one mArousalItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MArousalItemDTO> findOne(Long id) {
        log.debug("Request to get MArousalItem : {}", id);
        return mArousalItemRepository.findById(id)
            .map(mArousalItemMapper::toDto);
    }

    /**
     * Delete the mArousalItem by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MArousalItem : {}", id);
        mArousalItemRepository.deleteById(id);
    }
}
