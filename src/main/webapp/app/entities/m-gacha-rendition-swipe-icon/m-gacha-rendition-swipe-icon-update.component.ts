import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMGachaRenditionSwipeIcon, MGachaRenditionSwipeIcon } from 'app/shared/model/m-gacha-rendition-swipe-icon.model';
import { MGachaRenditionSwipeIconService } from './m-gacha-rendition-swipe-icon.service';

@Component({
  selector: 'jhi-m-gacha-rendition-swipe-icon-update',
  templateUrl: './m-gacha-rendition-swipe-icon-update.component.html'
})
export class MGachaRenditionSwipeIconUpdateComponent implements OnInit {
  mGachaRenditionSwipeIcon: IMGachaRenditionSwipeIcon;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    isSsr: [null, [Validators.required]],
    isROrLess: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    swipeIconPrefabName: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGachaRenditionSwipeIconService: MGachaRenditionSwipeIconService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGachaRenditionSwipeIcon }) => {
      this.updateForm(mGachaRenditionSwipeIcon);
      this.mGachaRenditionSwipeIcon = mGachaRenditionSwipeIcon;
    });
  }

  updateForm(mGachaRenditionSwipeIcon: IMGachaRenditionSwipeIcon) {
    this.editForm.patchValue({
      id: mGachaRenditionSwipeIcon.id,
      isSsr: mGachaRenditionSwipeIcon.isSsr,
      isROrLess: mGachaRenditionSwipeIcon.isROrLess,
      weight: mGachaRenditionSwipeIcon.weight,
      swipeIconPrefabName: mGachaRenditionSwipeIcon.swipeIconPrefabName
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
    const mGachaRenditionSwipeIcon = this.createFromForm();
    if (mGachaRenditionSwipeIcon.id !== undefined) {
      this.subscribeToSaveResponse(this.mGachaRenditionSwipeIconService.update(mGachaRenditionSwipeIcon));
    } else {
      this.subscribeToSaveResponse(this.mGachaRenditionSwipeIconService.create(mGachaRenditionSwipeIcon));
    }
  }

  private createFromForm(): IMGachaRenditionSwipeIcon {
    const entity = {
      ...new MGachaRenditionSwipeIcon(),
      id: this.editForm.get(['id']).value,
      isSsr: this.editForm.get(['isSsr']).value,
      isROrLess: this.editForm.get(['isROrLess']).value,
      weight: this.editForm.get(['weight']).value,
      swipeIconPrefabName: this.editForm.get(['swipeIconPrefabName']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGachaRenditionSwipeIcon>>) {
    result.subscribe(
      (res: HttpResponse<IMGachaRenditionSwipeIcon>) => this.onSaveSuccess(),
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
