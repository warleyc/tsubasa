package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMatchEnvironmentService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMatchEnvironmentDTO;
import io.shm.tsubasa.service.dto.MMatchEnvironmentCriteria;
import io.shm.tsubasa.service.MMatchEnvironmentQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMatchEnvironment}.
 */
@RestController
@RequestMapping("/api")
public class MMatchEnvironmentResource {

    private final Logger log = LoggerFactory.getLogger(MMatchEnvironmentResource.class);

    private static final String ENTITY_NAME = "mMatchEnvironment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMatchEnvironmentService mMatchEnvironmentService;

    private final MMatchEnvironmentQueryService mMatchEnvironmentQueryService;

    public MMatchEnvironmentResource(MMatchEnvironmentService mMatchEnvironmentService, MMatchEnvironmentQueryService mMatchEnvironmentQueryService) {
        this.mMatchEnvironmentService = mMatchEnvironmentService;
        this.mMatchEnvironmentQueryService = mMatchEnvironmentQueryService;
    }

    /**
     * {@code POST  /m-match-environments} : Create a new mMatchEnvironment.
     *
     * @param mMatchEnvironmentDTO the mMatchEnvironmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMatchEnvironmentDTO, or with status {@code 400 (Bad Request)} if the mMatchEnvironment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-match-environments")
    public ResponseEntity<MMatchEnvironmentDTO> createMMatchEnvironment(@Valid @RequestBody MMatchEnvironmentDTO mMatchEnvironmentDTO) throws URISyntaxException {
        log.debug("REST request to save MMatchEnvironment : {}", mMatchEnvironmentDTO);
        if (mMatchEnvironmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMatchEnvironment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMatchEnvironmentDTO result = mMatchEnvironmentService.save(mMatchEnvironmentDTO);
        return ResponseEntity.created(new URI("/api/m-match-environments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-match-environments} : Updates an existing mMatchEnvironment.
     *
     * @param mMatchEnvironmentDTO the mMatchEnvironmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMatchEnvironmentDTO,
     * or with status {@code 400 (Bad Request)} if the mMatchEnvironmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMatchEnvironmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-match-environments")
    public ResponseEntity<MMatchEnvironmentDTO> updateMMatchEnvironment(@Valid @RequestBody MMatchEnvironmentDTO mMatchEnvironmentDTO) throws URISyntaxException {
        log.debug("REST request to update MMatchEnvironment : {}", mMatchEnvironmentDTO);
        if (mMatchEnvironmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMatchEnvironmentDTO result = mMatchEnvironmentService.save(mMatchEnvironmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMatchEnvironmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-match-environments} : get all the mMatchEnvironments.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMatchEnvironments in body.
     */
    @GetMapping("/m-match-environments")
    public ResponseEntity<List<MMatchEnvironmentDTO>> getAllMMatchEnvironments(MMatchEnvironmentCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMatchEnvironments by criteria: {}", criteria);
        Page<MMatchEnvironmentDTO> page = mMatchEnvironmentQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-match-environments/count} : count all the mMatchEnvironments.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-match-environments/count")
    public ResponseEntity<Long> countMMatchEnvironments(MMatchEnvironmentCriteria criteria) {
        log.debug("REST request to count MMatchEnvironments by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMatchEnvironmentQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-match-environments/:id} : get the "id" mMatchEnvironment.
     *
     * @param id the id of the mMatchEnvironmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMatchEnvironmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-match-environments/{id}")
    public ResponseEntity<MMatchEnvironmentDTO> getMMatchEnvironment(@PathVariable Long id) {
        log.debug("REST request to get MMatchEnvironment : {}", id);
        Optional<MMatchEnvironmentDTO> mMatchEnvironmentDTO = mMatchEnvironmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMatchEnvironmentDTO);
    }

    /**
     * {@code DELETE  /m-match-environments/:id} : delete the "id" mMatchEnvironment.
     *
     * @param id the id of the mMatchEnvironmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-match-environments/{id}")
    public ResponseEntity<Void> deleteMMatchEnvironment(@PathVariable Long id) {
        log.debug("REST request to delete MMatchEnvironment : {}", id);
        mMatchEnvironmentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
