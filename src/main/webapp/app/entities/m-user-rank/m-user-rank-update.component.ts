import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMUserRank, MUserRank } from 'app/shared/model/m-user-rank.model';
import { MUserRankService } from './m-user-rank.service';

@Component({
  selector: 'jhi-m-user-rank-update',
  templateUrl: './m-user-rank-update.component.html'
})
export class MUserRankUpdateComponent implements OnInit {
  mUserRank: IMUserRank;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    rank: [null, [Validators.required]],
    exp: [null, [Validators.required]],
    questAp: [null, [Validators.required]],
    maxFriendCount: [null, [Validators.required]],
    rankupSerif: [null, [Validators.required]],
    charaAssetName: [null, [Validators.required]],
    voiceCharaId: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mUserRankService: MUserRankService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mUserRank }) => {
      this.updateForm(mUserRank);
      this.mUserRank = mUserRank;
    });
  }

  updateForm(mUserRank: IMUserRank) {
    this.editForm.patchValue({
      id: mUserRank.id,
      rank: mUserRank.rank,
      exp: mUserRank.exp,
      questAp: mUserRank.questAp,
      maxFriendCount: mUserRank.maxFriendCount,
      rankupSerif: mUserRank.rankupSerif,
      charaAssetName: mUserRank.charaAssetName,
      voiceCharaId: mUserRank.voiceCharaId
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
    const mUserRank = this.createFromForm();
    if (mUserRank.id !== undefined) {
      this.subscribeToSaveResponse(this.mUserRankService.update(mUserRank));
    } else {
      this.subscribeToSaveResponse(this.mUserRankService.create(mUserRank));
    }
  }

  private createFromForm(): IMUserRank {
    const entity = {
      ...new MUserRank(),
      id: this.editForm.get(['id']).value,
      rank: this.editForm.get(['rank']).value,
      exp: this.editForm.get(['exp']).value,
      questAp: this.editForm.get(['questAp']).value,
      maxFriendCount: this.editForm.get(['maxFriendCount']).value,
      rankupSerif: this.editForm.get(['rankupSerif']).value,
      charaAssetName: this.editForm.get(['charaAssetName']).value,
      voiceCharaId: this.editForm.get(['voiceCharaId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMUserRank>>) {
    result.subscribe((res: HttpResponse<IMUserRank>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
