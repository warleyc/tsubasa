package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCutActionService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCutActionDTO;
import io.shm.tsubasa.service.dto.MCutActionCriteria;
import io.shm.tsubasa.service.MCutActionQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCutAction}.
 */
@RestController
@RequestMapping("/api")
public class MCutActionResource {

    private final Logger log = LoggerFactory.getLogger(MCutActionResource.class);

    private static final String ENTITY_NAME = "mCutAction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCutActionService mCutActionService;

    private final MCutActionQueryService mCutActionQueryService;

    public MCutActionResource(MCutActionService mCutActionService, MCutActionQueryService mCutActionQueryService) {
        this.mCutActionService = mCutActionService;
        this.mCutActionQueryService = mCutActionQueryService;
    }

    /**
     * {@code POST  /m-cut-actions} : Create a new mCutAction.
     *
     * @param mCutActionDTO the mCutActionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCutActionDTO, or with status {@code 400 (Bad Request)} if the mCutAction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-cut-actions")
    public ResponseEntity<MCutActionDTO> createMCutAction(@Valid @RequestBody MCutActionDTO mCutActionDTO) throws URISyntaxException {
        log.debug("REST request to save MCutAction : {}", mCutActionDTO);
        if (mCutActionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCutAction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCutActionDTO result = mCutActionService.save(mCutActionDTO);
        return ResponseEntity.created(new URI("/api/m-cut-actions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-cut-actions} : Updates an existing mCutAction.
     *
     * @param mCutActionDTO the mCutActionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCutActionDTO,
     * or with status {@code 400 (Bad Request)} if the mCutActionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCutActionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-cut-actions")
    public ResponseEntity<MCutActionDTO> updateMCutAction(@Valid @RequestBody MCutActionDTO mCutActionDTO) throws URISyntaxException {
        log.debug("REST request to update MCutAction : {}", mCutActionDTO);
        if (mCutActionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCutActionDTO result = mCutActionService.save(mCutActionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCutActionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-cut-actions} : get all the mCutActions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCutActions in body.
     */
    @GetMapping("/m-cut-actions")
    public ResponseEntity<List<MCutActionDTO>> getAllMCutActions(MCutActionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCutActions by criteria: {}", criteria);
        Page<MCutActionDTO> page = mCutActionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-cut-actions/count} : count all the mCutActions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-cut-actions/count")
    public ResponseEntity<Long> countMCutActions(MCutActionCriteria criteria) {
        log.debug("REST request to count MCutActions by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCutActionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-cut-actions/:id} : get the "id" mCutAction.
     *
     * @param id the id of the mCutActionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCutActionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-cut-actions/{id}")
    public ResponseEntity<MCutActionDTO> getMCutAction(@PathVariable Long id) {
        log.debug("REST request to get MCutAction : {}", id);
        Optional<MCutActionDTO> mCutActionDTO = mCutActionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCutActionDTO);
    }

    /**
     * {@code DELETE  /m-cut-actions/:id} : delete the "id" mCutAction.
     *
     * @param id the id of the mCutActionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-cut-actions/{id}")
    public ResponseEntity<Void> deleteMCutAction(@PathVariable Long id) {
        log.debug("REST request to delete MCutAction : {}", id);
        mCutActionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
