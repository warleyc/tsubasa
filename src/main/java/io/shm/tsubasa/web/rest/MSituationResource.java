package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MSituationService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MSituationDTO;
import io.shm.tsubasa.service.dto.MSituationCriteria;
import io.shm.tsubasa.service.MSituationQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MSituation}.
 */
@RestController
@RequestMapping("/api")
public class MSituationResource {

    private final Logger log = LoggerFactory.getLogger(MSituationResource.class);

    private static final String ENTITY_NAME = "mSituation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MSituationService mSituationService;

    private final MSituationQueryService mSituationQueryService;

    public MSituationResource(MSituationService mSituationService, MSituationQueryService mSituationQueryService) {
        this.mSituationService = mSituationService;
        this.mSituationQueryService = mSituationQueryService;
    }

    /**
     * {@code POST  /m-situations} : Create a new mSituation.
     *
     * @param mSituationDTO the mSituationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mSituationDTO, or with status {@code 400 (Bad Request)} if the mSituation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-situations")
    public ResponseEntity<MSituationDTO> createMSituation(@Valid @RequestBody MSituationDTO mSituationDTO) throws URISyntaxException {
        log.debug("REST request to save MSituation : {}", mSituationDTO);
        if (mSituationDTO.getId() != null) {
            throw new BadRequestAlertException("A new mSituation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MSituationDTO result = mSituationService.save(mSituationDTO);
        return ResponseEntity.created(new URI("/api/m-situations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-situations} : Updates an existing mSituation.
     *
     * @param mSituationDTO the mSituationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mSituationDTO,
     * or with status {@code 400 (Bad Request)} if the mSituationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mSituationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-situations")
    public ResponseEntity<MSituationDTO> updateMSituation(@Valid @RequestBody MSituationDTO mSituationDTO) throws URISyntaxException {
        log.debug("REST request to update MSituation : {}", mSituationDTO);
        if (mSituationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MSituationDTO result = mSituationService.save(mSituationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mSituationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-situations} : get all the mSituations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mSituations in body.
     */
    @GetMapping("/m-situations")
    public ResponseEntity<List<MSituationDTO>> getAllMSituations(MSituationCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MSituations by criteria: {}", criteria);
        Page<MSituationDTO> page = mSituationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-situations/count} : count all the mSituations.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-situations/count")
    public ResponseEntity<Long> countMSituations(MSituationCriteria criteria) {
        log.debug("REST request to count MSituations by criteria: {}", criteria);
        return ResponseEntity.ok().body(mSituationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-situations/:id} : get the "id" mSituation.
     *
     * @param id the id of the mSituationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mSituationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-situations/{id}")
    public ResponseEntity<MSituationDTO> getMSituation(@PathVariable Long id) {
        log.debug("REST request to get MSituation : {}", id);
        Optional<MSituationDTO> mSituationDTO = mSituationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mSituationDTO);
    }

    /**
     * {@code DELETE  /m-situations/:id} : delete the "id" mSituation.
     *
     * @param id the id of the mSituationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-situations/{id}")
    public ResponseEntity<Void> deleteMSituation(@PathVariable Long id) {
        log.debug("REST request to delete MSituation : {}", id);
        mSituationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
