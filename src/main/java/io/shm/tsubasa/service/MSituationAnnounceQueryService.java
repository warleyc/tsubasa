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

import io.shm.tsubasa.domain.MSituationAnnounce;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MSituationAnnounceRepository;
import io.shm.tsubasa.service.dto.MSituationAnnounceCriteria;
import io.shm.tsubasa.service.dto.MSituationAnnounceDTO;
import io.shm.tsubasa.service.mapper.MSituationAnnounceMapper;

/**
 * Service for executing complex queries for {@link MSituationAnnounce} entities in the database.
 * The main input is a {@link MSituationAnnounceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MSituationAnnounceDTO} or a {@link Page} of {@link MSituationAnnounceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MSituationAnnounceQueryService extends QueryService<MSituationAnnounce> {

    private final Logger log = LoggerFactory.getLogger(MSituationAnnounceQueryService.class);

    private final MSituationAnnounceRepository mSituationAnnounceRepository;

    private final MSituationAnnounceMapper mSituationAnnounceMapper;

    public MSituationAnnounceQueryService(MSituationAnnounceRepository mSituationAnnounceRepository, MSituationAnnounceMapper mSituationAnnounceMapper) {
        this.mSituationAnnounceRepository = mSituationAnnounceRepository;
        this.mSituationAnnounceMapper = mSituationAnnounceMapper;
    }

    /**
     * Return a {@link List} of {@link MSituationAnnounceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MSituationAnnounceDTO> findByCriteria(MSituationAnnounceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MSituationAnnounce> specification = createSpecification(criteria);
        return mSituationAnnounceMapper.toDto(mSituationAnnounceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MSituationAnnounceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MSituationAnnounceDTO> findByCriteria(MSituationAnnounceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MSituationAnnounce> specification = createSpecification(criteria);
        return mSituationAnnounceRepository.findAll(specification, page)
            .map(mSituationAnnounceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MSituationAnnounceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MSituationAnnounce> specification = createSpecification(criteria);
        return mSituationAnnounceRepository.count(specification);
    }

    /**
     * Function to convert MSituationAnnounceCriteria to a {@link Specification}.
     */
    private Specification<MSituationAnnounce> createSpecification(MSituationAnnounceCriteria criteria) {
        Specification<MSituationAnnounce> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MSituationAnnounce_.id));
            }
            if (criteria.getSituationId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSituationId(), MSituationAnnounce_.situationId));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MSituationAnnounce_.groupId));
            }
        }
        return specification;
    }
}
