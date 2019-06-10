package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCombinationCutPositionService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCombinationCutPositionDTO;
import io.shm.tsubasa.service.dto.MCombinationCutPositionCriteria;
import io.shm.tsubasa.service.MCombinationCutPositionQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCombinationCutPosition}.
 */
@RestController
@RequestMapping("/api")
public class MCombinationCutPositionResource {

    private final Logger log = LoggerFactory.getLogger(MCombinationCutPositionResource.class);

    private static final String ENTITY_NAME = "mCombinationCutPosition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCombinationCutPositionService mCombinationCutPositionService;

    private final MCombinationCutPositionQueryService mCombinationCutPositionQueryService;

    public MCombinationCutPositionResource(MCombinationCutPositionService mCombinationCutPositionService, MCombinationCutPositionQueryService mCombinationCutPositionQueryService) {
        this.mCombinationCutPositionService = mCombinationCutPositionService;
        this.mCombinationCutPositionQueryService = mCombinationCutPositionQueryService;
    }

    /**
     * {@code POST  /m-combination-cut-positions} : Create a new mCombinationCutPosition.
     *
     * @param mCombinationCutPositionDTO the mCombinationCutPositionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCombinationCutPositionDTO, or with status {@code 400 (Bad Request)} if the mCombinationCutPosition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-combination-cut-positions")
    public ResponseEntity<MCombinationCutPositionDTO> createMCombinationCutPosition(@Valid @RequestBody MCombinationCutPositionDTO mCombinationCutPositionDTO) throws URISyntaxException {
        log.debug("REST request to save MCombinationCutPosition : {}", mCombinationCutPositionDTO);
        if (mCombinationCutPositionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCombinationCutPosition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCombinationCutPositionDTO result = mCombinationCutPositionService.save(mCombinationCutPositionDTO);
        return ResponseEntity.created(new URI("/api/m-combination-cut-positions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-combination-cut-positions} : Updates an existing mCombinationCutPosition.
     *
     * @param mCombinationCutPositionDTO the mCombinationCutPositionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCombinationCutPositionDTO,
     * or with status {@code 400 (Bad Request)} if the mCombinationCutPositionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCombinationCutPositionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-combination-cut-positions")
    public ResponseEntity<MCombinationCutPositionDTO> updateMCombinationCutPosition(@Valid @RequestBody MCombinationCutPositionDTO mCombinationCutPositionDTO) throws URISyntaxException {
        log.debug("REST request to update MCombinationCutPosition : {}", mCombinationCutPositionDTO);
        if (mCombinationCutPositionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCombinationCutPositionDTO result = mCombinationCutPositionService.save(mCombinationCutPositionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCombinationCutPositionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-combination-cut-positions} : get all the mCombinationCutPositions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCombinationCutPositions in body.
     */
    @GetMapping("/m-combination-cut-positions")
    public ResponseEntity<List<MCombinationCutPositionDTO>> getAllMCombinationCutPositions(MCombinationCutPositionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCombinationCutPositions by criteria: {}", criteria);
        Page<MCombinationCutPositionDTO> page = mCombinationCutPositionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-combination-cut-positions/count} : count all the mCombinationCutPositions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-combination-cut-positions/count")
    public ResponseEntity<Long> countMCombinationCutPositions(MCombinationCutPositionCriteria criteria) {
        log.debug("REST request to count MCombinationCutPositions by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCombinationCutPositionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-combination-cut-positions/:id} : get the "id" mCombinationCutPosition.
     *
     * @param id the id of the mCombinationCutPositionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCombinationCutPositionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-combination-cut-positions/{id}")
    public ResponseEntity<MCombinationCutPositionDTO> getMCombinationCutPosition(@PathVariable Long id) {
        log.debug("REST request to get MCombinationCutPosition : {}", id);
        Optional<MCombinationCutPositionDTO> mCombinationCutPositionDTO = mCombinationCutPositionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCombinationCutPositionDTO);
    }

    /**
     * {@code DELETE  /m-combination-cut-positions/:id} : delete the "id" mCombinationCutPosition.
     *
     * @param id the id of the mCombinationCutPositionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-combination-cut-positions/{id}")
    public ResponseEntity<Void> deleteMCombinationCutPosition(@PathVariable Long id) {
        log.debug("REST request to delete MCombinationCutPosition : {}", id);
        mCombinationCutPositionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
