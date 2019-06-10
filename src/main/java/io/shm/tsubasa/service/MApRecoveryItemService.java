package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MApRecoveryItem;
import io.shm.tsubasa.repository.MApRecoveryItemRepository;
import io.shm.tsubasa.service.dto.MApRecoveryItemDTO;
import io.shm.tsubasa.service.mapper.MApRecoveryItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MApRecoveryItem}.
 */
@Service
@Transactional
public class MApRecoveryItemService {

    private final Logger log = LoggerFactory.getLogger(MApRecoveryItemService.class);

    private final MApRecoveryItemRepository mApRecoveryItemRepository;

    private final MApRecoveryItemMapper mApRecoveryItemMapper;

    public MApRecoveryItemService(MApRecoveryItemRepository mApRecoveryItemRepository, MApRecoveryItemMapper mApRecoveryItemMapper) {
        this.mApRecoveryItemRepository = mApRecoveryItemRepository;
        this.mApRecoveryItemMapper = mApRecoveryItemMapper;
    }

    /**
     * Save a mApRecoveryItem.
     *
     * @param mApRecoveryItemDTO the entity to save.
     * @return the persisted entity.
     */
    public MApRecoveryItemDTO save(MApRecoveryItemDTO mApRecoveryItemDTO) {
        log.debug("Request to save MApRecoveryItem : {}", mApRecoveryItemDTO);
        MApRecoveryItem mApRecoveryItem = mApRecoveryItemMapper.toEntity(mApRecoveryItemDTO);
        mApRecoveryItem = mApRecoveryItemRepository.save(mApRecoveryItem);
        return mApRecoveryItemMapper.toDto(mApRecoveryItem);
    }

    /**
     * Get all the mApRecoveryItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MApRecoveryItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MApRecoveryItems");
        return mApRecoveryItemRepository.findAll(pageable)
            .map(mApRecoveryItemMapper::toDto);
    }


    /**
     * Get one mApRecoveryItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MApRecoveryItemDTO> findOne(Long id) {
        log.debug("Request to get MApRecoveryItem : {}", id);
        return mApRecoveryItemRepository.findById(id)
            .map(mApRecoveryItemMapper::toDto);
    }

    /**
     * Delete the mApRecoveryItem by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MApRecoveryItem : {}", id);
        mApRecoveryItemRepository.deleteById(id);
    }
}
