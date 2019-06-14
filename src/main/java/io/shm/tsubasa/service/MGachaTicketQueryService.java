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

import io.shm.tsubasa.domain.MGachaTicket;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGachaTicketRepository;
import io.shm.tsubasa.service.dto.MGachaTicketCriteria;
import io.shm.tsubasa.service.dto.MGachaTicketDTO;
import io.shm.tsubasa.service.mapper.MGachaTicketMapper;

/**
 * Service for executing complex queries for {@link MGachaTicket} entities in the database.
 * The main input is a {@link MGachaTicketCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGachaTicketDTO} or a {@link Page} of {@link MGachaTicketDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGachaTicketQueryService extends QueryService<MGachaTicket> {

    private final Logger log = LoggerFactory.getLogger(MGachaTicketQueryService.class);

    private final MGachaTicketRepository mGachaTicketRepository;

    private final MGachaTicketMapper mGachaTicketMapper;

    public MGachaTicketQueryService(MGachaTicketRepository mGachaTicketRepository, MGachaTicketMapper mGachaTicketMapper) {
        this.mGachaTicketRepository = mGachaTicketRepository;
        this.mGachaTicketMapper = mGachaTicketMapper;
    }

    /**
     * Return a {@link List} of {@link MGachaTicketDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGachaTicketDTO> findByCriteria(MGachaTicketCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGachaTicket> specification = createSpecification(criteria);
        return mGachaTicketMapper.toDto(mGachaTicketRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGachaTicketDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaTicketDTO> findByCriteria(MGachaTicketCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGachaTicket> specification = createSpecification(criteria);
        return mGachaTicketRepository.findAll(specification, page)
            .map(mGachaTicketMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGachaTicketCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGachaTicket> specification = createSpecification(criteria);
        return mGachaTicketRepository.count(specification);
    }

    /**
     * Function to convert MGachaTicketCriteria to a {@link Specification}.
     */
    private Specification<MGachaTicket> createSpecification(MGachaTicketCriteria criteria) {
        Specification<MGachaTicket> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGachaTicket_.id));
            }
            if (criteria.getMaxAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxAmount(), MGachaTicket_.maxAmount));
            }
            if (criteria.getEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndAt(), MGachaTicket_.endAt));
            }
        }
        return specification;
    }
}
