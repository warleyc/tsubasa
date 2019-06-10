import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMActionSkillCutin, MActionSkillCutin } from 'app/shared/model/m-action-skill-cutin.model';
import { MActionSkillCutinService } from './m-action-skill-cutin.service';

@Component({
  selector: 'jhi-m-action-skill-cutin-update',
  templateUrl: './m-action-skill-cutin-update.component.html'
})
export class MActionSkillCutinUpdateComponent implements OnInit {
  mActionSkillCutin: IMActionSkillCutin;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    actionCutId: [null, [Validators.required]],
    characterId: [null, [Validators.required]],
    cutName: [null, [Validators.required]],
    type: [null, [Validators.required]],
    startSynchronize: [null, [Validators.required]],
    finishSynchronize: [null, [Validators.required]],
    startDelay: [null, [Validators.required]],
    chapter1Character: [],
    chapter1Text: [],
    chapter1SoundEvent: [],
    chapter2Character: [],
    chapter2Text: [],
    chapter2SoundEvent: [],
    chapter3Character: [],
    chapter3Text: [],
    chapter3SoundEvent: [],
    chapter4Character: [],
    chapter4Text: [],
    chapter4SoundEvent: [],
    chapter5Character: [],
    chapter5Text: [],
    chapter5SoundEvent: [],
    synchronizeText: [],
    synchronizeSoundEvent: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mActionSkillCutinService: MActionSkillCutinService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mActionSkillCutin }) => {
      this.updateForm(mActionSkillCutin);
      this.mActionSkillCutin = mActionSkillCutin;
    });
  }

  updateForm(mActionSkillCutin: IMActionSkillCutin) {
    this.editForm.patchValue({
      id: mActionSkillCutin.id,
      actionCutId: mActionSkillCutin.actionCutId,
      characterId: mActionSkillCutin.characterId,
      cutName: mActionSkillCutin.cutName,
      type: mActionSkillCutin.type,
      startSynchronize: mActionSkillCutin.startSynchronize,
      finishSynchronize: mActionSkillCutin.finishSynchronize,
      startDelay: mActionSkillCutin.startDelay,
      chapter1Character: mActionSkillCutin.chapter1Character,
      chapter1Text: mActionSkillCutin.chapter1Text,
      chapter1SoundEvent: mActionSkillCutin.chapter1SoundEvent,
      chapter2Character: mActionSkillCutin.chapter2Character,
      chapter2Text: mActionSkillCutin.chapter2Text,
      chapter2SoundEvent: mActionSkillCutin.chapter2SoundEvent,
      chapter3Character: mActionSkillCutin.chapter3Character,
      chapter3Text: mActionSkillCutin.chapter3Text,
      chapter3SoundEvent: mActionSkillCutin.chapter3SoundEvent,
      chapter4Character: mActionSkillCutin.chapter4Character,
      chapter4Text: mActionSkillCutin.chapter4Text,
      chapter4SoundEvent: mActionSkillCutin.chapter4SoundEvent,
      chapter5Character: mActionSkillCutin.chapter5Character,
      chapter5Text: mActionSkillCutin.chapter5Text,
      chapter5SoundEvent: mActionSkillCutin.chapter5SoundEvent,
      synchronizeText: mActionSkillCutin.synchronizeText,
      synchronizeSoundEvent: mActionSkillCutin.synchronizeSoundEvent
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
    const mActionSkillCutin = this.createFromForm();
    if (mActionSkillCutin.id !== undefined) {
      this.subscribeToSaveResponse(this.mActionSkillCutinService.update(mActionSkillCutin));
    } else {
      this.subscribeToSaveResponse(this.mActionSkillCutinService.create(mActionSkillCutin));
    }
  }

  private createFromForm(): IMActionSkillCutin {
    const entity = {
      ...new MActionSkillCutin(),
      id: this.editForm.get(['id']).value,
      actionCutId: this.editForm.get(['actionCutId']).value,
      characterId: this.editForm.get(['characterId']).value,
      cutName: this.editForm.get(['cutName']).value,
      type: this.editForm.get(['type']).value,
      startSynchronize: this.editForm.get(['startSynchronize']).value,
      finishSynchronize: this.editForm.get(['finishSynchronize']).value,
      startDelay: this.editForm.get(['startDelay']).value,
      chapter1Character: this.editForm.get(['chapter1Character']).value,
      chapter1Text: this.editForm.get(['chapter1Text']).value,
      chapter1SoundEvent: this.editForm.get(['chapter1SoundEvent']).value,
      chapter2Character: this.editForm.get(['chapter2Character']).value,
      chapter2Text: this.editForm.get(['chapter2Text']).value,
      chapter2SoundEvent: this.editForm.get(['chapter2SoundEvent']).value,
      chapter3Character: this.editForm.get(['chapter3Character']).value,
      chapter3Text: this.editForm.get(['chapter3Text']).value,
      chapter3SoundEvent: this.editForm.get(['chapter3SoundEvent']).value,
      chapter4Character: this.editForm.get(['chapter4Character']).value,
      chapter4Text: this.editForm.get(['chapter4Text']).value,
      chapter4SoundEvent: this.editForm.get(['chapter4SoundEvent']).value,
      chapter5Character: this.editForm.get(['chapter5Character']).value,
      chapter5Text: this.editForm.get(['chapter5Text']).value,
      chapter5SoundEvent: this.editForm.get(['chapter5SoundEvent']).value,
      synchronizeText: this.editForm.get(['synchronizeText']).value,
      synchronizeSoundEvent: this.editForm.get(['synchronizeSoundEvent']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMActionSkillCutin>>) {
    result.subscribe((res: HttpResponse<IMActionSkillCutin>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
