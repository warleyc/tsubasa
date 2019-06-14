package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MPvpWatcherStampService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MPvpWatcherStampDTO;
import io.shm.tsubasa.service.dto.MPvpWatcherStampCriteria;
import io.shm.tsubasa.service.MPvpWatcherStampQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MPvpWatcherStamp}.
 */
@RestController
@RequestMapping("/api")
public class MPvpWatcherStampResource {

    private final Logger log = LoggerFactory.getLogger(MPvpWatcherStampResource.class);

    private static final String ENTITY_NAME = "mPvpWatcherStamp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MPvpWatcherStampService mPvpWatcherStampService;

    private final MPvpWatcherStampQueryService mPvpWatcherStampQueryService;

    public MPvpWatcherStampResource(MPvpWatcherStampService mPvpWatcherStampService, MPvpWatcherStampQueryService mPvpWatcherStampQueryService) {
        this.mPvpWatcherStampService = mPvpWatcherStampService;
        this.mPvpWatcherStampQueryService = mPvpWatcherStampQueryService;
    }

    /**
     * {@code POST  /m-pvp-watcher-stamps} : Create a new mPvpWatcherStamp.
     *
     * @param mPvpWatcherStampDTO the mPvpWatcherStampDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mPvpWatcherStampDTO, or with status {@code 400 (Bad Request)} if the mPvpWatcherStamp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-pvp-watcher-stamps")
    public ResponseEntity<MPvpWatcherStampDTO> createMPvpWatcherStamp(@Valid @RequestBody MPvpWatcherStampDTO mPvpWatcherStampDTO) throws URISyntaxException {
        log.debug("REST request to save MPvpWatcherStamp : {}", mPvpWatcherStampDTO);
        if (mPvpWatcherStampDTO.getId() != null) {
            throw new BadRequestAlertException("A new mPvpWatcherStamp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MPvpWatcherStampDTO result = mPvpWatcherStampService.save(mPvpWatcherStampDTO);
        return ResponseEntity.created(new URI("/api/m-pvp-watcher-stamps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-pvp-watcher-stamps} : Updates an existing mPvpWatcherStamp.
     *
     * @param mPvpWatcherStampDTO the mPvpWatcherStampDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mPvpWatcherStampDTO,
     * or with status {@code 400 (Bad Request)} if the mPvpWatcherStampDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mPvpWatcherStampDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-pvp-watcher-stamps")
    public ResponseEntity<MPvpWatcherStampDTO> updateMPvpWatcherStamp(@Valid @RequestBody MPvpWatcherStampDTO mPvpWatcherStampDTO) throws URISyntaxException {
        log.debug("REST request to update MPvpWatcherStamp : {}", mPvpWatcherStampDTO);
        if (mPvpWatcherStampDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MPvpWatcherStampDTO result = mPvpWatcherStampService.save(mPvpWatcherStampDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mPvpWatcherStampDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-pvp-watcher-stamps} : get all the mPvpWatcherStamps.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mPvpWatcherStamps in body.
     */
    @GetMapping("/m-pvp-watcher-stamps")
    public ResponseEntity<List<MPvpWatcherStampDTO>> getAllMPvpWatcherStamps(MPvpWatcherStampCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MPvpWatcherStamps by criteria: {}", criteria);
        Page<MPvpWatcherStampDTO> page = mPvpWatcherStampQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-pvp-watcher-stamps/count} : count all the mPvpWatcherStamps.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-pvp-watcher-stamps/count")
    public ResponseEntity<Long> countMPvpWatcherStamps(MPvpWatcherStampCriteria criteria) {
        log.debug("REST request to count MPvpWatcherStamps by criteria: {}", criteria);
        return ResponseEntity.ok().body(mPvpWatcherStampQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-pvp-watcher-stamps/:id} : get the "id" mPvpWatcherStamp.
     *
     * @param id the id of the mPvpWatcherStampDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mPvpWatcherStampDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-pvp-watcher-stamps/{id}")
    public ResponseEntity<MPvpWatcherStampDTO> getMPvpWatcherStamp(@PathVariable Long id) {
        log.debug("REST request to get MPvpWatcherStamp : {}", id);
        Optional<MPvpWatcherStampDTO> mPvpWatcherStampDTO = mPvpWatcherStampService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mPvpWatcherStampDTO);
    }

    /**
     * {@code DELETE  /m-pvp-watcher-stamps/:id} : delete the "id" mPvpWatcherStamp.
     *
     * @param id the id of the mPvpWatcherStampDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-pvp-watcher-stamps/{id}")
    public ResponseEntity<Void> deleteMPvpWatcherStamp(@PathVariable Long id) {
        log.debug("REST request to delete MPvpWatcherStamp : {}", id);
        mPvpWatcherStampService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
