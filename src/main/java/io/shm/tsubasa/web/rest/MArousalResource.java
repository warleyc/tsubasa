package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MArousalService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MArousalDTO;
import io.shm.tsubasa.service.dto.MArousalCriteria;
import io.shm.tsubasa.service.MArousalQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MArousal}.
 */
@RestController
@RequestMapping("/api")
public class MArousalResource {

    private final Logger log = LoggerFactory.getLogger(MArousalResource.class);

    private static final String ENTITY_NAME = "mArousal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MArousalService mArousalService;

    private final MArousalQueryService mArousalQueryService;

    public MArousalResource(MArousalService mArousalService, MArousalQueryService mArousalQueryService) {
        this.mArousalService = mArousalService;
        this.mArousalQueryService = mArousalQueryService;
    }

    /**
     * {@code POST  /m-arousals} : Create a new mArousal.
     *
     * @param mArousalDTO the mArousalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mArousalDTO, or with status {@code 400 (Bad Request)} if the mArousal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-arousals")
    public ResponseEntity<MArousalDTO> createMArousal(@Valid @RequestBody MArousalDTO mArousalDTO) throws URISyntaxException {
        log.debug("REST request to save MArousal : {}", mArousalDTO);
        if (mArousalDTO.getId() != null) {
            throw new BadRequestAlertException("A new mArousal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MArousalDTO result = mArousalService.save(mArousalDTO);
        return ResponseEntity.created(new URI("/api/m-arousals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-arousals} : Updates an existing mArousal.
     *
     * @param mArousalDTO the mArousalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mArousalDTO,
     * or with status {@code 400 (Bad Request)} if the mArousalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mArousalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-arousals")
    public ResponseEntity<MArousalDTO> updateMArousal(@Valid @RequestBody MArousalDTO mArousalDTO) throws URISyntaxException {
        log.debug("REST request to update MArousal : {}", mArousalDTO);
        if (mArousalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MArousalDTO result = mArousalService.save(mArousalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mArousalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-arousals} : get all the mArousals.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mArousals in body.
     */
    @GetMapping("/m-arousals")
    public ResponseEntity<List<MArousalDTO>> getAllMArousals(MArousalCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MArousals by criteria: {}", criteria);
        Page<MArousalDTO> page = mArousalQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-arousals/count} : count all the mArousals.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-arousals/count")
    public ResponseEntity<Long> countMArousals(MArousalCriteria criteria) {
        log.debug("REST request to count MArousals by criteria: {}", criteria);
        return ResponseEntity.ok().body(mArousalQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-arousals/:id} : get the "id" mArousal.
     *
     * @param id the id of the mArousalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mArousalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-arousals/{id}")
    public ResponseEntity<MArousalDTO> getMArousal(@PathVariable Long id) {
        log.debug("REST request to get MArousal : {}", id);
        Optional<MArousalDTO> mArousalDTO = mArousalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mArousalDTO);
    }

    /**
     * {@code DELETE  /m-arousals/:id} : delete the "id" mArousal.
     *
     * @param id the id of the mArousalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-arousals/{id}")
    public ResponseEntity<Void> deleteMArousal(@PathVariable Long id) {
        log.debug("REST request to delete MArousal : {}", id);
        mArousalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
