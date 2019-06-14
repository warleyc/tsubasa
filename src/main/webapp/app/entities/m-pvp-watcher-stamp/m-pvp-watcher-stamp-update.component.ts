import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMPvpWatcherStamp, MPvpWatcherStamp } from 'app/shared/model/m-pvp-watcher-stamp.model';
import { MPvpWatcherStampService } from './m-pvp-watcher-stamp.service';

@Component({
  selector: 'jhi-m-pvp-watcher-stamp-update',
  templateUrl: './m-pvp-watcher-stamp-update.component.html'
})
export class MPvpWatcherStampUpdateComponent implements OnInit {
  mPvpWatcherStamp: IMPvpWatcherStamp;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    orderNum: [null, [Validators.required]],
    masterId: [null, [Validators.required]]
  });

  constructor(
    protected mPvpWatcherStampService: MPvpWatcherStampService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mPvpWatcherStamp }) => {
      this.updateForm(mPvpWatcherStamp);
      this.mPvpWatcherStamp = mPvpWatcherStamp;
    });
  }

  updateForm(mPvpWatcherStamp: IMPvpWatcherStamp) {
    this.editForm.patchValue({
      id: mPvpWatcherStamp.id,
      orderNum: mPvpWatcherStamp.orderNum,
      masterId: mPvpWatcherStamp.masterId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mPvpWatcherStamp = this.createFromForm();
    if (mPvpWatcherStamp.id !== undefined) {
      this.subscribeToSaveResponse(this.mPvpWatcherStampService.update(mPvpWatcherStamp));
    } else {
      this.subscribeToSaveResponse(this.mPvpWatcherStampService.create(mPvpWatcherStamp));
    }
  }

  private createFromForm(): IMPvpWatcherStamp {
    const entity = {
      ...new MPvpWatcherStamp(),
      id: this.editForm.get(['id']).value,
      orderNum: this.editForm.get(['orderNum']).value,
      masterId: this.editForm.get(['masterId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMPvpWatcherStamp>>) {
    result.subscribe((res: HttpResponse<IMPvpWatcherStamp>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
