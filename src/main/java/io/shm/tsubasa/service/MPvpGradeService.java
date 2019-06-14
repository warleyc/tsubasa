package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MPvpGrade;
import io.shm.tsubasa.repository.MPvpGradeRepository;
import io.shm.tsubasa.service.dto.MPvpGradeDTO;
import io.shm.tsubasa.service.mapper.MPvpGradeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MPvpGrade}.
 */
@Service
@Transactional
public class MPvpGradeService {

    private final Logger log = LoggerFactory.getLogger(MPvpGradeService.class);

    private final MPvpGradeRepository mPvpGradeRepository;

    private final MPvpGradeMapper mPvpGradeMapper;

    public MPvpGradeService(MPvpGradeRepository mPvpGradeRepository, MPvpGradeMapper mPvpGradeMapper) {
        this.mPvpGradeRepository = mPvpGradeRepository;
        this.mPvpGradeMapper = mPvpGradeMapper;
    }

    /**
     * Save a mPvpGrade.
     *
     * @param mPvpGradeDTO the entity to save.
     * @return the persisted entity.
     */
    public MPvpGradeDTO save(MPvpGradeDTO mPvpGradeDTO) {
        log.debug("Request to save MPvpGrade : {}", mPvpGradeDTO);
        MPvpGrade mPvpGrade = mPvpGradeMapper.toEntity(mPvpGradeDTO);
        mPvpGrade = mPvpGradeRepository.save(mPvpGrade);
        return mPvpGradeMapper.toDto(mPvpGrade);
    }

    /**
     * Get all the mPvpGrades.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MPvpGradeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MPvpGrades");
        return mPvpGradeRepository.findAll(pageable)
            .map(mPvpGradeMapper::toDto);
    }


    /**
     * Get one mPvpGrade by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MPvpGradeDTO> findOne(Long id) {
        log.debug("Request to get MPvpGrade : {}", id);
        return mPvpGradeRepository.findById(id)
            .map(mPvpGradeMapper::toDto);
    }

    /**
     * Delete the mPvpGrade by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MPvpGrade : {}", id);
        mPvpGradeRepository.deleteById(id);
    }
}
