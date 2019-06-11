import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMDeckCondition, MDeckCondition } from 'app/shared/model/m-deck-condition.model';
import { MDeckConditionService } from './m-deck-condition.service';

@Component({
  selector: 'jhi-m-deck-condition-update',
  templateUrl: './m-deck-condition-update.component.html'
})
export class MDeckConditionUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    targetFormationGroupId: [],
    targetCharacterGroupMinId: [],
    targetCharacterGroupMaxId: [],
    targetPlayableCardGroupMinId: [],
    targetPlayableCardGroupMaxId: [],
    targetRarityGroupId: [],
    targetAttribute: [],
    targetNationalityGroupMinId: [],
    targetNationalityGroupMaxId: [],
    targetTeamGroupMinId: [],
    targetTeamGroupMaxId: [],
    targetActionGroupMinId: [],
    targetActionGroupMaxId: [],
    targetTriggerEffectGroupMinId: [],
    targetTriggerEffectGroupMaxId: [],
    targetCharacterMinCount: [null, [Validators.required]],
    targetCharacterMaxCount: [null, [Validators.required]],
    targetPlayableCardMinCount: [null, [Validators.required]],
    targetPlayableCardMaxCount: [null, [Validators.required]],
    targetRarityMaxCount: [null, [Validators.required]],
    targetAttributeMinCount: [null, [Validators.required]],
    targetNationalityMinCount: [null, [Validators.required]],
    targetNationalityMaxCount: [null, [Validators.required]],
    targetTeamMinCount: [null, [Validators.required]],
    targetTeamMaxCount: [null, [Validators.required]],
    targetActionMinCount: [null, [Validators.required]],
    targetActionMaxCount: [null, [Validators.required]],
    targetTriggerEffectMinCount: [null, [Validators.required]],
    targetTriggerEffectMaxCount: [null, [Validators.required]],
    targetCharacterIsStartingMin: [null, [Validators.required]],
    targetCharacterIsStartingMax: [null, [Validators.required]],
    targetPlayableCardIsStartingMin: [null, [Validators.required]],
    targetPlayableCardIsStartingMax: [null, [Validators.required]],
    targetRarityIsStarting: [null, [Validators.required]],
    targetAttributeIsStarting: [null, [Validators.required]],
    targetNationalityIsStartingMin: [null, [Validators.required]],
    targetNationalityIsStartingMax: [null, [Validators.required]],
    targetTeamIsStartingMin: [null, [Validators.required]],
    targetTeamIsStartingMax: [null, [Validators.required]],
    targetActionIsStartingMin: [null, [Validators.required]],
    targetActionIsStartingMax: [null, [Validators.required]],
    targetTriggerEffectIsStartingMin: [null, [Validators.required]],
    targetTriggerEffectIsStartingMax: [null, [Validators.required]]
  });

  constructor(protected mDeckConditionService: MDeckConditionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mDeckCondition }) => {
      this.updateForm(mDeckCondition);
    });
  }

  updateForm(mDeckCondition: IMDeckCondition) {
    this.editForm.patchValue({
      id: mDeckCondition.id,
      targetFormationGroupId: mDeckCondition.targetFormationGroupId,
      targetCharacterGroupMinId: mDeckCondition.targetCharacterGroupMinId,
      targetCharacterGroupMaxId: mDeckCondition.targetCharacterGroupMaxId,
      targetPlayableCardGroupMinId: mDeckCondition.targetPlayableCardGroupMinId,
      targetPlayableCardGroupMaxId: mDeckCondition.targetPlayableCardGroupMaxId,
      targetRarityGroupId: mDeckCondition.targetRarityGroupId,
      targetAttribute: mDeckCondition.targetAttribute,
      targetNationalityGroupMinId: mDeckCondition.targetNationalityGroupMinId,
      targetNationalityGroupMaxId: mDeckCondition.targetNationalityGroupMaxId,
      targetTeamGroupMinId: mDeckCondition.targetTeamGroupMinId,
      targetTeamGroupMaxId: mDeckCondition.targetTeamGroupMaxId,
      targetActionGroupMinId: mDeckCondition.targetActionGroupMinId,
      targetActionGroupMaxId: mDeckCondition.targetActionGroupMaxId,
      targetTriggerEffectGroupMinId: mDeckCondition.targetTriggerEffectGroupMinId,
      targetTriggerEffectGroupMaxId: mDeckCondition.targetTriggerEffectGroupMaxId,
      targetCharacterMinCount: mDeckCondition.targetCharacterMinCount,
      targetCharacterMaxCount: mDeckCondition.targetCharacterMaxCount,
      targetPlayableCardMinCount: mDeckCondition.targetPlayableCardMinCount,
      targetPlayableCardMaxCount: mDeckCondition.targetPlayableCardMaxCount,
      targetRarityMaxCount: mDeckCondition.targetRarityMaxCount,
      targetAttributeMinCount: mDeckCondition.targetAttributeMinCount,
      targetNationalityMinCount: mDeckCondition.targetNationalityMinCount,
      targetNationalityMaxCount: mDeckCondition.targetNationalityMaxCount,
      targetTeamMinCount: mDeckCondition.targetTeamMinCount,
      targetTeamMaxCount: mDeckCondition.targetTeamMaxCount,
      targetActionMinCount: mDeckCondition.targetActionMinCount,
      targetActionMaxCount: mDeckCondition.targetActionMaxCount,
      targetTriggerEffectMinCount: mDeckCondition.targetTriggerEffectMinCount,
      targetTriggerEffectMaxCount: mDeckCondition.targetTriggerEffectMaxCount,
      targetCharacterIsStartingMin: mDeckCondition.targetCharacterIsStartingMin,
      targetCharacterIsStartingMax: mDeckCondition.targetCharacterIsStartingMax,
      targetPlayableCardIsStartingMin: mDeckCondition.targetPlayableCardIsStartingMin,
      targetPlayableCardIsStartingMax: mDeckCondition.targetPlayableCardIsStartingMax,
      targetRarityIsStarting: mDeckCondition.targetRarityIsStarting,
      targetAttributeIsStarting: mDeckCondition.targetAttributeIsStarting,
      targetNationalityIsStartingMin: mDeckCondition.targetNationalityIsStartingMin,
      targetNationalityIsStartingMax: mDeckCondition.targetNationalityIsStartingMax,
      targetTeamIsStartingMin: mDeckCondition.targetTeamIsStartingMin,
      targetTeamIsStartingMax: mDeckCondition.targetTeamIsStartingMax,
      targetActionIsStartingMin: mDeckCondition.targetActionIsStartingMin,
      targetActionIsStartingMax: mDeckCondition.targetActionIsStartingMax,
      targetTriggerEffectIsStartingMin: mDeckCondition.targetTriggerEffectIsStartingMin,
      targetTriggerEffectIsStartingMax: mDeckCondition.targetTriggerEffectIsStartingMax
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mDeckCondition = this.createFromForm();
    if (mDeckCondition.id !== undefined) {
      this.subscribeToSaveResponse(this.mDeckConditionService.update(mDeckCondition));
    } else {
      this.subscribeToSaveResponse(this.mDeckConditionService.create(mDeckCondition));
    }
  }

  private createFromForm(): IMDeckCondition {
    const entity = {
      ...new MDeckCondition(),
      id: this.editForm.get(['id']).value,
      targetFormationGroupId: this.editForm.get(['targetFormationGroupId']).value,
      targetCharacterGroupMinId: this.editForm.get(['targetCharacterGroupMinId']).value,
      targetCharacterGroupMaxId: this.editForm.get(['targetCharacterGroupMaxId']).value,
      targetPlayableCardGroupMinId: this.editForm.get(['targetPlayableCardGroupMinId']).value,
      targetPlayableCardGroupMaxId: this.editForm.get(['targetPlayableCardGroupMaxId']).value,
      targetRarityGroupId: this.editForm.get(['targetRarityGroupId']).value,
      targetAttribute: this.editForm.get(['targetAttribute']).value,
      targetNationalityGroupMinId: this.editForm.get(['targetNationalityGroupMinId']).value,
      targetNationalityGroupMaxId: this.editForm.get(['targetNationalityGroupMaxId']).value,
      targetTeamGroupMinId: this.editForm.get(['targetTeamGroupMinId']).value,
      targetTeamGroupMaxId: this.editForm.get(['targetTeamGroupMaxId']).value,
      targetActionGroupMinId: this.editForm.get(['targetActionGroupMinId']).value,
      targetActionGroupMaxId: this.editForm.get(['targetActionGroupMaxId']).value,
      targetTriggerEffectGroupMinId: this.editForm.get(['targetTriggerEffectGroupMinId']).value,
      targetTriggerEffectGroupMaxId: this.editForm.get(['targetTriggerEffectGroupMaxId']).value,
      targetCharacterMinCount: this.editForm.get(['targetCharacterMinCount']).value,
      targetCharacterMaxCount: this.editForm.get(['targetCharacterMaxCount']).value,
      targetPlayableCardMinCount: this.editForm.get(['targetPlayableCardMinCount']).value,
      targetPlayableCardMaxCount: this.editForm.get(['targetPlayableCardMaxCount']).value,
      targetRarityMaxCount: this.editForm.get(['targetRarityMaxCount']).value,
      targetAttributeMinCount: this.editForm.get(['targetAttributeMinCount']).value,
      targetNationalityMinCount: this.editForm.get(['targetNationalityMinCount']).value,
      targetNationalityMaxCount: this.editForm.get(['targetNationalityMaxCount']).value,
      targetTeamMinCount: this.editForm.get(['targetTeamMinCount']).value,
      targetTeamMaxCount: this.editForm.get(['targetTeamMaxCount']).value,
      targetActionMinCount: this.editForm.get(['targetActionMinCount']).value,
      targetActionMaxCount: this.editForm.get(['targetActionMaxCount']).value,
      targetTriggerEffectMinCount: this.editForm.get(['targetTriggerEffectMinCount']).value,
      targetTriggerEffectMaxCount: this.editForm.get(['targetTriggerEffectMaxCount']).value,
      targetCharacterIsStartingMin: this.editForm.get(['targetCharacterIsStartingMin']).value,
      targetCharacterIsStartingMax: this.editForm.get(['targetCharacterIsStartingMax']).value,
      targetPlayableCardIsStartingMin: this.editForm.get(['targetPlayableCardIsStartingMin']).value,
      targetPlayableCardIsStartingMax: this.editForm.get(['targetPlayableCardIsStartingMax']).value,
      targetRarityIsStarting: this.editForm.get(['targetRarityIsStarting']).value,
      targetAttributeIsStarting: this.editForm.get(['targetAttributeIsStarting']).value,
      targetNationalityIsStartingMin: this.editForm.get(['targetNationalityIsStartingMin']).value,
      targetNationalityIsStartingMax: this.editForm.get(['targetNationalityIsStartingMax']).value,
      targetTeamIsStartingMin: this.editForm.get(['targetTeamIsStartingMin']).value,
      targetTeamIsStartingMax: this.editForm.get(['targetTeamIsStartingMax']).value,
      targetActionIsStartingMin: this.editForm.get(['targetActionIsStartingMin']).value,
      targetActionIsStartingMax: this.editForm.get(['targetActionIsStartingMax']).value,
      targetTriggerEffectIsStartingMin: this.editForm.get(['targetTriggerEffectIsStartingMin']).value,
      targetTriggerEffectIsStartingMax: this.editForm.get(['targetTriggerEffectIsStartingMax']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMDeckCondition>>) {
    result.subscribe((res: HttpResponse<IMDeckCondition>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
