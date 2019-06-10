package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCharacterScoreCutService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCharacterScoreCutDTO;
import io.shm.tsubasa.service.dto.MCharacterScoreCutCriteria;
import io.shm.tsubasa.service.MCharacterScoreCutQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCharacterScoreCut}.
 */
@RestController
@RequestMapping("/api")
public class MCharacterScoreCutResource {

    private final Logger log = LoggerFactory.getLogger(MCharacterScoreCutResource.class);

    private static final String ENTITY_NAME = "mCharacterScoreCut";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCharacterScoreCutService mCharacterScoreCutService;

    private final MCharacterScoreCutQueryService mCharacterScoreCutQueryService;

    public MCharacterScoreCutResource(MCharacterScoreCutService mCharacterScoreCutService, MCharacterScoreCutQueryService mCharacterScoreCutQueryService) {
        this.mCharacterScoreCutService = mCharacterScoreCutService;
        this.mCharacterScoreCutQueryService = mCharacterScoreCutQueryService;
    }

    /**
     * {@code POST  /m-character-score-cuts} : Create a new mCharacterScoreCut.
     *
     * @param mCharacterScoreCutDTO the mCharacterScoreCutDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCharacterScoreCutDTO, or with status {@code 400 (Bad Request)} if the mCharacterScoreCut has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-character-score-cuts")
    public ResponseEntity<MCharacterScoreCutDTO> createMCharacterScoreCut(@Valid @RequestBody MCharacterScoreCutDTO mCharacterScoreCutDTO) throws URISyntaxException {
        log.debug("REST request to save MCharacterScoreCut : {}", mCharacterScoreCutDTO);
        if (mCharacterScoreCutDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCharacterScoreCut cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCharacterScoreCutDTO result = mCharacterScoreCutService.save(mCharacterScoreCutDTO);
        return ResponseEntity.created(new URI("/api/m-character-score-cuts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-character-score-cuts} : Updates an existing mCharacterScoreCut.
     *
     * @param mCharacterScoreCutDTO the mCharacterScoreCutDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCharacterScoreCutDTO,
     * or with status {@code 400 (Bad Request)} if the mCharacterScoreCutDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCharacterScoreCutDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-character-score-cuts")
    public ResponseEntity<MCharacterScoreCutDTO> updateMCharacterScoreCut(@Valid @RequestBody MCharacterScoreCutDTO mCharacterScoreCutDTO) throws URISyntaxException {
        log.debug("REST request to update MCharacterScoreCut : {}", mCharacterScoreCutDTO);
        if (mCharacterScoreCutDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCharacterScoreCutDTO result = mCharacterScoreCutService.save(mCharacterScoreCutDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCharacterScoreCutDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-character-score-cuts} : get all the mCharacterScoreCuts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCharacterScoreCuts in body.
     */
    @GetMapping("/m-character-score-cuts")
    public ResponseEntity<List<MCharacterScoreCutDTO>> getAllMCharacterScoreCuts(MCharacterScoreCutCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCharacterScoreCuts by criteria: {}", criteria);
        Page<MCharacterScoreCutDTO> page = mCharacterScoreCutQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-character-score-cuts/count} : count all the mCharacterScoreCuts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-character-score-cuts/count")
    public ResponseEntity<Long> countMCharacterScoreCuts(MCharacterScoreCutCriteria criteria) {
        log.debug("REST request to count MCharacterScoreCuts by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCharacterScoreCutQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-character-score-cuts/:id} : get the "id" mCharacterScoreCut.
     *
     * @param id the id of the mCharacterScoreCutDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCharacterScoreCutDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-character-score-cuts/{id}")
    public ResponseEntity<MCharacterScoreCutDTO> getMCharacterScoreCut(@PathVariable Long id) {
        log.debug("REST request to get MCharacterScoreCut : {}", id);
        Optional<MCharacterScoreCutDTO> mCharacterScoreCutDTO = mCharacterScoreCutService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCharacterScoreCutDTO);
    }

    /**
     * {@code DELETE  /m-character-score-cuts/:id} : delete the "id" mCharacterScoreCut.
     *
     * @param id the id of the mCharacterScoreCutDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-character-score-cuts/{id}")
    public ResponseEntity<Void> deleteMCharacterScoreCut(@PathVariable Long id) {
        log.debug("REST request to delete MCharacterScoreCut : {}", id);
        mCharacterScoreCutService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
