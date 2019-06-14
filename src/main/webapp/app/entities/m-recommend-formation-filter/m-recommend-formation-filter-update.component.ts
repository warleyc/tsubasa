import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMRecommendFormationFilter, MRecommendFormationFilter } from 'app/shared/model/m-recommend-formation-filter.model';
import { MRecommendFormationFilterService } from './m-recommend-formation-filter.service';

@Component({
  selector: 'jhi-m-recommend-formation-filter-update',
  templateUrl: './m-recommend-formation-filter-update.component.html'
})
export class MRecommendFormationFilterUpdateComponent implements OnInit {
  mRecommendFormationFilter: IMRecommendFormationFilter;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    filterType: [null, [Validators.required]],
    name: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mRecommendFormationFilterService: MRecommendFormationFilterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mRecommendFormationFilter }) => {
      this.updateForm(mRecommendFormationFilter);
      this.mRecommendFormationFilter = mRecommendFormationFilter;
    });
  }

  updateForm(mRecommendFormationFilter: IMRecommendFormationFilter) {
    this.editForm.patchValue({
      id: mRecommendFormationFilter.id,
      filterType: mRecommendFormationFilter.filterType,
      name: mRecommendFormationFilter.name
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
    const mRecommendFormationFilter = this.createFromForm();
    if (mRecommendFormationFilter.id !== undefined) {
      this.subscribeToSaveResponse(this.mRecommendFormationFilterService.update(mRecommendFormationFilter));
    } else {
      this.subscribeToSaveResponse(this.mRecommendFormationFilterService.create(mRecommendFormationFilter));
    }
  }

  private createFromForm(): IMRecommendFormationFilter {
    const entity = {
      ...new MRecommendFormationFilter(),
      id: this.editForm.get(['id']).value,
      filterType: this.editForm.get(['filterType']).value,
      name: this.editForm.get(['name']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMRecommendFormationFilter>>) {
    result.subscribe(
      (res: HttpResponse<IMRecommendFormationFilter>) => this.onSaveSuccess(),
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
