import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import {
  IMGachaRenditionTrajectoryPhoenix,
  MGachaRenditionTrajectoryPhoenix
} from 'app/shared/model/m-gacha-rendition-trajectory-phoenix.model';
import { MGachaRenditionTrajectoryPhoenixService } from './m-gacha-rendition-trajectory-phoenix.service';

@Component({
  selector: 'jhi-m-gacha-rendition-trajectory-phoenix-update',
  templateUrl: './m-gacha-rendition-trajectory-phoenix-update.component.html'
})
export class MGachaRenditionTrajectoryPhoenixUpdateComponent implements OnInit {
  mGachaRenditionTrajectoryPhoenix: IMGachaRenditionTrajectoryPhoenix;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    isPhoenix: [null, [Validators.required]],
    weight: [null, [Validators.required]]
  });

  constructor(
    protected mGachaRenditionTrajectoryPhoenixService: MGachaRenditionTrajectoryPhoenixService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGachaRenditionTrajectoryPhoenix }) => {
      this.updateForm(mGachaRenditionTrajectoryPhoenix);
      this.mGachaRenditionTrajectoryPhoenix = mGachaRenditionTrajectoryPhoenix;
    });
  }

  updateForm(mGachaRenditionTrajectoryPhoenix: IMGachaRenditionTrajectoryPhoenix) {
    this.editForm.patchValue({
      id: mGachaRenditionTrajectoryPhoenix.id,
      isPhoenix: mGachaRenditionTrajectoryPhoenix.isPhoenix,
      weight: mGachaRenditionTrajectoryPhoenix.weight
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mGachaRenditionTrajectoryPhoenix = this.createFromForm();
    if (mGachaRenditionTrajectoryPhoenix.id !== undefined) {
      this.subscribeToSaveResponse(this.mGachaRenditionTrajectoryPhoenixService.update(mGachaRenditionTrajectoryPhoenix));
    } else {
      this.subscribeToSaveResponse(this.mGachaRenditionTrajectoryPhoenixService.create(mGachaRenditionTrajectoryPhoenix));
    }
  }

  private createFromForm(): IMGachaRenditionTrajectoryPhoenix {
    const entity = {
      ...new MGachaRenditionTrajectoryPhoenix(),
      id: this.editForm.get(['id']).value,
      isPhoenix: this.editForm.get(['isPhoenix']).value,
      weight: this.editForm.get(['weight']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGachaRenditionTrajectoryPhoenix>>) {
    result.subscribe(
      (res: HttpResponse<IMGachaRenditionTrajectoryPhoenix>) => this.onSaveSuccess(),
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
