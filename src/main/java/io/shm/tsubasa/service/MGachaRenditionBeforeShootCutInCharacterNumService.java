package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGachaRenditionBeforeShootCutInCharacterNum;
import io.shm.tsubasa.repository.MGachaRenditionBeforeShootCutInCharacterNumRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionBeforeShootCutInCharacterNumDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionBeforeShootCutInCharacterNumMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGachaRenditionBeforeShootCutInCharacterNum}.
 */
@Service
@Transactional
public class MGachaRenditionBeforeShootCutInCharacterNumService {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionBeforeShootCutInCharacterNumService.class);

    private final MGachaRenditionBeforeShootCutInCharacterNumRepository mGachaRenditionBeforeShootCutInCharacterNumRepository;

    private final MGachaRenditionBeforeShootCutInCharacterNumMapper mGachaRenditionBeforeShootCutInCharacterNumMapper;

    public MGachaRenditionBeforeShootCutInCharacterNumService(MGachaRenditionBeforeShootCutInCharacterNumRepository mGachaRenditionBeforeShootCutInCharacterNumRepository, MGachaRenditionBeforeShootCutInCharacterNumMapper mGachaRenditionBeforeShootCutInCharacterNumMapper) {
        this.mGachaRenditionBeforeShootCutInCharacterNumRepository = mGachaRenditionBeforeShootCutInCharacterNumRepository;
        this.mGachaRenditionBeforeShootCutInCharacterNumMapper = mGachaRenditionBeforeShootCutInCharacterNumMapper;
    }

    /**
     * Save a mGachaRenditionBeforeShootCutInCharacterNum.
     *
     * @param mGachaRenditionBeforeShootCutInCharacterNumDTO the entity to save.
     * @return the persisted entity.
     */
    public MGachaRenditionBeforeShootCutInCharacterNumDTO save(MGachaRenditionBeforeShootCutInCharacterNumDTO mGachaRenditionBeforeShootCutInCharacterNumDTO) {
        log.debug("Request to save MGachaRenditionBeforeShootCutInCharacterNum : {}", mGachaRenditionBeforeShootCutInCharacterNumDTO);
        MGachaRenditionBeforeShootCutInCharacterNum mGachaRenditionBeforeShootCutInCharacterNum = mGachaRenditionBeforeShootCutInCharacterNumMapper.toEntity(mGachaRenditionBeforeShootCutInCharacterNumDTO);
        mGachaRenditionBeforeShootCutInCharacterNum = mGachaRenditionBeforeShootCutInCharacterNumRepository.save(mGachaRenditionBeforeShootCutInCharacterNum);
        return mGachaRenditionBeforeShootCutInCharacterNumMapper.toDto(mGachaRenditionBeforeShootCutInCharacterNum);
    }

    /**
     * Get all the mGachaRenditionBeforeShootCutInCharacterNums.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionBeforeShootCutInCharacterNumDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGachaRenditionBeforeShootCutInCharacterNums");
        return mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll(pageable)
            .map(mGachaRenditionBeforeShootCutInCharacterNumMapper::toDto);
    }


    /**
     * Get one mGachaRenditionBeforeShootCutInCharacterNum by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGachaRenditionBeforeShootCutInCharacterNumDTO> findOne(Long id) {
        log.debug("Request to get MGachaRenditionBeforeShootCutInCharacterNum : {}", id);
        return mGachaRenditionBeforeShootCutInCharacterNumRepository.findById(id)
            .map(mGachaRenditionBeforeShootCutInCharacterNumMapper::toDto);
    }

    /**
     * Delete the mGachaRenditionBeforeShootCutInCharacterNum by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGachaRenditionBeforeShootCutInCharacterNum : {}", id);
        mGachaRenditionBeforeShootCutInCharacterNumRepository.deleteById(id);
    }
}
