package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGoalEffectiveCard;
import io.shm.tsubasa.repository.MGoalEffectiveCardRepository;
import io.shm.tsubasa.service.dto.MGoalEffectiveCardDTO;
import io.shm.tsubasa.service.mapper.MGoalEffectiveCardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGoalEffectiveCard}.
 */
@Service
@Transactional
public class MGoalEffectiveCardService {

    private final Logger log = LoggerFactory.getLogger(MGoalEffectiveCardService.class);

    private final MGoalEffectiveCardRepository mGoalEffectiveCardRepository;

    private final MGoalEffectiveCardMapper mGoalEffectiveCardMapper;

    public MGoalEffectiveCardService(MGoalEffectiveCardRepository mGoalEffectiveCardRepository, MGoalEffectiveCardMapper mGoalEffectiveCardMapper) {
        this.mGoalEffectiveCardRepository = mGoalEffectiveCardRepository;
        this.mGoalEffectiveCardMapper = mGoalEffectiveCardMapper;
    }

    /**
     * Save a mGoalEffectiveCard.
     *
     * @param mGoalEffectiveCardDTO the entity to save.
     * @return the persisted entity.
     */
    public MGoalEffectiveCardDTO save(MGoalEffectiveCardDTO mGoalEffectiveCardDTO) {
        log.debug("Request to save MGoalEffectiveCard : {}", mGoalEffectiveCardDTO);
        MGoalEffectiveCard mGoalEffectiveCard = mGoalEffectiveCardMapper.toEntity(mGoalEffectiveCardDTO);
        mGoalEffectiveCard = mGoalEffectiveCardRepository.save(mGoalEffectiveCard);
        return mGoalEffectiveCardMapper.toDto(mGoalEffectiveCard);
    }

    /**
     * Get all the mGoalEffectiveCards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGoalEffectiveCardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGoalEffectiveCards");
        return mGoalEffectiveCardRepository.findAll(pageable)
            .map(mGoalEffectiveCardMapper::toDto);
    }


    /**
     * Get one mGoalEffectiveCard by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGoalEffectiveCardDTO> findOne(Long id) {
        log.debug("Request to get MGoalEffectiveCard : {}", id);
        return mGoalEffectiveCardRepository.findById(id)
            .map(mGoalEffectiveCardMapper::toDto);
    }

    /**
     * Delete the mGoalEffectiveCard by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGoalEffectiveCard : {}", id);
        mGoalEffectiveCardRepository.deleteById(id);
    }
}
