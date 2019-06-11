import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import {
  IMDeckRarityConditionDescription,
  MDeckRarityConditionDescription
} from 'app/shared/model/m-deck-rarity-condition-description.model';
import { MDeckRarityConditionDescriptionService } from './m-deck-rarity-condition-description.service';

@Component({
  selector: 'jhi-m-deck-rarity-condition-description-update',
  templateUrl: './m-deck-rarity-condition-description-update.component.html'
})
export class MDeckRarityConditionDescriptionUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    rarityGroupId: [null, [Validators.required]],
    countType: [null, [Validators.required]],
    isStarting: [null, [Validators.required]],
    description: [null, [Validators.required]],
    iconName: [null, [Validators.required]],
    smallIconName: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mDeckRarityConditionDescriptionService: MDeckRarityConditionDescriptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mDeckRarityConditionDescription }) => {
      this.updateForm(mDeckRarityConditionDescription);
    });
  }

  updateForm(mDeckRarityConditionDescription: IMDeckRarityConditionDescription) {
    this.editForm.patchValue({
      id: mDeckRarityConditionDescription.id,
      rarityGroupId: mDeckRarityConditionDescription.rarityGroupId,
      countType: mDeckRarityConditionDescription.countType,
      isStarting: mDeckRarityConditionDescription.isStarting,
      description: mDeckRarityConditionDescription.description,
      iconName: mDeckRarityConditionDescription.iconName,
      smallIconName: mDeckRarityConditionDescription.smallIconName
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
    const mDeckRarityConditionDescription = this.createFromForm();
    if (mDeckRarityConditionDescription.id !== undefined) {
      this.subscribeToSaveResponse(this.mDeckRarityConditionDescriptionService.update(mDeckRarityConditionDescription));
    } else {
      this.subscribeToSaveResponse(this.mDeckRarityConditionDescriptionService.create(mDeckRarityConditionDescription));
    }
  }

  private createFromForm(): IMDeckRarityConditionDescription {
    const entity = {
      ...new MDeckRarityConditionDescription(),
      id: this.editForm.get(['id']).value,
      rarityGroupId: this.editForm.get(['rarityGroupId']).value,
      countType: this.editForm.get(['countType']).value,
      isStarting: this.editForm.get(['isStarting']).value,
      description: this.editForm.get(['description']).value,
      iconName: this.editForm.get(['iconName']).value,
      smallIconName: this.editForm.get(['smallIconName']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMDeckRarityConditionDescription>>) {
    result.subscribe(
      (res: HttpResponse<IMDeckRarityConditionDescription>) => this.onSaveSuccess(),
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
