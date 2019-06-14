package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTargetFormationGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTargetFormationGroupDTO;
import io.shm.tsubasa.service.dto.MTargetFormationGroupCriteria;
import io.shm.tsubasa.service.MTargetFormationGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTargetFormationGroup}.
 */
@RestController
@RequestMapping("/api")
public class MTargetFormationGroupResource {

    private final Logger log = LoggerFactory.getLogger(MTargetFormationGroupResource.class);

    private static final String ENTITY_NAME = "mTargetFormationGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTargetFormationGroupService mTargetFormationGroupService;

    private final MTargetFormationGroupQueryService mTargetFormationGroupQueryService;

    public MTargetFormationGroupResource(MTargetFormationGroupService mTargetFormationGroupService, MTargetFormationGroupQueryService mTargetFormationGroupQueryService) {
        this.mTargetFormationGroupService = mTargetFormationGroupService;
        this.mTargetFormationGroupQueryService = mTargetFormationGroupQueryService;
    }

    /**
     * {@code POST  /m-target-formation-groups} : Create a new mTargetFormationGroup.
     *
     * @param mTargetFormationGroupDTO the mTargetFormationGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTargetFormationGroupDTO, or with status {@code 400 (Bad Request)} if the mTargetFormationGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-target-formation-groups")
    public ResponseEntity<MTargetFormationGroupDTO> createMTargetFormationGroup(@Valid @RequestBody MTargetFormationGroupDTO mTargetFormationGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MTargetFormationGroup : {}", mTargetFormationGroupDTO);
        if (mTargetFormationGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTargetFormationGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTargetFormationGroupDTO result = mTargetFormationGroupService.save(mTargetFormationGroupDTO);
        return ResponseEntity.created(new URI("/api/m-target-formation-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-target-formation-groups} : Updates an existing mTargetFormationGroup.
     *
     * @param mTargetFormationGroupDTO the mTargetFormationGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTargetFormationGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mTargetFormationGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTargetFormationGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-target-formation-groups")
    public ResponseEntity<MTargetFormationGroupDTO> updateMTargetFormationGroup(@Valid @RequestBody MTargetFormationGroupDTO mTargetFormationGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MTargetFormationGroup : {}", mTargetFormationGroupDTO);
        if (mTargetFormationGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTargetFormationGroupDTO result = mTargetFormationGroupService.save(mTargetFormationGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTargetFormationGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-target-formation-groups} : get all the mTargetFormationGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTargetFormationGroups in body.
     */
    @GetMapping("/m-target-formation-groups")
    public ResponseEntity<List<MTargetFormationGroupDTO>> getAllMTargetFormationGroups(MTargetFormationGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTargetFormationGroups by criteria: {}", criteria);
        Page<MTargetFormationGroupDTO> page = mTargetFormationGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-target-formation-groups/count} : count all the mTargetFormationGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-target-formation-groups/count")
    public ResponseEntity<Long> countMTargetFormationGroups(MTargetFormationGroupCriteria criteria) {
        log.debug("REST request to count MTargetFormationGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTargetFormationGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-target-formation-groups/:id} : get the "id" mTargetFormationGroup.
     *
     * @param id the id of the mTargetFormationGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTargetFormationGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-target-formation-groups/{id}")
    public ResponseEntity<MTargetFormationGroupDTO> getMTargetFormationGroup(@PathVariable Long id) {
        log.debug("REST request to get MTargetFormationGroup : {}", id);
        Optional<MTargetFormationGroupDTO> mTargetFormationGroupDTO = mTargetFormationGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTargetFormationGroupDTO);
    }

    /**
     * {@code DELETE  /m-target-formation-groups/:id} : delete the "id" mTargetFormationGroup.
     *
     * @param id the id of the mTargetFormationGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-target-formation-groups/{id}")
    public ResponseEntity<Void> deleteMTargetFormationGroup(@PathVariable Long id) {
        log.debug("REST request to delete MTargetFormationGroup : {}", id);
        mTargetFormationGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
