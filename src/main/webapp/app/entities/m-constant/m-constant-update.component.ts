import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMConstant, MConstant } from 'app/shared/model/m-constant.model';
import { MConstantService } from './m-constant.service';

@Component({
  selector: 'jhi-m-constant-update',
  templateUrl: './m-constant-update.component.html'
})
export class MConstantUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    key: [null, [Validators.required]],
    value: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mConstantService: MConstantService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mConstant }) => {
      this.updateForm(mConstant);
    });
  }

  updateForm(mConstant: IMConstant) {
    this.editForm.patchValue({
      id: mConstant.id,
      key: mConstant.key,
      value: mConstant.value
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
    const mConstant = this.createFromForm();
    if (mConstant.id !== undefined) {
      this.subscribeToSaveResponse(this.mConstantService.update(mConstant));
    } else {
      this.subscribeToSaveResponse(this.mConstantService.create(mConstant));
    }
  }

  private createFromForm(): IMConstant {
    const entity = {
      ...new MConstant(),
      id: this.editForm.get(['id']).value,
      key: this.editForm.get(['key']).value,
      value: this.editForm.get(['value']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMConstant>>) {
    result.subscribe((res: HttpResponse<IMConstant>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
