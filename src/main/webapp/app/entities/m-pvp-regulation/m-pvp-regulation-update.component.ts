import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMPvpRegulation, MPvpRegulation } from 'app/shared/model/m-pvp-regulation.model';
import { MPvpRegulationService } from './m-pvp-regulation.service';
import { IMMatchOption } from 'app/shared/model/m-match-option.model';
import { MMatchOptionService } from 'app/entities/m-match-option';

@Component({
  selector: 'jhi-m-pvp-regulation-update',
  templateUrl: './m-pvp-regulation-update.component.html'
})
export class MPvpRegulationUpdateComponent implements OnInit {
  mPvpRegulation: IMPvpRegulation;
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
    protected mPvpRegulationService: MPvpRegulationService,
    protected mMatchOptionService: MMatchOptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mPvpRegulation }) => {
      this.updateForm(mPvpRegulation);
      this.mPvpRegulation = mPvpRegulation;
    });
    this.mMatchOptionService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMMatchOption[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMMatchOption[]>) => response.body)
      )
      .subscribe((res: IMMatchOption[]) => (this.mmatchoptions = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mPvpRegulation: IMPvpRegulation) {
    this.editForm.patchValue({
      id: mPvpRegulation.id,
      startAt: mPvpRegulation.startAt,
      endAt: mPvpRegulation.endAt,
      matchOptionId: mPvpRegulation.matchOptionId,
      deckConditionId: mPvpRegulation.deckConditionId,
      ruleTutorialId: mPvpRegulation.ruleTutorialId,
      mmatchoptionId: mPvpRegulation.mmatchoptionId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mPvpRegulation = this.createFromForm();
    if (mPvpRegulation.id !== undefined) {
      this.subscribeToSaveResponse(this.mPvpRegulationService.update(mPvpRegulation));
    } else {
      this.subscribeToSaveResponse(this.mPvpRegulationService.create(mPvpRegulation));
    }
  }

  private createFromForm(): IMPvpRegulation {
    const entity = {
      ...new MPvpRegulation(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMPvpRegulation>>) {
    result.subscribe((res: HttpResponse<IMPvpRegulation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
