import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMMasterVersion, MMasterVersion } from 'app/shared/model/m-master-version.model';
import { MMasterVersionService } from './m-master-version.service';

@Component({
  selector: 'jhi-m-master-version-update',
  templateUrl: './m-master-version-update.component.html'
})
export class MMasterVersionUpdateComponent implements OnInit {
  mMasterVersion: IMMasterVersion;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    version: [null, [Validators.required]],
    path: [],
    size: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mMasterVersionService: MMasterVersionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMasterVersion }) => {
      this.updateForm(mMasterVersion);
      this.mMasterVersion = mMasterVersion;
    });
  }

  updateForm(mMasterVersion: IMMasterVersion) {
    this.editForm.patchValue({
      id: mMasterVersion.id,
      name: mMasterVersion.name,
      version: mMasterVersion.version,
      path: mMasterVersion.path,
      size: mMasterVersion.size
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
    const mMasterVersion = this.createFromForm();
    if (mMasterVersion.id !== undefined) {
      this.subscribeToSaveResponse(this.mMasterVersionService.update(mMasterVersion));
    } else {
      this.subscribeToSaveResponse(this.mMasterVersionService.create(mMasterVersion));
    }
  }

  private createFromForm(): IMMasterVersion {
    const entity = {
      ...new MMasterVersion(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      version: this.editForm.get(['version']).value,
      path: this.editForm.get(['path']).value,
      size: this.editForm.get(['size']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMasterVersion>>) {
    result.subscribe((res: HttpResponse<IMMasterVersion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
