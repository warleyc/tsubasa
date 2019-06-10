package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCharacter;
import io.shm.tsubasa.repository.MCharacterRepository;
import io.shm.tsubasa.service.dto.MCharacterDTO;
import io.shm.tsubasa.service.mapper.MCharacterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCharacter}.
 */
@Service
@Transactional
public class MCharacterService {

    private final Logger log = LoggerFactory.getLogger(MCharacterService.class);

    private final MCharacterRepository mCharacterRepository;

    private final MCharacterMapper mCharacterMapper;

    public MCharacterService(MCharacterRepository mCharacterRepository, MCharacterMapper mCharacterMapper) {
        this.mCharacterRepository = mCharacterRepository;
        this.mCharacterMapper = mCharacterMapper;
    }

    /**
     * Save a mCharacter.
     *
     * @param mCharacterDTO the entity to save.
     * @return the persisted entity.
     */
    public MCharacterDTO save(MCharacterDTO mCharacterDTO) {
        log.debug("Request to save MCharacter : {}", mCharacterDTO);
        MCharacter mCharacter = mCharacterMapper.toEntity(mCharacterDTO);
        mCharacter = mCharacterRepository.save(mCharacter);
        return mCharacterMapper.toDto(mCharacter);
    }

    /**
     * Get all the mCharacters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCharacterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCharacters");
        return mCharacterRepository.findAll(pageable)
            .map(mCharacterMapper::toDto);
    }


    /**
     * Get one mCharacter by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCharacterDTO> findOne(Long id) {
        log.debug("Request to get MCharacter : {}", id);
        return mCharacterRepository.findById(id)
            .map(mCharacterMapper::toDto);
    }

    /**
     * Delete the mCharacter by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCharacter : {}", id);
        mCharacterRepository.deleteById(id);
    }
}
