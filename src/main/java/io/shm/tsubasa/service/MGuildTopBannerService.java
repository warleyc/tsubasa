package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGuildTopBanner;
import io.shm.tsubasa.repository.MGuildTopBannerRepository;
import io.shm.tsubasa.service.dto.MGuildTopBannerDTO;
import io.shm.tsubasa.service.mapper.MGuildTopBannerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGuildTopBanner}.
 */
@Service
@Transactional
public class MGuildTopBannerService {

    private final Logger log = LoggerFactory.getLogger(MGuildTopBannerService.class);

    private final MGuildTopBannerRepository mGuildTopBannerRepository;

    private final MGuildTopBannerMapper mGuildTopBannerMapper;

    public MGuildTopBannerService(MGuildTopBannerRepository mGuildTopBannerRepository, MGuildTopBannerMapper mGuildTopBannerMapper) {
        this.mGuildTopBannerRepository = mGuildTopBannerRepository;
        this.mGuildTopBannerMapper = mGuildTopBannerMapper;
    }

    /**
     * Save a mGuildTopBanner.
     *
     * @param mGuildTopBannerDTO the entity to save.
     * @return the persisted entity.
     */
    public MGuildTopBannerDTO save(MGuildTopBannerDTO mGuildTopBannerDTO) {
        log.debug("Request to save MGuildTopBanner : {}", mGuildTopBannerDTO);
        MGuildTopBanner mGuildTopBanner = mGuildTopBannerMapper.toEntity(mGuildTopBannerDTO);
        mGuildTopBanner = mGuildTopBannerRepository.save(mGuildTopBanner);
        return mGuildTopBannerMapper.toDto(mGuildTopBanner);
    }

    /**
     * Get all the mGuildTopBanners.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuildTopBannerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGuildTopBanners");
        return mGuildTopBannerRepository.findAll(pageable)
            .map(mGuildTopBannerMapper::toDto);
    }


    /**
     * Get one mGuildTopBanner by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGuildTopBannerDTO> findOne(Long id) {
        log.debug("Request to get MGuildTopBanner : {}", id);
        return mGuildTopBannerRepository.findById(id)
            .map(mGuildTopBannerMapper::toDto);
    }

    /**
     * Delete the mGuildTopBanner by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGuildTopBanner : {}", id);
        mGuildTopBannerRepository.deleteById(id);
    }
}
