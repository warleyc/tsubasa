import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMLoginBonusSerif, MLoginBonusSerif } from 'app/shared/model/m-login-bonus-serif.model';
import { MLoginBonusSerifService } from './m-login-bonus-serif.service';

@Component({
  selector: 'jhi-m-login-bonus-serif-update',
  templateUrl: './m-login-bonus-serif-update.component.html'
})
export class MLoginBonusSerifUpdateComponent implements OnInit {
  mLoginBonusSerif: IMLoginBonusSerif;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    serifId: [null, [Validators.required]],
    serif1: [],
    serif2: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mLoginBonusSerifService: MLoginBonusSerifService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mLoginBonusSerif }) => {
      this.updateForm(mLoginBonusSerif);
      this.mLoginBonusSerif = mLoginBonusSerif;
    });
  }

  updateForm(mLoginBonusSerif: IMLoginBonusSerif) {
    this.editForm.patchValue({
      id: mLoginBonusSerif.id,
      serifId: mLoginBonusSerif.serifId,
      serif1: mLoginBonusSerif.serif1,
      serif2: mLoginBonusSerif.serif2
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
    const mLoginBonusSerif = this.createFromForm();
    if (mLoginBonusSerif.id !== undefined) {
      this.subscribeToSaveResponse(this.mLoginBonusSerifService.update(mLoginBonusSerif));
    } else {
      this.subscribeToSaveResponse(this.mLoginBonusSerifService.create(mLoginBonusSerif));
    }
  }

  private createFromForm(): IMLoginBonusSerif {
    const entity = {
      ...new MLoginBonusSerif(),
      id: this.editForm.get(['id']).value,
      serifId: this.editForm.get(['serifId']).value,
      serif1: this.editForm.get(['serif1']).value,
      serif2: this.editForm.get(['serif2']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMLoginBonusSerif>>) {
    result.subscribe((res: HttpResponse<IMLoginBonusSerif>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
