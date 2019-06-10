package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDictionaryZhService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDictionaryZhDTO;
import io.shm.tsubasa.service.dto.MDictionaryZhCriteria;
import io.shm.tsubasa.service.MDictionaryZhQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDictionaryZh}.
 */
@RestController
@RequestMapping("/api")
public class MDictionaryZhResource {

    private final Logger log = LoggerFactory.getLogger(MDictionaryZhResource.class);

    private static final String ENTITY_NAME = "mDictionaryZh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDictionaryZhService mDictionaryZhService;

    private final MDictionaryZhQueryService mDictionaryZhQueryService;

    public MDictionaryZhResource(MDictionaryZhService mDictionaryZhService, MDictionaryZhQueryService mDictionaryZhQueryService) {
        this.mDictionaryZhService = mDictionaryZhService;
        this.mDictionaryZhQueryService = mDictionaryZhQueryService;
    }

    /**
     * {@code POST  /m-dictionary-zhs} : Create a new mDictionaryZh.
     *
     * @param mDictionaryZhDTO the mDictionaryZhDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDictionaryZhDTO, or with status {@code 400 (Bad Request)} if the mDictionaryZh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-dictionary-zhs")
    public ResponseEntity<MDictionaryZhDTO> createMDictionaryZh(@Valid @RequestBody MDictionaryZhDTO mDictionaryZhDTO) throws URISyntaxException {
        log.debug("REST request to save MDictionaryZh : {}", mDictionaryZhDTO);
        if (mDictionaryZhDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDictionaryZh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDictionaryZhDTO result = mDictionaryZhService.save(mDictionaryZhDTO);
        return ResponseEntity.created(new URI("/api/m-dictionary-zhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-dictionary-zhs} : Updates an existing mDictionaryZh.
     *
     * @param mDictionaryZhDTO the mDictionaryZhDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDictionaryZhDTO,
     * or with status {@code 400 (Bad Request)} if the mDictionaryZhDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDictionaryZhDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-dictionary-zhs")
    public ResponseEntity<MDictionaryZhDTO> updateMDictionaryZh(@Valid @RequestBody MDictionaryZhDTO mDictionaryZhDTO) throws URISyntaxException {
        log.debug("REST request to update MDictionaryZh : {}", mDictionaryZhDTO);
        if (mDictionaryZhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDictionaryZhDTO result = mDictionaryZhService.save(mDictionaryZhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDictionaryZhDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-dictionary-zhs} : get all the mDictionaryZhs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDictionaryZhs in body.
     */
    @GetMapping("/m-dictionary-zhs")
    public ResponseEntity<List<MDictionaryZhDTO>> getAllMDictionaryZhs(MDictionaryZhCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDictionaryZhs by criteria: {}", criteria);
        Page<MDictionaryZhDTO> page = mDictionaryZhQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-dictionary-zhs/count} : count all the mDictionaryZhs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-dictionary-zhs/count")
    public ResponseEntity<Long> countMDictionaryZhs(MDictionaryZhCriteria criteria) {
        log.debug("REST request to count MDictionaryZhs by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDictionaryZhQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-dictionary-zhs/:id} : get the "id" mDictionaryZh.
     *
     * @param id the id of the mDictionaryZhDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDictionaryZhDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-dictionary-zhs/{id}")
    public ResponseEntity<MDictionaryZhDTO> getMDictionaryZh(@PathVariable Long id) {
        log.debug("REST request to get MDictionaryZh : {}", id);
        Optional<MDictionaryZhDTO> mDictionaryZhDTO = mDictionaryZhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDictionaryZhDTO);
    }

    /**
     * {@code DELETE  /m-dictionary-zhs/:id} : delete the "id" mDictionaryZh.
     *
     * @param id the id of the mDictionaryZhDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-dictionary-zhs/{id}")
    public ResponseEntity<Void> deleteMDictionaryZh(@PathVariable Long id) {
        log.debug("REST request to delete MDictionaryZh : {}", id);
        mDictionaryZhService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
