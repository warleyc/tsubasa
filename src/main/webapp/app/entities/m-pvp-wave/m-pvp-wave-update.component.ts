import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMPvpWave, MPvpWave } from 'app/shared/model/m-pvp-wave.model';
import { MPvpWaveService } from './m-pvp-wave.service';

@Component({
  selector: 'jhi-m-pvp-wave-update',
  templateUrl: './m-pvp-wave-update.component.html'
})
export class MPvpWaveUpdateComponent implements OnInit {
  mPvpWave: IMPvpWave;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    startAt: [null, [Validators.required]],
    endAt: [null, [Validators.required]],
    isRanking: [null, [Validators.required]]
  });

  constructor(protected mPvpWaveService: MPvpWaveService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mPvpWave }) => {
      this.updateForm(mPvpWave);
      this.mPvpWave = mPvpWave;
    });
  }

  updateForm(mPvpWave: IMPvpWave) {
    this.editForm.patchValue({
      id: mPvpWave.id,
      startAt: mPvpWave.startAt,
      endAt: mPvpWave.endAt,
      isRanking: mPvpWave.isRanking
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mPvpWave = this.createFromForm();
    if (mPvpWave.id !== undefined) {
      this.subscribeToSaveResponse(this.mPvpWaveService.update(mPvpWave));
    } else {
      this.subscribeToSaveResponse(this.mPvpWaveService.create(mPvpWave));
    }
  }

  private createFromForm(): IMPvpWave {
    const entity = {
      ...new MPvpWave(),
      id: this.editForm.get(['id']).value,
      startAt: this.editForm.get(['startAt']).value,
      endAt: this.editForm.get(['endAt']).value,
      isRanking: this.editForm.get(['isRanking']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMPvpWave>>) {
    result.subscribe((res: HttpResponse<IMPvpWave>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
