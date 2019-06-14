import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMTargetTriggerEffectGroup, MTargetTriggerEffectGroup } from 'app/shared/model/m-target-trigger-effect-group.model';
import { MTargetTriggerEffectGroupService } from './m-target-trigger-effect-group.service';
import { IMTriggerEffectBase } from 'app/shared/model/m-trigger-effect-base.model';
import { MTriggerEffectBaseService } from 'app/entities/m-trigger-effect-base';

@Component({
  selector: 'jhi-m-target-trigger-effect-group-update',
  templateUrl: './m-target-trigger-effect-group-update.component.html'
})
export class MTargetTriggerEffectGroupUpdateComponent implements OnInit {
  mTargetTriggerEffectGroup: IMTargetTriggerEffectGroup;
  isSaving: boolean;

  mtriggereffectbases: IMTriggerEffectBase[];

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    triggerEffectId: [null, [Validators.required]],
    mtriggereffectbaseId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mTargetTriggerEffectGroupService: MTargetTriggerEffectGroupService,
    protected mTriggerEffectBaseService: MTriggerEffectBaseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTargetTriggerEffectGroup }) => {
      this.updateForm(mTargetTriggerEffectGroup);
      this.mTargetTriggerEffectGroup = mTargetTriggerEffectGroup;
    });
    this.mTriggerEffectBaseService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMTriggerEffectBase[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMTriggerEffectBase[]>) => response.body)
      )
      .subscribe((res: IMTriggerEffectBase[]) => (this.mtriggereffectbases = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mTargetTriggerEffectGroup: IMTargetTriggerEffectGroup) {
    this.editForm.patchValue({
      id: mTargetTriggerEffectGroup.id,
      groupId: mTargetTriggerEffectGroup.groupId,
      triggerEffectId: mTargetTriggerEffectGroup.triggerEffectId,
      mtriggereffectbaseId: mTargetTriggerEffectGroup.mtriggereffectbaseId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTargetTriggerEffectGroup = this.createFromForm();
    if (mTargetTriggerEffectGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mTargetTriggerEffectGroupService.update(mTargetTriggerEffectGroup));
    } else {
      this.subscribeToSaveResponse(this.mTargetTriggerEffectGroupService.create(mTargetTriggerEffectGroup));
    }
  }

  private createFromForm(): IMTargetTriggerEffectGroup {
    const entity = {
      ...new MTargetTriggerEffectGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      triggerEffectId: this.editForm.get(['triggerEffectId']).value,
      mtriggereffectbaseId: this.editForm.get(['mtriggereffectbaseId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTargetTriggerEffectGroup>>) {
    result.subscribe(
      (res: HttpResponse<IMTargetTriggerEffectGroup>) => this.onSaveSuccess(),
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
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackMTriggerEffectBaseById(index: number, item: IMTriggerEffectBase) {
    return item.id;
  }
}
