package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCutService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCutDTO;
import io.shm.tsubasa.service.dto.MCutCriteria;
import io.shm.tsubasa.service.MCutQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCut}.
 */
@RestController
@RequestMapping("/api")
public class MCutResource {

    private final Logger log = LoggerFactory.getLogger(MCutResource.class);

    private static final String ENTITY_NAME = "mCut";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCutService mCutService;

    private final MCutQueryService mCutQueryService;

    public MCutResource(MCutService mCutService, MCutQueryService mCutQueryService) {
        this.mCutService = mCutService;
        this.mCutQueryService = mCutQueryService;
    }

    /**
     * {@code POST  /m-cuts} : Create a new mCut.
     *
     * @param mCutDTO the mCutDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCutDTO, or with status {@code 400 (Bad Request)} if the mCut has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-cuts")
    public ResponseEntity<MCutDTO> createMCut(@Valid @RequestBody MCutDTO mCutDTO) throws URISyntaxException {
        log.debug("REST request to save MCut : {}", mCutDTO);
        if (mCutDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCut cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCutDTO result = mCutService.save(mCutDTO);
        return ResponseEntity.created(new URI("/api/m-cuts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-cuts} : Updates an existing mCut.
     *
     * @param mCutDTO the mCutDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCutDTO,
     * or with status {@code 400 (Bad Request)} if the mCutDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCutDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-cuts")
    public ResponseEntity<MCutDTO> updateMCut(@Valid @RequestBody MCutDTO mCutDTO) throws URISyntaxException {
        log.debug("REST request to update MCut : {}", mCutDTO);
        if (mCutDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCutDTO result = mCutService.save(mCutDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCutDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-cuts} : get all the mCuts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCuts in body.
     */
    @GetMapping("/m-cuts")
    public ResponseEntity<List<MCutDTO>> getAllMCuts(MCutCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCuts by criteria: {}", criteria);
        Page<MCutDTO> page = mCutQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-cuts/count} : count all the mCuts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-cuts/count")
    public ResponseEntity<Long> countMCuts(MCutCriteria criteria) {
        log.debug("REST request to count MCuts by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCutQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-cuts/:id} : get the "id" mCut.
     *
     * @param id the id of the mCutDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCutDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-cuts/{id}")
    public ResponseEntity<MCutDTO> getMCut(@PathVariable Long id) {
        log.debug("REST request to get MCut : {}", id);
        Optional<MCutDTO> mCutDTO = mCutService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCutDTO);
    }

    /**
     * {@code DELETE  /m-cuts/:id} : delete the "id" mCut.
     *
     * @param id the id of the mCutDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-cuts/{id}")
    public ResponseEntity<Void> deleteMCut(@PathVariable Long id) {
        log.debug("REST request to delete MCut : {}", id);
        mCutService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
