import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMGachaRenditionTrajectory, MGachaRenditionTrajectory } from 'app/shared/model/m-gacha-rendition-trajectory.model';
import { MGachaRenditionTrajectoryService } from './m-gacha-rendition-trajectory.service';

@Component({
  selector: 'jhi-m-gacha-rendition-trajectory-update',
  templateUrl: './m-gacha-rendition-trajectory-update.component.html'
})
export class MGachaRenditionTrajectoryUpdateComponent implements OnInit {
  mGachaRenditionTrajectory: IMGachaRenditionTrajectory;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    weight: [null, [Validators.required]],
    trajectoryType: [null, [Validators.required]]
  });

  constructor(
    protected mGachaRenditionTrajectoryService: MGachaRenditionTrajectoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGachaRenditionTrajectory }) => {
      this.updateForm(mGachaRenditionTrajectory);
      this.mGachaRenditionTrajectory = mGachaRenditionTrajectory;
    });
  }

  updateForm(mGachaRenditionTrajectory: IMGachaRenditionTrajectory) {
    this.editForm.patchValue({
      id: mGachaRenditionTrajectory.id,
      weight: mGachaRenditionTrajectory.weight,
      trajectoryType: mGachaRenditionTrajectory.trajectoryType
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mGachaRenditionTrajectory = this.createFromForm();
    if (mGachaRenditionTrajectory.id !== undefined) {
      this.subscribeToSaveResponse(this.mGachaRenditionTrajectoryService.update(mGachaRenditionTrajectory));
    } else {
      this.subscribeToSaveResponse(this.mGachaRenditionTrajectoryService.create(mGachaRenditionTrajectory));
    }
  }

  private createFromForm(): IMGachaRenditionTrajectory {
    const entity = {
      ...new MGachaRenditionTrajectory(),
      id: this.editForm.get(['id']).value,
      weight: this.editForm.get(['weight']).value,
      trajectoryType: this.editForm.get(['trajectoryType']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGachaRenditionTrajectory>>) {
    result.subscribe(
      (res: HttpResponse<IMGachaRenditionTrajectory>) => this.onSaveSuccess(),
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
