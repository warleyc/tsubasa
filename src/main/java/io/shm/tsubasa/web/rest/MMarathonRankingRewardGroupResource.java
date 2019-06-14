package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMarathonRankingRewardGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMarathonRankingRewardGroupDTO;
import io.shm.tsubasa.service.dto.MMarathonRankingRewardGroupCriteria;
import io.shm.tsubasa.service.MMarathonRankingRewardGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMarathonRankingRewardGroup}.
 */
@RestController
@RequestMapping("/api")
public class MMarathonRankingRewardGroupResource {

    private final Logger log = LoggerFactory.getLogger(MMarathonRankingRewardGroupResource.class);

    private static final String ENTITY_NAME = "mMarathonRankingRewardGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMarathonRankingRewardGroupService mMarathonRankingRewardGroupService;

    private final MMarathonRankingRewardGroupQueryService mMarathonRankingRewardGroupQueryService;

    public MMarathonRankingRewardGroupResource(MMarathonRankingRewardGroupService mMarathonRankingRewardGroupService, MMarathonRankingRewardGroupQueryService mMarathonRankingRewardGroupQueryService) {
        this.mMarathonRankingRewardGroupService = mMarathonRankingRewardGroupService;
        this.mMarathonRankingRewardGroupQueryService = mMarathonRankingRewardGroupQueryService;
    }

    /**
     * {@code POST  /m-marathon-ranking-reward-groups} : Create a new mMarathonRankingRewardGroup.
     *
     * @param mMarathonRankingRewardGroupDTO the mMarathonRankingRewardGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMarathonRankingRewardGroupDTO, or with status {@code 400 (Bad Request)} if the mMarathonRankingRewardGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-marathon-ranking-reward-groups")
    public ResponseEntity<MMarathonRankingRewardGroupDTO> createMMarathonRankingRewardGroup(@Valid @RequestBody MMarathonRankingRewardGroupDTO mMarathonRankingRewardGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MMarathonRankingRewardGroup : {}", mMarathonRankingRewardGroupDTO);
        if (mMarathonRankingRewardGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMarathonRankingRewardGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMarathonRankingRewardGroupDTO result = mMarathonRankingRewardGroupService.save(mMarathonRankingRewardGroupDTO);
        return ResponseEntity.created(new URI("/api/m-marathon-ranking-reward-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-marathon-ranking-reward-groups} : Updates an existing mMarathonRankingRewardGroup.
     *
     * @param mMarathonRankingRewardGroupDTO the mMarathonRankingRewardGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMarathonRankingRewardGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mMarathonRankingRewardGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMarathonRankingRewardGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-marathon-ranking-reward-groups")
    public ResponseEntity<MMarathonRankingRewardGroupDTO> updateMMarathonRankingRewardGroup(@Valid @RequestBody MMarathonRankingRewardGroupDTO mMarathonRankingRewardGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MMarathonRankingRewardGroup : {}", mMarathonRankingRewardGroupDTO);
        if (mMarathonRankingRewardGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMarathonRankingRewardGroupDTO result = mMarathonRankingRewardGroupService.save(mMarathonRankingRewardGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMarathonRankingRewardGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-marathon-ranking-reward-groups} : get all the mMarathonRankingRewardGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMarathonRankingRewardGroups in body.
     */
    @GetMapping("/m-marathon-ranking-reward-groups")
    public ResponseEntity<List<MMarathonRankingRewardGroupDTO>> getAllMMarathonRankingRewardGroups(MMarathonRankingRewardGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMarathonRankingRewardGroups by criteria: {}", criteria);
        Page<MMarathonRankingRewardGroupDTO> page = mMarathonRankingRewardGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-marathon-ranking-reward-groups/count} : count all the mMarathonRankingRewardGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-marathon-ranking-reward-groups/count")
    public ResponseEntity<Long> countMMarathonRankingRewardGroups(MMarathonRankingRewardGroupCriteria criteria) {
        log.debug("REST request to count MMarathonRankingRewardGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMarathonRankingRewardGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-marathon-ranking-reward-groups/:id} : get the "id" mMarathonRankingRewardGroup.
     *
     * @param id the id of the mMarathonRankingRewardGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMarathonRankingRewardGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-marathon-ranking-reward-groups/{id}")
    public ResponseEntity<MMarathonRankingRewardGroupDTO> getMMarathonRankingRewardGroup(@PathVariable Long id) {
        log.debug("REST request to get MMarathonRankingRewardGroup : {}", id);
        Optional<MMarathonRankingRewardGroupDTO> mMarathonRankingRewardGroupDTO = mMarathonRankingRewardGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMarathonRankingRewardGroupDTO);
    }

    /**
     * {@code DELETE  /m-marathon-ranking-reward-groups/:id} : delete the "id" mMarathonRankingRewardGroup.
     *
     * @param id the id of the mMarathonRankingRewardGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-marathon-ranking-reward-groups/{id}")
    public ResponseEntity<Void> deleteMMarathonRankingRewardGroup(@PathVariable Long id) {
        log.debug("REST request to delete MMarathonRankingRewardGroup : {}", id);
        mMarathonRankingRewardGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
