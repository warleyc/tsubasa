import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMQuestGoalReward, MQuestGoalReward } from 'app/shared/model/m-quest-goal-reward.model';
import { MQuestGoalRewardService } from './m-quest-goal-reward.service';

@Component({
  selector: 'jhi-m-quest-goal-reward-update',
  templateUrl: './m-quest-goal-reward-update.component.html'
})
export class MQuestGoalRewardUpdateComponent implements OnInit {
  mQuestGoalReward: IMQuestGoalReward;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    assetName: [null, [Validators.required]],
    contentType: [null, [Validators.required]],
    contentId: [],
    contentAmount: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mQuestGoalRewardService: MQuestGoalRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mQuestGoalReward }) => {
      this.updateForm(mQuestGoalReward);
      this.mQuestGoalReward = mQuestGoalReward;
    });
  }

  updateForm(mQuestGoalReward: IMQuestGoalReward) {
    this.editForm.patchValue({
      id: mQuestGoalReward.id,
      groupId: mQuestGoalReward.groupId,
      weight: mQuestGoalReward.weight,
      assetName: mQuestGoalReward.assetName,
      contentType: mQuestGoalReward.contentType,
      contentId: mQuestGoalReward.contentId,
      contentAmount: mQuestGoalReward.contentAmount
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
    const mQuestGoalReward = this.createFromForm();
    if (mQuestGoalReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mQuestGoalRewardService.update(mQuestGoalReward));
    } else {
      this.subscribeToSaveResponse(this.mQuestGoalRewardService.create(mQuestGoalReward));
    }
  }

  private createFromForm(): IMQuestGoalReward {
    const entity = {
      ...new MQuestGoalReward(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      weight: this.editForm.get(['weight']).value,
      assetName: this.editForm.get(['assetName']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMQuestGoalReward>>) {
    result.subscribe((res: HttpResponse<IMQuestGoalReward>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
