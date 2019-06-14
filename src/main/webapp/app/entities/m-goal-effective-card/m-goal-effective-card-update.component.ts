import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMGoalEffectiveCard, MGoalEffectiveCard } from 'app/shared/model/m-goal-effective-card.model';
import { MGoalEffectiveCardService } from './m-goal-effective-card.service';

@Component({
  selector: 'jhi-m-goal-effective-card-update',
  templateUrl: './m-goal-effective-card-update.component.html'
})
export class MGoalEffectiveCardUpdateComponent implements OnInit {
  mGoalEffectiveCard: IMGoalEffectiveCard;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    eventType: [null, [Validators.required]],
    eventId: [null, [Validators.required]],
    playableCardId: [null, [Validators.required]],
    rate: [null, [Validators.required]]
  });

  constructor(
    protected mGoalEffectiveCardService: MGoalEffectiveCardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGoalEffectiveCard }) => {
      this.updateForm(mGoalEffectiveCard);
      this.mGoalEffectiveCard = mGoalEffectiveCard;
    });
  }

  updateForm(mGoalEffectiveCard: IMGoalEffectiveCard) {
    this.editForm.patchValue({
      id: mGoalEffectiveCard.id,
      eventType: mGoalEffectiveCard.eventType,
      eventId: mGoalEffectiveCard.eventId,
      playableCardId: mGoalEffectiveCard.playableCardId,
      rate: mGoalEffectiveCard.rate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mGoalEffectiveCard = this.createFromForm();
    if (mGoalEffectiveCard.id !== undefined) {
      this.subscribeToSaveResponse(this.mGoalEffectiveCardService.update(mGoalEffectiveCard));
    } else {
      this.subscribeToSaveResponse(this.mGoalEffectiveCardService.create(mGoalEffectiveCard));
    }
  }

  private createFromForm(): IMGoalEffectiveCard {
    const entity = {
      ...new MGoalEffectiveCard(),
      id: this.editForm.get(['id']).value,
      eventType: this.editForm.get(['eventType']).value,
      eventId: this.editForm.get(['eventId']).value,
      playableCardId: this.editForm.get(['playableCardId']).value,
      rate: this.editForm.get(['rate']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGoalEffectiveCard>>) {
    result.subscribe((res: HttpResponse<IMGoalEffectiveCard>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
