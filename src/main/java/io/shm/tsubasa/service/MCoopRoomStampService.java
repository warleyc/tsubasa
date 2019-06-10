package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCoopRoomStamp;
import io.shm.tsubasa.repository.MCoopRoomStampRepository;
import io.shm.tsubasa.service.dto.MCoopRoomStampDTO;
import io.shm.tsubasa.service.mapper.MCoopRoomStampMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCoopRoomStamp}.
 */
@Service
@Transactional
public class MCoopRoomStampService {

    private final Logger log = LoggerFactory.getLogger(MCoopRoomStampService.class);

    private final MCoopRoomStampRepository mCoopRoomStampRepository;

    private final MCoopRoomStampMapper mCoopRoomStampMapper;

    public MCoopRoomStampService(MCoopRoomStampRepository mCoopRoomStampRepository, MCoopRoomStampMapper mCoopRoomStampMapper) {
        this.mCoopRoomStampRepository = mCoopRoomStampRepository;
        this.mCoopRoomStampMapper = mCoopRoomStampMapper;
    }

    /**
     * Save a mCoopRoomStamp.
     *
     * @param mCoopRoomStampDTO the entity to save.
     * @return the persisted entity.
     */
    public MCoopRoomStampDTO save(MCoopRoomStampDTO mCoopRoomStampDTO) {
        log.debug("Request to save MCoopRoomStamp : {}", mCoopRoomStampDTO);
        MCoopRoomStamp mCoopRoomStamp = mCoopRoomStampMapper.toEntity(mCoopRoomStampDTO);
        mCoopRoomStamp = mCoopRoomStampRepository.save(mCoopRoomStamp);
        return mCoopRoomStampMapper.toDto(mCoopRoomStamp);
    }

    /**
     * Get all the mCoopRoomStamps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCoopRoomStampDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCoopRoomStamps");
        return mCoopRoomStampRepository.findAll(pageable)
            .map(mCoopRoomStampMapper::toDto);
    }


    /**
     * Get one mCoopRoomStamp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCoopRoomStampDTO> findOne(Long id) {
        log.debug("Request to get MCoopRoomStamp : {}", id);
        return mCoopRoomStampRepository.findById(id)
            .map(mCoopRoomStampMapper::toDto);
    }

    /**
     * Delete the mCoopRoomStamp by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCoopRoomStamp : {}", id);
        mCoopRoomStampRepository.deleteById(id);
    }
}
