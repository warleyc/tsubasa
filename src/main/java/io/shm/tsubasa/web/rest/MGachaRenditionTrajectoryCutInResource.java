package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGachaRenditionTrajectoryCutInService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryCutInDTO;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryCutInCriteria;
import io.shm.tsubasa.service.MGachaRenditionTrajectoryCutInQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGachaRenditionTrajectoryCutIn}.
 */
@RestController
@RequestMapping("/api")
public class MGachaRenditionTrajectoryCutInResource {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionTrajectoryCutInResource.class);

    private static final String ENTITY_NAME = "mGachaRenditionTrajectoryCutIn";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGachaRenditionTrajectoryCutInService mGachaRenditionTrajectoryCutInService;

    private final MGachaRenditionTrajectoryCutInQueryService mGachaRenditionTrajectoryCutInQueryService;

    public MGachaRenditionTrajectoryCutInResource(MGachaRenditionTrajectoryCutInService mGachaRenditionTrajectoryCutInService, MGachaRenditionTrajectoryCutInQueryService mGachaRenditionTrajectoryCutInQueryService) {
        this.mGachaRenditionTrajectoryCutInService = mGachaRenditionTrajectoryCutInService;
        this.mGachaRenditionTrajectoryCutInQueryService = mGachaRenditionTrajectoryCutInQueryService;
    }

    /**
     * {@code POST  /m-gacha-rendition-trajectory-cut-ins} : Create a new mGachaRenditionTrajectoryCutIn.
     *
     * @param mGachaRenditionTrajectoryCutInDTO the mGachaRenditionTrajectoryCutInDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGachaRenditionTrajectoryCutInDTO, or with status {@code 400 (Bad Request)} if the mGachaRenditionTrajectoryCutIn has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-gacha-rendition-trajectory-cut-ins")
    public ResponseEntity<MGachaRenditionTrajectoryCutInDTO> createMGachaRenditionTrajectoryCutIn(@Valid @RequestBody MGachaRenditionTrajectoryCutInDTO mGachaRenditionTrajectoryCutInDTO) throws URISyntaxException {
        log.debug("REST request to save MGachaRenditionTrajectoryCutIn : {}", mGachaRenditionTrajectoryCutInDTO);
        if (mGachaRenditionTrajectoryCutInDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGachaRenditionTrajectoryCutIn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGachaRenditionTrajectoryCutInDTO result = mGachaRenditionTrajectoryCutInService.save(mGachaRenditionTrajectoryCutInDTO);
        return ResponseEntity.created(new URI("/api/m-gacha-rendition-trajectory-cut-ins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-gacha-rendition-trajectory-cut-ins} : Updates an existing mGachaRenditionTrajectoryCutIn.
     *
     * @param mGachaRenditionTrajectoryCutInDTO the mGachaRenditionTrajectoryCutInDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGachaRenditionTrajectoryCutInDTO,
     * or with status {@code 400 (Bad Request)} if the mGachaRenditionTrajectoryCutInDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGachaRenditionTrajectoryCutInDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-gacha-rendition-trajectory-cut-ins")
    public ResponseEntity<MGachaRenditionTrajectoryCutInDTO> updateMGachaRenditionTrajectoryCutIn(@Valid @RequestBody MGachaRenditionTrajectoryCutInDTO mGachaRenditionTrajectoryCutInDTO) throws URISyntaxException {
        log.debug("REST request to update MGachaRenditionTrajectoryCutIn : {}", mGachaRenditionTrajectoryCutInDTO);
        if (mGachaRenditionTrajectoryCutInDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGachaRenditionTrajectoryCutInDTO result = mGachaRenditionTrajectoryCutInService.save(mGachaRenditionTrajectoryCutInDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGachaRenditionTrajectoryCutInDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-gacha-rendition-trajectory-cut-ins} : get all the mGachaRenditionTrajectoryCutIns.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGachaRenditionTrajectoryCutIns in body.
     */
    @GetMapping("/m-gacha-rendition-trajectory-cut-ins")
    public ResponseEntity<List<MGachaRenditionTrajectoryCutInDTO>> getAllMGachaRenditionTrajectoryCutIns(MGachaRenditionTrajectoryCutInCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGachaRenditionTrajectoryCutIns by criteria: {}", criteria);
        Page<MGachaRenditionTrajectoryCutInDTO> page = mGachaRenditionTrajectoryCutInQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-gacha-rendition-trajectory-cut-ins/count} : count all the mGachaRenditionTrajectoryCutIns.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-gacha-rendition-trajectory-cut-ins/count")
    public ResponseEntity<Long> countMGachaRenditionTrajectoryCutIns(MGachaRenditionTrajectoryCutInCriteria criteria) {
        log.debug("REST request to count MGachaRenditionTrajectoryCutIns by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGachaRenditionTrajectoryCutInQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-gacha-rendition-trajectory-cut-ins/:id} : get the "id" mGachaRenditionTrajectoryCutIn.
     *
     * @param id the id of the mGachaRenditionTrajectoryCutInDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGachaRenditionTrajectoryCutInDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-gacha-rendition-trajectory-cut-ins/{id}")
    public ResponseEntity<MGachaRenditionTrajectoryCutInDTO> getMGachaRenditionTrajectoryCutIn(@PathVariable Long id) {
        log.debug("REST request to get MGachaRenditionTrajectoryCutIn : {}", id);
        Optional<MGachaRenditionTrajectoryCutInDTO> mGachaRenditionTrajectoryCutInDTO = mGachaRenditionTrajectoryCutInService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGachaRenditionTrajectoryCutInDTO);
    }

    /**
     * {@code DELETE  /m-gacha-rendition-trajectory-cut-ins/:id} : delete the "id" mGachaRenditionTrajectoryCutIn.
     *
     * @param id the id of the mGachaRenditionTrajectoryCutInDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-gacha-rendition-trajectory-cut-ins/{id}")
    public ResponseEntity<Void> deleteMGachaRenditionTrajectoryCutIn(@PathVariable Long id) {
        log.debug("REST request to delete MGachaRenditionTrajectoryCutIn : {}", id);
        mGachaRenditionTrajectoryCutInService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
