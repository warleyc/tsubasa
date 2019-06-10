package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGachaRenditionBeforeShootCutInPatternService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGachaRenditionBeforeShootCutInPatternDTO;
import io.shm.tsubasa.service.dto.MGachaRenditionBeforeShootCutInPatternCriteria;
import io.shm.tsubasa.service.MGachaRenditionBeforeShootCutInPatternQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGachaRenditionBeforeShootCutInPattern}.
 */
@RestController
@RequestMapping("/api")
public class MGachaRenditionBeforeShootCutInPatternResource {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionBeforeShootCutInPatternResource.class);

    private static final String ENTITY_NAME = "mGachaRenditionBeforeShootCutInPattern";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGachaRenditionBeforeShootCutInPatternService mGachaRenditionBeforeShootCutInPatternService;

    private final MGachaRenditionBeforeShootCutInPatternQueryService mGachaRenditionBeforeShootCutInPatternQueryService;

    public MGachaRenditionBeforeShootCutInPatternResource(MGachaRenditionBeforeShootCutInPatternService mGachaRenditionBeforeShootCutInPatternService, MGachaRenditionBeforeShootCutInPatternQueryService mGachaRenditionBeforeShootCutInPatternQueryService) {
        this.mGachaRenditionBeforeShootCutInPatternService = mGachaRenditionBeforeShootCutInPatternService;
        this.mGachaRenditionBeforeShootCutInPatternQueryService = mGachaRenditionBeforeShootCutInPatternQueryService;
    }

    /**
     * {@code POST  /m-gacha-rendition-before-shoot-cut-in-patterns} : Create a new mGachaRenditionBeforeShootCutInPattern.
     *
     * @param mGachaRenditionBeforeShootCutInPatternDTO the mGachaRenditionBeforeShootCutInPatternDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGachaRenditionBeforeShootCutInPatternDTO, or with status {@code 400 (Bad Request)} if the mGachaRenditionBeforeShootCutInPattern has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-gacha-rendition-before-shoot-cut-in-patterns")
    public ResponseEntity<MGachaRenditionBeforeShootCutInPatternDTO> createMGachaRenditionBeforeShootCutInPattern(@Valid @RequestBody MGachaRenditionBeforeShootCutInPatternDTO mGachaRenditionBeforeShootCutInPatternDTO) throws URISyntaxException {
        log.debug("REST request to save MGachaRenditionBeforeShootCutInPattern : {}", mGachaRenditionBeforeShootCutInPatternDTO);
        if (mGachaRenditionBeforeShootCutInPatternDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGachaRenditionBeforeShootCutInPattern cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGachaRenditionBeforeShootCutInPatternDTO result = mGachaRenditionBeforeShootCutInPatternService.save(mGachaRenditionBeforeShootCutInPatternDTO);
        return ResponseEntity.created(new URI("/api/m-gacha-rendition-before-shoot-cut-in-patterns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-gacha-rendition-before-shoot-cut-in-patterns} : Updates an existing mGachaRenditionBeforeShootCutInPattern.
     *
     * @param mGachaRenditionBeforeShootCutInPatternDTO the mGachaRenditionBeforeShootCutInPatternDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGachaRenditionBeforeShootCutInPatternDTO,
     * or with status {@code 400 (Bad Request)} if the mGachaRenditionBeforeShootCutInPatternDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGachaRenditionBeforeShootCutInPatternDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-gacha-rendition-before-shoot-cut-in-patterns")
    public ResponseEntity<MGachaRenditionBeforeShootCutInPatternDTO> updateMGachaRenditionBeforeShootCutInPattern(@Valid @RequestBody MGachaRenditionBeforeShootCutInPatternDTO mGachaRenditionBeforeShootCutInPatternDTO) throws URISyntaxException {
        log.debug("REST request to update MGachaRenditionBeforeShootCutInPattern : {}", mGachaRenditionBeforeShootCutInPatternDTO);
        if (mGachaRenditionBeforeShootCutInPatternDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGachaRenditionBeforeShootCutInPatternDTO result = mGachaRenditionBeforeShootCutInPatternService.save(mGachaRenditionBeforeShootCutInPatternDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGachaRenditionBeforeShootCutInPatternDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-gacha-rendition-before-shoot-cut-in-patterns} : get all the mGachaRenditionBeforeShootCutInPatterns.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGachaRenditionBeforeShootCutInPatterns in body.
     */
    @GetMapping("/m-gacha-rendition-before-shoot-cut-in-patterns")
    public ResponseEntity<List<MGachaRenditionBeforeShootCutInPatternDTO>> getAllMGachaRenditionBeforeShootCutInPatterns(MGachaRenditionBeforeShootCutInPatternCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGachaRenditionBeforeShootCutInPatterns by criteria: {}", criteria);
        Page<MGachaRenditionBeforeShootCutInPatternDTO> page = mGachaRenditionBeforeShootCutInPatternQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-gacha-rendition-before-shoot-cut-in-patterns/count} : count all the mGachaRenditionBeforeShootCutInPatterns.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-gacha-rendition-before-shoot-cut-in-patterns/count")
    public ResponseEntity<Long> countMGachaRenditionBeforeShootCutInPatterns(MGachaRenditionBeforeShootCutInPatternCriteria criteria) {
        log.debug("REST request to count MGachaRenditionBeforeShootCutInPatterns by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGachaRenditionBeforeShootCutInPatternQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-gacha-rendition-before-shoot-cut-in-patterns/:id} : get the "id" mGachaRenditionBeforeShootCutInPattern.
     *
     * @param id the id of the mGachaRenditionBeforeShootCutInPatternDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGachaRenditionBeforeShootCutInPatternDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-gacha-rendition-before-shoot-cut-in-patterns/{id}")
    public ResponseEntity<MGachaRenditionBeforeShootCutInPatternDTO> getMGachaRenditionBeforeShootCutInPattern(@PathVariable Long id) {
        log.debug("REST request to get MGachaRenditionBeforeShootCutInPattern : {}", id);
        Optional<MGachaRenditionBeforeShootCutInPatternDTO> mGachaRenditionBeforeShootCutInPatternDTO = mGachaRenditionBeforeShootCutInPatternService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGachaRenditionBeforeShootCutInPatternDTO);
    }

    /**
     * {@code DELETE  /m-gacha-rendition-before-shoot-cut-in-patterns/:id} : delete the "id" mGachaRenditionBeforeShootCutInPattern.
     *
     * @param id the id of the mGachaRenditionBeforeShootCutInPatternDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-gacha-rendition-before-shoot-cut-in-patterns/{id}")
    public ResponseEntity<Void> deleteMGachaRenditionBeforeShootCutInPattern(@PathVariable Long id) {
        log.debug("REST request to delete MGachaRenditionBeforeShootCutInPattern : {}", id);
        mGachaRenditionBeforeShootCutInPatternService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
