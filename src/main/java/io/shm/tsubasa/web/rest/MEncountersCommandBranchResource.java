package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MEncountersCommandBranchService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MEncountersCommandBranchDTO;
import io.shm.tsubasa.service.dto.MEncountersCommandBranchCriteria;
import io.shm.tsubasa.service.MEncountersCommandBranchQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MEncountersCommandBranch}.
 */
@RestController
@RequestMapping("/api")
public class MEncountersCommandBranchResource {

    private final Logger log = LoggerFactory.getLogger(MEncountersCommandBranchResource.class);

    private static final String ENTITY_NAME = "mEncountersCommandBranch";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MEncountersCommandBranchService mEncountersCommandBranchService;

    private final MEncountersCommandBranchQueryService mEncountersCommandBranchQueryService;

    public MEncountersCommandBranchResource(MEncountersCommandBranchService mEncountersCommandBranchService, MEncountersCommandBranchQueryService mEncountersCommandBranchQueryService) {
        this.mEncountersCommandBranchService = mEncountersCommandBranchService;
        this.mEncountersCommandBranchQueryService = mEncountersCommandBranchQueryService;
    }

    /**
     * {@code POST  /m-encounters-command-branches} : Create a new mEncountersCommandBranch.
     *
     * @param mEncountersCommandBranchDTO the mEncountersCommandBranchDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mEncountersCommandBranchDTO, or with status {@code 400 (Bad Request)} if the mEncountersCommandBranch has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-encounters-command-branches")
    public ResponseEntity<MEncountersCommandBranchDTO> createMEncountersCommandBranch(@Valid @RequestBody MEncountersCommandBranchDTO mEncountersCommandBranchDTO) throws URISyntaxException {
        log.debug("REST request to save MEncountersCommandBranch : {}", mEncountersCommandBranchDTO);
        if (mEncountersCommandBranchDTO.getId() != null) {
            throw new BadRequestAlertException("A new mEncountersCommandBranch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MEncountersCommandBranchDTO result = mEncountersCommandBranchService.save(mEncountersCommandBranchDTO);
        return ResponseEntity.created(new URI("/api/m-encounters-command-branches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-encounters-command-branches} : Updates an existing mEncountersCommandBranch.
     *
     * @param mEncountersCommandBranchDTO the mEncountersCommandBranchDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mEncountersCommandBranchDTO,
     * or with status {@code 400 (Bad Request)} if the mEncountersCommandBranchDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mEncountersCommandBranchDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-encounters-command-branches")
    public ResponseEntity<MEncountersCommandBranchDTO> updateMEncountersCommandBranch(@Valid @RequestBody MEncountersCommandBranchDTO mEncountersCommandBranchDTO) throws URISyntaxException {
        log.debug("REST request to update MEncountersCommandBranch : {}", mEncountersCommandBranchDTO);
        if (mEncountersCommandBranchDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MEncountersCommandBranchDTO result = mEncountersCommandBranchService.save(mEncountersCommandBranchDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mEncountersCommandBranchDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-encounters-command-branches} : get all the mEncountersCommandBranches.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mEncountersCommandBranches in body.
     */
    @GetMapping("/m-encounters-command-branches")
    public ResponseEntity<List<MEncountersCommandBranchDTO>> getAllMEncountersCommandBranches(MEncountersCommandBranchCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MEncountersCommandBranches by criteria: {}", criteria);
        Page<MEncountersCommandBranchDTO> page = mEncountersCommandBranchQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-encounters-command-branches/count} : count all the mEncountersCommandBranches.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-encounters-command-branches/count")
    public ResponseEntity<Long> countMEncountersCommandBranches(MEncountersCommandBranchCriteria criteria) {
        log.debug("REST request to count MEncountersCommandBranches by criteria: {}", criteria);
        return ResponseEntity.ok().body(mEncountersCommandBranchQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-encounters-command-branches/:id} : get the "id" mEncountersCommandBranch.
     *
     * @param id the id of the mEncountersCommandBranchDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mEncountersCommandBranchDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-encounters-command-branches/{id}")
    public ResponseEntity<MEncountersCommandBranchDTO> getMEncountersCommandBranch(@PathVariable Long id) {
        log.debug("REST request to get MEncountersCommandBranch : {}", id);
        Optional<MEncountersCommandBranchDTO> mEncountersCommandBranchDTO = mEncountersCommandBranchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mEncountersCommandBranchDTO);
    }

    /**
     * {@code DELETE  /m-encounters-command-branches/:id} : delete the "id" mEncountersCommandBranch.
     *
     * @param id the id of the mEncountersCommandBranchDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-encounters-command-branches/{id}")
    public ResponseEntity<Void> deleteMEncountersCommandBranch(@PathVariable Long id) {
        log.debug("REST request to delete MEncountersCommandBranch : {}", id);
        mEncountersCommandBranchService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
