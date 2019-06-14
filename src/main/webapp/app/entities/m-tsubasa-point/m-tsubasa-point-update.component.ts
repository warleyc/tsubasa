import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMTsubasaPoint, MTsubasaPoint } from 'app/shared/model/m-tsubasa-point.model';
import { MTsubasaPointService } from './m-tsubasa-point.service';

@Component({
  selector: 'jhi-m-tsubasa-point-update',
  templateUrl: './m-tsubasa-point-update.component.html'
})
export class MTsubasaPointUpdateComponent implements OnInit {
  mTsubasaPoint: IMTsubasaPoint;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    matchType: [null, [Validators.required]],
    pointType: [null, [Validators.required]],
    calcType: [null, [Validators.required]],
    aValue: [null, [Validators.required]],
    bValue: [null, [Validators.required]],
    orderNum: [null, [Validators.required]]
  });

  constructor(protected mTsubasaPointService: MTsubasaPointService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTsubasaPoint }) => {
      this.updateForm(mTsubasaPoint);
      this.mTsubasaPoint = mTsubasaPoint;
    });
  }

  updateForm(mTsubasaPoint: IMTsubasaPoint) {
    this.editForm.patchValue({
      id: mTsubasaPoint.id,
      matchType: mTsubasaPoint.matchType,
      pointType: mTsubasaPoint.pointType,
      calcType: mTsubasaPoint.calcType,
      aValue: mTsubasaPoint.aValue,
      bValue: mTsubasaPoint.bValue,
      orderNum: mTsubasaPoint.orderNum
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTsubasaPoint = this.createFromForm();
    if (mTsubasaPoint.id !== undefined) {
      this.subscribeToSaveResponse(this.mTsubasaPointService.update(mTsubasaPoint));
    } else {
      this.subscribeToSaveResponse(this.mTsubasaPointService.create(mTsubasaPoint));
    }
  }

  private createFromForm(): IMTsubasaPoint {
    const entity = {
      ...new MTsubasaPoint(),
      id: this.editForm.get(['id']).value,
      matchType: this.editForm.get(['matchType']).value,
      pointType: this.editForm.get(['pointType']).value,
      calcType: this.editForm.get(['calcType']).value,
      aValue: this.editForm.get(['aValue']).value,
      bValue: this.editForm.get(['bValue']).value,
      orderNum: this.editForm.get(['orderNum']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTsubasaPoint>>) {
    result.subscribe((res: HttpResponse<IMTsubasaPoint>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
