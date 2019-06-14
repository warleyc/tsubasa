import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMTargetFormationGroup, MTargetFormationGroup } from 'app/shared/model/m-target-formation-group.model';
import { MTargetFormationGroupService } from './m-target-formation-group.service';
import { IMFormation } from 'app/shared/model/m-formation.model';
import { MFormationService } from 'app/entities/m-formation';

@Component({
  selector: 'jhi-m-target-formation-group-update',
  templateUrl: './m-target-formation-group-update.component.html'
})
export class MTargetFormationGroupUpdateComponent implements OnInit {
  mTargetFormationGroup: IMTargetFormationGroup;
  isSaving: boolean;

  mformations: IMFormation[];

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    formationId: [null, [Validators.required]],
    idId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mTargetFormationGroupService: MTargetFormationGroupService,
    protected mFormationService: MFormationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTargetFormationGroup }) => {
      this.updateForm(mTargetFormationGroup);
      this.mTargetFormationGroup = mTargetFormationGroup;
    });
    this.mFormationService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMFormation[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMFormation[]>) => response.body)
      )
      .subscribe((res: IMFormation[]) => (this.mformations = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mTargetFormationGroup: IMTargetFormationGroup) {
    this.editForm.patchValue({
      id: mTargetFormationGroup.id,
      groupId: mTargetFormationGroup.groupId,
      formationId: mTargetFormationGroup.formationId,
      idId: mTargetFormationGroup.idId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTargetFormationGroup = this.createFromForm();
    if (mTargetFormationGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mTargetFormationGroupService.update(mTargetFormationGroup));
    } else {
      this.subscribeToSaveResponse(this.mTargetFormationGroupService.create(mTargetFormationGroup));
    }
  }

  private createFromForm(): IMTargetFormationGroup {
    const entity = {
      ...new MTargetFormationGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      formationId: this.editForm.get(['formationId']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTargetFormationGroup>>) {
    result.subscribe((res: HttpResponse<IMTargetFormationGroup>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMFormationById(index: number, item: IMFormation) {
    return item.id;
  }
}
