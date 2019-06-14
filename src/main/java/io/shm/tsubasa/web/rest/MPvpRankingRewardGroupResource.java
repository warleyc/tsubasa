package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MPvpRankingRewardGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MPvpRankingRewardGroupDTO;
import io.shm.tsubasa.service.dto.MPvpRankingRewardGroupCriteria;
import io.shm.tsubasa.service.MPvpRankingRewardGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MPvpRankingRewardGroup}.
 */
@RestController
@RequestMapping("/api")
public class MPvpRankingRewardGroupResource {

    private final Logger log = LoggerFactory.getLogger(MPvpRankingRewardGroupResource.class);

    private static final String ENTITY_NAME = "mPvpRankingRewardGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MPvpRankingRewardGroupService mPvpRankingRewardGroupService;

    private final MPvpRankingRewardGroupQueryService mPvpRankingRewardGroupQueryService;

    public MPvpRankingRewardGroupResource(MPvpRankingRewardGroupService mPvpRankingRewardGroupService, MPvpRankingRewardGroupQueryService mPvpRankingRewardGroupQueryService) {
        this.mPvpRankingRewardGroupService = mPvpRankingRewardGroupService;
        this.mPvpRankingRewardGroupQueryService = mPvpRankingRewardGroupQueryService;
    }

    /**
     * {@code POST  /m-pvp-ranking-reward-groups} : Create a new mPvpRankingRewardGroup.
     *
     * @param mPvpRankingRewardGroupDTO the mPvpRankingRewardGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mPvpRankingRewardGroupDTO, or with status {@code 400 (Bad Request)} if the mPvpRankingRewardGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-pvp-ranking-reward-groups")
    public ResponseEntity<MPvpRankingRewardGroupDTO> createMPvpRankingRewardGroup(@Valid @RequestBody MPvpRankingRewardGroupDTO mPvpRankingRewardGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MPvpRankingRewardGroup : {}", mPvpRankingRewardGroupDTO);
        if (mPvpRankingRewardGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mPvpRankingRewardGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MPvpRankingRewardGroupDTO result = mPvpRankingRewardGroupService.save(mPvpRankingRewardGroupDTO);
        return ResponseEntity.created(new URI("/api/m-pvp-ranking-reward-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-pvp-ranking-reward-groups} : Updates an existing mPvpRankingRewardGroup.
     *
     * @param mPvpRankingRewardGroupDTO the mPvpRankingRewardGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mPvpRankingRewardGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mPvpRankingRewardGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mPvpRankingRewardGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-pvp-ranking-reward-groups")
    public ResponseEntity<MPvpRankingRewardGroupDTO> updateMPvpRankingRewardGroup(@Valid @RequestBody MPvpRankingRewardGroupDTO mPvpRankingRewardGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MPvpRankingRewardGroup : {}", mPvpRankingRewardGroupDTO);
        if (mPvpRankingRewardGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MPvpRankingRewardGroupDTO result = mPvpRankingRewardGroupService.save(mPvpRankingRewardGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mPvpRankingRewardGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-pvp-ranking-reward-groups} : get all the mPvpRankingRewardGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mPvpRankingRewardGroups in body.
     */
    @GetMapping("/m-pvp-ranking-reward-groups")
    public ResponseEntity<List<MPvpRankingRewardGroupDTO>> getAllMPvpRankingRewardGroups(MPvpRankingRewardGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MPvpRankingRewardGroups by criteria: {}", criteria);
        Page<MPvpRankingRewardGroupDTO> page = mPvpRankingRewardGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-pvp-ranking-reward-groups/count} : count all the mPvpRankingRewardGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-pvp-ranking-reward-groups/count")
    public ResponseEntity<Long> countMPvpRankingRewardGroups(MPvpRankingRewardGroupCriteria criteria) {
        log.debug("REST request to count MPvpRankingRewardGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mPvpRankingRewardGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-pvp-ranking-reward-groups/:id} : get the "id" mPvpRankingRewardGroup.
     *
     * @param id the id of the mPvpRankingRewardGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mPvpRankingRewardGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-pvp-ranking-reward-groups/{id}")
    public ResponseEntity<MPvpRankingRewardGroupDTO> getMPvpRankingRewardGroup(@PathVariable Long id) {
        log.debug("REST request to get MPvpRankingRewardGroup : {}", id);
        Optional<MPvpRankingRewardGroupDTO> mPvpRankingRewardGroupDTO = mPvpRankingRewardGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mPvpRankingRewardGroupDTO);
    }

    /**
     * {@code DELETE  /m-pvp-ranking-reward-groups/:id} : delete the "id" mPvpRankingRewardGroup.
     *
     * @param id the id of the mPvpRankingRewardGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-pvp-ranking-reward-groups/{id}")
    public ResponseEntity<Void> deleteMPvpRankingRewardGroup(@PathVariable Long id) {
        log.debug("REST request to delete MPvpRankingRewardGroup : {}", id);
        mPvpRankingRewardGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
