import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMCharacterScoreCut, MCharacterScoreCut } from 'app/shared/model/m-character-score-cut.model';
import { MCharacterScoreCutService } from './m-character-score-cut.service';

@Component({
  selector: 'jhi-m-character-score-cut-update',
  templateUrl: './m-character-score-cut-update.component.html'
})
export class MCharacterScoreCutUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    characterId: [null, [Validators.required]],
    teamId: [null, [Validators.required]],
    scoreCutType: [null, [Validators.required]]
  });

  constructor(
    protected mCharacterScoreCutService: MCharacterScoreCutService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mCharacterScoreCut }) => {
      this.updateForm(mCharacterScoreCut);
    });
  }

  updateForm(mCharacterScoreCut: IMCharacterScoreCut) {
    this.editForm.patchValue({
      id: mCharacterScoreCut.id,
      characterId: mCharacterScoreCut.characterId,
      teamId: mCharacterScoreCut.teamId,
      scoreCutType: mCharacterScoreCut.scoreCutType
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mCharacterScoreCut = this.createFromForm();
    if (mCharacterScoreCut.id !== undefined) {
      this.subscribeToSaveResponse(this.mCharacterScoreCutService.update(mCharacterScoreCut));
    } else {
      this.subscribeToSaveResponse(this.mCharacterScoreCutService.create(mCharacterScoreCut));
    }
  }

  private createFromForm(): IMCharacterScoreCut {
    const entity = {
      ...new MCharacterScoreCut(),
      id: this.editForm.get(['id']).value,
      characterId: this.editForm.get(['characterId']).value,
      teamId: this.editForm.get(['teamId']).value,
      scoreCutType: this.editForm.get(['scoreCutType']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMCharacterScoreCut>>) {
    result.subscribe((res: HttpResponse<IMCharacterScoreCut>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
