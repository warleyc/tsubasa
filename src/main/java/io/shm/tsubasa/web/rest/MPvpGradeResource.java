package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MPvpGradeService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MPvpGradeDTO;
import io.shm.tsubasa.service.dto.MPvpGradeCriteria;
import io.shm.tsubasa.service.MPvpGradeQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MPvpGrade}.
 */
@RestController
@RequestMapping("/api")
public class MPvpGradeResource {

    private final Logger log = LoggerFactory.getLogger(MPvpGradeResource.class);

    private static final String ENTITY_NAME = "mPvpGrade";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MPvpGradeService mPvpGradeService;

    private final MPvpGradeQueryService mPvpGradeQueryService;

    public MPvpGradeResource(MPvpGradeService mPvpGradeService, MPvpGradeQueryService mPvpGradeQueryService) {
        this.mPvpGradeService = mPvpGradeService;
        this.mPvpGradeQueryService = mPvpGradeQueryService;
    }

    /**
     * {@code POST  /m-pvp-grades} : Create a new mPvpGrade.
     *
     * @param mPvpGradeDTO the mPvpGradeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mPvpGradeDTO, or with status {@code 400 (Bad Request)} if the mPvpGrade has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-pvp-grades")
    public ResponseEntity<MPvpGradeDTO> createMPvpGrade(@Valid @RequestBody MPvpGradeDTO mPvpGradeDTO) throws URISyntaxException {
        log.debug("REST request to save MPvpGrade : {}", mPvpGradeDTO);
        if (mPvpGradeDTO.getId() != null) {
            throw new BadRequestAlertException("A new mPvpGrade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MPvpGradeDTO result = mPvpGradeService.save(mPvpGradeDTO);
        return ResponseEntity.created(new URI("/api/m-pvp-grades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-pvp-grades} : Updates an existing mPvpGrade.
     *
     * @param mPvpGradeDTO the mPvpGradeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mPvpGradeDTO,
     * or with status {@code 400 (Bad Request)} if the mPvpGradeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mPvpGradeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-pvp-grades")
    public ResponseEntity<MPvpGradeDTO> updateMPvpGrade(@Valid @RequestBody MPvpGradeDTO mPvpGradeDTO) throws URISyntaxException {
        log.debug("REST request to update MPvpGrade : {}", mPvpGradeDTO);
        if (mPvpGradeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MPvpGradeDTO result = mPvpGradeService.save(mPvpGradeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mPvpGradeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-pvp-grades} : get all the mPvpGrades.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mPvpGrades in body.
     */
    @GetMapping("/m-pvp-grades")
    public ResponseEntity<List<MPvpGradeDTO>> getAllMPvpGrades(MPvpGradeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MPvpGrades by criteria: {}", criteria);
        Page<MPvpGradeDTO> page = mPvpGradeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-pvp-grades/count} : count all the mPvpGrades.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-pvp-grades/count")
    public ResponseEntity<Long> countMPvpGrades(MPvpGradeCriteria criteria) {
        log.debug("REST request to count MPvpGrades by criteria: {}", criteria);
        return ResponseEntity.ok().body(mPvpGradeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-pvp-grades/:id} : get the "id" mPvpGrade.
     *
     * @param id the id of the mPvpGradeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mPvpGradeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-pvp-grades/{id}")
    public ResponseEntity<MPvpGradeDTO> getMPvpGrade(@PathVariable Long id) {
        log.debug("REST request to get MPvpGrade : {}", id);
        Optional<MPvpGradeDTO> mPvpGradeDTO = mPvpGradeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mPvpGradeDTO);
    }

    /**
     * {@code DELETE  /m-pvp-grades/:id} : delete the "id" mPvpGrade.
     *
     * @param id the id of the mPvpGradeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-pvp-grades/{id}")
    public ResponseEntity<Void> deleteMPvpGrade(@PathVariable Long id) {
        log.debug("REST request to delete MPvpGrade : {}", id);
        mPvpGradeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
