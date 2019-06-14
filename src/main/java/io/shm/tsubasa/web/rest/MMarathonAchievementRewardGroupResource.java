package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMarathonAchievementRewardGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMarathonAchievementRewardGroupDTO;
import io.shm.tsubasa.service.dto.MMarathonAchievementRewardGroupCriteria;
import io.shm.tsubasa.service.MMarathonAchievementRewardGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMarathonAchievementRewardGroup}.
 */
@RestController
@RequestMapping("/api")
public class MMarathonAchievementRewardGroupResource {

    private final Logger log = LoggerFactory.getLogger(MMarathonAchievementRewardGroupResource.class);

    private static final String ENTITY_NAME = "mMarathonAchievementRewardGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMarathonAchievementRewardGroupService mMarathonAchievementRewardGroupService;

    private final MMarathonAchievementRewardGroupQueryService mMarathonAchievementRewardGroupQueryService;

    public MMarathonAchievementRewardGroupResource(MMarathonAchievementRewardGroupService mMarathonAchievementRewardGroupService, MMarathonAchievementRewardGroupQueryService mMarathonAchievementRewardGroupQueryService) {
        this.mMarathonAchievementRewardGroupService = mMarathonAchievementRewardGroupService;
        this.mMarathonAchievementRewardGroupQueryService = mMarathonAchievementRewardGroupQueryService;
    }

    /**
     * {@code POST  /m-marathon-achievement-reward-groups} : Create a new mMarathonAchievementRewardGroup.
     *
     * @param mMarathonAchievementRewardGroupDTO the mMarathonAchievementRewardGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMarathonAchievementRewardGroupDTO, or with status {@code 400 (Bad Request)} if the mMarathonAchievementRewardGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-marathon-achievement-reward-groups")
    public ResponseEntity<MMarathonAchievementRewardGroupDTO> createMMarathonAchievementRewardGroup(@Valid @RequestBody MMarathonAchievementRewardGroupDTO mMarathonAchievementRewardGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MMarathonAchievementRewardGroup : {}", mMarathonAchievementRewardGroupDTO);
        if (mMarathonAchievementRewardGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMarathonAchievementRewardGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMarathonAchievementRewardGroupDTO result = mMarathonAchievementRewardGroupService.save(mMarathonAchievementRewardGroupDTO);
        return ResponseEntity.created(new URI("/api/m-marathon-achievement-reward-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-marathon-achievement-reward-groups} : Updates an existing mMarathonAchievementRewardGroup.
     *
     * @param mMarathonAchievementRewardGroupDTO the mMarathonAchievementRewardGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMarathonAchievementRewardGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mMarathonAchievementRewardGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMarathonAchievementRewardGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-marathon-achievement-reward-groups")
    public ResponseEntity<MMarathonAchievementRewardGroupDTO> updateMMarathonAchievementRewardGroup(@Valid @RequestBody MMarathonAchievementRewardGroupDTO mMarathonAchievementRewardGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MMarathonAchievementRewardGroup : {}", mMarathonAchievementRewardGroupDTO);
        if (mMarathonAchievementRewardGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMarathonAchievementRewardGroupDTO result = mMarathonAchievementRewardGroupService.save(mMarathonAchievementRewardGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMarathonAchievementRewardGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-marathon-achievement-reward-groups} : get all the mMarathonAchievementRewardGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMarathonAchievementRewardGroups in body.
     */
    @GetMapping("/m-marathon-achievement-reward-groups")
    public ResponseEntity<List<MMarathonAchievementRewardGroupDTO>> getAllMMarathonAchievementRewardGroups(MMarathonAchievementRewardGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMarathonAchievementRewardGroups by criteria: {}", criteria);
        Page<MMarathonAchievementRewardGroupDTO> page = mMarathonAchievementRewardGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-marathon-achievement-reward-groups/count} : count all the mMarathonAchievementRewardGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-marathon-achievement-reward-groups/count")
    public ResponseEntity<Long> countMMarathonAchievementRewardGroups(MMarathonAchievementRewardGroupCriteria criteria) {
        log.debug("REST request to count MMarathonAchievementRewardGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMarathonAchievementRewardGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-marathon-achievement-reward-groups/:id} : get the "id" mMarathonAchievementRewardGroup.
     *
     * @param id the id of the mMarathonAchievementRewardGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMarathonAchievementRewardGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-marathon-achievement-reward-groups/{id}")
    public ResponseEntity<MMarathonAchievementRewardGroupDTO> getMMarathonAchievementRewardGroup(@PathVariable Long id) {
        log.debug("REST request to get MMarathonAchievementRewardGroup : {}", id);
        Optional<MMarathonAchievementRewardGroupDTO> mMarathonAchievementRewardGroupDTO = mMarathonAchievementRewardGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMarathonAchievementRewardGroupDTO);
    }

    /**
     * {@code DELETE  /m-marathon-achievement-reward-groups/:id} : delete the "id" mMarathonAchievementRewardGroup.
     *
     * @param id the id of the mMarathonAchievementRewardGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-marathon-achievement-reward-groups/{id}")
    public ResponseEntity<Void> deleteMMarathonAchievementRewardGroup(@PathVariable Long id) {
        log.debug("REST request to delete MMarathonAchievementRewardGroup : {}", id);
        mMarathonAchievementRewardGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
