import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMExtensionSale, MExtensionSale } from 'app/shared/model/m-extension-sale.model';
import { MExtensionSaleService } from './m-extension-sale.service';

@Component({
  selector: 'jhi-m-extension-sale-update',
  templateUrl: './m-extension-sale-update.component.html'
})
export class MExtensionSaleUpdateComponent implements OnInit {
  mExtensionSale: IMExtensionSale;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    menuMessage: [],
    startAt: [null, [Validators.required]],
    endAt: [null, [Validators.required]],
    type: [null, [Validators.required]],
    addExtension: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mExtensionSaleService: MExtensionSaleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mExtensionSale }) => {
      this.updateForm(mExtensionSale);
      this.mExtensionSale = mExtensionSale;
    });
  }

  updateForm(mExtensionSale: IMExtensionSale) {
    this.editForm.patchValue({
      id: mExtensionSale.id,
      menuMessage: mExtensionSale.menuMessage,
      startAt: mExtensionSale.startAt,
      endAt: mExtensionSale.endAt,
      type: mExtensionSale.type,
      addExtension: mExtensionSale.addExtension
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
    const mExtensionSale = this.createFromForm();
    if (mExtensionSale.id !== undefined) {
      this.subscribeToSaveResponse(this.mExtensionSaleService.update(mExtensionSale));
    } else {
      this.subscribeToSaveResponse(this.mExtensionSaleService.create(mExtensionSale));
    }
  }

  private createFromForm(): IMExtensionSale {
    const entity = {
      ...new MExtensionSale(),
      id: this.editForm.get(['id']).value,
      menuMessage: this.editForm.get(['menuMessage']).value,
      startAt: this.editForm.get(['startAt']).value,
      endAt: this.editForm.get(['endAt']).value,
      type: this.editForm.get(['type']).value,
      addExtension: this.editForm.get(['addExtension']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMExtensionSale>>) {
    result.subscribe((res: HttpResponse<IMExtensionSale>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
