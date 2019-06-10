package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MExtraNews;
import io.shm.tsubasa.repository.MExtraNewsRepository;
import io.shm.tsubasa.service.dto.MExtraNewsDTO;
import io.shm.tsubasa.service.mapper.MExtraNewsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MExtraNews}.
 */
@Service
@Transactional
public class MExtraNewsService {

    private final Logger log = LoggerFactory.getLogger(MExtraNewsService.class);

    private final MExtraNewsRepository mExtraNewsRepository;

    private final MExtraNewsMapper mExtraNewsMapper;

    public MExtraNewsService(MExtraNewsRepository mExtraNewsRepository, MExtraNewsMapper mExtraNewsMapper) {
        this.mExtraNewsRepository = mExtraNewsRepository;
        this.mExtraNewsMapper = mExtraNewsMapper;
    }

    /**
     * Save a mExtraNews.
     *
     * @param mExtraNewsDTO the entity to save.
     * @return the persisted entity.
     */
    public MExtraNewsDTO save(MExtraNewsDTO mExtraNewsDTO) {
        log.debug("Request to save MExtraNews : {}", mExtraNewsDTO);
        MExtraNews mExtraNews = mExtraNewsMapper.toEntity(mExtraNewsDTO);
        mExtraNews = mExtraNewsRepository.save(mExtraNews);
        return mExtraNewsMapper.toDto(mExtraNews);
    }

    /**
     * Get all the mExtraNews.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MExtraNewsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MExtraNews");
        return mExtraNewsRepository.findAll(pageable)
            .map(mExtraNewsMapper::toDto);
    }


    /**
     * Get one mExtraNews by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MExtraNewsDTO> findOne(Long id) {
        log.debug("Request to get MExtraNews : {}", id);
        return mExtraNewsRepository.findById(id)
            .map(mExtraNewsMapper::toDto);
    }

    /**
     * Delete the mExtraNews by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MExtraNews : {}", id);
        mExtraNewsRepository.deleteById(id);
    }
}
