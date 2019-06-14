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

import io.shm.tsubasa.domain.MRecommendShopMessage;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MRecommendShopMessageRepository;
import io.shm.tsubasa.service.dto.MRecommendShopMessageCriteria;
import io.shm.tsubasa.service.dto.MRecommendShopMessageDTO;
import io.shm.tsubasa.service.mapper.MRecommendShopMessageMapper;

/**
 * Service for executing complex queries for {@link MRecommendShopMessage} entities in the database.
 * The main input is a {@link MRecommendShopMessageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MRecommendShopMessageDTO} or a {@link Page} of {@link MRecommendShopMessageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MRecommendShopMessageQueryService extends QueryService<MRecommendShopMessage> {

    private final Logger log = LoggerFactory.getLogger(MRecommendShopMessageQueryService.class);

    private final MRecommendShopMessageRepository mRecommendShopMessageRepository;

    private final MRecommendShopMessageMapper mRecommendShopMessageMapper;

    public MRecommendShopMessageQueryService(MRecommendShopMessageRepository mRecommendShopMessageRepository, MRecommendShopMessageMapper mRecommendShopMessageMapper) {
        this.mRecommendShopMessageRepository = mRecommendShopMessageRepository;
        this.mRecommendShopMessageMapper = mRecommendShopMessageMapper;
    }

    /**
     * Return a {@link List} of {@link MRecommendShopMessageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MRecommendShopMessageDTO> findByCriteria(MRecommendShopMessageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MRecommendShopMessage> specification = createSpecification(criteria);
        return mRecommendShopMessageMapper.toDto(mRecommendShopMessageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MRecommendShopMessageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MRecommendShopMessageDTO> findByCriteria(MRecommendShopMessageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MRecommendShopMessage> specification = createSpecification(criteria);
        return mRecommendShopMessageRepository.findAll(specification, page)
            .map(mRecommendShopMessageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MRecommendShopMessageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MRecommendShopMessage> specification = createSpecification(criteria);
        return mRecommendShopMessageRepository.count(specification);
    }

    /**
     * Function to convert MRecommendShopMessageCriteria to a {@link Specification}.
     */
    private Specification<MRecommendShopMessage> createSpecification(MRecommendShopMessageCriteria criteria) {
        Specification<MRecommendShopMessage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MRecommendShopMessage_.id));
            }
            if (criteria.getHasSalesPeriod() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHasSalesPeriod(), MRecommendShopMessage_.hasSalesPeriod));
            }
        }
        return specification;
    }
}
