import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMQuestCoop, MQuestCoop } from 'app/shared/model/m-quest-coop.model';
import { MQuestCoopService } from './m-quest-coop.service';

@Component({
  selector: 'jhi-m-quest-coop-update',
  templateUrl: './m-quest-coop-update.component.html'
})
export class MQuestCoopUpdateComponent implements OnInit {
  mQuestCoop: IMQuestCoop;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    effectRarity: [null, [Validators.required]],
    effectLevelFrom: [null, [Validators.required]],
    effectLevelTo: [null, [Validators.required]],
    choose1Weight: [null, [Validators.required]],
    choose2Weight: [null, [Validators.required]]
  });

  constructor(protected mQuestCoopService: MQuestCoopService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mQuestCoop }) => {
      this.updateForm(mQuestCoop);
      this.mQuestCoop = mQuestCoop;
    });
  }

  updateForm(mQuestCoop: IMQuestCoop) {
    this.editForm.patchValue({
      id: mQuestCoop.id,
      groupId: mQuestCoop.groupId,
      effectRarity: mQuestCoop.effectRarity,
      effectLevelFrom: mQuestCoop.effectLevelFrom,
      effectLevelTo: mQuestCoop.effectLevelTo,
      choose1Weight: mQuestCoop.choose1Weight,
      choose2Weight: mQuestCoop.choose2Weight
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mQuestCoop = this.createFromForm();
    if (mQuestCoop.id !== undefined) {
      this.subscribeToSaveResponse(this.mQuestCoopService.update(mQuestCoop));
    } else {
      this.subscribeToSaveResponse(this.mQuestCoopService.create(mQuestCoop));
    }
  }

  private createFromForm(): IMQuestCoop {
    const entity = {
      ...new MQuestCoop(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      effectRarity: this.editForm.get(['effectRarity']).value,
      effectLevelFrom: this.editForm.get(['effectLevelFrom']).value,
      effectLevelTo: this.editForm.get(['effectLevelTo']).value,
      choose1Weight: this.editForm.get(['choose1Weight']).value,
      choose2Weight: this.editForm.get(['choose2Weight']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMQuestCoop>>) {
    result.subscribe((res: HttpResponse<IMQuestCoop>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
