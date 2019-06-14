import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMTargetActionTypeGroup, MTargetActionTypeGroup } from 'app/shared/model/m-target-action-type-group.model';
import { MTargetActionTypeGroupService } from './m-target-action-type-group.service';

@Component({
  selector: 'jhi-m-target-action-type-group-update',
  templateUrl: './m-target-action-type-group-update.component.html'
})
export class MTargetActionTypeGroupUpdateComponent implements OnInit {
  mTargetActionTypeGroup: IMTargetActionTypeGroup;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    commandType: [null, [Validators.required]]
  });

  constructor(
    protected mTargetActionTypeGroupService: MTargetActionTypeGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTargetActionTypeGroup }) => {
      this.updateForm(mTargetActionTypeGroup);
      this.mTargetActionTypeGroup = mTargetActionTypeGroup;
    });
  }

  updateForm(mTargetActionTypeGroup: IMTargetActionTypeGroup) {
    this.editForm.patchValue({
      id: mTargetActionTypeGroup.id,
      groupId: mTargetActionTypeGroup.groupId,
      commandType: mTargetActionTypeGroup.commandType
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTargetActionTypeGroup = this.createFromForm();
    if (mTargetActionTypeGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mTargetActionTypeGroupService.update(mTargetActionTypeGroup));
    } else {
      this.subscribeToSaveResponse(this.mTargetActionTypeGroupService.create(mTargetActionTypeGroup));
    }
  }

  private createFromForm(): IMTargetActionTypeGroup {
    const entity = {
      ...new MTargetActionTypeGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      commandType: this.editForm.get(['commandType']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTargetActionTypeGroup>>) {
    result.subscribe((res: HttpResponse<IMTargetActionTypeGroup>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
