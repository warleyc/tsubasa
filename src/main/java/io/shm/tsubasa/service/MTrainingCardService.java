package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTrainingCard;
import io.shm.tsubasa.repository.MTrainingCardRepository;
import io.shm.tsubasa.service.dto.MTrainingCardDTO;
import io.shm.tsubasa.service.mapper.MTrainingCardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTrainingCard}.
 */
@Service
@Transactional
public class MTrainingCardService {

    private final Logger log = LoggerFactory.getLogger(MTrainingCardService.class);

    private final MTrainingCardRepository mTrainingCardRepository;

    private final MTrainingCardMapper mTrainingCardMapper;

    public MTrainingCardService(MTrainingCardRepository mTrainingCardRepository, MTrainingCardMapper mTrainingCardMapper) {
        this.mTrainingCardRepository = mTrainingCardRepository;
        this.mTrainingCardMapper = mTrainingCardMapper;
    }

    /**
     * Save a mTrainingCard.
     *
     * @param mTrainingCardDTO the entity to save.
     * @return the persisted entity.
     */
    public MTrainingCardDTO save(MTrainingCardDTO mTrainingCardDTO) {
        log.debug("Request to save MTrainingCard : {}", mTrainingCardDTO);
        MTrainingCard mTrainingCard = mTrainingCardMapper.toEntity(mTrainingCardDTO);
        mTrainingCard = mTrainingCardRepository.save(mTrainingCard);
        return mTrainingCardMapper.toDto(mTrainingCard);
    }

    /**
     * Get all the mTrainingCards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTrainingCardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTrainingCards");
        return mTrainingCardRepository.findAll(pageable)
            .map(mTrainingCardMapper::toDto);
    }


    /**
     * Get one mTrainingCard by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTrainingCardDTO> findOne(Long id) {
        log.debug("Request to get MTrainingCard : {}", id);
        return mTrainingCardRepository.findById(id)
            .map(mTrainingCardMapper::toDto);
    }

    /**
     * Delete the mTrainingCard by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTrainingCard : {}", id);
        mTrainingCardRepository.deleteById(id);
    }
}
