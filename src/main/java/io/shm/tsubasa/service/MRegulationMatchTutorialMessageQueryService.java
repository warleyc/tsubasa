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

import io.shm.tsubasa.domain.MRegulationMatchTutorialMessage;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MRegulationMatchTutorialMessageRepository;
import io.shm.tsubasa.service.dto.MRegulationMatchTutorialMessageCriteria;
import io.shm.tsubasa.service.dto.MRegulationMatchTutorialMessageDTO;
import io.shm.tsubasa.service.mapper.MRegulationMatchTutorialMessageMapper;

/**
 * Service for executing complex queries for {@link MRegulationMatchTutorialMessage} entities in the database.
 * The main input is a {@link MRegulationMatchTutorialMessageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MRegulationMatchTutorialMessageDTO} or a {@link Page} of {@link MRegulationMatchTutorialMessageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MRegulationMatchTutorialMessageQueryService extends QueryService<MRegulationMatchTutorialMessage> {

    private final Logger log = LoggerFactory.getLogger(MRegulationMatchTutorialMessageQueryService.class);

    private final MRegulationMatchTutorialMessageRepository mRegulationMatchTutorialMessageRepository;

    private final MRegulationMatchTutorialMessageMapper mRegulationMatchTutorialMessageMapper;

    public MRegulationMatchTutorialMessageQueryService(MRegulationMatchTutorialMessageRepository mRegulationMatchTutorialMessageRepository, MRegulationMatchTutorialMessageMapper mRegulationMatchTutorialMessageMapper) {
        this.mRegulationMatchTutorialMessageRepository = mRegulationMatchTutorialMessageRepository;
        this.mRegulationMatchTutorialMessageMapper = mRegulationMatchTutorialMessageMapper;
    }

    /**
     * Return a {@link List} of {@link MRegulationMatchTutorialMessageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MRegulationMatchTutorialMessageDTO> findByCriteria(MRegulationMatchTutorialMessageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MRegulationMatchTutorialMessage> specification = createSpecification(criteria);
        return mRegulationMatchTutorialMessageMapper.toDto(mRegulationMatchTutorialMessageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MRegulationMatchTutorialMessageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MRegulationMatchTutorialMessageDTO> findByCriteria(MRegulationMatchTutorialMessageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MRegulationMatchTutorialMessage> specification = createSpecification(criteria);
        return mRegulationMatchTutorialMessageRepository.findAll(specification, page)
            .map(mRegulationMatchTutorialMessageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MRegulationMatchTutorialMessageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MRegulationMatchTutorialMessage> specification = createSpecification(criteria);
        return mRegulationMatchTutorialMessageRepository.count(specification);
    }

    /**
     * Function to convert MRegulationMatchTutorialMessageCriteria to a {@link Specification}.
     */
    private Specification<MRegulationMatchTutorialMessage> createSpecification(MRegulationMatchTutorialMessageCriteria criteria) {
        Specification<MRegulationMatchTutorialMessage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MRegulationMatchTutorialMessage_.id));
            }
            if (criteria.getRuleId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRuleId(), MRegulationMatchTutorialMessage_.ruleId));
            }
            if (criteria.getOrderNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderNum(), MRegulationMatchTutorialMessage_.orderNum));
            }
            if (criteria.getPosition() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPosition(), MRegulationMatchTutorialMessage_.position));
            }
        }
        return specification;
    }
}
