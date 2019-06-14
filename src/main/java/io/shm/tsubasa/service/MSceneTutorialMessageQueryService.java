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

import io.shm.tsubasa.domain.MSceneTutorialMessage;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MSceneTutorialMessageRepository;
import io.shm.tsubasa.service.dto.MSceneTutorialMessageCriteria;
import io.shm.tsubasa.service.dto.MSceneTutorialMessageDTO;
import io.shm.tsubasa.service.mapper.MSceneTutorialMessageMapper;

/**
 * Service for executing complex queries for {@link MSceneTutorialMessage} entities in the database.
 * The main input is a {@link MSceneTutorialMessageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MSceneTutorialMessageDTO} or a {@link Page} of {@link MSceneTutorialMessageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MSceneTutorialMessageQueryService extends QueryService<MSceneTutorialMessage> {

    private final Logger log = LoggerFactory.getLogger(MSceneTutorialMessageQueryService.class);

    private final MSceneTutorialMessageRepository mSceneTutorialMessageRepository;

    private final MSceneTutorialMessageMapper mSceneTutorialMessageMapper;

    public MSceneTutorialMessageQueryService(MSceneTutorialMessageRepository mSceneTutorialMessageRepository, MSceneTutorialMessageMapper mSceneTutorialMessageMapper) {
        this.mSceneTutorialMessageRepository = mSceneTutorialMessageRepository;
        this.mSceneTutorialMessageMapper = mSceneTutorialMessageMapper;
    }

    /**
     * Return a {@link List} of {@link MSceneTutorialMessageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MSceneTutorialMessageDTO> findByCriteria(MSceneTutorialMessageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MSceneTutorialMessage> specification = createSpecification(criteria);
        return mSceneTutorialMessageMapper.toDto(mSceneTutorialMessageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MSceneTutorialMessageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MSceneTutorialMessageDTO> findByCriteria(MSceneTutorialMessageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MSceneTutorialMessage> specification = createSpecification(criteria);
        return mSceneTutorialMessageRepository.findAll(specification, page)
            .map(mSceneTutorialMessageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MSceneTutorialMessageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MSceneTutorialMessage> specification = createSpecification(criteria);
        return mSceneTutorialMessageRepository.count(specification);
    }

    /**
     * Function to convert MSceneTutorialMessageCriteria to a {@link Specification}.
     */
    private Specification<MSceneTutorialMessage> createSpecification(MSceneTutorialMessageCriteria criteria) {
        Specification<MSceneTutorialMessage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MSceneTutorialMessage_.id));
            }
            if (criteria.getTarget() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTarget(), MSceneTutorialMessage_.target));
            }
            if (criteria.getOrderNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderNum(), MSceneTutorialMessage_.orderNum));
            }
            if (criteria.getPosition() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPosition(), MSceneTutorialMessage_.position));
            }
        }
        return specification;
    }
}
