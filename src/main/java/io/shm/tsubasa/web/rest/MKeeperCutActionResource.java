package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MKeeperCutActionService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MKeeperCutActionDTO;
import io.shm.tsubasa.service.dto.MKeeperCutActionCriteria;
import io.shm.tsubasa.service.MKeeperCutActionQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MKeeperCutAction}.
 */
@RestController
@RequestMapping("/api")
public class MKeeperCutActionResource {

    private final Logger log = LoggerFactory.getLogger(MKeeperCutActionResource.class);

    private static final String ENTITY_NAME = "mKeeperCutAction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MKeeperCutActionService mKeeperCutActionService;

    private final MKeeperCutActionQueryService mKeeperCutActionQueryService;

    public MKeeperCutActionResource(MKeeperCutActionService mKeeperCutActionService, MKeeperCutActionQueryService mKeeperCutActionQueryService) {
        this.mKeeperCutActionService = mKeeperCutActionService;
        this.mKeeperCutActionQueryService = mKeeperCutActionQueryService;
    }

    /**
     * {@code POST  /m-keeper-cut-actions} : Create a new mKeeperCutAction.
     *
     * @param mKeeperCutActionDTO the mKeeperCutActionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mKeeperCutActionDTO, or with status {@code 400 (Bad Request)} if the mKeeperCutAction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-keeper-cut-actions")
    public ResponseEntity<MKeeperCutActionDTO> createMKeeperCutAction(@Valid @RequestBody MKeeperCutActionDTO mKeeperCutActionDTO) throws URISyntaxException {
        log.debug("REST request to save MKeeperCutAction : {}", mKeeperCutActionDTO);
        if (mKeeperCutActionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mKeeperCutAction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MKeeperCutActionDTO result = mKeeperCutActionService.save(mKeeperCutActionDTO);
        return ResponseEntity.created(new URI("/api/m-keeper-cut-actions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-keeper-cut-actions} : Updates an existing mKeeperCutAction.
     *
     * @param mKeeperCutActionDTO the mKeeperCutActionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mKeeperCutActionDTO,
     * or with status {@code 400 (Bad Request)} if the mKeeperCutActionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mKeeperCutActionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-keeper-cut-actions")
    public ResponseEntity<MKeeperCutActionDTO> updateMKeeperCutAction(@Valid @RequestBody MKeeperCutActionDTO mKeeperCutActionDTO) throws URISyntaxException {
        log.debug("REST request to update MKeeperCutAction : {}", mKeeperCutActionDTO);
        if (mKeeperCutActionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MKeeperCutActionDTO result = mKeeperCutActionService.save(mKeeperCutActionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mKeeperCutActionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-keeper-cut-actions} : get all the mKeeperCutActions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mKeeperCutActions in body.
     */
    @GetMapping("/m-keeper-cut-actions")
    public ResponseEntity<List<MKeeperCutActionDTO>> getAllMKeeperCutActions(MKeeperCutActionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MKeeperCutActions by criteria: {}", criteria);
        Page<MKeeperCutActionDTO> page = mKeeperCutActionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-keeper-cut-actions/count} : count all the mKeeperCutActions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-keeper-cut-actions/count")
    public ResponseEntity<Long> countMKeeperCutActions(MKeeperCutActionCriteria criteria) {
        log.debug("REST request to count MKeeperCutActions by criteria: {}", criteria);
        return ResponseEntity.ok().body(mKeeperCutActionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-keeper-cut-actions/:id} : get the "id" mKeeperCutAction.
     *
     * @param id the id of the mKeeperCutActionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mKeeperCutActionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-keeper-cut-actions/{id}")
    public ResponseEntity<MKeeperCutActionDTO> getMKeeperCutAction(@PathVariable Long id) {
        log.debug("REST request to get MKeeperCutAction : {}", id);
        Optional<MKeeperCutActionDTO> mKeeperCutActionDTO = mKeeperCutActionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mKeeperCutActionDTO);
    }

    /**
     * {@code DELETE  /m-keeper-cut-actions/:id} : delete the "id" mKeeperCutAction.
     *
     * @param id the id of the mKeeperCutActionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-keeper-cut-actions/{id}")
    public ResponseEntity<Void> deleteMKeeperCutAction(@PathVariable Long id) {
        log.debug("REST request to delete MKeeperCutAction : {}", id);
        mKeeperCutActionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
