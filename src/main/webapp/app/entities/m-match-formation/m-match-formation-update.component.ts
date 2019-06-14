import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMMatchFormation, MMatchFormation } from 'app/shared/model/m-match-formation.model';
import { MMatchFormationService } from './m-match-formation.service';

@Component({
  selector: 'jhi-m-match-formation-update',
  templateUrl: './m-match-formation-update.component.html'
})
export class MMatchFormationUpdateComponent implements OnInit {
  mMatchFormation: IMMatchFormation;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    position1: [null, [Validators.required]],
    position2: [null, [Validators.required]],
    position3: [null, [Validators.required]],
    position4: [null, [Validators.required]],
    position5: [null, [Validators.required]],
    position6: [null, [Validators.required]],
    position7: [null, [Validators.required]],
    position8: [null, [Validators.required]],
    position9: [null, [Validators.required]],
    position10: [null, [Validators.required]],
    position11: [null, [Validators.required]]
  });

  constructor(
    protected mMatchFormationService: MMatchFormationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMatchFormation }) => {
      this.updateForm(mMatchFormation);
      this.mMatchFormation = mMatchFormation;
    });
  }

  updateForm(mMatchFormation: IMMatchFormation) {
    this.editForm.patchValue({
      id: mMatchFormation.id,
      position1: mMatchFormation.position1,
      position2: mMatchFormation.position2,
      position3: mMatchFormation.position3,
      position4: mMatchFormation.position4,
      position5: mMatchFormation.position5,
      position6: mMatchFormation.position6,
      position7: mMatchFormation.position7,
      position8: mMatchFormation.position8,
      position9: mMatchFormation.position9,
      position10: mMatchFormation.position10,
      position11: mMatchFormation.position11
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mMatchFormation = this.createFromForm();
    if (mMatchFormation.id !== undefined) {
      this.subscribeToSaveResponse(this.mMatchFormationService.update(mMatchFormation));
    } else {
      this.subscribeToSaveResponse(this.mMatchFormationService.create(mMatchFormation));
    }
  }

  private createFromForm(): IMMatchFormation {
    const entity = {
      ...new MMatchFormation(),
      id: this.editForm.get(['id']).value,
      position1: this.editForm.get(['position1']).value,
      position2: this.editForm.get(['position2']).value,
      position3: this.editForm.get(['position3']).value,
      position4: this.editForm.get(['position4']).value,
      position5: this.editForm.get(['position5']).value,
      position6: this.editForm.get(['position6']).value,
      position7: this.editForm.get(['position7']).value,
      position8: this.editForm.get(['position8']).value,
      position9: this.editForm.get(['position9']).value,
      position10: this.editForm.get(['position10']).value,
      position11: this.editForm.get(['position11']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMatchFormation>>) {
    result.subscribe((res: HttpResponse<IMMatchFormation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
