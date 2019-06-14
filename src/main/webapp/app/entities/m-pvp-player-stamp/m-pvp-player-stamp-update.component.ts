import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMPvpPlayerStamp, MPvpPlayerStamp } from 'app/shared/model/m-pvp-player-stamp.model';
import { MPvpPlayerStampService } from './m-pvp-player-stamp.service';

@Component({
  selector: 'jhi-m-pvp-player-stamp-update',
  templateUrl: './m-pvp-player-stamp-update.component.html'
})
export class MPvpPlayerStampUpdateComponent implements OnInit {
  mPvpPlayerStamp: IMPvpPlayerStamp;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    orderNum: [null, [Validators.required]],
    masterId: [null, [Validators.required]]
  });

  constructor(
    protected mPvpPlayerStampService: MPvpPlayerStampService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mPvpPlayerStamp }) => {
      this.updateForm(mPvpPlayerStamp);
      this.mPvpPlayerStamp = mPvpPlayerStamp;
    });
  }

  updateForm(mPvpPlayerStamp: IMPvpPlayerStamp) {
    this.editForm.patchValue({
      id: mPvpPlayerStamp.id,
      orderNum: mPvpPlayerStamp.orderNum,
      masterId: mPvpPlayerStamp.masterId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mPvpPlayerStamp = this.createFromForm();
    if (mPvpPlayerStamp.id !== undefined) {
      this.subscribeToSaveResponse(this.mPvpPlayerStampService.update(mPvpPlayerStamp));
    } else {
      this.subscribeToSaveResponse(this.mPvpPlayerStampService.create(mPvpPlayerStamp));
    }
  }

  private createFromForm(): IMPvpPlayerStamp {
    const entity = {
      ...new MPvpPlayerStamp(),
      id: this.editForm.get(['id']).value,
      orderNum: this.editForm.get(['orderNum']).value,
      masterId: this.editForm.get(['masterId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMPvpPlayerStamp>>) {
    result.subscribe((res: HttpResponse<IMPvpPlayerStamp>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
