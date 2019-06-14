package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MLoginBonusRoundService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MLoginBonusRoundDTO;
import io.shm.tsubasa.service.dto.MLoginBonusRoundCriteria;
import io.shm.tsubasa.service.MLoginBonusRoundQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MLoginBonusRound}.
 */
@RestController
@RequestMapping("/api")
public class MLoginBonusRoundResource {

    private final Logger log = LoggerFactory.getLogger(MLoginBonusRoundResource.class);

    private static final String ENTITY_NAME = "mLoginBonusRound";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MLoginBonusRoundService mLoginBonusRoundService;

    private final MLoginBonusRoundQueryService mLoginBonusRoundQueryService;

    public MLoginBonusRoundResource(MLoginBonusRoundService mLoginBonusRoundService, MLoginBonusRoundQueryService mLoginBonusRoundQueryService) {
        this.mLoginBonusRoundService = mLoginBonusRoundService;
        this.mLoginBonusRoundQueryService = mLoginBonusRoundQueryService;
    }

    /**
     * {@code POST  /m-login-bonus-rounds} : Create a new mLoginBonusRound.
     *
     * @param mLoginBonusRoundDTO the mLoginBonusRoundDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mLoginBonusRoundDTO, or with status {@code 400 (Bad Request)} if the mLoginBonusRound has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-login-bonus-rounds")
    public ResponseEntity<MLoginBonusRoundDTO> createMLoginBonusRound(@Valid @RequestBody MLoginBonusRoundDTO mLoginBonusRoundDTO) throws URISyntaxException {
        log.debug("REST request to save MLoginBonusRound : {}", mLoginBonusRoundDTO);
        if (mLoginBonusRoundDTO.getId() != null) {
            throw new BadRequestAlertException("A new mLoginBonusRound cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MLoginBonusRoundDTO result = mLoginBonusRoundService.save(mLoginBonusRoundDTO);
        return ResponseEntity.created(new URI("/api/m-login-bonus-rounds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-login-bonus-rounds} : Updates an existing mLoginBonusRound.
     *
     * @param mLoginBonusRoundDTO the mLoginBonusRoundDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mLoginBonusRoundDTO,
     * or with status {@code 400 (Bad Request)} if the mLoginBonusRoundDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mLoginBonusRoundDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-login-bonus-rounds")
    public ResponseEntity<MLoginBonusRoundDTO> updateMLoginBonusRound(@Valid @RequestBody MLoginBonusRoundDTO mLoginBonusRoundDTO) throws URISyntaxException {
        log.debug("REST request to update MLoginBonusRound : {}", mLoginBonusRoundDTO);
        if (mLoginBonusRoundDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MLoginBonusRoundDTO result = mLoginBonusRoundService.save(mLoginBonusRoundDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mLoginBonusRoundDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-login-bonus-rounds} : get all the mLoginBonusRounds.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mLoginBonusRounds in body.
     */
    @GetMapping("/m-login-bonus-rounds")
    public ResponseEntity<List<MLoginBonusRoundDTO>> getAllMLoginBonusRounds(MLoginBonusRoundCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MLoginBonusRounds by criteria: {}", criteria);
        Page<MLoginBonusRoundDTO> page = mLoginBonusRoundQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-login-bonus-rounds/count} : count all the mLoginBonusRounds.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-login-bonus-rounds/count")
    public ResponseEntity<Long> countMLoginBonusRounds(MLoginBonusRoundCriteria criteria) {
        log.debug("REST request to count MLoginBonusRounds by criteria: {}", criteria);
        return ResponseEntity.ok().body(mLoginBonusRoundQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-login-bonus-rounds/:id} : get the "id" mLoginBonusRound.
     *
     * @param id the id of the mLoginBonusRoundDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mLoginBonusRoundDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-login-bonus-rounds/{id}")
    public ResponseEntity<MLoginBonusRoundDTO> getMLoginBonusRound(@PathVariable Long id) {
        log.debug("REST request to get MLoginBonusRound : {}", id);
        Optional<MLoginBonusRoundDTO> mLoginBonusRoundDTO = mLoginBonusRoundService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mLoginBonusRoundDTO);
    }

    /**
     * {@code DELETE  /m-login-bonus-rounds/:id} : delete the "id" mLoginBonusRound.
     *
     * @param id the id of the mLoginBonusRoundDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-login-bonus-rounds/{id}")
    public ResponseEntity<Void> deleteMLoginBonusRound(@PathVariable Long id) {
        log.debug("REST request to delete MLoginBonusRound : {}", id);
        mLoginBonusRoundService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
