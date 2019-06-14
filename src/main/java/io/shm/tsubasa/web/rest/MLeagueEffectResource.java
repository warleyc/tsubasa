package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MLeagueEffectService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MLeagueEffectDTO;
import io.shm.tsubasa.service.dto.MLeagueEffectCriteria;
import io.shm.tsubasa.service.MLeagueEffectQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MLeagueEffect}.
 */
@RestController
@RequestMapping("/api")
public class MLeagueEffectResource {

    private final Logger log = LoggerFactory.getLogger(MLeagueEffectResource.class);

    private static final String ENTITY_NAME = "mLeagueEffect";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MLeagueEffectService mLeagueEffectService;

    private final MLeagueEffectQueryService mLeagueEffectQueryService;

    public MLeagueEffectResource(MLeagueEffectService mLeagueEffectService, MLeagueEffectQueryService mLeagueEffectQueryService) {
        this.mLeagueEffectService = mLeagueEffectService;
        this.mLeagueEffectQueryService = mLeagueEffectQueryService;
    }

    /**
     * {@code POST  /m-league-effects} : Create a new mLeagueEffect.
     *
     * @param mLeagueEffectDTO the mLeagueEffectDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mLeagueEffectDTO, or with status {@code 400 (Bad Request)} if the mLeagueEffect has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-league-effects")
    public ResponseEntity<MLeagueEffectDTO> createMLeagueEffect(@Valid @RequestBody MLeagueEffectDTO mLeagueEffectDTO) throws URISyntaxException {
        log.debug("REST request to save MLeagueEffect : {}", mLeagueEffectDTO);
        if (mLeagueEffectDTO.getId() != null) {
            throw new BadRequestAlertException("A new mLeagueEffect cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MLeagueEffectDTO result = mLeagueEffectService.save(mLeagueEffectDTO);
        return ResponseEntity.created(new URI("/api/m-league-effects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-league-effects} : Updates an existing mLeagueEffect.
     *
     * @param mLeagueEffectDTO the mLeagueEffectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mLeagueEffectDTO,
     * or with status {@code 400 (Bad Request)} if the mLeagueEffectDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mLeagueEffectDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-league-effects")
    public ResponseEntity<MLeagueEffectDTO> updateMLeagueEffect(@Valid @RequestBody MLeagueEffectDTO mLeagueEffectDTO) throws URISyntaxException {
        log.debug("REST request to update MLeagueEffect : {}", mLeagueEffectDTO);
        if (mLeagueEffectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MLeagueEffectDTO result = mLeagueEffectService.save(mLeagueEffectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mLeagueEffectDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-league-effects} : get all the mLeagueEffects.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mLeagueEffects in body.
     */
    @GetMapping("/m-league-effects")
    public ResponseEntity<List<MLeagueEffectDTO>> getAllMLeagueEffects(MLeagueEffectCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MLeagueEffects by criteria: {}", criteria);
        Page<MLeagueEffectDTO> page = mLeagueEffectQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-league-effects/count} : count all the mLeagueEffects.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-league-effects/count")
    public ResponseEntity<Long> countMLeagueEffects(MLeagueEffectCriteria criteria) {
        log.debug("REST request to count MLeagueEffects by criteria: {}", criteria);
        return ResponseEntity.ok().body(mLeagueEffectQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-league-effects/:id} : get the "id" mLeagueEffect.
     *
     * @param id the id of the mLeagueEffectDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mLeagueEffectDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-league-effects/{id}")
    public ResponseEntity<MLeagueEffectDTO> getMLeagueEffect(@PathVariable Long id) {
        log.debug("REST request to get MLeagueEffect : {}", id);
        Optional<MLeagueEffectDTO> mLeagueEffectDTO = mLeagueEffectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mLeagueEffectDTO);
    }

    /**
     * {@code DELETE  /m-league-effects/:id} : delete the "id" mLeagueEffect.
     *
     * @param id the id of the mLeagueEffectDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-league-effects/{id}")
    public ResponseEntity<Void> deleteMLeagueEffect(@PathVariable Long id) {
        log.debug("REST request to delete MLeagueEffect : {}", id);
        mLeagueEffectService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
