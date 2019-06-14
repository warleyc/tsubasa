package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MModelUniformBottom;
import io.shm.tsubasa.repository.MModelUniformBottomRepository;
import io.shm.tsubasa.service.dto.MModelUniformBottomDTO;
import io.shm.tsubasa.service.mapper.MModelUniformBottomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MModelUniformBottom}.
 */
@Service
@Transactional
public class MModelUniformBottomService {

    private final Logger log = LoggerFactory.getLogger(MModelUniformBottomService.class);

    private final MModelUniformBottomRepository mModelUniformBottomRepository;

    private final MModelUniformBottomMapper mModelUniformBottomMapper;

    public MModelUniformBottomService(MModelUniformBottomRepository mModelUniformBottomRepository, MModelUniformBottomMapper mModelUniformBottomMapper) {
        this.mModelUniformBottomRepository = mModelUniformBottomRepository;
        this.mModelUniformBottomMapper = mModelUniformBottomMapper;
    }

    /**
     * Save a mModelUniformBottom.
     *
     * @param mModelUniformBottomDTO the entity to save.
     * @return the persisted entity.
     */
    public MModelUniformBottomDTO save(MModelUniformBottomDTO mModelUniformBottomDTO) {
        log.debug("Request to save MModelUniformBottom : {}", mModelUniformBottomDTO);
        MModelUniformBottom mModelUniformBottom = mModelUniformBottomMapper.toEntity(mModelUniformBottomDTO);
        mModelUniformBottom = mModelUniformBottomRepository.save(mModelUniformBottom);
        return mModelUniformBottomMapper.toDto(mModelUniformBottom);
    }

    /**
     * Get all the mModelUniformBottoms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MModelUniformBottomDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MModelUniformBottoms");
        return mModelUniformBottomRepository.findAll(pageable)
            .map(mModelUniformBottomMapper::toDto);
    }


    /**
     * Get one mModelUniformBottom by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MModelUniformBottomDTO> findOne(Long id) {
        log.debug("Request to get MModelUniformBottom : {}", id);
        return mModelUniformBottomRepository.findById(id)
            .map(mModelUniformBottomMapper::toDto);
    }

    /**
     * Delete the mModelUniformBottom by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MModelUniformBottom : {}", id);
        mModelUniformBottomRepository.deleteById(id);
    }
}
