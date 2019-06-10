package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MArousalMaterial;
import io.shm.tsubasa.repository.MArousalMaterialRepository;
import io.shm.tsubasa.service.dto.MArousalMaterialDTO;
import io.shm.tsubasa.service.mapper.MArousalMaterialMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MArousalMaterial}.
 */
@Service
@Transactional
public class MArousalMaterialService {

    private final Logger log = LoggerFactory.getLogger(MArousalMaterialService.class);

    private final MArousalMaterialRepository mArousalMaterialRepository;

    private final MArousalMaterialMapper mArousalMaterialMapper;

    public MArousalMaterialService(MArousalMaterialRepository mArousalMaterialRepository, MArousalMaterialMapper mArousalMaterialMapper) {
        this.mArousalMaterialRepository = mArousalMaterialRepository;
        this.mArousalMaterialMapper = mArousalMaterialMapper;
    }

    /**
     * Save a mArousalMaterial.
     *
     * @param mArousalMaterialDTO the entity to save.
     * @return the persisted entity.
     */
    public MArousalMaterialDTO save(MArousalMaterialDTO mArousalMaterialDTO) {
        log.debug("Request to save MArousalMaterial : {}", mArousalMaterialDTO);
        MArousalMaterial mArousalMaterial = mArousalMaterialMapper.toEntity(mArousalMaterialDTO);
        mArousalMaterial = mArousalMaterialRepository.save(mArousalMaterial);
        return mArousalMaterialMapper.toDto(mArousalMaterial);
    }

    /**
     * Get all the mArousalMaterials.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MArousalMaterialDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MArousalMaterials");
        return mArousalMaterialRepository.findAll(pageable)
            .map(mArousalMaterialMapper::toDto);
    }


    /**
     * Get one mArousalMaterial by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MArousalMaterialDTO> findOne(Long id) {
        log.debug("Request to get MArousalMaterial : {}", id);
        return mArousalMaterialRepository.findById(id)
            .map(mArousalMaterialMapper::toDto);
    }

    /**
     * Delete the mArousalMaterial by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MArousalMaterial : {}", id);
        mArousalMaterialRepository.deleteById(id);
    }
}
