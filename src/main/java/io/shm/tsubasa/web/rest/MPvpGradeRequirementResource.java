package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MPvpGradeRequirementService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MPvpGradeRequirementDTO;
import io.shm.tsubasa.service.dto.MPvpGradeRequirementCriteria;
import io.shm.tsubasa.service.MPvpGradeRequirementQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MPvpGradeRequirement}.
 */
@RestController
@RequestMapping("/api")
public class MPvpGradeRequirementResource {

    private final Logger log = LoggerFactory.getLogger(MPvpGradeRequirementResource.class);

    private static final String ENTITY_NAME = "mPvpGradeRequirement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MPvpGradeRequirementService mPvpGradeRequirementService;

    private final MPvpGradeRequirementQueryService mPvpGradeRequirementQueryService;

    public MPvpGradeRequirementResource(MPvpGradeRequirementService mPvpGradeRequirementService, MPvpGradeRequirementQueryService mPvpGradeRequirementQueryService) {
        this.mPvpGradeRequirementService = mPvpGradeRequirementService;
        this.mPvpGradeRequirementQueryService = mPvpGradeRequirementQueryService;
    }

    /**
     * {@code POST  /m-pvp-grade-requirements} : Create a new mPvpGradeRequirement.
     *
     * @param mPvpGradeRequirementDTO the mPvpGradeRequirementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mPvpGradeRequirementDTO, or with status {@code 400 (Bad Request)} if the mPvpGradeRequirement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-pvp-grade-requirements")
    public ResponseEntity<MPvpGradeRequirementDTO> createMPvpGradeRequirement(@Valid @RequestBody MPvpGradeRequirementDTO mPvpGradeRequirementDTO) throws URISyntaxException {
        log.debug("REST request to save MPvpGradeRequirement : {}", mPvpGradeRequirementDTO);
        if (mPvpGradeRequirementDTO.getId() != null) {
            throw new BadRequestAlertException("A new mPvpGradeRequirement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MPvpGradeRequirementDTO result = mPvpGradeRequirementService.save(mPvpGradeRequirementDTO);
        return ResponseEntity.created(new URI("/api/m-pvp-grade-requirements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-pvp-grade-requirements} : Updates an existing mPvpGradeRequirement.
     *
     * @param mPvpGradeRequirementDTO the mPvpGradeRequirementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mPvpGradeRequirementDTO,
     * or with status {@code 400 (Bad Request)} if the mPvpGradeRequirementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mPvpGradeRequirementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-pvp-grade-requirements")
    public ResponseEntity<MPvpGradeRequirementDTO> updateMPvpGradeRequirement(@Valid @RequestBody MPvpGradeRequirementDTO mPvpGradeRequirementDTO) throws URISyntaxException {
        log.debug("REST request to update MPvpGradeRequirement : {}", mPvpGradeRequirementDTO);
        if (mPvpGradeRequirementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MPvpGradeRequirementDTO result = mPvpGradeRequirementService.save(mPvpGradeRequirementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mPvpGradeRequirementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-pvp-grade-requirements} : get all the mPvpGradeRequirements.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mPvpGradeRequirements in body.
     */
    @GetMapping("/m-pvp-grade-requirements")
    public ResponseEntity<List<MPvpGradeRequirementDTO>> getAllMPvpGradeRequirements(MPvpGradeRequirementCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MPvpGradeRequirements by criteria: {}", criteria);
        Page<MPvpGradeRequirementDTO> page = mPvpGradeRequirementQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-pvp-grade-requirements/count} : count all the mPvpGradeRequirements.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-pvp-grade-requirements/count")
    public ResponseEntity<Long> countMPvpGradeRequirements(MPvpGradeRequirementCriteria criteria) {
        log.debug("REST request to count MPvpGradeRequirements by criteria: {}", criteria);
        return ResponseEntity.ok().body(mPvpGradeRequirementQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-pvp-grade-requirements/:id} : get the "id" mPvpGradeRequirement.
     *
     * @param id the id of the mPvpGradeRequirementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mPvpGradeRequirementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-pvp-grade-requirements/{id}")
    public ResponseEntity<MPvpGradeRequirementDTO> getMPvpGradeRequirement(@PathVariable Long id) {
        log.debug("REST request to get MPvpGradeRequirement : {}", id);
        Optional<MPvpGradeRequirementDTO> mPvpGradeRequirementDTO = mPvpGradeRequirementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mPvpGradeRequirementDTO);
    }

    /**
     * {@code DELETE  /m-pvp-grade-requirements/:id} : delete the "id" mPvpGradeRequirement.
     *
     * @param id the id of the mPvpGradeRequirementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-pvp-grade-requirements/{id}")
    public ResponseEntity<Void> deleteMPvpGradeRequirement(@PathVariable Long id) {
        log.debug("REST request to delete MPvpGradeRequirement : {}", id);
        mPvpGradeRequirementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
