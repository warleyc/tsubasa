package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDefineService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDefineDTO;
import io.shm.tsubasa.service.dto.MDefineCriteria;
import io.shm.tsubasa.service.MDefineQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDefine}.
 */
@RestController
@RequestMapping("/api")
public class MDefineResource {

    private final Logger log = LoggerFactory.getLogger(MDefineResource.class);

    private static final String ENTITY_NAME = "mDefine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDefineService mDefineService;

    private final MDefineQueryService mDefineQueryService;

    public MDefineResource(MDefineService mDefineService, MDefineQueryService mDefineQueryService) {
        this.mDefineService = mDefineService;
        this.mDefineQueryService = mDefineQueryService;
    }

    /**
     * {@code POST  /m-defines} : Create a new mDefine.
     *
     * @param mDefineDTO the mDefineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDefineDTO, or with status {@code 400 (Bad Request)} if the mDefine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-defines")
    public ResponseEntity<MDefineDTO> createMDefine(@Valid @RequestBody MDefineDTO mDefineDTO) throws URISyntaxException {
        log.debug("REST request to save MDefine : {}", mDefineDTO);
        if (mDefineDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDefine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDefineDTO result = mDefineService.save(mDefineDTO);
        return ResponseEntity.created(new URI("/api/m-defines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-defines} : Updates an existing mDefine.
     *
     * @param mDefineDTO the mDefineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDefineDTO,
     * or with status {@code 400 (Bad Request)} if the mDefineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDefineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-defines")
    public ResponseEntity<MDefineDTO> updateMDefine(@Valid @RequestBody MDefineDTO mDefineDTO) throws URISyntaxException {
        log.debug("REST request to update MDefine : {}", mDefineDTO);
        if (mDefineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDefineDTO result = mDefineService.save(mDefineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDefineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-defines} : get all the mDefines.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDefines in body.
     */
    @GetMapping("/m-defines")
    public ResponseEntity<List<MDefineDTO>> getAllMDefines(MDefineCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDefines by criteria: {}", criteria);
        Page<MDefineDTO> page = mDefineQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-defines/count} : count all the mDefines.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-defines/count")
    public ResponseEntity<Long> countMDefines(MDefineCriteria criteria) {
        log.debug("REST request to count MDefines by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDefineQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-defines/:id} : get the "id" mDefine.
     *
     * @param id the id of the mDefineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDefineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-defines/{id}")
    public ResponseEntity<MDefineDTO> getMDefine(@PathVariable Long id) {
        log.debug("REST request to get MDefine : {}", id);
        Optional<MDefineDTO> mDefineDTO = mDefineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDefineDTO);
    }

    /**
     * {@code DELETE  /m-defines/:id} : delete the "id" mDefine.
     *
     * @param id the id of the mDefineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-defines/{id}")
    public ResponseEntity<Void> deleteMDefine(@PathVariable Long id) {
        log.debug("REST request to delete MDefine : {}", id);
        mDefineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
