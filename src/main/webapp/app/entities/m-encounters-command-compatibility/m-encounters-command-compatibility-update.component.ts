import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import {
  IMEncountersCommandCompatibility,
  MEncountersCommandCompatibility
} from 'app/shared/model/m-encounters-command-compatibility.model';
import { MEncountersCommandCompatibilityService } from './m-encounters-command-compatibility.service';

@Component({
  selector: 'jhi-m-encounters-command-compatibility-update',
  templateUrl: './m-encounters-command-compatibility-update.component.html'
})
export class MEncountersCommandCompatibilityUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    encountersType: [null, [Validators.required]],
    offenseCommandType: [null, [Validators.required]],
    defenseCommandType: [null, [Validators.required]],
    offenseMagnification: [null, [Validators.required]],
    defenseMagnification: [null, [Validators.required]]
  });

  constructor(
    protected mEncountersCommandCompatibilityService: MEncountersCommandCompatibilityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mEncountersCommandCompatibility }) => {
      this.updateForm(mEncountersCommandCompatibility);
    });
  }

  updateForm(mEncountersCommandCompatibility: IMEncountersCommandCompatibility) {
    this.editForm.patchValue({
      id: mEncountersCommandCompatibility.id,
      encountersType: mEncountersCommandCompatibility.encountersType,
      offenseCommandType: mEncountersCommandCompatibility.offenseCommandType,
      defenseCommandType: mEncountersCommandCompatibility.defenseCommandType,
      offenseMagnification: mEncountersCommandCompatibility.offenseMagnification,
      defenseMagnification: mEncountersCommandCompatibility.defenseMagnification
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mEncountersCommandCompatibility = this.createFromForm();
    if (mEncountersCommandCompatibility.id !== undefined) {
      this.subscribeToSaveResponse(this.mEncountersCommandCompatibilityService.update(mEncountersCommandCompatibility));
    } else {
      this.subscribeToSaveResponse(this.mEncountersCommandCompatibilityService.create(mEncountersCommandCompatibility));
    }
  }

  private createFromForm(): IMEncountersCommandCompatibility {
    const entity = {
      ...new MEncountersCommandCompatibility(),
      id: this.editForm.get(['id']).value,
      encountersType: this.editForm.get(['encountersType']).value,
      offenseCommandType: this.editForm.get(['offenseCommandType']).value,
      defenseCommandType: this.editForm.get(['defenseCommandType']).value,
      offenseMagnification: this.editForm.get(['offenseMagnification']).value,
      defenseMagnification: this.editForm.get(['defenseMagnification']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMEncountersCommandCompatibility>>) {
    result.subscribe(
      (res: HttpResponse<IMEncountersCommandCompatibility>) => this.onSaveSuccess(),
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
}
