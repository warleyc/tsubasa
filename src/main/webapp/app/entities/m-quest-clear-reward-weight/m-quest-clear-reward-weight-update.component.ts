import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMQuestClearRewardWeight, MQuestClearRewardWeight } from 'app/shared/model/m-quest-clear-reward-weight.model';
import { MQuestClearRewardWeightService } from './m-quest-clear-reward-weight.service';

@Component({
  selector: 'jhi-m-quest-clear-reward-weight-update',
  templateUrl: './m-quest-clear-reward-weight-update.component.html'
})
export class MQuestClearRewardWeightUpdateComponent implements OnInit {
  mQuestClearRewardWeight: IMQuestClearRewardWeight;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    reward1: [null, [Validators.required]],
    reward2: [null, [Validators.required]],
    reward3: [null, [Validators.required]],
    reward4: [null, [Validators.required]],
    reward5: [null, [Validators.required]]
  });

  constructor(
    protected mQuestClearRewardWeightService: MQuestClearRewardWeightService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mQuestClearRewardWeight }) => {
      this.updateForm(mQuestClearRewardWeight);
      this.mQuestClearRewardWeight = mQuestClearRewardWeight;
    });
  }

  updateForm(mQuestClearRewardWeight: IMQuestClearRewardWeight) {
    this.editForm.patchValue({
      id: mQuestClearRewardWeight.id,
      reward1: mQuestClearRewardWeight.reward1,
      reward2: mQuestClearRewardWeight.reward2,
      reward3: mQuestClearRewardWeight.reward3,
      reward4: mQuestClearRewardWeight.reward4,
      reward5: mQuestClearRewardWeight.reward5
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mQuestClearRewardWeight = this.createFromForm();
    if (mQuestClearRewardWeight.id !== undefined) {
      this.subscribeToSaveResponse(this.mQuestClearRewardWeightService.update(mQuestClearRewardWeight));
    } else {
      this.subscribeToSaveResponse(this.mQuestClearRewardWeightService.create(mQuestClearRewardWeight));
    }
  }

  private createFromForm(): IMQuestClearRewardWeight {
    const entity = {
      ...new MQuestClearRewardWeight(),
      id: this.editForm.get(['id']).value,
      reward1: this.editForm.get(['reward1']).value,
      reward2: this.editForm.get(['reward2']).value,
      reward3: this.editForm.get(['reward3']).value,
      reward4: this.editForm.get(['reward4']).value,
      reward5: this.editForm.get(['reward5']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMQuestClearRewardWeight>>) {
    result.subscribe((res: HttpResponse<IMQuestClearRewardWeight>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
