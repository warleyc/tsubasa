package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGuildRoulettePrizeService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGuildRoulettePrizeDTO;
import io.shm.tsubasa.service.dto.MGuildRoulettePrizeCriteria;
import io.shm.tsubasa.service.MGuildRoulettePrizeQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGuildRoulettePrize}.
 */
@RestController
@RequestMapping("/api")
public class MGuildRoulettePrizeResource {

    private final Logger log = LoggerFactory.getLogger(MGuildRoulettePrizeResource.class);

    private static final String ENTITY_NAME = "mGuildRoulettePrize";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGuildRoulettePrizeService mGuildRoulettePrizeService;

    private final MGuildRoulettePrizeQueryService mGuildRoulettePrizeQueryService;

    public MGuildRoulettePrizeResource(MGuildRoulettePrizeService mGuildRoulettePrizeService, MGuildRoulettePrizeQueryService mGuildRoulettePrizeQueryService) {
        this.mGuildRoulettePrizeService = mGuildRoulettePrizeService;
        this.mGuildRoulettePrizeQueryService = mGuildRoulettePrizeQueryService;
    }

    /**
     * {@code POST  /m-guild-roulette-prizes} : Create a new mGuildRoulettePrize.
     *
     * @param mGuildRoulettePrizeDTO the mGuildRoulettePrizeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGuildRoulettePrizeDTO, or with status {@code 400 (Bad Request)} if the mGuildRoulettePrize has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-guild-roulette-prizes")
    public ResponseEntity<MGuildRoulettePrizeDTO> createMGuildRoulettePrize(@Valid @RequestBody MGuildRoulettePrizeDTO mGuildRoulettePrizeDTO) throws URISyntaxException {
        log.debug("REST request to save MGuildRoulettePrize : {}", mGuildRoulettePrizeDTO);
        if (mGuildRoulettePrizeDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGuildRoulettePrize cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGuildRoulettePrizeDTO result = mGuildRoulettePrizeService.save(mGuildRoulettePrizeDTO);
        return ResponseEntity.created(new URI("/api/m-guild-roulette-prizes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-guild-roulette-prizes} : Updates an existing mGuildRoulettePrize.
     *
     * @param mGuildRoulettePrizeDTO the mGuildRoulettePrizeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGuildRoulettePrizeDTO,
     * or with status {@code 400 (Bad Request)} if the mGuildRoulettePrizeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGuildRoulettePrizeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-guild-roulette-prizes")
    public ResponseEntity<MGuildRoulettePrizeDTO> updateMGuildRoulettePrize(@Valid @RequestBody MGuildRoulettePrizeDTO mGuildRoulettePrizeDTO) throws URISyntaxException {
        log.debug("REST request to update MGuildRoulettePrize : {}", mGuildRoulettePrizeDTO);
        if (mGuildRoulettePrizeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGuildRoulettePrizeDTO result = mGuildRoulettePrizeService.save(mGuildRoulettePrizeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGuildRoulettePrizeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-guild-roulette-prizes} : get all the mGuildRoulettePrizes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGuildRoulettePrizes in body.
     */
    @GetMapping("/m-guild-roulette-prizes")
    public ResponseEntity<List<MGuildRoulettePrizeDTO>> getAllMGuildRoulettePrizes(MGuildRoulettePrizeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGuildRoulettePrizes by criteria: {}", criteria);
        Page<MGuildRoulettePrizeDTO> page = mGuildRoulettePrizeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-guild-roulette-prizes/count} : count all the mGuildRoulettePrizes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-guild-roulette-prizes/count")
    public ResponseEntity<Long> countMGuildRoulettePrizes(MGuildRoulettePrizeCriteria criteria) {
        log.debug("REST request to count MGuildRoulettePrizes by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGuildRoulettePrizeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-guild-roulette-prizes/:id} : get the "id" mGuildRoulettePrize.
     *
     * @param id the id of the mGuildRoulettePrizeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGuildRoulettePrizeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-guild-roulette-prizes/{id}")
    public ResponseEntity<MGuildRoulettePrizeDTO> getMGuildRoulettePrize(@PathVariable Long id) {
        log.debug("REST request to get MGuildRoulettePrize : {}", id);
        Optional<MGuildRoulettePrizeDTO> mGuildRoulettePrizeDTO = mGuildRoulettePrizeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGuildRoulettePrizeDTO);
    }

    /**
     * {@code DELETE  /m-guild-roulette-prizes/:id} : delete the "id" mGuildRoulettePrize.
     *
     * @param id the id of the mGuildRoulettePrizeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-guild-roulette-prizes/{id}")
    public ResponseEntity<Void> deleteMGuildRoulettePrize(@PathVariable Long id) {
        log.debug("REST request to delete MGuildRoulettePrize : {}", id);
        mGuildRoulettePrizeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
