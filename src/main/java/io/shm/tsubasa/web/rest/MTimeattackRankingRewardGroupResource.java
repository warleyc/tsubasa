package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTimeattackRankingRewardGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTimeattackRankingRewardGroupDTO;
import io.shm.tsubasa.service.dto.MTimeattackRankingRewardGroupCriteria;
import io.shm.tsubasa.service.MTimeattackRankingRewardGroupQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.shm.tsubasa.domain.MTimeattackRankingRewardGroup}.
 */
@RestController
@RequestMapping("/api")
public class MTimeattackRankingRewardGroupResource {

    private final Logger log = LoggerFactory.getLogger(MTimeattackRankingRewardGroupResource.class);

    private static final String ENTITY_NAME = "mTimeattackRankingRewardGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTimeattackRankingRewardGroupService mTimeattackRankingRewardGroupService;

    private final MTimeattackRankingRewardGroupQueryService mTimeattackRankingRewardGroupQueryService;

    public MTimeattackRankingRewardGroupResource(MTimeattackRankingRewardGroupService mTimeattackRankingRewardGroupService, MTimeattackRankingRewardGroupQueryService mTimeattackRankingRewardGroupQueryService) {
        this.mTimeattackRankingRewardGroupService = mTimeattackRankingRewardGroupService;
        this.mTimeattackRankingRewardGroupQueryService = mTimeattackRankingRewardGroupQueryService;
    }

    /**
     * {@code POST  /m-timeattack-ranking-reward-groups} : Create a new mTimeattackRankingRewardGroup.
     *
     * @param mTimeattackRankingRewardGroupDTO the mTimeattackRankingRewardGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTimeattackRankingRewardGroupDTO, or with status {@code 400 (Bad Request)} if the mTimeattackRankingRewardGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-timeattack-ranking-reward-groups")
    public ResponseEntity<MTimeattackRankingRewardGroupDTO> createMTimeattackRankingRewardGroup(@Valid @RequestBody MTimeattackRankingRewardGroupDTO mTimeattackRankingRewardGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MTimeattackRankingRewardGroup : {}", mTimeattackRankingRewardGroupDTO);
        if (mTimeattackRankingRewardGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTimeattackRankingRewardGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTimeattackRankingRewardGroupDTO result = mTimeattackRankingRewardGroupService.save(mTimeattackRankingRewardGroupDTO);
        return ResponseEntity.created(new URI("/api/m-timeattack-ranking-reward-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-timeattack-ranking-reward-groups} : Updates an existing mTimeattackRankingRewardGroup.
     *
     * @param mTimeattackRankingRewardGroupDTO the mTimeattackRankingRewardGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTimeattackRankingRewardGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mTimeattackRankingRewardGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTimeattackRankingRewardGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-timeattack-ranking-reward-groups")
    public ResponseEntity<MTimeattackRankingRewardGroupDTO> updateMTimeattackRankingRewardGroup(@Valid @RequestBody MTimeattackRankingRewardGroupDTO mTimeattackRankingRewardGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MTimeattackRankingRewardGroup : {}", mTimeattackRankingRewardGroupDTO);
        if (mTimeattackRankingRewardGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTimeattackRankingRewardGroupDTO result = mTimeattackRankingRewardGroupService.save(mTimeattackRankingRewardGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTimeattackRankingRewardGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-timeattack-ranking-reward-groups} : get all the mTimeattackRankingRewardGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTimeattackRankingRewardGroups in body.
     */
    @GetMapping("/m-timeattack-ranking-reward-groups")
    public ResponseEntity<List<MTimeattackRankingRewardGroupDTO>> getAllMTimeattackRankingRewardGroups(MTimeattackRankingRewardGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTimeattackRankingRewardGroups by criteria: {}", criteria);
        Page<MTimeattackRankingRewardGroupDTO> page = mTimeattackRankingRewardGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-timeattack-ranking-reward-groups/count} : count all the mTimeattackRankingRewardGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-timeattack-ranking-reward-groups/count")
    public ResponseEntity<Long> countMTimeattackRankingRewardGroups(MTimeattackRankingRewardGroupCriteria criteria) {
        log.debug("REST request to count MTimeattackRankingRewardGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTimeattackRankingRewardGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-timeattack-ranking-reward-groups/:id} : get the "id" mTimeattackRankingRewardGroup.
     *
     * @param id the id of the mTimeattackRankingRewardGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTimeattackRankingRewardGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-timeattack-ranking-reward-groups/{id}")
    public ResponseEntity<MTimeattackRankingRewardGroupDTO> getMTimeattackRankingRewardGroup(@PathVariable Long id) {
        log.debug("REST request to get MTimeattackRankingRewardGroup : {}", id);
        Optional<MTimeattackRankingRewardGroupDTO> mTimeattackRankingRewardGroupDTO = mTimeattackRankingRewardGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTimeattackRankingRewardGroupDTO);
    }

    /**
     * {@code DELETE  /m-timeattack-ranking-reward-groups/:id} : delete the "id" mTimeattackRankingRewardGroup.
     *
     * @param id the id of the mTimeattackRankingRewardGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-timeattack-ranking-reward-groups/{id}")
    public ResponseEntity<Void> deleteMTimeattackRankingRewardGroup(@PathVariable Long id) {
        log.debug("REST request to delete MTimeattackRankingRewardGroup : {}", id);
        mTimeattackRankingRewardGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
