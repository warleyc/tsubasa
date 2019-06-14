import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMMarathonEffectiveCard, MMarathonEffectiveCard } from 'app/shared/model/m-marathon-effective-card.model';
import { MMarathonEffectiveCardService } from './m-marathon-effective-card.service';

@Component({
  selector: 'jhi-m-marathon-effective-card-update',
  templateUrl: './m-marathon-effective-card-update.component.html'
})
export class MMarathonEffectiveCardUpdateComponent implements OnInit {
  mMarathonEffectiveCard: IMMarathonEffectiveCard;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    eventId: [null, [Validators.required]],
    playableCardId: [null, [Validators.required]],
    rate: [null, [Validators.required]]
  });

  constructor(
    protected mMarathonEffectiveCardService: MMarathonEffectiveCardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMarathonEffectiveCard }) => {
      this.updateForm(mMarathonEffectiveCard);
      this.mMarathonEffectiveCard = mMarathonEffectiveCard;
    });
  }

  updateForm(mMarathonEffectiveCard: IMMarathonEffectiveCard) {
    this.editForm.patchValue({
      id: mMarathonEffectiveCard.id,
      eventId: mMarathonEffectiveCard.eventId,
      playableCardId: mMarathonEffectiveCard.playableCardId,
      rate: mMarathonEffectiveCard.rate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mMarathonEffectiveCard = this.createFromForm();
    if (mMarathonEffectiveCard.id !== undefined) {
      this.subscribeToSaveResponse(this.mMarathonEffectiveCardService.update(mMarathonEffectiveCard));
    } else {
      this.subscribeToSaveResponse(this.mMarathonEffectiveCardService.create(mMarathonEffectiveCard));
    }
  }

  private createFromForm(): IMMarathonEffectiveCard {
    const entity = {
      ...new MMarathonEffectiveCard(),
      id: this.editForm.get(['id']).value,
      eventId: this.editForm.get(['eventId']).value,
      playableCardId: this.editForm.get(['playableCardId']).value,
      rate: this.editForm.get(['rate']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMarathonEffectiveCard>>) {
    result.subscribe((res: HttpResponse<IMMarathonEffectiveCard>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
