package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MUniformOriginalSetService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MUniformOriginalSetDTO;
import io.shm.tsubasa.service.dto.MUniformOriginalSetCriteria;
import io.shm.tsubasa.service.MUniformOriginalSetQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MUniformOriginalSet}.
 */
@RestController
@RequestMapping("/api")
public class MUniformOriginalSetResource {

    private final Logger log = LoggerFactory.getLogger(MUniformOriginalSetResource.class);

    private static final String ENTITY_NAME = "mUniformOriginalSet";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MUniformOriginalSetService mUniformOriginalSetService;

    private final MUniformOriginalSetQueryService mUniformOriginalSetQueryService;

    public MUniformOriginalSetResource(MUniformOriginalSetService mUniformOriginalSetService, MUniformOriginalSetQueryService mUniformOriginalSetQueryService) {
        this.mUniformOriginalSetService = mUniformOriginalSetService;
        this.mUniformOriginalSetQueryService = mUniformOriginalSetQueryService;
    }

    /**
     * {@code POST  /m-uniform-original-sets} : Create a new mUniformOriginalSet.
     *
     * @param mUniformOriginalSetDTO the mUniformOriginalSetDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mUniformOriginalSetDTO, or with status {@code 400 (Bad Request)} if the mUniformOriginalSet has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-uniform-original-sets")
    public ResponseEntity<MUniformOriginalSetDTO> createMUniformOriginalSet(@Valid @RequestBody MUniformOriginalSetDTO mUniformOriginalSetDTO) throws URISyntaxException {
        log.debug("REST request to save MUniformOriginalSet : {}", mUniformOriginalSetDTO);
        if (mUniformOriginalSetDTO.getId() != null) {
            throw new BadRequestAlertException("A new mUniformOriginalSet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MUniformOriginalSetDTO result = mUniformOriginalSetService.save(mUniformOriginalSetDTO);
        return ResponseEntity.created(new URI("/api/m-uniform-original-sets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-uniform-original-sets} : Updates an existing mUniformOriginalSet.
     *
     * @param mUniformOriginalSetDTO the mUniformOriginalSetDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mUniformOriginalSetDTO,
     * or with status {@code 400 (Bad Request)} if the mUniformOriginalSetDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mUniformOriginalSetDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-uniform-original-sets")
    public ResponseEntity<MUniformOriginalSetDTO> updateMUniformOriginalSet(@Valid @RequestBody MUniformOriginalSetDTO mUniformOriginalSetDTO) throws URISyntaxException {
        log.debug("REST request to update MUniformOriginalSet : {}", mUniformOriginalSetDTO);
        if (mUniformOriginalSetDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MUniformOriginalSetDTO result = mUniformOriginalSetService.save(mUniformOriginalSetDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mUniformOriginalSetDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-uniform-original-sets} : get all the mUniformOriginalSets.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mUniformOriginalSets in body.
     */
    @GetMapping("/m-uniform-original-sets")
    public ResponseEntity<List<MUniformOriginalSetDTO>> getAllMUniformOriginalSets(MUniformOriginalSetCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MUniformOriginalSets by criteria: {}", criteria);
        Page<MUniformOriginalSetDTO> page = mUniformOriginalSetQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-uniform-original-sets/count} : count all the mUniformOriginalSets.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-uniform-original-sets/count")
    public ResponseEntity<Long> countMUniformOriginalSets(MUniformOriginalSetCriteria criteria) {
        log.debug("REST request to count MUniformOriginalSets by criteria: {}", criteria);
        return ResponseEntity.ok().body(mUniformOriginalSetQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-uniform-original-sets/:id} : get the "id" mUniformOriginalSet.
     *
     * @param id the id of the mUniformOriginalSetDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mUniformOriginalSetDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-uniform-original-sets/{id}")
    public ResponseEntity<MUniformOriginalSetDTO> getMUniformOriginalSet(@PathVariable Long id) {
        log.debug("REST request to get MUniformOriginalSet : {}", id);
        Optional<MUniformOriginalSetDTO> mUniformOriginalSetDTO = mUniformOriginalSetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mUniformOriginalSetDTO);
    }

    /**
     * {@code DELETE  /m-uniform-original-sets/:id} : delete the "id" mUniformOriginalSet.
     *
     * @param id the id of the mUniformOriginalSetDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-uniform-original-sets/{id}")
    public ResponseEntity<Void> deleteMUniformOriginalSet(@PathVariable Long id) {
        log.debug("REST request to delete MUniformOriginalSet : {}", id);
        mUniformOriginalSetService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
