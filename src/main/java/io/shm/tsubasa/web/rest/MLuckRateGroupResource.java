package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MLuckRateGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MLuckRateGroupDTO;
import io.shm.tsubasa.service.dto.MLuckRateGroupCriteria;
import io.shm.tsubasa.service.MLuckRateGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MLuckRateGroup}.
 */
@RestController
@RequestMapping("/api")
public class MLuckRateGroupResource {

    private final Logger log = LoggerFactory.getLogger(MLuckRateGroupResource.class);

    private static final String ENTITY_NAME = "mLuckRateGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MLuckRateGroupService mLuckRateGroupService;

    private final MLuckRateGroupQueryService mLuckRateGroupQueryService;

    public MLuckRateGroupResource(MLuckRateGroupService mLuckRateGroupService, MLuckRateGroupQueryService mLuckRateGroupQueryService) {
        this.mLuckRateGroupService = mLuckRateGroupService;
        this.mLuckRateGroupQueryService = mLuckRateGroupQueryService;
    }

    /**
     * {@code POST  /m-luck-rate-groups} : Create a new mLuckRateGroup.
     *
     * @param mLuckRateGroupDTO the mLuckRateGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mLuckRateGroupDTO, or with status {@code 400 (Bad Request)} if the mLuckRateGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-luck-rate-groups")
    public ResponseEntity<MLuckRateGroupDTO> createMLuckRateGroup(@Valid @RequestBody MLuckRateGroupDTO mLuckRateGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MLuckRateGroup : {}", mLuckRateGroupDTO);
        if (mLuckRateGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mLuckRateGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MLuckRateGroupDTO result = mLuckRateGroupService.save(mLuckRateGroupDTO);
        return ResponseEntity.created(new URI("/api/m-luck-rate-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-luck-rate-groups} : Updates an existing mLuckRateGroup.
     *
     * @param mLuckRateGroupDTO the mLuckRateGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mLuckRateGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mLuckRateGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mLuckRateGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-luck-rate-groups")
    public ResponseEntity<MLuckRateGroupDTO> updateMLuckRateGroup(@Valid @RequestBody MLuckRateGroupDTO mLuckRateGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MLuckRateGroup : {}", mLuckRateGroupDTO);
        if (mLuckRateGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MLuckRateGroupDTO result = mLuckRateGroupService.save(mLuckRateGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mLuckRateGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-luck-rate-groups} : get all the mLuckRateGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mLuckRateGroups in body.
     */
    @GetMapping("/m-luck-rate-groups")
    public ResponseEntity<List<MLuckRateGroupDTO>> getAllMLuckRateGroups(MLuckRateGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MLuckRateGroups by criteria: {}", criteria);
        Page<MLuckRateGroupDTO> page = mLuckRateGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-luck-rate-groups/count} : count all the mLuckRateGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-luck-rate-groups/count")
    public ResponseEntity<Long> countMLuckRateGroups(MLuckRateGroupCriteria criteria) {
        log.debug("REST request to count MLuckRateGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mLuckRateGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-luck-rate-groups/:id} : get the "id" mLuckRateGroup.
     *
     * @param id the id of the mLuckRateGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mLuckRateGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-luck-rate-groups/{id}")
    public ResponseEntity<MLuckRateGroupDTO> getMLuckRateGroup(@PathVariable Long id) {
        log.debug("REST request to get MLuckRateGroup : {}", id);
        Optional<MLuckRateGroupDTO> mLuckRateGroupDTO = mLuckRateGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mLuckRateGroupDTO);
    }

    /**
     * {@code DELETE  /m-luck-rate-groups/:id} : delete the "id" mLuckRateGroup.
     *
     * @param id the id of the mLuckRateGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-luck-rate-groups/{id}")
    public ResponseEntity<Void> deleteMLuckRateGroup(@PathVariable Long id) {
        log.debug("REST request to delete MLuckRateGroup : {}", id);
        mLuckRateGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
