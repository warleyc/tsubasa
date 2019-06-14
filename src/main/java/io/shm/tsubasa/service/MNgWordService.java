package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MNgWord;
import io.shm.tsubasa.repository.MNgWordRepository;
import io.shm.tsubasa.service.dto.MNgWordDTO;
import io.shm.tsubasa.service.mapper.MNgWordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MNgWord}.
 */
@Service
@Transactional
public class MNgWordService {

    private final Logger log = LoggerFactory.getLogger(MNgWordService.class);

    private final MNgWordRepository mNgWordRepository;

    private final MNgWordMapper mNgWordMapper;

    public MNgWordService(MNgWordRepository mNgWordRepository, MNgWordMapper mNgWordMapper) {
        this.mNgWordRepository = mNgWordRepository;
        this.mNgWordMapper = mNgWordMapper;
    }

    /**
     * Save a mNgWord.
     *
     * @param mNgWordDTO the entity to save.
     * @return the persisted entity.
     */
    public MNgWordDTO save(MNgWordDTO mNgWordDTO) {
        log.debug("Request to save MNgWord : {}", mNgWordDTO);
        MNgWord mNgWord = mNgWordMapper.toEntity(mNgWordDTO);
        mNgWord = mNgWordRepository.save(mNgWord);
        return mNgWordMapper.toDto(mNgWord);
    }

    /**
     * Get all the mNgWords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MNgWordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MNgWords");
        return mNgWordRepository.findAll(pageable)
            .map(mNgWordMapper::toDto);
    }


    /**
     * Get one mNgWord by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MNgWordDTO> findOne(Long id) {
        log.debug("Request to get MNgWord : {}", id);
        return mNgWordRepository.findById(id)
            .map(mNgWordMapper::toDto);
    }

    /**
     * Delete the mNgWord by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MNgWord : {}", id);
        mNgWordRepository.deleteById(id);
    }
}
