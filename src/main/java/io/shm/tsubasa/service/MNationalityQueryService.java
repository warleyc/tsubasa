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

import io.shm.tsubasa.domain.MNationality;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MNationalityRepository;
import io.shm.tsubasa.service.dto.MNationalityCriteria;
import io.shm.tsubasa.service.dto.MNationalityDTO;
import io.shm.tsubasa.service.mapper.MNationalityMapper;

/**
 * Service for executing complex queries for {@link MNationality} entities in the database.
 * The main input is a {@link MNationalityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MNationalityDTO} or a {@link Page} of {@link MNationalityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MNationalityQueryService extends QueryService<MNationality> {

    private final Logger log = LoggerFactory.getLogger(MNationalityQueryService.class);

    private final MNationalityRepository mNationalityRepository;

    private final MNationalityMapper mNationalityMapper;

    public MNationalityQueryService(MNationalityRepository mNationalityRepository, MNationalityMapper mNationalityMapper) {
        this.mNationalityRepository = mNationalityRepository;
        this.mNationalityMapper = mNationalityMapper;
    }

    /**
     * Return a {@link List} of {@link MNationalityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MNationalityDTO> findByCriteria(MNationalityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MNationality> specification = createSpecification(criteria);
        return mNationalityMapper.toDto(mNationalityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MNationalityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MNationalityDTO> findByCriteria(MNationalityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MNationality> specification = createSpecification(criteria);
        return mNationalityRepository.findAll(specification, page)
            .map(mNationalityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MNationalityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MNationality> specification = createSpecification(criteria);
        return mNationalityRepository.count(specification);
    }

    /**
     * Function to convert MNationalityCriteria to a {@link Specification}.
     */
    private Specification<MNationality> createSpecification(MNationalityCriteria criteria) {
        Specification<MNationality> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MNationality_.id));
            }
            if (criteria.getMTargetNationalityGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getMTargetNationalityGroupId(),
                    root -> root.join(MNationality_.mTargetNationalityGroups, JoinType.LEFT).get(MTargetNationalityGroup_.id)));
            }
        }
        return specification;
    }
}
