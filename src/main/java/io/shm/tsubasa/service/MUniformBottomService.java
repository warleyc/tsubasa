package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MUniformBottom;
import io.shm.tsubasa.repository.MUniformBottomRepository;
import io.shm.tsubasa.service.dto.MUniformBottomDTO;
import io.shm.tsubasa.service.mapper.MUniformBottomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MUniformBottom}.
 */
@Service
@Transactional
public class MUniformBottomService {

    private final Logger log = LoggerFactory.getLogger(MUniformBottomService.class);

    private final MUniformBottomRepository mUniformBottomRepository;

    private final MUniformBottomMapper mUniformBottomMapper;

    public MUniformBottomService(MUniformBottomRepository mUniformBottomRepository, MUniformBottomMapper mUniformBottomMapper) {
        this.mUniformBottomRepository = mUniformBottomRepository;
        this.mUniformBottomMapper = mUniformBottomMapper;
    }

    /**
     * Save a mUniformBottom.
     *
     * @param mUniformBottomDTO the entity to save.
     * @return the persisted entity.
     */
    public MUniformBottomDTO save(MUniformBottomDTO mUniformBottomDTO) {
        log.debug("Request to save MUniformBottom : {}", mUniformBottomDTO);
        MUniformBottom mUniformBottom = mUniformBottomMapper.toEntity(mUniformBottomDTO);
        mUniformBottom = mUniformBottomRepository.save(mUniformBottom);
        return mUniformBottomMapper.toDto(mUniformBottom);
    }

    /**
     * Get all the mUniformBottoms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MUniformBottomDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MUniformBottoms");
        return mUniformBottomRepository.findAll(pageable)
            .map(mUniformBottomMapper::toDto);
    }


    /**
     * Get one mUniformBottom by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MUniformBottomDTO> findOne(Long id) {
        log.debug("Request to get MUniformBottom : {}", id);
        return mUniformBottomRepository.findById(id)
            .map(mUniformBottomMapper::toDto);
    }

    /**
     * Delete the mUniformBottom by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MUniformBottom : {}", id);
        mUniformBottomRepository.deleteById(id);
    }
}
