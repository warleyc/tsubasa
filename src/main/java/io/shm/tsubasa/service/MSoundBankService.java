package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MSoundBank;
import io.shm.tsubasa.repository.MSoundBankRepository;
import io.shm.tsubasa.service.dto.MSoundBankDTO;
import io.shm.tsubasa.service.mapper.MSoundBankMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MSoundBank}.
 */
@Service
@Transactional
public class MSoundBankService {

    private final Logger log = LoggerFactory.getLogger(MSoundBankService.class);

    private final MSoundBankRepository mSoundBankRepository;

    private final MSoundBankMapper mSoundBankMapper;

    public MSoundBankService(MSoundBankRepository mSoundBankRepository, MSoundBankMapper mSoundBankMapper) {
        this.mSoundBankRepository = mSoundBankRepository;
        this.mSoundBankMapper = mSoundBankMapper;
    }

    /**
     * Save a mSoundBank.
     *
     * @param mSoundBankDTO the entity to save.
     * @return the persisted entity.
     */
    public MSoundBankDTO save(MSoundBankDTO mSoundBankDTO) {
        log.debug("Request to save MSoundBank : {}", mSoundBankDTO);
        MSoundBank mSoundBank = mSoundBankMapper.toEntity(mSoundBankDTO);
        mSoundBank = mSoundBankRepository.save(mSoundBank);
        return mSoundBankMapper.toDto(mSoundBank);
    }

    /**
     * Get all the mSoundBanks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MSoundBankDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MSoundBanks");
        return mSoundBankRepository.findAll(pageable)
            .map(mSoundBankMapper::toDto);
    }


    /**
     * Get one mSoundBank by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MSoundBankDTO> findOne(Long id) {
        log.debug("Request to get MSoundBank : {}", id);
        return mSoundBankRepository.findById(id)
            .map(mSoundBankMapper::toDto);
    }

    /**
     * Delete the mSoundBank by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MSoundBank : {}", id);
        mSoundBankRepository.deleteById(id);
    }
}
