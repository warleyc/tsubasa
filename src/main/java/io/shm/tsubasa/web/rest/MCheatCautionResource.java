package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCheatCautionService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCheatCautionDTO;
import io.shm.tsubasa.service.dto.MCheatCautionCriteria;
import io.shm.tsubasa.service.MCheatCautionQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCheatCaution}.
 */
@RestController
@RequestMapping("/api")
public class MCheatCautionResource {

    private final Logger log = LoggerFactory.getLogger(MCheatCautionResource.class);

    private static final String ENTITY_NAME = "mCheatCaution";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCheatCautionService mCheatCautionService;

    private final MCheatCautionQueryService mCheatCautionQueryService;

    public MCheatCautionResource(MCheatCautionService mCheatCautionService, MCheatCautionQueryService mCheatCautionQueryService) {
        this.mCheatCautionService = mCheatCautionService;
        this.mCheatCautionQueryService = mCheatCautionQueryService;
    }

    /**
     * {@code POST  /m-cheat-cautions} : Create a new mCheatCaution.
     *
     * @param mCheatCautionDTO the mCheatCautionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCheatCautionDTO, or with status {@code 400 (Bad Request)} if the mCheatCaution has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-cheat-cautions")
    public ResponseEntity<MCheatCautionDTO> createMCheatCaution(@Valid @RequestBody MCheatCautionDTO mCheatCautionDTO) throws URISyntaxException {
        log.debug("REST request to save MCheatCaution : {}", mCheatCautionDTO);
        if (mCheatCautionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCheatCaution cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCheatCautionDTO result = mCheatCautionService.save(mCheatCautionDTO);
        return ResponseEntity.created(new URI("/api/m-cheat-cautions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-cheat-cautions} : Updates an existing mCheatCaution.
     *
     * @param mCheatCautionDTO the mCheatCautionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCheatCautionDTO,
     * or with status {@code 400 (Bad Request)} if the mCheatCautionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCheatCautionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-cheat-cautions")
    public ResponseEntity<MCheatCautionDTO> updateMCheatCaution(@Valid @RequestBody MCheatCautionDTO mCheatCautionDTO) throws URISyntaxException {
        log.debug("REST request to update MCheatCaution : {}", mCheatCautionDTO);
        if (mCheatCautionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCheatCautionDTO result = mCheatCautionService.save(mCheatCautionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCheatCautionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-cheat-cautions} : get all the mCheatCautions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCheatCautions in body.
     */
    @GetMapping("/m-cheat-cautions")
    public ResponseEntity<List<MCheatCautionDTO>> getAllMCheatCautions(MCheatCautionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCheatCautions by criteria: {}", criteria);
        Page<MCheatCautionDTO> page = mCheatCautionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-cheat-cautions/count} : count all the mCheatCautions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-cheat-cautions/count")
    public ResponseEntity<Long> countMCheatCautions(MCheatCautionCriteria criteria) {
        log.debug("REST request to count MCheatCautions by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCheatCautionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-cheat-cautions/:id} : get the "id" mCheatCaution.
     *
     * @param id the id of the mCheatCautionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCheatCautionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-cheat-cautions/{id}")
    public ResponseEntity<MCheatCautionDTO> getMCheatCaution(@PathVariable Long id) {
        log.debug("REST request to get MCheatCaution : {}", id);
        Optional<MCheatCautionDTO> mCheatCautionDTO = mCheatCautionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCheatCautionDTO);
    }

    /**
     * {@code DELETE  /m-cheat-cautions/:id} : delete the "id" mCheatCaution.
     *
     * @param id the id of the mCheatCautionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-cheat-cautions/{id}")
    public ResponseEntity<Void> deleteMCheatCaution(@PathVariable Long id) {
        log.debug("REST request to delete MCheatCaution : {}", id);
        mCheatCautionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
