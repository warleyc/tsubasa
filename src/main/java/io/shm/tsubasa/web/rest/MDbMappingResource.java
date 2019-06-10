package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDbMappingService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDbMappingDTO;
import io.shm.tsubasa.service.dto.MDbMappingCriteria;
import io.shm.tsubasa.service.MDbMappingQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDbMapping}.
 */
@RestController
@RequestMapping("/api")
public class MDbMappingResource {

    private final Logger log = LoggerFactory.getLogger(MDbMappingResource.class);

    private static final String ENTITY_NAME = "mDbMapping";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDbMappingService mDbMappingService;

    private final MDbMappingQueryService mDbMappingQueryService;

    public MDbMappingResource(MDbMappingService mDbMappingService, MDbMappingQueryService mDbMappingQueryService) {
        this.mDbMappingService = mDbMappingService;
        this.mDbMappingQueryService = mDbMappingQueryService;
    }

    /**
     * {@code POST  /m-db-mappings} : Create a new mDbMapping.
     *
     * @param mDbMappingDTO the mDbMappingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDbMappingDTO, or with status {@code 400 (Bad Request)} if the mDbMapping has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-db-mappings")
    public ResponseEntity<MDbMappingDTO> createMDbMapping(@Valid @RequestBody MDbMappingDTO mDbMappingDTO) throws URISyntaxException {
        log.debug("REST request to save MDbMapping : {}", mDbMappingDTO);
        if (mDbMappingDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDbMapping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDbMappingDTO result = mDbMappingService.save(mDbMappingDTO);
        return ResponseEntity.created(new URI("/api/m-db-mappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-db-mappings} : Updates an existing mDbMapping.
     *
     * @param mDbMappingDTO the mDbMappingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDbMappingDTO,
     * or with status {@code 400 (Bad Request)} if the mDbMappingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDbMappingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-db-mappings")
    public ResponseEntity<MDbMappingDTO> updateMDbMapping(@Valid @RequestBody MDbMappingDTO mDbMappingDTO) throws URISyntaxException {
        log.debug("REST request to update MDbMapping : {}", mDbMappingDTO);
        if (mDbMappingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDbMappingDTO result = mDbMappingService.save(mDbMappingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDbMappingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-db-mappings} : get all the mDbMappings.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDbMappings in body.
     */
    @GetMapping("/m-db-mappings")
    public ResponseEntity<List<MDbMappingDTO>> getAllMDbMappings(MDbMappingCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDbMappings by criteria: {}", criteria);
        Page<MDbMappingDTO> page = mDbMappingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-db-mappings/count} : count all the mDbMappings.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-db-mappings/count")
    public ResponseEntity<Long> countMDbMappings(MDbMappingCriteria criteria) {
        log.debug("REST request to count MDbMappings by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDbMappingQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-db-mappings/:id} : get the "id" mDbMapping.
     *
     * @param id the id of the mDbMappingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDbMappingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-db-mappings/{id}")
    public ResponseEntity<MDbMappingDTO> getMDbMapping(@PathVariable Long id) {
        log.debug("REST request to get MDbMapping : {}", id);
        Optional<MDbMappingDTO> mDbMappingDTO = mDbMappingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDbMappingDTO);
    }

    /**
     * {@code DELETE  /m-db-mappings/:id} : delete the "id" mDbMapping.
     *
     * @param id the id of the mDbMappingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-db-mappings/{id}")
    public ResponseEntity<Void> deleteMDbMapping(@PathVariable Long id) {
        log.debug("REST request to delete MDbMapping : {}", id);
        mDbMappingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
