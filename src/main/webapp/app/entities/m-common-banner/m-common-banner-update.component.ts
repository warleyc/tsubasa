import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMCommonBanner, MCommonBanner } from 'app/shared/model/m-common-banner.model';
import { MCommonBannerService } from './m-common-banner.service';

@Component({
  selector: 'jhi-m-common-banner-update',
  templateUrl: './m-common-banner-update.component.html'
})
export class MCommonBannerUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    assetName: [null, [Validators.required]],
    startAt: [null, [Validators.required]],
    endAt: [null, [Validators.required]],
    labelEndAt: [null, [Validators.required]],
    sceneTransition: [null, [Validators.required]],
    sceneTransitionParam: [],
    orderNum: [null, [Validators.required]],
    appealType: [],
    appealContentId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mCommonBannerService: MCommonBannerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mCommonBanner }) => {
      this.updateForm(mCommonBanner);
    });
  }

  updateForm(mCommonBanner: IMCommonBanner) {
    this.editForm.patchValue({
      id: mCommonBanner.id,
      assetName: mCommonBanner.assetName,
      startAt: mCommonBanner.startAt,
      endAt: mCommonBanner.endAt,
      labelEndAt: mCommonBanner.labelEndAt,
      sceneTransition: mCommonBanner.sceneTransition,
      sceneTransitionParam: mCommonBanner.sceneTransitionParam,
      orderNum: mCommonBanner.orderNum,
      appealType: mCommonBanner.appealType,
      appealContentId: mCommonBanner.appealContentId
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
    const mCommonBanner = this.createFromForm();
    if (mCommonBanner.id !== undefined) {
      this.subscribeToSaveResponse(this.mCommonBannerService.update(mCommonBanner));
    } else {
      this.subscribeToSaveResponse(this.mCommonBannerService.create(mCommonBanner));
    }
  }

  private createFromForm(): IMCommonBanner {
    const entity = {
      ...new MCommonBanner(),
      id: this.editForm.get(['id']).value,
      assetName: this.editForm.get(['assetName']).value,
      startAt: this.editForm.get(['startAt']).value,
      endAt: this.editForm.get(['endAt']).value,
      labelEndAt: this.editForm.get(['labelEndAt']).value,
      sceneTransition: this.editForm.get(['sceneTransition']).value,
      sceneTransitionParam: this.editForm.get(['sceneTransitionParam']).value,
      orderNum: this.editForm.get(['orderNum']).value,
      appealType: this.editForm.get(['appealType']).value,
      appealContentId: this.editForm.get(['appealContentId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMCommonBanner>>) {
    result.subscribe((res: HttpResponse<IMCommonBanner>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
