import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMRivalEncountCutin, MRivalEncountCutin } from 'app/shared/model/m-rival-encount-cutin.model';
import { MRivalEncountCutinService } from './m-rival-encount-cutin.service';

@Component({
  selector: 'jhi-m-rival-encount-cutin-update',
  templateUrl: './m-rival-encount-cutin-update.component.html'
})
export class MRivalEncountCutinUpdateComponent implements OnInit {
  mRivalEncountCutin: IMRivalEncountCutin;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    offenseCharacterId: [null, [Validators.required]],
    offenseTeamId: [],
    defenseCharacterId: [null, [Validators.required]],
    defenseTeamId: [],
    cutinType: [null, [Validators.required]],
    chapter1Text: [],
    chapter1SoundEvent: [],
    chapter2Text: [],
    chapter2SoundEvent: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mRivalEncountCutinService: MRivalEncountCutinService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mRivalEncountCutin }) => {
      this.updateForm(mRivalEncountCutin);
      this.mRivalEncountCutin = mRivalEncountCutin;
    });
  }

  updateForm(mRivalEncountCutin: IMRivalEncountCutin) {
    this.editForm.patchValue({
      id: mRivalEncountCutin.id,
      offenseCharacterId: mRivalEncountCutin.offenseCharacterId,
      offenseTeamId: mRivalEncountCutin.offenseTeamId,
      defenseCharacterId: mRivalEncountCutin.defenseCharacterId,
      defenseTeamId: mRivalEncountCutin.defenseTeamId,
      cutinType: mRivalEncountCutin.cutinType,
      chapter1Text: mRivalEncountCutin.chapter1Text,
      chapter1SoundEvent: mRivalEncountCutin.chapter1SoundEvent,
      chapter2Text: mRivalEncountCutin.chapter2Text,
      chapter2SoundEvent: mRivalEncountCutin.chapter2SoundEvent
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
    const mRivalEncountCutin = this.createFromForm();
    if (mRivalEncountCutin.id !== undefined) {
      this.subscribeToSaveResponse(this.mRivalEncountCutinService.update(mRivalEncountCutin));
    } else {
      this.subscribeToSaveResponse(this.mRivalEncountCutinService.create(mRivalEncountCutin));
    }
  }

  private createFromForm(): IMRivalEncountCutin {
    const entity = {
      ...new MRivalEncountCutin(),
      id: this.editForm.get(['id']).value,
      offenseCharacterId: this.editForm.get(['offenseCharacterId']).value,
      offenseTeamId: this.editForm.get(['offenseTeamId']).value,
      defenseCharacterId: this.editForm.get(['defenseCharacterId']).value,
      defenseTeamId: this.editForm.get(['defenseTeamId']).value,
      cutinType: this.editForm.get(['cutinType']).value,
      chapter1Text: this.editForm.get(['chapter1Text']).value,
      chapter1SoundEvent: this.editForm.get(['chapter1SoundEvent']).value,
      chapter2Text: this.editForm.get(['chapter2Text']).value,
      chapter2SoundEvent: this.editForm.get(['chapter2SoundEvent']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMRivalEncountCutin>>) {
    result.subscribe((res: HttpResponse<IMRivalEncountCutin>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
