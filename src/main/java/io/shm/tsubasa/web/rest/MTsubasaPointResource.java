package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTsubasaPointService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTsubasaPointDTO;
import io.shm.tsubasa.service.dto.MTsubasaPointCriteria;
import io.shm.tsubasa.service.MTsubasaPointQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTsubasaPoint}.
 */
@RestController
@RequestMapping("/api")
public class MTsubasaPointResource {

    private final Logger log = LoggerFactory.getLogger(MTsubasaPointResource.class);

    private static final String ENTITY_NAME = "mTsubasaPoint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTsubasaPointService mTsubasaPointService;

    private final MTsubasaPointQueryService mTsubasaPointQueryService;

    public MTsubasaPointResource(MTsubasaPointService mTsubasaPointService, MTsubasaPointQueryService mTsubasaPointQueryService) {
        this.mTsubasaPointService = mTsubasaPointService;
        this.mTsubasaPointQueryService = mTsubasaPointQueryService;
    }

    /**
     * {@code POST  /m-tsubasa-points} : Create a new mTsubasaPoint.
     *
     * @param mTsubasaPointDTO the mTsubasaPointDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTsubasaPointDTO, or with status {@code 400 (Bad Request)} if the mTsubasaPoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-tsubasa-points")
    public ResponseEntity<MTsubasaPointDTO> createMTsubasaPoint(@Valid @RequestBody MTsubasaPointDTO mTsubasaPointDTO) throws URISyntaxException {
        log.debug("REST request to save MTsubasaPoint : {}", mTsubasaPointDTO);
        if (mTsubasaPointDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTsubasaPoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTsubasaPointDTO result = mTsubasaPointService.save(mTsubasaPointDTO);
        return ResponseEntity.created(new URI("/api/m-tsubasa-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-tsubasa-points} : Updates an existing mTsubasaPoint.
     *
     * @param mTsubasaPointDTO the mTsubasaPointDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTsubasaPointDTO,
     * or with status {@code 400 (Bad Request)} if the mTsubasaPointDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTsubasaPointDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-tsubasa-points")
    public ResponseEntity<MTsubasaPointDTO> updateMTsubasaPoint(@Valid @RequestBody MTsubasaPointDTO mTsubasaPointDTO) throws URISyntaxException {
        log.debug("REST request to update MTsubasaPoint : {}", mTsubasaPointDTO);
        if (mTsubasaPointDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTsubasaPointDTO result = mTsubasaPointService.save(mTsubasaPointDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTsubasaPointDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-tsubasa-points} : get all the mTsubasaPoints.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTsubasaPoints in body.
     */
    @GetMapping("/m-tsubasa-points")
    public ResponseEntity<List<MTsubasaPointDTO>> getAllMTsubasaPoints(MTsubasaPointCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTsubasaPoints by criteria: {}", criteria);
        Page<MTsubasaPointDTO> page = mTsubasaPointQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-tsubasa-points/count} : count all the mTsubasaPoints.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-tsubasa-points/count")
    public ResponseEntity<Long> countMTsubasaPoints(MTsubasaPointCriteria criteria) {
        log.debug("REST request to count MTsubasaPoints by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTsubasaPointQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-tsubasa-points/:id} : get the "id" mTsubasaPoint.
     *
     * @param id the id of the mTsubasaPointDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTsubasaPointDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-tsubasa-points/{id}")
    public ResponseEntity<MTsubasaPointDTO> getMTsubasaPoint(@PathVariable Long id) {
        log.debug("REST request to get MTsubasaPoint : {}", id);
        Optional<MTsubasaPointDTO> mTsubasaPointDTO = mTsubasaPointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTsubasaPointDTO);
    }

    /**
     * {@code DELETE  /m-tsubasa-points/:id} : delete the "id" mTsubasaPoint.
     *
     * @param id the id of the mTsubasaPointDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-tsubasa-points/{id}")
    public ResponseEntity<Void> deleteMTsubasaPoint(@PathVariable Long id) {
        log.debug("REST request to delete MTsubasaPoint : {}", id);
        mTsubasaPointService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
