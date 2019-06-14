package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MQuestTicket;
import io.shm.tsubasa.repository.MQuestTicketRepository;
import io.shm.tsubasa.service.dto.MQuestTicketDTO;
import io.shm.tsubasa.service.mapper.MQuestTicketMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MQuestTicket}.
 */
@Service
@Transactional
public class MQuestTicketService {

    private final Logger log = LoggerFactory.getLogger(MQuestTicketService.class);

    private final MQuestTicketRepository mQuestTicketRepository;

    private final MQuestTicketMapper mQuestTicketMapper;

    public MQuestTicketService(MQuestTicketRepository mQuestTicketRepository, MQuestTicketMapper mQuestTicketMapper) {
        this.mQuestTicketRepository = mQuestTicketRepository;
        this.mQuestTicketMapper = mQuestTicketMapper;
    }

    /**
     * Save a mQuestTicket.
     *
     * @param mQuestTicketDTO the entity to save.
     * @return the persisted entity.
     */
    public MQuestTicketDTO save(MQuestTicketDTO mQuestTicketDTO) {
        log.debug("Request to save MQuestTicket : {}", mQuestTicketDTO);
        MQuestTicket mQuestTicket = mQuestTicketMapper.toEntity(mQuestTicketDTO);
        mQuestTicket = mQuestTicketRepository.save(mQuestTicket);
        return mQuestTicketMapper.toDto(mQuestTicket);
    }

    /**
     * Get all the mQuestTickets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestTicketDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MQuestTickets");
        return mQuestTicketRepository.findAll(pageable)
            .map(mQuestTicketMapper::toDto);
    }


    /**
     * Get one mQuestTicket by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MQuestTicketDTO> findOne(Long id) {
        log.debug("Request to get MQuestTicket : {}", id);
        return mQuestTicketRepository.findById(id)
            .map(mQuestTicketMapper::toDto);
    }

    /**
     * Delete the mQuestTicket by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MQuestTicket : {}", id);
        mQuestTicketRepository.deleteById(id);
    }
}
