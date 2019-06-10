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

import io.shm.tsubasa.domain.MCutSeqGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCutSeqGroupRepository;
import io.shm.tsubasa.service.dto.MCutSeqGroupCriteria;
import io.shm.tsubasa.service.dto.MCutSeqGroupDTO;
import io.shm.tsubasa.service.mapper.MCutSeqGroupMapper;

/**
 * Service for executing complex queries for {@link MCutSeqGroup} entities in the database.
 * The main input is a {@link MCutSeqGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCutSeqGroupDTO} or a {@link Page} of {@link MCutSeqGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCutSeqGroupQueryService extends QueryService<MCutSeqGroup> {

    private final Logger log = LoggerFactory.getLogger(MCutSeqGroupQueryService.class);

    private final MCutSeqGroupRepository mCutSeqGroupRepository;

    private final MCutSeqGroupMapper mCutSeqGroupMapper;

    public MCutSeqGroupQueryService(MCutSeqGroupRepository mCutSeqGroupRepository, MCutSeqGroupMapper mCutSeqGroupMapper) {
        this.mCutSeqGroupRepository = mCutSeqGroupRepository;
        this.mCutSeqGroupMapper = mCutSeqGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MCutSeqGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCutSeqGroupDTO> findByCriteria(MCutSeqGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCutSeqGroup> specification = createSpecification(criteria);
        return mCutSeqGroupMapper.toDto(mCutSeqGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCutSeqGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCutSeqGroupDTO> findByCriteria(MCutSeqGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCutSeqGroup> specification = createSpecification(criteria);
        return mCutSeqGroupRepository.findAll(specification, page)
            .map(mCutSeqGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCutSeqGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCutSeqGroup> specification = createSpecification(criteria);
        return mCutSeqGroupRepository.count(specification);
    }

    /**
     * Function to convert MCutSeqGroupCriteria to a {@link Specification}.
     */
    private Specification<MCutSeqGroup> createSpecification(MCutSeqGroupCriteria criteria) {
        Specification<MCutSeqGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCutSeqGroup_.id));
            }
        }
        return specification;
    }
}
