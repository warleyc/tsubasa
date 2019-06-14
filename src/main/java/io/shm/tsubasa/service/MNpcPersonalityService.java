package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MNpcPersonality;
import io.shm.tsubasa.repository.MNpcPersonalityRepository;
import io.shm.tsubasa.service.dto.MNpcPersonalityDTO;
import io.shm.tsubasa.service.mapper.MNpcPersonalityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MNpcPersonality}.
 */
@Service
@Transactional
public class MNpcPersonalityService {

    private final Logger log = LoggerFactory.getLogger(MNpcPersonalityService.class);

    private final MNpcPersonalityRepository mNpcPersonalityRepository;

    private final MNpcPersonalityMapper mNpcPersonalityMapper;

    public MNpcPersonalityService(MNpcPersonalityRepository mNpcPersonalityRepository, MNpcPersonalityMapper mNpcPersonalityMapper) {
        this.mNpcPersonalityRepository = mNpcPersonalityRepository;
        this.mNpcPersonalityMapper = mNpcPersonalityMapper;
    }

    /**
     * Save a mNpcPersonality.
     *
     * @param mNpcPersonalityDTO the entity to save.
     * @return the persisted entity.
     */
    public MNpcPersonalityDTO save(MNpcPersonalityDTO mNpcPersonalityDTO) {
        log.debug("Request to save MNpcPersonality : {}", mNpcPersonalityDTO);
        MNpcPersonality mNpcPersonality = mNpcPersonalityMapper.toEntity(mNpcPersonalityDTO);
        mNpcPersonality = mNpcPersonalityRepository.save(mNpcPersonality);
        return mNpcPersonalityMapper.toDto(mNpcPersonality);
    }

    /**
     * Get all the mNpcPersonalities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MNpcPersonalityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MNpcPersonalities");
        return mNpcPersonalityRepository.findAll(pageable)
            .map(mNpcPersonalityMapper::toDto);
    }


    /**
     * Get one mNpcPersonality by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MNpcPersonalityDTO> findOne(Long id) {
        log.debug("Request to get MNpcPersonality : {}", id);
        return mNpcPersonalityRepository.findById(id)
            .map(mNpcPersonalityMapper::toDto);
    }

    /**
     * Delete the mNpcPersonality by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MNpcPersonality : {}", id);
        mNpcPersonalityRepository.deleteById(id);
    }
}
