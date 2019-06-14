package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MRecommendFormationFilterElement;
import io.shm.tsubasa.repository.MRecommendFormationFilterElementRepository;
import io.shm.tsubasa.service.dto.MRecommendFormationFilterElementDTO;
import io.shm.tsubasa.service.mapper.MRecommendFormationFilterElementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MRecommendFormationFilterElement}.
 */
@Service
@Transactional
public class MRecommendFormationFilterElementService {

    private final Logger log = LoggerFactory.getLogger(MRecommendFormationFilterElementService.class);

    private final MRecommendFormationFilterElementRepository mRecommendFormationFilterElementRepository;

    private final MRecommendFormationFilterElementMapper mRecommendFormationFilterElementMapper;

    public MRecommendFormationFilterElementService(MRecommendFormationFilterElementRepository mRecommendFormationFilterElementRepository, MRecommendFormationFilterElementMapper mRecommendFormationFilterElementMapper) {
        this.mRecommendFormationFilterElementRepository = mRecommendFormationFilterElementRepository;
        this.mRecommendFormationFilterElementMapper = mRecommendFormationFilterElementMapper;
    }

    /**
     * Save a mRecommendFormationFilterElement.
     *
     * @param mRecommendFormationFilterElementDTO the entity to save.
     * @return the persisted entity.
     */
    public MRecommendFormationFilterElementDTO save(MRecommendFormationFilterElementDTO mRecommendFormationFilterElementDTO) {
        log.debug("Request to save MRecommendFormationFilterElement : {}", mRecommendFormationFilterElementDTO);
        MRecommendFormationFilterElement mRecommendFormationFilterElement = mRecommendFormationFilterElementMapper.toEntity(mRecommendFormationFilterElementDTO);
        mRecommendFormationFilterElement = mRecommendFormationFilterElementRepository.save(mRecommendFormationFilterElement);
        return mRecommendFormationFilterElementMapper.toDto(mRecommendFormationFilterElement);
    }

    /**
     * Get all the mRecommendFormationFilterElements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MRecommendFormationFilterElementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MRecommendFormationFilterElements");
        return mRecommendFormationFilterElementRepository.findAll(pageable)
            .map(mRecommendFormationFilterElementMapper::toDto);
    }


    /**
     * Get one mRecommendFormationFilterElement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MRecommendFormationFilterElementDTO> findOne(Long id) {
        log.debug("Request to get MRecommendFormationFilterElement : {}", id);
        return mRecommendFormationFilterElementRepository.findById(id)
            .map(mRecommendFormationFilterElementMapper::toDto);
    }

    /**
     * Delete the mRecommendFormationFilterElement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MRecommendFormationFilterElement : {}", id);
        mRecommendFormationFilterElementRepository.deleteById(id);
    }
}
