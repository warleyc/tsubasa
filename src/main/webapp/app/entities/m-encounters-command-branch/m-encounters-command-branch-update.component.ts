import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMEncountersCommandBranch, MEncountersCommandBranch } from 'app/shared/model/m-encounters-command-branch.model';
import { MEncountersCommandBranchService } from './m-encounters-command-branch.service';

@Component({
  selector: 'jhi-m-encounters-command-branch-update',
  templateUrl: './m-encounters-command-branch-update.component.html'
})
export class MEncountersCommandBranchUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    ballFloatCondition: [null, [Validators.required]],
    condition: [null, [Validators.required]],
    encountersType: [null, [Validators.required]],
    isSuccess: [null, [Validators.required]],
    commandType: [null, [Validators.required]],
    successRate: [null, [Validators.required]],
    looseBallRate: [null, [Validators.required]],
    touchLightlyRate: [null, [Validators.required]],
    postRate: [null, [Validators.required]]
  });

  constructor(
    protected mEncountersCommandBranchService: MEncountersCommandBranchService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mEncountersCommandBranch }) => {
      this.updateForm(mEncountersCommandBranch);
    });
  }

  updateForm(mEncountersCommandBranch: IMEncountersCommandBranch) {
    this.editForm.patchValue({
      id: mEncountersCommandBranch.id,
      ballFloatCondition: mEncountersCommandBranch.ballFloatCondition,
      condition: mEncountersCommandBranch.condition,
      encountersType: mEncountersCommandBranch.encountersType,
      isSuccess: mEncountersCommandBranch.isSuccess,
      commandType: mEncountersCommandBranch.commandType,
      successRate: mEncountersCommandBranch.successRate,
      looseBallRate: mEncountersCommandBranch.looseBallRate,
      touchLightlyRate: mEncountersCommandBranch.touchLightlyRate,
      postRate: mEncountersCommandBranch.postRate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mEncountersCommandBranch = this.createFromForm();
    if (mEncountersCommandBranch.id !== undefined) {
      this.subscribeToSaveResponse(this.mEncountersCommandBranchService.update(mEncountersCommandBranch));
    } else {
      this.subscribeToSaveResponse(this.mEncountersCommandBranchService.create(mEncountersCommandBranch));
    }
  }

  private createFromForm(): IMEncountersCommandBranch {
    const entity = {
      ...new MEncountersCommandBranch(),
      id: this.editForm.get(['id']).value,
      ballFloatCondition: this.editForm.get(['ballFloatCondition']).value,
      condition: this.editForm.get(['condition']).value,
      encountersType: this.editForm.get(['encountersType']).value,
      isSuccess: this.editForm.get(['isSuccess']).value,
      commandType: this.editForm.get(['commandType']).value,
      successRate: this.editForm.get(['successRate']).value,
      looseBallRate: this.editForm.get(['looseBallRate']).value,
      touchLightlyRate: this.editForm.get(['touchLightlyRate']).value,
      postRate: this.editForm.get(['postRate']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMEncountersCommandBranch>>) {
    result.subscribe(
      (res: HttpResponse<IMEncountersCommandBranch>) => this.onSaveSuccess(),
      (res: HttpErrorResponse) => this.onSaveError()
    );
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
