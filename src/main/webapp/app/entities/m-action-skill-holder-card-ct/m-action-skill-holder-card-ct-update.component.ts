import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMActionSkillHolderCardCt, MActionSkillHolderCardCt } from 'app/shared/model/m-action-skill-holder-card-ct.model';
import { MActionSkillHolderCardCtService } from './m-action-skill-holder-card-ct.service';
import { IMCharacter } from 'app/shared/model/m-character.model';
import { MCharacterService } from 'app/entities/m-character';

@Component({
  selector: 'jhi-m-action-skill-holder-card-ct-update',
  templateUrl: './m-action-skill-holder-card-ct-update.component.html'
})
export class MActionSkillHolderCardCtUpdateComponent implements OnInit {
  mActionSkillHolderCardCt: IMActionSkillHolderCardCt;
  isSaving: boolean;

  mcharacters: IMCharacter[];

  editForm = this.fb.group({
    id: [],
    targetCharaId: [null, [Validators.required]],
    actionMasterId: [null, [Validators.required]],
    actionSkillExp: [null, [Validators.required]],
    name: [null, [Validators.required]],
    description: [null, [Validators.required]],
    idId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mActionSkillHolderCardCtService: MActionSkillHolderCardCtService,
    protected mCharacterService: MCharacterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mActionSkillHolderCardCt }) => {
      this.updateForm(mActionSkillHolderCardCt);
      this.mActionSkillHolderCardCt = mActionSkillHolderCardCt;
    });
    this.mCharacterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMCharacter[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMCharacter[]>) => response.body)
      )
      .subscribe((res: IMCharacter[]) => (this.mcharacters = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mActionSkillHolderCardCt: IMActionSkillHolderCardCt) {
    this.editForm.patchValue({
      id: mActionSkillHolderCardCt.id,
      targetCharaId: mActionSkillHolderCardCt.targetCharaId,
      actionMasterId: mActionSkillHolderCardCt.actionMasterId,
      actionSkillExp: mActionSkillHolderCardCt.actionSkillExp,
      name: mActionSkillHolderCardCt.name,
      description: mActionSkillHolderCardCt.description,
      idId: mActionSkillHolderCardCt.idId
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
    const mActionSkillHolderCardCt = this.createFromForm();
    if (mActionSkillHolderCardCt.id !== undefined) {
      this.subscribeToSaveResponse(this.mActionSkillHolderCardCtService.update(mActionSkillHolderCardCt));
    } else {
      this.subscribeToSaveResponse(this.mActionSkillHolderCardCtService.create(mActionSkillHolderCardCt));
    }
  }

  private createFromForm(): IMActionSkillHolderCardCt {
    const entity = {
      ...new MActionSkillHolderCardCt(),
      id: this.editForm.get(['id']).value,
      targetCharaId: this.editForm.get(['targetCharaId']).value,
      actionMasterId: this.editForm.get(['actionMasterId']).value,
      actionSkillExp: this.editForm.get(['actionSkillExp']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMActionSkillHolderCardCt>>) {
    result.subscribe(
      (res: HttpResponse<IMActionSkillHolderCardCt>) => this.onSaveSuccess(),
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
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackMCharacterById(index: number, item: IMCharacter) {
    return item.id;
  }
}
