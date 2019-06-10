package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MEmblemSetService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MEmblemSetDTO;
import io.shm.tsubasa.service.dto.MEmblemSetCriteria;
import io.shm.tsubasa.service.MEmblemSetQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MEmblemSet}.
 */
@RestController
@RequestMapping("/api")
public class MEmblemSetResource {

    private final Logger log = LoggerFactory.getLogger(MEmblemSetResource.class);

    private static final String ENTITY_NAME = "mEmblemSet";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MEmblemSetService mEmblemSetService;

    private final MEmblemSetQueryService mEmblemSetQueryService;

    public MEmblemSetResource(MEmblemSetService mEmblemSetService, MEmblemSetQueryService mEmblemSetQueryService) {
        this.mEmblemSetService = mEmblemSetService;
        this.mEmblemSetQueryService = mEmblemSetQueryService;
    }

    /**
     * {@code POST  /m-emblem-sets} : Create a new mEmblemSet.
     *
     * @param mEmblemSetDTO the mEmblemSetDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mEmblemSetDTO, or with status {@code 400 (Bad Request)} if the mEmblemSet has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-emblem-sets")
    public ResponseEntity<MEmblemSetDTO> createMEmblemSet(@Valid @RequestBody MEmblemSetDTO mEmblemSetDTO) throws URISyntaxException {
        log.debug("REST request to save MEmblemSet : {}", mEmblemSetDTO);
        if (mEmblemSetDTO.getId() != null) {
            throw new BadRequestAlertException("A new mEmblemSet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MEmblemSetDTO result = mEmblemSetService.save(mEmblemSetDTO);
        return ResponseEntity.created(new URI("/api/m-emblem-sets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-emblem-sets} : Updates an existing mEmblemSet.
     *
     * @param mEmblemSetDTO the mEmblemSetDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mEmblemSetDTO,
     * or with status {@code 400 (Bad Request)} if the mEmblemSetDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mEmblemSetDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-emblem-sets")
    public ResponseEntity<MEmblemSetDTO> updateMEmblemSet(@Valid @RequestBody MEmblemSetDTO mEmblemSetDTO) throws URISyntaxException {
        log.debug("REST request to update MEmblemSet : {}", mEmblemSetDTO);
        if (mEmblemSetDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MEmblemSetDTO result = mEmblemSetService.save(mEmblemSetDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mEmblemSetDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-emblem-sets} : get all the mEmblemSets.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mEmblemSets in body.
     */
    @GetMapping("/m-emblem-sets")
    public ResponseEntity<List<MEmblemSetDTO>> getAllMEmblemSets(MEmblemSetCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MEmblemSets by criteria: {}", criteria);
        Page<MEmblemSetDTO> page = mEmblemSetQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-emblem-sets/count} : count all the mEmblemSets.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-emblem-sets/count")
    public ResponseEntity<Long> countMEmblemSets(MEmblemSetCriteria criteria) {
        log.debug("REST request to count MEmblemSets by criteria: {}", criteria);
        return ResponseEntity.ok().body(mEmblemSetQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-emblem-sets/:id} : get the "id" mEmblemSet.
     *
     * @param id the id of the mEmblemSetDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mEmblemSetDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-emblem-sets/{id}")
    public ResponseEntity<MEmblemSetDTO> getMEmblemSet(@PathVariable Long id) {
        log.debug("REST request to get MEmblemSet : {}", id);
        Optional<MEmblemSetDTO> mEmblemSetDTO = mEmblemSetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mEmblemSetDTO);
    }

    /**
     * {@code DELETE  /m-emblem-sets/:id} : delete the "id" mEmblemSet.
     *
     * @param id the id of the mEmblemSetDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-emblem-sets/{id}")
    public ResponseEntity<Void> deleteMEmblemSet(@PathVariable Long id) {
        log.debug("REST request to delete MEmblemSet : {}", id);
        mEmblemSetService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
