package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTargetCharacterGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTargetCharacterGroupDTO;
import io.shm.tsubasa.service.dto.MTargetCharacterGroupCriteria;
import io.shm.tsubasa.service.MTargetCharacterGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTargetCharacterGroup}.
 */
@RestController
@RequestMapping("/api")
public class MTargetCharacterGroupResource {

    private final Logger log = LoggerFactory.getLogger(MTargetCharacterGroupResource.class);

    private static final String ENTITY_NAME = "mTargetCharacterGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTargetCharacterGroupService mTargetCharacterGroupService;

    private final MTargetCharacterGroupQueryService mTargetCharacterGroupQueryService;

    public MTargetCharacterGroupResource(MTargetCharacterGroupService mTargetCharacterGroupService, MTargetCharacterGroupQueryService mTargetCharacterGroupQueryService) {
        this.mTargetCharacterGroupService = mTargetCharacterGroupService;
        this.mTargetCharacterGroupQueryService = mTargetCharacterGroupQueryService;
    }

    /**
     * {@code POST  /m-target-character-groups} : Create a new mTargetCharacterGroup.
     *
     * @param mTargetCharacterGroupDTO the mTargetCharacterGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTargetCharacterGroupDTO, or with status {@code 400 (Bad Request)} if the mTargetCharacterGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-target-character-groups")
    public ResponseEntity<MTargetCharacterGroupDTO> createMTargetCharacterGroup(@Valid @RequestBody MTargetCharacterGroupDTO mTargetCharacterGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MTargetCharacterGroup : {}", mTargetCharacterGroupDTO);
        if (mTargetCharacterGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTargetCharacterGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTargetCharacterGroupDTO result = mTargetCharacterGroupService.save(mTargetCharacterGroupDTO);
        return ResponseEntity.created(new URI("/api/m-target-character-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-target-character-groups} : Updates an existing mTargetCharacterGroup.
     *
     * @param mTargetCharacterGroupDTO the mTargetCharacterGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTargetCharacterGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mTargetCharacterGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTargetCharacterGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-target-character-groups")
    public ResponseEntity<MTargetCharacterGroupDTO> updateMTargetCharacterGroup(@Valid @RequestBody MTargetCharacterGroupDTO mTargetCharacterGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MTargetCharacterGroup : {}", mTargetCharacterGroupDTO);
        if (mTargetCharacterGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTargetCharacterGroupDTO result = mTargetCharacterGroupService.save(mTargetCharacterGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTargetCharacterGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-target-character-groups} : get all the mTargetCharacterGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTargetCharacterGroups in body.
     */
    @GetMapping("/m-target-character-groups")
    public ResponseEntity<List<MTargetCharacterGroupDTO>> getAllMTargetCharacterGroups(MTargetCharacterGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTargetCharacterGroups by criteria: {}", criteria);
        Page<MTargetCharacterGroupDTO> page = mTargetCharacterGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-target-character-groups/count} : count all the mTargetCharacterGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-target-character-groups/count")
    public ResponseEntity<Long> countMTargetCharacterGroups(MTargetCharacterGroupCriteria criteria) {
        log.debug("REST request to count MTargetCharacterGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTargetCharacterGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-target-character-groups/:id} : get the "id" mTargetCharacterGroup.
     *
     * @param id the id of the mTargetCharacterGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTargetCharacterGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-target-character-groups/{id}")
    public ResponseEntity<MTargetCharacterGroupDTO> getMTargetCharacterGroup(@PathVariable Long id) {
        log.debug("REST request to get MTargetCharacterGroup : {}", id);
        Optional<MTargetCharacterGroupDTO> mTargetCharacterGroupDTO = mTargetCharacterGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTargetCharacterGroupDTO);
    }

    /**
     * {@code DELETE  /m-target-character-groups/:id} : delete the "id" mTargetCharacterGroup.
     *
     * @param id the id of the mTargetCharacterGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-target-character-groups/{id}")
    public ResponseEntity<Void> deleteMTargetCharacterGroup(@PathVariable Long id) {
        log.debug("REST request to delete MTargetCharacterGroup : {}", id);
        mTargetCharacterGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
