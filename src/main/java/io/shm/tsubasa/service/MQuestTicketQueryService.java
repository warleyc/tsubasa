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

import io.shm.tsubasa.domain.MQuestTicket;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MQuestTicketRepository;
import io.shm.tsubasa.service.dto.MQuestTicketCriteria;
import io.shm.tsubasa.service.dto.MQuestTicketDTO;
import io.shm.tsubasa.service.mapper.MQuestTicketMapper;

/**
 * Service for executing complex queries for {@link MQuestTicket} entities in the database.
 * The main input is a {@link MQuestTicketCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MQuestTicketDTO} or a {@link Page} of {@link MQuestTicketDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MQuestTicketQueryService extends QueryService<MQuestTicket> {

    private final Logger log = LoggerFactory.getLogger(MQuestTicketQueryService.class);

    private final MQuestTicketRepository mQuestTicketRepository;

    private final MQuestTicketMapper mQuestTicketMapper;

    public MQuestTicketQueryService(MQuestTicketRepository mQuestTicketRepository, MQuestTicketMapper mQuestTicketMapper) {
        this.mQuestTicketRepository = mQuestTicketRepository;
        this.mQuestTicketMapper = mQuestTicketMapper;
    }

    /**
     * Return a {@link List} of {@link MQuestTicketDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MQuestTicketDTO> findByCriteria(MQuestTicketCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MQuestTicket> specification = createSpecification(criteria);
        return mQuestTicketMapper.toDto(mQuestTicketRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MQuestTicketDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestTicketDTO> findByCriteria(MQuestTicketCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MQuestTicket> specification = createSpecification(criteria);
        return mQuestTicketRepository.findAll(specification, page)
            .map(mQuestTicketMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MQuestTicketCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MQuestTicket> specification = createSpecification(criteria);
        return mQuestTicketRepository.count(specification);
    }

    /**
     * Function to convert MQuestTicketCriteria to a {@link Specification}.
     */
    private Specification<MQuestTicket> createSpecification(MQuestTicketCriteria criteria) {
        Specification<MQuestTicket> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MQuestTicket_.id));
            }
        }
        return specification;
    }
}
