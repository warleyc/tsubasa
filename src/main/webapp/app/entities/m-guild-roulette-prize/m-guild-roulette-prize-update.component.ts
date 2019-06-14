import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMGuildRoulettePrize, MGuildRoulettePrize } from 'app/shared/model/m-guild-roulette-prize.model';
import { MGuildRoulettePrizeService } from './m-guild-roulette-prize.service';

@Component({
  selector: 'jhi-m-guild-roulette-prize-update',
  templateUrl: './m-guild-roulette-prize-update.component.html'
})
export class MGuildRoulettePrizeUpdateComponent implements OnInit {
  mGuildRoulettePrize: IMGuildRoulettePrize;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    rank: [null, [Validators.required]],
    contentType: [null, [Validators.required]],
    contentId: [],
    contentAmount: [null, [Validators.required]]
  });

  constructor(
    protected mGuildRoulettePrizeService: MGuildRoulettePrizeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGuildRoulettePrize }) => {
      this.updateForm(mGuildRoulettePrize);
      this.mGuildRoulettePrize = mGuildRoulettePrize;
    });
  }

  updateForm(mGuildRoulettePrize: IMGuildRoulettePrize) {
    this.editForm.patchValue({
      id: mGuildRoulettePrize.id,
      rank: mGuildRoulettePrize.rank,
      contentType: mGuildRoulettePrize.contentType,
      contentId: mGuildRoulettePrize.contentId,
      contentAmount: mGuildRoulettePrize.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mGuildRoulettePrize = this.createFromForm();
    if (mGuildRoulettePrize.id !== undefined) {
      this.subscribeToSaveResponse(this.mGuildRoulettePrizeService.update(mGuildRoulettePrize));
    } else {
      this.subscribeToSaveResponse(this.mGuildRoulettePrizeService.create(mGuildRoulettePrize));
    }
  }

  private createFromForm(): IMGuildRoulettePrize {
    const entity = {
      ...new MGuildRoulettePrize(),
      id: this.editForm.get(['id']).value,
      rank: this.editForm.get(['rank']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGuildRoulettePrize>>) {
    result.subscribe((res: HttpResponse<IMGuildRoulettePrize>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
