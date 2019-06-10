import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMDictionaryEn, MDictionaryEn } from 'app/shared/model/m-dictionary-en.model';
import { MDictionaryEnService } from './m-dictionary-en.service';

@Component({
  selector: 'jhi-m-dictionary-en-update',
  templateUrl: './m-dictionary-en-update.component.html'
})
export class MDictionaryEnUpdateComponent implements OnInit {
  mDictionaryEn: IMDictionaryEn;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    key: [null, [Validators.required]],
    message: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mDictionaryEnService: MDictionaryEnService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mDictionaryEn }) => {
      this.updateForm(mDictionaryEn);
      this.mDictionaryEn = mDictionaryEn;
    });
  }

  updateForm(mDictionaryEn: IMDictionaryEn) {
    this.editForm.patchValue({
      id: mDictionaryEn.id,
      key: mDictionaryEn.key,
      message: mDictionaryEn.message
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
    const mDictionaryEn = this.createFromForm();
    if (mDictionaryEn.id !== undefined) {
      this.subscribeToSaveResponse(this.mDictionaryEnService.update(mDictionaryEn));
    } else {
      this.subscribeToSaveResponse(this.mDictionaryEnService.create(mDictionaryEn));
    }
  }

  private createFromForm(): IMDictionaryEn {
    const entity = {
      ...new MDictionaryEn(),
      id: this.editForm.get(['id']).value,
      key: this.editForm.get(['key']).value,
      message: this.editForm.get(['message']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMDictionaryEn>>) {
    result.subscribe((res: HttpResponse<IMDictionaryEn>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
