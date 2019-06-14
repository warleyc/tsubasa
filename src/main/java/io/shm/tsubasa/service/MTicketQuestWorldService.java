package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTicketQuestWorld;
import io.shm.tsubasa.repository.MTicketQuestWorldRepository;
import io.shm.tsubasa.service.dto.MTicketQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MTicketQuestWorldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTicketQuestWorld}.
 */
@Service
@Transactional
public class MTicketQuestWorldService {

    private final Logger log = LoggerFactory.getLogger(MTicketQuestWorldService.class);

    private final MTicketQuestWorldRepository mTicketQuestWorldRepository;

    private final MTicketQuestWorldMapper mTicketQuestWorldMapper;

    public MTicketQuestWorldService(MTicketQuestWorldRepository mTicketQuestWorldRepository, MTicketQuestWorldMapper mTicketQuestWorldMapper) {
        this.mTicketQuestWorldRepository = mTicketQuestWorldRepository;
        this.mTicketQuestWorldMapper = mTicketQuestWorldMapper;
    }

    /**
     * Save a mTicketQuestWorld.
     *
     * @param mTicketQuestWorldDTO the entity to save.
     * @return the persisted entity.
     */
    public MTicketQuestWorldDTO save(MTicketQuestWorldDTO mTicketQuestWorldDTO) {
        log.debug("Request to save MTicketQuestWorld : {}", mTicketQuestWorldDTO);
        MTicketQuestWorld mTicketQuestWorld = mTicketQuestWorldMapper.toEntity(mTicketQuestWorldDTO);
        mTicketQuestWorld = mTicketQuestWorldRepository.save(mTicketQuestWorld);
        return mTicketQuestWorldMapper.toDto(mTicketQuestWorld);
    }

    /**
     * Get all the mTicketQuestWorlds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTicketQuestWorldDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTicketQuestWorlds");
        return mTicketQuestWorldRepository.findAll(pageable)
            .map(mTicketQuestWorldMapper::toDto);
    }


    /**
     * Get one mTicketQuestWorld by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTicketQuestWorldDTO> findOne(Long id) {
        log.debug("Request to get MTicketQuestWorld : {}", id);
        return mTicketQuestWorldRepository.findById(id)
            .map(mTicketQuestWorldMapper::toDto);
    }

    /**
     * Delete the mTicketQuestWorld by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTicketQuestWorld : {}", id);
        mTicketQuestWorldRepository.deleteById(id);
    }
}
