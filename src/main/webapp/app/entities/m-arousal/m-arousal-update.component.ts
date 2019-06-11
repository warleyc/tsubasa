import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMArousal, MArousal } from 'app/shared/model/m-arousal.model';
import { MArousalService } from './m-arousal.service';
import { IMPlayableCard } from 'app/shared/model/m-playable-card.model';
import { MPlayableCardService } from 'app/entities/m-playable-card';

@Component({
  selector: 'jhi-m-arousal-update',
  templateUrl: './m-arousal-update.component.html'
})
export class MArousalUpdateComponent implements OnInit {
  isSaving: boolean;

  mplayablecards: IMPlayableCard[];

  editForm = this.fb.group({
    id: [],
    beforeId: [null, [Validators.required]],
    afterId: [null, [Validators.required]],
    cost: [null, [Validators.required]],
    materialGroupId: [null, [Validators.required]],
    idId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mArousalService: MArousalService,
    protected mPlayableCardService: MPlayableCardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mArousal }) => {
      this.updateForm(mArousal);
    });
    this.mPlayableCardService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMPlayableCard[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMPlayableCard[]>) => response.body)
      )
      .subscribe((res: IMPlayableCard[]) => (this.mplayablecards = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mArousal: IMArousal) {
    this.editForm.patchValue({
      id: mArousal.id,
      beforeId: mArousal.beforeId,
      afterId: mArousal.afterId,
      cost: mArousal.cost,
      materialGroupId: mArousal.materialGroupId,
      idId: mArousal.idId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mArousal = this.createFromForm();
    if (mArousal.id !== undefined) {
      this.subscribeToSaveResponse(this.mArousalService.update(mArousal));
    } else {
      this.subscribeToSaveResponse(this.mArousalService.create(mArousal));
    }
  }

  private createFromForm(): IMArousal {
    const entity = {
      ...new MArousal(),
      id: this.editForm.get(['id']).value,
      beforeId: this.editForm.get(['beforeId']).value,
      afterId: this.editForm.get(['afterId']).value,
      cost: this.editForm.get(['cost']).value,
      materialGroupId: this.editForm.get(['materialGroupId']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMArousal>>) {
    result.subscribe((res: HttpResponse<IMArousal>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackMPlayableCardById(index: number, item: IMPlayableCard) {
    return item.id;
  }
}
