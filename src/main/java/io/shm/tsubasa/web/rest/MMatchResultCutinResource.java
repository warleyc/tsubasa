package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMatchResultCutinService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMatchResultCutinDTO;
import io.shm.tsubasa.service.dto.MMatchResultCutinCriteria;
import io.shm.tsubasa.service.MMatchResultCutinQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMatchResultCutin}.
 */
@RestController
@RequestMapping("/api")
public class MMatchResultCutinResource {

    private final Logger log = LoggerFactory.getLogger(MMatchResultCutinResource.class);

    private static final String ENTITY_NAME = "mMatchResultCutin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMatchResultCutinService mMatchResultCutinService;

    private final MMatchResultCutinQueryService mMatchResultCutinQueryService;

    public MMatchResultCutinResource(MMatchResultCutinService mMatchResultCutinService, MMatchResultCutinQueryService mMatchResultCutinQueryService) {
        this.mMatchResultCutinService = mMatchResultCutinService;
        this.mMatchResultCutinQueryService = mMatchResultCutinQueryService;
    }

    /**
     * {@code POST  /m-match-result-cutins} : Create a new mMatchResultCutin.
     *
     * @param mMatchResultCutinDTO the mMatchResultCutinDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMatchResultCutinDTO, or with status {@code 400 (Bad Request)} if the mMatchResultCutin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-match-result-cutins")
    public ResponseEntity<MMatchResultCutinDTO> createMMatchResultCutin(@Valid @RequestBody MMatchResultCutinDTO mMatchResultCutinDTO) throws URISyntaxException {
        log.debug("REST request to save MMatchResultCutin : {}", mMatchResultCutinDTO);
        if (mMatchResultCutinDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMatchResultCutin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMatchResultCutinDTO result = mMatchResultCutinService.save(mMatchResultCutinDTO);
        return ResponseEntity.created(new URI("/api/m-match-result-cutins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-match-result-cutins} : Updates an existing mMatchResultCutin.
     *
     * @param mMatchResultCutinDTO the mMatchResultCutinDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMatchResultCutinDTO,
     * or with status {@code 400 (Bad Request)} if the mMatchResultCutinDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMatchResultCutinDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-match-result-cutins")
    public ResponseEntity<MMatchResultCutinDTO> updateMMatchResultCutin(@Valid @RequestBody MMatchResultCutinDTO mMatchResultCutinDTO) throws URISyntaxException {
        log.debug("REST request to update MMatchResultCutin : {}", mMatchResultCutinDTO);
        if (mMatchResultCutinDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMatchResultCutinDTO result = mMatchResultCutinService.save(mMatchResultCutinDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMatchResultCutinDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-match-result-cutins} : get all the mMatchResultCutins.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMatchResultCutins in body.
     */
    @GetMapping("/m-match-result-cutins")
    public ResponseEntity<List<MMatchResultCutinDTO>> getAllMMatchResultCutins(MMatchResultCutinCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMatchResultCutins by criteria: {}", criteria);
        Page<MMatchResultCutinDTO> page = mMatchResultCutinQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-match-result-cutins/count} : count all the mMatchResultCutins.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-match-result-cutins/count")
    public ResponseEntity<Long> countMMatchResultCutins(MMatchResultCutinCriteria criteria) {
        log.debug("REST request to count MMatchResultCutins by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMatchResultCutinQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-match-result-cutins/:id} : get the "id" mMatchResultCutin.
     *
     * @param id the id of the mMatchResultCutinDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMatchResultCutinDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-match-result-cutins/{id}")
    public ResponseEntity<MMatchResultCutinDTO> getMMatchResultCutin(@PathVariable Long id) {
        log.debug("REST request to get MMatchResultCutin : {}", id);
        Optional<MMatchResultCutinDTO> mMatchResultCutinDTO = mMatchResultCutinService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMatchResultCutinDTO);
    }

    /**
     * {@code DELETE  /m-match-result-cutins/:id} : delete the "id" mMatchResultCutin.
     *
     * @param id the id of the mMatchResultCutinDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-match-result-cutins/{id}")
    public ResponseEntity<Void> deleteMMatchResultCutin(@PathVariable Long id) {
        log.debug("REST request to delete MMatchResultCutin : {}", id);
        mMatchResultCutinService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
