import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMCoopRoomStamp, MCoopRoomStamp } from 'app/shared/model/m-coop-room-stamp.model';
import { MCoopRoomStampService } from './m-coop-room-stamp.service';

@Component({
  selector: 'jhi-m-coop-room-stamp-update',
  templateUrl: './m-coop-room-stamp-update.component.html'
})
export class MCoopRoomStampUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    role: [null, [Validators.required]],
    orderNum: [null, [Validators.required]],
    masterId: [null, [Validators.required]]
  });

  constructor(protected mCoopRoomStampService: MCoopRoomStampService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mCoopRoomStamp }) => {
      this.updateForm(mCoopRoomStamp);
    });
  }

  updateForm(mCoopRoomStamp: IMCoopRoomStamp) {
    this.editForm.patchValue({
      id: mCoopRoomStamp.id,
      role: mCoopRoomStamp.role,
      orderNum: mCoopRoomStamp.orderNum,
      masterId: mCoopRoomStamp.masterId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mCoopRoomStamp = this.createFromForm();
    if (mCoopRoomStamp.id !== undefined) {
      this.subscribeToSaveResponse(this.mCoopRoomStampService.update(mCoopRoomStamp));
    } else {
      this.subscribeToSaveResponse(this.mCoopRoomStampService.create(mCoopRoomStamp));
    }
  }

  private createFromForm(): IMCoopRoomStamp {
    const entity = {
      ...new MCoopRoomStamp(),
      id: this.editForm.get(['id']).value,
      role: this.editForm.get(['role']).value,
      orderNum: this.editForm.get(['orderNum']).value,
      masterId: this.editForm.get(['masterId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMCoopRoomStamp>>) {
    result.subscribe((res: HttpResponse<IMCoopRoomStamp>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
