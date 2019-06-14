package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMatchOption;
import io.shm.tsubasa.repository.MMatchOptionRepository;
import io.shm.tsubasa.service.dto.MMatchOptionDTO;
import io.shm.tsubasa.service.mapper.MMatchOptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMatchOption}.
 */
@Service
@Transactional
public class MMatchOptionService {

    private final Logger log = LoggerFactory.getLogger(MMatchOptionService.class);

    private final MMatchOptionRepository mMatchOptionRepository;

    private final MMatchOptionMapper mMatchOptionMapper;

    public MMatchOptionService(MMatchOptionRepository mMatchOptionRepository, MMatchOptionMapper mMatchOptionMapper) {
        this.mMatchOptionRepository = mMatchOptionRepository;
        this.mMatchOptionMapper = mMatchOptionMapper;
    }

    /**
     * Save a mMatchOption.
     *
     * @param mMatchOptionDTO the entity to save.
     * @return the persisted entity.
     */
    public MMatchOptionDTO save(MMatchOptionDTO mMatchOptionDTO) {
        log.debug("Request to save MMatchOption : {}", mMatchOptionDTO);
        MMatchOption mMatchOption = mMatchOptionMapper.toEntity(mMatchOptionDTO);
        mMatchOption = mMatchOptionRepository.save(mMatchOption);
        return mMatchOptionMapper.toDto(mMatchOption);
    }

    /**
     * Get all the mMatchOptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMatchOptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMatchOptions");
        return mMatchOptionRepository.findAll(pageable)
            .map(mMatchOptionMapper::toDto);
    }


    /**
     * Get one mMatchOption by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMatchOptionDTO> findOne(Long id) {
        log.debug("Request to get MMatchOption : {}", id);
        return mMatchOptionRepository.findById(id)
            .map(mMatchOptionMapper::toDto);
    }

    /**
     * Delete the mMatchOption by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMatchOption : {}", id);
        mMatchOptionRepository.deleteById(id);
    }
}
