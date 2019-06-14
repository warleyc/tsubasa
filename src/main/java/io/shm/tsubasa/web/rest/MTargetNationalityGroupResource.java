package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTargetNationalityGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTargetNationalityGroupDTO;
import io.shm.tsubasa.service.dto.MTargetNationalityGroupCriteria;
import io.shm.tsubasa.service.MTargetNationalityGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTargetNationalityGroup}.
 */
@RestController
@RequestMapping("/api")
public class MTargetNationalityGroupResource {

    private final Logger log = LoggerFactory.getLogger(MTargetNationalityGroupResource.class);

    private static final String ENTITY_NAME = "mTargetNationalityGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTargetNationalityGroupService mTargetNationalityGroupService;

    private final MTargetNationalityGroupQueryService mTargetNationalityGroupQueryService;

    public MTargetNationalityGroupResource(MTargetNationalityGroupService mTargetNationalityGroupService, MTargetNationalityGroupQueryService mTargetNationalityGroupQueryService) {
        this.mTargetNationalityGroupService = mTargetNationalityGroupService;
        this.mTargetNationalityGroupQueryService = mTargetNationalityGroupQueryService;
    }

    /**
     * {@code POST  /m-target-nationality-groups} : Create a new mTargetNationalityGroup.
     *
     * @param mTargetNationalityGroupDTO the mTargetNationalityGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTargetNationalityGroupDTO, or with status {@code 400 (Bad Request)} if the mTargetNationalityGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-target-nationality-groups")
    public ResponseEntity<MTargetNationalityGroupDTO> createMTargetNationalityGroup(@Valid @RequestBody MTargetNationalityGroupDTO mTargetNationalityGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MTargetNationalityGroup : {}", mTargetNationalityGroupDTO);
        if (mTargetNationalityGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTargetNationalityGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTargetNationalityGroupDTO result = mTargetNationalityGroupService.save(mTargetNationalityGroupDTO);
        return ResponseEntity.created(new URI("/api/m-target-nationality-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-target-nationality-groups} : Updates an existing mTargetNationalityGroup.
     *
     * @param mTargetNationalityGroupDTO the mTargetNationalityGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTargetNationalityGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mTargetNationalityGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTargetNationalityGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-target-nationality-groups")
    public ResponseEntity<MTargetNationalityGroupDTO> updateMTargetNationalityGroup(@Valid @RequestBody MTargetNationalityGroupDTO mTargetNationalityGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MTargetNationalityGroup : {}", mTargetNationalityGroupDTO);
        if (mTargetNationalityGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTargetNationalityGroupDTO result = mTargetNationalityGroupService.save(mTargetNationalityGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTargetNationalityGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-target-nationality-groups} : get all the mTargetNationalityGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTargetNationalityGroups in body.
     */
    @GetMapping("/m-target-nationality-groups")
    public ResponseEntity<List<MTargetNationalityGroupDTO>> getAllMTargetNationalityGroups(MTargetNationalityGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTargetNationalityGroups by criteria: {}", criteria);
        Page<MTargetNationalityGroupDTO> page = mTargetNationalityGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-target-nationality-groups/count} : count all the mTargetNationalityGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-target-nationality-groups/count")
    public ResponseEntity<Long> countMTargetNationalityGroups(MTargetNationalityGroupCriteria criteria) {
        log.debug("REST request to count MTargetNationalityGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTargetNationalityGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-target-nationality-groups/:id} : get the "id" mTargetNationalityGroup.
     *
     * @param id the id of the mTargetNationalityGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTargetNationalityGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-target-nationality-groups/{id}")
    public ResponseEntity<MTargetNationalityGroupDTO> getMTargetNationalityGroup(@PathVariable Long id) {
        log.debug("REST request to get MTargetNationalityGroup : {}", id);
        Optional<MTargetNationalityGroupDTO> mTargetNationalityGroupDTO = mTargetNationalityGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTargetNationalityGroupDTO);
    }

    /**
     * {@code DELETE  /m-target-nationality-groups/:id} : delete the "id" mTargetNationalityGroup.
     *
     * @param id the id of the mTargetNationalityGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-target-nationality-groups/{id}")
    public ResponseEntity<Void> deleteMTargetNationalityGroup(@PathVariable Long id) {
        log.debug("REST request to delete MTargetNationalityGroup : {}", id);
        mTargetNationalityGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
