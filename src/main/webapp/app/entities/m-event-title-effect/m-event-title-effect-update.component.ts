import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMEventTitleEffect, MEventTitleEffect } from 'app/shared/model/m-event-title-effect.model';
import { MEventTitleEffectService } from './m-event-title-effect.service';

@Component({
  selector: 'jhi-m-event-title-effect-update',
  templateUrl: './m-event-title-effect-update.component.html'
})
export class MEventTitleEffectUpdateComponent implements OnInit {
  mEventTitleEffect: IMEventTitleEffect;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    effectPath: [null, [Validators.required]],
    bgmSoundEvent: [null, [Validators.required]],
    seSoundEvent: [null, [Validators.required]],
    voiceSoundEventSuffixes: [null, [Validators.required]],
    copyrightText: [null, [Validators.required]],
    startAt: [null, [Validators.required]],
    endAt: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mEventTitleEffectService: MEventTitleEffectService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mEventTitleEffect }) => {
      this.updateForm(mEventTitleEffect);
      this.mEventTitleEffect = mEventTitleEffect;
    });
  }

  updateForm(mEventTitleEffect: IMEventTitleEffect) {
    this.editForm.patchValue({
      id: mEventTitleEffect.id,
      effectPath: mEventTitleEffect.effectPath,
      bgmSoundEvent: mEventTitleEffect.bgmSoundEvent,
      seSoundEvent: mEventTitleEffect.seSoundEvent,
      voiceSoundEventSuffixes: mEventTitleEffect.voiceSoundEventSuffixes,
      copyrightText: mEventTitleEffect.copyrightText,
      startAt: mEventTitleEffect.startAt,
      endAt: mEventTitleEffect.endAt
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
    const mEventTitleEffect = this.createFromForm();
    if (mEventTitleEffect.id !== undefined) {
      this.subscribeToSaveResponse(this.mEventTitleEffectService.update(mEventTitleEffect));
    } else {
      this.subscribeToSaveResponse(this.mEventTitleEffectService.create(mEventTitleEffect));
    }
  }

  private createFromForm(): IMEventTitleEffect {
    const entity = {
      ...new MEventTitleEffect(),
      id: this.editForm.get(['id']).value,
      effectPath: this.editForm.get(['effectPath']).value,
      bgmSoundEvent: this.editForm.get(['bgmSoundEvent']).value,
      seSoundEvent: this.editForm.get(['seSoundEvent']).value,
      voiceSoundEventSuffixes: this.editForm.get(['voiceSoundEventSuffixes']).value,
      copyrightText: this.editForm.get(['copyrightText']).value,
      startAt: this.editForm.get(['startAt']).value,
      endAt: this.editForm.get(['endAt']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMEventTitleEffect>>) {
    result.subscribe((res: HttpResponse<IMEventTitleEffect>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
