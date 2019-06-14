import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMTargetCharacterGroup, MTargetCharacterGroup } from 'app/shared/model/m-target-character-group.model';
import { MTargetCharacterGroupService } from './m-target-character-group.service';
import { IMCharacter } from 'app/shared/model/m-character.model';
import { MCharacterService } from 'app/entities/m-character';

@Component({
  selector: 'jhi-m-target-character-group-update',
  templateUrl: './m-target-character-group-update.component.html'
})
export class MTargetCharacterGroupUpdateComponent implements OnInit {
  mTargetCharacterGroup: IMTargetCharacterGroup;
  isSaving: boolean;

  mcharacters: IMCharacter[];

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    characterId: [null, [Validators.required]],
    idId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mTargetCharacterGroupService: MTargetCharacterGroupService,
    protected mCharacterService: MCharacterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTargetCharacterGroup }) => {
      this.updateForm(mTargetCharacterGroup);
      this.mTargetCharacterGroup = mTargetCharacterGroup;
    });
    this.mCharacterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMCharacter[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMCharacter[]>) => response.body)
      )
      .subscribe((res: IMCharacter[]) => (this.mcharacters = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mTargetCharacterGroup: IMTargetCharacterGroup) {
    this.editForm.patchValue({
      id: mTargetCharacterGroup.id,
      groupId: mTargetCharacterGroup.groupId,
      characterId: mTargetCharacterGroup.characterId,
      idId: mTargetCharacterGroup.idId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTargetCharacterGroup = this.createFromForm();
    if (mTargetCharacterGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mTargetCharacterGroupService.update(mTargetCharacterGroup));
    } else {
      this.subscribeToSaveResponse(this.mTargetCharacterGroupService.create(mTargetCharacterGroup));
    }
  }

  private createFromForm(): IMTargetCharacterGroup {
    const entity = {
      ...new MTargetCharacterGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      characterId: this.editForm.get(['characterId']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTargetCharacterGroup>>) {
    result.subscribe((res: HttpResponse<IMTargetCharacterGroup>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
