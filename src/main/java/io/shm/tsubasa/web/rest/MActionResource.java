package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MActionService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MActionDTO;
import io.shm.tsubasa.service.dto.MActionCriteria;
import io.shm.tsubasa.service.MActionQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MAction}.
 */
@RestController
@RequestMapping("/api")
public class MActionResource {

    private final Logger log = LoggerFactory.getLogger(MActionResource.class);

    private static final String ENTITY_NAME = "mAction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MActionService mActionService;

    private final MActionQueryService mActionQueryService;

    public MActionResource(MActionService mActionService, MActionQueryService mActionQueryService) {
        this.mActionService = mActionService;
        this.mActionQueryService = mActionQueryService;
    }

    /**
     * {@code POST  /m-actions} : Create a new mAction.
     *
     * @param mActionDTO the mActionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mActionDTO, or with status {@code 400 (Bad Request)} if the mAction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-actions")
    public ResponseEntity<MActionDTO> createMAction(@Valid @RequestBody MActionDTO mActionDTO) throws URISyntaxException {
        log.debug("REST request to save MAction : {}", mActionDTO);
        if (mActionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mAction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MActionDTO result = mActionService.save(mActionDTO);
        return ResponseEntity.created(new URI("/api/m-actions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-actions} : Updates an existing mAction.
     *
     * @param mActionDTO the mActionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mActionDTO,
     * or with status {@code 400 (Bad Request)} if the mActionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mActionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-actions")
    public ResponseEntity<MActionDTO> updateMAction(@Valid @RequestBody MActionDTO mActionDTO) throws URISyntaxException {
        log.debug("REST request to update MAction : {}", mActionDTO);
        if (mActionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MActionDTO result = mActionService.save(mActionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mActionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-actions} : get all the mActions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mActions in body.
     */
    @GetMapping("/m-actions")
    public ResponseEntity<List<MActionDTO>> getAllMActions(MActionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MActions by criteria: {}", criteria);
        Page<MActionDTO> page = mActionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-actions/count} : count all the mActions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-actions/count")
    public ResponseEntity<Long> countMActions(MActionCriteria criteria) {
        log.debug("REST request to count MActions by criteria: {}", criteria);
        return ResponseEntity.ok().body(mActionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-actions/:id} : get the "id" mAction.
     *
     * @param id the id of the mActionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mActionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-actions/{id}")
    public ResponseEntity<MActionDTO> getMAction(@PathVariable Long id) {
        log.debug("REST request to get MAction : {}", id);
        Optional<MActionDTO> mActionDTO = mActionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mActionDTO);
    }

    /**
     * {@code DELETE  /m-actions/:id} : delete the "id" mAction.
     *
     * @param id the id of the mActionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-actions/{id}")
    public ResponseEntity<Void> deleteMAction(@PathVariable Long id) {
        log.debug("REST request to delete MAction : {}", id);
        mActionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
