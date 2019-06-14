package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MModelUniformBottomResource;
import io.shm.tsubasa.repository.MModelUniformBottomResourceRepository;
import io.shm.tsubasa.service.dto.MModelUniformBottomResourceDTO;
import io.shm.tsubasa.service.mapper.MModelUniformBottomResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MModelUniformBottomResource}.
 */
@Service
@Transactional
public class MModelUniformBottomResourceService {

    private final Logger log = LoggerFactory.getLogger(MModelUniformBottomResourceService.class);

    private final MModelUniformBottomResourceRepository mModelUniformBottomResourceRepository;

    private final MModelUniformBottomResourceMapper mModelUniformBottomResourceMapper;

    public MModelUniformBottomResourceService(MModelUniformBottomResourceRepository mModelUniformBottomResourceRepository, MModelUniformBottomResourceMapper mModelUniformBottomResourceMapper) {
        this.mModelUniformBottomResourceRepository = mModelUniformBottomResourceRepository;
        this.mModelUniformBottomResourceMapper = mModelUniformBottomResourceMapper;
    }

    /**
     * Save a mModelUniformBottomResource.
     *
     * @param mModelUniformBottomResourceDTO the entity to save.
     * @return the persisted entity.
     */
    public MModelUniformBottomResourceDTO save(MModelUniformBottomResourceDTO mModelUniformBottomResourceDTO) {
        log.debug("Request to save MModelUniformBottomResource : {}", mModelUniformBottomResourceDTO);
        MModelUniformBottomResource mModelUniformBottomResource = mModelUniformBottomResourceMapper.toEntity(mModelUniformBottomResourceDTO);
        mModelUniformBottomResource = mModelUniformBottomResourceRepository.save(mModelUniformBottomResource);
        return mModelUniformBottomResourceMapper.toDto(mModelUniformBottomResource);
    }

    /**
     * Get all the mModelUniformBottomResources.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MModelUniformBottomResourceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MModelUniformBottomResources");
        return mModelUniformBottomResourceRepository.findAll(pageable)
            .map(mModelUniformBottomResourceMapper::toDto);
    }


    /**
     * Get one mModelUniformBottomResource by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MModelUniformBottomResourceDTO> findOne(Long id) {
        log.debug("Request to get MModelUniformBottomResource : {}", id);
        return mModelUniformBottomResourceRepository.findById(id)
            .map(mModelUniformBottomResourceMapper::toDto);
    }

    /**
     * Delete the mModelUniformBottomResource by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MModelUniformBottomResource : {}", id);
        mModelUniformBottomResourceRepository.deleteById(id);
    }
}
