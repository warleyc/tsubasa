import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMCombinationCutPosition, MCombinationCutPosition } from 'app/shared/model/m-combination-cut-position.model';
import { MCombinationCutPositionService } from './m-combination-cut-position.service';
import { IMCharacter } from 'app/shared/model/m-character.model';
import { MCharacterService } from 'app/entities/m-character';

@Component({
  selector: 'jhi-m-combination-cut-position-update',
  templateUrl: './m-combination-cut-position-update.component.html'
})
export class MCombinationCutPositionUpdateComponent implements OnInit {
  isSaving: boolean;

  mcharacters: IMCharacter[];

  editForm = this.fb.group({
    id: [],
    actionCutId: [null, [Validators.required]],
    characterId: [null, [Validators.required]],
    activatorPosition: [null, [Validators.required]],
    participantPosition1: [null, [Validators.required]],
    participantPosition2: [null, [Validators.required]],
    participantPosition3: [null, [Validators.required]],
    participantPosition4: [null, [Validators.required]],
    participantPosition5: [null, [Validators.required]],
    idId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mCombinationCutPositionService: MCombinationCutPositionService,
    protected mCharacterService: MCharacterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mCombinationCutPosition }) => {
      this.updateForm(mCombinationCutPosition);
    });
    this.mCharacterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMCharacter[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMCharacter[]>) => response.body)
      )
      .subscribe((res: IMCharacter[]) => (this.mcharacters = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mCombinationCutPosition: IMCombinationCutPosition) {
    this.editForm.patchValue({
      id: mCombinationCutPosition.id,
      actionCutId: mCombinationCutPosition.actionCutId,
      characterId: mCombinationCutPosition.characterId,
      activatorPosition: mCombinationCutPosition.activatorPosition,
      participantPosition1: mCombinationCutPosition.participantPosition1,
      participantPosition2: mCombinationCutPosition.participantPosition2,
      participantPosition3: mCombinationCutPosition.participantPosition3,
      participantPosition4: mCombinationCutPosition.participantPosition4,
      participantPosition5: mCombinationCutPosition.participantPosition5,
      idId: mCombinationCutPosition.idId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mCombinationCutPosition = this.createFromForm();
    if (mCombinationCutPosition.id !== undefined) {
      this.subscribeToSaveResponse(this.mCombinationCutPositionService.update(mCombinationCutPosition));
    } else {
      this.subscribeToSaveResponse(this.mCombinationCutPositionService.create(mCombinationCutPosition));
    }
  }

  private createFromForm(): IMCombinationCutPosition {
    const entity = {
      ...new MCombinationCutPosition(),
      id: this.editForm.get(['id']).value,
      actionCutId: this.editForm.get(['actionCutId']).value,
      characterId: this.editForm.get(['characterId']).value,
      activatorPosition: this.editForm.get(['activatorPosition']).value,
      participantPosition1: this.editForm.get(['participantPosition1']).value,
      participantPosition2: this.editForm.get(['participantPosition2']).value,
      participantPosition3: this.editForm.get(['participantPosition3']).value,
      participantPosition4: this.editForm.get(['participantPosition4']).value,
      participantPosition5: this.editForm.get(['participantPosition5']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMCombinationCutPosition>>) {
    result.subscribe((res: HttpResponse<IMCombinationCutPosition>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMCharacterById(index: number, item: IMCharacter) {
    return item.id;
  }
}
