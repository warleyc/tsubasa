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

import io.shm.tsubasa.domain.MUniformBottom;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MUniformBottomRepository;
import io.shm.tsubasa.service.dto.MUniformBottomCriteria;
import io.shm.tsubasa.service.dto.MUniformBottomDTO;
import io.shm.tsubasa.service.mapper.MUniformBottomMapper;

/**
 * Service for executing complex queries for {@link MUniformBottom} entities in the database.
 * The main input is a {@link MUniformBottomCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MUniformBottomDTO} or a {@link Page} of {@link MUniformBottomDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MUniformBottomQueryService extends QueryService<MUniformBottom> {

    private final Logger log = LoggerFactory.getLogger(MUniformBottomQueryService.class);

    private final MUniformBottomRepository mUniformBottomRepository;

    private final MUniformBottomMapper mUniformBottomMapper;

    public MUniformBottomQueryService(MUniformBottomRepository mUniformBottomRepository, MUniformBottomMapper mUniformBottomMapper) {
        this.mUniformBottomRepository = mUniformBottomRepository;
        this.mUniformBottomMapper = mUniformBottomMapper;
    }

    /**
     * Return a {@link List} of {@link MUniformBottomDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MUniformBottomDTO> findByCriteria(MUniformBottomCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MUniformBottom> specification = createSpecification(criteria);
        return mUniformBottomMapper.toDto(mUniformBottomRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MUniformBottomDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MUniformBottomDTO> findByCriteria(MUniformBottomCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MUniformBottom> specification = createSpecification(criteria);
        return mUniformBottomRepository.findAll(specification, page)
            .map(mUniformBottomMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MUniformBottomCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MUniformBottom> specification = createSpecification(criteria);
        return mUniformBottomRepository.count(specification);
    }

    /**
     * Function to convert MUniformBottomCriteria to a {@link Specification}.
     */
    private Specification<MUniformBottom> createSpecification(MUniformBottomCriteria criteria) {
        Specification<MUniformBottom> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MUniformBottom_.id));
            }
            if (criteria.getModelId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModelId(), MUniformBottom_.modelId));
            }
            if (criteria.getUniformType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformType(), MUniformBottom_.uniformType));
            }
            if (criteria.getIsDefault() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsDefault(), MUniformBottom_.isDefault));
            }
            if (criteria.getOrderNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderNum(), MUniformBottom_.orderNum));
            }
        }
        return specification;
    }
}
