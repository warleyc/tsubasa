package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MRegulationMatchTutorialMessage;
import io.shm.tsubasa.repository.MRegulationMatchTutorialMessageRepository;
import io.shm.tsubasa.service.dto.MRegulationMatchTutorialMessageDTO;
import io.shm.tsubasa.service.mapper.MRegulationMatchTutorialMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MRegulationMatchTutorialMessage}.
 */
@Service
@Transactional
public class MRegulationMatchTutorialMessageService {

    private final Logger log = LoggerFactory.getLogger(MRegulationMatchTutorialMessageService.class);

    private final MRegulationMatchTutorialMessageRepository mRegulationMatchTutorialMessageRepository;

    private final MRegulationMatchTutorialMessageMapper mRegulationMatchTutorialMessageMapper;

    public MRegulationMatchTutorialMessageService(MRegulationMatchTutorialMessageRepository mRegulationMatchTutorialMessageRepository, MRegulationMatchTutorialMessageMapper mRegulationMatchTutorialMessageMapper) {
        this.mRegulationMatchTutorialMessageRepository = mRegulationMatchTutorialMessageRepository;
        this.mRegulationMatchTutorialMessageMapper = mRegulationMatchTutorialMessageMapper;
    }

    /**
     * Save a mRegulationMatchTutorialMessage.
     *
     * @param mRegulationMatchTutorialMessageDTO the entity to save.
     * @return the persisted entity.
     */
    public MRegulationMatchTutorialMessageDTO save(MRegulationMatchTutorialMessageDTO mRegulationMatchTutorialMessageDTO) {
        log.debug("Request to save MRegulationMatchTutorialMessage : {}", mRegulationMatchTutorialMessageDTO);
        MRegulationMatchTutorialMessage mRegulationMatchTutorialMessage = mRegulationMatchTutorialMessageMapper.toEntity(mRegulationMatchTutorialMessageDTO);
        mRegulationMatchTutorialMessage = mRegulationMatchTutorialMessageRepository.save(mRegulationMatchTutorialMessage);
        return mRegulationMatchTutorialMessageMapper.toDto(mRegulationMatchTutorialMessage);
    }

    /**
     * Get all the mRegulationMatchTutorialMessages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MRegulationMatchTutorialMessageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MRegulationMatchTutorialMessages");
        return mRegulationMatchTutorialMessageRepository.findAll(pageable)
            .map(mRegulationMatchTutorialMessageMapper::toDto);
    }


    /**
     * Get one mRegulationMatchTutorialMessage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MRegulationMatchTutorialMessageDTO> findOne(Long id) {
        log.debug("Request to get MRegulationMatchTutorialMessage : {}", id);
        return mRegulationMatchTutorialMessageRepository.findById(id)
            .map(mRegulationMatchTutorialMessageMapper::toDto);
    }

    /**
     * Delete the mRegulationMatchTutorialMessage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MRegulationMatchTutorialMessage : {}", id);
        mRegulationMatchTutorialMessageRepository.deleteById(id);
    }
}
