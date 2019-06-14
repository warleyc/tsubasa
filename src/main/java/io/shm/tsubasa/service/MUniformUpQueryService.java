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

import io.shm.tsubasa.domain.MUniformUp;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MUniformUpRepository;
import io.shm.tsubasa.service.dto.MUniformUpCriteria;
import io.shm.tsubasa.service.dto.MUniformUpDTO;
import io.shm.tsubasa.service.mapper.MUniformUpMapper;

/**
 * Service for executing complex queries for {@link MUniformUp} entities in the database.
 * The main input is a {@link MUniformUpCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MUniformUpDTO} or a {@link Page} of {@link MUniformUpDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MUniformUpQueryService extends QueryService<MUniformUp> {

    private final Logger log = LoggerFactory.getLogger(MUniformUpQueryService.class);

    private final MUniformUpRepository mUniformUpRepository;

    private final MUniformUpMapper mUniformUpMapper;

    public MUniformUpQueryService(MUniformUpRepository mUniformUpRepository, MUniformUpMapper mUniformUpMapper) {
        this.mUniformUpRepository = mUniformUpRepository;
        this.mUniformUpMapper = mUniformUpMapper;
    }

    /**
     * Return a {@link List} of {@link MUniformUpDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MUniformUpDTO> findByCriteria(MUniformUpCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MUniformUp> specification = createSpecification(criteria);
        return mUniformUpMapper.toDto(mUniformUpRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MUniformUpDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MUniformUpDTO> findByCriteria(MUniformUpCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MUniformUp> specification = createSpecification(criteria);
        return mUniformUpRepository.findAll(specification, page)
            .map(mUniformUpMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MUniformUpCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MUniformUp> specification = createSpecification(criteria);
        return mUniformUpRepository.count(specification);
    }

    /**
     * Function to convert MUniformUpCriteria to a {@link Specification}.
     */
    private Specification<MUniformUp> createSpecification(MUniformUpCriteria criteria) {
        Specification<MUniformUp> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MUniformUp_.id));
            }
            if (criteria.getModelId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModelId(), MUniformUp_.modelId));
            }
            if (criteria.getUniformType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformType(), MUniformUp_.uniformType));
            }
            if (criteria.getIsDefault() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsDefault(), MUniformUp_.isDefault));
            }
            if (criteria.getOrderNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderNum(), MUniformUp_.orderNum));
            }
        }
        return specification;
    }
}
