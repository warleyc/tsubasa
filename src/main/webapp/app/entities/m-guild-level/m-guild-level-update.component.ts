import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMGuildLevel, MGuildLevel } from 'app/shared/model/m-guild-level.model';
import { MGuildLevelService } from './m-guild-level.service';

@Component({
  selector: 'jhi-m-guild-level-update',
  templateUrl: './m-guild-level-update.component.html'
})
export class MGuildLevelUpdateComponent implements OnInit {
  mGuildLevel: IMGuildLevel;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    level: [null, [Validators.required]],
    exp: [null, [Validators.required]],
    memberCount: [null, [Validators.required]],
    recommendMemberCount: [null, [Validators.required]],
    guildMedal: [null, [Validators.required]]
  });

  constructor(protected mGuildLevelService: MGuildLevelService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGuildLevel }) => {
      this.updateForm(mGuildLevel);
      this.mGuildLevel = mGuildLevel;
    });
  }

  updateForm(mGuildLevel: IMGuildLevel) {
    this.editForm.patchValue({
      id: mGuildLevel.id,
      level: mGuildLevel.level,
      exp: mGuildLevel.exp,
      memberCount: mGuildLevel.memberCount,
      recommendMemberCount: mGuildLevel.recommendMemberCount,
      guildMedal: mGuildLevel.guildMedal
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mGuildLevel = this.createFromForm();
    if (mGuildLevel.id !== undefined) {
      this.subscribeToSaveResponse(this.mGuildLevelService.update(mGuildLevel));
    } else {
      this.subscribeToSaveResponse(this.mGuildLevelService.create(mGuildLevel));
    }
  }

  private createFromForm(): IMGuildLevel {
    const entity = {
      ...new MGuildLevel(),
      id: this.editForm.get(['id']).value,
      level: this.editForm.get(['level']).value,
      exp: this.editForm.get(['exp']).value,
      memberCount: this.editForm.get(['memberCount']).value,
      recommendMemberCount: this.editForm.get(['recommendMemberCount']).value,
      guildMedal: this.editForm.get(['guildMedal']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGuildLevel>>) {
    result.subscribe((res: HttpResponse<IMGuildLevel>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
