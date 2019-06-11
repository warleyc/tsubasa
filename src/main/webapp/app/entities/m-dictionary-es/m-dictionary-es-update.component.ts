import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMDictionaryEs, MDictionaryEs } from 'app/shared/model/m-dictionary-es.model';
import { MDictionaryEsService } from './m-dictionary-es.service';

@Component({
  selector: 'jhi-m-dictionary-es-update',
  templateUrl: './m-dictionary-es-update.component.html'
})
export class MDictionaryEsUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    key: [null, [Validators.required]],
    message: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mDictionaryEsService: MDictionaryEsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mDictionaryEs }) => {
      this.updateForm(mDictionaryEs);
    });
  }

  updateForm(mDictionaryEs: IMDictionaryEs) {
    this.editForm.patchValue({
      id: mDictionaryEs.id,
      key: mDictionaryEs.key,
      message: mDictionaryEs.message
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
    const mDictionaryEs = this.createFromForm();
    if (mDictionaryEs.id !== undefined) {
      this.subscribeToSaveResponse(this.mDictionaryEsService.update(mDictionaryEs));
    } else {
      this.subscribeToSaveResponse(this.mDictionaryEsService.create(mDictionaryEs));
    }
  }

  private createFromForm(): IMDictionaryEs {
    const entity = {
      ...new MDictionaryEs(),
      id: this.editForm.get(['id']).value,
      key: this.editForm.get(['key']).value,
      message: this.editForm.get(['message']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMDictionaryEs>>) {
    result.subscribe((res: HttpResponse<IMDictionaryEs>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
