package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMedalService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMedalDTO;
import io.shm.tsubasa.service.dto.MMedalCriteria;
import io.shm.tsubasa.service.MMedalQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMedal}.
 */
@RestController
@RequestMapping("/api")
public class MMedalResource {

    private final Logger log = LoggerFactory.getLogger(MMedalResource.class);

    private static final String ENTITY_NAME = "mMedal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMedalService mMedalService;

    private final MMedalQueryService mMedalQueryService;

    public MMedalResource(MMedalService mMedalService, MMedalQueryService mMedalQueryService) {
        this.mMedalService = mMedalService;
        this.mMedalQueryService = mMedalQueryService;
    }

    /**
     * {@code POST  /m-medals} : Create a new mMedal.
     *
     * @param mMedalDTO the mMedalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMedalDTO, or with status {@code 400 (Bad Request)} if the mMedal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-medals")
    public ResponseEntity<MMedalDTO> createMMedal(@Valid @RequestBody MMedalDTO mMedalDTO) throws URISyntaxException {
        log.debug("REST request to save MMedal : {}", mMedalDTO);
        if (mMedalDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMedal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMedalDTO result = mMedalService.save(mMedalDTO);
        return ResponseEntity.created(new URI("/api/m-medals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-medals} : Updates an existing mMedal.
     *
     * @param mMedalDTO the mMedalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMedalDTO,
     * or with status {@code 400 (Bad Request)} if the mMedalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMedalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-medals")
    public ResponseEntity<MMedalDTO> updateMMedal(@Valid @RequestBody MMedalDTO mMedalDTO) throws URISyntaxException {
        log.debug("REST request to update MMedal : {}", mMedalDTO);
        if (mMedalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMedalDTO result = mMedalService.save(mMedalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMedalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-medals} : get all the mMedals.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMedals in body.
     */
    @GetMapping("/m-medals")
    public ResponseEntity<List<MMedalDTO>> getAllMMedals(MMedalCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMedals by criteria: {}", criteria);
        Page<MMedalDTO> page = mMedalQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-medals/count} : count all the mMedals.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-medals/count")
    public ResponseEntity<Long> countMMedals(MMedalCriteria criteria) {
        log.debug("REST request to count MMedals by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMedalQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-medals/:id} : get the "id" mMedal.
     *
     * @param id the id of the mMedalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMedalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-medals/{id}")
    public ResponseEntity<MMedalDTO> getMMedal(@PathVariable Long id) {
        log.debug("REST request to get MMedal : {}", id);
        Optional<MMedalDTO> mMedalDTO = mMedalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMedalDTO);
    }

    /**
     * {@code DELETE  /m-medals/:id} : delete the "id" mMedal.
     *
     * @param id the id of the mMedalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-medals/{id}")
    public ResponseEntity<Void> deleteMMedal(@PathVariable Long id) {
        log.debug("REST request to delete MMedal : {}", id);
        mMedalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
