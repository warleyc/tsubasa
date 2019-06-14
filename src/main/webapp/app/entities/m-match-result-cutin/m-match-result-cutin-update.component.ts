import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMMatchResultCutin, MMatchResultCutin } from 'app/shared/model/m-match-result-cutin.model';
import { MMatchResultCutinService } from './m-match-result-cutin.service';
import { IMCharacter } from 'app/shared/model/m-character.model';
import { MCharacterService } from 'app/entities/m-character';

@Component({
  selector: 'jhi-m-match-result-cutin-update',
  templateUrl: './m-match-result-cutin-update.component.html'
})
export class MMatchResultCutinUpdateComponent implements OnInit {
  mMatchResultCutin: IMMatchResultCutin;
  isSaving: boolean;

  mcharacters: IMCharacter[];

  editForm = this.fb.group({
    id: [],
    characterId: [null, [Validators.required]],
    teamId: [null, [Validators.required]],
    isWin: [null, [Validators.required]],
    text: [null, [Validators.required]],
    soundEvent: [null, [Validators.required]],
    idId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mMatchResultCutinService: MMatchResultCutinService,
    protected mCharacterService: MCharacterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMatchResultCutin }) => {
      this.updateForm(mMatchResultCutin);
      this.mMatchResultCutin = mMatchResultCutin;
    });
    this.mCharacterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMCharacter[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMCharacter[]>) => response.body)
      )
      .subscribe((res: IMCharacter[]) => (this.mcharacters = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mMatchResultCutin: IMMatchResultCutin) {
    this.editForm.patchValue({
      id: mMatchResultCutin.id,
      characterId: mMatchResultCutin.characterId,
      teamId: mMatchResultCutin.teamId,
      isWin: mMatchResultCutin.isWin,
      text: mMatchResultCutin.text,
      soundEvent: mMatchResultCutin.soundEvent,
      idId: mMatchResultCutin.idId
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
    const mMatchResultCutin = this.createFromForm();
    if (mMatchResultCutin.id !== undefined) {
      this.subscribeToSaveResponse(this.mMatchResultCutinService.update(mMatchResultCutin));
    } else {
      this.subscribeToSaveResponse(this.mMatchResultCutinService.create(mMatchResultCutin));
    }
  }

  private createFromForm(): IMMatchResultCutin {
    const entity = {
      ...new MMatchResultCutin(),
      id: this.editForm.get(['id']).value,
      characterId: this.editForm.get(['characterId']).value,
      teamId: this.editForm.get(['teamId']).value,
      isWin: this.editForm.get(['isWin']).value,
      text: this.editForm.get(['text']).value,
      soundEvent: this.editForm.get(['soundEvent']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMatchResultCutin>>) {
    result.subscribe((res: HttpResponse<IMMatchResultCutin>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
