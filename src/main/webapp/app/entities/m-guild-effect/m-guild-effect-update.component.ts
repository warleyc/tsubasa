import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMGuildEffect, MGuildEffect } from 'app/shared/model/m-guild-effect.model';
import { MGuildEffectService } from './m-guild-effect.service';

@Component({
  selector: 'jhi-m-guild-effect-update',
  templateUrl: './m-guild-effect-update.component.html'
})
export class MGuildEffectUpdateComponent implements OnInit {
  mGuildEffect: IMGuildEffect;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    effectType: [null, [Validators.required]],
    name: [null, [Validators.required]],
    effectId: [],
    thumbnailPath: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGuildEffectService: MGuildEffectService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGuildEffect }) => {
      this.updateForm(mGuildEffect);
      this.mGuildEffect = mGuildEffect;
    });
  }

  updateForm(mGuildEffect: IMGuildEffect) {
    this.editForm.patchValue({
      id: mGuildEffect.id,
      effectType: mGuildEffect.effectType,
      name: mGuildEffect.name,
      effectId: mGuildEffect.effectId,
      thumbnailPath: mGuildEffect.thumbnailPath
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
    const mGuildEffect = this.createFromForm();
    if (mGuildEffect.id !== undefined) {
      this.subscribeToSaveResponse(this.mGuildEffectService.update(mGuildEffect));
    } else {
      this.subscribeToSaveResponse(this.mGuildEffectService.create(mGuildEffect));
    }
  }

  private createFromForm(): IMGuildEffect {
    const entity = {
      ...new MGuildEffect(),
      id: this.editForm.get(['id']).value,
      effectType: this.editForm.get(['effectType']).value,
      name: this.editForm.get(['name']).value,
      effectId: this.editForm.get(['effectId']).value,
      thumbnailPath: this.editForm.get(['thumbnailPath']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGuildEffect>>) {
    result.subscribe((res: HttpResponse<IMGuildEffect>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
