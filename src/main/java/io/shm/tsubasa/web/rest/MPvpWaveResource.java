package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MPvpWaveService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MPvpWaveDTO;
import io.shm.tsubasa.service.dto.MPvpWaveCriteria;
import io.shm.tsubasa.service.MPvpWaveQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MPvpWave}.
 */
@RestController
@RequestMapping("/api")
public class MPvpWaveResource {

    private final Logger log = LoggerFactory.getLogger(MPvpWaveResource.class);

    private static final String ENTITY_NAME = "mPvpWave";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MPvpWaveService mPvpWaveService;

    private final MPvpWaveQueryService mPvpWaveQueryService;

    public MPvpWaveResource(MPvpWaveService mPvpWaveService, MPvpWaveQueryService mPvpWaveQueryService) {
        this.mPvpWaveService = mPvpWaveService;
        this.mPvpWaveQueryService = mPvpWaveQueryService;
    }

    /**
     * {@code POST  /m-pvp-waves} : Create a new mPvpWave.
     *
     * @param mPvpWaveDTO the mPvpWaveDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mPvpWaveDTO, or with status {@code 400 (Bad Request)} if the mPvpWave has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-pvp-waves")
    public ResponseEntity<MPvpWaveDTO> createMPvpWave(@Valid @RequestBody MPvpWaveDTO mPvpWaveDTO) throws URISyntaxException {
        log.debug("REST request to save MPvpWave : {}", mPvpWaveDTO);
        if (mPvpWaveDTO.getId() != null) {
            throw new BadRequestAlertException("A new mPvpWave cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MPvpWaveDTO result = mPvpWaveService.save(mPvpWaveDTO);
        return ResponseEntity.created(new URI("/api/m-pvp-waves/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-pvp-waves} : Updates an existing mPvpWave.
     *
     * @param mPvpWaveDTO the mPvpWaveDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mPvpWaveDTO,
     * or with status {@code 400 (Bad Request)} if the mPvpWaveDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mPvpWaveDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-pvp-waves")
    public ResponseEntity<MPvpWaveDTO> updateMPvpWave(@Valid @RequestBody MPvpWaveDTO mPvpWaveDTO) throws URISyntaxException {
        log.debug("REST request to update MPvpWave : {}", mPvpWaveDTO);
        if (mPvpWaveDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MPvpWaveDTO result = mPvpWaveService.save(mPvpWaveDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mPvpWaveDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-pvp-waves} : get all the mPvpWaves.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mPvpWaves in body.
     */
    @GetMapping("/m-pvp-waves")
    public ResponseEntity<List<MPvpWaveDTO>> getAllMPvpWaves(MPvpWaveCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MPvpWaves by criteria: {}", criteria);
        Page<MPvpWaveDTO> page = mPvpWaveQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-pvp-waves/count} : count all the mPvpWaves.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-pvp-waves/count")
    public ResponseEntity<Long> countMPvpWaves(MPvpWaveCriteria criteria) {
        log.debug("REST request to count MPvpWaves by criteria: {}", criteria);
        return ResponseEntity.ok().body(mPvpWaveQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-pvp-waves/:id} : get the "id" mPvpWave.
     *
     * @param id the id of the mPvpWaveDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mPvpWaveDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-pvp-waves/{id}")
    public ResponseEntity<MPvpWaveDTO> getMPvpWave(@PathVariable Long id) {
        log.debug("REST request to get MPvpWave : {}", id);
        Optional<MPvpWaveDTO> mPvpWaveDTO = mPvpWaveService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mPvpWaveDTO);
    }

    /**
     * {@code DELETE  /m-pvp-waves/:id} : delete the "id" mPvpWave.
     *
     * @param id the id of the mPvpWaveDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-pvp-waves/{id}")
    public ResponseEntity<Void> deleteMPvpWave(@PathVariable Long id) {
        log.debug("REST request to delete MPvpWave : {}", id);
        mPvpWaveService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
