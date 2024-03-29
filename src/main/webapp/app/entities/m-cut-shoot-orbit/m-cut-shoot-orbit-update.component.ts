import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMCutShootOrbit, MCutShootOrbit } from 'app/shared/model/m-cut-shoot-orbit.model';
import { MCutShootOrbitService } from './m-cut-shoot-orbit.service';

@Component({
  selector: 'jhi-m-cut-shoot-orbit-update',
  templateUrl: './m-cut-shoot-orbit-update.component.html'
})
export class MCutShootOrbitUpdateComponent implements OnInit {
  mCutShootOrbit: IMCutShootOrbit;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    shootOrbit: [null, [Validators.required]],
    shootResult: [null, [Validators.required]],
    offenseSequenceText: [null, [Validators.required]],
    defenseSequenceText: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mCutShootOrbitService: MCutShootOrbitService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mCutShootOrbit }) => {
      this.updateForm(mCutShootOrbit);
      this.mCutShootOrbit = mCutShootOrbit;
    });
  }

  updateForm(mCutShootOrbit: IMCutShootOrbit) {
    this.editForm.patchValue({
      id: mCutShootOrbit.id,
      shootOrbit: mCutShootOrbit.shootOrbit,
      shootResult: mCutShootOrbit.shootResult,
      offenseSequenceText: mCutShootOrbit.offenseSequenceText,
      defenseSequenceText: mCutShootOrbit.defenseSequenceText
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file = event.target.files[0];
        if (isImage && !/^image\//.test(file.type)) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      () => console.log('blob added'), // sucess
      this.onError
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mCutShootOrbit = this.createFromForm();
    if (mCutShootOrbit.id !== undefined) {
      this.subscribeToSaveResponse(this.mCutShootOrbitService.update(mCutShootOrbit));
    } else {
      this.subscribeToSaveResponse(this.mCutShootOrbitService.create(mCutShootOrbit));
    }
  }

  private createFromForm(): IMCutShootOrbit {
    const entity = {
      ...new MCutShootOrbit(),
      id: this.editForm.get(['id']).value,
      shootOrbit: this.editForm.get(['shootOrbit']).value,
      shootResult: this.editForm.get(['shootResult']).value,
      offenseSequenceText: this.editForm.get(['offenseSequenceText']).value,
      defenseSequenceText: this.editForm.get(['defenseSequenceText']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMCutShootOrbit>>) {
    result.subscribe((res: HttpResponse<IMCutShootOrbit>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
