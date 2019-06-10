import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMGachaRenditionFirstGimmick, MGachaRenditionFirstGimmick } from 'app/shared/model/m-gacha-rendition-first-gimmick.model';
import { MGachaRenditionFirstGimmickService } from './m-gacha-rendition-first-gimmick.service';

@Component({
  selector: 'jhi-m-gacha-rendition-first-gimmick-update',
  templateUrl: './m-gacha-rendition-first-gimmick-update.component.html'
})
export class MGachaRenditionFirstGimmickUpdateComponent implements OnInit {
  mGachaRenditionFirstGimmick: IMGachaRenditionFirstGimmick;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    isSsr: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    birdNum: [null, [Validators.required]],
    isTickerTape: [null, [Validators.required]]
  });

  constructor(
    protected mGachaRenditionFirstGimmickService: MGachaRenditionFirstGimmickService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGachaRenditionFirstGimmick }) => {
      this.updateForm(mGachaRenditionFirstGimmick);
      this.mGachaRenditionFirstGimmick = mGachaRenditionFirstGimmick;
    });
  }

  updateForm(mGachaRenditionFirstGimmick: IMGachaRenditionFirstGimmick) {
    this.editForm.patchValue({
      id: mGachaRenditionFirstGimmick.id,
      isSsr: mGachaRenditionFirstGimmick.isSsr,
      weight: mGachaRenditionFirstGimmick.weight,
      birdNum: mGachaRenditionFirstGimmick.birdNum,
      isTickerTape: mGachaRenditionFirstGimmick.isTickerTape
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mGachaRenditionFirstGimmick = this.createFromForm();
    if (mGachaRenditionFirstGimmick.id !== undefined) {
      this.subscribeToSaveResponse(this.mGachaRenditionFirstGimmickService.update(mGachaRenditionFirstGimmick));
    } else {
      this.subscribeToSaveResponse(this.mGachaRenditionFirstGimmickService.create(mGachaRenditionFirstGimmick));
    }
  }

  private createFromForm(): IMGachaRenditionFirstGimmick {
    const entity = {
      ...new MGachaRenditionFirstGimmick(),
      id: this.editForm.get(['id']).value,
      isSsr: this.editForm.get(['isSsr']).value,
      weight: this.editForm.get(['weight']).value,
      birdNum: this.editForm.get(['birdNum']).value,
      isTickerTape: this.editForm.get(['isTickerTape']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGachaRenditionFirstGimmick>>) {
    result.subscribe(
      (res: HttpResponse<IMGachaRenditionFirstGimmick>) => this.onSaveSuccess(),
      (res: HttpErrorResponse) => this.onSaveError()
    );
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
