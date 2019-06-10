import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import {
  IMGachaRenditionBeforeShootCutInCharacterNum,
  MGachaRenditionBeforeShootCutInCharacterNum
} from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-character-num.model';
import { MGachaRenditionBeforeShootCutInCharacterNumService } from './m-gacha-rendition-before-shoot-cut-in-character-num.service';

@Component({
  selector: 'jhi-m-gacha-rendition-before-shoot-cut-in-character-num-update',
  templateUrl: './m-gacha-rendition-before-shoot-cut-in-character-num-update.component.html'
})
export class MGachaRenditionBeforeShootCutInCharacterNumUpdateComponent implements OnInit {
  mGachaRenditionBeforeShootCutInCharacterNum: IMGachaRenditionBeforeShootCutInCharacterNum;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    isManySsr: [null, [Validators.required]],
    isSsr: [null, [Validators.required]],
    pattern: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    num: [null, [Validators.required]]
  });

  constructor(
    protected mGachaRenditionBeforeShootCutInCharacterNumService: MGachaRenditionBeforeShootCutInCharacterNumService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGachaRenditionBeforeShootCutInCharacterNum }) => {
      this.updateForm(mGachaRenditionBeforeShootCutInCharacterNum);
      this.mGachaRenditionBeforeShootCutInCharacterNum = mGachaRenditionBeforeShootCutInCharacterNum;
    });
  }

  updateForm(mGachaRenditionBeforeShootCutInCharacterNum: IMGachaRenditionBeforeShootCutInCharacterNum) {
    this.editForm.patchValue({
      id: mGachaRenditionBeforeShootCutInCharacterNum.id,
      isManySsr: mGachaRenditionBeforeShootCutInCharacterNum.isManySsr,
      isSsr: mGachaRenditionBeforeShootCutInCharacterNum.isSsr,
      pattern: mGachaRenditionBeforeShootCutInCharacterNum.pattern,
      weight: mGachaRenditionBeforeShootCutInCharacterNum.weight,
      num: mGachaRenditionBeforeShootCutInCharacterNum.num
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mGachaRenditionBeforeShootCutInCharacterNum = this.createFromForm();
    if (mGachaRenditionBeforeShootCutInCharacterNum.id !== undefined) {
      this.subscribeToSaveResponse(
        this.mGachaRenditionBeforeShootCutInCharacterNumService.update(mGachaRenditionBeforeShootCutInCharacterNum)
      );
    } else {
      this.subscribeToSaveResponse(
        this.mGachaRenditionBeforeShootCutInCharacterNumService.create(mGachaRenditionBeforeShootCutInCharacterNum)
      );
    }
  }

  private createFromForm(): IMGachaRenditionBeforeShootCutInCharacterNum {
    const entity = {
      ...new MGachaRenditionBeforeShootCutInCharacterNum(),
      id: this.editForm.get(['id']).value,
      isManySsr: this.editForm.get(['isManySsr']).value,
      isSsr: this.editForm.get(['isSsr']).value,
      pattern: this.editForm.get(['pattern']).value,
      weight: this.editForm.get(['weight']).value,
      num: this.editForm.get(['num']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGachaRenditionBeforeShootCutInCharacterNum>>) {
    result.subscribe(
      (res: HttpResponse<IMGachaRenditionBeforeShootCutInCharacterNum>) => this.onSaveSuccess(),
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
