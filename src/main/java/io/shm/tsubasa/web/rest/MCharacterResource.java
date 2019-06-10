package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCharacterService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCharacterDTO;
import io.shm.tsubasa.service.dto.MCharacterCriteria;
import io.shm.tsubasa.service.MCharacterQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCharacter}.
 */
@RestController
@RequestMapping("/api")
public class MCharacterResource {

    private final Logger log = LoggerFactory.getLogger(MCharacterResource.class);

    private static final String ENTITY_NAME = "mCharacter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCharacterService mCharacterService;

    private final MCharacterQueryService mCharacterQueryService;

    public MCharacterResource(MCharacterService mCharacterService, MCharacterQueryService mCharacterQueryService) {
        this.mCharacterService = mCharacterService;
        this.mCharacterQueryService = mCharacterQueryService;
    }

    /**
     * {@code POST  /m-characters} : Create a new mCharacter.
     *
     * @param mCharacterDTO the mCharacterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCharacterDTO, or with status {@code 400 (Bad Request)} if the mCharacter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-characters")
    public ResponseEntity<MCharacterDTO> createMCharacter(@Valid @RequestBody MCharacterDTO mCharacterDTO) throws URISyntaxException {
        log.debug("REST request to save MCharacter : {}", mCharacterDTO);
        if (mCharacterDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCharacter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCharacterDTO result = mCharacterService.save(mCharacterDTO);
        return ResponseEntity.created(new URI("/api/m-characters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-characters} : Updates an existing mCharacter.
     *
     * @param mCharacterDTO the mCharacterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCharacterDTO,
     * or with status {@code 400 (Bad Request)} if the mCharacterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCharacterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-characters")
    public ResponseEntity<MCharacterDTO> updateMCharacter(@Valid @RequestBody MCharacterDTO mCharacterDTO) throws URISyntaxException {
        log.debug("REST request to update MCharacter : {}", mCharacterDTO);
        if (mCharacterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCharacterDTO result = mCharacterService.save(mCharacterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCharacterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-characters} : get all the mCharacters.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCharacters in body.
     */
    @GetMapping("/m-characters")
    public ResponseEntity<List<MCharacterDTO>> getAllMCharacters(MCharacterCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCharacters by criteria: {}", criteria);
        Page<MCharacterDTO> page = mCharacterQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-characters/count} : count all the mCharacters.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-characters/count")
    public ResponseEntity<Long> countMCharacters(MCharacterCriteria criteria) {
        log.debug("REST request to count MCharacters by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCharacterQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-characters/:id} : get the "id" mCharacter.
     *
     * @param id the id of the mCharacterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCharacterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-characters/{id}")
    public ResponseEntity<MCharacterDTO> getMCharacter(@PathVariable Long id) {
        log.debug("REST request to get MCharacter : {}", id);
        Optional<MCharacterDTO> mCharacterDTO = mCharacterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCharacterDTO);
    }

    /**
     * {@code DELETE  /m-characters/:id} : delete the "id" mCharacter.
     *
     * @param id the id of the mCharacterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-characters/{id}")
    public ResponseEntity<Void> deleteMCharacter(@PathVariable Long id) {
        log.debug("REST request to delete MCharacter : {}", id);
        mCharacterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
