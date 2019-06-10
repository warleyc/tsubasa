import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMFullPowerPoint, MFullPowerPoint } from 'app/shared/model/m-full-power-point.model';
import { MFullPowerPointService } from './m-full-power-point.service';

@Component({
  selector: 'jhi-m-full-power-point-update',
  templateUrl: './m-full-power-point-update.component.html'
})
export class MFullPowerPointUpdateComponent implements OnInit {
  mFullPowerPoint: IMFullPowerPoint;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    pointType: [null, [Validators.required]],
    value: [null, [Validators.required]]
  });

  constructor(
    protected mFullPowerPointService: MFullPowerPointService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mFullPowerPoint }) => {
      this.updateForm(mFullPowerPoint);
      this.mFullPowerPoint = mFullPowerPoint;
    });
  }

  updateForm(mFullPowerPoint: IMFullPowerPoint) {
    this.editForm.patchValue({
      id: mFullPowerPoint.id,
      pointType: mFullPowerPoint.pointType,
      value: mFullPowerPoint.value
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mFullPowerPoint = this.createFromForm();
    if (mFullPowerPoint.id !== undefined) {
      this.subscribeToSaveResponse(this.mFullPowerPointService.update(mFullPowerPoint));
    } else {
      this.subscribeToSaveResponse(this.mFullPowerPointService.create(mFullPowerPoint));
    }
  }

  private createFromForm(): IMFullPowerPoint {
    const entity = {
      ...new MFullPowerPoint(),
      id: this.editForm.get(['id']).value,
      pointType: this.editForm.get(['pointType']).value,
      value: this.editForm.get(['value']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMFullPowerPoint>>) {
    result.subscribe((res: HttpResponse<IMFullPowerPoint>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
