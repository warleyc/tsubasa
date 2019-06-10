package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MContentableCard;
import io.shm.tsubasa.repository.MContentableCardRepository;
import io.shm.tsubasa.service.dto.MContentableCardDTO;
import io.shm.tsubasa.service.mapper.MContentableCardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MContentableCard}.
 */
@Service
@Transactional
public class MContentableCardService {

    private final Logger log = LoggerFactory.getLogger(MContentableCardService.class);

    private final MContentableCardRepository mContentableCardRepository;

    private final MContentableCardMapper mContentableCardMapper;

    public MContentableCardService(MContentableCardRepository mContentableCardRepository, MContentableCardMapper mContentableCardMapper) {
        this.mContentableCardRepository = mContentableCardRepository;
        this.mContentableCardMapper = mContentableCardMapper;
    }

    /**
     * Save a mContentableCard.
     *
     * @param mContentableCardDTO the entity to save.
     * @return the persisted entity.
     */
    public MContentableCardDTO save(MContentableCardDTO mContentableCardDTO) {
        log.debug("Request to save MContentableCard : {}", mContentableCardDTO);
        MContentableCard mContentableCard = mContentableCardMapper.toEntity(mContentableCardDTO);
        mContentableCard = mContentableCardRepository.save(mContentableCard);
        return mContentableCardMapper.toDto(mContentableCard);
    }

    /**
     * Get all the mContentableCards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MContentableCardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MContentableCards");
        return mContentableCardRepository.findAll(pageable)
            .map(mContentableCardMapper::toDto);
    }


    /**
     * Get one mContentableCard by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MContentableCardDTO> findOne(Long id) {
        log.debug("Request to get MContentableCard : {}", id);
        return mContentableCardRepository.findById(id)
            .map(mContentableCardMapper::toDto);
    }

    /**
     * Delete the mContentableCard by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MContentableCard : {}", id);
        mContentableCardRepository.deleteById(id);
    }
}
