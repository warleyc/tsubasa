import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMTargetNationalityGroup, MTargetNationalityGroup } from 'app/shared/model/m-target-nationality-group.model';
import { MTargetNationalityGroupService } from './m-target-nationality-group.service';
import { IMNationality } from 'app/shared/model/m-nationality.model';
import { MNationalityService } from 'app/entities/m-nationality';

@Component({
  selector: 'jhi-m-target-nationality-group-update',
  templateUrl: './m-target-nationality-group-update.component.html'
})
export class MTargetNationalityGroupUpdateComponent implements OnInit {
  mTargetNationalityGroup: IMTargetNationalityGroup;
  isSaving: boolean;

  mnationalities: IMNationality[];

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    nationalityId: [null, [Validators.required]],
    mnationalityId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mTargetNationalityGroupService: MTargetNationalityGroupService,
    protected mNationalityService: MNationalityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTargetNationalityGroup }) => {
      this.updateForm(mTargetNationalityGroup);
      this.mTargetNationalityGroup = mTargetNationalityGroup;
    });
    this.mNationalityService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMNationality[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMNationality[]>) => response.body)
      )
      .subscribe((res: IMNationality[]) => (this.mnationalities = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mTargetNationalityGroup: IMTargetNationalityGroup) {
    this.editForm.patchValue({
      id: mTargetNationalityGroup.id,
      groupId: mTargetNationalityGroup.groupId,
      nationalityId: mTargetNationalityGroup.nationalityId,
      mnationalityId: mTargetNationalityGroup.mnationalityId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTargetNationalityGroup = this.createFromForm();
    if (mTargetNationalityGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mTargetNationalityGroupService.update(mTargetNationalityGroup));
    } else {
      this.subscribeToSaveResponse(this.mTargetNationalityGroupService.create(mTargetNationalityGroup));
    }
  }

  private createFromForm(): IMTargetNationalityGroup {
    const entity = {
      ...new MTargetNationalityGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      nationalityId: this.editForm.get(['nationalityId']).value,
      mnationalityId: this.editForm.get(['mnationalityId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTargetNationalityGroup>>) {
    result.subscribe((res: HttpResponse<IMTargetNationalityGroup>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMNationalityById(index: number, item: IMNationality) {
    return item.id;
  }
}
