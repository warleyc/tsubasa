import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMLoginBonusRound, MLoginBonusRound } from 'app/shared/model/m-login-bonus-round.model';
import { MLoginBonusRoundService } from './m-login-bonus-round.service';

@Component({
  selector: 'jhi-m-login-bonus-round-update',
  templateUrl: './m-login-bonus-round-update.component.html'
})
export class MLoginBonusRoundUpdateComponent implements OnInit {
  mLoginBonusRound: IMLoginBonusRound;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    roundId: [null, [Validators.required]],
    bonusType: [null, [Validators.required]],
    startAt: [null, [Validators.required]],
    endAt: [null, [Validators.required]],
    serifSanae: [null, [Validators.required]],
    serifYayoi: [null, [Validators.required]],
    serifYoshiko: [null, [Validators.required]],
    serifMaki: [null, [Validators.required]],
    sanaeImage: [],
    yayoiImage: [],
    yoshikoImage: [],
    makiImage: [],
    soundSwitchEventName: [null, [Validators.required]],
    nextId: [],
    lastDayAppealComment: [],
    backgroundImage: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mLoginBonusRoundService: MLoginBonusRoundService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mLoginBonusRound }) => {
      this.updateForm(mLoginBonusRound);
      this.mLoginBonusRound = mLoginBonusRound;
    });
  }

  updateForm(mLoginBonusRound: IMLoginBonusRound) {
    this.editForm.patchValue({
      id: mLoginBonusRound.id,
      roundId: mLoginBonusRound.roundId,
      bonusType: mLoginBonusRound.bonusType,
      startAt: mLoginBonusRound.startAt,
      endAt: mLoginBonusRound.endAt,
      serifSanae: mLoginBonusRound.serifSanae,
      serifYayoi: mLoginBonusRound.serifYayoi,
      serifYoshiko: mLoginBonusRound.serifYoshiko,
      serifMaki: mLoginBonusRound.serifMaki,
      sanaeImage: mLoginBonusRound.sanaeImage,
      yayoiImage: mLoginBonusRound.yayoiImage,
      yoshikoImage: mLoginBonusRound.yoshikoImage,
      makiImage: mLoginBonusRound.makiImage,
      soundSwitchEventName: mLoginBonusRound.soundSwitchEventName,
      nextId: mLoginBonusRound.nextId,
      lastDayAppealComment: mLoginBonusRound.lastDayAppealComment,
      backgroundImage: mLoginBonusRound.backgroundImage
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
    const mLoginBonusRound = this.createFromForm();
    if (mLoginBonusRound.id !== undefined) {
      this.subscribeToSaveResponse(this.mLoginBonusRoundService.update(mLoginBonusRound));
    } else {
      this.subscribeToSaveResponse(this.mLoginBonusRoundService.create(mLoginBonusRound));
    }
  }

  private createFromForm(): IMLoginBonusRound {
    const entity = {
      ...new MLoginBonusRound(),
      id: this.editForm.get(['id']).value,
      roundId: this.editForm.get(['roundId']).value,
      bonusType: this.editForm.get(['bonusType']).value,
      startAt: this.editForm.get(['startAt']).value,
      endAt: this.editForm.get(['endAt']).value,
      serifSanae: this.editForm.get(['serifSanae']).value,
      serifYayoi: this.editForm.get(['serifYayoi']).value,
      serifYoshiko: this.editForm.get(['serifYoshiko']).value,
      serifMaki: this.editForm.get(['serifMaki']).value,
      sanaeImage: this.editForm.get(['sanaeImage']).value,
      yayoiImage: this.editForm.get(['yayoiImage']).value,
      yoshikoImage: this.editForm.get(['yoshikoImage']).value,
      makiImage: this.editForm.get(['makiImage']).value,
      soundSwitchEventName: this.editForm.get(['soundSwitchEventName']).value,
      nextId: this.editForm.get(['nextId']).value,
      lastDayAppealComment: this.editForm.get(['lastDayAppealComment']).value,
      backgroundImage: this.editForm.get(['backgroundImage']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMLoginBonusRound>>) {
    result.subscribe((res: HttpResponse<IMLoginBonusRound>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
