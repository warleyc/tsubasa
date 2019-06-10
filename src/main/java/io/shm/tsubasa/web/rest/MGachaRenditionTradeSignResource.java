package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGachaRenditionTradeSignService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGachaRenditionTradeSignDTO;
import io.shm.tsubasa.service.dto.MGachaRenditionTradeSignCriteria;
import io.shm.tsubasa.service.MGachaRenditionTradeSignQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGachaRenditionTradeSign}.
 */
@RestController
@RequestMapping("/api")
public class MGachaRenditionTradeSignResource {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionTradeSignResource.class);

    private static final String ENTITY_NAME = "mGachaRenditionTradeSign";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGachaRenditionTradeSignService mGachaRenditionTradeSignService;

    private final MGachaRenditionTradeSignQueryService mGachaRenditionTradeSignQueryService;

    public MGachaRenditionTradeSignResource(MGachaRenditionTradeSignService mGachaRenditionTradeSignService, MGachaRenditionTradeSignQueryService mGachaRenditionTradeSignQueryService) {
        this.mGachaRenditionTradeSignService = mGachaRenditionTradeSignService;
        this.mGachaRenditionTradeSignQueryService = mGachaRenditionTradeSignQueryService;
    }

    /**
     * {@code POST  /m-gacha-rendition-trade-signs} : Create a new mGachaRenditionTradeSign.
     *
     * @param mGachaRenditionTradeSignDTO the mGachaRenditionTradeSignDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGachaRenditionTradeSignDTO, or with status {@code 400 (Bad Request)} if the mGachaRenditionTradeSign has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-gacha-rendition-trade-signs")
    public ResponseEntity<MGachaRenditionTradeSignDTO> createMGachaRenditionTradeSign(@Valid @RequestBody MGachaRenditionTradeSignDTO mGachaRenditionTradeSignDTO) throws URISyntaxException {
        log.debug("REST request to save MGachaRenditionTradeSign : {}", mGachaRenditionTradeSignDTO);
        if (mGachaRenditionTradeSignDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGachaRenditionTradeSign cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGachaRenditionTradeSignDTO result = mGachaRenditionTradeSignService.save(mGachaRenditionTradeSignDTO);
        return ResponseEntity.created(new URI("/api/m-gacha-rendition-trade-signs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-gacha-rendition-trade-signs} : Updates an existing mGachaRenditionTradeSign.
     *
     * @param mGachaRenditionTradeSignDTO the mGachaRenditionTradeSignDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGachaRenditionTradeSignDTO,
     * or with status {@code 400 (Bad Request)} if the mGachaRenditionTradeSignDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGachaRenditionTradeSignDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-gacha-rendition-trade-signs")
    public ResponseEntity<MGachaRenditionTradeSignDTO> updateMGachaRenditionTradeSign(@Valid @RequestBody MGachaRenditionTradeSignDTO mGachaRenditionTradeSignDTO) throws URISyntaxException {
        log.debug("REST request to update MGachaRenditionTradeSign : {}", mGachaRenditionTradeSignDTO);
        if (mGachaRenditionTradeSignDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGachaRenditionTradeSignDTO result = mGachaRenditionTradeSignService.save(mGachaRenditionTradeSignDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGachaRenditionTradeSignDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-gacha-rendition-trade-signs} : get all the mGachaRenditionTradeSigns.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGachaRenditionTradeSigns in body.
     */
    @GetMapping("/m-gacha-rendition-trade-signs")
    public ResponseEntity<List<MGachaRenditionTradeSignDTO>> getAllMGachaRenditionTradeSigns(MGachaRenditionTradeSignCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGachaRenditionTradeSigns by criteria: {}", criteria);
        Page<MGachaRenditionTradeSignDTO> page = mGachaRenditionTradeSignQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-gacha-rendition-trade-signs/count} : count all the mGachaRenditionTradeSigns.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-gacha-rendition-trade-signs/count")
    public ResponseEntity<Long> countMGachaRenditionTradeSigns(MGachaRenditionTradeSignCriteria criteria) {
        log.debug("REST request to count MGachaRenditionTradeSigns by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGachaRenditionTradeSignQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-gacha-rendition-trade-signs/:id} : get the "id" mGachaRenditionTradeSign.
     *
     * @param id the id of the mGachaRenditionTradeSignDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGachaRenditionTradeSignDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-gacha-rendition-trade-signs/{id}")
    public ResponseEntity<MGachaRenditionTradeSignDTO> getMGachaRenditionTradeSign(@PathVariable Long id) {
        log.debug("REST request to get MGachaRenditionTradeSign : {}", id);
        Optional<MGachaRenditionTradeSignDTO> mGachaRenditionTradeSignDTO = mGachaRenditionTradeSignService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGachaRenditionTradeSignDTO);
    }

    /**
     * {@code DELETE  /m-gacha-rendition-trade-signs/:id} : delete the "id" mGachaRenditionTradeSign.
     *
     * @param id the id of the mGachaRenditionTradeSignDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-gacha-rendition-trade-signs/{id}")
    public ResponseEntity<Void> deleteMGachaRenditionTradeSign(@PathVariable Long id) {
        log.debug("REST request to delete MGachaRenditionTradeSign : {}", id);
        mGachaRenditionTradeSignService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
