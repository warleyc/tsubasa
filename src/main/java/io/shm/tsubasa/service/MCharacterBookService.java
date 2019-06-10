package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCharacterBook;
import io.shm.tsubasa.repository.MCharacterBookRepository;
import io.shm.tsubasa.service.dto.MCharacterBookDTO;
import io.shm.tsubasa.service.mapper.MCharacterBookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCharacterBook}.
 */
@Service
@Transactional
public class MCharacterBookService {

    private final Logger log = LoggerFactory.getLogger(MCharacterBookService.class);

    private final MCharacterBookRepository mCharacterBookRepository;

    private final MCharacterBookMapper mCharacterBookMapper;

    public MCharacterBookService(MCharacterBookRepository mCharacterBookRepository, MCharacterBookMapper mCharacterBookMapper) {
        this.mCharacterBookRepository = mCharacterBookRepository;
        this.mCharacterBookMapper = mCharacterBookMapper;
    }

    /**
     * Save a mCharacterBook.
     *
     * @param mCharacterBookDTO the entity to save.
     * @return the persisted entity.
     */
    public MCharacterBookDTO save(MCharacterBookDTO mCharacterBookDTO) {
        log.debug("Request to save MCharacterBook : {}", mCharacterBookDTO);
        MCharacterBook mCharacterBook = mCharacterBookMapper.toEntity(mCharacterBookDTO);
        mCharacterBook = mCharacterBookRepository.save(mCharacterBook);
        return mCharacterBookMapper.toDto(mCharacterBook);
    }

    /**
     * Get all the mCharacterBooks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCharacterBookDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCharacterBooks");
        return mCharacterBookRepository.findAll(pageable)
            .map(mCharacterBookMapper::toDto);
    }


    /**
     * Get one mCharacterBook by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCharacterBookDTO> findOne(Long id) {
        log.debug("Request to get MCharacterBook : {}", id);
        return mCharacterBookRepository.findById(id)
            .map(mCharacterBookMapper::toDto);
    }

    /**
     * Delete the mCharacterBook by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCharacterBook : {}", id);
        mCharacterBookRepository.deleteById(id);
    }
}
