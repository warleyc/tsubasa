package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MModelCard;
import io.shm.tsubasa.repository.MModelCardRepository;
import io.shm.tsubasa.service.dto.MModelCardDTO;
import io.shm.tsubasa.service.mapper.MModelCardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MModelCard}.
 */
@Service
@Transactional
public class MModelCardService {

    private final Logger log = LoggerFactory.getLogger(MModelCardService.class);

    private final MModelCardRepository mModelCardRepository;

    private final MModelCardMapper mModelCardMapper;

    public MModelCardService(MModelCardRepository mModelCardRepository, MModelCardMapper mModelCardMapper) {
        this.mModelCardRepository = mModelCardRepository;
        this.mModelCardMapper = mModelCardMapper;
    }

    /**
     * Save a mModelCard.
     *
     * @param mModelCardDTO the entity to save.
     * @return the persisted entity.
     */
    public MModelCardDTO save(MModelCardDTO mModelCardDTO) {
        log.debug("Request to save MModelCard : {}", mModelCardDTO);
        MModelCard mModelCard = mModelCardMapper.toEntity(mModelCardDTO);
        mModelCard = mModelCardRepository.save(mModelCard);
        return mModelCardMapper.toDto(mModelCard);
    }

    /**
     * Get all the mModelCards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MModelCardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MModelCards");
        return mModelCardRepository.findAll(pageable)
            .map(mModelCardMapper::toDto);
    }


    /**
     * Get one mModelCard by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MModelCardDTO> findOne(Long id) {
        log.debug("Request to get MModelCard : {}", id);
        return mModelCardRepository.findById(id)
            .map(mModelCardMapper::toDto);
    }

    /**
     * Delete the mModelCard by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MModelCard : {}", id);
        mModelCardRepository.deleteById(id);
    }
}
