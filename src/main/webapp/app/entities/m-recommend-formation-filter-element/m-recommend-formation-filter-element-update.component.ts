import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import {
  IMRecommendFormationFilterElement,
  MRecommendFormationFilterElement
} from 'app/shared/model/m-recommend-formation-filter-element.model';
import { MRecommendFormationFilterElementService } from './m-recommend-formation-filter-element.service';

@Component({
  selector: 'jhi-m-recommend-formation-filter-element-update',
  templateUrl: './m-recommend-formation-filter-element-update.component.html'
})
export class MRecommendFormationFilterElementUpdateComponent implements OnInit {
  mRecommendFormationFilterElement: IMRecommendFormationFilterElement;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    filterType: [null, [Validators.required]],
    elementId: [null, [Validators.required]],
    name: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mRecommendFormationFilterElementService: MRecommendFormationFilterElementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mRecommendFormationFilterElement }) => {
      this.updateForm(mRecommendFormationFilterElement);
      this.mRecommendFormationFilterElement = mRecommendFormationFilterElement;
    });
  }

  updateForm(mRecommendFormationFilterElement: IMRecommendFormationFilterElement) {
    this.editForm.patchValue({
      id: mRecommendFormationFilterElement.id,
      filterType: mRecommendFormationFilterElement.filterType,
      elementId: mRecommendFormationFilterElement.elementId,
      name: mRecommendFormationFilterElement.name
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
    const mRecommendFormationFilterElement = this.createFromForm();
    if (mRecommendFormationFilterElement.id !== undefined) {
      this.subscribeToSaveResponse(this.mRecommendFormationFilterElementService.update(mRecommendFormationFilterElement));
    } else {
      this.subscribeToSaveResponse(this.mRecommendFormationFilterElementService.create(mRecommendFormationFilterElement));
    }
  }

  private createFromForm(): IMRecommendFormationFilterElement {
    const entity = {
      ...new MRecommendFormationFilterElement(),
      id: this.editForm.get(['id']).value,
      filterType: this.editForm.get(['filterType']).value,
      elementId: this.editForm.get(['elementId']).value,
      name: this.editForm.get(['name']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMRecommendFormationFilterElement>>) {
    result.subscribe(
      (res: HttpResponse<IMRecommendFormationFilterElement>) => this.onSaveSuccess(),
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
