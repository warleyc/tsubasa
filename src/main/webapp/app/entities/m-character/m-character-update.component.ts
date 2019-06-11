import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMCharacter, MCharacter } from 'app/shared/model/m-character.model';
import { MCharacterService } from './m-character.service';

@Component({
  selector: 'jhi-m-character-update',
  templateUrl: './m-character-update.component.html'
})
export class MCharacterUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    announceName: [null, [Validators.required]],
    shortName: [null, [Validators.required]],
    characterBookPriority: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mCharacterService: MCharacterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mCharacter }) => {
      this.updateForm(mCharacter);
    });
  }

  updateForm(mCharacter: IMCharacter) {
    this.editForm.patchValue({
      id: mCharacter.id,
      name: mCharacter.name,
      announceName: mCharacter.announceName,
      shortName: mCharacter.shortName,
      characterBookPriority: mCharacter.characterBookPriority
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
    const mCharacter = this.createFromForm();
    if (mCharacter.id !== undefined) {
      this.subscribeToSaveResponse(this.mCharacterService.update(mCharacter));
    } else {
      this.subscribeToSaveResponse(this.mCharacterService.create(mCharacter));
    }
  }

  private createFromForm(): IMCharacter {
    const entity = {
      ...new MCharacter(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      announceName: this.editForm.get(['announceName']).value,
      shortName: this.editForm.get(['shortName']).value,
      characterBookPriority: this.editForm.get(['characterBookPriority']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMCharacter>>) {
    result.subscribe((res: HttpResponse<IMCharacter>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
