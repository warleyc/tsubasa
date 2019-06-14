package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMatchOptionService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMatchOptionDTO;
import io.shm.tsubasa.service.dto.MMatchOptionCriteria;
import io.shm.tsubasa.service.MMatchOptionQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMatchOption}.
 */
@RestController
@RequestMapping("/api")
public class MMatchOptionResource {

    private final Logger log = LoggerFactory.getLogger(MMatchOptionResource.class);

    private static final String ENTITY_NAME = "mMatchOption";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMatchOptionService mMatchOptionService;

    private final MMatchOptionQueryService mMatchOptionQueryService;

    public MMatchOptionResource(MMatchOptionService mMatchOptionService, MMatchOptionQueryService mMatchOptionQueryService) {
        this.mMatchOptionService = mMatchOptionService;
        this.mMatchOptionQueryService = mMatchOptionQueryService;
    }

    /**
     * {@code POST  /m-match-options} : Create a new mMatchOption.
     *
     * @param mMatchOptionDTO the mMatchOptionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMatchOptionDTO, or with status {@code 400 (Bad Request)} if the mMatchOption has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-match-options")
    public ResponseEntity<MMatchOptionDTO> createMMatchOption(@Valid @RequestBody MMatchOptionDTO mMatchOptionDTO) throws URISyntaxException {
        log.debug("REST request to save MMatchOption : {}", mMatchOptionDTO);
        if (mMatchOptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMatchOption cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMatchOptionDTO result = mMatchOptionService.save(mMatchOptionDTO);
        return ResponseEntity.created(new URI("/api/m-match-options/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-match-options} : Updates an existing mMatchOption.
     *
     * @param mMatchOptionDTO the mMatchOptionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMatchOptionDTO,
     * or with status {@code 400 (Bad Request)} if the mMatchOptionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMatchOptionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-match-options")
    public ResponseEntity<MMatchOptionDTO> updateMMatchOption(@Valid @RequestBody MMatchOptionDTO mMatchOptionDTO) throws URISyntaxException {
        log.debug("REST request to update MMatchOption : {}", mMatchOptionDTO);
        if (mMatchOptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMatchOptionDTO result = mMatchOptionService.save(mMatchOptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMatchOptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-match-options} : get all the mMatchOptions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMatchOptions in body.
     */
    @GetMapping("/m-match-options")
    public ResponseEntity<List<MMatchOptionDTO>> getAllMMatchOptions(MMatchOptionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMatchOptions by criteria: {}", criteria);
        Page<MMatchOptionDTO> page = mMatchOptionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-match-options/count} : count all the mMatchOptions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-match-options/count")
    public ResponseEntity<Long> countMMatchOptions(MMatchOptionCriteria criteria) {
        log.debug("REST request to count MMatchOptions by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMatchOptionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-match-options/:id} : get the "id" mMatchOption.
     *
     * @param id the id of the mMatchOptionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMatchOptionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-match-options/{id}")
    public ResponseEntity<MMatchOptionDTO> getMMatchOption(@PathVariable Long id) {
        log.debug("REST request to get MMatchOption : {}", id);
        Optional<MMatchOptionDTO> mMatchOptionDTO = mMatchOptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMatchOptionDTO);
    }

    /**
     * {@code DELETE  /m-match-options/:id} : delete the "id" mMatchOption.
     *
     * @param id the id of the mMatchOptionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-match-options/{id}")
    public ResponseEntity<Void> deleteMMatchOption(@PathVariable Long id) {
        log.debug("REST request to delete MMatchOption : {}", id);
        mMatchOptionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
