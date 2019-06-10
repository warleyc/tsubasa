import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMCut, MCut } from 'app/shared/model/m-cut.model';
import { MCutService } from './m-cut.service';

@Component({
  selector: 'jhi-m-cut-update',
  templateUrl: './m-cut-update.component.html'
})
export class MCutUpdateComponent implements OnInit {
  mCut: IMCut;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    fpA: [null, [Validators.required]],
    fpB: [null, [Validators.required]],
    fpC: [null, [Validators.required]],
    fpD: [null, [Validators.required]],
    fpE: [null, [Validators.required]],
    fpF: [null, [Validators.required]],
    gkA: [null, [Validators.required]],
    gkB: [null, [Validators.required]],
    showEnvironmentalEffect: [null, [Validators.required]],
    cutType: [null, [Validators.required]],
    groupId: [null, [Validators.required]],
    isCombiCut: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mCutService: MCutService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mCut }) => {
      this.updateForm(mCut);
      this.mCut = mCut;
    });
  }

  updateForm(mCut: IMCut) {
    this.editForm.patchValue({
      id: mCut.id,
      name: mCut.name,
      fpA: mCut.fpA,
      fpB: mCut.fpB,
      fpC: mCut.fpC,
      fpD: mCut.fpD,
      fpE: mCut.fpE,
      fpF: mCut.fpF,
      gkA: mCut.gkA,
      gkB: mCut.gkB,
      showEnvironmentalEffect: mCut.showEnvironmentalEffect,
      cutType: mCut.cutType,
      groupId: mCut.groupId,
      isCombiCut: mCut.isCombiCut
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
    const mCut = this.createFromForm();
    if (mCut.id !== undefined) {
      this.subscribeToSaveResponse(this.mCutService.update(mCut));
    } else {
      this.subscribeToSaveResponse(this.mCutService.create(mCut));
    }
  }

  private createFromForm(): IMCut {
    const entity = {
      ...new MCut(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      fpA: this.editForm.get(['fpA']).value,
      fpB: this.editForm.get(['fpB']).value,
      fpC: this.editForm.get(['fpC']).value,
      fpD: this.editForm.get(['fpD']).value,
      fpE: this.editForm.get(['fpE']).value,
      fpF: this.editForm.get(['fpF']).value,
      gkA: this.editForm.get(['gkA']).value,
      gkB: this.editForm.get(['gkB']).value,
      showEnvironmentalEffect: this.editForm.get(['showEnvironmentalEffect']).value,
      cutType: this.editForm.get(['cutType']).value,
      groupId: this.editForm.get(['groupId']).value,
      isCombiCut: this.editForm.get(['isCombiCut']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMCut>>) {
    result.subscribe((res: HttpResponse<IMCut>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
