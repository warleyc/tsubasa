package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMarathonBoostItem;
import io.shm.tsubasa.repository.MMarathonBoostItemRepository;
import io.shm.tsubasa.service.dto.MMarathonBoostItemDTO;
import io.shm.tsubasa.service.mapper.MMarathonBoostItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMarathonBoostItem}.
 */
@Service
@Transactional
public class MMarathonBoostItemService {

    private final Logger log = LoggerFactory.getLogger(MMarathonBoostItemService.class);

    private final MMarathonBoostItemRepository mMarathonBoostItemRepository;

    private final MMarathonBoostItemMapper mMarathonBoostItemMapper;

    public MMarathonBoostItemService(MMarathonBoostItemRepository mMarathonBoostItemRepository, MMarathonBoostItemMapper mMarathonBoostItemMapper) {
        this.mMarathonBoostItemRepository = mMarathonBoostItemRepository;
        this.mMarathonBoostItemMapper = mMarathonBoostItemMapper;
    }

    /**
     * Save a mMarathonBoostItem.
     *
     * @param mMarathonBoostItemDTO the entity to save.
     * @return the persisted entity.
     */
    public MMarathonBoostItemDTO save(MMarathonBoostItemDTO mMarathonBoostItemDTO) {
        log.debug("Request to save MMarathonBoostItem : {}", mMarathonBoostItemDTO);
        MMarathonBoostItem mMarathonBoostItem = mMarathonBoostItemMapper.toEntity(mMarathonBoostItemDTO);
        mMarathonBoostItem = mMarathonBoostItemRepository.save(mMarathonBoostItem);
        return mMarathonBoostItemMapper.toDto(mMarathonBoostItem);
    }

    /**
     * Get all the mMarathonBoostItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonBoostItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMarathonBoostItems");
        return mMarathonBoostItemRepository.findAll(pageable)
            .map(mMarathonBoostItemMapper::toDto);
    }


    /**
     * Get one mMarathonBoostItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMarathonBoostItemDTO> findOne(Long id) {
        log.debug("Request to get MMarathonBoostItem : {}", id);
        return mMarathonBoostItemRepository.findById(id)
            .map(mMarathonBoostItemMapper::toDto);
    }

    /**
     * Delete the mMarathonBoostItem by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMarathonBoostItem : {}", id);
        mMarathonBoostItemRepository.deleteById(id);
    }
}
