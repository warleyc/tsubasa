package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGachaRenditionTrajectoryCutIn;
import io.shm.tsubasa.repository.MGachaRenditionTrajectoryCutInRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryCutInDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionTrajectoryCutInMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGachaRenditionTrajectoryCutIn}.
 */
@Service
@Transactional
public class MGachaRenditionTrajectoryCutInService {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionTrajectoryCutInService.class);

    private final MGachaRenditionTrajectoryCutInRepository mGachaRenditionTrajectoryCutInRepository;

    private final MGachaRenditionTrajectoryCutInMapper mGachaRenditionTrajectoryCutInMapper;

    public MGachaRenditionTrajectoryCutInService(MGachaRenditionTrajectoryCutInRepository mGachaRenditionTrajectoryCutInRepository, MGachaRenditionTrajectoryCutInMapper mGachaRenditionTrajectoryCutInMapper) {
        this.mGachaRenditionTrajectoryCutInRepository = mGachaRenditionTrajectoryCutInRepository;
        this.mGachaRenditionTrajectoryCutInMapper = mGachaRenditionTrajectoryCutInMapper;
    }

    /**
     * Save a mGachaRenditionTrajectoryCutIn.
     *
     * @param mGachaRenditionTrajectoryCutInDTO the entity to save.
     * @return the persisted entity.
     */
    public MGachaRenditionTrajectoryCutInDTO save(MGachaRenditionTrajectoryCutInDTO mGachaRenditionTrajectoryCutInDTO) {
        log.debug("Request to save MGachaRenditionTrajectoryCutIn : {}", mGachaRenditionTrajectoryCutInDTO);
        MGachaRenditionTrajectoryCutIn mGachaRenditionTrajectoryCutIn = mGachaRenditionTrajectoryCutInMapper.toEntity(mGachaRenditionTrajectoryCutInDTO);
        mGachaRenditionTrajectoryCutIn = mGachaRenditionTrajectoryCutInRepository.save(mGachaRenditionTrajectoryCutIn);
        return mGachaRenditionTrajectoryCutInMapper.toDto(mGachaRenditionTrajectoryCutIn);
    }

    /**
     * Get all the mGachaRenditionTrajectoryCutIns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionTrajectoryCutInDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGachaRenditionTrajectoryCutIns");
        return mGachaRenditionTrajectoryCutInRepository.findAll(pageable)
            .map(mGachaRenditionTrajectoryCutInMapper::toDto);
    }


    /**
     * Get one mGachaRenditionTrajectoryCutIn by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGachaRenditionTrajectoryCutInDTO> findOne(Long id) {
        log.debug("Request to get MGachaRenditionTrajectoryCutIn : {}", id);
        return mGachaRenditionTrajectoryCutInRepository.findById(id)
            .map(mGachaRenditionTrajectoryCutInMapper::toDto);
    }

    /**
     * Delete the mGachaRenditionTrajectoryCutIn by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGachaRenditionTrajectoryCutIn : {}", id);
        mGachaRenditionTrajectoryCutInRepository.deleteById(id);
    }
}
