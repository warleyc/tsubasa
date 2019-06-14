import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMPenaltyKickCut, MPenaltyKickCut } from 'app/shared/model/m-penalty-kick-cut.model';
import { MPenaltyKickCutService } from './m-penalty-kick-cut.service';

@Component({
  selector: 'jhi-m-penalty-kick-cut-update',
  templateUrl: './m-penalty-kick-cut-update.component.html'
})
export class MPenaltyKickCutUpdateComponent implements OnInit {
  mPenaltyKickCut: IMPenaltyKickCut;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    kickerCourse: [null, [Validators.required]],
    keeperCourse: [null, [Validators.required]],
    keeperCommand: [null, [Validators.required]],
    resultType: [null, [Validators.required]],
    offenseSequenceText: [null, [Validators.required]],
    defenseSequenceText: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mPenaltyKickCutService: MPenaltyKickCutService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mPenaltyKickCut }) => {
      this.updateForm(mPenaltyKickCut);
      this.mPenaltyKickCut = mPenaltyKickCut;
    });
  }

  updateForm(mPenaltyKickCut: IMPenaltyKickCut) {
    this.editForm.patchValue({
      id: mPenaltyKickCut.id,
      kickerCourse: mPenaltyKickCut.kickerCourse,
      keeperCourse: mPenaltyKickCut.keeperCourse,
      keeperCommand: mPenaltyKickCut.keeperCommand,
      resultType: mPenaltyKickCut.resultType,
      offenseSequenceText: mPenaltyKickCut.offenseSequenceText,
      defenseSequenceText: mPenaltyKickCut.defenseSequenceText
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
    const mPenaltyKickCut = this.createFromForm();
    if (mPenaltyKickCut.id !== undefined) {
      this.subscribeToSaveResponse(this.mPenaltyKickCutService.update(mPenaltyKickCut));
    } else {
      this.subscribeToSaveResponse(this.mPenaltyKickCutService.create(mPenaltyKickCut));
    }
  }

  private createFromForm(): IMPenaltyKickCut {
    const entity = {
      ...new MPenaltyKickCut(),
      id: this.editForm.get(['id']).value,
      kickerCourse: this.editForm.get(['kickerCourse']).value,
      keeperCourse: this.editForm.get(['keeperCourse']).value,
      keeperCommand: this.editForm.get(['keeperCommand']).value,
      resultType: this.editForm.get(['resultType']).value,
      offenseSequenceText: this.editForm.get(['offenseSequenceText']).value,
      defenseSequenceText: this.editForm.get(['defenseSequenceText']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMPenaltyKickCut>>) {
    result.subscribe((res: HttpResponse<IMPenaltyKickCut>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
