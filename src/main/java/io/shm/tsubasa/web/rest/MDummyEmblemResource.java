package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDummyEmblemService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDummyEmblemDTO;
import io.shm.tsubasa.service.dto.MDummyEmblemCriteria;
import io.shm.tsubasa.service.MDummyEmblemQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDummyEmblem}.
 */
@RestController
@RequestMapping("/api")
public class MDummyEmblemResource {

    private final Logger log = LoggerFactory.getLogger(MDummyEmblemResource.class);

    private static final String ENTITY_NAME = "mDummyEmblem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDummyEmblemService mDummyEmblemService;

    private final MDummyEmblemQueryService mDummyEmblemQueryService;

    public MDummyEmblemResource(MDummyEmblemService mDummyEmblemService, MDummyEmblemQueryService mDummyEmblemQueryService) {
        this.mDummyEmblemService = mDummyEmblemService;
        this.mDummyEmblemQueryService = mDummyEmblemQueryService;
    }

    /**
     * {@code POST  /m-dummy-emblems} : Create a new mDummyEmblem.
     *
     * @param mDummyEmblemDTO the mDummyEmblemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDummyEmblemDTO, or with status {@code 400 (Bad Request)} if the mDummyEmblem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-dummy-emblems")
    public ResponseEntity<MDummyEmblemDTO> createMDummyEmblem(@Valid @RequestBody MDummyEmblemDTO mDummyEmblemDTO) throws URISyntaxException {
        log.debug("REST request to save MDummyEmblem : {}", mDummyEmblemDTO);
        if (mDummyEmblemDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDummyEmblem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDummyEmblemDTO result = mDummyEmblemService.save(mDummyEmblemDTO);
        return ResponseEntity.created(new URI("/api/m-dummy-emblems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-dummy-emblems} : Updates an existing mDummyEmblem.
     *
     * @param mDummyEmblemDTO the mDummyEmblemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDummyEmblemDTO,
     * or with status {@code 400 (Bad Request)} if the mDummyEmblemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDummyEmblemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-dummy-emblems")
    public ResponseEntity<MDummyEmblemDTO> updateMDummyEmblem(@Valid @RequestBody MDummyEmblemDTO mDummyEmblemDTO) throws URISyntaxException {
        log.debug("REST request to update MDummyEmblem : {}", mDummyEmblemDTO);
        if (mDummyEmblemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDummyEmblemDTO result = mDummyEmblemService.save(mDummyEmblemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDummyEmblemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-dummy-emblems} : get all the mDummyEmblems.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDummyEmblems in body.
     */
    @GetMapping("/m-dummy-emblems")
    public ResponseEntity<List<MDummyEmblemDTO>> getAllMDummyEmblems(MDummyEmblemCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDummyEmblems by criteria: {}", criteria);
        Page<MDummyEmblemDTO> page = mDummyEmblemQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-dummy-emblems/count} : count all the mDummyEmblems.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-dummy-emblems/count")
    public ResponseEntity<Long> countMDummyEmblems(MDummyEmblemCriteria criteria) {
        log.debug("REST request to count MDummyEmblems by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDummyEmblemQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-dummy-emblems/:id} : get the "id" mDummyEmblem.
     *
     * @param id the id of the mDummyEmblemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDummyEmblemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-dummy-emblems/{id}")
    public ResponseEntity<MDummyEmblemDTO> getMDummyEmblem(@PathVariable Long id) {
        log.debug("REST request to get MDummyEmblem : {}", id);
        Optional<MDummyEmblemDTO> mDummyEmblemDTO = mDummyEmblemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDummyEmblemDTO);
    }

    /**
     * {@code DELETE  /m-dummy-emblems/:id} : delete the "id" mDummyEmblem.
     *
     * @param id the id of the mDummyEmblemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-dummy-emblems/{id}")
    public ResponseEntity<Void> deleteMDummyEmblem(@PathVariable Long id) {
        log.debug("REST request to delete MDummyEmblem : {}", id);
        mDummyEmblemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
