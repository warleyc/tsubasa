package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MUniformBottomService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MUniformBottomDTO;
import io.shm.tsubasa.service.dto.MUniformBottomCriteria;
import io.shm.tsubasa.service.MUniformBottomQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MUniformBottom}.
 */
@RestController
@RequestMapping("/api")
public class MUniformBottomResource {

    private final Logger log = LoggerFactory.getLogger(MUniformBottomResource.class);

    private static final String ENTITY_NAME = "mUniformBottom";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MUniformBottomService mUniformBottomService;

    private final MUniformBottomQueryService mUniformBottomQueryService;

    public MUniformBottomResource(MUniformBottomService mUniformBottomService, MUniformBottomQueryService mUniformBottomQueryService) {
        this.mUniformBottomService = mUniformBottomService;
        this.mUniformBottomQueryService = mUniformBottomQueryService;
    }

    /**
     * {@code POST  /m-uniform-bottoms} : Create a new mUniformBottom.
     *
     * @param mUniformBottomDTO the mUniformBottomDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mUniformBottomDTO, or with status {@code 400 (Bad Request)} if the mUniformBottom has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-uniform-bottoms")
    public ResponseEntity<MUniformBottomDTO> createMUniformBottom(@Valid @RequestBody MUniformBottomDTO mUniformBottomDTO) throws URISyntaxException {
        log.debug("REST request to save MUniformBottom : {}", mUniformBottomDTO);
        if (mUniformBottomDTO.getId() != null) {
            throw new BadRequestAlertException("A new mUniformBottom cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MUniformBottomDTO result = mUniformBottomService.save(mUniformBottomDTO);
        return ResponseEntity.created(new URI("/api/m-uniform-bottoms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-uniform-bottoms} : Updates an existing mUniformBottom.
     *
     * @param mUniformBottomDTO the mUniformBottomDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mUniformBottomDTO,
     * or with status {@code 400 (Bad Request)} if the mUniformBottomDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mUniformBottomDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-uniform-bottoms")
    public ResponseEntity<MUniformBottomDTO> updateMUniformBottom(@Valid @RequestBody MUniformBottomDTO mUniformBottomDTO) throws URISyntaxException {
        log.debug("REST request to update MUniformBottom : {}", mUniformBottomDTO);
        if (mUniformBottomDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MUniformBottomDTO result = mUniformBottomService.save(mUniformBottomDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mUniformBottomDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-uniform-bottoms} : get all the mUniformBottoms.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mUniformBottoms in body.
     */
    @GetMapping("/m-uniform-bottoms")
    public ResponseEntity<List<MUniformBottomDTO>> getAllMUniformBottoms(MUniformBottomCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MUniformBottoms by criteria: {}", criteria);
        Page<MUniformBottomDTO> page = mUniformBottomQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-uniform-bottoms/count} : count all the mUniformBottoms.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-uniform-bottoms/count")
    public ResponseEntity<Long> countMUniformBottoms(MUniformBottomCriteria criteria) {
        log.debug("REST request to count MUniformBottoms by criteria: {}", criteria);
        return ResponseEntity.ok().body(mUniformBottomQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-uniform-bottoms/:id} : get the "id" mUniformBottom.
     *
     * @param id the id of the mUniformBottomDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mUniformBottomDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-uniform-bottoms/{id}")
    public ResponseEntity<MUniformBottomDTO> getMUniformBottom(@PathVariable Long id) {
        log.debug("REST request to get MUniformBottom : {}", id);
        Optional<MUniformBottomDTO> mUniformBottomDTO = mUniformBottomService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mUniformBottomDTO);
    }

    /**
     * {@code DELETE  /m-uniform-bottoms/:id} : delete the "id" mUniformBottom.
     *
     * @param id the id of the mUniformBottomDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-uniform-bottoms/{id}")
    public ResponseEntity<Void> deleteMUniformBottom(@PathVariable Long id) {
        log.debug("REST request to delete MUniformBottom : {}", id);
        mUniformBottomService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
