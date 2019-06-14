package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTargetActionTypeGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTargetActionTypeGroupDTO;
import io.shm.tsubasa.service.dto.MTargetActionTypeGroupCriteria;
import io.shm.tsubasa.service.MTargetActionTypeGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTargetActionTypeGroup}.
 */
@RestController
@RequestMapping("/api")
public class MTargetActionTypeGroupResource {

    private final Logger log = LoggerFactory.getLogger(MTargetActionTypeGroupResource.class);

    private static final String ENTITY_NAME = "mTargetActionTypeGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTargetActionTypeGroupService mTargetActionTypeGroupService;

    private final MTargetActionTypeGroupQueryService mTargetActionTypeGroupQueryService;

    public MTargetActionTypeGroupResource(MTargetActionTypeGroupService mTargetActionTypeGroupService, MTargetActionTypeGroupQueryService mTargetActionTypeGroupQueryService) {
        this.mTargetActionTypeGroupService = mTargetActionTypeGroupService;
        this.mTargetActionTypeGroupQueryService = mTargetActionTypeGroupQueryService;
    }

    /**
     * {@code POST  /m-target-action-type-groups} : Create a new mTargetActionTypeGroup.
     *
     * @param mTargetActionTypeGroupDTO the mTargetActionTypeGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTargetActionTypeGroupDTO, or with status {@code 400 (Bad Request)} if the mTargetActionTypeGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-target-action-type-groups")
    public ResponseEntity<MTargetActionTypeGroupDTO> createMTargetActionTypeGroup(@Valid @RequestBody MTargetActionTypeGroupDTO mTargetActionTypeGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MTargetActionTypeGroup : {}", mTargetActionTypeGroupDTO);
        if (mTargetActionTypeGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTargetActionTypeGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTargetActionTypeGroupDTO result = mTargetActionTypeGroupService.save(mTargetActionTypeGroupDTO);
        return ResponseEntity.created(new URI("/api/m-target-action-type-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-target-action-type-groups} : Updates an existing mTargetActionTypeGroup.
     *
     * @param mTargetActionTypeGroupDTO the mTargetActionTypeGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTargetActionTypeGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mTargetActionTypeGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTargetActionTypeGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-target-action-type-groups")
    public ResponseEntity<MTargetActionTypeGroupDTO> updateMTargetActionTypeGroup(@Valid @RequestBody MTargetActionTypeGroupDTO mTargetActionTypeGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MTargetActionTypeGroup : {}", mTargetActionTypeGroupDTO);
        if (mTargetActionTypeGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTargetActionTypeGroupDTO result = mTargetActionTypeGroupService.save(mTargetActionTypeGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTargetActionTypeGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-target-action-type-groups} : get all the mTargetActionTypeGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTargetActionTypeGroups in body.
     */
    @GetMapping("/m-target-action-type-groups")
    public ResponseEntity<List<MTargetActionTypeGroupDTO>> getAllMTargetActionTypeGroups(MTargetActionTypeGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTargetActionTypeGroups by criteria: {}", criteria);
        Page<MTargetActionTypeGroupDTO> page = mTargetActionTypeGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-target-action-type-groups/count} : count all the mTargetActionTypeGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-target-action-type-groups/count")
    public ResponseEntity<Long> countMTargetActionTypeGroups(MTargetActionTypeGroupCriteria criteria) {
        log.debug("REST request to count MTargetActionTypeGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTargetActionTypeGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-target-action-type-groups/:id} : get the "id" mTargetActionTypeGroup.
     *
     * @param id the id of the mTargetActionTypeGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTargetActionTypeGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-target-action-type-groups/{id}")
    public ResponseEntity<MTargetActionTypeGroupDTO> getMTargetActionTypeGroup(@PathVariable Long id) {
        log.debug("REST request to get MTargetActionTypeGroup : {}", id);
        Optional<MTargetActionTypeGroupDTO> mTargetActionTypeGroupDTO = mTargetActionTypeGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTargetActionTypeGroupDTO);
    }

    /**
     * {@code DELETE  /m-target-action-type-groups/:id} : delete the "id" mTargetActionTypeGroup.
     *
     * @param id the id of the mTargetActionTypeGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-target-action-type-groups/{id}")
    public ResponseEntity<Void> deleteMTargetActionTypeGroup(@PathVariable Long id) {
        log.debug("REST request to delete MTargetActionTypeGroup : {}", id);
        mTargetActionTypeGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
