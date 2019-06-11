import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMDistributeParamPoint, MDistributeParamPoint } from 'app/shared/model/m-distribute-param-point.model';
import { MDistributeParamPointService } from './m-distribute-param-point.service';

@Component({
  selector: 'jhi-m-distribute-param-point-update',
  templateUrl: './m-distribute-param-point-update.component.html'
})
export class MDistributeParamPointUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    targetAttribute: [null, [Validators.required]],
    targetNationalityGroupId: [null, [Validators.required]],
    name: [null, [Validators.required]],
    description: [null, [Validators.required]],
    thumbnailAssetName: [null, [Validators.required]],
    iconAssetName: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mDistributeParamPointService: MDistributeParamPointService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mDistributeParamPoint }) => {
      this.updateForm(mDistributeParamPoint);
    });
  }

  updateForm(mDistributeParamPoint: IMDistributeParamPoint) {
    this.editForm.patchValue({
      id: mDistributeParamPoint.id,
      targetAttribute: mDistributeParamPoint.targetAttribute,
      targetNationalityGroupId: mDistributeParamPoint.targetNationalityGroupId,
      name: mDistributeParamPoint.name,
      description: mDistributeParamPoint.description,
      thumbnailAssetName: mDistributeParamPoint.thumbnailAssetName,
      iconAssetName: mDistributeParamPoint.iconAssetName
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
    const mDistributeParamPoint = this.createFromForm();
    if (mDistributeParamPoint.id !== undefined) {
      this.subscribeToSaveResponse(this.mDistributeParamPointService.update(mDistributeParamPoint));
    } else {
      this.subscribeToSaveResponse(this.mDistributeParamPointService.create(mDistributeParamPoint));
    }
  }

  private createFromForm(): IMDistributeParamPoint {
    const entity = {
      ...new MDistributeParamPoint(),
      id: this.editForm.get(['id']).value,
      targetAttribute: this.editForm.get(['targetAttribute']).value,
      targetNationalityGroupId: this.editForm.get(['targetNationalityGroupId']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value,
      thumbnailAssetName: this.editForm.get(['thumbnailAssetName']).value,
      iconAssetName: this.editForm.get(['iconAssetName']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMDistributeParamPoint>>) {
    result.subscribe((res: HttpResponse<IMDistributeParamPoint>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
