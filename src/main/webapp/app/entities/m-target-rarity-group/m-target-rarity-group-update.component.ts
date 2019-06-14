import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMTargetRarityGroup, MTargetRarityGroup } from 'app/shared/model/m-target-rarity-group.model';
import { MTargetRarityGroupService } from './m-target-rarity-group.service';

@Component({
  selector: 'jhi-m-target-rarity-group-update',
  templateUrl: './m-target-rarity-group-update.component.html'
})
export class MTargetRarityGroupUpdateComponent implements OnInit {
  mTargetRarityGroup: IMTargetRarityGroup;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    cardRarity: [null, [Validators.required]]
  });

  constructor(
    protected mTargetRarityGroupService: MTargetRarityGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTargetRarityGroup }) => {
      this.updateForm(mTargetRarityGroup);
      this.mTargetRarityGroup = mTargetRarityGroup;
    });
  }

  updateForm(mTargetRarityGroup: IMTargetRarityGroup) {
    this.editForm.patchValue({
      id: mTargetRarityGroup.id,
      groupId: mTargetRarityGroup.groupId,
      cardRarity: mTargetRarityGroup.cardRarity
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTargetRarityGroup = this.createFromForm();
    if (mTargetRarityGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mTargetRarityGroupService.update(mTargetRarityGroup));
    } else {
      this.subscribeToSaveResponse(this.mTargetRarityGroupService.create(mTargetRarityGroup));
    }
  }

  private createFromForm(): IMTargetRarityGroup {
    const entity = {
      ...new MTargetRarityGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      cardRarity: this.editForm.get(['cardRarity']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTargetRarityGroup>>) {
    result.subscribe((res: HttpResponse<IMTargetRarityGroup>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
