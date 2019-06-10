package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGachaRenditionService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGachaRenditionDTO;
import io.shm.tsubasa.service.dto.MGachaRenditionCriteria;
import io.shm.tsubasa.service.MGachaRenditionQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGachaRendition}.
 */
@RestController
@RequestMapping("/api")
public class MGachaRenditionResource {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionResource.class);

    private static final String ENTITY_NAME = "mGachaRendition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGachaRenditionService mGachaRenditionService;

    private final MGachaRenditionQueryService mGachaRenditionQueryService;

    public MGachaRenditionResource(MGachaRenditionService mGachaRenditionService, MGachaRenditionQueryService mGachaRenditionQueryService) {
        this.mGachaRenditionService = mGachaRenditionService;
        this.mGachaRenditionQueryService = mGachaRenditionQueryService;
    }

    /**
     * {@code POST  /m-gacha-renditions} : Create a new mGachaRendition.
     *
     * @param mGachaRenditionDTO the mGachaRenditionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGachaRenditionDTO, or with status {@code 400 (Bad Request)} if the mGachaRendition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-gacha-renditions")
    public ResponseEntity<MGachaRenditionDTO> createMGachaRendition(@Valid @RequestBody MGachaRenditionDTO mGachaRenditionDTO) throws URISyntaxException {
        log.debug("REST request to save MGachaRendition : {}", mGachaRenditionDTO);
        if (mGachaRenditionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGachaRendition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGachaRenditionDTO result = mGachaRenditionService.save(mGachaRenditionDTO);
        return ResponseEntity.created(new URI("/api/m-gacha-renditions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-gacha-renditions} : Updates an existing mGachaRendition.
     *
     * @param mGachaRenditionDTO the mGachaRenditionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGachaRenditionDTO,
     * or with status {@code 400 (Bad Request)} if the mGachaRenditionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGachaRenditionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-gacha-renditions")
    public ResponseEntity<MGachaRenditionDTO> updateMGachaRendition(@Valid @RequestBody MGachaRenditionDTO mGachaRenditionDTO) throws URISyntaxException {
        log.debug("REST request to update MGachaRendition : {}", mGachaRenditionDTO);
        if (mGachaRenditionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGachaRenditionDTO result = mGachaRenditionService.save(mGachaRenditionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGachaRenditionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-gacha-renditions} : get all the mGachaRenditions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGachaRenditions in body.
     */
    @GetMapping("/m-gacha-renditions")
    public ResponseEntity<List<MGachaRenditionDTO>> getAllMGachaRenditions(MGachaRenditionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGachaRenditions by criteria: {}", criteria);
        Page<MGachaRenditionDTO> page = mGachaRenditionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-gacha-renditions/count} : count all the mGachaRenditions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-gacha-renditions/count")
    public ResponseEntity<Long> countMGachaRenditions(MGachaRenditionCriteria criteria) {
        log.debug("REST request to count MGachaRenditions by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGachaRenditionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-gacha-renditions/:id} : get the "id" mGachaRendition.
     *
     * @param id the id of the mGachaRenditionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGachaRenditionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-gacha-renditions/{id}")
    public ResponseEntity<MGachaRenditionDTO> getMGachaRendition(@PathVariable Long id) {
        log.debug("REST request to get MGachaRendition : {}", id);
        Optional<MGachaRenditionDTO> mGachaRenditionDTO = mGachaRenditionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGachaRenditionDTO);
    }

    /**
     * {@code DELETE  /m-gacha-renditions/:id} : delete the "id" mGachaRendition.
     *
     * @param id the id of the mGachaRenditionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-gacha-renditions/{id}")
    public ResponseEntity<Void> deleteMGachaRendition(@PathVariable Long id) {
        log.debug("REST request to delete MGachaRendition : {}", id);
        mGachaRenditionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
