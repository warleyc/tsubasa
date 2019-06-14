package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MModelUniformUpResource;
import io.shm.tsubasa.repository.MModelUniformUpResourceRepository;
import io.shm.tsubasa.service.dto.MModelUniformUpResourceDTO;
import io.shm.tsubasa.service.mapper.MModelUniformUpResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MModelUniformUpResource}.
 */
@Service
@Transactional
public class MModelUniformUpResourceService {

    private final Logger log = LoggerFactory.getLogger(MModelUniformUpResourceService.class);

    private final MModelUniformUpResourceRepository mModelUniformUpResourceRepository;

    private final MModelUniformUpResourceMapper mModelUniformUpResourceMapper;

    public MModelUniformUpResourceService(MModelUniformUpResourceRepository mModelUniformUpResourceRepository, MModelUniformUpResourceMapper mModelUniformUpResourceMapper) {
        this.mModelUniformUpResourceRepository = mModelUniformUpResourceRepository;
        this.mModelUniformUpResourceMapper = mModelUniformUpResourceMapper;
    }

    /**
     * Save a mModelUniformUpResource.
     *
     * @param mModelUniformUpResourceDTO the entity to save.
     * @return the persisted entity.
     */
    public MModelUniformUpResourceDTO save(MModelUniformUpResourceDTO mModelUniformUpResourceDTO) {
        log.debug("Request to save MModelUniformUpResource : {}", mModelUniformUpResourceDTO);
        MModelUniformUpResource mModelUniformUpResource = mModelUniformUpResourceMapper.toEntity(mModelUniformUpResourceDTO);
        mModelUniformUpResource = mModelUniformUpResourceRepository.save(mModelUniformUpResource);
        return mModelUniformUpResourceMapper.toDto(mModelUniformUpResource);
    }

    /**
     * Get all the mModelUniformUpResources.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MModelUniformUpResourceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MModelUniformUpResources");
        return mModelUniformUpResourceRepository.findAll(pageable)
            .map(mModelUniformUpResourceMapper::toDto);
    }


    /**
     * Get one mModelUniformUpResource by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MModelUniformUpResourceDTO> findOne(Long id) {
        log.debug("Request to get MModelUniformUpResource : {}", id);
        return mModelUniformUpResourceRepository.findById(id)
            .map(mModelUniformUpResourceMapper::toDto);
    }

    /**
     * Delete the mModelUniformUpResource by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MModelUniformUpResource : {}", id);
        mModelUniformUpResourceRepository.deleteById(id);
    }
}
