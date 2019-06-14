import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMLuckRateGroup, MLuckRateGroup } from 'app/shared/model/m-luck-rate-group.model';
import { MLuckRateGroupService } from './m-luck-rate-group.service';

@Component({
  selector: 'jhi-m-luck-rate-group-update',
  templateUrl: './m-luck-rate-group-update.component.html'
})
export class MLuckRateGroupUpdateComponent implements OnInit {
  mLuckRateGroup: IMLuckRateGroup;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    rarity: [null, [Validators.required]],
    rate: [null, [Validators.required]]
  });

  constructor(protected mLuckRateGroupService: MLuckRateGroupService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mLuckRateGroup }) => {
      this.updateForm(mLuckRateGroup);
      this.mLuckRateGroup = mLuckRateGroup;
    });
  }

  updateForm(mLuckRateGroup: IMLuckRateGroup) {
    this.editForm.patchValue({
      id: mLuckRateGroup.id,
      groupId: mLuckRateGroup.groupId,
      rarity: mLuckRateGroup.rarity,
      rate: mLuckRateGroup.rate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mLuckRateGroup = this.createFromForm();
    if (mLuckRateGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mLuckRateGroupService.update(mLuckRateGroup));
    } else {
      this.subscribeToSaveResponse(this.mLuckRateGroupService.create(mLuckRateGroup));
    }
  }

  private createFromForm(): IMLuckRateGroup {
    const entity = {
      ...new MLuckRateGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      rarity: this.editForm.get(['rarity']).value,
      rate: this.editForm.get(['rate']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMLuckRateGroup>>) {
    result.subscribe((res: HttpResponse<IMLuckRateGroup>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
