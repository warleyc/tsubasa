import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMSoundBank, MSoundBank } from 'app/shared/model/m-sound-bank.model';
import { MSoundBankService } from './m-sound-bank.service';

@Component({
  selector: 'jhi-m-sound-bank-update',
  templateUrl: './m-sound-bank-update.component.html'
})
export class MSoundBankUpdateComponent implements OnInit {
  mSoundBank: IMSoundBank;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    path: [null, [Validators.required]],
    pf: [null, [Validators.required]],
    version: [null, [Validators.required]],
    fileSize: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mSoundBankService: MSoundBankService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mSoundBank }) => {
      this.updateForm(mSoundBank);
      this.mSoundBank = mSoundBank;
    });
  }

  updateForm(mSoundBank: IMSoundBank) {
    this.editForm.patchValue({
      id: mSoundBank.id,
      path: mSoundBank.path,
      pf: mSoundBank.pf,
      version: mSoundBank.version,
      fileSize: mSoundBank.fileSize
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
    const mSoundBank = this.createFromForm();
    if (mSoundBank.id !== undefined) {
      this.subscribeToSaveResponse(this.mSoundBankService.update(mSoundBank));
    } else {
      this.subscribeToSaveResponse(this.mSoundBankService.create(mSoundBank));
    }
  }

  private createFromForm(): IMSoundBank {
    const entity = {
      ...new MSoundBank(),
      id: this.editForm.get(['id']).value,
      path: this.editForm.get(['path']).value,
      pf: this.editForm.get(['pf']).value,
      version: this.editForm.get(['version']).value,
      fileSize: this.editForm.get(['fileSize']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMSoundBank>>) {
    result.subscribe((res: HttpResponse<IMSoundBank>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
