package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMarathonBoostSchedule;
import io.shm.tsubasa.repository.MMarathonBoostScheduleRepository;
import io.shm.tsubasa.service.dto.MMarathonBoostScheduleDTO;
import io.shm.tsubasa.service.mapper.MMarathonBoostScheduleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMarathonBoostSchedule}.
 */
@Service
@Transactional
public class MMarathonBoostScheduleService {

    private final Logger log = LoggerFactory.getLogger(MMarathonBoostScheduleService.class);

    private final MMarathonBoostScheduleRepository mMarathonBoostScheduleRepository;

    private final MMarathonBoostScheduleMapper mMarathonBoostScheduleMapper;

    public MMarathonBoostScheduleService(MMarathonBoostScheduleRepository mMarathonBoostScheduleRepository, MMarathonBoostScheduleMapper mMarathonBoostScheduleMapper) {
        this.mMarathonBoostScheduleRepository = mMarathonBoostScheduleRepository;
        this.mMarathonBoostScheduleMapper = mMarathonBoostScheduleMapper;
    }

    /**
     * Save a mMarathonBoostSchedule.
     *
     * @param mMarathonBoostScheduleDTO the entity to save.
     * @return the persisted entity.
     */
    public MMarathonBoostScheduleDTO save(MMarathonBoostScheduleDTO mMarathonBoostScheduleDTO) {
        log.debug("Request to save MMarathonBoostSchedule : {}", mMarathonBoostScheduleDTO);
        MMarathonBoostSchedule mMarathonBoostSchedule = mMarathonBoostScheduleMapper.toEntity(mMarathonBoostScheduleDTO);
        mMarathonBoostSchedule = mMarathonBoostScheduleRepository.save(mMarathonBoostSchedule);
        return mMarathonBoostScheduleMapper.toDto(mMarathonBoostSchedule);
    }

    /**
     * Get all the mMarathonBoostSchedules.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonBoostScheduleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMarathonBoostSchedules");
        return mMarathonBoostScheduleRepository.findAll(pageable)
            .map(mMarathonBoostScheduleMapper::toDto);
    }


    /**
     * Get one mMarathonBoostSchedule by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMarathonBoostScheduleDTO> findOne(Long id) {
        log.debug("Request to get MMarathonBoostSchedule : {}", id);
        return mMarathonBoostScheduleRepository.findById(id)
            .map(mMarathonBoostScheduleMapper::toDto);
    }

    /**
     * Delete the mMarathonBoostSchedule by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMarathonBoostSchedule : {}", id);
        mMarathonBoostScheduleRepository.deleteById(id);
    }
}
