import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMAssetTagMapping, MAssetTagMapping } from 'app/shared/model/m-asset-tag-mapping.model';
import { MAssetTagMappingService } from './m-asset-tag-mapping.service';

@Component({
  selector: 'jhi-m-asset-tag-mapping-update',
  templateUrl: './m-asset-tag-mapping-update.component.html'
})
export class MAssetTagMappingUpdateComponent implements OnInit {
  mAssetTagMapping: IMAssetTagMapping;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    tag: [null, [Validators.required]],
    ids: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mAssetTagMappingService: MAssetTagMappingService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mAssetTagMapping }) => {
      this.updateForm(mAssetTagMapping);
      this.mAssetTagMapping = mAssetTagMapping;
    });
  }

  updateForm(mAssetTagMapping: IMAssetTagMapping) {
    this.editForm.patchValue({
      id: mAssetTagMapping.id,
      tag: mAssetTagMapping.tag,
      ids: mAssetTagMapping.ids
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
    const mAssetTagMapping = this.createFromForm();
    if (mAssetTagMapping.id !== undefined) {
      this.subscribeToSaveResponse(this.mAssetTagMappingService.update(mAssetTagMapping));
    } else {
      this.subscribeToSaveResponse(this.mAssetTagMappingService.create(mAssetTagMapping));
    }
  }

  private createFromForm(): IMAssetTagMapping {
    const entity = {
      ...new MAssetTagMapping(),
      id: this.editForm.get(['id']).value,
      tag: this.editForm.get(['tag']).value,
      ids: this.editForm.get(['ids']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMAssetTagMapping>>) {
    result.subscribe((res: HttpResponse<IMAssetTagMapping>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
