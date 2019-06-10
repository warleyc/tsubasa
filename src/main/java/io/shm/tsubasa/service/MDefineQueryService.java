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

import io.shm.tsubasa.domain.MDefine;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDefineRepository;
import io.shm.tsubasa.service.dto.MDefineCriteria;
import io.shm.tsubasa.service.dto.MDefineDTO;
import io.shm.tsubasa.service.mapper.MDefineMapper;

/**
 * Service for executing complex queries for {@link MDefine} entities in the database.
 * The main input is a {@link MDefineCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDefineDTO} or a {@link Page} of {@link MDefineDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDefineQueryService extends QueryService<MDefine> {

    private final Logger log = LoggerFactory.getLogger(MDefineQueryService.class);

    private final MDefineRepository mDefineRepository;

    private final MDefineMapper mDefineMapper;

    public MDefineQueryService(MDefineRepository mDefineRepository, MDefineMapper mDefineMapper) {
        this.mDefineRepository = mDefineRepository;
        this.mDefineMapper = mDefineMapper;
    }

    /**
     * Return a {@link List} of {@link MDefineDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDefineDTO> findByCriteria(MDefineCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDefine> specification = createSpecification(criteria);
        return mDefineMapper.toDto(mDefineRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDefineDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDefineDTO> findByCriteria(MDefineCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDefine> specification = createSpecification(criteria);
        return mDefineRepository.findAll(specification, page)
            .map(mDefineMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDefineCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDefine> specification = createSpecification(criteria);
        return mDefineRepository.count(specification);
    }

    /**
     * Function to convert MDefineCriteria to a {@link Specification}.
     */
    private Specification<MDefine> createSpecification(MDefineCriteria criteria) {
        Specification<MDefine> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDefine_.id));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValue(), MDefine_.value));
            }
        }
        return specification;
    }
}
