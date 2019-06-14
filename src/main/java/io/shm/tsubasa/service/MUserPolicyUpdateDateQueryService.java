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

import io.shm.tsubasa.domain.MUserPolicyUpdateDate;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MUserPolicyUpdateDateRepository;
import io.shm.tsubasa.service.dto.MUserPolicyUpdateDateCriteria;
import io.shm.tsubasa.service.dto.MUserPolicyUpdateDateDTO;
import io.shm.tsubasa.service.mapper.MUserPolicyUpdateDateMapper;

/**
 * Service for executing complex queries for {@link MUserPolicyUpdateDate} entities in the database.
 * The main input is a {@link MUserPolicyUpdateDateCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MUserPolicyUpdateDateDTO} or a {@link Page} of {@link MUserPolicyUpdateDateDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MUserPolicyUpdateDateQueryService extends QueryService<MUserPolicyUpdateDate> {

    private final Logger log = LoggerFactory.getLogger(MUserPolicyUpdateDateQueryService.class);

    private final MUserPolicyUpdateDateRepository mUserPolicyUpdateDateRepository;

    private final MUserPolicyUpdateDateMapper mUserPolicyUpdateDateMapper;

    public MUserPolicyUpdateDateQueryService(MUserPolicyUpdateDateRepository mUserPolicyUpdateDateRepository, MUserPolicyUpdateDateMapper mUserPolicyUpdateDateMapper) {
        this.mUserPolicyUpdateDateRepository = mUserPolicyUpdateDateRepository;
        this.mUserPolicyUpdateDateMapper = mUserPolicyUpdateDateMapper;
    }

    /**
     * Return a {@link List} of {@link MUserPolicyUpdateDateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MUserPolicyUpdateDateDTO> findByCriteria(MUserPolicyUpdateDateCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MUserPolicyUpdateDate> specification = createSpecification(criteria);
        return mUserPolicyUpdateDateMapper.toDto(mUserPolicyUpdateDateRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MUserPolicyUpdateDateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MUserPolicyUpdateDateDTO> findByCriteria(MUserPolicyUpdateDateCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MUserPolicyUpdateDate> specification = createSpecification(criteria);
        return mUserPolicyUpdateDateRepository.findAll(specification, page)
            .map(mUserPolicyUpdateDateMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MUserPolicyUpdateDateCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MUserPolicyUpdateDate> specification = createSpecification(criteria);
        return mUserPolicyUpdateDateRepository.count(specification);
    }

    /**
     * Function to convert MUserPolicyUpdateDateCriteria to a {@link Specification}.
     */
    private Specification<MUserPolicyUpdateDate> createSpecification(MUserPolicyUpdateDateCriteria criteria) {
        Specification<MUserPolicyUpdateDate> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MUserPolicyUpdateDate_.id));
            }
            if (criteria.getUpdateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateDate(), MUserPolicyUpdateDate_.updateDate));
            }
        }
        return specification;
    }
}
