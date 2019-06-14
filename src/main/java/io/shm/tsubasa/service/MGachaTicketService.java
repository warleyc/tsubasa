package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGachaTicket;
import io.shm.tsubasa.repository.MGachaTicketRepository;
import io.shm.tsubasa.service.dto.MGachaTicketDTO;
import io.shm.tsubasa.service.mapper.MGachaTicketMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGachaTicket}.
 */
@Service
@Transactional
public class MGachaTicketService {

    private final Logger log = LoggerFactory.getLogger(MGachaTicketService.class);

    private final MGachaTicketRepository mGachaTicketRepository;

    private final MGachaTicketMapper mGachaTicketMapper;

    public MGachaTicketService(MGachaTicketRepository mGachaTicketRepository, MGachaTicketMapper mGachaTicketMapper) {
        this.mGachaTicketRepository = mGachaTicketRepository;
        this.mGachaTicketMapper = mGachaTicketMapper;
    }

    /**
     * Save a mGachaTicket.
     *
     * @param mGachaTicketDTO the entity to save.
     * @return the persisted entity.
     */
    public MGachaTicketDTO save(MGachaTicketDTO mGachaTicketDTO) {
        log.debug("Request to save MGachaTicket : {}", mGachaTicketDTO);
        MGachaTicket mGachaTicket = mGachaTicketMapper.toEntity(mGachaTicketDTO);
        mGachaTicket = mGachaTicketRepository.save(mGachaTicket);
        return mGachaTicketMapper.toDto(mGachaTicket);
    }

    /**
     * Get all the mGachaTickets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaTicketDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGachaTickets");
        return mGachaTicketRepository.findAll(pageable)
            .map(mGachaTicketMapper::toDto);
    }


    /**
     * Get one mGachaTicket by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGachaTicketDTO> findOne(Long id) {
        log.debug("Request to get MGachaTicket : {}", id);
        return mGachaTicketRepository.findById(id)
            .map(mGachaTicketMapper::toDto);
    }

    /**
     * Delete the mGachaTicket by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGachaTicket : {}", id);
        mGachaTicketRepository.deleteById(id);
    }
}
