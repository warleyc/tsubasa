import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMLeagueRegulation, MLeagueRegulation } from 'app/shared/model/m-league-regulation.model';
import { MLeagueRegulationService } from './m-league-regulation.service';
import { IMMatchOption } from 'app/shared/model/m-match-option.model';
import { MMatchOptionService } from 'app/entities/m-match-option';

@Component({
  selector: 'jhi-m-league-regulation-update',
  templateUrl: './m-league-regulation-update.component.html'
})
export class MLeagueRegulationUpdateComponent implements OnInit {
  mLeagueRegulation: IMLeagueRegulation;
  isSaving: boolean;

  mmatchoptions: IMMatchOption[];

  editForm = this.fb.group({
    id: [],
    startAt: [null, [Validators.required]],
    endAt: [null, [Validators.required]],
    matchOptionId: [null, [Validators.required]],
    deckConditionId: [null, [Validators.required]],
    ruleTutorialId: [null, [Validators.required]],
    mmatchoptionId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mLeagueRegulationService: MLeagueRegulationService,
    protected mMatchOptionService: MMatchOptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mLeagueRegulation }) => {
      this.updateForm(mLeagueRegulation);
      this.mLeagueRegulation = mLeagueRegulation;
    });
    this.mMatchOptionService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMMatchOption[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMMatchOption[]>) => response.body)
      )
      .subscribe((res: IMMatchOption[]) => (this.mmatchoptions = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mLeagueRegulation: IMLeagueRegulation) {
    this.editForm.patchValue({
      id: mLeagueRegulation.id,
      startAt: mLeagueRegulation.startAt,
      endAt: mLeagueRegulation.endAt,
      matchOptionId: mLeagueRegulation.matchOptionId,
      deckConditionId: mLeagueRegulation.deckConditionId,
      ruleTutorialId: mLeagueRegulation.ruleTutorialId,
      mmatchoptionId: mLeagueRegulation.mmatchoptionId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mLeagueRegulation = this.createFromForm();
    if (mLeagueRegulation.id !== undefined) {
      this.subscribeToSaveResponse(this.mLeagueRegulationService.update(mLeagueRegulation));
    } else {
      this.subscribeToSaveResponse(this.mLeagueRegulationService.create(mLeagueRegulation));
    }
  }

  private createFromForm(): IMLeagueRegulation {
    const entity = {
      ...new MLeagueRegulation(),
      id: this.editForm.get(['id']).value,
      startAt: this.editForm.get(['startAt']).value,
      endAt: this.editForm.get(['endAt']).value,
      matchOptionId: this.editForm.get(['matchOptionId']).value,
      deckConditionId: this.editForm.get(['deckConditionId']).value,
      ruleTutorialId: this.editForm.get(['ruleTutorialId']).value,
      mmatchoptionId: this.editForm.get(['mmatchoptionId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMLeagueRegulation>>) {
    result.subscribe((res: HttpResponse<IMLeagueRegulation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMMatchOptionById(index: number, item: IMMatchOption) {
    return item.id;
  }
}
