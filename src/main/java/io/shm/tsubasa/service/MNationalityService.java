package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MNationality;
import io.shm.tsubasa.repository.MNationalityRepository;
import io.shm.tsubasa.service.dto.MNationalityDTO;
import io.shm.tsubasa.service.mapper.MNationalityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MNationality}.
 */
@Service
@Transactional
public class MNationalityService {

    private final Logger log = LoggerFactory.getLogger(MNationalityService.class);

    private final MNationalityRepository mNationalityRepository;

    private final MNationalityMapper mNationalityMapper;

    public MNationalityService(MNationalityRepository mNationalityRepository, MNationalityMapper mNationalityMapper) {
        this.mNationalityRepository = mNationalityRepository;
        this.mNationalityMapper = mNationalityMapper;
    }

    /**
     * Save a mNationality.
     *
     * @param mNationalityDTO the entity to save.
     * @return the persisted entity.
     */
    public MNationalityDTO save(MNationalityDTO mNationalityDTO) {
        log.debug("Request to save MNationality : {}", mNationalityDTO);
        MNationality mNationality = mNationalityMapper.toEntity(mNationalityDTO);
        mNationality = mNationalityRepository.save(mNationality);
        return mNationalityMapper.toDto(mNationality);
    }

    /**
     * Get all the mNationalities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MNationalityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MNationalities");
        return mNationalityRepository.findAll(pageable)
            .map(mNationalityMapper::toDto);
    }


    /**
     * Get one mNationality by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MNationalityDTO> findOne(Long id) {
        log.debug("Request to get MNationality : {}", id);
        return mNationalityRepository.findById(id)
            .map(mNationalityMapper::toDto);
    }

    /**
     * Delete the mNationality by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MNationality : {}", id);
        mNationalityRepository.deleteById(id);
    }
}
