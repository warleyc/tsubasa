import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMTargetPlayableCardGroup, MTargetPlayableCardGroup } from 'app/shared/model/m-target-playable-card-group.model';
import { MTargetPlayableCardGroupService } from './m-target-playable-card-group.service';
import { IMPlayableCard } from 'app/shared/model/m-playable-card.model';
import { MPlayableCardService } from 'app/entities/m-playable-card';

@Component({
  selector: 'jhi-m-target-playable-card-group-update',
  templateUrl: './m-target-playable-card-group-update.component.html'
})
export class MTargetPlayableCardGroupUpdateComponent implements OnInit {
  mTargetPlayableCardGroup: IMTargetPlayableCardGroup;
  isSaving: boolean;

  mplayablecards: IMPlayableCard[];

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    cardId: [null, [Validators.required]],
    isShowThumbnail: [null, [Validators.required]],
    idId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mTargetPlayableCardGroupService: MTargetPlayableCardGroupService,
    protected mPlayableCardService: MPlayableCardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTargetPlayableCardGroup }) => {
      this.updateForm(mTargetPlayableCardGroup);
      this.mTargetPlayableCardGroup = mTargetPlayableCardGroup;
    });
    this.mPlayableCardService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMPlayableCard[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMPlayableCard[]>) => response.body)
      )
      .subscribe((res: IMPlayableCard[]) => (this.mplayablecards = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mTargetPlayableCardGroup: IMTargetPlayableCardGroup) {
    this.editForm.patchValue({
      id: mTargetPlayableCardGroup.id,
      groupId: mTargetPlayableCardGroup.groupId,
      cardId: mTargetPlayableCardGroup.cardId,
      isShowThumbnail: mTargetPlayableCardGroup.isShowThumbnail,
      idId: mTargetPlayableCardGroup.idId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTargetPlayableCardGroup = this.createFromForm();
    if (mTargetPlayableCardGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mTargetPlayableCardGroupService.update(mTargetPlayableCardGroup));
    } else {
      this.subscribeToSaveResponse(this.mTargetPlayableCardGroupService.create(mTargetPlayableCardGroup));
    }
  }

  private createFromForm(): IMTargetPlayableCardGroup {
    const entity = {
      ...new MTargetPlayableCardGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      cardId: this.editForm.get(['cardId']).value,
      isShowThumbnail: this.editForm.get(['isShowThumbnail']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTargetPlayableCardGroup>>) {
    result.subscribe(
      (res: HttpResponse<IMTargetPlayableCardGroup>) => this.onSaveSuccess(),
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
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackMPlayableCardById(index: number, item: IMPlayableCard) {
    return item.id;
  }
}
