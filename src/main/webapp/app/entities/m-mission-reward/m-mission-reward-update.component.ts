import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMMissionReward, MMissionReward } from 'app/shared/model/m-mission-reward.model';
import { MMissionRewardService } from './m-mission-reward.service';

@Component({
  selector: 'jhi-m-mission-reward-update',
  templateUrl: './m-mission-reward-update.component.html'
})
export class MMissionRewardUpdateComponent implements OnInit {
  mMissionReward: IMMissionReward;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    contentType: [null, [Validators.required]],
    contentId: [],
    contentAmount: [null, [Validators.required]]
  });

  constructor(protected mMissionRewardService: MMissionRewardService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMissionReward }) => {
      this.updateForm(mMissionReward);
      this.mMissionReward = mMissionReward;
    });
  }

  updateForm(mMissionReward: IMMissionReward) {
    this.editForm.patchValue({
      id: mMissionReward.id,
      contentType: mMissionReward.contentType,
      contentId: mMissionReward.contentId,
      contentAmount: mMissionReward.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mMissionReward = this.createFromForm();
    if (mMissionReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mMissionRewardService.update(mMissionReward));
    } else {
      this.subscribeToSaveResponse(this.mMissionRewardService.create(mMissionReward));
    }
  }

  private createFromForm(): IMMissionReward {
    const entity = {
      ...new MMissionReward(),
      id: this.editForm.get(['id']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMissionReward>>) {
    result.subscribe((res: HttpResponse<IMMissionReward>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
