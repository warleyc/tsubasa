package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDictionaryJaService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDictionaryJaDTO;
import io.shm.tsubasa.service.dto.MDictionaryJaCriteria;
import io.shm.tsubasa.service.MDictionaryJaQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDictionaryJa}.
 */
@RestController
@RequestMapping("/api")
public class MDictionaryJaResource {

    private final Logger log = LoggerFactory.getLogger(MDictionaryJaResource.class);

    private static final String ENTITY_NAME = "mDictionaryJa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDictionaryJaService mDictionaryJaService;

    private final MDictionaryJaQueryService mDictionaryJaQueryService;

    public MDictionaryJaResource(MDictionaryJaService mDictionaryJaService, MDictionaryJaQueryService mDictionaryJaQueryService) {
        this.mDictionaryJaService = mDictionaryJaService;
        this.mDictionaryJaQueryService = mDictionaryJaQueryService;
    }

    /**
     * {@code POST  /m-dictionary-jas} : Create a new mDictionaryJa.
     *
     * @param mDictionaryJaDTO the mDictionaryJaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDictionaryJaDTO, or with status {@code 400 (Bad Request)} if the mDictionaryJa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-dictionary-jas")
    public ResponseEntity<MDictionaryJaDTO> createMDictionaryJa(@Valid @RequestBody MDictionaryJaDTO mDictionaryJaDTO) throws URISyntaxException {
        log.debug("REST request to save MDictionaryJa : {}", mDictionaryJaDTO);
        if (mDictionaryJaDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDictionaryJa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDictionaryJaDTO result = mDictionaryJaService.save(mDictionaryJaDTO);
        return ResponseEntity.created(new URI("/api/m-dictionary-jas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-dictionary-jas} : Updates an existing mDictionaryJa.
     *
     * @param mDictionaryJaDTO the mDictionaryJaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDictionaryJaDTO,
     * or with status {@code 400 (Bad Request)} if the mDictionaryJaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDictionaryJaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-dictionary-jas")
    public ResponseEntity<MDictionaryJaDTO> updateMDictionaryJa(@Valid @RequestBody MDictionaryJaDTO mDictionaryJaDTO) throws URISyntaxException {
        log.debug("REST request to update MDictionaryJa : {}", mDictionaryJaDTO);
        if (mDictionaryJaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDictionaryJaDTO result = mDictionaryJaService.save(mDictionaryJaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDictionaryJaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-dictionary-jas} : get all the mDictionaryJas.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDictionaryJas in body.
     */
    @GetMapping("/m-dictionary-jas")
    public ResponseEntity<List<MDictionaryJaDTO>> getAllMDictionaryJas(MDictionaryJaCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDictionaryJas by criteria: {}", criteria);
        Page<MDictionaryJaDTO> page = mDictionaryJaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-dictionary-jas/count} : count all the mDictionaryJas.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-dictionary-jas/count")
    public ResponseEntity<Long> countMDictionaryJas(MDictionaryJaCriteria criteria) {
        log.debug("REST request to count MDictionaryJas by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDictionaryJaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-dictionary-jas/:id} : get the "id" mDictionaryJa.
     *
     * @param id the id of the mDictionaryJaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDictionaryJaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-dictionary-jas/{id}")
    public ResponseEntity<MDictionaryJaDTO> getMDictionaryJa(@PathVariable Long id) {
        log.debug("REST request to get MDictionaryJa : {}", id);
        Optional<MDictionaryJaDTO> mDictionaryJaDTO = mDictionaryJaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDictionaryJaDTO);
    }

    /**
     * {@code DELETE  /m-dictionary-jas/:id} : delete the "id" mDictionaryJa.
     *
     * @param id the id of the mDictionaryJaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-dictionary-jas/{id}")
    public ResponseEntity<Void> deleteMDictionaryJa(@PathVariable Long id) {
        log.debug("REST request to delete MDictionaryJa : {}", id);
        mDictionaryJaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
