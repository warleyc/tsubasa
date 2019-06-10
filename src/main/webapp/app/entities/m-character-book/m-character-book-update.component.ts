import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMCharacterBook, MCharacterBook } from 'app/shared/model/m-character-book.model';
import { MCharacterBookService } from './m-character-book.service';

@Component({
  selector: 'jhi-m-character-book-update',
  templateUrl: './m-character-book-update.component.html'
})
export class MCharacterBookUpdateComponent implements OnInit {
  mCharacterBook: IMCharacterBook;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    cvName: [],
    series: [null, [Validators.required]],
    height: [],
    weight: [],
    introduction: [null, [Validators.required]],
    firstAppearedComic: [null, [Validators.required]],
    firstAppearedComicLink: [null, [Validators.required]],
    skill1: [],
    skill1Comic: [],
    skill1ComicLink: [],
    skill2: [],
    skill2Comic: [],
    skill2ComicLink: [],
    skill3: [],
    skill3Comic: [],
    skill3ComicLink: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mCharacterBookService: MCharacterBookService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mCharacterBook }) => {
      this.updateForm(mCharacterBook);
      this.mCharacterBook = mCharacterBook;
    });
  }

  updateForm(mCharacterBook: IMCharacterBook) {
    this.editForm.patchValue({
      id: mCharacterBook.id,
      cvName: mCharacterBook.cvName,
      series: mCharacterBook.series,
      height: mCharacterBook.height,
      weight: mCharacterBook.weight,
      introduction: mCharacterBook.introduction,
      firstAppearedComic: mCharacterBook.firstAppearedComic,
      firstAppearedComicLink: mCharacterBook.firstAppearedComicLink,
      skill1: mCharacterBook.skill1,
      skill1Comic: mCharacterBook.skill1Comic,
      skill1ComicLink: mCharacterBook.skill1ComicLink,
      skill2: mCharacterBook.skill2,
      skill2Comic: mCharacterBook.skill2Comic,
      skill2ComicLink: mCharacterBook.skill2ComicLink,
      skill3: mCharacterBook.skill3,
      skill3Comic: mCharacterBook.skill3Comic,
      skill3ComicLink: mCharacterBook.skill3ComicLink
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
    const mCharacterBook = this.createFromForm();
    if (mCharacterBook.id !== undefined) {
      this.subscribeToSaveResponse(this.mCharacterBookService.update(mCharacterBook));
    } else {
      this.subscribeToSaveResponse(this.mCharacterBookService.create(mCharacterBook));
    }
  }

  private createFromForm(): IMCharacterBook {
    const entity = {
      ...new MCharacterBook(),
      id: this.editForm.get(['id']).value,
      cvName: this.editForm.get(['cvName']).value,
      series: this.editForm.get(['series']).value,
      height: this.editForm.get(['height']).value,
      weight: this.editForm.get(['weight']).value,
      introduction: this.editForm.get(['introduction']).value,
      firstAppearedComic: this.editForm.get(['firstAppearedComic']).value,
      firstAppearedComicLink: this.editForm.get(['firstAppearedComicLink']).value,
      skill1: this.editForm.get(['skill1']).value,
      skill1Comic: this.editForm.get(['skill1Comic']).value,
      skill1ComicLink: this.editForm.get(['skill1ComicLink']).value,
      skill2: this.editForm.get(['skill2']).value,
      skill2Comic: this.editForm.get(['skill2Comic']).value,
      skill2ComicLink: this.editForm.get(['skill2ComicLink']).value,
      skill3: this.editForm.get(['skill3']).value,
      skill3Comic: this.editForm.get(['skill3Comic']).value,
      skill3ComicLink: this.editForm.get(['skill3ComicLink']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMCharacterBook>>) {
    result.subscribe((res: HttpResponse<IMCharacterBook>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
