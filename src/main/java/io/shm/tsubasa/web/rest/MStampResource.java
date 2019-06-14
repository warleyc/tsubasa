package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MStampService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MStampDTO;
import io.shm.tsubasa.service.dto.MStampCriteria;
import io.shm.tsubasa.service.MStampQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MStamp}.
 */
@RestController
@RequestMapping("/api")
public class MStampResource {

    private final Logger log = LoggerFactory.getLogger(MStampResource.class);

    private static final String ENTITY_NAME = "mStamp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MStampService mStampService;

    private final MStampQueryService mStampQueryService;

    public MStampResource(MStampService mStampService, MStampQueryService mStampQueryService) {
        this.mStampService = mStampService;
        this.mStampQueryService = mStampQueryService;
    }

    /**
     * {@code POST  /m-stamps} : Create a new mStamp.
     *
     * @param mStampDTO the mStampDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mStampDTO, or with status {@code 400 (Bad Request)} if the mStamp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-stamps")
    public ResponseEntity<MStampDTO> createMStamp(@Valid @RequestBody MStampDTO mStampDTO) throws URISyntaxException {
        log.debug("REST request to save MStamp : {}", mStampDTO);
        if (mStampDTO.getId() != null) {
            throw new BadRequestAlertException("A new mStamp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MStampDTO result = mStampService.save(mStampDTO);
        return ResponseEntity.created(new URI("/api/m-stamps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-stamps} : Updates an existing mStamp.
     *
     * @param mStampDTO the mStampDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mStampDTO,
     * or with status {@code 400 (Bad Request)} if the mStampDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mStampDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-stamps")
    public ResponseEntity<MStampDTO> updateMStamp(@Valid @RequestBody MStampDTO mStampDTO) throws URISyntaxException {
        log.debug("REST request to update MStamp : {}", mStampDTO);
        if (mStampDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MStampDTO result = mStampService.save(mStampDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mStampDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-stamps} : get all the mStamps.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mStamps in body.
     */
    @GetMapping("/m-stamps")
    public ResponseEntity<List<MStampDTO>> getAllMStamps(MStampCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MStamps by criteria: {}", criteria);
        Page<MStampDTO> page = mStampQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-stamps/count} : count all the mStamps.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-stamps/count")
    public ResponseEntity<Long> countMStamps(MStampCriteria criteria) {
        log.debug("REST request to count MStamps by criteria: {}", criteria);
        return ResponseEntity.ok().body(mStampQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-stamps/:id} : get the "id" mStamp.
     *
     * @param id the id of the mStampDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mStampDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-stamps/{id}")
    public ResponseEntity<MStampDTO> getMStamp(@PathVariable Long id) {
        log.debug("REST request to get MStamp : {}", id);
        Optional<MStampDTO> mStampDTO = mStampService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mStampDTO);
    }

    /**
     * {@code DELETE  /m-stamps/:id} : delete the "id" mStamp.
     *
     * @param id the id of the mStampDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-stamps/{id}")
    public ResponseEntity<Void> deleteMStamp(@PathVariable Long id) {
        log.debug("REST request to delete MStamp : {}", id);
        mStampService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
