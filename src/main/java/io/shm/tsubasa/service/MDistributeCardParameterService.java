package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDistributeCardParameter;
import io.shm.tsubasa.repository.MDistributeCardParameterRepository;
import io.shm.tsubasa.service.dto.MDistributeCardParameterDTO;
import io.shm.tsubasa.service.mapper.MDistributeCardParameterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDistributeCardParameter}.
 */
@Service
@Transactional
public class MDistributeCardParameterService {

    private final Logger log = LoggerFactory.getLogger(MDistributeCardParameterService.class);

    private final MDistributeCardParameterRepository mDistributeCardParameterRepository;

    private final MDistributeCardParameterMapper mDistributeCardParameterMapper;

    public MDistributeCardParameterService(MDistributeCardParameterRepository mDistributeCardParameterRepository, MDistributeCardParameterMapper mDistributeCardParameterMapper) {
        this.mDistributeCardParameterRepository = mDistributeCardParameterRepository;
        this.mDistributeCardParameterMapper = mDistributeCardParameterMapper;
    }

    /**
     * Save a mDistributeCardParameter.
     *
     * @param mDistributeCardParameterDTO the entity to save.
     * @return the persisted entity.
     */
    public MDistributeCardParameterDTO save(MDistributeCardParameterDTO mDistributeCardParameterDTO) {
        log.debug("Request to save MDistributeCardParameter : {}", mDistributeCardParameterDTO);
        MDistributeCardParameter mDistributeCardParameter = mDistributeCardParameterMapper.toEntity(mDistributeCardParameterDTO);
        mDistributeCardParameter = mDistributeCardParameterRepository.save(mDistributeCardParameter);
        return mDistributeCardParameterMapper.toDto(mDistributeCardParameter);
    }

    /**
     * Get all the mDistributeCardParameters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDistributeCardParameterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDistributeCardParameters");
        return mDistributeCardParameterRepository.findAll(pageable)
            .map(mDistributeCardParameterMapper::toDto);
    }


    /**
     * Get one mDistributeCardParameter by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDistributeCardParameterDTO> findOne(Long id) {
        log.debug("Request to get MDistributeCardParameter : {}", id);
        return mDistributeCardParameterRepository.findById(id)
            .map(mDistributeCardParameterMapper::toDto);
    }

    /**
     * Delete the mDistributeCardParameter by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDistributeCardParameter : {}", id);
        mDistributeCardParameterRepository.deleteById(id);
    }
}
