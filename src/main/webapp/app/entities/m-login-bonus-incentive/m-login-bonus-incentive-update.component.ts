import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMLoginBonusIncentive, MLoginBonusIncentive } from 'app/shared/model/m-login-bonus-incentive.model';
import { MLoginBonusIncentiveService } from './m-login-bonus-incentive.service';

@Component({
  selector: 'jhi-m-login-bonus-incentive-update',
  templateUrl: './m-login-bonus-incentive-update.component.html'
})
export class MLoginBonusIncentiveUpdateComponent implements OnInit {
  mLoginBonusIncentive: IMLoginBonusIncentive;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    roundId: [null, [Validators.required]],
    day: [null, [Validators.required]],
    contentType: [null, [Validators.required]],
    contentId: [],
    contentAmount: [null, [Validators.required]],
    presentDetail: [null, [Validators.required]],
    isDecorated: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mLoginBonusIncentiveService: MLoginBonusIncentiveService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mLoginBonusIncentive }) => {
      this.updateForm(mLoginBonusIncentive);
      this.mLoginBonusIncentive = mLoginBonusIncentive;
    });
  }

  updateForm(mLoginBonusIncentive: IMLoginBonusIncentive) {
    this.editForm.patchValue({
      id: mLoginBonusIncentive.id,
      roundId: mLoginBonusIncentive.roundId,
      day: mLoginBonusIncentive.day,
      contentType: mLoginBonusIncentive.contentType,
      contentId: mLoginBonusIncentive.contentId,
      contentAmount: mLoginBonusIncentive.contentAmount,
      presentDetail: mLoginBonusIncentive.presentDetail,
      isDecorated: mLoginBonusIncentive.isDecorated
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
    const mLoginBonusIncentive = this.createFromForm();
    if (mLoginBonusIncentive.id !== undefined) {
      this.subscribeToSaveResponse(this.mLoginBonusIncentiveService.update(mLoginBonusIncentive));
    } else {
      this.subscribeToSaveResponse(this.mLoginBonusIncentiveService.create(mLoginBonusIncentive));
    }
  }

  private createFromForm(): IMLoginBonusIncentive {
    const entity = {
      ...new MLoginBonusIncentive(),
      id: this.editForm.get(['id']).value,
      roundId: this.editForm.get(['roundId']).value,
      day: this.editForm.get(['day']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value,
      presentDetail: this.editForm.get(['presentDetail']).value,
      isDecorated: this.editForm.get(['isDecorated']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMLoginBonusIncentive>>) {
    result.subscribe((res: HttpResponse<IMLoginBonusIncentive>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
