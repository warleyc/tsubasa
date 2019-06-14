package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MHomeMarquee;
import io.shm.tsubasa.repository.MHomeMarqueeRepository;
import io.shm.tsubasa.service.dto.MHomeMarqueeDTO;
import io.shm.tsubasa.service.mapper.MHomeMarqueeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MHomeMarquee}.
 */
@Service
@Transactional
public class MHomeMarqueeService {

    private final Logger log = LoggerFactory.getLogger(MHomeMarqueeService.class);

    private final MHomeMarqueeRepository mHomeMarqueeRepository;

    private final MHomeMarqueeMapper mHomeMarqueeMapper;

    public MHomeMarqueeService(MHomeMarqueeRepository mHomeMarqueeRepository, MHomeMarqueeMapper mHomeMarqueeMapper) {
        this.mHomeMarqueeRepository = mHomeMarqueeRepository;
        this.mHomeMarqueeMapper = mHomeMarqueeMapper;
    }

    /**
     * Save a mHomeMarquee.
     *
     * @param mHomeMarqueeDTO the entity to save.
     * @return the persisted entity.
     */
    public MHomeMarqueeDTO save(MHomeMarqueeDTO mHomeMarqueeDTO) {
        log.debug("Request to save MHomeMarquee : {}", mHomeMarqueeDTO);
        MHomeMarquee mHomeMarquee = mHomeMarqueeMapper.toEntity(mHomeMarqueeDTO);
        mHomeMarquee = mHomeMarqueeRepository.save(mHomeMarquee);
        return mHomeMarqueeMapper.toDto(mHomeMarquee);
    }

    /**
     * Get all the mHomeMarquees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MHomeMarqueeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MHomeMarquees");
        return mHomeMarqueeRepository.findAll(pageable)
            .map(mHomeMarqueeMapper::toDto);
    }


    /**
     * Get one mHomeMarquee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MHomeMarqueeDTO> findOne(Long id) {
        log.debug("Request to get MHomeMarquee : {}", id);
        return mHomeMarqueeRepository.findById(id)
            .map(mHomeMarqueeMapper::toDto);
    }

    /**
     * Delete the mHomeMarquee by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MHomeMarquee : {}", id);
        mHomeMarqueeRepository.deleteById(id);
    }
}
