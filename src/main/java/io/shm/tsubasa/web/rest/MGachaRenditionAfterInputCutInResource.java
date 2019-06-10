package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGachaRenditionAfterInputCutInService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGachaRenditionAfterInputCutInDTO;
import io.shm.tsubasa.service.dto.MGachaRenditionAfterInputCutInCriteria;
import io.shm.tsubasa.service.MGachaRenditionAfterInputCutInQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGachaRenditionAfterInputCutIn}.
 */
@RestController
@RequestMapping("/api")
public class MGachaRenditionAfterInputCutInResource {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionAfterInputCutInResource.class);

    private static final String ENTITY_NAME = "mGachaRenditionAfterInputCutIn";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGachaRenditionAfterInputCutInService mGachaRenditionAfterInputCutInService;

    private final MGachaRenditionAfterInputCutInQueryService mGachaRenditionAfterInputCutInQueryService;

    public MGachaRenditionAfterInputCutInResource(MGachaRenditionAfterInputCutInService mGachaRenditionAfterInputCutInService, MGachaRenditionAfterInputCutInQueryService mGachaRenditionAfterInputCutInQueryService) {
        this.mGachaRenditionAfterInputCutInService = mGachaRenditionAfterInputCutInService;
        this.mGachaRenditionAfterInputCutInQueryService = mGachaRenditionAfterInputCutInQueryService;
    }

    /**
     * {@code POST  /m-gacha-rendition-after-input-cut-ins} : Create a new mGachaRenditionAfterInputCutIn.
     *
     * @param mGachaRenditionAfterInputCutInDTO the mGachaRenditionAfterInputCutInDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGachaRenditionAfterInputCutInDTO, or with status {@code 400 (Bad Request)} if the mGachaRenditionAfterInputCutIn has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-gacha-rendition-after-input-cut-ins")
    public ResponseEntity<MGachaRenditionAfterInputCutInDTO> createMGachaRenditionAfterInputCutIn(@Valid @RequestBody MGachaRenditionAfterInputCutInDTO mGachaRenditionAfterInputCutInDTO) throws URISyntaxException {
        log.debug("REST request to save MGachaRenditionAfterInputCutIn : {}", mGachaRenditionAfterInputCutInDTO);
        if (mGachaRenditionAfterInputCutInDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGachaRenditionAfterInputCutIn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGachaRenditionAfterInputCutInDTO result = mGachaRenditionAfterInputCutInService.save(mGachaRenditionAfterInputCutInDTO);
        return ResponseEntity.created(new URI("/api/m-gacha-rendition-after-input-cut-ins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-gacha-rendition-after-input-cut-ins} : Updates an existing mGachaRenditionAfterInputCutIn.
     *
     * @param mGachaRenditionAfterInputCutInDTO the mGachaRenditionAfterInputCutInDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGachaRenditionAfterInputCutInDTO,
     * or with status {@code 400 (Bad Request)} if the mGachaRenditionAfterInputCutInDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGachaRenditionAfterInputCutInDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-gacha-rendition-after-input-cut-ins")
    public ResponseEntity<MGachaRenditionAfterInputCutInDTO> updateMGachaRenditionAfterInputCutIn(@Valid @RequestBody MGachaRenditionAfterInputCutInDTO mGachaRenditionAfterInputCutInDTO) throws URISyntaxException {
        log.debug("REST request to update MGachaRenditionAfterInputCutIn : {}", mGachaRenditionAfterInputCutInDTO);
        if (mGachaRenditionAfterInputCutInDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGachaRenditionAfterInputCutInDTO result = mGachaRenditionAfterInputCutInService.save(mGachaRenditionAfterInputCutInDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGachaRenditionAfterInputCutInDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-gacha-rendition-after-input-cut-ins} : get all the mGachaRenditionAfterInputCutIns.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGachaRenditionAfterInputCutIns in body.
     */
    @GetMapping("/m-gacha-rendition-after-input-cut-ins")
    public ResponseEntity<List<MGachaRenditionAfterInputCutInDTO>> getAllMGachaRenditionAfterInputCutIns(MGachaRenditionAfterInputCutInCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGachaRenditionAfterInputCutIns by criteria: {}", criteria);
        Page<MGachaRenditionAfterInputCutInDTO> page = mGachaRenditionAfterInputCutInQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-gacha-rendition-after-input-cut-ins/count} : count all the mGachaRenditionAfterInputCutIns.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-gacha-rendition-after-input-cut-ins/count")
    public ResponseEntity<Long> countMGachaRenditionAfterInputCutIns(MGachaRenditionAfterInputCutInCriteria criteria) {
        log.debug("REST request to count MGachaRenditionAfterInputCutIns by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGachaRenditionAfterInputCutInQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-gacha-rendition-after-input-cut-ins/:id} : get the "id" mGachaRenditionAfterInputCutIn.
     *
     * @param id the id of the mGachaRenditionAfterInputCutInDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGachaRenditionAfterInputCutInDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-gacha-rendition-after-input-cut-ins/{id}")
    public ResponseEntity<MGachaRenditionAfterInputCutInDTO> getMGachaRenditionAfterInputCutIn(@PathVariable Long id) {
        log.debug("REST request to get MGachaRenditionAfterInputCutIn : {}", id);
        Optional<MGachaRenditionAfterInputCutInDTO> mGachaRenditionAfterInputCutInDTO = mGachaRenditionAfterInputCutInService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGachaRenditionAfterInputCutInDTO);
    }

    /**
     * {@code DELETE  /m-gacha-rendition-after-input-cut-ins/:id} : delete the "id" mGachaRenditionAfterInputCutIn.
     *
     * @param id the id of the mGachaRenditionAfterInputCutInDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-gacha-rendition-after-input-cut-ins/{id}")
    public ResponseEntity<Void> deleteMGachaRenditionAfterInputCutIn(@PathVariable Long id) {
        log.debug("REST request to delete MGachaRenditionAfterInputCutIn : {}", id);
        mGachaRenditionAfterInputCutInService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
