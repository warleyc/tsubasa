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

import io.shm.tsubasa.domain.MTutorialMessage;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTutorialMessageRepository;
import io.shm.tsubasa.service.dto.MTutorialMessageCriteria;
import io.shm.tsubasa.service.dto.MTutorialMessageDTO;
import io.shm.tsubasa.service.mapper.MTutorialMessageMapper;

/**
 * Service for executing complex queries for {@link MTutorialMessage} entities in the database.
 * The main input is a {@link MTutorialMessageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTutorialMessageDTO} or a {@link Page} of {@link MTutorialMessageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTutorialMessageQueryService extends QueryService<MTutorialMessage> {

    private final Logger log = LoggerFactory.getLogger(MTutorialMessageQueryService.class);

    private final MTutorialMessageRepository mTutorialMessageRepository;

    private final MTutorialMessageMapper mTutorialMessageMapper;

    public MTutorialMessageQueryService(MTutorialMessageRepository mTutorialMessageRepository, MTutorialMessageMapper mTutorialMessageMapper) {
        this.mTutorialMessageRepository = mTutorialMessageRepository;
        this.mTutorialMessageMapper = mTutorialMessageMapper;
    }

    /**
     * Return a {@link List} of {@link MTutorialMessageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTutorialMessageDTO> findByCriteria(MTutorialMessageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTutorialMessage> specification = createSpecification(criteria);
        return mTutorialMessageMapper.toDto(mTutorialMessageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTutorialMessageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTutorialMessageDTO> findByCriteria(MTutorialMessageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTutorialMessage> specification = createSpecification(criteria);
        return mTutorialMessageRepository.findAll(specification, page)
            .map(mTutorialMessageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTutorialMessageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTutorialMessage> specification = createSpecification(criteria);
        return mTutorialMessageRepository.count(specification);
    }

    /**
     * Function to convert MTutorialMessageCriteria to a {@link Specification}.
     */
    private Specification<MTutorialMessage> createSpecification(MTutorialMessageCriteria criteria) {
        Specification<MTutorialMessage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTutorialMessage_.id));
            }
            if (criteria.getStep() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStep(), MTutorialMessage_.step));
            }
            if (criteria.getOrderNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderNum(), MTutorialMessage_.orderNum));
            }
            if (criteria.getPosition() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPosition(), MTutorialMessage_.position));
            }
        }
        return specification;
    }
}
