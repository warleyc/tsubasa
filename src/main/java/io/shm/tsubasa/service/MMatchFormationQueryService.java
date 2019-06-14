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

import io.shm.tsubasa.domain.MMatchFormation;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMatchFormationRepository;
import io.shm.tsubasa.service.dto.MMatchFormationCriteria;
import io.shm.tsubasa.service.dto.MMatchFormationDTO;
import io.shm.tsubasa.service.mapper.MMatchFormationMapper;

/**
 * Service for executing complex queries for {@link MMatchFormation} entities in the database.
 * The main input is a {@link MMatchFormationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMatchFormationDTO} or a {@link Page} of {@link MMatchFormationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMatchFormationQueryService extends QueryService<MMatchFormation> {

    private final Logger log = LoggerFactory.getLogger(MMatchFormationQueryService.class);

    private final MMatchFormationRepository mMatchFormationRepository;

    private final MMatchFormationMapper mMatchFormationMapper;

    public MMatchFormationQueryService(MMatchFormationRepository mMatchFormationRepository, MMatchFormationMapper mMatchFormationMapper) {
        this.mMatchFormationRepository = mMatchFormationRepository;
        this.mMatchFormationMapper = mMatchFormationMapper;
    }

    /**
     * Return a {@link List} of {@link MMatchFormationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMatchFormationDTO> findByCriteria(MMatchFormationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMatchFormation> specification = createSpecification(criteria);
        return mMatchFormationMapper.toDto(mMatchFormationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMatchFormationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMatchFormationDTO> findByCriteria(MMatchFormationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMatchFormation> specification = createSpecification(criteria);
        return mMatchFormationRepository.findAll(specification, page)
            .map(mMatchFormationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMatchFormationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMatchFormation> specification = createSpecification(criteria);
        return mMatchFormationRepository.count(specification);
    }

    /**
     * Function to convert MMatchFormationCriteria to a {@link Specification}.
     */
    private Specification<MMatchFormation> createSpecification(MMatchFormationCriteria criteria) {
        Specification<MMatchFormation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMatchFormation_.id));
            }
            if (criteria.getPosition1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPosition1(), MMatchFormation_.position1));
            }
            if (criteria.getPosition2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPosition2(), MMatchFormation_.position2));
            }
            if (criteria.getPosition3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPosition3(), MMatchFormation_.position3));
            }
            if (criteria.getPosition4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPosition4(), MMatchFormation_.position4));
            }
            if (criteria.getPosition5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPosition5(), MMatchFormation_.position5));
            }
            if (criteria.getPosition6() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPosition6(), MMatchFormation_.position6));
            }
            if (criteria.getPosition7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPosition7(), MMatchFormation_.position7));
            }
            if (criteria.getPosition8() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPosition8(), MMatchFormation_.position8));
            }
            if (criteria.getPosition9() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPosition9(), MMatchFormation_.position9));
            }
            if (criteria.getPosition10() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPosition10(), MMatchFormation_.position10));
            }
            if (criteria.getPosition11() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPosition11(), MMatchFormation_.position11));
            }
        }
        return specification;
    }
}
