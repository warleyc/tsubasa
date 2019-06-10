package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MEncountersBonusService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MEncountersBonusDTO;
import io.shm.tsubasa.service.dto.MEncountersBonusCriteria;
import io.shm.tsubasa.service.MEncountersBonusQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MEncountersBonus}.
 */
@RestController
@RequestMapping("/api")
public class MEncountersBonusResource {

    private final Logger log = LoggerFactory.getLogger(MEncountersBonusResource.class);

    private static final String ENTITY_NAME = "mEncountersBonus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MEncountersBonusService mEncountersBonusService;

    private final MEncountersBonusQueryService mEncountersBonusQueryService;

    public MEncountersBonusResource(MEncountersBonusService mEncountersBonusService, MEncountersBonusQueryService mEncountersBonusQueryService) {
        this.mEncountersBonusService = mEncountersBonusService;
        this.mEncountersBonusQueryService = mEncountersBonusQueryService;
    }

    /**
     * {@code POST  /m-encounters-bonuses} : Create a new mEncountersBonus.
     *
     * @param mEncountersBonusDTO the mEncountersBonusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mEncountersBonusDTO, or with status {@code 400 (Bad Request)} if the mEncountersBonus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-encounters-bonuses")
    public ResponseEntity<MEncountersBonusDTO> createMEncountersBonus(@Valid @RequestBody MEncountersBonusDTO mEncountersBonusDTO) throws URISyntaxException {
        log.debug("REST request to save MEncountersBonus : {}", mEncountersBonusDTO);
        if (mEncountersBonusDTO.getId() != null) {
            throw new BadRequestAlertException("A new mEncountersBonus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MEncountersBonusDTO result = mEncountersBonusService.save(mEncountersBonusDTO);
        return ResponseEntity.created(new URI("/api/m-encounters-bonuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-encounters-bonuses} : Updates an existing mEncountersBonus.
     *
     * @param mEncountersBonusDTO the mEncountersBonusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mEncountersBonusDTO,
     * or with status {@code 400 (Bad Request)} if the mEncountersBonusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mEncountersBonusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-encounters-bonuses")
    public ResponseEntity<MEncountersBonusDTO> updateMEncountersBonus(@Valid @RequestBody MEncountersBonusDTO mEncountersBonusDTO) throws URISyntaxException {
        log.debug("REST request to update MEncountersBonus : {}", mEncountersBonusDTO);
        if (mEncountersBonusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MEncountersBonusDTO result = mEncountersBonusService.save(mEncountersBonusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mEncountersBonusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-encounters-bonuses} : get all the mEncountersBonuses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mEncountersBonuses in body.
     */
    @GetMapping("/m-encounters-bonuses")
    public ResponseEntity<List<MEncountersBonusDTO>> getAllMEncountersBonuses(MEncountersBonusCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MEncountersBonuses by criteria: {}", criteria);
        Page<MEncountersBonusDTO> page = mEncountersBonusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-encounters-bonuses/count} : count all the mEncountersBonuses.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-encounters-bonuses/count")
    public ResponseEntity<Long> countMEncountersBonuses(MEncountersBonusCriteria criteria) {
        log.debug("REST request to count MEncountersBonuses by criteria: {}", criteria);
        return ResponseEntity.ok().body(mEncountersBonusQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-encounters-bonuses/:id} : get the "id" mEncountersBonus.
     *
     * @param id the id of the mEncountersBonusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mEncountersBonusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-encounters-bonuses/{id}")
    public ResponseEntity<MEncountersBonusDTO> getMEncountersBonus(@PathVariable Long id) {
        log.debug("REST request to get MEncountersBonus : {}", id);
        Optional<MEncountersBonusDTO> mEncountersBonusDTO = mEncountersBonusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mEncountersBonusDTO);
    }

    /**
     * {@code DELETE  /m-encounters-bonuses/:id} : delete the "id" mEncountersBonus.
     *
     * @param id the id of the mEncountersBonusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-encounters-bonuses/{id}")
    public ResponseEntity<Void> deleteMEncountersBonus(@PathVariable Long id) {
        log.debug("REST request to delete MEncountersBonus : {}", id);
        mEncountersBonusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
