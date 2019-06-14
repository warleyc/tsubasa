package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MShootService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MShootDTO;
import io.shm.tsubasa.service.dto.MShootCriteria;
import io.shm.tsubasa.service.MShootQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MShoot}.
 */
@RestController
@RequestMapping("/api")
public class MShootResource {

    private final Logger log = LoggerFactory.getLogger(MShootResource.class);

    private static final String ENTITY_NAME = "mShoot";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MShootService mShootService;

    private final MShootQueryService mShootQueryService;

    public MShootResource(MShootService mShootService, MShootQueryService mShootQueryService) {
        this.mShootService = mShootService;
        this.mShootQueryService = mShootQueryService;
    }

    /**
     * {@code POST  /m-shoots} : Create a new mShoot.
     *
     * @param mShootDTO the mShootDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mShootDTO, or with status {@code 400 (Bad Request)} if the mShoot has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-shoots")
    public ResponseEntity<MShootDTO> createMShoot(@Valid @RequestBody MShootDTO mShootDTO) throws URISyntaxException {
        log.debug("REST request to save MShoot : {}", mShootDTO);
        if (mShootDTO.getId() != null) {
            throw new BadRequestAlertException("A new mShoot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MShootDTO result = mShootService.save(mShootDTO);
        return ResponseEntity.created(new URI("/api/m-shoots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-shoots} : Updates an existing mShoot.
     *
     * @param mShootDTO the mShootDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mShootDTO,
     * or with status {@code 400 (Bad Request)} if the mShootDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mShootDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-shoots")
    public ResponseEntity<MShootDTO> updateMShoot(@Valid @RequestBody MShootDTO mShootDTO) throws URISyntaxException {
        log.debug("REST request to update MShoot : {}", mShootDTO);
        if (mShootDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MShootDTO result = mShootService.save(mShootDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mShootDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-shoots} : get all the mShoots.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mShoots in body.
     */
    @GetMapping("/m-shoots")
    public ResponseEntity<List<MShootDTO>> getAllMShoots(MShootCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MShoots by criteria: {}", criteria);
        Page<MShootDTO> page = mShootQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-shoots/count} : count all the mShoots.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-shoots/count")
    public ResponseEntity<Long> countMShoots(MShootCriteria criteria) {
        log.debug("REST request to count MShoots by criteria: {}", criteria);
        return ResponseEntity.ok().body(mShootQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-shoots/:id} : get the "id" mShoot.
     *
     * @param id the id of the mShootDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mShootDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-shoots/{id}")
    public ResponseEntity<MShootDTO> getMShoot(@PathVariable Long id) {
        log.debug("REST request to get MShoot : {}", id);
        Optional<MShootDTO> mShootDTO = mShootService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mShootDTO);
    }

    /**
     * {@code DELETE  /m-shoots/:id} : delete the "id" mShoot.
     *
     * @param id the id of the mShootDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-shoots/{id}")
    public ResponseEntity<Void> deleteMShoot(@PathVariable Long id) {
        log.debug("REST request to delete MShoot : {}", id);
        mShootService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
