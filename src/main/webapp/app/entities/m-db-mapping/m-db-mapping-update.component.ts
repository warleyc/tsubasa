import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMDbMapping, MDbMapping } from 'app/shared/model/m-db-mapping.model';
import { MDbMappingService } from './m-db-mapping.service';

@Component({
  selector: 'jhi-m-db-mapping-update',
  templateUrl: './m-db-mapping-update.component.html'
})
export class MDbMappingUpdateComponent implements OnInit {
  mDbMapping: IMDbMapping;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    fileName: [null, [Validators.required]],
    dbName: [null, [Validators.required]],
    path: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mDbMappingService: MDbMappingService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mDbMapping }) => {
      this.updateForm(mDbMapping);
      this.mDbMapping = mDbMapping;
    });
  }

  updateForm(mDbMapping: IMDbMapping) {
    this.editForm.patchValue({
      id: mDbMapping.id,
      fileName: mDbMapping.fileName,
      dbName: mDbMapping.dbName,
      path: mDbMapping.path
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
    const mDbMapping = this.createFromForm();
    if (mDbMapping.id !== undefined) {
      this.subscribeToSaveResponse(this.mDbMappingService.update(mDbMapping));
    } else {
      this.subscribeToSaveResponse(this.mDbMappingService.create(mDbMapping));
    }
  }

  private createFromForm(): IMDbMapping {
    const entity = {
      ...new MDbMapping(),
      id: this.editForm.get(['id']).value,
      fileName: this.editForm.get(['fileName']).value,
      dbName: this.editForm.get(['dbName']).value,
      path: this.editForm.get(['path']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMDbMapping>>) {
    result.subscribe((res: HttpResponse<IMDbMapping>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
