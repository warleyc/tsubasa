package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTargetActionGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTargetActionGroupDTO;
import io.shm.tsubasa.service.dto.MTargetActionGroupCriteria;
import io.shm.tsubasa.service.MTargetActionGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTargetActionGroup}.
 */
@RestController
@RequestMapping("/api")
public class MTargetActionGroupResource {

    private final Logger log = LoggerFactory.getLogger(MTargetActionGroupResource.class);

    private static final String ENTITY_NAME = "mTargetActionGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTargetActionGroupService mTargetActionGroupService;

    private final MTargetActionGroupQueryService mTargetActionGroupQueryService;

    public MTargetActionGroupResource(MTargetActionGroupService mTargetActionGroupService, MTargetActionGroupQueryService mTargetActionGroupQueryService) {
        this.mTargetActionGroupService = mTargetActionGroupService;
        this.mTargetActionGroupQueryService = mTargetActionGroupQueryService;
    }

    /**
     * {@code POST  /m-target-action-groups} : Create a new mTargetActionGroup.
     *
     * @param mTargetActionGroupDTO the mTargetActionGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTargetActionGroupDTO, or with status {@code 400 (Bad Request)} if the mTargetActionGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-target-action-groups")
    public ResponseEntity<MTargetActionGroupDTO> createMTargetActionGroup(@Valid @RequestBody MTargetActionGroupDTO mTargetActionGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MTargetActionGroup : {}", mTargetActionGroupDTO);
        if (mTargetActionGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTargetActionGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTargetActionGroupDTO result = mTargetActionGroupService.save(mTargetActionGroupDTO);
        return ResponseEntity.created(new URI("/api/m-target-action-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-target-action-groups} : Updates an existing mTargetActionGroup.
     *
     * @param mTargetActionGroupDTO the mTargetActionGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTargetActionGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mTargetActionGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTargetActionGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-target-action-groups")
    public ResponseEntity<MTargetActionGroupDTO> updateMTargetActionGroup(@Valid @RequestBody MTargetActionGroupDTO mTargetActionGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MTargetActionGroup : {}", mTargetActionGroupDTO);
        if (mTargetActionGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTargetActionGroupDTO result = mTargetActionGroupService.save(mTargetActionGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTargetActionGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-target-action-groups} : get all the mTargetActionGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTargetActionGroups in body.
     */
    @GetMapping("/m-target-action-groups")
    public ResponseEntity<List<MTargetActionGroupDTO>> getAllMTargetActionGroups(MTargetActionGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTargetActionGroups by criteria: {}", criteria);
        Page<MTargetActionGroupDTO> page = mTargetActionGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-target-action-groups/count} : count all the mTargetActionGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-target-action-groups/count")
    public ResponseEntity<Long> countMTargetActionGroups(MTargetActionGroupCriteria criteria) {
        log.debug("REST request to count MTargetActionGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTargetActionGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-target-action-groups/:id} : get the "id" mTargetActionGroup.
     *
     * @param id the id of the mTargetActionGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTargetActionGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-target-action-groups/{id}")
    public ResponseEntity<MTargetActionGroupDTO> getMTargetActionGroup(@PathVariable Long id) {
        log.debug("REST request to get MTargetActionGroup : {}", id);
        Optional<MTargetActionGroupDTO> mTargetActionGroupDTO = mTargetActionGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTargetActionGroupDTO);
    }

    /**
     * {@code DELETE  /m-target-action-groups/:id} : delete the "id" mTargetActionGroup.
     *
     * @param id the id of the mTargetActionGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-target-action-groups/{id}")
    public ResponseEntity<Void> deleteMTargetActionGroup(@PathVariable Long id) {
        log.debug("REST request to delete MTargetActionGroup : {}", id);
        mTargetActionGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
