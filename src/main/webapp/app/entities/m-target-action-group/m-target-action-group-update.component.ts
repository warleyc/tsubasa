import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMTargetActionGroup, MTargetActionGroup } from 'app/shared/model/m-target-action-group.model';
import { MTargetActionGroupService } from './m-target-action-group.service';
import { IMAction } from 'app/shared/model/m-action.model';
import { MActionService } from 'app/entities/m-action';

@Component({
  selector: 'jhi-m-target-action-group-update',
  templateUrl: './m-target-action-group-update.component.html'
})
export class MTargetActionGroupUpdateComponent implements OnInit {
  mTargetActionGroup: IMTargetActionGroup;
  isSaving: boolean;

  mactions: IMAction[];

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    actionId: [null, [Validators.required]],
    mactionId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mTargetActionGroupService: MTargetActionGroupService,
    protected mActionService: MActionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTargetActionGroup }) => {
      this.updateForm(mTargetActionGroup);
      this.mTargetActionGroup = mTargetActionGroup;
    });
    this.mActionService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMAction[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMAction[]>) => response.body)
      )
      .subscribe((res: IMAction[]) => (this.mactions = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mTargetActionGroup: IMTargetActionGroup) {
    this.editForm.patchValue({
      id: mTargetActionGroup.id,
      groupId: mTargetActionGroup.groupId,
      actionId: mTargetActionGroup.actionId,
      mactionId: mTargetActionGroup.mactionId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTargetActionGroup = this.createFromForm();
    if (mTargetActionGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mTargetActionGroupService.update(mTargetActionGroup));
    } else {
      this.subscribeToSaveResponse(this.mTargetActionGroupService.create(mTargetActionGroup));
    }
  }

  private createFromForm(): IMTargetActionGroup {
    const entity = {
      ...new MTargetActionGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      actionId: this.editForm.get(['actionId']).value,
      mactionId: this.editForm.get(['mactionId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTargetActionGroup>>) {
    result.subscribe((res: HttpResponse<IMTargetActionGroup>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMActionById(index: number, item: IMAction) {
    return item.id;
  }
}
