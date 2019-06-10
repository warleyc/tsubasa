package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGachaRenditionAfterInputCutInTextColor;
import io.shm.tsubasa.repository.MGachaRenditionAfterInputCutInTextColorRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionAfterInputCutInTextColorDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionAfterInputCutInTextColorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGachaRenditionAfterInputCutInTextColor}.
 */
@Service
@Transactional
public class MGachaRenditionAfterInputCutInTextColorService {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionAfterInputCutInTextColorService.class);

    private final MGachaRenditionAfterInputCutInTextColorRepository mGachaRenditionAfterInputCutInTextColorRepository;

    private final MGachaRenditionAfterInputCutInTextColorMapper mGachaRenditionAfterInputCutInTextColorMapper;

    public MGachaRenditionAfterInputCutInTextColorService(MGachaRenditionAfterInputCutInTextColorRepository mGachaRenditionAfterInputCutInTextColorRepository, MGachaRenditionAfterInputCutInTextColorMapper mGachaRenditionAfterInputCutInTextColorMapper) {
        this.mGachaRenditionAfterInputCutInTextColorRepository = mGachaRenditionAfterInputCutInTextColorRepository;
        this.mGachaRenditionAfterInputCutInTextColorMapper = mGachaRenditionAfterInputCutInTextColorMapper;
    }

    /**
     * Save a mGachaRenditionAfterInputCutInTextColor.
     *
     * @param mGachaRenditionAfterInputCutInTextColorDTO the entity to save.
     * @return the persisted entity.
     */
    public MGachaRenditionAfterInputCutInTextColorDTO save(MGachaRenditionAfterInputCutInTextColorDTO mGachaRenditionAfterInputCutInTextColorDTO) {
        log.debug("Request to save MGachaRenditionAfterInputCutInTextColor : {}", mGachaRenditionAfterInputCutInTextColorDTO);
        MGachaRenditionAfterInputCutInTextColor mGachaRenditionAfterInputCutInTextColor = mGachaRenditionAfterInputCutInTextColorMapper.toEntity(mGachaRenditionAfterInputCutInTextColorDTO);
        mGachaRenditionAfterInputCutInTextColor = mGachaRenditionAfterInputCutInTextColorRepository.save(mGachaRenditionAfterInputCutInTextColor);
        return mGachaRenditionAfterInputCutInTextColorMapper.toDto(mGachaRenditionAfterInputCutInTextColor);
    }

    /**
     * Get all the mGachaRenditionAfterInputCutInTextColors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionAfterInputCutInTextColorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGachaRenditionAfterInputCutInTextColors");
        return mGachaRenditionAfterInputCutInTextColorRepository.findAll(pageable)
            .map(mGachaRenditionAfterInputCutInTextColorMapper::toDto);
    }


    /**
     * Get one mGachaRenditionAfterInputCutInTextColor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGachaRenditionAfterInputCutInTextColorDTO> findOne(Long id) {
        log.debug("Request to get MGachaRenditionAfterInputCutInTextColor : {}", id);
        return mGachaRenditionAfterInputCutInTextColorRepository.findById(id)
            .map(mGachaRenditionAfterInputCutInTextColorMapper::toDto);
    }

    /**
     * Delete the mGachaRenditionAfterInputCutInTextColor by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGachaRenditionAfterInputCutInTextColor : {}", id);
        mGachaRenditionAfterInputCutInTextColorRepository.deleteById(id);
    }
}
