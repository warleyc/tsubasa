import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMColorPalette, MColorPalette } from 'app/shared/model/m-color-palette.model';
import { MColorPaletteService } from './m-color-palette.service';

@Component({
  selector: 'jhi-m-color-palette-update',
  templateUrl: './m-color-palette-update.component.html'
})
export class MColorPaletteUpdateComponent implements OnInit {
  mColorPalette: IMColorPalette;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    color: [null, [Validators.required]],
    orderNum: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mColorPaletteService: MColorPaletteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mColorPalette }) => {
      this.updateForm(mColorPalette);
      this.mColorPalette = mColorPalette;
    });
  }

  updateForm(mColorPalette: IMColorPalette) {
    this.editForm.patchValue({
      id: mColorPalette.id,
      color: mColorPalette.color,
      orderNum: mColorPalette.orderNum
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
    const mColorPalette = this.createFromForm();
    if (mColorPalette.id !== undefined) {
      this.subscribeToSaveResponse(this.mColorPaletteService.update(mColorPalette));
    } else {
      this.subscribeToSaveResponse(this.mColorPaletteService.create(mColorPalette));
    }
  }

  private createFromForm(): IMColorPalette {
    const entity = {
      ...new MColorPalette(),
      id: this.editForm.get(['id']).value,
      color: this.editForm.get(['color']).value,
      orderNum: this.editForm.get(['orderNum']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMColorPalette>>) {
    result.subscribe((res: HttpResponse<IMColorPalette>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
