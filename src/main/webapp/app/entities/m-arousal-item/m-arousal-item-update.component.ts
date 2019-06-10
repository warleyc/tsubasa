import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMArousalItem, MArousalItem } from 'app/shared/model/m-arousal-item.model';
import { MArousalItemService } from './m-arousal-item.service';

@Component({
  selector: 'jhi-m-arousal-item-update',
  templateUrl: './m-arousal-item-update.component.html'
})
export class MArousalItemUpdateComponent implements OnInit {
  mArousalItem: IMArousalItem;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    description: [null, [Validators.required]],
    thumbnailAssetName: [null, [Validators.required]],
    thumbnailBgAssetName: [null, [Validators.required]],
    thumbnailFrameAssetName: [null, [Validators.required]],
    arousalItemType: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mArousalItemService: MArousalItemService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mArousalItem }) => {
      this.updateForm(mArousalItem);
      this.mArousalItem = mArousalItem;
    });
  }

  updateForm(mArousalItem: IMArousalItem) {
    this.editForm.patchValue({
      id: mArousalItem.id,
      name: mArousalItem.name,
      description: mArousalItem.description,
      thumbnailAssetName: mArousalItem.thumbnailAssetName,
      thumbnailBgAssetName: mArousalItem.thumbnailBgAssetName,
      thumbnailFrameAssetName: mArousalItem.thumbnailFrameAssetName,
      arousalItemType: mArousalItem.arousalItemType
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
    const mArousalItem = this.createFromForm();
    if (mArousalItem.id !== undefined) {
      this.subscribeToSaveResponse(this.mArousalItemService.update(mArousalItem));
    } else {
      this.subscribeToSaveResponse(this.mArousalItemService.create(mArousalItem));
    }
  }

  private createFromForm(): IMArousalItem {
    const entity = {
      ...new MArousalItem(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value,
      thumbnailAssetName: this.editForm.get(['thumbnailAssetName']).value,
      thumbnailBgAssetName: this.editForm.get(['thumbnailBgAssetName']).value,
      thumbnailFrameAssetName: this.editForm.get(['thumbnailFrameAssetName']).value,
      arousalItemType: this.editForm.get(['arousalItemType']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMArousalItem>>) {
    result.subscribe((res: HttpResponse<IMArousalItem>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
