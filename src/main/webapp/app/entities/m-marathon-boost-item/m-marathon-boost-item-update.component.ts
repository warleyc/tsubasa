import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMMarathonBoostItem, MMarathonBoostItem } from 'app/shared/model/m-marathon-boost-item.model';
import { MMarathonBoostItemService } from './m-marathon-boost-item.service';

@Component({
  selector: 'jhi-m-marathon-boost-item-update',
  templateUrl: './m-marathon-boost-item-update.component.html'
})
export class MMarathonBoostItemUpdateComponent implements OnInit {
  mMarathonBoostItem: IMMarathonBoostItem;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    eventId: [],
    boostRatio: [null, [Validators.required]],
    name: [null, [Validators.required]],
    shortName: [null, [Validators.required]],
    thumbnailAssetName: [null, [Validators.required]],
    description: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mMarathonBoostItemService: MMarathonBoostItemService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMarathonBoostItem }) => {
      this.updateForm(mMarathonBoostItem);
      this.mMarathonBoostItem = mMarathonBoostItem;
    });
  }

  updateForm(mMarathonBoostItem: IMMarathonBoostItem) {
    this.editForm.patchValue({
      id: mMarathonBoostItem.id,
      eventId: mMarathonBoostItem.eventId,
      boostRatio: mMarathonBoostItem.boostRatio,
      name: mMarathonBoostItem.name,
      shortName: mMarathonBoostItem.shortName,
      thumbnailAssetName: mMarathonBoostItem.thumbnailAssetName,
      description: mMarathonBoostItem.description
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
    const mMarathonBoostItem = this.createFromForm();
    if (mMarathonBoostItem.id !== undefined) {
      this.subscribeToSaveResponse(this.mMarathonBoostItemService.update(mMarathonBoostItem));
    } else {
      this.subscribeToSaveResponse(this.mMarathonBoostItemService.create(mMarathonBoostItem));
    }
  }

  private createFromForm(): IMMarathonBoostItem {
    const entity = {
      ...new MMarathonBoostItem(),
      id: this.editForm.get(['id']).value,
      eventId: this.editForm.get(['eventId']).value,
      boostRatio: this.editForm.get(['boostRatio']).value,
      name: this.editForm.get(['name']).value,
      shortName: this.editForm.get(['shortName']).value,
      thumbnailAssetName: this.editForm.get(['thumbnailAssetName']).value,
      description: this.editForm.get(['description']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMarathonBoostItem>>) {
    result.subscribe((res: HttpResponse<IMMarathonBoostItem>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
