import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMTips, MTips } from 'app/shared/model/m-tips.model';
import { MTipsService } from './m-tips.service';

@Component({
  selector: 'jhi-m-tips-update',
  templateUrl: './m-tips-update.component.html'
})
export class MTipsUpdateComponent implements OnInit {
  mTips: IMTips;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    priority: [null, [Validators.required]],
    title: [null, [Validators.required]],
    description: [null, [Validators.required]],
    imageAssetName: [null, [Validators.required]],
    colorType: [null, [Validators.required]],
    isTips: [null, [Validators.required]],
    startAt: [null, [Validators.required]],
    endAt: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mTipsService: MTipsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTips }) => {
      this.updateForm(mTips);
      this.mTips = mTips;
    });
  }

  updateForm(mTips: IMTips) {
    this.editForm.patchValue({
      id: mTips.id,
      priority: mTips.priority,
      title: mTips.title,
      description: mTips.description,
      imageAssetName: mTips.imageAssetName,
      colorType: mTips.colorType,
      isTips: mTips.isTips,
      startAt: mTips.startAt,
      endAt: mTips.endAt
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
    const mTips = this.createFromForm();
    if (mTips.id !== undefined) {
      this.subscribeToSaveResponse(this.mTipsService.update(mTips));
    } else {
      this.subscribeToSaveResponse(this.mTipsService.create(mTips));
    }
  }

  private createFromForm(): IMTips {
    const entity = {
      ...new MTips(),
      id: this.editForm.get(['id']).value,
      priority: this.editForm.get(['priority']).value,
      title: this.editForm.get(['title']).value,
      description: this.editForm.get(['description']).value,
      imageAssetName: this.editForm.get(['imageAssetName']).value,
      colorType: this.editForm.get(['colorType']).value,
      isTips: this.editForm.get(['isTips']).value,
      startAt: this.editForm.get(['startAt']).value,
      endAt: this.editForm.get(['endAt']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTips>>) {
    result.subscribe((res: HttpResponse<IMTips>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
