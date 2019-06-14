import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMNpcDeck, MNpcDeck } from 'app/shared/model/m-npc-deck.model';
import { MNpcDeckService } from './m-npc-deck.service';
import { IMFormation } from 'app/shared/model/m-formation.model';
import { MFormationService } from 'app/entities/m-formation';

@Component({
  selector: 'jhi-m-npc-deck-update',
  templateUrl: './m-npc-deck-update.component.html'
})
export class MNpcDeckUpdateComponent implements OnInit {
  mNpcDeck: IMNpcDeck;
  isSaving: boolean;

  mformations: IMFormation[];

  editForm = this.fb.group({
    id: [],
    teamName: [null, [Validators.required]],
    uniformBottomFp: [null, [Validators.required]],
    uniformUpFp: [null, [Validators.required]],
    uniformBottomGk: [null, [Validators.required]],
    uniformUpGk: [null, [Validators.required]],
    formationId: [null, [Validators.required]],
    captainCardId: [null, [Validators.required]],
    teamEffect1CardId: [null, [Validators.required]],
    teamEffect2CardId: [null, [Validators.required]],
    teamEffect3CardId: [null, [Validators.required]],
    npcCardId1: [null, [Validators.required]],
    npcCardId2: [null, [Validators.required]],
    npcCardId3: [null, [Validators.required]],
    npcCardId4: [null, [Validators.required]],
    npcCardId5: [null, [Validators.required]],
    npcCardId6: [null, [Validators.required]],
    npcCardId7: [null, [Validators.required]],
    npcCardId8: [null, [Validators.required]],
    npcCardId9: [null, [Validators.required]],
    npcCardId10: [null, [Validators.required]],
    npcCardId11: [null, [Validators.required]],
    tick: [null, [Validators.required]],
    mformationId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mNpcDeckService: MNpcDeckService,
    protected mFormationService: MFormationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mNpcDeck }) => {
      this.updateForm(mNpcDeck);
      this.mNpcDeck = mNpcDeck;
    });
    this.mFormationService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMFormation[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMFormation[]>) => response.body)
      )
      .subscribe((res: IMFormation[]) => (this.mformations = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mNpcDeck: IMNpcDeck) {
    this.editForm.patchValue({
      id: mNpcDeck.id,
      teamName: mNpcDeck.teamName,
      uniformBottomFp: mNpcDeck.uniformBottomFp,
      uniformUpFp: mNpcDeck.uniformUpFp,
      uniformBottomGk: mNpcDeck.uniformBottomGk,
      uniformUpGk: mNpcDeck.uniformUpGk,
      formationId: mNpcDeck.formationId,
      captainCardId: mNpcDeck.captainCardId,
      teamEffect1CardId: mNpcDeck.teamEffect1CardId,
      teamEffect2CardId: mNpcDeck.teamEffect2CardId,
      teamEffect3CardId: mNpcDeck.teamEffect3CardId,
      npcCardId1: mNpcDeck.npcCardId1,
      npcCardId2: mNpcDeck.npcCardId2,
      npcCardId3: mNpcDeck.npcCardId3,
      npcCardId4: mNpcDeck.npcCardId4,
      npcCardId5: mNpcDeck.npcCardId5,
      npcCardId6: mNpcDeck.npcCardId6,
      npcCardId7: mNpcDeck.npcCardId7,
      npcCardId8: mNpcDeck.npcCardId8,
      npcCardId9: mNpcDeck.npcCardId9,
      npcCardId10: mNpcDeck.npcCardId10,
      npcCardId11: mNpcDeck.npcCardId11,
      tick: mNpcDeck.tick,
      mformationId: mNpcDeck.mformationId
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file = event.target.files[0];
        if (isImage && !/^image\//.test(file.type)) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      () => console.log('blob added'), // sucess
      this.onError
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mNpcDeck = this.createFromForm();
    if (mNpcDeck.id !== undefined) {
      this.subscribeToSaveResponse(this.mNpcDeckService.update(mNpcDeck));
    } else {
      this.subscribeToSaveResponse(this.mNpcDeckService.create(mNpcDeck));
    }
  }

  private createFromForm(): IMNpcDeck {
    const entity = {
      ...new MNpcDeck(),
      id: this.editForm.get(['id']).value,
      teamName: this.editForm.get(['teamName']).value,
      uniformBottomFp: this.editForm.get(['uniformBottomFp']).value,
      uniformUpFp: this.editForm.get(['uniformUpFp']).value,
      uniformBottomGk: this.editForm.get(['uniformBottomGk']).value,
      uniformUpGk: this.editForm.get(['uniformUpGk']).value,
      formationId: this.editForm.get(['formationId']).value,
      captainCardId: this.editForm.get(['captainCardId']).value,
      teamEffect1CardId: this.editForm.get(['teamEffect1CardId']).value,
      teamEffect2CardId: this.editForm.get(['teamEffect2CardId']).value,
      teamEffect3CardId: this.editForm.get(['teamEffect3CardId']).value,
      npcCardId1: this.editForm.get(['npcCardId1']).value,
      npcCardId2: this.editForm.get(['npcCardId2']).value,
      npcCardId3: this.editForm.get(['npcCardId3']).value,
      npcCardId4: this.editForm.get(['npcCardId4']).value,
      npcCardId5: this.editForm.get(['npcCardId5']).value,
      npcCardId6: this.editForm.get(['npcCardId6']).value,
      npcCardId7: this.editForm.get(['npcCardId7']).value,
      npcCardId8: this.editForm.get(['npcCardId8']).value,
      npcCardId9: this.editForm.get(['npcCardId9']).value,
      npcCardId10: this.editForm.get(['npcCardId10']).value,
      npcCardId11: this.editForm.get(['npcCardId11']).value,
      tick: this.editForm.get(['tick']).value,
      mformationId: this.editForm.get(['mformationId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMNpcDeck>>) {
    result.subscribe((res: HttpResponse<IMNpcDeck>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackMFormationById(index: number, item: IMFormation) {
    return item.id;
  }
}
