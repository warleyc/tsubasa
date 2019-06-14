package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MSellCardCoinService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MSellCardCoinDTO;
import io.shm.tsubasa.service.dto.MSellCardCoinCriteria;
import io.shm.tsubasa.service.MSellCardCoinQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MSellCardCoin}.
 */
@RestController
@RequestMapping("/api")
public class MSellCardCoinResource {

    private final Logger log = LoggerFactory.getLogger(MSellCardCoinResource.class);

    private static final String ENTITY_NAME = "mSellCardCoin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MSellCardCoinService mSellCardCoinService;

    private final MSellCardCoinQueryService mSellCardCoinQueryService;

    public MSellCardCoinResource(MSellCardCoinService mSellCardCoinService, MSellCardCoinQueryService mSellCardCoinQueryService) {
        this.mSellCardCoinService = mSellCardCoinService;
        this.mSellCardCoinQueryService = mSellCardCoinQueryService;
    }

    /**
     * {@code POST  /m-sell-card-coins} : Create a new mSellCardCoin.
     *
     * @param mSellCardCoinDTO the mSellCardCoinDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mSellCardCoinDTO, or with status {@code 400 (Bad Request)} if the mSellCardCoin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-sell-card-coins")
    public ResponseEntity<MSellCardCoinDTO> createMSellCardCoin(@Valid @RequestBody MSellCardCoinDTO mSellCardCoinDTO) throws URISyntaxException {
        log.debug("REST request to save MSellCardCoin : {}", mSellCardCoinDTO);
        if (mSellCardCoinDTO.getId() != null) {
            throw new BadRequestAlertException("A new mSellCardCoin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MSellCardCoinDTO result = mSellCardCoinService.save(mSellCardCoinDTO);
        return ResponseEntity.created(new URI("/api/m-sell-card-coins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-sell-card-coins} : Updates an existing mSellCardCoin.
     *
     * @param mSellCardCoinDTO the mSellCardCoinDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mSellCardCoinDTO,
     * or with status {@code 400 (Bad Request)} if the mSellCardCoinDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mSellCardCoinDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-sell-card-coins")
    public ResponseEntity<MSellCardCoinDTO> updateMSellCardCoin(@Valid @RequestBody MSellCardCoinDTO mSellCardCoinDTO) throws URISyntaxException {
        log.debug("REST request to update MSellCardCoin : {}", mSellCardCoinDTO);
        if (mSellCardCoinDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MSellCardCoinDTO result = mSellCardCoinService.save(mSellCardCoinDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mSellCardCoinDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-sell-card-coins} : get all the mSellCardCoins.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mSellCardCoins in body.
     */
    @GetMapping("/m-sell-card-coins")
    public ResponseEntity<List<MSellCardCoinDTO>> getAllMSellCardCoins(MSellCardCoinCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MSellCardCoins by criteria: {}", criteria);
        Page<MSellCardCoinDTO> page = mSellCardCoinQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-sell-card-coins/count} : count all the mSellCardCoins.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-sell-card-coins/count")
    public ResponseEntity<Long> countMSellCardCoins(MSellCardCoinCriteria criteria) {
        log.debug("REST request to count MSellCardCoins by criteria: {}", criteria);
        return ResponseEntity.ok().body(mSellCardCoinQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-sell-card-coins/:id} : get the "id" mSellCardCoin.
     *
     * @param id the id of the mSellCardCoinDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mSellCardCoinDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-sell-card-coins/{id}")
    public ResponseEntity<MSellCardCoinDTO> getMSellCardCoin(@PathVariable Long id) {
        log.debug("REST request to get MSellCardCoin : {}", id);
        Optional<MSellCardCoinDTO> mSellCardCoinDTO = mSellCardCoinService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mSellCardCoinDTO);
    }

    /**
     * {@code DELETE  /m-sell-card-coins/:id} : delete the "id" mSellCardCoin.
     *
     * @param id the id of the mSellCardCoinDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-sell-card-coins/{id}")
    public ResponseEntity<Void> deleteMSellCardCoin(@PathVariable Long id) {
        log.debug("REST request to delete MSellCardCoin : {}", id);
        mSellCardCoinService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
