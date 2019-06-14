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

import io.shm.tsubasa.domain.MStoreReviewUrl;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MStoreReviewUrlRepository;
import io.shm.tsubasa.service.dto.MStoreReviewUrlCriteria;
import io.shm.tsubasa.service.dto.MStoreReviewUrlDTO;
import io.shm.tsubasa.service.mapper.MStoreReviewUrlMapper;

/**
 * Service for executing complex queries for {@link MStoreReviewUrl} entities in the database.
 * The main input is a {@link MStoreReviewUrlCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MStoreReviewUrlDTO} or a {@link Page} of {@link MStoreReviewUrlDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MStoreReviewUrlQueryService extends QueryService<MStoreReviewUrl> {

    private final Logger log = LoggerFactory.getLogger(MStoreReviewUrlQueryService.class);

    private final MStoreReviewUrlRepository mStoreReviewUrlRepository;

    private final MStoreReviewUrlMapper mStoreReviewUrlMapper;

    public MStoreReviewUrlQueryService(MStoreReviewUrlRepository mStoreReviewUrlRepository, MStoreReviewUrlMapper mStoreReviewUrlMapper) {
        this.mStoreReviewUrlRepository = mStoreReviewUrlRepository;
        this.mStoreReviewUrlMapper = mStoreReviewUrlMapper;
    }

    /**
     * Return a {@link List} of {@link MStoreReviewUrlDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MStoreReviewUrlDTO> findByCriteria(MStoreReviewUrlCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MStoreReviewUrl> specification = createSpecification(criteria);
        return mStoreReviewUrlMapper.toDto(mStoreReviewUrlRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MStoreReviewUrlDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MStoreReviewUrlDTO> findByCriteria(MStoreReviewUrlCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MStoreReviewUrl> specification = createSpecification(criteria);
        return mStoreReviewUrlRepository.findAll(specification, page)
            .map(mStoreReviewUrlMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MStoreReviewUrlCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MStoreReviewUrl> specification = createSpecification(criteria);
        return mStoreReviewUrlRepository.count(specification);
    }

    /**
     * Function to convert MStoreReviewUrlCriteria to a {@link Specification}.
     */
    private Specification<MStoreReviewUrl> createSpecification(MStoreReviewUrlCriteria criteria) {
        Specification<MStoreReviewUrl> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MStoreReviewUrl_.id));
            }
            if (criteria.getPlatform() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlatform(), MStoreReviewUrl_.platform));
            }
        }
        return specification;
    }
}
