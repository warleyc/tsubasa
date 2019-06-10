package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGachaRenditionFirstGimmick;
import io.shm.tsubasa.repository.MGachaRenditionFirstGimmickRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionFirstGimmickDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionFirstGimmickMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGachaRenditionFirstGimmick}.
 */
@Service
@Transactional
public class MGachaRenditionFirstGimmickService {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionFirstGimmickService.class);

    private final MGachaRenditionFirstGimmickRepository mGachaRenditionFirstGimmickRepository;

    private final MGachaRenditionFirstGimmickMapper mGachaRenditionFirstGimmickMapper;

    public MGachaRenditionFirstGimmickService(MGachaRenditionFirstGimmickRepository mGachaRenditionFirstGimmickRepository, MGachaRenditionFirstGimmickMapper mGachaRenditionFirstGimmickMapper) {
        this.mGachaRenditionFirstGimmickRepository = mGachaRenditionFirstGimmickRepository;
        this.mGachaRenditionFirstGimmickMapper = mGachaRenditionFirstGimmickMapper;
    }

    /**
     * Save a mGachaRenditionFirstGimmick.
     *
     * @param mGachaRenditionFirstGimmickDTO the entity to save.
     * @return the persisted entity.
     */
    public MGachaRenditionFirstGimmickDTO save(MGachaRenditionFirstGimmickDTO mGachaRenditionFirstGimmickDTO) {
        log.debug("Request to save MGachaRenditionFirstGimmick : {}", mGachaRenditionFirstGimmickDTO);
        MGachaRenditionFirstGimmick mGachaRenditionFirstGimmick = mGachaRenditionFirstGimmickMapper.toEntity(mGachaRenditionFirstGimmickDTO);
        mGachaRenditionFirstGimmick = mGachaRenditionFirstGimmickRepository.save(mGachaRenditionFirstGimmick);
        return mGachaRenditionFirstGimmickMapper.toDto(mGachaRenditionFirstGimmick);
    }

    /**
     * Get all the mGachaRenditionFirstGimmicks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionFirstGimmickDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGachaRenditionFirstGimmicks");
        return mGachaRenditionFirstGimmickRepository.findAll(pageable)
            .map(mGachaRenditionFirstGimmickMapper::toDto);
    }


    /**
     * Get one mGachaRenditionFirstGimmick by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGachaRenditionFirstGimmickDTO> findOne(Long id) {
        log.debug("Request to get MGachaRenditionFirstGimmick : {}", id);
        return mGachaRenditionFirstGimmickRepository.findById(id)
            .map(mGachaRenditionFirstGimmickMapper::toDto);
    }

    /**
     * Delete the mGachaRenditionFirstGimmick by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGachaRenditionFirstGimmick : {}", id);
        mGachaRenditionFirstGimmickRepository.deleteById(id);
    }
}
