package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDistributeCardParameterStepService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDistributeCardParameterStepDTO;
import io.shm.tsubasa.service.dto.MDistributeCardParameterStepCriteria;
import io.shm.tsubasa.service.MDistributeCardParameterStepQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDistributeCardParameterStep}.
 */
@RestController
@RequestMapping("/api")
public class MDistributeCardParameterStepResource {

    private final Logger log = LoggerFactory.getLogger(MDistributeCardParameterStepResource.class);

    private static final String ENTITY_NAME = "mDistributeCardParameterStep";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDistributeCardParameterStepService mDistributeCardParameterStepService;

    private final MDistributeCardParameterStepQueryService mDistributeCardParameterStepQueryService;

    public MDistributeCardParameterStepResource(MDistributeCardParameterStepService mDistributeCardParameterStepService, MDistributeCardParameterStepQueryService mDistributeCardParameterStepQueryService) {
        this.mDistributeCardParameterStepService = mDistributeCardParameterStepService;
        this.mDistributeCardParameterStepQueryService = mDistributeCardParameterStepQueryService;
    }

    /**
     * {@code POST  /m-distribute-card-parameter-steps} : Create a new mDistributeCardParameterStep.
     *
     * @param mDistributeCardParameterStepDTO the mDistributeCardParameterStepDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDistributeCardParameterStepDTO, or with status {@code 400 (Bad Request)} if the mDistributeCardParameterStep has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-distribute-card-parameter-steps")
    public ResponseEntity<MDistributeCardParameterStepDTO> createMDistributeCardParameterStep(@Valid @RequestBody MDistributeCardParameterStepDTO mDistributeCardParameterStepDTO) throws URISyntaxException {
        log.debug("REST request to save MDistributeCardParameterStep : {}", mDistributeCardParameterStepDTO);
        if (mDistributeCardParameterStepDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDistributeCardParameterStep cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDistributeCardParameterStepDTO result = mDistributeCardParameterStepService.save(mDistributeCardParameterStepDTO);
        return ResponseEntity.created(new URI("/api/m-distribute-card-parameter-steps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-distribute-card-parameter-steps} : Updates an existing mDistributeCardParameterStep.
     *
     * @param mDistributeCardParameterStepDTO the mDistributeCardParameterStepDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDistributeCardParameterStepDTO,
     * or with status {@code 400 (Bad Request)} if the mDistributeCardParameterStepDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDistributeCardParameterStepDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-distribute-card-parameter-steps")
    public ResponseEntity<MDistributeCardParameterStepDTO> updateMDistributeCardParameterStep(@Valid @RequestBody MDistributeCardParameterStepDTO mDistributeCardParameterStepDTO) throws URISyntaxException {
        log.debug("REST request to update MDistributeCardParameterStep : {}", mDistributeCardParameterStepDTO);
        if (mDistributeCardParameterStepDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDistributeCardParameterStepDTO result = mDistributeCardParameterStepService.save(mDistributeCardParameterStepDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDistributeCardParameterStepDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-distribute-card-parameter-steps} : get all the mDistributeCardParameterSteps.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDistributeCardParameterSteps in body.
     */
    @GetMapping("/m-distribute-card-parameter-steps")
    public ResponseEntity<List<MDistributeCardParameterStepDTO>> getAllMDistributeCardParameterSteps(MDistributeCardParameterStepCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDistributeCardParameterSteps by criteria: {}", criteria);
        Page<MDistributeCardParameterStepDTO> page = mDistributeCardParameterStepQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-distribute-card-parameter-steps/count} : count all the mDistributeCardParameterSteps.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-distribute-card-parameter-steps/count")
    public ResponseEntity<Long> countMDistributeCardParameterSteps(MDistributeCardParameterStepCriteria criteria) {
        log.debug("REST request to count MDistributeCardParameterSteps by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDistributeCardParameterStepQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-distribute-card-parameter-steps/:id} : get the "id" mDistributeCardParameterStep.
     *
     * @param id the id of the mDistributeCardParameterStepDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDistributeCardParameterStepDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-distribute-card-parameter-steps/{id}")
    public ResponseEntity<MDistributeCardParameterStepDTO> getMDistributeCardParameterStep(@PathVariable Long id) {
        log.debug("REST request to get MDistributeCardParameterStep : {}", id);
        Optional<MDistributeCardParameterStepDTO> mDistributeCardParameterStepDTO = mDistributeCardParameterStepService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDistributeCardParameterStepDTO);
    }

    /**
     * {@code DELETE  /m-distribute-card-parameter-steps/:id} : delete the "id" mDistributeCardParameterStep.
     *
     * @param id the id of the mDistributeCardParameterStepDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-distribute-card-parameter-steps/{id}")
    public ResponseEntity<Void> deleteMDistributeCardParameterStep(@PathVariable Long id) {
        log.debug("REST request to delete MDistributeCardParameterStep : {}", id);
        mDistributeCardParameterStepService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
