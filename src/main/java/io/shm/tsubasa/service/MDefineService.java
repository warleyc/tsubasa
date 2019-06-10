package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDefine;
import io.shm.tsubasa.repository.MDefineRepository;
import io.shm.tsubasa.service.dto.MDefineDTO;
import io.shm.tsubasa.service.mapper.MDefineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDefine}.
 */
@Service
@Transactional
public class MDefineService {

    private final Logger log = LoggerFactory.getLogger(MDefineService.class);

    private final MDefineRepository mDefineRepository;

    private final MDefineMapper mDefineMapper;

    public MDefineService(MDefineRepository mDefineRepository, MDefineMapper mDefineMapper) {
        this.mDefineRepository = mDefineRepository;
        this.mDefineMapper = mDefineMapper;
    }

    /**
     * Save a mDefine.
     *
     * @param mDefineDTO the entity to save.
     * @return the persisted entity.
     */
    public MDefineDTO save(MDefineDTO mDefineDTO) {
        log.debug("Request to save MDefine : {}", mDefineDTO);
        MDefine mDefine = mDefineMapper.toEntity(mDefineDTO);
        mDefine = mDefineRepository.save(mDefine);
        return mDefineMapper.toDto(mDefine);
    }

    /**
     * Get all the mDefines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDefineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDefines");
        return mDefineRepository.findAll(pageable)
            .map(mDefineMapper::toDto);
    }


    /**
     * Get one mDefine by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDefineDTO> findOne(Long id) {
        log.debug("Request to get MDefine : {}", id);
        return mDefineRepository.findById(id)
            .map(mDefineMapper::toDto);
    }

    /**
     * Delete the mDefine by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDefine : {}", id);
        mDefineRepository.deleteById(id);
    }
}
