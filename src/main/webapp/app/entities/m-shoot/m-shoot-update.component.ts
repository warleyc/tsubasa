import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMShoot, MShoot } from 'app/shared/model/m-shoot.model';
import { MShootService } from './m-shoot.service';

@Component({
  selector: 'jhi-m-shoot-update',
  templateUrl: './m-shoot-update.component.html'
})
export class MShootUpdateComponent implements OnInit {
  mShoot: IMShoot;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    angleDecayType: [null, [Validators.required]],
    shootOrbit: [null, [Validators.required]],
    judgementId: [null, [Validators.required]]
  });

  constructor(protected mShootService: MShootService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mShoot }) => {
      this.updateForm(mShoot);
      this.mShoot = mShoot;
    });
  }

  updateForm(mShoot: IMShoot) {
    this.editForm.patchValue({
      id: mShoot.id,
      angleDecayType: mShoot.angleDecayType,
      shootOrbit: mShoot.shootOrbit,
      judgementId: mShoot.judgementId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mShoot = this.createFromForm();
    if (mShoot.id !== undefined) {
      this.subscribeToSaveResponse(this.mShootService.update(mShoot));
    } else {
      this.subscribeToSaveResponse(this.mShootService.create(mShoot));
    }
  }

  private createFromForm(): IMShoot {
    const entity = {
      ...new MShoot(),
      id: this.editForm.get(['id']).value,
      angleDecayType: this.editForm.get(['angleDecayType']).value,
      shootOrbit: this.editForm.get(['shootOrbit']).value,
      judgementId: this.editForm.get(['judgementId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMShoot>>) {
    result.subscribe((res: HttpResponse<IMShoot>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
