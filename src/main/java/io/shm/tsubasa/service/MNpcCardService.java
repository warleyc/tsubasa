package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MNpcCard;
import io.shm.tsubasa.repository.MNpcCardRepository;
import io.shm.tsubasa.service.dto.MNpcCardDTO;
import io.shm.tsubasa.service.mapper.MNpcCardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MNpcCard}.
 */
@Service
@Transactional
public class MNpcCardService {

    private final Logger log = LoggerFactory.getLogger(MNpcCardService.class);

    private final MNpcCardRepository mNpcCardRepository;

    private final MNpcCardMapper mNpcCardMapper;

    public MNpcCardService(MNpcCardRepository mNpcCardRepository, MNpcCardMapper mNpcCardMapper) {
        this.mNpcCardRepository = mNpcCardRepository;
        this.mNpcCardMapper = mNpcCardMapper;
    }

    /**
     * Save a mNpcCard.
     *
     * @param mNpcCardDTO the entity to save.
     * @return the persisted entity.
     */
    public MNpcCardDTO save(MNpcCardDTO mNpcCardDTO) {
        log.debug("Request to save MNpcCard : {}", mNpcCardDTO);
        MNpcCard mNpcCard = mNpcCardMapper.toEntity(mNpcCardDTO);
        mNpcCard = mNpcCardRepository.save(mNpcCard);
        return mNpcCardMapper.toDto(mNpcCard);
    }

    /**
     * Get all the mNpcCards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MNpcCardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MNpcCards");
        return mNpcCardRepository.findAll(pageable)
            .map(mNpcCardMapper::toDto);
    }


    /**
     * Get one mNpcCard by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MNpcCardDTO> findOne(Long id) {
        log.debug("Request to get MNpcCard : {}", id);
        return mNpcCardRepository.findById(id)
            .map(mNpcCardMapper::toDto);
    }

    /**
     * Delete the mNpcCard by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MNpcCard : {}", id);
        mNpcCardRepository.deleteById(id);
    }
}
