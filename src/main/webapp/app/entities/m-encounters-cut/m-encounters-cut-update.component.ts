import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMEncountersCut, MEncountersCut } from 'app/shared/model/m-encounters-cut.model';
import { MEncountersCutService } from './m-encounters-cut.service';

@Component({
  selector: 'jhi-m-encounters-cut-update',
  templateUrl: './m-encounters-cut-update.component.html'
})
export class MEncountersCutUpdateComponent implements OnInit {
  mEncountersCut: IMEncountersCut;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    condition: [null, [Validators.required]],
    ballFloatCondition: [null, [Validators.required]],
    command1Type: [null, [Validators.required]],
    command1IsSkill: [null, [Validators.required]],
    command2Type: [null, [Validators.required]],
    command2IsSkill: [null, [Validators.required]],
    result: [null, [Validators.required]],
    shootResult: [null, [Validators.required]],
    isThrown: [null, [Validators.required]],
    offenseSequenceText: [null, [Validators.required]],
    defenseSequenceText: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mEncountersCutService: MEncountersCutService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mEncountersCut }) => {
      this.updateForm(mEncountersCut);
      this.mEncountersCut = mEncountersCut;
    });
  }

  updateForm(mEncountersCut: IMEncountersCut) {
    this.editForm.patchValue({
      id: mEncountersCut.id,
      condition: mEncountersCut.condition,
      ballFloatCondition: mEncountersCut.ballFloatCondition,
      command1Type: mEncountersCut.command1Type,
      command1IsSkill: mEncountersCut.command1IsSkill,
      command2Type: mEncountersCut.command2Type,
      command2IsSkill: mEncountersCut.command2IsSkill,
      result: mEncountersCut.result,
      shootResult: mEncountersCut.shootResult,
      isThrown: mEncountersCut.isThrown,
      offenseSequenceText: mEncountersCut.offenseSequenceText,
      defenseSequenceText: mEncountersCut.defenseSequenceText
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
    const mEncountersCut = this.createFromForm();
    if (mEncountersCut.id !== undefined) {
      this.subscribeToSaveResponse(this.mEncountersCutService.update(mEncountersCut));
    } else {
      this.subscribeToSaveResponse(this.mEncountersCutService.create(mEncountersCut));
    }
  }

  private createFromForm(): IMEncountersCut {
    const entity = {
      ...new MEncountersCut(),
      id: this.editForm.get(['id']).value,
      condition: this.editForm.get(['condition']).value,
      ballFloatCondition: this.editForm.get(['ballFloatCondition']).value,
      command1Type: this.editForm.get(['command1Type']).value,
      command1IsSkill: this.editForm.get(['command1IsSkill']).value,
      command2Type: this.editForm.get(['command2Type']).value,
      command2IsSkill: this.editForm.get(['command2IsSkill']).value,
      result: this.editForm.get(['result']).value,
      shootResult: this.editForm.get(['shootResult']).value,
      isThrown: this.editForm.get(['isThrown']).value,
      offenseSequenceText: this.editForm.get(['offenseSequenceText']).value,
      defenseSequenceText: this.editForm.get(['defenseSequenceText']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMEncountersCut>>) {
    result.subscribe((res: HttpResponse<IMEncountersCut>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
