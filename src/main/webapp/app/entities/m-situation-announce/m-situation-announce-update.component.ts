import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMSituationAnnounce, MSituationAnnounce } from 'app/shared/model/m-situation-announce.model';
import { MSituationAnnounceService } from './m-situation-announce.service';

@Component({
  selector: 'jhi-m-situation-announce-update',
  templateUrl: './m-situation-announce-update.component.html'
})
export class MSituationAnnounceUpdateComponent implements OnInit {
  mSituationAnnounce: IMSituationAnnounce;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    situationId: [null, [Validators.required]],
    groupId: [null, [Validators.required]]
  });

  constructor(
    protected mSituationAnnounceService: MSituationAnnounceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mSituationAnnounce }) => {
      this.updateForm(mSituationAnnounce);
      this.mSituationAnnounce = mSituationAnnounce;
    });
  }

  updateForm(mSituationAnnounce: IMSituationAnnounce) {
    this.editForm.patchValue({
      id: mSituationAnnounce.id,
      situationId: mSituationAnnounce.situationId,
      groupId: mSituationAnnounce.groupId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mSituationAnnounce = this.createFromForm();
    if (mSituationAnnounce.id !== undefined) {
      this.subscribeToSaveResponse(this.mSituationAnnounceService.update(mSituationAnnounce));
    } else {
      this.subscribeToSaveResponse(this.mSituationAnnounceService.create(mSituationAnnounce));
    }
  }

  private createFromForm(): IMSituationAnnounce {
    const entity = {
      ...new MSituationAnnounce(),
      id: this.editForm.get(['id']).value,
      situationId: this.editForm.get(['situationId']).value,
      groupId: this.editForm.get(['groupId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMSituationAnnounce>>) {
    result.subscribe((res: HttpResponse<IMSituationAnnounce>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
