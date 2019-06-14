import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMTargetTeamGroup, MTargetTeamGroup } from 'app/shared/model/m-target-team-group.model';
import { MTargetTeamGroupService } from './m-target-team-group.service';
import { IMTeam } from 'app/shared/model/m-team.model';
import { MTeamService } from 'app/entities/m-team';

@Component({
  selector: 'jhi-m-target-team-group-update',
  templateUrl: './m-target-team-group-update.component.html'
})
export class MTargetTeamGroupUpdateComponent implements OnInit {
  mTargetTeamGroup: IMTargetTeamGroup;
  isSaving: boolean;

  mteams: IMTeam[];

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    teamId: [null, [Validators.required]],
    idId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mTargetTeamGroupService: MTargetTeamGroupService,
    protected mTeamService: MTeamService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTargetTeamGroup }) => {
      this.updateForm(mTargetTeamGroup);
      this.mTargetTeamGroup = mTargetTeamGroup;
    });
    this.mTeamService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMTeam[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMTeam[]>) => response.body)
      )
      .subscribe((res: IMTeam[]) => (this.mteams = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mTargetTeamGroup: IMTargetTeamGroup) {
    this.editForm.patchValue({
      id: mTargetTeamGroup.id,
      groupId: mTargetTeamGroup.groupId,
      teamId: mTargetTeamGroup.teamId,
      idId: mTargetTeamGroup.idId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTargetTeamGroup = this.createFromForm();
    if (mTargetTeamGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mTargetTeamGroupService.update(mTargetTeamGroup));
    } else {
      this.subscribeToSaveResponse(this.mTargetTeamGroupService.create(mTargetTeamGroup));
    }
  }

  private createFromForm(): IMTargetTeamGroup {
    const entity = {
      ...new MTargetTeamGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      teamId: this.editForm.get(['teamId']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTargetTeamGroup>>) {
    result.subscribe((res: HttpResponse<IMTargetTeamGroup>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMTeamById(index: number, item: IMTeam) {
    return item.id;
  }
}
