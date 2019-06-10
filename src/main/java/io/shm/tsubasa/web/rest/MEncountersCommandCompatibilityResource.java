package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MEncountersCommandCompatibilityService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MEncountersCommandCompatibilityDTO;
import io.shm.tsubasa.service.dto.MEncountersCommandCompatibilityCriteria;
import io.shm.tsubasa.service.MEncountersCommandCompatibilityQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MEncountersCommandCompatibility}.
 */
@RestController
@RequestMapping("/api")
public class MEncountersCommandCompatibilityResource {

    private final Logger log = LoggerFactory.getLogger(MEncountersCommandCompatibilityResource.class);

    private static final String ENTITY_NAME = "mEncountersCommandCompatibility";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MEncountersCommandCompatibilityService mEncountersCommandCompatibilityService;

    private final MEncountersCommandCompatibilityQueryService mEncountersCommandCompatibilityQueryService;

    public MEncountersCommandCompatibilityResource(MEncountersCommandCompatibilityService mEncountersCommandCompatibilityService, MEncountersCommandCompatibilityQueryService mEncountersCommandCompatibilityQueryService) {
        this.mEncountersCommandCompatibilityService = mEncountersCommandCompatibilityService;
        this.mEncountersCommandCompatibilityQueryService = mEncountersCommandCompatibilityQueryService;
    }

    /**
     * {@code POST  /m-encounters-command-compatibilities} : Create a new mEncountersCommandCompatibility.
     *
     * @param mEncountersCommandCompatibilityDTO the mEncountersCommandCompatibilityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mEncountersCommandCompatibilityDTO, or with status {@code 400 (Bad Request)} if the mEncountersCommandCompatibility has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-encounters-command-compatibilities")
    public ResponseEntity<MEncountersCommandCompatibilityDTO> createMEncountersCommandCompatibility(@Valid @RequestBody MEncountersCommandCompatibilityDTO mEncountersCommandCompatibilityDTO) throws URISyntaxException {
        log.debug("REST request to save MEncountersCommandCompatibility : {}", mEncountersCommandCompatibilityDTO);
        if (mEncountersCommandCompatibilityDTO.getId() != null) {
            throw new BadRequestAlertException("A new mEncountersCommandCompatibility cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MEncountersCommandCompatibilityDTO result = mEncountersCommandCompatibilityService.save(mEncountersCommandCompatibilityDTO);
        return ResponseEntity.created(new URI("/api/m-encounters-command-compatibilities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-encounters-command-compatibilities} : Updates an existing mEncountersCommandCompatibility.
     *
     * @param mEncountersCommandCompatibilityDTO the mEncountersCommandCompatibilityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mEncountersCommandCompatibilityDTO,
     * or with status {@code 400 (Bad Request)} if the mEncountersCommandCompatibilityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mEncountersCommandCompatibilityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-encounters-command-compatibilities")
    public ResponseEntity<MEncountersCommandCompatibilityDTO> updateMEncountersCommandCompatibility(@Valid @RequestBody MEncountersCommandCompatibilityDTO mEncountersCommandCompatibilityDTO) throws URISyntaxException {
        log.debug("REST request to update MEncountersCommandCompatibility : {}", mEncountersCommandCompatibilityDTO);
        if (mEncountersCommandCompatibilityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MEncountersCommandCompatibilityDTO result = mEncountersCommandCompatibilityService.save(mEncountersCommandCompatibilityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mEncountersCommandCompatibilityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-encounters-command-compatibilities} : get all the mEncountersCommandCompatibilities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mEncountersCommandCompatibilities in body.
     */
    @GetMapping("/m-encounters-command-compatibilities")
    public ResponseEntity<List<MEncountersCommandCompatibilityDTO>> getAllMEncountersCommandCompatibilities(MEncountersCommandCompatibilityCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MEncountersCommandCompatibilities by criteria: {}", criteria);
        Page<MEncountersCommandCompatibilityDTO> page = mEncountersCommandCompatibilityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-encounters-command-compatibilities/count} : count all the mEncountersCommandCompatibilities.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-encounters-command-compatibilities/count")
    public ResponseEntity<Long> countMEncountersCommandCompatibilities(MEncountersCommandCompatibilityCriteria criteria) {
        log.debug("REST request to count MEncountersCommandCompatibilities by criteria: {}", criteria);
        return ResponseEntity.ok().body(mEncountersCommandCompatibilityQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-encounters-command-compatibilities/:id} : get the "id" mEncountersCommandCompatibility.
     *
     * @param id the id of the mEncountersCommandCompatibilityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mEncountersCommandCompatibilityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-encounters-command-compatibilities/{id}")
    public ResponseEntity<MEncountersCommandCompatibilityDTO> getMEncountersCommandCompatibility(@PathVariable Long id) {
        log.debug("REST request to get MEncountersCommandCompatibility : {}", id);
        Optional<MEncountersCommandCompatibilityDTO> mEncountersCommandCompatibilityDTO = mEncountersCommandCompatibilityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mEncountersCommandCompatibilityDTO);
    }

    /**
     * {@code DELETE  /m-encounters-command-compatibilities/:id} : delete the "id" mEncountersCommandCompatibility.
     *
     * @param id the id of the mEncountersCommandCompatibilityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-encounters-command-compatibilities/{id}")
    public ResponseEntity<Void> deleteMEncountersCommandCompatibility(@PathVariable Long id) {
        log.debug("REST request to delete MEncountersCommandCompatibility : {}", id);
        mEncountersCommandCompatibilityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
