import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMGuildChatDefaultStamp, MGuildChatDefaultStamp } from 'app/shared/model/m-guild-chat-default-stamp.model';
import { MGuildChatDefaultStampService } from './m-guild-chat-default-stamp.service';

@Component({
  selector: 'jhi-m-guild-chat-default-stamp-update',
  templateUrl: './m-guild-chat-default-stamp-update.component.html'
})
export class MGuildChatDefaultStampUpdateComponent implements OnInit {
  mGuildChatDefaultStamp: IMGuildChatDefaultStamp;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    masterId: [null, [Validators.required]]
  });

  constructor(
    protected mGuildChatDefaultStampService: MGuildChatDefaultStampService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGuildChatDefaultStamp }) => {
      this.updateForm(mGuildChatDefaultStamp);
      this.mGuildChatDefaultStamp = mGuildChatDefaultStamp;
    });
  }

  updateForm(mGuildChatDefaultStamp: IMGuildChatDefaultStamp) {
    this.editForm.patchValue({
      id: mGuildChatDefaultStamp.id,
      masterId: mGuildChatDefaultStamp.masterId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mGuildChatDefaultStamp = this.createFromForm();
    if (mGuildChatDefaultStamp.id !== undefined) {
      this.subscribeToSaveResponse(this.mGuildChatDefaultStampService.update(mGuildChatDefaultStamp));
    } else {
      this.subscribeToSaveResponse(this.mGuildChatDefaultStampService.create(mGuildChatDefaultStamp));
    }
  }

  private createFromForm(): IMGuildChatDefaultStamp {
    const entity = {
      ...new MGuildChatDefaultStamp(),
      id: this.editForm.get(['id']).value,
      masterId: this.editForm.get(['masterId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGuildChatDefaultStamp>>) {
    result.subscribe((res: HttpResponse<IMGuildChatDefaultStamp>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
