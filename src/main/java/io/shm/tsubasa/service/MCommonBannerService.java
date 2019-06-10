package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCommonBanner;
import io.shm.tsubasa.repository.MCommonBannerRepository;
import io.shm.tsubasa.service.dto.MCommonBannerDTO;
import io.shm.tsubasa.service.mapper.MCommonBannerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCommonBanner}.
 */
@Service
@Transactional
public class MCommonBannerService {

    private final Logger log = LoggerFactory.getLogger(MCommonBannerService.class);

    private final MCommonBannerRepository mCommonBannerRepository;

    private final MCommonBannerMapper mCommonBannerMapper;

    public MCommonBannerService(MCommonBannerRepository mCommonBannerRepository, MCommonBannerMapper mCommonBannerMapper) {
        this.mCommonBannerRepository = mCommonBannerRepository;
        this.mCommonBannerMapper = mCommonBannerMapper;
    }

    /**
     * Save a mCommonBanner.
     *
     * @param mCommonBannerDTO the entity to save.
     * @return the persisted entity.
     */
    public MCommonBannerDTO save(MCommonBannerDTO mCommonBannerDTO) {
        log.debug("Request to save MCommonBanner : {}", mCommonBannerDTO);
        MCommonBanner mCommonBanner = mCommonBannerMapper.toEntity(mCommonBannerDTO);
        mCommonBanner = mCommonBannerRepository.save(mCommonBanner);
        return mCommonBannerMapper.toDto(mCommonBanner);
    }

    /**
     * Get all the mCommonBanners.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCommonBannerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCommonBanners");
        return mCommonBannerRepository.findAll(pageable)
            .map(mCommonBannerMapper::toDto);
    }


    /**
     * Get one mCommonBanner by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCommonBannerDTO> findOne(Long id) {
        log.debug("Request to get MCommonBanner : {}", id);
        return mCommonBannerRepository.findById(id)
            .map(mCommonBannerMapper::toDto);
    }

    /**
     * Delete the mCommonBanner by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCommonBanner : {}", id);
        mCommonBannerRepository.deleteById(id);
    }
}
