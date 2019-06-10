package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MAnnounceTextService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MAnnounceTextDTO;
import io.shm.tsubasa.service.dto.MAnnounceTextCriteria;
import io.shm.tsubasa.service.MAnnounceTextQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MAnnounceText}.
 */
@RestController
@RequestMapping("/api")
public class MAnnounceTextResource {

    private final Logger log = LoggerFactory.getLogger(MAnnounceTextResource.class);

    private static final String ENTITY_NAME = "mAnnounceText";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MAnnounceTextService mAnnounceTextService;

    private final MAnnounceTextQueryService mAnnounceTextQueryService;

    public MAnnounceTextResource(MAnnounceTextService mAnnounceTextService, MAnnounceTextQueryService mAnnounceTextQueryService) {
        this.mAnnounceTextService = mAnnounceTextService;
        this.mAnnounceTextQueryService = mAnnounceTextQueryService;
    }

    /**
     * {@code POST  /m-announce-texts} : Create a new mAnnounceText.
     *
     * @param mAnnounceTextDTO the mAnnounceTextDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mAnnounceTextDTO, or with status {@code 400 (Bad Request)} if the mAnnounceText has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-announce-texts")
    public ResponseEntity<MAnnounceTextDTO> createMAnnounceText(@Valid @RequestBody MAnnounceTextDTO mAnnounceTextDTO) throws URISyntaxException {
        log.debug("REST request to save MAnnounceText : {}", mAnnounceTextDTO);
        if (mAnnounceTextDTO.getId() != null) {
            throw new BadRequestAlertException("A new mAnnounceText cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MAnnounceTextDTO result = mAnnounceTextService.save(mAnnounceTextDTO);
        return ResponseEntity.created(new URI("/api/m-announce-texts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-announce-texts} : Updates an existing mAnnounceText.
     *
     * @param mAnnounceTextDTO the mAnnounceTextDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mAnnounceTextDTO,
     * or with status {@code 400 (Bad Request)} if the mAnnounceTextDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mAnnounceTextDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-announce-texts")
    public ResponseEntity<MAnnounceTextDTO> updateMAnnounceText(@Valid @RequestBody MAnnounceTextDTO mAnnounceTextDTO) throws URISyntaxException {
        log.debug("REST request to update MAnnounceText : {}", mAnnounceTextDTO);
        if (mAnnounceTextDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MAnnounceTextDTO result = mAnnounceTextService.save(mAnnounceTextDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mAnnounceTextDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-announce-texts} : get all the mAnnounceTexts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mAnnounceTexts in body.
     */
    @GetMapping("/m-announce-texts")
    public ResponseEntity<List<MAnnounceTextDTO>> getAllMAnnounceTexts(MAnnounceTextCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MAnnounceTexts by criteria: {}", criteria);
        Page<MAnnounceTextDTO> page = mAnnounceTextQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-announce-texts/count} : count all the mAnnounceTexts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-announce-texts/count")
    public ResponseEntity<Long> countMAnnounceTexts(MAnnounceTextCriteria criteria) {
        log.debug("REST request to count MAnnounceTexts by criteria: {}", criteria);
        return ResponseEntity.ok().body(mAnnounceTextQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-announce-texts/:id} : get the "id" mAnnounceText.
     *
     * @param id the id of the mAnnounceTextDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mAnnounceTextDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-announce-texts/{id}")
    public ResponseEntity<MAnnounceTextDTO> getMAnnounceText(@PathVariable Long id) {
        log.debug("REST request to get MAnnounceText : {}", id);
        Optional<MAnnounceTextDTO> mAnnounceTextDTO = mAnnounceTextService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mAnnounceTextDTO);
    }

    /**
     * {@code DELETE  /m-announce-texts/:id} : delete the "id" mAnnounceText.
     *
     * @param id the id of the mAnnounceTextDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-announce-texts/{id}")
    public ResponseEntity<Void> deleteMAnnounceText(@PathVariable Long id) {
        log.debug("REST request to delete MAnnounceText : {}", id);
        mAnnounceTextService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
