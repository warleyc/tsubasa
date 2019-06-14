package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MStamp;
import io.shm.tsubasa.repository.MStampRepository;
import io.shm.tsubasa.service.dto.MStampDTO;
import io.shm.tsubasa.service.mapper.MStampMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MStamp}.
 */
@Service
@Transactional
public class MStampService {

    private final Logger log = LoggerFactory.getLogger(MStampService.class);

    private final MStampRepository mStampRepository;

    private final MStampMapper mStampMapper;

    public MStampService(MStampRepository mStampRepository, MStampMapper mStampMapper) {
        this.mStampRepository = mStampRepository;
        this.mStampMapper = mStampMapper;
    }

    /**
     * Save a mStamp.
     *
     * @param mStampDTO the entity to save.
     * @return the persisted entity.
     */
    public MStampDTO save(MStampDTO mStampDTO) {
        log.debug("Request to save MStamp : {}", mStampDTO);
        MStamp mStamp = mStampMapper.toEntity(mStampDTO);
        mStamp = mStampRepository.save(mStamp);
        return mStampMapper.toDto(mStamp);
    }

    /**
     * Get all the mStamps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MStampDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MStamps");
        return mStampRepository.findAll(pageable)
            .map(mStampMapper::toDto);
    }


    /**
     * Get one mStamp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MStampDTO> findOne(Long id) {
        log.debug("Request to get MStamp : {}", id);
        return mStampRepository.findById(id)
            .map(mStampMapper::toDto);
    }

    /**
     * Delete the mStamp by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MStamp : {}", id);
        mStampRepository.deleteById(id);
    }
}
