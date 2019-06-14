package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MQuestCoop;
import io.shm.tsubasa.repository.MQuestCoopRepository;
import io.shm.tsubasa.service.dto.MQuestCoopDTO;
import io.shm.tsubasa.service.mapper.MQuestCoopMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MQuestCoop}.
 */
@Service
@Transactional
public class MQuestCoopService {

    private final Logger log = LoggerFactory.getLogger(MQuestCoopService.class);

    private final MQuestCoopRepository mQuestCoopRepository;

    private final MQuestCoopMapper mQuestCoopMapper;

    public MQuestCoopService(MQuestCoopRepository mQuestCoopRepository, MQuestCoopMapper mQuestCoopMapper) {
        this.mQuestCoopRepository = mQuestCoopRepository;
        this.mQuestCoopMapper = mQuestCoopMapper;
    }

    /**
     * Save a mQuestCoop.
     *
     * @param mQuestCoopDTO the entity to save.
     * @return the persisted entity.
     */
    public MQuestCoopDTO save(MQuestCoopDTO mQuestCoopDTO) {
        log.debug("Request to save MQuestCoop : {}", mQuestCoopDTO);
        MQuestCoop mQuestCoop = mQuestCoopMapper.toEntity(mQuestCoopDTO);
        mQuestCoop = mQuestCoopRepository.save(mQuestCoop);
        return mQuestCoopMapper.toDto(mQuestCoop);
    }

    /**
     * Get all the mQuestCoops.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestCoopDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MQuestCoops");
        return mQuestCoopRepository.findAll(pageable)
            .map(mQuestCoopMapper::toDto);
    }


    /**
     * Get one mQuestCoop by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MQuestCoopDTO> findOne(Long id) {
        log.debug("Request to get MQuestCoop : {}", id);
        return mQuestCoopRepository.findById(id)
            .map(mQuestCoopMapper::toDto);
    }

    /**
     * Delete the mQuestCoop by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MQuestCoop : {}", id);
        mQuestCoopRepository.deleteById(id);
    }
}
