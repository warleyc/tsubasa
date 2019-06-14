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

import io.shm.tsubasa.domain.MUniformOriginalSet;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MUniformOriginalSetRepository;
import io.shm.tsubasa.service.dto.MUniformOriginalSetCriteria;
import io.shm.tsubasa.service.dto.MUniformOriginalSetDTO;
import io.shm.tsubasa.service.mapper.MUniformOriginalSetMapper;

/**
 * Service for executing complex queries for {@link MUniformOriginalSet} entities in the database.
 * The main input is a {@link MUniformOriginalSetCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MUniformOriginalSetDTO} or a {@link Page} of {@link MUniformOriginalSetDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MUniformOriginalSetQueryService extends QueryService<MUniformOriginalSet> {

    private final Logger log = LoggerFactory.getLogger(MUniformOriginalSetQueryService.class);

    private final MUniformOriginalSetRepository mUniformOriginalSetRepository;

    private final MUniformOriginalSetMapper mUniformOriginalSetMapper;

    public MUniformOriginalSetQueryService(MUniformOriginalSetRepository mUniformOriginalSetRepository, MUniformOriginalSetMapper mUniformOriginalSetMapper) {
        this.mUniformOriginalSetRepository = mUniformOriginalSetRepository;
        this.mUniformOriginalSetMapper = mUniformOriginalSetMapper;
    }

    /**
     * Return a {@link List} of {@link MUniformOriginalSetDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MUniformOriginalSetDTO> findByCriteria(MUniformOriginalSetCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MUniformOriginalSet> specification = createSpecification(criteria);
        return mUniformOriginalSetMapper.toDto(mUniformOriginalSetRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MUniformOriginalSetDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MUniformOriginalSetDTO> findByCriteria(MUniformOriginalSetCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MUniformOriginalSet> specification = createSpecification(criteria);
        return mUniformOriginalSetRepository.findAll(specification, page)
            .map(mUniformOriginalSetMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MUniformOriginalSetCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MUniformOriginalSet> specification = createSpecification(criteria);
        return mUniformOriginalSetRepository.count(specification);
    }

    /**
     * Function to convert MUniformOriginalSetCriteria to a {@link Specification}.
     */
    private Specification<MUniformOriginalSet> createSpecification(MUniformOriginalSetCriteria criteria) {
        Specification<MUniformOriginalSet> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MUniformOriginalSet_.id));
            }
            if (criteria.getUpModelId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpModelId(), MUniformOriginalSet_.upModelId));
            }
            if (criteria.getBottomModelId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBottomModelId(), MUniformOriginalSet_.bottomModelId));
            }
            if (criteria.getUniformType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformType(), MUniformOriginalSet_.uniformType));
            }
            if (criteria.getIsDefault() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsDefault(), MUniformOriginalSet_.isDefault));
            }
            if (criteria.getOrderNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderNum(), MUniformOriginalSet_.orderNum));
            }
        }
        return specification;
    }
}
