package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCutSeqGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCutSeqGroupDTO;
import io.shm.tsubasa.service.dto.MCutSeqGroupCriteria;
import io.shm.tsubasa.service.MCutSeqGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCutSeqGroup}.
 */
@RestController
@RequestMapping("/api")
public class MCutSeqGroupResource {

    private final Logger log = LoggerFactory.getLogger(MCutSeqGroupResource.class);

    private static final String ENTITY_NAME = "mCutSeqGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCutSeqGroupService mCutSeqGroupService;

    private final MCutSeqGroupQueryService mCutSeqGroupQueryService;

    public MCutSeqGroupResource(MCutSeqGroupService mCutSeqGroupService, MCutSeqGroupQueryService mCutSeqGroupQueryService) {
        this.mCutSeqGroupService = mCutSeqGroupService;
        this.mCutSeqGroupQueryService = mCutSeqGroupQueryService;
    }

    /**
     * {@code POST  /m-cut-seq-groups} : Create a new mCutSeqGroup.
     *
     * @param mCutSeqGroupDTO the mCutSeqGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCutSeqGroupDTO, or with status {@code 400 (Bad Request)} if the mCutSeqGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-cut-seq-groups")
    public ResponseEntity<MCutSeqGroupDTO> createMCutSeqGroup(@Valid @RequestBody MCutSeqGroupDTO mCutSeqGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MCutSeqGroup : {}", mCutSeqGroupDTO);
        if (mCutSeqGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCutSeqGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCutSeqGroupDTO result = mCutSeqGroupService.save(mCutSeqGroupDTO);
        return ResponseEntity.created(new URI("/api/m-cut-seq-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-cut-seq-groups} : Updates an existing mCutSeqGroup.
     *
     * @param mCutSeqGroupDTO the mCutSeqGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCutSeqGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mCutSeqGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCutSeqGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-cut-seq-groups")
    public ResponseEntity<MCutSeqGroupDTO> updateMCutSeqGroup(@Valid @RequestBody MCutSeqGroupDTO mCutSeqGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MCutSeqGroup : {}", mCutSeqGroupDTO);
        if (mCutSeqGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCutSeqGroupDTO result = mCutSeqGroupService.save(mCutSeqGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCutSeqGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-cut-seq-groups} : get all the mCutSeqGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCutSeqGroups in body.
     */
    @GetMapping("/m-cut-seq-groups")
    public ResponseEntity<List<MCutSeqGroupDTO>> getAllMCutSeqGroups(MCutSeqGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCutSeqGroups by criteria: {}", criteria);
        Page<MCutSeqGroupDTO> page = mCutSeqGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-cut-seq-groups/count} : count all the mCutSeqGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-cut-seq-groups/count")
    public ResponseEntity<Long> countMCutSeqGroups(MCutSeqGroupCriteria criteria) {
        log.debug("REST request to count MCutSeqGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCutSeqGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-cut-seq-groups/:id} : get the "id" mCutSeqGroup.
     *
     * @param id the id of the mCutSeqGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCutSeqGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-cut-seq-groups/{id}")
    public ResponseEntity<MCutSeqGroupDTO> getMCutSeqGroup(@PathVariable Long id) {
        log.debug("REST request to get MCutSeqGroup : {}", id);
        Optional<MCutSeqGroupDTO> mCutSeqGroupDTO = mCutSeqGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCutSeqGroupDTO);
    }

    /**
     * {@code DELETE  /m-cut-seq-groups/:id} : delete the "id" mCutSeqGroup.
     *
     * @param id the id of the mCutSeqGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-cut-seq-groups/{id}")
    public ResponseEntity<Void> deleteMCutSeqGroup(@PathVariable Long id) {
        log.debug("REST request to delete MCutSeqGroup : {}", id);
        mCutSeqGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
