import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMApRecoveryItem, MApRecoveryItem } from 'app/shared/model/m-ap-recovery-item.model';
import { MApRecoveryItemService } from './m-ap-recovery-item.service';

@Component({
  selector: 'jhi-m-ap-recovery-item-update',
  templateUrl: './m-ap-recovery-item-update.component.html'
})
export class MApRecoveryItemUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    apRecoveryItemType: [null, [Validators.required]],
    name: [null, [Validators.required]],
    description: [null, [Validators.required]],
    thumbnailAssetName: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mApRecoveryItemService: MApRecoveryItemService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mApRecoveryItem }) => {
      this.updateForm(mApRecoveryItem);
    });
  }

  updateForm(mApRecoveryItem: IMApRecoveryItem) {
    this.editForm.patchValue({
      id: mApRecoveryItem.id,
      apRecoveryItemType: mApRecoveryItem.apRecoveryItemType,
      name: mApRecoveryItem.name,
      description: mApRecoveryItem.description,
      thumbnailAssetName: mApRecoveryItem.thumbnailAssetName
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
    const mApRecoveryItem = this.createFromForm();
    if (mApRecoveryItem.id !== undefined) {
      this.subscribeToSaveResponse(this.mApRecoveryItemService.update(mApRecoveryItem));
    } else {
      this.subscribeToSaveResponse(this.mApRecoveryItemService.create(mApRecoveryItem));
    }
  }

  private createFromForm(): IMApRecoveryItem {
    const entity = {
      ...new MApRecoveryItem(),
      id: this.editForm.get(['id']).value,
      apRecoveryItemType: this.editForm.get(['apRecoveryItemType']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value,
      thumbnailAssetName: this.editForm.get(['thumbnailAssetName']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMApRecoveryItem>>) {
    result.subscribe((res: HttpResponse<IMApRecoveryItem>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
