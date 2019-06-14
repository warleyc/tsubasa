package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MLuckService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MLuckDTO;
import io.shm.tsubasa.service.dto.MLuckCriteria;
import io.shm.tsubasa.service.MLuckQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MLuck}.
 */
@RestController
@RequestMapping("/api")
public class MLuckResource {

    private final Logger log = LoggerFactory.getLogger(MLuckResource.class);

    private static final String ENTITY_NAME = "mLuck";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MLuckService mLuckService;

    private final MLuckQueryService mLuckQueryService;

    public MLuckResource(MLuckService mLuckService, MLuckQueryService mLuckQueryService) {
        this.mLuckService = mLuckService;
        this.mLuckQueryService = mLuckQueryService;
    }

    /**
     * {@code POST  /m-lucks} : Create a new mLuck.
     *
     * @param mLuckDTO the mLuckDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mLuckDTO, or with status {@code 400 (Bad Request)} if the mLuck has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-lucks")
    public ResponseEntity<MLuckDTO> createMLuck(@Valid @RequestBody MLuckDTO mLuckDTO) throws URISyntaxException {
        log.debug("REST request to save MLuck : {}", mLuckDTO);
        if (mLuckDTO.getId() != null) {
            throw new BadRequestAlertException("A new mLuck cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MLuckDTO result = mLuckService.save(mLuckDTO);
        return ResponseEntity.created(new URI("/api/m-lucks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-lucks} : Updates an existing mLuck.
     *
     * @param mLuckDTO the mLuckDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mLuckDTO,
     * or with status {@code 400 (Bad Request)} if the mLuckDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mLuckDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-lucks")
    public ResponseEntity<MLuckDTO> updateMLuck(@Valid @RequestBody MLuckDTO mLuckDTO) throws URISyntaxException {
        log.debug("REST request to update MLuck : {}", mLuckDTO);
        if (mLuckDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MLuckDTO result = mLuckService.save(mLuckDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mLuckDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-lucks} : get all the mLucks.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mLucks in body.
     */
    @GetMapping("/m-lucks")
    public ResponseEntity<List<MLuckDTO>> getAllMLucks(MLuckCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MLucks by criteria: {}", criteria);
        Page<MLuckDTO> page = mLuckQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-lucks/count} : count all the mLucks.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-lucks/count")
    public ResponseEntity<Long> countMLucks(MLuckCriteria criteria) {
        log.debug("REST request to count MLucks by criteria: {}", criteria);
        return ResponseEntity.ok().body(mLuckQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-lucks/:id} : get the "id" mLuck.
     *
     * @param id the id of the mLuckDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mLuckDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-lucks/{id}")
    public ResponseEntity<MLuckDTO> getMLuck(@PathVariable Long id) {
        log.debug("REST request to get MLuck : {}", id);
        Optional<MLuckDTO> mLuckDTO = mLuckService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mLuckDTO);
    }

    /**
     * {@code DELETE  /m-lucks/:id} : delete the "id" mLuck.
     *
     * @param id the id of the mLuckDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-lucks/{id}")
    public ResponseEntity<Void> deleteMLuck(@PathVariable Long id) {
        log.debug("REST request to delete MLuck : {}", id);
        mLuckService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
