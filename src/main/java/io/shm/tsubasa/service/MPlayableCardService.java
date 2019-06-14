package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MPlayableCard;
import io.shm.tsubasa.repository.MPlayableCardRepository;
import io.shm.tsubasa.service.dto.MPlayableCardDTO;
import io.shm.tsubasa.service.mapper.MPlayableCardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MPlayableCard}.
 */
@Service
@Transactional
public class MPlayableCardService {

    private final Logger log = LoggerFactory.getLogger(MPlayableCardService.class);

    private final MPlayableCardRepository mPlayableCardRepository;

    private final MPlayableCardMapper mPlayableCardMapper;

    public MPlayableCardService(MPlayableCardRepository mPlayableCardRepository, MPlayableCardMapper mPlayableCardMapper) {
        this.mPlayableCardRepository = mPlayableCardRepository;
        this.mPlayableCardMapper = mPlayableCardMapper;
    }

    /**
     * Save a mPlayableCard.
     *
     * @param mPlayableCardDTO the entity to save.
     * @return the persisted entity.
     */
    public MPlayableCardDTO save(MPlayableCardDTO mPlayableCardDTO) {
        log.debug("Request to save MPlayableCard : {}", mPlayableCardDTO);
        MPlayableCard mPlayableCard = mPlayableCardMapper.toEntity(mPlayableCardDTO);
        mPlayableCard = mPlayableCardRepository.save(mPlayableCard);
        return mPlayableCardMapper.toDto(mPlayableCard);
    }

    /**
     * Get all the mPlayableCards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MPlayableCardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MPlayableCards");
        return mPlayableCardRepository.findAll(pageable)
            .map(mPlayableCardMapper::toDto);
    }


    /**
     * Get one mPlayableCard by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MPlayableCardDTO> findOne(Long id) {
        log.debug("Request to get MPlayableCard : {}", id);
        return mPlayableCardRepository.findById(id)
            .map(mPlayableCardMapper::toDto);
    }

    /**
     * Delete the mPlayableCard by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MPlayableCard : {}", id);
        mPlayableCardRepository.deleteById(id);
    }
}
