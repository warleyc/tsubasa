package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MFullPowerPointService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MFullPowerPointDTO;
import io.shm.tsubasa.service.dto.MFullPowerPointCriteria;
import io.shm.tsubasa.service.MFullPowerPointQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MFullPowerPoint}.
 */
@RestController
@RequestMapping("/api")
public class MFullPowerPointResource {

    private final Logger log = LoggerFactory.getLogger(MFullPowerPointResource.class);

    private static final String ENTITY_NAME = "mFullPowerPoint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MFullPowerPointService mFullPowerPointService;

    private final MFullPowerPointQueryService mFullPowerPointQueryService;

    public MFullPowerPointResource(MFullPowerPointService mFullPowerPointService, MFullPowerPointQueryService mFullPowerPointQueryService) {
        this.mFullPowerPointService = mFullPowerPointService;
        this.mFullPowerPointQueryService = mFullPowerPointQueryService;
    }

    /**
     * {@code POST  /m-full-power-points} : Create a new mFullPowerPoint.
     *
     * @param mFullPowerPointDTO the mFullPowerPointDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mFullPowerPointDTO, or with status {@code 400 (Bad Request)} if the mFullPowerPoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-full-power-points")
    public ResponseEntity<MFullPowerPointDTO> createMFullPowerPoint(@Valid @RequestBody MFullPowerPointDTO mFullPowerPointDTO) throws URISyntaxException {
        log.debug("REST request to save MFullPowerPoint : {}", mFullPowerPointDTO);
        if (mFullPowerPointDTO.getId() != null) {
            throw new BadRequestAlertException("A new mFullPowerPoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MFullPowerPointDTO result = mFullPowerPointService.save(mFullPowerPointDTO);
        return ResponseEntity.created(new URI("/api/m-full-power-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-full-power-points} : Updates an existing mFullPowerPoint.
     *
     * @param mFullPowerPointDTO the mFullPowerPointDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mFullPowerPointDTO,
     * or with status {@code 400 (Bad Request)} if the mFullPowerPointDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mFullPowerPointDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-full-power-points")
    public ResponseEntity<MFullPowerPointDTO> updateMFullPowerPoint(@Valid @RequestBody MFullPowerPointDTO mFullPowerPointDTO) throws URISyntaxException {
        log.debug("REST request to update MFullPowerPoint : {}", mFullPowerPointDTO);
        if (mFullPowerPointDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MFullPowerPointDTO result = mFullPowerPointService.save(mFullPowerPointDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mFullPowerPointDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-full-power-points} : get all the mFullPowerPoints.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mFullPowerPoints in body.
     */
    @GetMapping("/m-full-power-points")
    public ResponseEntity<List<MFullPowerPointDTO>> getAllMFullPowerPoints(MFullPowerPointCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MFullPowerPoints by criteria: {}", criteria);
        Page<MFullPowerPointDTO> page = mFullPowerPointQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-full-power-points/count} : count all the mFullPowerPoints.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-full-power-points/count")
    public ResponseEntity<Long> countMFullPowerPoints(MFullPowerPointCriteria criteria) {
        log.debug("REST request to count MFullPowerPoints by criteria: {}", criteria);
        return ResponseEntity.ok().body(mFullPowerPointQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-full-power-points/:id} : get the "id" mFullPowerPoint.
     *
     * @param id the id of the mFullPowerPointDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mFullPowerPointDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-full-power-points/{id}")
    public ResponseEntity<MFullPowerPointDTO> getMFullPowerPoint(@PathVariable Long id) {
        log.debug("REST request to get MFullPowerPoint : {}", id);
        Optional<MFullPowerPointDTO> mFullPowerPointDTO = mFullPowerPointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mFullPowerPointDTO);
    }

    /**
     * {@code DELETE  /m-full-power-points/:id} : delete the "id" mFullPowerPoint.
     *
     * @param id the id of the mFullPowerPointDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-full-power-points/{id}")
    public ResponseEntity<Void> deleteMFullPowerPoint(@PathVariable Long id) {
        log.debug("REST request to delete MFullPowerPoint : {}", id);
        mFullPowerPointService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
