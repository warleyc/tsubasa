import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMGuildNegativeWord, MGuildNegativeWord } from 'app/shared/model/m-guild-negative-word.model';
import { MGuildNegativeWordService } from './m-guild-negative-word.service';

@Component({
  selector: 'jhi-m-guild-negative-word-update',
  templateUrl: './m-guild-negative-word-update.component.html'
})
export class MGuildNegativeWordUpdateComponent implements OnInit {
  mGuildNegativeWord: IMGuildNegativeWord;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    word: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGuildNegativeWordService: MGuildNegativeWordService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGuildNegativeWord }) => {
      this.updateForm(mGuildNegativeWord);
      this.mGuildNegativeWord = mGuildNegativeWord;
    });
  }

  updateForm(mGuildNegativeWord: IMGuildNegativeWord) {
    this.editForm.patchValue({
      id: mGuildNegativeWord.id,
      word: mGuildNegativeWord.word
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
    const mGuildNegativeWord = this.createFromForm();
    if (mGuildNegativeWord.id !== undefined) {
      this.subscribeToSaveResponse(this.mGuildNegativeWordService.update(mGuildNegativeWord));
    } else {
      this.subscribeToSaveResponse(this.mGuildNegativeWordService.create(mGuildNegativeWord));
    }
  }

  private createFromForm(): IMGuildNegativeWord {
    const entity = {
      ...new MGuildNegativeWord(),
      id: this.editForm.get(['id']).value,
      word: this.editForm.get(['word']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGuildNegativeWord>>) {
    result.subscribe((res: HttpResponse<IMGuildNegativeWord>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
