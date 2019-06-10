package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDistributeParamPointService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDistributeParamPointDTO;
import io.shm.tsubasa.service.dto.MDistributeParamPointCriteria;
import io.shm.tsubasa.service.MDistributeParamPointQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDistributeParamPoint}.
 */
@RestController
@RequestMapping("/api")
public class MDistributeParamPointResource {

    private final Logger log = LoggerFactory.getLogger(MDistributeParamPointResource.class);

    private static final String ENTITY_NAME = "mDistributeParamPoint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDistributeParamPointService mDistributeParamPointService;

    private final MDistributeParamPointQueryService mDistributeParamPointQueryService;

    public MDistributeParamPointResource(MDistributeParamPointService mDistributeParamPointService, MDistributeParamPointQueryService mDistributeParamPointQueryService) {
        this.mDistributeParamPointService = mDistributeParamPointService;
        this.mDistributeParamPointQueryService = mDistributeParamPointQueryService;
    }

    /**
     * {@code POST  /m-distribute-param-points} : Create a new mDistributeParamPoint.
     *
     * @param mDistributeParamPointDTO the mDistributeParamPointDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDistributeParamPointDTO, or with status {@code 400 (Bad Request)} if the mDistributeParamPoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-distribute-param-points")
    public ResponseEntity<MDistributeParamPointDTO> createMDistributeParamPoint(@Valid @RequestBody MDistributeParamPointDTO mDistributeParamPointDTO) throws URISyntaxException {
        log.debug("REST request to save MDistributeParamPoint : {}", mDistributeParamPointDTO);
        if (mDistributeParamPointDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDistributeParamPoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDistributeParamPointDTO result = mDistributeParamPointService.save(mDistributeParamPointDTO);
        return ResponseEntity.created(new URI("/api/m-distribute-param-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-distribute-param-points} : Updates an existing mDistributeParamPoint.
     *
     * @param mDistributeParamPointDTO the mDistributeParamPointDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDistributeParamPointDTO,
     * or with status {@code 400 (Bad Request)} if the mDistributeParamPointDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDistributeParamPointDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-distribute-param-points")
    public ResponseEntity<MDistributeParamPointDTO> updateMDistributeParamPoint(@Valid @RequestBody MDistributeParamPointDTO mDistributeParamPointDTO) throws URISyntaxException {
        log.debug("REST request to update MDistributeParamPoint : {}", mDistributeParamPointDTO);
        if (mDistributeParamPointDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDistributeParamPointDTO result = mDistributeParamPointService.save(mDistributeParamPointDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDistributeParamPointDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-distribute-param-points} : get all the mDistributeParamPoints.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDistributeParamPoints in body.
     */
    @GetMapping("/m-distribute-param-points")
    public ResponseEntity<List<MDistributeParamPointDTO>> getAllMDistributeParamPoints(MDistributeParamPointCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDistributeParamPoints by criteria: {}", criteria);
        Page<MDistributeParamPointDTO> page = mDistributeParamPointQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-distribute-param-points/count} : count all the mDistributeParamPoints.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-distribute-param-points/count")
    public ResponseEntity<Long> countMDistributeParamPoints(MDistributeParamPointCriteria criteria) {
        log.debug("REST request to count MDistributeParamPoints by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDistributeParamPointQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-distribute-param-points/:id} : get the "id" mDistributeParamPoint.
     *
     * @param id the id of the mDistributeParamPointDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDistributeParamPointDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-distribute-param-points/{id}")
    public ResponseEntity<MDistributeParamPointDTO> getMDistributeParamPoint(@PathVariable Long id) {
        log.debug("REST request to get MDistributeParamPoint : {}", id);
        Optional<MDistributeParamPointDTO> mDistributeParamPointDTO = mDistributeParamPointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDistributeParamPointDTO);
    }

    /**
     * {@code DELETE  /m-distribute-param-points/:id} : delete the "id" mDistributeParamPoint.
     *
     * @param id the id of the mDistributeParamPointDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-distribute-param-points/{id}")
    public ResponseEntity<Void> deleteMDistributeParamPoint(@PathVariable Long id) {
        log.debug("REST request to delete MDistributeParamPoint : {}", id);
        mDistributeParamPointService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
