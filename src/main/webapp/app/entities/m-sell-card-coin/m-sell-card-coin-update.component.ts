import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMSellCardCoin, MSellCardCoin } from 'app/shared/model/m-sell-card-coin.model';
import { MSellCardCoinService } from './m-sell-card-coin.service';

@Component({
  selector: 'jhi-m-sell-card-coin-update',
  templateUrl: './m-sell-card-coin-update.component.html'
})
export class MSellCardCoinUpdateComponent implements OnInit {
  mSellCardCoin: IMSellCardCoin;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupNum: [null, [Validators.required]],
    level: [null, [Validators.required]],
    coin: [null, [Validators.required]]
  });

  constructor(protected mSellCardCoinService: MSellCardCoinService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mSellCardCoin }) => {
      this.updateForm(mSellCardCoin);
      this.mSellCardCoin = mSellCardCoin;
    });
  }

  updateForm(mSellCardCoin: IMSellCardCoin) {
    this.editForm.patchValue({
      id: mSellCardCoin.id,
      groupNum: mSellCardCoin.groupNum,
      level: mSellCardCoin.level,
      coin: mSellCardCoin.coin
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mSellCardCoin = this.createFromForm();
    if (mSellCardCoin.id !== undefined) {
      this.subscribeToSaveResponse(this.mSellCardCoinService.update(mSellCardCoin));
    } else {
      this.subscribeToSaveResponse(this.mSellCardCoinService.create(mSellCardCoin));
    }
  }

  private createFromForm(): IMSellCardCoin {
    const entity = {
      ...new MSellCardCoin(),
      id: this.editForm.get(['id']).value,
      groupNum: this.editForm.get(['groupNum']).value,
      level: this.editForm.get(['level']).value,
      coin: this.editForm.get(['coin']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMSellCardCoin>>) {
    result.subscribe((res: HttpResponse<IMSellCardCoin>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
