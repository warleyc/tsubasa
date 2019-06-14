import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMHomeBanner, MHomeBanner } from 'app/shared/model/m-home-banner.model';
import { MHomeBannerService } from './m-home-banner.service';

@Component({
  selector: 'jhi-m-home-banner-update',
  templateUrl: './m-home-banner-update.component.html'
})
export class MHomeBannerUpdateComponent implements OnInit {
  mHomeBanner: IMHomeBanner;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    bannerType: [null, [Validators.required]],
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
    protected mHomeBannerService: MHomeBannerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mHomeBanner }) => {
      this.updateForm(mHomeBanner);
      this.mHomeBanner = mHomeBanner;
    });
  }

  updateForm(mHomeBanner: IMHomeBanner) {
    this.editForm.patchValue({
      id: mHomeBanner.id,
      bannerType: mHomeBanner.bannerType,
      assetName: mHomeBanner.assetName,
      startAt: mHomeBanner.startAt,
      endAt: mHomeBanner.endAt,
      labelEndAt: mHomeBanner.labelEndAt,
      sceneTransition: mHomeBanner.sceneTransition,
      sceneTransitionParam: mHomeBanner.sceneTransitionParam,
      orderNum: mHomeBanner.orderNum,
      appealType: mHomeBanner.appealType,
      appealContentId: mHomeBanner.appealContentId
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
    const mHomeBanner = this.createFromForm();
    if (mHomeBanner.id !== undefined) {
      this.subscribeToSaveResponse(this.mHomeBannerService.update(mHomeBanner));
    } else {
      this.subscribeToSaveResponse(this.mHomeBannerService.create(mHomeBanner));
    }
  }

  private createFromForm(): IMHomeBanner {
    const entity = {
      ...new MHomeBanner(),
      id: this.editForm.get(['id']).value,
      bannerType: this.editForm.get(['bannerType']).value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMHomeBanner>>) {
    result.subscribe((res: HttpResponse<IMHomeBanner>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
