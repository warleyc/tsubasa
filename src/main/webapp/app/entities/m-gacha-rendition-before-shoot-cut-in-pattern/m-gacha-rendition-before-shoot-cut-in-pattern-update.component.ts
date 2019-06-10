import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import {
  IMGachaRenditionBeforeShootCutInPattern,
  MGachaRenditionBeforeShootCutInPattern
} from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-pattern.model';
import { MGachaRenditionBeforeShootCutInPatternService } from './m-gacha-rendition-before-shoot-cut-in-pattern.service';

@Component({
  selector: 'jhi-m-gacha-rendition-before-shoot-cut-in-pattern-update',
  templateUrl: './m-gacha-rendition-before-shoot-cut-in-pattern-update.component.html'
})
export class MGachaRenditionBeforeShootCutInPatternUpdateComponent implements OnInit {
  mGachaRenditionBeforeShootCutInPattern: IMGachaRenditionBeforeShootCutInPattern;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    renditionId: [null, [Validators.required]],
    isManySsr: [null, [Validators.required]],
    isSsr: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    pattern: [null, [Validators.required]],
    normalPrefabName: [null, [Validators.required]],
    flashBackPrefabName1: [null, [Validators.required]],
    flashBackPrefabName2: [null, [Validators.required]],
    flashBackPrefabName3: [null, [Validators.required]],
    flashBackPrefabName4: [null, [Validators.required]],
    voicePrefix: [null, [Validators.required]],
    seNormal: [null, [Validators.required]],
    seFlashBack: [null, [Validators.required]],
    exceptKickerId: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGachaRenditionBeforeShootCutInPatternService: MGachaRenditionBeforeShootCutInPatternService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGachaRenditionBeforeShootCutInPattern }) => {
      this.updateForm(mGachaRenditionBeforeShootCutInPattern);
      this.mGachaRenditionBeforeShootCutInPattern = mGachaRenditionBeforeShootCutInPattern;
    });
  }

  updateForm(mGachaRenditionBeforeShootCutInPattern: IMGachaRenditionBeforeShootCutInPattern) {
    this.editForm.patchValue({
      id: mGachaRenditionBeforeShootCutInPattern.id,
      renditionId: mGachaRenditionBeforeShootCutInPattern.renditionId,
      isManySsr: mGachaRenditionBeforeShootCutInPattern.isManySsr,
      isSsr: mGachaRenditionBeforeShootCutInPattern.isSsr,
      weight: mGachaRenditionBeforeShootCutInPattern.weight,
      pattern: mGachaRenditionBeforeShootCutInPattern.pattern,
      normalPrefabName: mGachaRenditionBeforeShootCutInPattern.normalPrefabName,
      flashBackPrefabName1: mGachaRenditionBeforeShootCutInPattern.flashBackPrefabName1,
      flashBackPrefabName2: mGachaRenditionBeforeShootCutInPattern.flashBackPrefabName2,
      flashBackPrefabName3: mGachaRenditionBeforeShootCutInPattern.flashBackPrefabName3,
      flashBackPrefabName4: mGachaRenditionBeforeShootCutInPattern.flashBackPrefabName4,
      voicePrefix: mGachaRenditionBeforeShootCutInPattern.voicePrefix,
      seNormal: mGachaRenditionBeforeShootCutInPattern.seNormal,
      seFlashBack: mGachaRenditionBeforeShootCutInPattern.seFlashBack,
      exceptKickerId: mGachaRenditionBeforeShootCutInPattern.exceptKickerId
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
    const mGachaRenditionBeforeShootCutInPattern = this.createFromForm();
    if (mGachaRenditionBeforeShootCutInPattern.id !== undefined) {
      this.subscribeToSaveResponse(this.mGachaRenditionBeforeShootCutInPatternService.update(mGachaRenditionBeforeShootCutInPattern));
    } else {
      this.subscribeToSaveResponse(this.mGachaRenditionBeforeShootCutInPatternService.create(mGachaRenditionBeforeShootCutInPattern));
    }
  }

  private createFromForm(): IMGachaRenditionBeforeShootCutInPattern {
    const entity = {
      ...new MGachaRenditionBeforeShootCutInPattern(),
      id: this.editForm.get(['id']).value,
      renditionId: this.editForm.get(['renditionId']).value,
      isManySsr: this.editForm.get(['isManySsr']).value,
      isSsr: this.editForm.get(['isSsr']).value,
      weight: this.editForm.get(['weight']).value,
      pattern: this.editForm.get(['pattern']).value,
      normalPrefabName: this.editForm.get(['normalPrefabName']).value,
      flashBackPrefabName1: this.editForm.get(['flashBackPrefabName1']).value,
      flashBackPrefabName2: this.editForm.get(['flashBackPrefabName2']).value,
      flashBackPrefabName3: this.editForm.get(['flashBackPrefabName3']).value,
      flashBackPrefabName4: this.editForm.get(['flashBackPrefabName4']).value,
      voicePrefix: this.editForm.get(['voicePrefix']).value,
      seNormal: this.editForm.get(['seNormal']).value,
      seFlashBack: this.editForm.get(['seFlashBack']).value,
      exceptKickerId: this.editForm.get(['exceptKickerId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGachaRenditionBeforeShootCutInPattern>>) {
    result.subscribe(
      (res: HttpResponse<IMGachaRenditionBeforeShootCutInPattern>) => this.onSaveSuccess(),
      (res: HttpErrorResponse) => this.onSaveError()
    );
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
