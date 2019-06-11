import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMDictionaryFr, MDictionaryFr } from 'app/shared/model/m-dictionary-fr.model';
import { MDictionaryFrService } from './m-dictionary-fr.service';

@Component({
  selector: 'jhi-m-dictionary-fr-update',
  templateUrl: './m-dictionary-fr-update.component.html'
})
export class MDictionaryFrUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    key: [null, [Validators.required]],
    message: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mDictionaryFrService: MDictionaryFrService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mDictionaryFr }) => {
      this.updateForm(mDictionaryFr);
    });
  }

  updateForm(mDictionaryFr: IMDictionaryFr) {
    this.editForm.patchValue({
      id: mDictionaryFr.id,
      key: mDictionaryFr.key,
      message: mDictionaryFr.message
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
    const mDictionaryFr = this.createFromForm();
    if (mDictionaryFr.id !== undefined) {
      this.subscribeToSaveResponse(this.mDictionaryFrService.update(mDictionaryFr));
    } else {
      this.subscribeToSaveResponse(this.mDictionaryFrService.create(mDictionaryFr));
    }
  }

  private createFromForm(): IMDictionaryFr {
    const entity = {
      ...new MDictionaryFr(),
      id: this.editForm.get(['id']).value,
      key: this.editForm.get(['key']).value,
      message: this.editForm.get(['message']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMDictionaryFr>>) {
    result.subscribe((res: HttpResponse<IMDictionaryFr>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
