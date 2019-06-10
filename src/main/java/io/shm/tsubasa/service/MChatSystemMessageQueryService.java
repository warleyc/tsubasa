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

import io.shm.tsubasa.domain.MChatSystemMessage;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MChatSystemMessageRepository;
import io.shm.tsubasa.service.dto.MChatSystemMessageCriteria;
import io.shm.tsubasa.service.dto.MChatSystemMessageDTO;
import io.shm.tsubasa.service.mapper.MChatSystemMessageMapper;

/**
 * Service for executing complex queries for {@link MChatSystemMessage} entities in the database.
 * The main input is a {@link MChatSystemMessageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MChatSystemMessageDTO} or a {@link Page} of {@link MChatSystemMessageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MChatSystemMessageQueryService extends QueryService<MChatSystemMessage> {

    private final Logger log = LoggerFactory.getLogger(MChatSystemMessageQueryService.class);

    private final MChatSystemMessageRepository mChatSystemMessageRepository;

    private final MChatSystemMessageMapper mChatSystemMessageMapper;

    public MChatSystemMessageQueryService(MChatSystemMessageRepository mChatSystemMessageRepository, MChatSystemMessageMapper mChatSystemMessageMapper) {
        this.mChatSystemMessageRepository = mChatSystemMessageRepository;
        this.mChatSystemMessageMapper = mChatSystemMessageMapper;
    }

    /**
     * Return a {@link List} of {@link MChatSystemMessageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MChatSystemMessageDTO> findByCriteria(MChatSystemMessageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MChatSystemMessage> specification = createSpecification(criteria);
        return mChatSystemMessageMapper.toDto(mChatSystemMessageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MChatSystemMessageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MChatSystemMessageDTO> findByCriteria(MChatSystemMessageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MChatSystemMessage> specification = createSpecification(criteria);
        return mChatSystemMessageRepository.findAll(specification, page)
            .map(mChatSystemMessageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MChatSystemMessageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MChatSystemMessage> specification = createSpecification(criteria);
        return mChatSystemMessageRepository.count(specification);
    }

    /**
     * Function to convert MChatSystemMessageCriteria to a {@link Specification}.
     */
    private Specification<MChatSystemMessage> createSpecification(MChatSystemMessageCriteria criteria) {
        Specification<MChatSystemMessage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MChatSystemMessage_.id));
            }
            if (criteria.getMessageType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMessageType(), MChatSystemMessage_.messageType));
            }
        }
        return specification;
    }
}
