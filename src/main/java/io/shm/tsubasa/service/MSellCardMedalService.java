package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MSellCardMedal;
import io.shm.tsubasa.repository.MSellCardMedalRepository;
import io.shm.tsubasa.service.dto.MSellCardMedalDTO;
import io.shm.tsubasa.service.mapper.MSellCardMedalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MSellCardMedal}.
 */
@Service
@Transactional
public class MSellCardMedalService {

    private final Logger log = LoggerFactory.getLogger(MSellCardMedalService.class);

    private final MSellCardMedalRepository mSellCardMedalRepository;

    private final MSellCardMedalMapper mSellCardMedalMapper;

    public MSellCardMedalService(MSellCardMedalRepository mSellCardMedalRepository, MSellCardMedalMapper mSellCardMedalMapper) {
        this.mSellCardMedalRepository = mSellCardMedalRepository;
        this.mSellCardMedalMapper = mSellCardMedalMapper;
    }

    /**
     * Save a mSellCardMedal.
     *
     * @param mSellCardMedalDTO the entity to save.
     * @return the persisted entity.
     */
    public MSellCardMedalDTO save(MSellCardMedalDTO mSellCardMedalDTO) {
        log.debug("Request to save MSellCardMedal : {}", mSellCardMedalDTO);
        MSellCardMedal mSellCardMedal = mSellCardMedalMapper.toEntity(mSellCardMedalDTO);
        mSellCardMedal = mSellCardMedalRepository.save(mSellCardMedal);
        return mSellCardMedalMapper.toDto(mSellCardMedal);
    }

    /**
     * Get all the mSellCardMedals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MSellCardMedalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MSellCardMedals");
        return mSellCardMedalRepository.findAll(pageable)
            .map(mSellCardMedalMapper::toDto);
    }


    /**
     * Get one mSellCardMedal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MSellCardMedalDTO> findOne(Long id) {
        log.debug("Request to get MSellCardMedal : {}", id);
        return mSellCardMedalRepository.findById(id)
            .map(mSellCardMedalMapper::toDto);
    }

    /**
     * Delete the mSellCardMedal by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MSellCardMedal : {}", id);
        mSellCardMedalRepository.deleteById(id);
    }
}
