package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MEmblemPartsService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MEmblemPartsDTO;
import io.shm.tsubasa.service.dto.MEmblemPartsCriteria;
import io.shm.tsubasa.service.MEmblemPartsQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MEmblemParts}.
 */
@RestController
@RequestMapping("/api")
public class MEmblemPartsResource {

    private final Logger log = LoggerFactory.getLogger(MEmblemPartsResource.class);

    private static final String ENTITY_NAME = "mEmblemParts";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MEmblemPartsService mEmblemPartsService;

    private final MEmblemPartsQueryService mEmblemPartsQueryService;

    public MEmblemPartsResource(MEmblemPartsService mEmblemPartsService, MEmblemPartsQueryService mEmblemPartsQueryService) {
        this.mEmblemPartsService = mEmblemPartsService;
        this.mEmblemPartsQueryService = mEmblemPartsQueryService;
    }

    /**
     * {@code POST  /m-emblem-parts} : Create a new mEmblemParts.
     *
     * @param mEmblemPartsDTO the mEmblemPartsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mEmblemPartsDTO, or with status {@code 400 (Bad Request)} if the mEmblemParts has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-emblem-parts")
    public ResponseEntity<MEmblemPartsDTO> createMEmblemParts(@Valid @RequestBody MEmblemPartsDTO mEmblemPartsDTO) throws URISyntaxException {
        log.debug("REST request to save MEmblemParts : {}", mEmblemPartsDTO);
        if (mEmblemPartsDTO.getId() != null) {
            throw new BadRequestAlertException("A new mEmblemParts cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MEmblemPartsDTO result = mEmblemPartsService.save(mEmblemPartsDTO);
        return ResponseEntity.created(new URI("/api/m-emblem-parts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-emblem-parts} : Updates an existing mEmblemParts.
     *
     * @param mEmblemPartsDTO the mEmblemPartsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mEmblemPartsDTO,
     * or with status {@code 400 (Bad Request)} if the mEmblemPartsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mEmblemPartsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-emblem-parts")
    public ResponseEntity<MEmblemPartsDTO> updateMEmblemParts(@Valid @RequestBody MEmblemPartsDTO mEmblemPartsDTO) throws URISyntaxException {
        log.debug("REST request to update MEmblemParts : {}", mEmblemPartsDTO);
        if (mEmblemPartsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MEmblemPartsDTO result = mEmblemPartsService.save(mEmblemPartsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mEmblemPartsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-emblem-parts} : get all the mEmblemParts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mEmblemParts in body.
     */
    @GetMapping("/m-emblem-parts")
    public ResponseEntity<List<MEmblemPartsDTO>> getAllMEmblemParts(MEmblemPartsCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MEmblemParts by criteria: {}", criteria);
        Page<MEmblemPartsDTO> page = mEmblemPartsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-emblem-parts/count} : count all the mEmblemParts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-emblem-parts/count")
    public ResponseEntity<Long> countMEmblemParts(MEmblemPartsCriteria criteria) {
        log.debug("REST request to count MEmblemParts by criteria: {}", criteria);
        return ResponseEntity.ok().body(mEmblemPartsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-emblem-parts/:id} : get the "id" mEmblemParts.
     *
     * @param id the id of the mEmblemPartsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mEmblemPartsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-emblem-parts/{id}")
    public ResponseEntity<MEmblemPartsDTO> getMEmblemParts(@PathVariable Long id) {
        log.debug("REST request to get MEmblemParts : {}", id);
        Optional<MEmblemPartsDTO> mEmblemPartsDTO = mEmblemPartsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mEmblemPartsDTO);
    }

    /**
     * {@code DELETE  /m-emblem-parts/:id} : delete the "id" mEmblemParts.
     *
     * @param id the id of the mEmblemPartsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-emblem-parts/{id}")
    public ResponseEntity<Void> deleteMEmblemParts(@PathVariable Long id) {
        log.debug("REST request to delete MEmblemParts : {}", id);
        mEmblemPartsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
