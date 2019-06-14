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

import io.shm.tsubasa.domain.MGuildChatDefaultStamp;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGuildChatDefaultStampRepository;
import io.shm.tsubasa.service.dto.MGuildChatDefaultStampCriteria;
import io.shm.tsubasa.service.dto.MGuildChatDefaultStampDTO;
import io.shm.tsubasa.service.mapper.MGuildChatDefaultStampMapper;

/**
 * Service for executing complex queries for {@link MGuildChatDefaultStamp} entities in the database.
 * The main input is a {@link MGuildChatDefaultStampCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGuildChatDefaultStampDTO} or a {@link Page} of {@link MGuildChatDefaultStampDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGuildChatDefaultStampQueryService extends QueryService<MGuildChatDefaultStamp> {

    private final Logger log = LoggerFactory.getLogger(MGuildChatDefaultStampQueryService.class);

    private final MGuildChatDefaultStampRepository mGuildChatDefaultStampRepository;

    private final MGuildChatDefaultStampMapper mGuildChatDefaultStampMapper;

    public MGuildChatDefaultStampQueryService(MGuildChatDefaultStampRepository mGuildChatDefaultStampRepository, MGuildChatDefaultStampMapper mGuildChatDefaultStampMapper) {
        this.mGuildChatDefaultStampRepository = mGuildChatDefaultStampRepository;
        this.mGuildChatDefaultStampMapper = mGuildChatDefaultStampMapper;
    }

    /**
     * Return a {@link List} of {@link MGuildChatDefaultStampDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGuildChatDefaultStampDTO> findByCriteria(MGuildChatDefaultStampCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGuildChatDefaultStamp> specification = createSpecification(criteria);
        return mGuildChatDefaultStampMapper.toDto(mGuildChatDefaultStampRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGuildChatDefaultStampDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuildChatDefaultStampDTO> findByCriteria(MGuildChatDefaultStampCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGuildChatDefaultStamp> specification = createSpecification(criteria);
        return mGuildChatDefaultStampRepository.findAll(specification, page)
            .map(mGuildChatDefaultStampMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGuildChatDefaultStampCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGuildChatDefaultStamp> specification = createSpecification(criteria);
        return mGuildChatDefaultStampRepository.count(specification);
    }

    /**
     * Function to convert MGuildChatDefaultStampCriteria to a {@link Specification}.
     */
    private Specification<MGuildChatDefaultStamp> createSpecification(MGuildChatDefaultStampCriteria criteria) {
        Specification<MGuildChatDefaultStamp> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGuildChatDefaultStamp_.id));
            }
            if (criteria.getMasterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMasterId(), MGuildChatDefaultStamp_.masterId));
            }
        }
        return specification;
    }
}
