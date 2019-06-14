package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTipsService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTipsDTO;
import io.shm.tsubasa.service.dto.MTipsCriteria;
import io.shm.tsubasa.service.MTipsQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTips}.
 */
@RestController
@RequestMapping("/api")
public class MTipsResource {

    private final Logger log = LoggerFactory.getLogger(MTipsResource.class);

    private static final String ENTITY_NAME = "mTips";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTipsService mTipsService;

    private final MTipsQueryService mTipsQueryService;

    public MTipsResource(MTipsService mTipsService, MTipsQueryService mTipsQueryService) {
        this.mTipsService = mTipsService;
        this.mTipsQueryService = mTipsQueryService;
    }

    /**
     * {@code POST  /m-tips} : Create a new mTips.
     *
     * @param mTipsDTO the mTipsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTipsDTO, or with status {@code 400 (Bad Request)} if the mTips has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-tips")
    public ResponseEntity<MTipsDTO> createMTips(@Valid @RequestBody MTipsDTO mTipsDTO) throws URISyntaxException {
        log.debug("REST request to save MTips : {}", mTipsDTO);
        if (mTipsDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTips cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTipsDTO result = mTipsService.save(mTipsDTO);
        return ResponseEntity.created(new URI("/api/m-tips/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-tips} : Updates an existing mTips.
     *
     * @param mTipsDTO the mTipsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTipsDTO,
     * or with status {@code 400 (Bad Request)} if the mTipsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTipsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-tips")
    public ResponseEntity<MTipsDTO> updateMTips(@Valid @RequestBody MTipsDTO mTipsDTO) throws URISyntaxException {
        log.debug("REST request to update MTips : {}", mTipsDTO);
        if (mTipsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTipsDTO result = mTipsService.save(mTipsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTipsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-tips} : get all the mTips.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTips in body.
     */
    @GetMapping("/m-tips")
    public ResponseEntity<List<MTipsDTO>> getAllMTips(MTipsCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTips by criteria: {}", criteria);
        Page<MTipsDTO> page = mTipsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-tips/count} : count all the mTips.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-tips/count")
    public ResponseEntity<Long> countMTips(MTipsCriteria criteria) {
        log.debug("REST request to count MTips by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTipsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-tips/:id} : get the "id" mTips.
     *
     * @param id the id of the mTipsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTipsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-tips/{id}")
    public ResponseEntity<MTipsDTO> getMTips(@PathVariable Long id) {
        log.debug("REST request to get MTips : {}", id);
        Optional<MTipsDTO> mTipsDTO = mTipsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTipsDTO);
    }

    /**
     * {@code DELETE  /m-tips/:id} : delete the "id" mTips.
     *
     * @param id the id of the mTipsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-tips/{id}")
    public ResponseEntity<Void> deleteMTips(@PathVariable Long id) {
        log.debug("REST request to delete MTips : {}", id);
        mTipsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
