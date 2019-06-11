import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMDictionaryJa, MDictionaryJa } from 'app/shared/model/m-dictionary-ja.model';
import { MDictionaryJaService } from './m-dictionary-ja.service';

@Component({
  selector: 'jhi-m-dictionary-ja-update',
  templateUrl: './m-dictionary-ja-update.component.html'
})
export class MDictionaryJaUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    key: [null, [Validators.required]],
    message: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mDictionaryJaService: MDictionaryJaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mDictionaryJa }) => {
      this.updateForm(mDictionaryJa);
    });
  }

  updateForm(mDictionaryJa: IMDictionaryJa) {
    this.editForm.patchValue({
      id: mDictionaryJa.id,
      key: mDictionaryJa.key,
      message: mDictionaryJa.message
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
    const mDictionaryJa = this.createFromForm();
    if (mDictionaryJa.id !== undefined) {
      this.subscribeToSaveResponse(this.mDictionaryJaService.update(mDictionaryJa));
    } else {
      this.subscribeToSaveResponse(this.mDictionaryJaService.create(mDictionaryJa));
    }
  }

  private createFromForm(): IMDictionaryJa {
    const entity = {
      ...new MDictionaryJa(),
      id: this.editForm.get(['id']).value,
      key: this.editForm.get(['key']).value,
      message: this.editForm.get(['message']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMDictionaryJa>>) {
    result.subscribe((res: HttpResponse<IMDictionaryJa>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
