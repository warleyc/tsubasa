import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMGachaRendition, MGachaRendition } from 'app/shared/model/m-gacha-rendition.model';
import { MGachaRenditionService } from './m-gacha-rendition.service';

@Component({
  selector: 'jhi-m-gacha-rendition-update',
  templateUrl: './m-gacha-rendition-update.component.html'
})
export class MGachaRenditionUpdateComponent implements OnInit {
  mGachaRendition: IMGachaRendition;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    mainPrefabName: [null, [Validators.required]],
    resultExpectedUpPrefabName: [null, [Validators.required]],
    resultQuestionPrefabName: [],
    soundSwitchEventName: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGachaRenditionService: MGachaRenditionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGachaRendition }) => {
      this.updateForm(mGachaRendition);
      this.mGachaRendition = mGachaRendition;
    });
  }

  updateForm(mGachaRendition: IMGachaRendition) {
    this.editForm.patchValue({
      id: mGachaRendition.id,
      mainPrefabName: mGachaRendition.mainPrefabName,
      resultExpectedUpPrefabName: mGachaRendition.resultExpectedUpPrefabName,
      resultQuestionPrefabName: mGachaRendition.resultQuestionPrefabName,
      soundSwitchEventName: mGachaRendition.soundSwitchEventName
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
    const mGachaRendition = this.createFromForm();
    if (mGachaRendition.id !== undefined) {
      this.subscribeToSaveResponse(this.mGachaRenditionService.update(mGachaRendition));
    } else {
      this.subscribeToSaveResponse(this.mGachaRenditionService.create(mGachaRendition));
    }
  }

  private createFromForm(): IMGachaRendition {
    const entity = {
      ...new MGachaRendition(),
      id: this.editForm.get(['id']).value,
      mainPrefabName: this.editForm.get(['mainPrefabName']).value,
      resultExpectedUpPrefabName: this.editForm.get(['resultExpectedUpPrefabName']).value,
      resultQuestionPrefabName: this.editForm.get(['resultQuestionPrefabName']).value,
      soundSwitchEventName: this.editForm.get(['soundSwitchEventName']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGachaRendition>>) {
    result.subscribe((res: HttpResponse<IMGachaRendition>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
