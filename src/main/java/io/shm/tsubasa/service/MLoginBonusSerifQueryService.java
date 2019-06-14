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

import io.shm.tsubasa.domain.MLoginBonusSerif;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MLoginBonusSerifRepository;
import io.shm.tsubasa.service.dto.MLoginBonusSerifCriteria;
import io.shm.tsubasa.service.dto.MLoginBonusSerifDTO;
import io.shm.tsubasa.service.mapper.MLoginBonusSerifMapper;

/**
 * Service for executing complex queries for {@link MLoginBonusSerif} entities in the database.
 * The main input is a {@link MLoginBonusSerifCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MLoginBonusSerifDTO} or a {@link Page} of {@link MLoginBonusSerifDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MLoginBonusSerifQueryService extends QueryService<MLoginBonusSerif> {

    private final Logger log = LoggerFactory.getLogger(MLoginBonusSerifQueryService.class);

    private final MLoginBonusSerifRepository mLoginBonusSerifRepository;

    private final MLoginBonusSerifMapper mLoginBonusSerifMapper;

    public MLoginBonusSerifQueryService(MLoginBonusSerifRepository mLoginBonusSerifRepository, MLoginBonusSerifMapper mLoginBonusSerifMapper) {
        this.mLoginBonusSerifRepository = mLoginBonusSerifRepository;
        this.mLoginBonusSerifMapper = mLoginBonusSerifMapper;
    }

    /**
     * Return a {@link List} of {@link MLoginBonusSerifDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MLoginBonusSerifDTO> findByCriteria(MLoginBonusSerifCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MLoginBonusSerif> specification = createSpecification(criteria);
        return mLoginBonusSerifMapper.toDto(mLoginBonusSerifRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MLoginBonusSerifDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MLoginBonusSerifDTO> findByCriteria(MLoginBonusSerifCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MLoginBonusSerif> specification = createSpecification(criteria);
        return mLoginBonusSerifRepository.findAll(specification, page)
            .map(mLoginBonusSerifMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MLoginBonusSerifCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MLoginBonusSerif> specification = createSpecification(criteria);
        return mLoginBonusSerifRepository.count(specification);
    }

    /**
     * Function to convert MLoginBonusSerifCriteria to a {@link Specification}.
     */
    private Specification<MLoginBonusSerif> createSpecification(MLoginBonusSerifCriteria criteria) {
        Specification<MLoginBonusSerif> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MLoginBonusSerif_.id));
            }
            if (criteria.getSerifId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSerifId(), MLoginBonusSerif_.serifId));
            }
        }
        return specification;
    }
}
