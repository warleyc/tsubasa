package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MExtraNewsService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MExtraNewsDTO;
import io.shm.tsubasa.service.dto.MExtraNewsCriteria;
import io.shm.tsubasa.service.MExtraNewsQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MExtraNews}.
 */
@RestController
@RequestMapping("/api")
public class MExtraNewsResource {

    private final Logger log = LoggerFactory.getLogger(MExtraNewsResource.class);

    private static final String ENTITY_NAME = "mExtraNews";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MExtraNewsService mExtraNewsService;

    private final MExtraNewsQueryService mExtraNewsQueryService;

    public MExtraNewsResource(MExtraNewsService mExtraNewsService, MExtraNewsQueryService mExtraNewsQueryService) {
        this.mExtraNewsService = mExtraNewsService;
        this.mExtraNewsQueryService = mExtraNewsQueryService;
    }

    /**
     * {@code POST  /m-extra-news} : Create a new mExtraNews.
     *
     * @param mExtraNewsDTO the mExtraNewsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mExtraNewsDTO, or with status {@code 400 (Bad Request)} if the mExtraNews has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-extra-news")
    public ResponseEntity<MExtraNewsDTO> createMExtraNews(@Valid @RequestBody MExtraNewsDTO mExtraNewsDTO) throws URISyntaxException {
        log.debug("REST request to save MExtraNews : {}", mExtraNewsDTO);
        if (mExtraNewsDTO.getId() != null) {
            throw new BadRequestAlertException("A new mExtraNews cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MExtraNewsDTO result = mExtraNewsService.save(mExtraNewsDTO);
        return ResponseEntity.created(new URI("/api/m-extra-news/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-extra-news} : Updates an existing mExtraNews.
     *
     * @param mExtraNewsDTO the mExtraNewsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mExtraNewsDTO,
     * or with status {@code 400 (Bad Request)} if the mExtraNewsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mExtraNewsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-extra-news")
    public ResponseEntity<MExtraNewsDTO> updateMExtraNews(@Valid @RequestBody MExtraNewsDTO mExtraNewsDTO) throws URISyntaxException {
        log.debug("REST request to update MExtraNews : {}", mExtraNewsDTO);
        if (mExtraNewsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MExtraNewsDTO result = mExtraNewsService.save(mExtraNewsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mExtraNewsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-extra-news} : get all the mExtraNews.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mExtraNews in body.
     */
    @GetMapping("/m-extra-news")
    public ResponseEntity<List<MExtraNewsDTO>> getAllMExtraNews(MExtraNewsCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MExtraNews by criteria: {}", criteria);
        Page<MExtraNewsDTO> page = mExtraNewsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-extra-news/count} : count all the mExtraNews.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-extra-news/count")
    public ResponseEntity<Long> countMExtraNews(MExtraNewsCriteria criteria) {
        log.debug("REST request to count MExtraNews by criteria: {}", criteria);
        return ResponseEntity.ok().body(mExtraNewsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-extra-news/:id} : get the "id" mExtraNews.
     *
     * @param id the id of the mExtraNewsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mExtraNewsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-extra-news/{id}")
    public ResponseEntity<MExtraNewsDTO> getMExtraNews(@PathVariable Long id) {
        log.debug("REST request to get MExtraNews : {}", id);
        Optional<MExtraNewsDTO> mExtraNewsDTO = mExtraNewsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mExtraNewsDTO);
    }

    /**
     * {@code DELETE  /m-extra-news/:id} : delete the "id" mExtraNews.
     *
     * @param id the id of the mExtraNewsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-extra-news/{id}")
    public ResponseEntity<Void> deleteMExtraNews(@PathVariable Long id) {
        log.debug("REST request to delete MExtraNews : {}", id);
        mExtraNewsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
