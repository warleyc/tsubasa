package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MHomeBanner;
import io.shm.tsubasa.repository.MHomeBannerRepository;
import io.shm.tsubasa.service.dto.MHomeBannerDTO;
import io.shm.tsubasa.service.mapper.MHomeBannerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MHomeBanner}.
 */
@Service
@Transactional
public class MHomeBannerService {

    private final Logger log = LoggerFactory.getLogger(MHomeBannerService.class);

    private final MHomeBannerRepository mHomeBannerRepository;

    private final MHomeBannerMapper mHomeBannerMapper;

    public MHomeBannerService(MHomeBannerRepository mHomeBannerRepository, MHomeBannerMapper mHomeBannerMapper) {
        this.mHomeBannerRepository = mHomeBannerRepository;
        this.mHomeBannerMapper = mHomeBannerMapper;
    }

    /**
     * Save a mHomeBanner.
     *
     * @param mHomeBannerDTO the entity to save.
     * @return the persisted entity.
     */
    public MHomeBannerDTO save(MHomeBannerDTO mHomeBannerDTO) {
        log.debug("Request to save MHomeBanner : {}", mHomeBannerDTO);
        MHomeBanner mHomeBanner = mHomeBannerMapper.toEntity(mHomeBannerDTO);
        mHomeBanner = mHomeBannerRepository.save(mHomeBanner);
        return mHomeBannerMapper.toDto(mHomeBanner);
    }

    /**
     * Get all the mHomeBanners.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MHomeBannerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MHomeBanners");
        return mHomeBannerRepository.findAll(pageable)
            .map(mHomeBannerMapper::toDto);
    }


    /**
     * Get one mHomeBanner by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MHomeBannerDTO> findOne(Long id) {
        log.debug("Request to get MHomeBanner : {}", id);
        return mHomeBannerRepository.findById(id)
            .map(mHomeBannerMapper::toDto);
    }

    /**
     * Delete the mHomeBanner by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MHomeBanner : {}", id);
        mHomeBannerRepository.deleteById(id);
    }
}
