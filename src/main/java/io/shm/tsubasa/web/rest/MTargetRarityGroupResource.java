package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTargetRarityGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTargetRarityGroupDTO;
import io.shm.tsubasa.service.dto.MTargetRarityGroupCriteria;
import io.shm.tsubasa.service.MTargetRarityGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTargetRarityGroup}.
 */
@RestController
@RequestMapping("/api")
public class MTargetRarityGroupResource {

    private final Logger log = LoggerFactory.getLogger(MTargetRarityGroupResource.class);

    private static final String ENTITY_NAME = "mTargetRarityGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTargetRarityGroupService mTargetRarityGroupService;

    private final MTargetRarityGroupQueryService mTargetRarityGroupQueryService;

    public MTargetRarityGroupResource(MTargetRarityGroupService mTargetRarityGroupService, MTargetRarityGroupQueryService mTargetRarityGroupQueryService) {
        this.mTargetRarityGroupService = mTargetRarityGroupService;
        this.mTargetRarityGroupQueryService = mTargetRarityGroupQueryService;
    }

    /**
     * {@code POST  /m-target-rarity-groups} : Create a new mTargetRarityGroup.
     *
     * @param mTargetRarityGroupDTO the mTargetRarityGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTargetRarityGroupDTO, or with status {@code 400 (Bad Request)} if the mTargetRarityGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-target-rarity-groups")
    public ResponseEntity<MTargetRarityGroupDTO> createMTargetRarityGroup(@Valid @RequestBody MTargetRarityGroupDTO mTargetRarityGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MTargetRarityGroup : {}", mTargetRarityGroupDTO);
        if (mTargetRarityGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTargetRarityGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTargetRarityGroupDTO result = mTargetRarityGroupService.save(mTargetRarityGroupDTO);
        return ResponseEntity.created(new URI("/api/m-target-rarity-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-target-rarity-groups} : Updates an existing mTargetRarityGroup.
     *
     * @param mTargetRarityGroupDTO the mTargetRarityGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTargetRarityGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mTargetRarityGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTargetRarityGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-target-rarity-groups")
    public ResponseEntity<MTargetRarityGroupDTO> updateMTargetRarityGroup(@Valid @RequestBody MTargetRarityGroupDTO mTargetRarityGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MTargetRarityGroup : {}", mTargetRarityGroupDTO);
        if (mTargetRarityGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTargetRarityGroupDTO result = mTargetRarityGroupService.save(mTargetRarityGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTargetRarityGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-target-rarity-groups} : get all the mTargetRarityGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTargetRarityGroups in body.
     */
    @GetMapping("/m-target-rarity-groups")
    public ResponseEntity<List<MTargetRarityGroupDTO>> getAllMTargetRarityGroups(MTargetRarityGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTargetRarityGroups by criteria: {}", criteria);
        Page<MTargetRarityGroupDTO> page = mTargetRarityGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-target-rarity-groups/count} : count all the mTargetRarityGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-target-rarity-groups/count")
    public ResponseEntity<Long> countMTargetRarityGroups(MTargetRarityGroupCriteria criteria) {
        log.debug("REST request to count MTargetRarityGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTargetRarityGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-target-rarity-groups/:id} : get the "id" mTargetRarityGroup.
     *
     * @param id the id of the mTargetRarityGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTargetRarityGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-target-rarity-groups/{id}")
    public ResponseEntity<MTargetRarityGroupDTO> getMTargetRarityGroup(@PathVariable Long id) {
        log.debug("REST request to get MTargetRarityGroup : {}", id);
        Optional<MTargetRarityGroupDTO> mTargetRarityGroupDTO = mTargetRarityGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTargetRarityGroupDTO);
    }

    /**
     * {@code DELETE  /m-target-rarity-groups/:id} : delete the "id" mTargetRarityGroup.
     *
     * @param id the id of the mTargetRarityGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-target-rarity-groups/{id}")
    public ResponseEntity<Void> deleteMTargetRarityGroup(@PathVariable Long id) {
        log.debug("REST request to delete MTargetRarityGroup : {}", id);
        mTargetRarityGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
