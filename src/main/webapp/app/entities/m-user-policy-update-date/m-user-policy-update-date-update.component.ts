import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMUserPolicyUpdateDate, MUserPolicyUpdateDate } from 'app/shared/model/m-user-policy-update-date.model';
import { MUserPolicyUpdateDateService } from './m-user-policy-update-date.service';

@Component({
  selector: 'jhi-m-user-policy-update-date-update',
  templateUrl: './m-user-policy-update-date-update.component.html'
})
export class MUserPolicyUpdateDateUpdateComponent implements OnInit {
  mUserPolicyUpdateDate: IMUserPolicyUpdateDate;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    updateDate: [null, [Validators.required]]
  });

  constructor(
    protected mUserPolicyUpdateDateService: MUserPolicyUpdateDateService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mUserPolicyUpdateDate }) => {
      this.updateForm(mUserPolicyUpdateDate);
      this.mUserPolicyUpdateDate = mUserPolicyUpdateDate;
    });
  }

  updateForm(mUserPolicyUpdateDate: IMUserPolicyUpdateDate) {
    this.editForm.patchValue({
      id: mUserPolicyUpdateDate.id,
      updateDate: mUserPolicyUpdateDate.updateDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mUserPolicyUpdateDate = this.createFromForm();
    if (mUserPolicyUpdateDate.id !== undefined) {
      this.subscribeToSaveResponse(this.mUserPolicyUpdateDateService.update(mUserPolicyUpdateDate));
    } else {
      this.subscribeToSaveResponse(this.mUserPolicyUpdateDateService.create(mUserPolicyUpdateDate));
    }
  }

  private createFromForm(): IMUserPolicyUpdateDate {
    const entity = {
      ...new MUserPolicyUpdateDate(),
      id: this.editForm.get(['id']).value,
      updateDate: this.editForm.get(['updateDate']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMUserPolicyUpdateDate>>) {
    result.subscribe((res: HttpResponse<IMUserPolicyUpdateDate>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
