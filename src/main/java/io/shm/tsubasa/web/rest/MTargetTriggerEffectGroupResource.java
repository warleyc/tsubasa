package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTargetTriggerEffectGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTargetTriggerEffectGroupDTO;
import io.shm.tsubasa.service.dto.MTargetTriggerEffectGroupCriteria;
import io.shm.tsubasa.service.MTargetTriggerEffectGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTargetTriggerEffectGroup}.
 */
@RestController
@RequestMapping("/api")
public class MTargetTriggerEffectGroupResource {

    private final Logger log = LoggerFactory.getLogger(MTargetTriggerEffectGroupResource.class);

    private static final String ENTITY_NAME = "mTargetTriggerEffectGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTargetTriggerEffectGroupService mTargetTriggerEffectGroupService;

    private final MTargetTriggerEffectGroupQueryService mTargetTriggerEffectGroupQueryService;

    public MTargetTriggerEffectGroupResource(MTargetTriggerEffectGroupService mTargetTriggerEffectGroupService, MTargetTriggerEffectGroupQueryService mTargetTriggerEffectGroupQueryService) {
        this.mTargetTriggerEffectGroupService = mTargetTriggerEffectGroupService;
        this.mTargetTriggerEffectGroupQueryService = mTargetTriggerEffectGroupQueryService;
    }

    /**
     * {@code POST  /m-target-trigger-effect-groups} : Create a new mTargetTriggerEffectGroup.
     *
     * @param mTargetTriggerEffectGroupDTO the mTargetTriggerEffectGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTargetTriggerEffectGroupDTO, or with status {@code 400 (Bad Request)} if the mTargetTriggerEffectGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-target-trigger-effect-groups")
    public ResponseEntity<MTargetTriggerEffectGroupDTO> createMTargetTriggerEffectGroup(@Valid @RequestBody MTargetTriggerEffectGroupDTO mTargetTriggerEffectGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MTargetTriggerEffectGroup : {}", mTargetTriggerEffectGroupDTO);
        if (mTargetTriggerEffectGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTargetTriggerEffectGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTargetTriggerEffectGroupDTO result = mTargetTriggerEffectGroupService.save(mTargetTriggerEffectGroupDTO);
        return ResponseEntity.created(new URI("/api/m-target-trigger-effect-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-target-trigger-effect-groups} : Updates an existing mTargetTriggerEffectGroup.
     *
     * @param mTargetTriggerEffectGroupDTO the mTargetTriggerEffectGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTargetTriggerEffectGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mTargetTriggerEffectGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTargetTriggerEffectGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-target-trigger-effect-groups")
    public ResponseEntity<MTargetTriggerEffectGroupDTO> updateMTargetTriggerEffectGroup(@Valid @RequestBody MTargetTriggerEffectGroupDTO mTargetTriggerEffectGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MTargetTriggerEffectGroup : {}", mTargetTriggerEffectGroupDTO);
        if (mTargetTriggerEffectGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTargetTriggerEffectGroupDTO result = mTargetTriggerEffectGroupService.save(mTargetTriggerEffectGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTargetTriggerEffectGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-target-trigger-effect-groups} : get all the mTargetTriggerEffectGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTargetTriggerEffectGroups in body.
     */
    @GetMapping("/m-target-trigger-effect-groups")
    public ResponseEntity<List<MTargetTriggerEffectGroupDTO>> getAllMTargetTriggerEffectGroups(MTargetTriggerEffectGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTargetTriggerEffectGroups by criteria: {}", criteria);
        Page<MTargetTriggerEffectGroupDTO> page = mTargetTriggerEffectGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-target-trigger-effect-groups/count} : count all the mTargetTriggerEffectGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-target-trigger-effect-groups/count")
    public ResponseEntity<Long> countMTargetTriggerEffectGroups(MTargetTriggerEffectGroupCriteria criteria) {
        log.debug("REST request to count MTargetTriggerEffectGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTargetTriggerEffectGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-target-trigger-effect-groups/:id} : get the "id" mTargetTriggerEffectGroup.
     *
     * @param id the id of the mTargetTriggerEffectGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTargetTriggerEffectGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-target-trigger-effect-groups/{id}")
    public ResponseEntity<MTargetTriggerEffectGroupDTO> getMTargetTriggerEffectGroup(@PathVariable Long id) {
        log.debug("REST request to get MTargetTriggerEffectGroup : {}", id);
        Optional<MTargetTriggerEffectGroupDTO> mTargetTriggerEffectGroupDTO = mTargetTriggerEffectGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTargetTriggerEffectGroupDTO);
    }

    /**
     * {@code DELETE  /m-target-trigger-effect-groups/:id} : delete the "id" mTargetTriggerEffectGroup.
     *
     * @param id the id of the mTargetTriggerEffectGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-target-trigger-effect-groups/{id}")
    public ResponseEntity<Void> deleteMTargetTriggerEffectGroup(@PathVariable Long id) {
        log.debug("REST request to delete MTargetTriggerEffectGroup : {}", id);
        mTargetTriggerEffectGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
