package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MEmblemParts;
import io.shm.tsubasa.repository.MEmblemPartsRepository;
import io.shm.tsubasa.service.dto.MEmblemPartsDTO;
import io.shm.tsubasa.service.mapper.MEmblemPartsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MEmblemParts}.
 */
@Service
@Transactional
public class MEmblemPartsService {

    private final Logger log = LoggerFactory.getLogger(MEmblemPartsService.class);

    private final MEmblemPartsRepository mEmblemPartsRepository;

    private final MEmblemPartsMapper mEmblemPartsMapper;

    public MEmblemPartsService(MEmblemPartsRepository mEmblemPartsRepository, MEmblemPartsMapper mEmblemPartsMapper) {
        this.mEmblemPartsRepository = mEmblemPartsRepository;
        this.mEmblemPartsMapper = mEmblemPartsMapper;
    }

    /**
     * Save a mEmblemParts.
     *
     * @param mEmblemPartsDTO the entity to save.
     * @return the persisted entity.
     */
    public MEmblemPartsDTO save(MEmblemPartsDTO mEmblemPartsDTO) {
        log.debug("Request to save MEmblemParts : {}", mEmblemPartsDTO);
        MEmblemParts mEmblemParts = mEmblemPartsMapper.toEntity(mEmblemPartsDTO);
        mEmblemParts = mEmblemPartsRepository.save(mEmblemParts);
        return mEmblemPartsMapper.toDto(mEmblemParts);
    }

    /**
     * Get all the mEmblemParts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MEmblemPartsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MEmblemParts");
        return mEmblemPartsRepository.findAll(pageable)
            .map(mEmblemPartsMapper::toDto);
    }


    /**
     * Get one mEmblemParts by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MEmblemPartsDTO> findOne(Long id) {
        log.debug("Request to get MEmblemParts : {}", id);
        return mEmblemPartsRepository.findById(id)
            .map(mEmblemPartsMapper::toDto);
    }

    /**
     * Delete the mEmblemParts by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MEmblemParts : {}", id);
        mEmblemPartsRepository.deleteById(id);
    }
}
