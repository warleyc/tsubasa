package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGachaRenditionAfterInputCutIn;
import io.shm.tsubasa.repository.MGachaRenditionAfterInputCutInRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionAfterInputCutInDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionAfterInputCutInMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGachaRenditionAfterInputCutIn}.
 */
@Service
@Transactional
public class MGachaRenditionAfterInputCutInService {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionAfterInputCutInService.class);

    private final MGachaRenditionAfterInputCutInRepository mGachaRenditionAfterInputCutInRepository;

    private final MGachaRenditionAfterInputCutInMapper mGachaRenditionAfterInputCutInMapper;

    public MGachaRenditionAfterInputCutInService(MGachaRenditionAfterInputCutInRepository mGachaRenditionAfterInputCutInRepository, MGachaRenditionAfterInputCutInMapper mGachaRenditionAfterInputCutInMapper) {
        this.mGachaRenditionAfterInputCutInRepository = mGachaRenditionAfterInputCutInRepository;
        this.mGachaRenditionAfterInputCutInMapper = mGachaRenditionAfterInputCutInMapper;
    }

    /**
     * Save a mGachaRenditionAfterInputCutIn.
     *
     * @param mGachaRenditionAfterInputCutInDTO the entity to save.
     * @return the persisted entity.
     */
    public MGachaRenditionAfterInputCutInDTO save(MGachaRenditionAfterInputCutInDTO mGachaRenditionAfterInputCutInDTO) {
        log.debug("Request to save MGachaRenditionAfterInputCutIn : {}", mGachaRenditionAfterInputCutInDTO);
        MGachaRenditionAfterInputCutIn mGachaRenditionAfterInputCutIn = mGachaRenditionAfterInputCutInMapper.toEntity(mGachaRenditionAfterInputCutInDTO);
        mGachaRenditionAfterInputCutIn = mGachaRenditionAfterInputCutInRepository.save(mGachaRenditionAfterInputCutIn);
        return mGachaRenditionAfterInputCutInMapper.toDto(mGachaRenditionAfterInputCutIn);
    }

    /**
     * Get all the mGachaRenditionAfterInputCutIns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionAfterInputCutInDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGachaRenditionAfterInputCutIns");
        return mGachaRenditionAfterInputCutInRepository.findAll(pageable)
            .map(mGachaRenditionAfterInputCutInMapper::toDto);
    }


    /**
     * Get one mGachaRenditionAfterInputCutIn by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGachaRenditionAfterInputCutInDTO> findOne(Long id) {
        log.debug("Request to get MGachaRenditionAfterInputCutIn : {}", id);
        return mGachaRenditionAfterInputCutInRepository.findById(id)
            .map(mGachaRenditionAfterInputCutInMapper::toDto);
    }

    /**
     * Delete the mGachaRenditionAfterInputCutIn by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGachaRenditionAfterInputCutIn : {}", id);
        mGachaRenditionAfterInputCutInRepository.deleteById(id);
    }
}
