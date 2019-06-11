import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMContentableCard, MContentableCard } from 'app/shared/model/m-contentable-card.model';
import { MContentableCardService } from './m-contentable-card.service';

@Component({
  selector: 'jhi-m-contentable-card-update',
  templateUrl: './m-contentable-card-update.component.html'
})
export class MContentableCardUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    playableCardId: [null, [Validators.required]],
    level: [null, [Validators.required]],
    actionMainLevel: [null, [Validators.required]],
    actionSub1Level: [null, [Validators.required]],
    actionSub2Level: [null, [Validators.required]],
    actionSub3Level: [null, [Validators.required]],
    actionSub4Level: [null, [Validators.required]],
    plusRate: [null, [Validators.required]]
  });

  constructor(
    protected mContentableCardService: MContentableCardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mContentableCard }) => {
      this.updateForm(mContentableCard);
    });
  }

  updateForm(mContentableCard: IMContentableCard) {
    this.editForm.patchValue({
      id: mContentableCard.id,
      playableCardId: mContentableCard.playableCardId,
      level: mContentableCard.level,
      actionMainLevel: mContentableCard.actionMainLevel,
      actionSub1Level: mContentableCard.actionSub1Level,
      actionSub2Level: mContentableCard.actionSub2Level,
      actionSub3Level: mContentableCard.actionSub3Level,
      actionSub4Level: mContentableCard.actionSub4Level,
      plusRate: mContentableCard.plusRate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mContentableCard = this.createFromForm();
    if (mContentableCard.id !== undefined) {
      this.subscribeToSaveResponse(this.mContentableCardService.update(mContentableCard));
    } else {
      this.subscribeToSaveResponse(this.mContentableCardService.create(mContentableCard));
    }
  }

  private createFromForm(): IMContentableCard {
    const entity = {
      ...new MContentableCard(),
      id: this.editForm.get(['id']).value,
      playableCardId: this.editForm.get(['playableCardId']).value,
      level: this.editForm.get(['level']).value,
      actionMainLevel: this.editForm.get(['actionMainLevel']).value,
      actionSub1Level: this.editForm.get(['actionSub1Level']).value,
      actionSub2Level: this.editForm.get(['actionSub2Level']).value,
      actionSub3Level: this.editForm.get(['actionSub3Level']).value,
      actionSub4Level: this.editForm.get(['actionSub4Level']).value,
      plusRate: this.editForm.get(['plusRate']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMContentableCard>>) {
    result.subscribe((res: HttpResponse<IMContentableCard>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
