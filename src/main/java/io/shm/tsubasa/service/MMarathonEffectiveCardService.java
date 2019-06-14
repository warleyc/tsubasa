package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMarathonEffectiveCard;
import io.shm.tsubasa.repository.MMarathonEffectiveCardRepository;
import io.shm.tsubasa.service.dto.MMarathonEffectiveCardDTO;
import io.shm.tsubasa.service.mapper.MMarathonEffectiveCardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMarathonEffectiveCard}.
 */
@Service
@Transactional
public class MMarathonEffectiveCardService {

    private final Logger log = LoggerFactory.getLogger(MMarathonEffectiveCardService.class);

    private final MMarathonEffectiveCardRepository mMarathonEffectiveCardRepository;

    private final MMarathonEffectiveCardMapper mMarathonEffectiveCardMapper;

    public MMarathonEffectiveCardService(MMarathonEffectiveCardRepository mMarathonEffectiveCardRepository, MMarathonEffectiveCardMapper mMarathonEffectiveCardMapper) {
        this.mMarathonEffectiveCardRepository = mMarathonEffectiveCardRepository;
        this.mMarathonEffectiveCardMapper = mMarathonEffectiveCardMapper;
    }

    /**
     * Save a mMarathonEffectiveCard.
     *
     * @param mMarathonEffectiveCardDTO the entity to save.
     * @return the persisted entity.
     */
    public MMarathonEffectiveCardDTO save(MMarathonEffectiveCardDTO mMarathonEffectiveCardDTO) {
        log.debug("Request to save MMarathonEffectiveCard : {}", mMarathonEffectiveCardDTO);
        MMarathonEffectiveCard mMarathonEffectiveCard = mMarathonEffectiveCardMapper.toEntity(mMarathonEffectiveCardDTO);
        mMarathonEffectiveCard = mMarathonEffectiveCardRepository.save(mMarathonEffectiveCard);
        return mMarathonEffectiveCardMapper.toDto(mMarathonEffectiveCard);
    }

    /**
     * Get all the mMarathonEffectiveCards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonEffectiveCardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMarathonEffectiveCards");
        return mMarathonEffectiveCardRepository.findAll(pageable)
            .map(mMarathonEffectiveCardMapper::toDto);
    }


    /**
     * Get one mMarathonEffectiveCard by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMarathonEffectiveCardDTO> findOne(Long id) {
        log.debug("Request to get MMarathonEffectiveCard : {}", id);
        return mMarathonEffectiveCardRepository.findById(id)
            .map(mMarathonEffectiveCardMapper::toDto);
    }

    /**
     * Delete the mMarathonEffectiveCard by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMarathonEffectiveCard : {}", id);
        mMarathonEffectiveCardRepository.deleteById(id);
    }
}
