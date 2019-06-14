package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MPenaltyKickCutService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MPenaltyKickCutDTO;
import io.shm.tsubasa.service.dto.MPenaltyKickCutCriteria;
import io.shm.tsubasa.service.MPenaltyKickCutQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MPenaltyKickCut}.
 */
@RestController
@RequestMapping("/api")
public class MPenaltyKickCutResource {

    private final Logger log = LoggerFactory.getLogger(MPenaltyKickCutResource.class);

    private static final String ENTITY_NAME = "mPenaltyKickCut";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MPenaltyKickCutService mPenaltyKickCutService;

    private final MPenaltyKickCutQueryService mPenaltyKickCutQueryService;

    public MPenaltyKickCutResource(MPenaltyKickCutService mPenaltyKickCutService, MPenaltyKickCutQueryService mPenaltyKickCutQueryService) {
        this.mPenaltyKickCutService = mPenaltyKickCutService;
        this.mPenaltyKickCutQueryService = mPenaltyKickCutQueryService;
    }

    /**
     * {@code POST  /m-penalty-kick-cuts} : Create a new mPenaltyKickCut.
     *
     * @param mPenaltyKickCutDTO the mPenaltyKickCutDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mPenaltyKickCutDTO, or with status {@code 400 (Bad Request)} if the mPenaltyKickCut has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-penalty-kick-cuts")
    public ResponseEntity<MPenaltyKickCutDTO> createMPenaltyKickCut(@Valid @RequestBody MPenaltyKickCutDTO mPenaltyKickCutDTO) throws URISyntaxException {
        log.debug("REST request to save MPenaltyKickCut : {}", mPenaltyKickCutDTO);
        if (mPenaltyKickCutDTO.getId() != null) {
            throw new BadRequestAlertException("A new mPenaltyKickCut cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MPenaltyKickCutDTO result = mPenaltyKickCutService.save(mPenaltyKickCutDTO);
        return ResponseEntity.created(new URI("/api/m-penalty-kick-cuts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-penalty-kick-cuts} : Updates an existing mPenaltyKickCut.
     *
     * @param mPenaltyKickCutDTO the mPenaltyKickCutDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mPenaltyKickCutDTO,
     * or with status {@code 400 (Bad Request)} if the mPenaltyKickCutDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mPenaltyKickCutDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-penalty-kick-cuts")
    public ResponseEntity<MPenaltyKickCutDTO> updateMPenaltyKickCut(@Valid @RequestBody MPenaltyKickCutDTO mPenaltyKickCutDTO) throws URISyntaxException {
        log.debug("REST request to update MPenaltyKickCut : {}", mPenaltyKickCutDTO);
        if (mPenaltyKickCutDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MPenaltyKickCutDTO result = mPenaltyKickCutService.save(mPenaltyKickCutDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mPenaltyKickCutDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-penalty-kick-cuts} : get all the mPenaltyKickCuts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mPenaltyKickCuts in body.
     */
    @GetMapping("/m-penalty-kick-cuts")
    public ResponseEntity<List<MPenaltyKickCutDTO>> getAllMPenaltyKickCuts(MPenaltyKickCutCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MPenaltyKickCuts by criteria: {}", criteria);
        Page<MPenaltyKickCutDTO> page = mPenaltyKickCutQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-penalty-kick-cuts/count} : count all the mPenaltyKickCuts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-penalty-kick-cuts/count")
    public ResponseEntity<Long> countMPenaltyKickCuts(MPenaltyKickCutCriteria criteria) {
        log.debug("REST request to count MPenaltyKickCuts by criteria: {}", criteria);
        return ResponseEntity.ok().body(mPenaltyKickCutQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-penalty-kick-cuts/:id} : get the "id" mPenaltyKickCut.
     *
     * @param id the id of the mPenaltyKickCutDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mPenaltyKickCutDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-penalty-kick-cuts/{id}")
    public ResponseEntity<MPenaltyKickCutDTO> getMPenaltyKickCut(@PathVariable Long id) {
        log.debug("REST request to get MPenaltyKickCut : {}", id);
        Optional<MPenaltyKickCutDTO> mPenaltyKickCutDTO = mPenaltyKickCutService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mPenaltyKickCutDTO);
    }

    /**
     * {@code DELETE  /m-penalty-kick-cuts/:id} : delete the "id" mPenaltyKickCut.
     *
     * @param id the id of the mPenaltyKickCutDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-penalty-kick-cuts/{id}")
    public ResponseEntity<Void> deleteMPenaltyKickCut(@PathVariable Long id) {
        log.debug("REST request to delete MPenaltyKickCut : {}", id);
        mPenaltyKickCutService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
