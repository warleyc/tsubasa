package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDictionaryDeService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDictionaryDeDTO;
import io.shm.tsubasa.service.dto.MDictionaryDeCriteria;
import io.shm.tsubasa.service.MDictionaryDeQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDictionaryDe}.
 */
@RestController
@RequestMapping("/api")
public class MDictionaryDeResource {

    private final Logger log = LoggerFactory.getLogger(MDictionaryDeResource.class);

    private static final String ENTITY_NAME = "mDictionaryDe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDictionaryDeService mDictionaryDeService;

    private final MDictionaryDeQueryService mDictionaryDeQueryService;

    public MDictionaryDeResource(MDictionaryDeService mDictionaryDeService, MDictionaryDeQueryService mDictionaryDeQueryService) {
        this.mDictionaryDeService = mDictionaryDeService;
        this.mDictionaryDeQueryService = mDictionaryDeQueryService;
    }

    /**
     * {@code POST  /m-dictionary-des} : Create a new mDictionaryDe.
     *
     * @param mDictionaryDeDTO the mDictionaryDeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDictionaryDeDTO, or with status {@code 400 (Bad Request)} if the mDictionaryDe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-dictionary-des")
    public ResponseEntity<MDictionaryDeDTO> createMDictionaryDe(@Valid @RequestBody MDictionaryDeDTO mDictionaryDeDTO) throws URISyntaxException {
        log.debug("REST request to save MDictionaryDe : {}", mDictionaryDeDTO);
        if (mDictionaryDeDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDictionaryDe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDictionaryDeDTO result = mDictionaryDeService.save(mDictionaryDeDTO);
        return ResponseEntity.created(new URI("/api/m-dictionary-des/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-dictionary-des} : Updates an existing mDictionaryDe.
     *
     * @param mDictionaryDeDTO the mDictionaryDeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDictionaryDeDTO,
     * or with status {@code 400 (Bad Request)} if the mDictionaryDeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDictionaryDeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-dictionary-des")
    public ResponseEntity<MDictionaryDeDTO> updateMDictionaryDe(@Valid @RequestBody MDictionaryDeDTO mDictionaryDeDTO) throws URISyntaxException {
        log.debug("REST request to update MDictionaryDe : {}", mDictionaryDeDTO);
        if (mDictionaryDeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDictionaryDeDTO result = mDictionaryDeService.save(mDictionaryDeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDictionaryDeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-dictionary-des} : get all the mDictionaryDes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDictionaryDes in body.
     */
    @GetMapping("/m-dictionary-des")
    public ResponseEntity<List<MDictionaryDeDTO>> getAllMDictionaryDes(MDictionaryDeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDictionaryDes by criteria: {}", criteria);
        Page<MDictionaryDeDTO> page = mDictionaryDeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-dictionary-des/count} : count all the mDictionaryDes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-dictionary-des/count")
    public ResponseEntity<Long> countMDictionaryDes(MDictionaryDeCriteria criteria) {
        log.debug("REST request to count MDictionaryDes by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDictionaryDeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-dictionary-des/:id} : get the "id" mDictionaryDe.
     *
     * @param id the id of the mDictionaryDeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDictionaryDeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-dictionary-des/{id}")
    public ResponseEntity<MDictionaryDeDTO> getMDictionaryDe(@PathVariable Long id) {
        log.debug("REST request to get MDictionaryDe : {}", id);
        Optional<MDictionaryDeDTO> mDictionaryDeDTO = mDictionaryDeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDictionaryDeDTO);
    }

    /**
     * {@code DELETE  /m-dictionary-des/:id} : delete the "id" mDictionaryDe.
     *
     * @param id the id of the mDictionaryDeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-dictionary-des/{id}")
    public ResponseEntity<Void> deleteMDictionaryDe(@PathVariable Long id) {
        log.debug("REST request to delete MDictionaryDe : {}", id);
        mDictionaryDeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
