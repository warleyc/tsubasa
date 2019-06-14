package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MHomeMarqueeService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MHomeMarqueeDTO;
import io.shm.tsubasa.service.dto.MHomeMarqueeCriteria;
import io.shm.tsubasa.service.MHomeMarqueeQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MHomeMarquee}.
 */
@RestController
@RequestMapping("/api")
public class MHomeMarqueeResource {

    private final Logger log = LoggerFactory.getLogger(MHomeMarqueeResource.class);

    private static final String ENTITY_NAME = "mHomeMarquee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MHomeMarqueeService mHomeMarqueeService;

    private final MHomeMarqueeQueryService mHomeMarqueeQueryService;

    public MHomeMarqueeResource(MHomeMarqueeService mHomeMarqueeService, MHomeMarqueeQueryService mHomeMarqueeQueryService) {
        this.mHomeMarqueeService = mHomeMarqueeService;
        this.mHomeMarqueeQueryService = mHomeMarqueeQueryService;
    }

    /**
     * {@code POST  /m-home-marquees} : Create a new mHomeMarquee.
     *
     * @param mHomeMarqueeDTO the mHomeMarqueeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mHomeMarqueeDTO, or with status {@code 400 (Bad Request)} if the mHomeMarquee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-home-marquees")
    public ResponseEntity<MHomeMarqueeDTO> createMHomeMarquee(@Valid @RequestBody MHomeMarqueeDTO mHomeMarqueeDTO) throws URISyntaxException {
        log.debug("REST request to save MHomeMarquee : {}", mHomeMarqueeDTO);
        if (mHomeMarqueeDTO.getId() != null) {
            throw new BadRequestAlertException("A new mHomeMarquee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MHomeMarqueeDTO result = mHomeMarqueeService.save(mHomeMarqueeDTO);
        return ResponseEntity.created(new URI("/api/m-home-marquees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-home-marquees} : Updates an existing mHomeMarquee.
     *
     * @param mHomeMarqueeDTO the mHomeMarqueeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mHomeMarqueeDTO,
     * or with status {@code 400 (Bad Request)} if the mHomeMarqueeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mHomeMarqueeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-home-marquees")
    public ResponseEntity<MHomeMarqueeDTO> updateMHomeMarquee(@Valid @RequestBody MHomeMarqueeDTO mHomeMarqueeDTO) throws URISyntaxException {
        log.debug("REST request to update MHomeMarquee : {}", mHomeMarqueeDTO);
        if (mHomeMarqueeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MHomeMarqueeDTO result = mHomeMarqueeService.save(mHomeMarqueeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mHomeMarqueeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-home-marquees} : get all the mHomeMarquees.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mHomeMarquees in body.
     */
    @GetMapping("/m-home-marquees")
    public ResponseEntity<List<MHomeMarqueeDTO>> getAllMHomeMarquees(MHomeMarqueeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MHomeMarquees by criteria: {}", criteria);
        Page<MHomeMarqueeDTO> page = mHomeMarqueeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-home-marquees/count} : count all the mHomeMarquees.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-home-marquees/count")
    public ResponseEntity<Long> countMHomeMarquees(MHomeMarqueeCriteria criteria) {
        log.debug("REST request to count MHomeMarquees by criteria: {}", criteria);
        return ResponseEntity.ok().body(mHomeMarqueeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-home-marquees/:id} : get the "id" mHomeMarquee.
     *
     * @param id the id of the mHomeMarqueeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mHomeMarqueeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-home-marquees/{id}")
    public ResponseEntity<MHomeMarqueeDTO> getMHomeMarquee(@PathVariable Long id) {
        log.debug("REST request to get MHomeMarquee : {}", id);
        Optional<MHomeMarqueeDTO> mHomeMarqueeDTO = mHomeMarqueeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mHomeMarqueeDTO);
    }

    /**
     * {@code DELETE  /m-home-marquees/:id} : delete the "id" mHomeMarquee.
     *
     * @param id the id of the mHomeMarqueeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-home-marquees/{id}")
    public ResponseEntity<Void> deleteMHomeMarquee(@PathVariable Long id) {
        log.debug("REST request to delete MHomeMarquee : {}", id);
        mHomeMarqueeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
