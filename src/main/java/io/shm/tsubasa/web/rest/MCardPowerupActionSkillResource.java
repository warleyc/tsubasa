package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCardPowerupActionSkillService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCardPowerupActionSkillDTO;
import io.shm.tsubasa.service.dto.MCardPowerupActionSkillCriteria;
import io.shm.tsubasa.service.MCardPowerupActionSkillQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCardPowerupActionSkill}.
 */
@RestController
@RequestMapping("/api")
public class MCardPowerupActionSkillResource {

    private final Logger log = LoggerFactory.getLogger(MCardPowerupActionSkillResource.class);

    private static final String ENTITY_NAME = "mCardPowerupActionSkill";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCardPowerupActionSkillService mCardPowerupActionSkillService;

    private final MCardPowerupActionSkillQueryService mCardPowerupActionSkillQueryService;

    public MCardPowerupActionSkillResource(MCardPowerupActionSkillService mCardPowerupActionSkillService, MCardPowerupActionSkillQueryService mCardPowerupActionSkillQueryService) {
        this.mCardPowerupActionSkillService = mCardPowerupActionSkillService;
        this.mCardPowerupActionSkillQueryService = mCardPowerupActionSkillQueryService;
    }

    /**
     * {@code POST  /m-card-powerup-action-skills} : Create a new mCardPowerupActionSkill.
     *
     * @param mCardPowerupActionSkillDTO the mCardPowerupActionSkillDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCardPowerupActionSkillDTO, or with status {@code 400 (Bad Request)} if the mCardPowerupActionSkill has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-card-powerup-action-skills")
    public ResponseEntity<MCardPowerupActionSkillDTO> createMCardPowerupActionSkill(@Valid @RequestBody MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO) throws URISyntaxException {
        log.debug("REST request to save MCardPowerupActionSkill : {}", mCardPowerupActionSkillDTO);
        if (mCardPowerupActionSkillDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCardPowerupActionSkill cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCardPowerupActionSkillDTO result = mCardPowerupActionSkillService.save(mCardPowerupActionSkillDTO);
        return ResponseEntity.created(new URI("/api/m-card-powerup-action-skills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-card-powerup-action-skills} : Updates an existing mCardPowerupActionSkill.
     *
     * @param mCardPowerupActionSkillDTO the mCardPowerupActionSkillDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCardPowerupActionSkillDTO,
     * or with status {@code 400 (Bad Request)} if the mCardPowerupActionSkillDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCardPowerupActionSkillDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-card-powerup-action-skills")
    public ResponseEntity<MCardPowerupActionSkillDTO> updateMCardPowerupActionSkill(@Valid @RequestBody MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO) throws URISyntaxException {
        log.debug("REST request to update MCardPowerupActionSkill : {}", mCardPowerupActionSkillDTO);
        if (mCardPowerupActionSkillDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCardPowerupActionSkillDTO result = mCardPowerupActionSkillService.save(mCardPowerupActionSkillDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCardPowerupActionSkillDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-card-powerup-action-skills} : get all the mCardPowerupActionSkills.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCardPowerupActionSkills in body.
     */
    @GetMapping("/m-card-powerup-action-skills")
    public ResponseEntity<List<MCardPowerupActionSkillDTO>> getAllMCardPowerupActionSkills(MCardPowerupActionSkillCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCardPowerupActionSkills by criteria: {}", criteria);
        Page<MCardPowerupActionSkillDTO> page = mCardPowerupActionSkillQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-card-powerup-action-skills/count} : count all the mCardPowerupActionSkills.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-card-powerup-action-skills/count")
    public ResponseEntity<Long> countMCardPowerupActionSkills(MCardPowerupActionSkillCriteria criteria) {
        log.debug("REST request to count MCardPowerupActionSkills by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCardPowerupActionSkillQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-card-powerup-action-skills/:id} : get the "id" mCardPowerupActionSkill.
     *
     * @param id the id of the mCardPowerupActionSkillDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCardPowerupActionSkillDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-card-powerup-action-skills/{id}")
    public ResponseEntity<MCardPowerupActionSkillDTO> getMCardPowerupActionSkill(@PathVariable Long id) {
        log.debug("REST request to get MCardPowerupActionSkill : {}", id);
        Optional<MCardPowerupActionSkillDTO> mCardPowerupActionSkillDTO = mCardPowerupActionSkillService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCardPowerupActionSkillDTO);
    }

    /**
     * {@code DELETE  /m-card-powerup-action-skills/:id} : delete the "id" mCardPowerupActionSkill.
     *
     * @param id the id of the mCardPowerupActionSkillDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-card-powerup-action-skills/{id}")
    public ResponseEntity<Void> deleteMCardPowerupActionSkill(@PathVariable Long id) {
        log.debug("REST request to delete MCardPowerupActionSkill : {}", id);
        mCardPowerupActionSkillService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
