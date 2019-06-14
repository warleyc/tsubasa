import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMSellCardMedal, MSellCardMedal } from 'app/shared/model/m-sell-card-medal.model';
import { MSellCardMedalService } from './m-sell-card-medal.service';

@Component({
  selector: 'jhi-m-sell-card-medal-update',
  templateUrl: './m-sell-card-medal-update.component.html'
})
export class MSellCardMedalUpdateComponent implements OnInit {
  mSellCardMedal: IMSellCardMedal;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    medalId: [null, [Validators.required]],
    amount: [null, [Validators.required]]
  });

  constructor(protected mSellCardMedalService: MSellCardMedalService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mSellCardMedal }) => {
      this.updateForm(mSellCardMedal);
      this.mSellCardMedal = mSellCardMedal;
    });
  }

  updateForm(mSellCardMedal: IMSellCardMedal) {
    this.editForm.patchValue({
      id: mSellCardMedal.id,
      medalId: mSellCardMedal.medalId,
      amount: mSellCardMedal.amount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mSellCardMedal = this.createFromForm();
    if (mSellCardMedal.id !== undefined) {
      this.subscribeToSaveResponse(this.mSellCardMedalService.update(mSellCardMedal));
    } else {
      this.subscribeToSaveResponse(this.mSellCardMedalService.create(mSellCardMedal));
    }
  }

  private createFromForm(): IMSellCardMedal {
    const entity = {
      ...new MSellCardMedal(),
      id: this.editForm.get(['id']).value,
      medalId: this.editForm.get(['medalId']).value,
      amount: this.editForm.get(['amount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMSellCardMedal>>) {
    result.subscribe((res: HttpResponse<IMSellCardMedal>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
