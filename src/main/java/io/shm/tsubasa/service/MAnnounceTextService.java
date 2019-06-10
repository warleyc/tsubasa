package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MAnnounceText;
import io.shm.tsubasa.repository.MAnnounceTextRepository;
import io.shm.tsubasa.service.dto.MAnnounceTextDTO;
import io.shm.tsubasa.service.mapper.MAnnounceTextMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MAnnounceText}.
 */
@Service
@Transactional
public class MAnnounceTextService {

    private final Logger log = LoggerFactory.getLogger(MAnnounceTextService.class);

    private final MAnnounceTextRepository mAnnounceTextRepository;

    private final MAnnounceTextMapper mAnnounceTextMapper;

    public MAnnounceTextService(MAnnounceTextRepository mAnnounceTextRepository, MAnnounceTextMapper mAnnounceTextMapper) {
        this.mAnnounceTextRepository = mAnnounceTextRepository;
        this.mAnnounceTextMapper = mAnnounceTextMapper;
    }

    /**
     * Save a mAnnounceText.
     *
     * @param mAnnounceTextDTO the entity to save.
     * @return the persisted entity.
     */
    public MAnnounceTextDTO save(MAnnounceTextDTO mAnnounceTextDTO) {
        log.debug("Request to save MAnnounceText : {}", mAnnounceTextDTO);
        MAnnounceText mAnnounceText = mAnnounceTextMapper.toEntity(mAnnounceTextDTO);
        mAnnounceText = mAnnounceTextRepository.save(mAnnounceText);
        return mAnnounceTextMapper.toDto(mAnnounceText);
    }

    /**
     * Get all the mAnnounceTexts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MAnnounceTextDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MAnnounceTexts");
        return mAnnounceTextRepository.findAll(pageable)
            .map(mAnnounceTextMapper::toDto);
    }


    /**
     * Get one mAnnounceText by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MAnnounceTextDTO> findOne(Long id) {
        log.debug("Request to get MAnnounceText : {}", id);
        return mAnnounceTextRepository.findById(id)
            .map(mAnnounceTextMapper::toDto);
    }

    /**
     * Delete the mAnnounceText by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MAnnounceText : {}", id);
        mAnnounceTextRepository.deleteById(id);
    }
}
