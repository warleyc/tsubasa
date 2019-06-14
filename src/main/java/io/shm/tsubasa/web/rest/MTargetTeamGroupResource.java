package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTargetTeamGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTargetTeamGroupDTO;
import io.shm.tsubasa.service.dto.MTargetTeamGroupCriteria;
import io.shm.tsubasa.service.MTargetTeamGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTargetTeamGroup}.
 */
@RestController
@RequestMapping("/api")
public class MTargetTeamGroupResource {

    private final Logger log = LoggerFactory.getLogger(MTargetTeamGroupResource.class);

    private static final String ENTITY_NAME = "mTargetTeamGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTargetTeamGroupService mTargetTeamGroupService;

    private final MTargetTeamGroupQueryService mTargetTeamGroupQueryService;

    public MTargetTeamGroupResource(MTargetTeamGroupService mTargetTeamGroupService, MTargetTeamGroupQueryService mTargetTeamGroupQueryService) {
        this.mTargetTeamGroupService = mTargetTeamGroupService;
        this.mTargetTeamGroupQueryService = mTargetTeamGroupQueryService;
    }

    /**
     * {@code POST  /m-target-team-groups} : Create a new mTargetTeamGroup.
     *
     * @param mTargetTeamGroupDTO the mTargetTeamGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTargetTeamGroupDTO, or with status {@code 400 (Bad Request)} if the mTargetTeamGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-target-team-groups")
    public ResponseEntity<MTargetTeamGroupDTO> createMTargetTeamGroup(@Valid @RequestBody MTargetTeamGroupDTO mTargetTeamGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MTargetTeamGroup : {}", mTargetTeamGroupDTO);
        if (mTargetTeamGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTargetTeamGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTargetTeamGroupDTO result = mTargetTeamGroupService.save(mTargetTeamGroupDTO);
        return ResponseEntity.created(new URI("/api/m-target-team-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-target-team-groups} : Updates an existing mTargetTeamGroup.
     *
     * @param mTargetTeamGroupDTO the mTargetTeamGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTargetTeamGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mTargetTeamGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTargetTeamGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-target-team-groups")
    public ResponseEntity<MTargetTeamGroupDTO> updateMTargetTeamGroup(@Valid @RequestBody MTargetTeamGroupDTO mTargetTeamGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MTargetTeamGroup : {}", mTargetTeamGroupDTO);
        if (mTargetTeamGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTargetTeamGroupDTO result = mTargetTeamGroupService.save(mTargetTeamGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTargetTeamGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-target-team-groups} : get all the mTargetTeamGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTargetTeamGroups in body.
     */
    @GetMapping("/m-target-team-groups")
    public ResponseEntity<List<MTargetTeamGroupDTO>> getAllMTargetTeamGroups(MTargetTeamGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTargetTeamGroups by criteria: {}", criteria);
        Page<MTargetTeamGroupDTO> page = mTargetTeamGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-target-team-groups/count} : count all the mTargetTeamGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-target-team-groups/count")
    public ResponseEntity<Long> countMTargetTeamGroups(MTargetTeamGroupCriteria criteria) {
        log.debug("REST request to count MTargetTeamGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTargetTeamGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-target-team-groups/:id} : get the "id" mTargetTeamGroup.
     *
     * @param id the id of the mTargetTeamGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTargetTeamGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-target-team-groups/{id}")
    public ResponseEntity<MTargetTeamGroupDTO> getMTargetTeamGroup(@PathVariable Long id) {
        log.debug("REST request to get MTargetTeamGroup : {}", id);
        Optional<MTargetTeamGroupDTO> mTargetTeamGroupDTO = mTargetTeamGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTargetTeamGroupDTO);
    }

    /**
     * {@code DELETE  /m-target-team-groups/:id} : delete the "id" mTargetTeamGroup.
     *
     * @param id the id of the mTargetTeamGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-target-team-groups/{id}")
    public ResponseEntity<Void> deleteMTargetTeamGroup(@PathVariable Long id) {
        log.debug("REST request to delete MTargetTeamGroup : {}", id);
        mTargetTeamGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
