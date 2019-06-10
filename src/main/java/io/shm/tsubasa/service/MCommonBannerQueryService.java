package io.shm.tsubasa.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import io.shm.tsubasa.domain.MCommonBanner;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCommonBannerRepository;
import io.shm.tsubasa.service.dto.MCommonBannerCriteria;
import io.shm.tsubasa.service.dto.MCommonBannerDTO;
import io.shm.tsubasa.service.mapper.MCommonBannerMapper;

/**
 * Service for executing complex queries for {@link MCommonBanner} entities in the database.
 * The main input is a {@link MCommonBannerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCommonBannerDTO} or a {@link Page} of {@link MCommonBannerDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCommonBannerQueryService extends QueryService<MCommonBanner> {

    private final Logger log = LoggerFactory.getLogger(MCommonBannerQueryService.class);

    private final MCommonBannerRepository mCommonBannerRepository;

    private final MCommonBannerMapper mCommonBannerMapper;

    public MCommonBannerQueryService(MCommonBannerRepository mCommonBannerRepository, MCommonBannerMapper mCommonBannerMapper) {
        this.mCommonBannerRepository = mCommonBannerRepository;
        this.mCommonBannerMapper = mCommonBannerMapper;
    }

    /**
     * Return a {@link List} of {@link MCommonBannerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCommonBannerDTO> findByCriteria(MCommonBannerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCommonBanner> specification = createSpecification(criteria);
        return mCommonBannerMapper.toDto(mCommonBannerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCommonBannerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCommonBannerDTO> findByCriteria(MCommonBannerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCommonBanner> specification = createSpecification(criteria);
        return mCommonBannerRepository.findAll(specification, page)
            .map(mCommonBannerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCommonBannerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCommonBanner> specification = createSpecification(criteria);
        return mCommonBannerRepository.count(specification);
    }

    /**
     * Function to convert MCommonBannerCriteria to a {@link Specification}.
     */
    private Specification<MCommonBanner> createSpecification(MCommonBannerCriteria criteria) {
        Specification<MCommonBanner> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCommonBanner_.id));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MCommonBanner_.startAt));
            }
            if (criteria.getEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndAt(), MCommonBanner_.endAt));
            }
            if (criteria.getLabelEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLabelEndAt(), MCommonBanner_.labelEndAt));
            }
            if (criteria.getSceneTransition() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSceneTransition(), MCommonBanner_.sceneTransition));
            }
            if (criteria.getOrderNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderNum(), MCommonBanner_.orderNum));
            }
            if (criteria.getAppealType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAppealType(), MCommonBanner_.appealType));
            }
            if (criteria.getAppealContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAppealContentId(), MCommonBanner_.appealContentId));
            }
        }
        return specification;
    }
}
