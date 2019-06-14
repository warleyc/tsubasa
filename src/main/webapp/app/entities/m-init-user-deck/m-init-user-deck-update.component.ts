import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMInitUserDeck, MInitUserDeck } from 'app/shared/model/m-init-user-deck.model';
import { MInitUserDeckService } from './m-init-user-deck.service';

@Component({
  selector: 'jhi-m-init-user-deck-update',
  templateUrl: './m-init-user-deck-update.component.html'
})
export class MInitUserDeckUpdateComponent implements OnInit {
  mInitUserDeck: IMInitUserDeck;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    deckId: [null, [Validators.required]],
    name: [null, [Validators.required]],
    formationId: [null, [Validators.required]],
    captainCardId: [],
    gkCardId: [],
    fp1CardId: [],
    fp2CardId: [],
    fp3CardId: [],
    fp4CardId: [],
    fp5CardId: [],
    fp6CardId: [],
    fp7CardId: [],
    fp8CardId: [],
    fp9CardId: [],
    fp10CardId: [],
    sub1CardId: [],
    sub2CardId: [],
    sub3CardId: [],
    sub4CardId: [],
    sub5CardId: [],
    teamEffect1CardId: [],
    teamEffect2CardId: [],
    teamEffect3CardId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mInitUserDeckService: MInitUserDeckService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mInitUserDeck }) => {
      this.updateForm(mInitUserDeck);
      this.mInitUserDeck = mInitUserDeck;
    });
  }

  updateForm(mInitUserDeck: IMInitUserDeck) {
    this.editForm.patchValue({
      id: mInitUserDeck.id,
      deckId: mInitUserDeck.deckId,
      name: mInitUserDeck.name,
      formationId: mInitUserDeck.formationId,
      captainCardId: mInitUserDeck.captainCardId,
      gkCardId: mInitUserDeck.gkCardId,
      fp1CardId: mInitUserDeck.fp1CardId,
      fp2CardId: mInitUserDeck.fp2CardId,
      fp3CardId: mInitUserDeck.fp3CardId,
      fp4CardId: mInitUserDeck.fp4CardId,
      fp5CardId: mInitUserDeck.fp5CardId,
      fp6CardId: mInitUserDeck.fp6CardId,
      fp7CardId: mInitUserDeck.fp7CardId,
      fp8CardId: mInitUserDeck.fp8CardId,
      fp9CardId: mInitUserDeck.fp9CardId,
      fp10CardId: mInitUserDeck.fp10CardId,
      sub1CardId: mInitUserDeck.sub1CardId,
      sub2CardId: mInitUserDeck.sub2CardId,
      sub3CardId: mInitUserDeck.sub3CardId,
      sub4CardId: mInitUserDeck.sub4CardId,
      sub5CardId: mInitUserDeck.sub5CardId,
      teamEffect1CardId: mInitUserDeck.teamEffect1CardId,
      teamEffect2CardId: mInitUserDeck.teamEffect2CardId,
      teamEffect3CardId: mInitUserDeck.teamEffect3CardId
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
    const mInitUserDeck = this.createFromForm();
    if (mInitUserDeck.id !== undefined) {
      this.subscribeToSaveResponse(this.mInitUserDeckService.update(mInitUserDeck));
    } else {
      this.subscribeToSaveResponse(this.mInitUserDeckService.create(mInitUserDeck));
    }
  }

  private createFromForm(): IMInitUserDeck {
    const entity = {
      ...new MInitUserDeck(),
      id: this.editForm.get(['id']).value,
      deckId: this.editForm.get(['deckId']).value,
      name: this.editForm.get(['name']).value,
      formationId: this.editForm.get(['formationId']).value,
      captainCardId: this.editForm.get(['captainCardId']).value,
      gkCardId: this.editForm.get(['gkCardId']).value,
      fp1CardId: this.editForm.get(['fp1CardId']).value,
      fp2CardId: this.editForm.get(['fp2CardId']).value,
      fp3CardId: this.editForm.get(['fp3CardId']).value,
      fp4CardId: this.editForm.get(['fp4CardId']).value,
      fp5CardId: this.editForm.get(['fp5CardId']).value,
      fp6CardId: this.editForm.get(['fp6CardId']).value,
      fp7CardId: this.editForm.get(['fp7CardId']).value,
      fp8CardId: this.editForm.get(['fp8CardId']).value,
      fp9CardId: this.editForm.get(['fp9CardId']).value,
      fp10CardId: this.editForm.get(['fp10CardId']).value,
      sub1CardId: this.editForm.get(['sub1CardId']).value,
      sub2CardId: this.editForm.get(['sub2CardId']).value,
      sub3CardId: this.editForm.get(['sub3CardId']).value,
      sub4CardId: this.editForm.get(['sub4CardId']).value,
      sub5CardId: this.editForm.get(['sub5CardId']).value,
      teamEffect1CardId: this.editForm.get(['teamEffect1CardId']).value,
      teamEffect2CardId: this.editForm.get(['teamEffect2CardId']).value,
      teamEffect3CardId: this.editForm.get(['teamEffect3CardId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMInitUserDeck>>) {
    result.subscribe((res: HttpResponse<IMInitUserDeck>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
