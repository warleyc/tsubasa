package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MRecommendShopMessage;
import io.shm.tsubasa.repository.MRecommendShopMessageRepository;
import io.shm.tsubasa.service.dto.MRecommendShopMessageDTO;
import io.shm.tsubasa.service.mapper.MRecommendShopMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MRecommendShopMessage}.
 */
@Service
@Transactional
public class MRecommendShopMessageService {

    private final Logger log = LoggerFactory.getLogger(MRecommendShopMessageService.class);

    private final MRecommendShopMessageRepository mRecommendShopMessageRepository;

    private final MRecommendShopMessageMapper mRecommendShopMessageMapper;

    public MRecommendShopMessageService(MRecommendShopMessageRepository mRecommendShopMessageRepository, MRecommendShopMessageMapper mRecommendShopMessageMapper) {
        this.mRecommendShopMessageRepository = mRecommendShopMessageRepository;
        this.mRecommendShopMessageMapper = mRecommendShopMessageMapper;
    }

    /**
     * Save a mRecommendShopMessage.
     *
     * @param mRecommendShopMessageDTO the entity to save.
     * @return the persisted entity.
     */
    public MRecommendShopMessageDTO save(MRecommendShopMessageDTO mRecommendShopMessageDTO) {
        log.debug("Request to save MRecommendShopMessage : {}", mRecommendShopMessageDTO);
        MRecommendShopMessage mRecommendShopMessage = mRecommendShopMessageMapper.toEntity(mRecommendShopMessageDTO);
        mRecommendShopMessage = mRecommendShopMessageRepository.save(mRecommendShopMessage);
        return mRecommendShopMessageMapper.toDto(mRecommendShopMessage);
    }

    /**
     * Get all the mRecommendShopMessages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MRecommendShopMessageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MRecommendShopMessages");
        return mRecommendShopMessageRepository.findAll(pageable)
            .map(mRecommendShopMessageMapper::toDto);
    }


    /**
     * Get one mRecommendShopMessage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MRecommendShopMessageDTO> findOne(Long id) {
        log.debug("Request to get MRecommendShopMessage : {}", id);
        return mRecommendShopMessageRepository.findById(id)
            .map(mRecommendShopMessageMapper::toDto);
    }

    /**
     * Delete the mRecommendShopMessage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MRecommendShopMessage : {}", id);
        mRecommendShopMessageRepository.deleteById(id);
    }
}
