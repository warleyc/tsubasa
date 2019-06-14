import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMGuildEffectLevel, MGuildEffectLevel } from 'app/shared/model/m-guild-effect-level.model';
import { MGuildEffectLevelService } from './m-guild-effect-level.service';

@Component({
  selector: 'jhi-m-guild-effect-level-update',
  templateUrl: './m-guild-effect-level-update.component.html'
})
export class MGuildEffectLevelUpdateComponent implements OnInit {
  mGuildEffectLevel: IMGuildEffectLevel;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    effectType: [null, [Validators.required]],
    level: [null, [Validators.required]],
    rate: [null, [Validators.required]],
    rateStr: [null, [Validators.required]],
    coin: [null, [Validators.required]],
    guildLevel: [null, [Validators.required]],
    guildMedal: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGuildEffectLevelService: MGuildEffectLevelService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGuildEffectLevel }) => {
      this.updateForm(mGuildEffectLevel);
      this.mGuildEffectLevel = mGuildEffectLevel;
    });
  }

  updateForm(mGuildEffectLevel: IMGuildEffectLevel) {
    this.editForm.patchValue({
      id: mGuildEffectLevel.id,
      effectType: mGuildEffectLevel.effectType,
      level: mGuildEffectLevel.level,
      rate: mGuildEffectLevel.rate,
      rateStr: mGuildEffectLevel.rateStr,
      coin: mGuildEffectLevel.coin,
      guildLevel: mGuildEffectLevel.guildLevel,
      guildMedal: mGuildEffectLevel.guildMedal
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
    const mGuildEffectLevel = this.createFromForm();
    if (mGuildEffectLevel.id !== undefined) {
      this.subscribeToSaveResponse(this.mGuildEffectLevelService.update(mGuildEffectLevel));
    } else {
      this.subscribeToSaveResponse(this.mGuildEffectLevelService.create(mGuildEffectLevel));
    }
  }

  private createFromForm(): IMGuildEffectLevel {
    const entity = {
      ...new MGuildEffectLevel(),
      id: this.editForm.get(['id']).value,
      effectType: this.editForm.get(['effectType']).value,
      level: this.editForm.get(['level']).value,
      rate: this.editForm.get(['rate']).value,
      rateStr: this.editForm.get(['rateStr']).value,
      coin: this.editForm.get(['coin']).value,
      guildLevel: this.editForm.get(['guildLevel']).value,
      guildMedal: this.editForm.get(['guildMedal']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGuildEffectLevel>>) {
    result.subscribe((res: HttpResponse<IMGuildEffectLevel>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
